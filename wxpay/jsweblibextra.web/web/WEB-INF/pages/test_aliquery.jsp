<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/11/14
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付宝订单查询</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="out_trade_no">商户订单号 二选一（有限支付宝交易号）</label>
                <input value="" name="out_trade_no" type="out_trade_no" class="form-control" id="out_trade_no" placeholder="商户订单号">
            </div>
            <div class="form-group">
                <label for="WIDtrade_no">支付宝交易号 二选一</label>
                <input value="" name="WIDtrade_no" type="WIDtrade_no" class="form-control" id="WIDtrade_no" placeholder="支付宝交易号">
            </div>
            <button type="submit" class="btn btn-default" ng-click="doalipay()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>
</body>
<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        $scope.doalipay = function () {
            $scope.data = {};
            $scope.data.out_trade_no=$('#out_trade_no').val();
            $scope.data.trade_no=$('#WIDtrade_no').val();
            console.log($scope.data);
            remotecall("alipay/aliQuery",$scope.data,function (data) {
                /*交易状态：WAIT_BUYER_PAY（交易创建，等待买家付款）、
                * TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）、
                * TRADE_SUCCESS（交易支付成功）、
                * TRADE_FINISHED（交易结束，不可退款）*/
                if (data.trade_status == "TRADE_SUCCESS"){
                    console.log("查询成功");
                    console.log(data);
                }
            },function (data) {

            });
        };
    });

</script>
</html>
