package com.liuzg.jswebextra.utils;

import com.liuzg.jswebextra.plugins.pay.sdk.tencent.WXPayConstants;
import com.liuzg.jswebextra.plugins.pay.sdk.tencent.WXPayConstants.SignType;
import com.liuzg.jswebextra.plugins.pay.model.WXResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.*;


public class WXPayUtil {

    public WXPayUtil(){}

    /**
     * 将微信返回的map结果转换为WXResultData
     * @param map 需要转换的参数
     */
    public static WXResultData mapToWXRsultData(Map<String ,String> map){
        WXResultData wxResultData = new WXResultData();
        wxResultData.setReturn_code(map.get("return_code")==null?"":map.get("return_code"));
        wxResultData.setReturn_msg(map.get("return_msg")==null?"":map.get("return_msg"));
        //以下字段在return_code为SUCCESS的时候有返回
        wxResultData.setAppid(map.get("appid")==null?"":map.get("appid"));
        wxResultData.setMch_id(map.get("mch_id")==null?"":map.get("mch_id"));
        wxResultData.setDevice_info(map.get("device_info")==null?"":map.get("device_info"));
        wxResultData.setNonce_str(map.get("nonce_str")==null?"":map.get("nonce_str"));
        wxResultData.setSign(map.get("sign")==null?"":map.get("sign"));
        wxResultData.setResult_code(map.get("result_code")==null?"":map.get("result_code"));
        wxResultData.setErr_code(map.get("err_code")==null?"":map.get("err_code"));
        wxResultData.setErr_code_des(map.get("err_code_des")==null?"":map.get("err_code_des"));
        //查询订单返回字段 以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 ，如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach（选传）。
        wxResultData.setOpenid(map.get("openid")==null?"":map.get("openid"));
        wxResultData.setIs_subscribe(map.get("is_subscribe")==null?"":map.get("is_subscribe"));
        wxResultData.setTrade_state(map.get("trade_state")==null?"":map.get("trade_state"));
        wxResultData.setBank_type(map.get("bank_type")==null?"":map.get("bank_type"));
        wxResultData.setCoupon_fee(map.get("coupon_fee")==null?"":map.get("coupon_fee"));
        wxResultData.setCoupon_count(map.get("coupon_count")==null?"":map.get("coupon_count"));
        wxResultData.setCoupon_id_$n(map.get("coupon_id_$n")==null?"":map.get("coupon_id_$n"));
        wxResultData.setCoupon_fee_$n(map.get("coupon_fee_$n")==null?"":map.get("coupon_fee_$n"));
        wxResultData.setAttach(map.get("attach")==null?"":map.get("attach"));
        wxResultData.setTime_end(map.get("time_end")==null?"":map.get("time_end"));
        wxResultData.setTrade_state_desc(map.get("trade_state_desc")==null?"":map.get("trade_state_desc"));
        //退款返回字段  以下字段在return_code为SUCCESS的时候有返回
        wxResultData.setTransaction_id(map.get("transaction_id")==null?"":map.get("transaction_id"));
        wxResultData.setOut_trade_no(map.get("out_trade_no")==null?"":map.get("out_trade_no"));
        wxResultData.setOut_refund_no(map.get("out_refund_no")==null?"":map.get("out_refund_no"));
        wxResultData.setRefund_id(map.get("refund_id")==null?"":map.get("refund_id"));
        wxResultData.setRefund_fee(map.get("refund_fee")==null?"":map.get("refund_fee"));
        wxResultData.setSettlement_refund_fee(map.get("settlement_refund_fee")==null?"":map.get("settlement_refund_fee"));
        wxResultData.setTotal_fee(map.get("total_fee")==null?"":map.get("total_fee"));
        wxResultData.setSettlement_total_fee(map.get("settlement_total_fee")==null?"":map.get("settlement_total_fee"));
        wxResultData.setFee_type(map.get("fee_type")==null?"":map.get("fee_type"));
        wxResultData.setCash_fee(map.get("cash_fee")==null?"":map.get("cash_fee"));
        wxResultData.setCash_fee_type(map.get("cash_fee_type")==null?"":map.get("cash_fee_type"));
        wxResultData.setCash_refund_fee(map.get("cash_refund_fee")==null?"":map.get("cash_refund_fee"));
        wxResultData.setCoupon_type_$n(map.get("coupon_type_$n")==null?"":map.get("coupon_type_$n"));
        wxResultData.setCoupon_refund_fee(map.get("coupon_refund_fee")==null?"":map.get("coupon_refund_fee"));
        wxResultData.setCoupon_refund_fee_$n(map.get("coupon_refund_fee_$n")==null?"":map.get("coupon_refund_fee_$n"));
        wxResultData.setCoupon_refund_count(map.get("coupon_refund_count")==null?"":map.get("coupon_refund_count"));
        wxResultData.setCoupon_refund_id_$n(map.get("coupon_refund_id_$n")==null?"":map.get("coupon_refund_id_$n"));
        //查询退款返回字段 以下字段在return_code为SUCCESS的时候有返回
        wxResultData.setRefund_count(map.get("refund_count")==null?"":map.get("refund_count"));
        wxResultData.setRefund_count(map.get("refund_channel_$n")==null?"":map.get("refund_channel_$n"));
        wxResultData.setRefund_count(map.get("refund_fee_$n")==null?"":map.get("refund_fee_$n"));
        wxResultData.setRefund_count(map.get("settlement_refund_fee_$n")==null?"":map.get("settlement_refund_fee_$n"));
        wxResultData.setRefund_count(map.get("coupon_type_$n_$m")==null?"":map.get("coupon_type_$n_$m"));
        wxResultData.setRefund_count(map.get("coupon_refund_count_$n")==null?"":map.get("coupon_refund_count_$n"));
        wxResultData.setRefund_count(map.get("coupon_refund_id_$n_$m")==null?"":map.get("coupon_refund_id_$n_$m"));
        wxResultData.setRefund_count(map.get("coupon_refund_fee_$n_$m")==null?"":map.get("coupon_refund_fee_$n_$m"));
        wxResultData.setRefund_count(map.get("refund_status_$n")==null?"":map.get("refund_status_$n"));
        wxResultData.setRefund_count(map.get("refund_account_$n")==null?"":map.get("refund_account_$n"));
        wxResultData.setRefund_count(map.get("refund_recv_accout_$n")==null?"":map.get("refund_recv_accout_$n"));
        wxResultData.setRefund_count(map.get("refund_success_time_$n")==null?"":map.get("refund_success_time_$n"));
        //退款成功返回字段 以下字段在return_code为SUCCESS的时候有返回：
        wxResultData.setRefund_count(map.get("success_time")==null?"":map.get("success_time"));
        wxResultData.setRefund_count(map.get("refund_request_source")==null?"":map.get("refund_request_source"));
        //以下字段在return_code 和result_code都为SUCCESS的时候有返回
        wxResultData.setTrade_type(map.get("trade_type")==null?"":map.get("trade_type"));
        wxResultData.setPrepay_id(map.get("prepay_id")==null?"":map.get("prepay_id"));
        wxResultData.setCode_url(map.get("code_url")==null?"":map.get("code_url"));
        return wxResultData;
    }

