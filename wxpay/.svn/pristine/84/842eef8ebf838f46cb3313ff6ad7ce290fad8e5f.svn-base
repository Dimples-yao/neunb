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
<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        $scope.pclogin = function (state,redirect_uri) {
            $scope.data = {};
            $scope.data.state=state;
            $scope.data.redirect_uri=redirect_uri;
            remotecall("webchat/getpcWxloginUrl",$scope.data,function (data) {
                if (data.result == "success"){
                    console.log("成功");
                    console.log(data);
                    window.location=data.url;
                }else if(data.result == "FAIL"){
                    console.log("失败");
                    console.log(data);
                }
            },function (data) {

            });

        }
        $scope.pclogin("success","http://192.168.0.248:8080/webchat/geWebtUserInfo.form")
    });
</script>
</body>
</html>
