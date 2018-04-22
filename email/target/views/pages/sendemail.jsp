<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/7/20
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>邮箱</title>
</head>
<%@include file="common/commons.jsp"%>
<body ng-app="app" ng-controller="ctrl">
<button class="btn btn-default" type="submit">返回</button>
<form>
    <div class="form-group">
        <label for="senderid">发件人</label>
        <input type="text" class="form-control" id="senderid" placeholder="发件人地址" value="{{userMessage.useraddress}}">
    </div>
    <div class="form-group">
        <label for="acceptorid">收件人</label>
        <input type="text" class="form-control" id="acceptorid" placeholder="收件人地址">
    </div>
    <div class="form-group">
        <label for="title">标题</label>
        <input type="text" class="form-control" id="title" placeholder="邮件标题">
    </div>
    <div class="form-group">
        <label for="content">内容</label>
        <input type="text" class="form-control" id="content" placeholder="邮件内容">
    </div>
    <button type="submit" class="btn btn-default" ng-click="sendEmail()">发送</button>
</form>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
//    加载初始信息
    $scope.loadData =function () {
        $.ajax({
            type:"POST",
            url:"/user/getUserMessage.do",
            dataType:"json",
            success:function(data){
                $scope.userMessage = data;
                $scope.$apply();
            },
            error:function(data){
            }
        });
    };

//    发送邮件
    $scope.sendEmail = function () {
        $.ajax({
            async:false,
            type : "POST",
            url : "/email/sendEmail.do",
            data : {
                title : $("#title").val(),
                content : $("#content").val(),
                senderaddress : $("#senderid").val(),
                acceptoraddress : $("#acceptorid").val(),
            },
            dataType : "json",
            success : function (data) {
            },
            error : function (data) {
            }
        });
        window.location = "/pages/index.jsp";
    }

//初始化数据
    $scope.loadData();
});
</script>
</body>
</html>
