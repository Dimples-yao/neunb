<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/7/17
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮箱</title>
</head>
<%@include file="common/commons.jsp"%>
<body ng-app="app" ng-controller="ctrl">
<a class="btn btn-default" href="/pages/sendemail.jsp" role="button">写邮件</a>
<div class="container">
    <div class="row">
        <div class="col-md-2 container-fluid">
            <div class="row">
                <button type="button" class="btn btn-default" ng-click="changeItem('inbox')">收件箱</button>
            </div>
            <div class="row">
                <button type="button" class="btn btn-default" ng-click="changeItem('outbox')">发件箱</button>
            </div>
            <div class="row">
                <button type="button" class="btn btn-default" ng-click="changeItem('recycle')">回收站</button>
            </div>
            <div class="row">
                <button type="button" class="btn btn-default" ng-click="changeItem('addressbook')">联系人地址</button>
            </div>
        </div>
        <div class="col-md-10 container-fluid">
            <iframe id="iframe" ng-src="{{iframe.src}}" width="100%" height="100%" frameborder="no"></iframe>
        </div>
    </div>
</div>

<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {

//    初始化的数据
    $scope.iframe = {
        src:"inbox/index.jsp"
    }

//    切换左边标题
    $scope.changeItem = function (tr) {
        switch (tr){
            case "inbox":
                $scope.iframe.src = "inbox/index.jsp";
                break;
            case "outbox":
                $scope.iframe.src = "outbox/index.jsp";
                break;
            case "recycle":
                $scope.iframe.src = "recycle/index.jsp";
                break;
            case "addressbook":
                $scope.iframe.src = "addressbook/index.jsp";
                break;
        }
    };

});
</script>
</body>
</html>
