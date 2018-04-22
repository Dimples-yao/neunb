<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/7/18
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-cn">
<meta name="viewport" content="width=device-width, initial-scale=1">
<head>
    <title>邮箱</title>
    <%@include file="common/commons.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-md-4"></div>
        <div class="col-xs-6 col-md-4">
            <form>
                <div class="form-group">
                    <label for="useraddress">邮箱地址</label>
                    <input type="email" class="form-control" id="useraddress" placeholder="Email">
                </div>
                <div class="form-group">
                    <label for="passwd">密码</label>
                    <input type="password" class="form-control" id="passwd" placeholder="Password">
                </div>
                <button type="submit" class="btn btn-default" ng-click="login()">登录</button>
            </form>
        </div>
        <div class="col-xs-6 col-md-4"></div>
    </div>
</div>



<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
$scope.login = function () {
    $scope.user = {
        useraddress : $("#useraddress").val(),
        passwd : $("#passwd").val()
    };
    $.ajax({
        async:false,
        type: "POST",
        url: "/user/login.form",//注意路径
        data:$scope.user,
        dataType:"json",
        success:function(data){
            if(data == "index"){
                window.location = "/pages/index.jsp";
            }else {
                window.location = "/pages/login.jsp";
            }
        },
        error:function(data){
        }
    });
};
});
</script>
</body>
</html>
