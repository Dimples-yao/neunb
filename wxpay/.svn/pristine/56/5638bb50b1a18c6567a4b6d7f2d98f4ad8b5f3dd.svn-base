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
<title>退款</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="transaction_id">微信订单号 与 商户订单号 二选一</label>
                <input value="" name="transaction_id" type="body" class="form-control" id="transaction_id" placeholder="商品描述">
            </div>
            <div class="form-group">
                <label for="out_trade_no">商户订单号 与 微信订单号 二选一</label>
                <input value="" name="out_trade_no" type="out_trade_no" class="form-control" id="out_trade_no" placeholder="商户订单号">
            </div>
            <div class="form-group">
                <label for="out_refund_no">商户退款单号 必填</label>
                <input value="" name="out_refund_no" type="openid" class="form-control" id="out_refund_no" placeholder="商户退款单号">
            </div>
            <div class="form-group">
                <label for="total_fee">订单金额 必填</label>
                <input value="" name="total_fee" type="total_fee" class="form-control" id="total_fee" placeholder="订单金额">
            </div>
            <div class="form-group">
                <label for="refund_fee">退款金额 必填</label>
                <input value="" name="refund_fee" type="notify_url" class="form-control" id="refund_fee" placeholder="退款金额">
            </div>
            <div class="form-group">
                <label for="refund_fee_type">货币种类 非必填</label>
                <input value="" name="refund_fee_type" type="device_info" class="form-control" id="refund_fee_type" placeholder="货币种类">
            </div>
            <div class="form-group">
                <label for="refund_desc">退款原因 非必填</label>
                <input value="" name="refund_desc" type="detail" class="form-control" id="refund_desc" placeholder="退款原因">
            </div>
            <div class="form-group">
                <label for="refund_account">退款资金来源 非必填</label>
                <input value="" name="refund_account" type="attach" class="form-control" id="refund_account" placeholder="退款资金来源">
            </div>
            <button type="submit" class="btn btn-default" ng-click="doRefund()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>
<div id="result"></div>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    $scope.doRefund = function () {
        $scope.data = {};
        $scope.data.transaction_id=$('#transaction_id').val();
        $scope.data.out_trade_no=$('#out_trade_no').val();
        $scope.data.out_refund_no=$('#out_refund_no').val();
        $scope.data.total_fee=$('#total_fee').val();
        $scope.data.refund_fee=$('#refund_fee').val();
        $scope.data.refund_fee_type=$('#refund_fee_type').val();
        $scope.data.refund_desc=$('#refund_desc').val();
        $scope.data.refund_account=$('#refund_account').val();
        remotecall("wxpay/doRefund",$scope.data,function (data) {
            if (data.return_code == "SUCCESS"){
                console.log("申请退款成功");
                console.log(data);
            }else if(data.return_code == "FAIL"){
                console.log("申请退款失败");
                console.log(data);
            }
        },function (data) {

        });
        /*$.ajax({
            async:false,
            type: "POST",
            url: "/wxpay/doRefund.form",//注意路径
            data:$scope.data,
            dataType:"json",
            success:function(data){
                if (data.return_code == "SUCCESS"){
                    console.log("申请退款成功");
                    console.log(data);
                }else if(data.return_code == "FAIL"){
                    console.log("申请退款失败");
                    console.log(data);
                }
            },
            error:function(data){
            }
        });*/
    };
});
</script>
</body>
</html>
