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
<title>Title</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">
<div id="qrcode"></div>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    $scope.getCode_url = function () {
        $scope.qrcode;
        $.ajax({
            async:false,
            type: "POST",
            url: "/wxpay/getCode_url.form",//注意路径
            success:function(data){
                if (data.length != 0){
                    $scope.qrcode = data;
                    new QRCode(document.getElementById('qrcode'), $scope.qrcode);
                }else {
                }
            },
            error:function(data){
            }
        });
    };
    function gennerQrcode(content,nodeid){
        $("#"+nodeid).attr("src","/code/qrcode.form?content="+content+"&WXAttenServlet="+Math.random());
    }
    $scope.getCode_url();
});
</script>
</body>
</html>
