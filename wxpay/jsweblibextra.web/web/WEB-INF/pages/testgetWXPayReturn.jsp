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
<title>支付结果通知</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">

<div class="row">
</div>
<div id="result"></div>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    $scope.data="<xml><appid><![CDATA[wx2421b1c4370ec43b]]></appid><attach><![CDATA[支付测试]]></attach><bank_type><![CDATA[CFT]]></bank_type><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10000100]]></mch_id><nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str><openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid><out_trade_no><![CDATA[1409811653]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign><sub_mch_id><![CDATA[10000100]]></sub_mch_id><time_end><![CDATA[20140903131540]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id></xml>";
    $scope.getWeChatPayReturn = function () {

        remotecall("wxpay/getwxPayReturn",$scope.data,function (data) {
            if (data!=null){
                console.log("返回结果");
                console.log(data);
            }else{
                console.log("无查询结果");
                console.log(data);
            }
        },function (data) {

        });

        /*$.ajax({
            async:false,
            type: "POST",
            url: "/wxpay/getwxPayReturn.form",//注意路径
            data:$scope.data,
            dataType:"XML",
            success:function(data){
                if (data!=null){
                    console.log("返回结果");
                    console.log(data);
                }else{
                    console.log("无查询结果");
                    console.log(data);
                }
            },
            error:function(data){
            }
        });*/
    };
    $scope.getWeChatPayReturn();
});
</script>
</body>
</html>
