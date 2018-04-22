package com.liuzg.jswebextra.plugins.pay.model;

/**
 * 自定义数据类型，ajax返回的json格式数据
 * @author lisy
 * Created by NEUNB_Lisy on 2017/10/18.
 */
public class WXResultData {
    private String return_code;//返回状态码
    private String return_msg;// 返回信息
    //以下字段在return_code为SUCCESS的时候有返回
    private String appid;//公众账号ID
    private String mch_id;//商户号
    private String device_info;//设备号
    private String nonce_str;//随机字符串
    private String sign;//签名
    private String result_code;//业务结果
    private String err_code;//错误代码
    private String err_code_des;//错误代码描述
    //查询订单返回字段 以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 ，如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach（选传）。
    private String openid;//用户标识
    private String is_subscribe;//是否关注公众账号
    private String trade_state;//交易状态
    private String bank_type;//付款银行
    private String coupon_fee;//代金券金额
    private String coupon_count;//代金券使用数量
    private String coupon_id_$n	;//代金券ID
    private String coupon_fee_$n;//单个代金券支付金额
    private String attach;//附加数据
    private String time_end;//支付完成时间
    private String trade_state_desc;//交易状态描述
    //退款返回字段  以下字段在return_code为SUCCESS的时候有返回
    private String transaction_id;//微信订单号
    private String out_trade_no;//商户订单号
    private String out_refund_no;//商户退款单号
    private String refund_id;//微信退款单号
    private String refund_fee;//退款金额
    private String settlement_refund_fee;//应结退款金额
    private String total_fee;//标价金额
    private String settlement_total_fee;//应结订单金额
    private String fee_type;//标价币种
    private String cash_fee;//现金支付金额
    private String cash_fee_type;//现金支付币种
    private String cash_refund_fee;//现金退款金额
    private String coupon_type_$n;//代金券类型
    private String coupon_refund_fee;//代金券退款总金额
    private String coupon_refund_fee_$n;//单个代金券退款金额
    private String coupon_refund_count;//退款代金券使用数量
    private String coupon_refund_id_$n;//退款代金券ID
    //查询退款返回字段 以下字段在return_code为SUCCESS的时候有返回
    private String refund_count;//退款笔数
    private String refund_channel_$n;//退款渠道
    private String refund_fee_$n;//申请退款金额
    private String settlement_refund_fee_$n;//退款金额
    private String coupon_type_$n_$m;//总代金券退款金额
    private String coupon_refund_count_$n;//退款代金券使用数量
    private String coupon_refund_id_$n_$m;//退款代金券ID
    private String coupon_refund_fee_$n_$m;//单个代金券退款金额
    private String refund_status_$n;//退款状态
    private String refund_account_$n;//退款资金来源
    private String refund_recv_accout_$n;//退款入账账户
    private String refund_success_time_$n;//退款成功实践
    //退款成功返回字段 以下字段在return_code为SUCCESS的时候有返回：
    private String success_time;//退款成功时间
    private String refund_request_source;//退款发起来源
    //以下字段在return_code 和result_code都为SUCCESS的时候有返回
    private String trade_type;//交易类型
    private String prepay_id;//预支付交易会话标识
    private String code_url;//二维码链接

    /**
     * controller处理结果所包含的数据
     */
    private Object data;

    public WXResultData() {}

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSuccess_time() {
        return success_time;
    }

    public void setSuccess_time(String success_time) {
        this.success_time = success_time;
    }

    public String getRefund_request_source() {
        return refund_request_source;
    }

