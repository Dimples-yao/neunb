package com.controllers;

import com.liuzg.jswebextra.plugins.WXpayPlugin;
import com.liuzg.jswebextra.plugins.pay.configure.WXPayConfigImpl;
import com.liuzg.jswebextra.plugins.pay.model.WXResultData;
import com.liuzg.jswebextra.plugins.pay.sdk.tencent.WXPay;
import com.liuzg.jswebextra.utils.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by NEUNB_Lisy on 2017/10/24.
 */
@Controller
@RequestMapping("/wxpay")
public class WXPayController {

    private WXpayPlugin wxpayPlugin = null;
    private WXPayConfigImpl config = new WXPayConfigImpl();
    //读取配置文件
    @Autowired
    public WXPayController(@Qualifier("wxpay_config") Properties wxpayConfig) {
        new WXPayConfigImpl(wxpayConfig.getProperty("appid"),wxpayConfig.getProperty("appsecret"),wxpayConfig.getProperty("key"),wxpayConfig.getProperty("mch_id"),wxpayConfig.getProperty("certpath"));
    }
    /**
     * 统一下单
     * 已测试，成功
     * @param body 商品描述 必填
     * @param out_trade_no 商户订单号 必填
     * @param openid 用户标识 (该方法下必填)
     * @param total_fee 标价金额 必填
     * @param notify_url 通知地址	必填
     * @param trade_type 交易类型 必填
     * @param device_info 设备号 非必填
     * @param detail 商品详情 非必填
     * @param attach 附加数据 非必填
     * @param fee_type 标价币种 非必填 (若不填，默认人民币CNY)
     * @param time_start 交易起始时间 非必填
     * @param time_expire 交易结束时间 非必填
     * @param goods_tag 订单优惠标记 非必填
     * @param product_id 商品ID 非必填
     * @param limit_pay  指定支付方式 非必填
     * @param scene_info 场景信息 非必填
     */
    @RequestMapping("/doUnifiedOrder")
    @ResponseBody
    public WXResultData doUnifiedOrder(String body, String out_trade_no, String openid, String total_fee, String notify_url, String trade_type, String device_info, String detail, String attach, String fee_type, String time_start, String time_expire, String goods_tag, String product_id, String limit_pay, String scene_info,HttpServletRequest request) {
        WXResultData wxResultData = new WXResultData();
        Map<String,String> map = new HashMap<>();
        double money = Double.parseDouble(total_fee);
        HttpSession session = request.getSession();
        try {
            wxpayPlugin = new WXpayPlugin();
            wxResultData = wxpayPlugin.doUnifiedOrder(body,out_trade_no,openid, money,notify_url,trade_type,device_info,detail,attach,fee_type,time_start,time_expire,goods_tag,product_id,limit_pay,scene_info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wxResultData.getReturn_code().equals("SUCCESS")){
            session.setAttribute("prepay_id",wxResultData.getPrepay_id());
            session.setAttribute("code_url",wxResultData.getCode_url());
            map.clear();
            //返回前端的数据
            map.put("appId", wxResultData.getAppid());
            map.put("nonceStr", WXPayUtil.generateNonceStr());
            map.put("timeStamp", "" + (new Date().getTime() / 1000));
            map.put("signType", "MD5");
            map.put("package", "prepay_id=" + wxResultData.getPrepay_id());
            String sign = null;
            try {
                sign = WXPayUtil.generateSignature(map,config.getKey());
            } catch (Exception e) {
                e.printStackTrace();
            }
            map.put("paySign", sign);
            wxResultData.setData(map);
            wxResultData.setSign(sign);
            return wxResultData;
        }else {
            return wxResultData;
        }
    }

    /**
     * 获取支付的URL
     */
    @RequestMapping("/getCode_url")
    @ResponseBody
    public String getCode_url(HttpServletRequest request){
        HttpSession session = request.getSession();
        String code_url = session.getAttribute("code_url")== null ? "" :session.getAttribute("code_url").toString();
        return code_url;
    }

    /**
     * 申请退款
     * @param transaction_id 微信订单号 与“商户订单号”二选一
     * @param out_trade_no 商户订单号 与“微信订单号”二选一
     * @param out_refund_no 商户退款单号 必填
     * @param total_fee 订单金额 必填
     * @param refund_fee 退款金额 必填
     * @param refund_fee_type 货币种类(默认为 CNY 人民币)
     * @param refund_desc 退款原因 非必填
     * @param refund_account 退款资金来源 非必填
     * @return 处理结果
     */
    @RequestMapping("/doRefund")
    @ResponseBody
    public WXResultData doRefund(String transaction_id,String out_trade_no,String out_refund_no,Double total_fee,Double refund_fee,
                                 String refund_fee_type,String refund_desc,String refund_account){
        WXResultData wxResultData = new WXResultData();
        try {
            wxpayPlugin = new WXpayPlugin();
            wxResultData = wxpayPlugin.doRefund(transaction_id,out_trade_no,out_refund_no,total_fee,refund_fee,refund_fee_type,refund_desc,refund_account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 查询订单 两个参数二选一
     * 已测试，成功
     * @param transaction_id 微信订单号
     * @param out_trade_no 商户订单号
     */
    @RequestMapping("/doOrderQuery")
    @ResponseBody
    public WXResultData doOrderQuery(String transaction_id,String out_trade_no){
        WXResultData wxResultData = new WXResultData();
        try {
            wxpayPlugin = new WXpayPlugin();
            wxResultData = wxpayPlugin.doOrderQuery(transaction_id,out_trade_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 关闭订单
     * 已测试，成功
     * @param out_trade_no 商户订单号	必填
     */
    @RequestMapping("/doOrderClose")
    @ResponseBody
    public WXResultData doOrderClose(String out_trade_no){
        WXResultData wxResultData = new WXResultData();
        try {
            wxpayPlugin = new WXpayPlugin();
            wxResultData = wxpayPlugin.doOrderClose(out_trade_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 支付结果通知
     */
    @RequestMapping("/getwxPayReturn")
    @ResponseBody
    public WXResultData getwxPayReturn(HttpServletRequest request,HttpServletResponse response){
        WXResultData wxResultData = new WXResultData();
        String returnResult;
        try {
            wxpayPlugin = new WXpayPlugin();
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                outStream.flush();
                //将流转换成字符串
                String result = new String(outStream.toByteArray(), "UTF-8");
                System.out.println(result);
                wxResultData = wxpayPlugin.getWeChatPayReturn(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wxResultData.getResult_code().equals("SUCCESS")){
            returnResult = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        }else {
            returnResult = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+wxResultData.getReturn_msg()+"]]></return_msg></xml>";
        }
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(returnResult.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 退款结果通知
     */
    @RequestMapping("/getWXRefundReturn")
    @ResponseBody
    public WXResultData getWXRefundReturn(HttpServletRequest request, HttpServletResponse response){
        WXResultData wxResultData = new WXResultData();
        String returnResult;
        try {
            wxpayPlugin = new WXpayPlugin();
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                outStream.flush();
                //将流转换成字符串
                String result = new String(outStream.toByteArray(), "UTF-8");
                wxResultData = wxpayPlugin.getWXRefundReturn(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (wxResultData.getResult_code().equals("SUCCESS")){
            returnResult = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
        }else {
            returnResult = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+wxResultData.getReturn_msg()+"]]></return_msg></xml>";
        }
        try {
            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(returnResult.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

}
