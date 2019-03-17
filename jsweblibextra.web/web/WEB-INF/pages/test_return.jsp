<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/11/13
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>同步回调地址</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
</body>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    var url = window.location.href; //获取全部url
    var domainName = location.protocol + "//" + location.host;
    var requesturl = url.split('?');
    console.log(requesturl[1]);
    $.ajax({
        type:"POST",
        url:domainName+"/alipay/getReturnResultPay.form?" + requesturl[1],
        success:function (data) {
            if (data.verify_result){
                console.log("用户支付成功");
            }else{
                /!*用户支付失败，或验证失败*!/
                console.log("用户支付失败");
            }
        },
        error:function () {
            
        }
    })
    /*remotecallasync("alipay/getReturnResultPay.form?" + requesturl[1],{},function (data) {
        if (data.verify_result){
            console.log("用户支付成功");
        }else{
            /!*用户支付失败，或验证失败*!/
            console.log("用户支付失败");
        }
    },function (data) {

    });*/
});
</script>
</html>