    public void setRefund_request_source(String refund_request_source) {
        this.refund_request_source = refund_request_source;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public String getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(String coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public String getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(String coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_id_$n() {
        return coupon_id_$n;
    }

    public void setCoupon_id_$n(String coupon_id_$n) {
        this.coupon_id_$n = coupon_id_$n;
    }

    public String getCoupon_fee_$n() {
        return coupon_fee_$n;
    }

    public void setCoupon_fee_$n(String coupon_fee_$n) {
        this.coupon_fee_$n = coupon_fee_$n;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public String getRefund_channel_$n() {
        return refund_channel_$n;
    }

    public void setRefund_channel_$n(String refund_channel_$n) {
        this.refund_channel_$n = refund_channel_$n;
    }

    public String getRefund_fee_$n() {
        return refund_fee_$n;
    }

    public void setRefund_fee_$n(String refund_fee_$n) {
        this.refund_fee_$n = refund_fee_$n;
    }

    public String getSettlement_refund_fee_$n() {
        return settlement_refund_fee_$n;
    }

    public void setSettlement_refund_fee_$n(String settlement_refund_fee_$n) {
        this.settlement_refund_fee_$n = settlement_refund_fee_$n;
    }

    public String getCoupon_type_$n_$m() {
        return coupon_type_$n_$m;
    }

    public void setCoupon_type_$n_$m(String coupon_type_$n_$m) {
        this.coupon_type_$n_$m = coupon_type_$n_$m;
    }

    public String getCoupon_refund_count_$n() {
        return coupon_refund_count_$n;
    }

    public void setCoupon_refund_count_$n(String coupon_refund_count_$n) {
        this.coupon_refund_count_$n = coupon_refund_count_$n;
    }

    public String getCoupon_refund_id_$n_$m() {
        return coupon_refund_id_$n_$m;
    }

    public void setCoupon_refund_id_$n_$m(String coupon_refund_id_$n_$m) {
        this.coupon_refund_id_$n_$m = coupon_refund_id_$n_$m;
    }

    public String getCoupon_refund_fee_$n_$m() {
        return coupon_refund_fee_$n_$m;
    }

    public void setCoupon_refund_fee_$n_$m(String coupon_refund_fee_$n_$m) {
        this.coupon_refund_fee_$n_$m = coupon_refund_fee_$n_$m;
    }

    public String getRefund_status_$n() {
        return refund_status_$n;
    }

    public void setRefund_status_$n(String refund_status_$n) {
        this.refund_status_$n = refund_status_$n;
    }

    public String getRefund_account_$n() {
        return refund_account_$n;
    }

    public void setRefund_account_$n(String refund_account_$n) {
        this.refund_account_$n = refund_account_$n;
    }

    public String getRefund_recv_accout_$n() {
        return refund_recv_accout_$n;
    }

    public void setRefund_recv_accout_$n(String refund_recv_accout_$n) {
        this.refund_recv_accout_$n = refund_recv_accout_$n;
    }

    public String getRefund_success_time_$n() {
        return refund_success_time_$n;
    }

    public void setRefund_success_time_$n(String refund_success_time_$n) {
        this.refund_success_time_$n = refund_success_time_$n;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getSettlement_refund_fee() {
        return settlement_refund_fee;
    }

    public void setSettlement_refund_fee(String settlement_refund_fee) {
        this.settlement_refund_fee = settlement_refund_fee;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getCash_refund_fee() {
        return cash_refund_fee;
    }

    public void setCash_refund_fee(String cash_refund_fee) {
        this.cash_refund_fee = cash_refund_fee;
    }

    public String getCoupon_type_$n() {
        return coupon_type_$n;
    }

    public void setCoupon_type_$n(String coupon_type_$n) {
        this.coupon_type_$n = coupon_type_$n;
    }

    public String getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(String coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public String getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public String getCoupon_refund_count() {
        return coupon_refund_count;
    }

    public void setCoupon_refund_count(String coupon_refund_count) {
        this.coupon_refund_count = coupon_refund_count;
    }

    public String getCoupon_refund_id_$n() {
        return coupon_refund_id_$n;
    }

    public void setCoupon_refund_id_$n(String coupon_refund_id_$n) {
        this.coupon_refund_id_$n = coupon_refund_id_$n;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }
}