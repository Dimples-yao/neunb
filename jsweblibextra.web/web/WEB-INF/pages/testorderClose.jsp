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
<title>关闭订单</title>
    <%@include file="common.jsp"%>
</head>
<body ng-app="app" ng-controller="ctrl">

<div class="row">
    <div class="col-md-4"></div>
    <div class="col-md-4">
        <form id="wxpay">
            <div class="form-group">
                <label for="out_trade_no">商户订单号 必填</label>
                <input value="" name="out_trade_no" type="out_trade_no" class="form-control" id="out_trade_no" placeholder="商户订单号">
            </div>
            <button type="submit" class="btn btn-default" ng-click="doRefund()">Submit</button>
        </form>
    </div>
    <div class="col-md-4"></div>
</div>
<div id="result"></div>
<script>
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    $scope.doRefund = function () {
        $scope.data = {};
        $scope.data.out_trade_no=$('#out_trade_no').val();

        remotecall("wxpay/doOrderQuery",$scope.data,function (data) {
            if (data.return_code == "SUCCESS"){
                console.log("关闭成功");
                console.log(data);
            }else if(data.return_code == "FAIL"){
                console.log("关闭成功");
                console.log(data);
            }
        },function (data) {

        });

        /*$.ajax({
            async:false,
            type: "POST",
            url: "/wxpay/doOrderQuery.form",//注意路径
            data:$scope.data,
            dataType:"json",
            success:function(data){
                if (data.return_code == "SUCCESS"){
                    console.log("关闭成功");
                    console.log(data);
                }else if(data.return_code == "FAIL"){
                    console.log("关闭成功");
                    console.log(data);
                }
            },
            error:function(data){
            }
        });*/
    };
});
</script>
</body>
</html>
