<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/10/24
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>公众号支付</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="body">商品描述 必填</label>
                <input value="" name="body" type="body" class="form-control" id="body" placeholder="商品描述">
            </div>
            <div class="form-group">
                <label for="out_trade_no">商户订单号 必填</label>
                <input value="" name="out_trade_no" type="out_trade_no" class="form-control" id="out_trade_no" placeholder="商户订单号">
            </div>
            <div class="form-group">
                <label for="openid">用户标识</label>
                <input value="oci96jlJag9f7hMcm9hp9GSUBiM8" name="openid" type="openid" class="form-control" id="openid" placeholder="用户表示">
            </div>
            <div class="form-group">
                <label for="total_fee">标价金额 必填</label>
                <input value="0.01" name="total_fee" type="total_fee" class="form-control" id="total_fee" placeholder="标价金额">
            </div>
            <div class="form-group">
                <label for="notify_url">通知地址 必填</label>
                <input value="http://project.neunb.com/wxpay/notify.form" name="notify_url" type="notify_url" class="form-control" id="notify_url" placeholder="通知地址">
            </div>
            <div class="form-group">
                <label for="trade_type">交易类型 必填</label>
                <select name="trade_type" type="trade_type" class="form-control" id="trade_type">
                    <option value="NATIVE" >原生扫码支付</option>
                    <option value="JSAPI" selected>公众号支付</option>
                    <option value="APP">app支付</option>
                    <option value="MICROPAY">刷卡支付</option>
                </select>
            </div>
            <div class="form-group">
                <label for="device_info">设备号 非必填</label>
                <input value="" name="device_info" type="device_info" class="form-control" id="device_info" placeholder="设备号">
            </div>
            <div class="form-group">
                <label for="detail">商品详情 非必填</label>
                <input value="" name="detail" type="detail" class="form-control" id="detail" placeholder="商品详情">
            </div>
            <div class="form-group">
                <label for="attach">附加数据 非必填</label>
                <input value="" name="attach" type="attach" class="form-control" id="attach" placeholder="附加数据">
            </div>
            <div class="form-group">
                <label for="fee_type">标价币种 非必填</label>
                <input value="" name="fee_type" type="fee_type" class="form-control" id="fee_type" placeholder="标价币种">
            </div>
            <div class="form-group">
                <label for="time_start">交易起始时间 非必填</label>
                <input value="" name="time_start" type="time_start" class="form-control" id="time_start" placeholder="交易起始时间">
            </div>
            <div class="form-group">
                <label for="time_expire">交易结束时间 非必填</label>
                <input value="" name="time_expire" type="time_expire" class="form-control" id="time_expire" placeholder="交易结束时间">
            </div>
            <div class="form-group">
                <label for="goods_tag">订单优惠标记 非必填</label>
                <input value="" name="goods_tag" type="goods_tag" class="form-control" id="goods_tag" placeholder="订单优惠标记">
            </div>
            <div class="form-group">
                <label for="product_id">商品ID 非必填</label>
                <input value="" name="product_id" type="product_id" class="form-control" id="product_id" placeholder="商品ID">
            </div>
            <div class="form-group">
                <label for="limit_pay">指定支付方式 非必填</label>
                <input value="" name="limit_pay" type="limit_pay" class="form-control" id="limit_pay" placeholder="指定支付方式">
            </div>
            <div class="form-group">
                <label for="scene_info">场景信息 非必填</label>
                <input value="" name="scene_info" type="scene_info" class="form-control" id="scene_info" placeholder="场景信息">
            </div>
            <button type="submit" class="btn btn-default" ng-click="unifiedOrder()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>

<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        $scope.unifiedOrder = function () {
            $scope.data = {};
            $scope.data.body=$('#body').val();
            $scope.data.out_trade_no=$('#out_trade_no').val();
            $scope.data.openid=$('#openid').val();
            $scope.data.total_fee=$('#total_fee').val();
            $scope.data.notify_url=$('#notify_url').val();
            $scope.data.trade_type=$('#trade_type').val();
            $scope.data.device_info=$('#device_info').val();
            $scope.data.detail=$('#detail').val();
            $scope.data.attach=$('#attach').val();
            $scope.data.fee_type=$('#fee_type').val();
            $scope.data.time_start=$('#time_start').val();
            $scope.data.time_expire=$('#time_expire').val();
            $scope.data.goods_tag=$('#goods_tag').val();
            $scope.data.product_id=$('#product_id').val();
            $scope.data.limit_pay=$('#limit_pay').val();
            $scope.data.scene_info=$('#scene_info').val();
            console.log($scope.data);
            remotecall("wxpay/doUnifiedOrder",$scope.data,function (data) {
                if (data.return_code == "SUCCESS"){
                    console.log("预支付订单创建成功");
                    if (typeof WeixinJSBridge == "undefined") {
                        if (document.addEventListener) {
                            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, true);
                        } else if (document.attachEvent) {
                            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
                        }
                    } else {
                        onBridgeReady(data.data);
                    }
                }else if(data.return_code == "FAIL"){
                    console.log("预支付订单创建失败");
                }
            },function (data) {

            });
        };
    });
    function onBridgeReady(da){
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": da.appId,     //公众号名称，由商户传入
                "timeStamp": da.timeStamp,         //时间戳，自1970年以来的秒数
                "nonceStr": da.nonceStr, //随机串
                "package": da.package,
                "signType": da.signType,         //微信签名方式：
                "paySign": da.paySign //微信签名
            },
            function(res){
                // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回 ok，但并不保证它绝对可靠。
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                    alert("支付成功");
                    console.log("支付成功");
                } else {
                    alert(res.err_msg);
                    console.log("支付遇到错误或用户取消");
                }
            }
        );
    }
</script>
</body>
</html>
