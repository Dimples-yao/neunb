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
	<title>授权</title>
	<%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div id="result"></div>
<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        $scope.authorization = function () {
            window.location = "http://192.168.0.248:8080/webchat/getinfo.form?state=1&redirect_uri=http://192.168.0.248:8080/webchat/getUserInfo.form";
        };
        $scope.authorization();
    });
</script>
</body>
</html>
