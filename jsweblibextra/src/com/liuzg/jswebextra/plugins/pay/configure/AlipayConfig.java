package com.liuzg.jswebextra.plugins.pay.configure;

public class AlipayConfig {
    // 商户appid
    public static String APPID = "";
    //合作伙伴身份（PID）
    public static String PID = "";

    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "";
    // 请求网关地址
    public static String URL = "";
    // 编码
    public static String CHARSET = "";
    // 返回格式
    public static String FORMAT = "";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "";
    // 日志记录目录
    public static String log_path = "";
    // RSA2
    public static String SIGNTYPE = "";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static String input_charset = "";

    // 签名方式 不需修改
    public static String sign_type = "";

    // 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
    public static String transport = "";
}
