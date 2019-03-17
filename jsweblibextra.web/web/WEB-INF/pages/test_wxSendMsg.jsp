<%--
  Created by IntelliJ IDEA.
  User: NEUNB_Lisy
  Date: 2017/12/19
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>捐款成功消息</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="userId">用户openid 必填</label>
                <input value="ox8WP0x23gTY9A_AJPn07hWzW2uA" name="userId" type="userId" class="form-control" id="userId" placeholder="用户openid">
            </div>
            <div class="form-group">
                <label for="url">链接 必填</label>
                <input value="www.baidu.com" name="url" type="url" class="form-control" id="url" placeholder="链接">
            </div>
            <div class="form-group">
                <label for="first">标题 必填</label>
                <input value="您的捐款已经成功，感谢您的热情参与" name="first" type="first" class="form-control" id="first" placeholder="标题">
            </div>
            <div class="form-group">
                <label for="keyword1">慈善项目名称 必填</label>
                <input value="青柠檬工艺·辽宁省青少年发展基金会" name="keyword1" type="keyword1" class="form-control" id="keyword1" placeholder="用户openid">
            </div>
            <div class="form-group">
                <label for="keyword2">捐款金额 必填</label>
                <input value="100元" name="keyword2" type="keyword2" class="form-control" id="keyword2" placeholder="捐款金额">
            </div>
            <div class="form-group">
                <label for="keyword3">捐款时间 必填</label>
                <input value="2017/12/19 13:50:00" name="keyword3" type="keyword3" class="form-control" id="keyword3" placeholder="捐款时间">
            </div>
            <div class="form-group">
                <label for="remark">简介 必填</label>
                <input value="聚沙成塔，集腋成裘，涓涓细流将汇成爱的海洋。我们相信，通过大家的共同努力一定能给受益人送去关怀和帮助。我们会实时发布善款实施进度，敬请关注" name="remark" type="remark" class="form-control" id="remark" placeholder="简介">
            </div>
            <button type="submit" class="btn btn-default" ng-click="sendMsg()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>

<script>
var app=angular.module('app',[]).controller('ctrl',function ($scope) {
    $scope.sendMsg = function () {
        $scope.data = {};
        $scope.data.userId = $('#userId').val();
        $scope.data.url = $('#url').val();
        $scope.data.first = $('#first').val();
        $scope.data.keyword1 = $('#keyword1').val();
        $scope.data.keyword2 = $('#keyword2').val();
        $scope.data.keyword3 = $('#keyword3').val();
        $scope.data.remark = $('#remark').val();
        remotecall("wxendmsg/sendmsg",$scope.data,function (data) {
            if (data.errmsg == "ok"){
                console.log("发送成功");
            }else {
                console.log("发送失败");
            }
        },function (data) {
            
        })
    }
});
</script>
</body>
</html>
