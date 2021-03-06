package com.liuzg.jswebextra.plugins;


import com.liuzg.jswebextra.utils.WXDecodeUtil;
import com.liuzg.jswebextra.utils.WXPayUtil;
import com.liuzg.jswebextra.plugins.pay.configure.WXPayConfigImpl;
import com.liuzg.jswebextra.plugins.pay.model.WXResultData;
import com.liuzg.jswebextra.plugins.pay.sdk.tencent.WXPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
* 微信支付
*/
public class WXpayPlugin {
    private WXPay wxpay;
    private WXPayConfigImpl config;
    private WXPayUtil wxPayUtil;
    private WXDecodeUtil decodeUtil;

    public WXpayPlugin() throws Exception {
        config = WXPayConfigImpl.getInstance();
        wxpay = new WXPay(config);
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
    public WXResultData doUnifiedOrder(String body, String out_trade_no, String openid, Double total_fee, String notify_url , String trade_type, String device_info, String detail, String attach, String fee_type, String time_start, String time_expire, String goods_tag, String product_id, String limit_pay, String scene_info) throws Exception {
        WXResultData wxResultData = null;

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", body);
        data.put("out_trade_no", out_trade_no);
        data.put("openid",openid);
        data.put("total_fee", String.valueOf(wxPayUtil.doubleToInt(total_fee)));
        data.put("device_info", device_info);
        data.put("detail", detail);
        data.put("attach", attach);
        data.put("fee_type", fee_type);
        data.put("time_start", time_start);
        data.put("time_expire", time_expire);
        data.put("goods_tag", goods_tag);
        data.put("product_id", product_id);
        data.put("limit_pay", limit_pay);
        data.put("scene_info", scene_info);

        data.put("trade_type", trade_type);//交易类型
        data.put("notify_url",notify_url);

        try {
            Map<String, String> r = wxpay.unifiedOrder(data);
            wxResultData = wxPayUtil.mapToWXRsultData(r);
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
    public WXResultData doOrderQuery(String transaction_id,String out_trade_no	) {
        WXResultData wxResultData = new WXResultData();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("transaction_id", transaction_id);
        data.put("out_trade_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.orderQuery(data);
            wxResultData = wxPayUtil.mapToWXRsultData(r);
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
    public WXResultData doOrderClose(String out_trade_no) {
        WXResultData wxResultData = new WXResultData();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", out_trade_no);
        try {
            Map<String, String> r = wxpay.closeOrder(data);
            wxResultData = wxPayUtil.mapToWXRsultData(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
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
    public WXResultData doRefund(String transaction_id,String out_trade_no,String out_refund_no,Double total_fee,Double refund_fee,
                                 String refund_fee_type,String refund_desc,String refund_account){
        WXResultData wxResultData = null;
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("transaction_id",transaction_id);
        data.put("out_trade_no", out_trade_no);
        data.put("out_refund_no", out_refund_no);
        data.put("total_fee", String.valueOf(wxPayUtil.doubleToInt(total_fee)));
        data.put("refund_fee", String.valueOf(wxPayUtil.doubleToInt(refund_fee)));
        data.put("refund_fee_type", refund_fee_type);
        data.put("refund_desc",refund_desc);
        data.put("refund_account",refund_account);
        data.put("op_user_id", config.getMchID());

        try {
            Map<String, String> r = wxpay.refund(data);
            wxResultData = wxPayUtil.mapToWXRsultData(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 查询退款 一下四个参数->四选一即可
     * @param transaction_id 微信订单号
     * @param out_trade_no 商户订单号
     * @param out_refund_no 商户退款单号
     * @param refund_id 微信退款单号
     */
    public WXResultData doRefundQuery(String transaction_id,String out_trade_no ,String out_refund_no,String refund_id) {
        HashMap<String, String> data = new HashMap<String, String>();
        WXResultData wxResultData = new WXResultData();
        data.put("transaction_id", transaction_id);
        data.put("out_refund_no", out_trade_no);
        data.put("out_refund_no", out_refund_no);
        data.put("refund_id", refund_id);
        try {
            Map<String, String> r = wxpay.refundQuery(data);
            System.out.println(r);
            wxResultData = wxPayUtil.mapToWXRsultData(r);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 支付结果通知
     */
    /*public WXResultData getWeChatPayReturn(HttpServletRequest request, HttpServletResponse response){
        String result = null;

        WXResultData wxResultData = new WXResultData();
        String returnResult;
        try {
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                //将流转换成字符串
                result = new String(outStream.toByteArray(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,String> map = new HashMap<>();
        try {
            map = wxPayUtil.xmlToMap(result);
            wxResultData = wxPayUtil.mapToWXRsultData(map);
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
    }*/
    public WXResultData getWeChatPayReturn(String result){
        WXResultData wxResultData = null;
        Map<String,String> map = new HashMap<>();
        try {
            map = wxPayUtil.xmlToMap(result);
            wxResultData = wxPayUtil.mapToWXRsultData(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

    /**
     * 退款结果通知
     */
    /*public WXResultData getWXRefundReturn(HttpServletRequest request, HttpServletResponse response){
        Map<String,String> map = new HashMap<>();
        WXResultData wxResultData = new WXResultData();
        String returnResult;
        String result = null;
        try {
            InputStream inStream = request.getInputStream();
            int _buffer_size = 1024;
            if (inStream != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] tempBytes = new byte[_buffer_size];
                int count = -1;
                while ((count = inStream.read(tempBytes, 0, _buffer_size)) != -1) {
                    outStream.write(tempBytes, 0, count);
                }
                tempBytes = null;
                outStream.flush();
                //将流转换成字符串
                result = new String(outStream.toByteArray(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            byte[] content = decodeUtil.hexStringToByte(result);
            byte[] B =decodeUtil.base64Decode(content);//对加密串A做base64解码，得到加密串B
            String keyMd5 = decodeUtil.getPassMD5(config.getKey());//对商户key做md5
            String resultXML= decodeUtil.AESDecoding(decodeUtil.bytesToHexString(B),keyMd5);//用key*对加密串B做AES-256-ECB解密
            map = wxPayUtil.xmlToMap(resultXML);
            wxResultData = wxPayUtil.mapToWXRsultData(map);
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
    }*/
    public WXResultData getWXRefundReturn(String result){
        WXResultData wxResultData = null;
        Map<String,String> map = new HashMap<>();
        try {
            byte[] content = decodeUtil.hexStringToByte(result);
            byte[] B =decodeUtil.base64Decode(content);//对加密串A做base64解码，得到加密串B
            String keyMd5 = decodeUtil.getPassMD5(config.getKey());//对商户key做md5
            String resultXML= decodeUtil.AESDecoding(decodeUtil.bytesToHexString(B),keyMd5);//用key*对加密串B做AES-256-ECB解密
            map = wxPayUtil.xmlToMap(resultXML);
            wxResultData = wxPayUtil.mapToWXRsultData(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wxResultData;
    }

}
