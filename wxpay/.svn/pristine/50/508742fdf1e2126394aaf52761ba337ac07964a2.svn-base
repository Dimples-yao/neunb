package com.liuzg.jswebextra.plugins;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.liuzg.jswebextra.plugins.pay.configure.AlipayConfig;
import com.liuzg.jswebextra.plugins.pay.model.AliResultData;
import com.liuzg.jswebextra.utils.AlipayUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class AlipayPlugin {

    static AlipayClient alipayClient;
    static AlipayClient alipayWapClient;
    public AlipayPlugin(String APPID,String PID,String RSA_PRIVATE_KEY,String notify_url,String return_url,String URL,String CHARSET,String FORMAT,String ALIPAY_PUBLIC_KEY,String log_path,String SIGNTYPE ,String input_charset,String sign_type,String transport){
        AlipayConfig.APPID = APPID;
        AlipayConfig.PID = PID;
        AlipayConfig.RSA_PRIVATE_KEY = RSA_PRIVATE_KEY;
        AlipayConfig.notify_url = notify_url;
        AlipayConfig.return_url = return_url;
        AlipayConfig.URL = URL;
        AlipayConfig.CHARSET = CHARSET;
        AlipayConfig.FORMAT = FORMAT;
        AlipayConfig.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
        AlipayConfig.log_path = log_path;
        AlipayConfig.SIGNTYPE = SIGNTYPE;
        AlipayConfig.input_charset = input_charset;
        AlipayConfig.sign_type = sign_type;
        AlipayConfig.transport = transport;
        alipayClient=new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        alipayWapClient=new DefaultAlipayClient(AlipayConfig.URL,AlipayConfig.APPID,AlipayConfig.RSA_PRIVATE_KEY, AlipayConstants.FORMAT_JSON,
                "UTF-8",AlipayConfig.ALIPAY_PUBLIC_KEY);
    }

    public AlipayPlugin() {}

    /**
     * 支付宝支付_移动端
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_amount 付款金额，必填
     * @param body 商品描述，可空
     * @param timeout_express 超时时间 可空
     * @param product_code 销售产品码 必填
     */
    public void doPay(String out_trade_no, String subject, String total_amount, String body, String timeout_express, String product_code , String return_url,String notify_url, HttpServletRequest httpRequest, HttpServletResponse httpResponse)throws AlipayApiException{

        AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();
        /*alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);//在公共参数中设置回跳和通知地址*/
        alipayRequest.setReturnUrl(return_url);//在请求参数中添加同步地址
        alipayRequest.setNotifyUrl(notify_url);//在公共参数中设置回跳和通知地址
        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipayRequest.setBizModel(model);
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            httpResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝支付_PC端
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param subject 订单名称，必填
     * @param total_amount 付款金额，必填
     * @param body 商品描述，可空
     */
    public void doPayPc(String out_trade_no, String subject, String total_amount, String body, String product_code ,String return_url,String notify_url, HttpServletRequest httpRequest, HttpServletResponse httpResponse)throws AlipayApiException{
        AlipayTradePagePayRequest alipayRequest=new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);//在请求参数中添加同步地址
        alipayRequest.setNotifyUrl(notify_url);//在公共参数中设置回跳和通知地址
        // 封装请求支付信息
        AlipayTradePagePayModel model=new AlipayTradePagePayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setProductCode(product_code);
        alipayRequest.setBizModel(model);
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        try {
            httpResponse.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            httpResponse.getWriter().write(result);//直接将完整的表单html输出到页面
            httpResponse.getWriter().flush();
            httpResponse.getWriter().close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    /**
     * 异步通知地址
     */
    public AliResultData getNotifyResultPay(HttpServletRequest request){
        AliResultData aliResultData = new AliResultData();
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        aliResultData = AlipayUtil.jsonToAli(params);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        aliResultData.setOut_trade_no(request.getParameter("out_trade_no"));
        //支付宝交易号
        aliResultData.setTrade_no(request.getParameter("trade_no"));
        //交易状态
        aliResultData.setTrade_status(request.getParameter("trade_status"));
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        boolean signVerified = false;//调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        aliResultData.setVerify_result(signVerified);
        return aliResultData;
        /*if(signVerified){//验证成功
            if(aliResultData.getTrade_status().equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (aliResultData.getTrade_status().equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                //根据订单号将订单状态和支付宝记录表中状态都改为已支付
            }
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
            //out.print("success"); //请不要修改或删除
            return aliResultData;
        }else{//验证失败
            return null;
        }*/
    }

    /**
     * 同步回调地址
     */
    public AliResultData getReturnResultPay(HttpServletRequest request){
        AliResultData aliResultData = new AliResultData();
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put(name, valueStr);
        }
        boolean verify_result = false;
        aliResultData = AlipayUtil.jsonToAli(params);
        try {
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            aliResultData.setOut_trade_no(request.getParameter("out_trade_no"));
            //支付宝交易号
            aliResultData.setTrade_no(request.getParameter("trade_no"));
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
            //计算得出通知验证结果
            //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
            verify_result = AlipaySignature.rsaCheckV1(params, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        aliResultData.setVerify_result(verify_result);
        return aliResultData;
    }

    /**
     * 支付宝订单查询
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no 支付宝订单号
     */
    public AlipayTradeQueryResponse aliQuery(String out_trade_no,String trade_no ){
        AlipayTradeQueryRequest alipay_request = new AlipayTradeQueryRequest();

        AlipayTradeQueryModel model=new AlipayTradeQueryModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        alipay_request.setBizModel(model);

        AlipayTradeQueryResponse alipay_response = new AlipayTradeQueryResponse();
        try {
            alipay_response = alipayClient.execute(alipay_request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return alipay_response;
    }


    /**
     * 支付宝关闭订单
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param trade_no 支付宝订单号
     */
    public AlipayTradeCloseResponse aliClose(String out_trade_no,String trade_no){
        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        alipayRequest.setBizModel(model);
        AlipayTradeCloseResponse alipayTradeCloseResponse = new AlipayTradeCloseResponse();
        try {
            alipayTradeCloseResponse = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
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
    public AlipayTradeRefundResponse aliRefund(String out_trade_no, String trade_no, String refund_amount, String refund_reason, String out_request_no ){
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        //初始化参数
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        model.setRefundAmount(refund_amount);
        model.setRefundReason(refund_reason);
        model.setOutRequestNo(out_request_no);
        alipayRequest.setBizModel(model);
        AlipayTradeRefundResponse alipayTradeRefundResponse = new AlipayTradeRefundResponse();
        //请求
        try {
            alipayTradeRefundResponse = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return alipayTradeRefundResponse;
    }

    /**
     * 支付宝退款查询
     * @param out_trade_no 商户订单号，商户网站订单系统中唯一订单号
     * @param trade_no 支付宝交易号(与商户订单号二选一)
     * @param out_request_no 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
     */
    public AlipayTradeFastpayRefundQueryResponse aliRefundQuery(String out_trade_no,String trade_no,String out_request_no){
        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        //初始化参数
        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(out_trade_no);
        model.setTradeNo(trade_no);
        model.setOutRequestNo(out_request_no);
        alipayRequest.setBizModel(model);
        AlipayTradeFastpayRefundQueryResponse alipayTradeFastpayRefundQueryResponse = new AlipayTradeFastpayRefundQueryResponse();
        try {
            alipayTradeFastpayRefundQueryResponse = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return alipayTradeFastpayRefundQueryResponse;
    }

}
