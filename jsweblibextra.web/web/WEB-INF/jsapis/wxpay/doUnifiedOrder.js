function main(context) {
    var result = wxpay.doUnifiedOrder(context.body,context.out_trade_no,context.openid,context.total_fee,context.notify_url ,context.trade_type,context.device_info, context.detail,context.attach,context.fee_type,context.time_start,context.time_expire,context.goods_tag,context.product_id,context.limit_pay,context.scene_info);
    return result;
}