    public static int doubleToInt(double doub){
        int money_total_fee = 0;
        BigDecimal total_feeBig = new BigDecimal(doub);
        BigDecimal tempBig = new BigDecimal("100");
        total_feeBig = total_feeBig.multiply(tempBig);
        money_total_fee = (int) Math.ceil(total_feeBig.doubleValue());
        return money_total_fee ;
    }

    /**
     * XML格式字符串转换为Map
     *
     * @param strXML XML字符串
     * @return XML数据转换后的Map
     * @throws Exception
     */
    public static Map<String, String> xmlToMap(String strXML) throws Exception {
        try {
            Map<String, String> data = new HashMap<String, String>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"));
            org.w3c.dom.Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx = 0; idx < nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            try {
                stream.close();
            } catch (Exception ex) {
                // do nothing
            }
            return data;
        } catch (Exception ex) {
            WXPayUtil.getLogger().warn("Invalid XML, can not convert to map. Error message: {}. XML content: {}", ex.getMessage(), strXML);
            throw ex;
        }

    }

    /**
     * 将Map转换为XML格式的字符串
     *
     * @param data Map类型数据
     * @return XML格式的字符串
     * @throws Exception
     */
    public static String mapToXml(Map<String, String> data) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
        org.w3c.dom.Document document = documentBuilder.newDocument();
        org.w3c.dom.Element root = document.createElement("xml");
        document.appendChild(root);
        for (String key: data.keySet()) {
            String value = data.get(key);
            if (value == null) {
                value = "";
            }
            value = value.trim();
            org.w3c.dom.Element filed = document.createElement(key);
            filed.appendChild(document.createTextNode(value));
            root.appendChild(filed);
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        DOMSource source = new DOMSource(document);
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        transformer.transform(source, result);
        String output = writer.getBuffer().toString(); //.replaceAll("\n|\r", "");
        try {
            writer.close();
        }
        catch (Exception ex) {
        }
        return output;
    }


    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data Map类型数据
     * @param key API密钥
     * @return 含有sign字段的XML
     */
    public static String generateSignedXml(final Map<String, String> data, String key) throws Exception {
        return generateSignedXml(data, key, SignType.MD5);
    }

