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
<%@include file="../common/commons.jsp"%>
<body ng-app="app" ng-controller="ctrl">
<div class="col-md-12">
    <table class="table table-hover">
        <thead>
        <tr>
            <td></td>
            <td>发件人</td>
            <td>标题</td>
            <td>内容</td>
            <td>时间</td>
            <td>状态</td>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="email in emails">
            <td></td>
            <td ng-bind="email.username"></td>
            <td ng-bind="email.title"></td>
            <td ng-bind="email.content"></td>
            <td ng-bind="email.emaildata"></td>
            <td>
                <span ng-if="email.isread == '1'">已读</span>
                <span ng-if="email.isread == '0'">未读</span>
            </td>
        </tr>
        </tbody>
    </table>

    <navPage>
        <paging></paging>
    </navPage>
    <span id="msg"></span>

</div>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {

    //初始化的数据
    $scope.iframe = {
        src:"inbox/index.jsp"
    };

    //分页初始化数据
    $scope.pages = [];
    var pageNum;//一共有多少页
    var pageCount = 5;//每页有多少数据

    $scope.loadData = function (pageNow) {
        $.ajax({
            type: "POST",
            url: "/email/loademail.do?pageNow="+pageNow,
            dataType:"json",
            success:function(data){
                $scope.emails = data.emails;
                $scope.$apply();
                if ($scope.emails[0].gologin == "login"){
                    window.location = "/pages/login.jsp";
                }else {
                    pageNum = Math.ceil(data.emailCount / pageCount);
                    for(var i=0;i<pageNum;i++){
                        $scope.pages[i] = i+1;
                        $scope.$apply();
                    }
                    setTimeout(function () {
                        $('.pagination').find('li').each(function (elem,index) {
                            if(index == 1){
                                $(elem).addClass("active");
                                return;
                            }
                        });
                    },500);
                }
            },
            error:function(data){
            }
        });
    };

    //分页
    $scope.gotoPage = function (tr) {
        if(tr == -1){
            $scope.loadData(0);
        }else if(tr == -2){
            $scope.loadData(pageCount*(pageNum-1));
        }else {
            $scope.loadData(pageCount*(tr-1));
        }
    };

    //得到用户信息
    $scope.getUserData =function () {
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

    /*
    * websocket
    * */
    //判断当前浏览器是否支持WebSocket
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://localhost:8080/websocket");
    }
    else {
        alert("对不起！你的浏览器不支持webSocket")
    }
    //连接发生错误的回调方法
    websocket.onerror = function () {
        setMessageInnerHTML("error");
    };
    //连接成功建立的回调方法
    websocket.onopen = function (event) {
    };
    //连接关闭的回调方法
    websocket.onclose = function () {
    };
    //接收到消息的回调方法
    websocket.onmessage = function (event) {
        setMessageInnerHTML(event.data);
    };
    window.onbeforeunload = function () {
            websocket.close();
    };
    //将消息显示在网页上
    function setMessageInnerHTML(innerHTML) {
        if($scope.userMessage.useraddress == innerHTML){
            $("#msg").append("您有新邮件"+"<br/>")
        }
    };
    //关闭连接
    function closeWebSocket() {
        websocket.close();
    };

    //初次加载数据
    $scope.loadData(0);
    $scope.getUserData();

});
</script>
<!--自定义组件-->
<script src="<%=request.getContextPath()%>/pages/js/angular_page.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
