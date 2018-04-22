<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/12/1
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>微信网页授权</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div id="login_container"></div>
<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        var obj = new WxLogin({
            id:"login_container",
            appid: "wx169165ae0a325d05",
            scope: "snsapi_login",
            redirect_uri: "http://192.168.0.248:8080/webchat/geWebtUserInfo.form",
            state: "success",
            style: "black",
            href: ".impowerBox .qrcode {width: 200px;}.impowerBox .title {display: none;}.impowerBox .info {width: 200px;}.status_icon {display: none}.impowerBox .status {text-align: center;} "
        });
    });
</script>
</body>
</html>