    /**
     * 生成带有 sign 的 XML 格式字符串
     *
     * @param data Map类型数据
     * @param key API密钥
     * @param signType 签名类型
     * @return 含有sign字段的XML
     */
    public static String generateSignedXml(final Map<String, String> data, String key, SignType signType) throws Exception {
        String sign = generateSignature(data, key, signType);
        data.put(WXPayConstants.FIELD_SIGN, sign);
        return mapToXml(data);
    }


    /**
     * 判断签名是否正确
     *
     * @param xmlStr XML格式数据
     * @param key API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(String xmlStr, String key) throws Exception {
        Map<String, String> data = xmlToMap(xmlStr);
        if (!data.containsKey(WXPayConstants.FIELD_SIGN) ) {
            return false;
        }
        String sign = data.get(WXPayConstants.FIELD_SIGN);
        return generateSignature(data, key).equals(sign);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。使用MD5签名。
     *
     * @param data Map类型数据
     * @param key API密钥
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        return isSignatureValid(data, key, SignType.MD5);
    }

    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data Map类型数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key, SignType signType) throws Exception {
        if (!data.containsKey(WXPayConstants.FIELD_SIGN) ) {
            return false;
        }
        String sign = data.get(WXPayConstants.FIELD_SIGN);
        return generateSignature(data, key, signType).equals(sign);
    }

    /**
     * 生成签名
     *
     * @param data 待签名数据
     * @param key API密钥
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        return generateSignature(data, key, SignType.MD5);
    }

    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key, SignType signType) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(WXPayConstants.FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        if (SignType.MD5.equals(signType)) {
            return MD5(sb.toString()).toUpperCase();
        }
        else if (SignType.HMACSHA256.equals(signType)) {
            return HMACSHA256(sb.toString(), key);
        }
        else {
            throw new Exception(String.format("Invalid sign_type: %s", signType));
        }
    }


    /**
     * 获取随机字符串 Nonce Str
     *
     * @return String 随机字符串
     */
    public static String generateNonceStr() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }


    /**
     * 生成 MD5
     *
     * @param data 待处理数据
     * @return MD5结果
     */
    public static String MD5(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = md.digest(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 生成 HMACSHA256
     * @param data 待处理数据
     * @param key 密钥
     * @return 加密结果
     * @throws Exception
     */
    public static String HMACSHA256(String data, String key) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : array) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 日志
     * @return
     */
    public static Logger getLogger() {
        Logger logger = LoggerFactory.getLogger("wxpay java sdk");
        return logger;
    }

    /**
     * 获取当前时间戳，单位秒
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis()/1000;
    }

    /**
     * 获取当前时间戳，单位毫秒
     * @return
     */
    public static long getCurrentTimestampMs() {
        return System.currentTimeMillis();
    }

    /**
     * 生成 uuid， 即用来标识一笔单，也用做 nonce_str
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

}
