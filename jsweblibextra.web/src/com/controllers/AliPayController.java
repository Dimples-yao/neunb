package com.controllers;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.liuzg.jswebextra.plugins.AlipayPlugin;
import com.liuzg.jswebextra.plugins.pay.model.AliResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/alipay")
public class AliPayController{

    AlipayPlugin alipayPlugin = new AlipayPlugin();

    /*
    * 读取配置信息
    */
    @Autowired
    public AliPayController(@Qualifier("alipay_config")Properties properties){
        new AlipayPlugin(properties.getProperty("APPID"),properties.getProperty("PID"),properties.getProperty("RSA_PRIVATE_KEY"),properties.getProperty("notify_url"),properties.getProperty("return_url"),properties.getProperty("URL"),properties.getProperty("CHARSET"),properties.getProperty("FORMAT"),properties.getProperty("ALIPAY_PUBLIC_KEY"),properties.getProperty("log_path"),properties.getProperty("SIGNTYPE"),properties.getProperty("input_charset"),properties.getProperty("sign_type"),properties.getProperty("transport"));
    }
    /**
     * 支付宝支付_手机端支付
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_amount 付款金额，必填
     * @param body 商品描述，可空
     * @param timeout_express 超时时间 可空
     * @param product_code 销售产品码 可空
     */
    @RequestMapping("/doalipay")
    @ResponseBody
    public void doalipay(String out_trade_no, String subject, String total_amount, String body, String timeout_express, String product_code,String return_url,String notify_url, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        try {
            alipayPlugin.doPay(out_trade_no,subject,total_amount,body,timeout_express,product_code,return_url,notify_url,httpRequest,httpResponse);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝订单查询_手机端
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no 支付宝订单号
     */
    @RequestMapping("/aliQuery")
    @ResponseBody
    public AlipayTradeQueryResponse aliQuery(String out_trade_no,String trade_no ){
        AlipayTradeQueryResponse alipayTradeQueryResponse = new AlipayTradeQueryResponse();
        alipayTradeQueryResponse = alipayPlugin.aliQuery(out_trade_no,trade_no);
        return alipayTradeQueryResponse;
    }

    /*
    * 异步通知_手机端
    */
    @RequestMapping("/getNotifyResultPay")
    @ResponseBody
    public AliResultData getNotifyResultPay(HttpServletRequest request){
        AliResultData aliResultData = new AliResultData();
        aliResultData = alipayPlugin.getNotifyResultPay(request);
        return aliResultData;
    }

    /*
    * 同步通知_手机端
    */
    @RequestMapping("/getReturnResultPay")
    @ResponseBody
    public AliResultData getReturnResultPay(HttpServletRequest request){
        AliResultData aliResultData = new AliResultData();
        aliResultData = alipayPlugin.getReturnResultPay(request);
        return aliResultData;
    }

    /**
     * 支付宝支付_PC端支付
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_amount 付款金额，必填
     * @param body 商品描述，可空
     */
    @RequestMapping("/doalipaypc")
    @ResponseBody
    public void doalipaypc(String out_trade_no, String subject, String total_amount, String body, String product_code, String return_url,String notify_url, HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        try {
            alipayPlugin.doPayPc(out_trade_no,subject,total_amount,body,product_code,return_url,notify_url,httpRequest,httpResponse);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝订单查询
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no 支付宝订单号
     */
    @RequestMapping("/aliQueryPc")
    @ResponseBody
    public AlipayTradeQueryResponse aliQueryPc(String out_trade_no,String trade_no ){
        AlipayTradeQueryResponse alipayTradeQueryResponse = new AlipayTradeQueryResponse();
        alipayTradeQueryResponse = alipayPlugin.aliQuery(out_trade_no,trade_no);
        return alipayTradeQueryResponse;
    }

    /**
     * 支付宝关闭订单
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no 支付宝订单号
     */
    @RequestMapping("/aliClose")
    @ResponseBody
    public AlipayTradeCloseResponse aliClose(String out_trade_no, String trade_no){
        AlipayTradeCloseResponse alipayTradeCloseResponse = new AlipayTradeCloseResponse();
        alipayTradeCloseResponse = alipayPlugin.aliClose(out_trade_no,trade_no);
        return alipayTradeCloseResponse;
    }

    /**
     * 支付宝退款
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号
     * @param trade_no 支付宝交易号 (与商户订单号二选一)
     * @param refund_amount 需要退款的金额，该金额不能大于订单金额，必填
     * @param refund_reason 退款的原因说明
     * @param out_request_no 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
     */
    @RequestMapping("/aliRefund")
    @ResponseBody
    public AlipayTradeRefundResponse aliRefund(String out_trade_no, String trade_no, String refund_amount, String refund_reason, String out_request_no ){
        AlipayTradeRefundResponse alipayTradeRefundResponse = new AlipayTradeRefundResponse();
        alipayTradeRefundResponse = alipayPlugin.aliRefund(out_trade_no,trade_no,refund_amount,refund_reason, out_request_no );
        return alipayTradeRefundResponse;
    }

    /**
     * 支付宝退款查询
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号
     * @param trade_no 支付宝交易号(与商户订单号二选一)
     * @param out_request_no 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
     */
    @RequestMapping("/aliRefundQuery")
    @ResponseBody
    public AlipayTradeFastpayRefundQueryResponse aliRefundQuery(String out_trade_no, String trade_no, String out_request_no){
        AlipayTradeFastpayRefundQueryResponse alipayTradeFastpayRefundQueryResponse = new AlipayTradeFastpayRefundQueryResponse();
        alipayTradeFastpayRefundQueryResponse  = alipayPlugin.aliRefundQuery(out_trade_no,trade_no,out_request_no);
        return alipayTradeFastpayRefundQueryResponse;
    }

}
