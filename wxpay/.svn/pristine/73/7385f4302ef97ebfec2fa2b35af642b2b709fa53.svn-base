<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建订单</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="out_trade_no">商户订单号 必填</label>
                <input value="" name="out_trade_no" type="out_trade_no" class="form-control" id="out_trade_no" placeholder="商户订单号">
            </div>
            <div class="form-group">
                <label for="subject">订单名称 必填</label>
                <input value="" name="subject" type="subject" class="form-control" id="subject" placeholder="订单名称-测试">
            </div>
            <div class="form-group">
                <label for="total_amount">付款金额 必填</label>
                <input value="" name="total_amount" type="total_amount" class="form-control" id="total_amount" placeholder="1">
            </div>
            <div class="form-group">
                <label for="body">商品描述 可空</label>
                <input value="" name="body" type="body" class="form-control" id="body" placeholder="商品描述">
            </div>
            <div class="form-group">
                <label for="timeout_express">超时时间 可空</label>
                <input value="" name="timeout_express" type="timeout_express" class="form-control" id="timeout_express" placeholder="超时时间">
            </div>
            <div class="form-group">
                <label for="product_code">销售产品码 可空</label>
                <input value="" name="product_code" type="product_code" class="form-control" id="product_code" placeholder="销售产品码">
            </div>
            <button type="submit" class="btn btn-default" ng-click="doalipay()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>
<form id="exportTo"></form>

</body>
<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        var ua = navigator.userAgent.toLowerCase();
        var isWeixin = ua.indexOf('micromessenger') != -1;
        var url = window.location.href;
        var domainName = location.protocol + "//" + location.host;
        GetDateNow();

        function GetDateNow() {
            var vNow = new Date();
            var sNow = "";
            sNow += String(vNow.getFullYear());
            sNow += String(vNow.getMonth() + 1);
            sNow += String(vNow.getDate());
            sNow += String(vNow.getHours());
            sNow += String(vNow.getMinutes());
            sNow += String(vNow.getSeconds());
            sNow += String(vNow.getMilliseconds());
            document.getElementById("out_trade_no").value =  sNow;
            document.getElementById("subject").value = "手机网站支付测试商品";
            document.getElementById("total_amount").value = "0.01";
            document.getElementById("body").value = "购买测试商品0.01元";
        }
        $scope.doalipay = function () {
            $scope.data = {};
            $scope.data.out_trade_no=$('#out_trade_no').val();
            $scope.data.subject=$('#subject').val();
            $scope.data.total_amount=$('#total_amount').val();
            console.log($scope.data);
            var url="/alipay/doalipay.form?out_trade_no="+$scope.data.out_trade_no+"&subject="+$scope.data.subject+"&total_amount="+$scope.data.total_amount + "&return_url="+ domainName + "/views/test_return.form" + "&notify_url=" + domainName + "/views/test_notify.form";
            window.location = domainName + url;
        };

        if (!isWeixin) {//非微信内置浏览器，说明已经在浏览器中打开了，执行这段逻辑
        }else{//微信浏览器，显示‘在浏览器中打开’图片
            alert("在浏览器内打开");
        }
    });

</script>
</html>
