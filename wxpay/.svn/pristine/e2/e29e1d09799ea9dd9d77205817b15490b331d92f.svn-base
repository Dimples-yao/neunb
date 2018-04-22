<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/12/29
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>redis测试</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="openid">openid 必填</label>
                <input value="" name="openid" type="openid" class="form-control" id="openid" placeholder="openid">
            </div>
            <button type="submit" class="btn btn-default" ng-click="setopenid()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>
</body>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    $scope.setopenid = function () {
        $scope.data = {};
        $scope.data.openid = $('#openid').val();
        remotecallasync("setredis",$scope.data,function (data){
            console.log(data.status);
        },function (data) {
            console.log("失败");
        });
    }
});
</script>
</html>
