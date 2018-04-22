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
    <title>退款结果通知</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">

<div class="row">
</div>
<div id="result"></div>
<script>
    var app = angular.module('app',[]).controller('ctrl',function ($scope) {
        $scope.data="微信加密报文";
        $scope.getWeChatPayReturn = function () {

            remotecall("wxpay/getWXRefundReturn",$scope.data,function (data) {
                if (data!=null){
                    console.log("返回结果");
                    console.log(data);
                }else{
                    console.log("无查询结果");
                    console.log(data);
                }
            },function (data) {

            });

            /*$.ajax({
                async:false,
                type: "POST",
                url: "/wxpay/getWXRefundReturn.form",//注意路径
                data:$scope.data,
                dataType:'JSON',
                success:function(data){
                    if (data!=null){
                        console.log("返回结果");
                        console.log(data);
                    }else{
                        console.log("无查询结果");
                        console.log(data);
                    }
                },
                error:function(data){
                }
            });*/
        };
        $scope.getWeChatPayReturn();
    });
</script>
</body>
</html>
