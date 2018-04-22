package com.liuzg.jswebextra.utils;

import com.liuzg.jswebextra.plugins.pay.model.AliResultData;

import java.util.Map;

/**
 * Created by NEUNB_Lisy on 2017/11/13.
 */
public class AlipayUtil {
    public static AliResultData jsonToAli(Map<String,String> params){
        AliResultData aliResultData = new AliResultData();
        aliResultData.setNotify_time(params.get("notify_time")==null?"":params.get("notify_time"));
        aliResultData.setNotify_type(params.get("notify_type")==null?"":params.get("notify_type"));
        aliResultData.setNotify_id(params.get("notify_id")==null?"":params.get("notify_id"));
        aliResultData.setApp_id(params.get("app_id")==null?"":params.get("app_id"));
        aliResultData.setCharset(params.get("charset")==null?"":params.get("charset"));
        aliResultData.setVersion(params.get("version")==null?"":params.get("version"));
        aliResultData.setSign_type(params.get("sign_type")==null?"":params.get("sign_type"));
        aliResultData.setSign(params.get("sign")==null?"":params.get("sign"));
        aliResultData.setTrade_no(params.get("trade_no")==null?"":params.get("trade_no"));
        aliResultData.setOut_trade_no(params.get("out_trade_no")==null?"":params.get("out_trade_no"));
        aliResultData.setOut_biz_no(params.get("out_biz_no")==null?"":params.get("out_biz_no"));
        aliResultData.setBuyer_id(params.get("buyer_id")==null?"":params.get("buyer_id"));
        aliResultData.setBuyer_logon_id(params.get("buyer_logon_id")==null?"":params.get("buyer_logon_id"));
        aliResultData.setSeller_id(params.get("seller_id")==null?"":params.get("seller_id"));
        aliResultData.setSeller_email(params.get("seller_email")==null?"":params.get("seller_email"));
        aliResultData.setTrade_status(params.get("trade_status")==null?"":params.get("trade_status"));
        aliResultData.setTotal_amount(params.get("total_amount")==null?"":params.get("total_amount"));
        aliResultData.setReceipt_amount(params.get("receipt_amount")==null?"":params.get("receipt_amount"));
        aliResultData.setInvoice_amount(params.get("invoice_amount")==null?"":params.get("invoice_amount"));
        aliResultData.setBuyer_pay_amount(params.get("buyer_pay_amount")==null?"":params.get("buyer_pay_amount"));
        aliResultData.setPoint_amount(params.get("point_amount")==null?"":params.get("point_amount"));
        aliResultData.setRefund_fee(params.get("refund_fee")==null?"":params.get("refund_fee"));
        aliResultData.setSubject(params.get("subject")==null?"":params.get("subject"));
        aliResultData.setBody(params.get("body")==null?"":params.get("body"));
        aliResultData.setGmt_create(params.get("gmt_create")==null?"":params.get("gmt_create"));
        aliResultData.setGmt_payment(params.get("gmt_payment")==null?"":params.get("gmt_payment"));
        aliResultData.setGmt_refund(params.get("gmt_refund")==null?"":params.get("gmt_refund"));
        aliResultData.setGmt_close(params.get("gmt_close")==null?"":params.get("gmt_close"));
        aliResultData.setFund_bill_list(params.get("fund_bill_list")==null?"":params.get("fund_bill_list"));
        aliResultData.setPassback_params(params.get("passback_params")==null?"":params.get("passback_params"));
        aliResultData.setVoucher_detail_list(params.get("voucher_detail_list")==null?"":params.get("voucher_detail_list"));
        return aliResultData;
    }
}
