<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建订单</title>
    <%@include file="common.jsp"%>
</head>

<body ng-app="app" ng-controller="ctrl">
<button id="onMenuShareAppMessage" class="btn btn-default">分享给好友</button>
<script type="text/javascript">
var app = angular.module('app',[]).controller('ctrl',function ($scope) {
    //当前项目的地址
    var domainName = location.protocol + "//" + location.host;
    $scope.data = {
        title:"title",
        link:domainName,
        imgUrl:"http://b.hiphotos.baidu.com/image/h%3D300/sign=f3a7ffa4e350352aae6123086343fb1a/f11f3a292df5e0fe50717912566034a85edf72bc.jpg",
        desc:"desc",
        type:"link"
    };
    window.onload=function () {
        remotecall("wxshare/doShare",{requestUrl:domainName + "/views/test_share.form"},function (data) {
            if (data!=null){
                // 微信信息的以及调用的配置
                wx.config({
                    debug: true,
                    appId: data.appId,
                    timestamp: data.timestamp,
                    nonceStr: data.nonceStr,
                    signature: data.signature,
                    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone']
                });
                wx.ready(function(){
                    // 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareTimeline({
                        title: $scope.data.title, // 分享标题
                        link:$scope.data.link,
                        imgUrl: $scope.data.imgUrl,// 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        },
                        fail: function (res) {
                            //用户分享失败
                        }
                    });
                    // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareAppMessage({
                        title: $scope.data.title, // 分享标题
                        desc: $scope.data.desc, // 分享描述
                        link:$scope.data.link,
                        imgUrl: $scope.data.imgUrl, // 分享图标
                        type: $scope.data.type, // 分享类型,music、video或link，不填默认为link
                        success: function () {
                            // 用户确认分享后执行的回调函数
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        },
                        fail: function (res) {
                            //用户分享失败后执行的回调函数
                        }
                    });

                    //获取“分享到QQ”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareQQ({
                        title: $scope.data.title, // 分享标题
                        desc: $scope.data.desc, // 分享描述
                        link: $scope.data.link, // 分享链接
                        imgUrl: $scope.data.imgUrl, // 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        },
                        fail: function (res) {
                            //用户分享失败后执行的回调函数
                        }
                    });

                    //获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareWeibo({
                        title: $scope.data.title, // 分享标题
                        desc: $scope.data.desc, // 分享描述
                        link: $scope.data.link, // 分享链接
                        imgUrl: $scope.data.imgUrl, // 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        },
                        fail: function (res) {
                            //用户分享失败后执行的回调函数
                        }
                    });

                    //获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
                    wx.onMenuShareQZone({
                        title: $scope.data.title, // 分享标题
                        desc: $scope.data.desc, // 分享描述
                        link: $scope.data.link, // 分享链接
                        imgUrl: $scope.data.imgUrl, // 分享图标
                        success: function () {
                            // 用户确认分享后执行的回调函数
                        },
                        cancel: function () {
                            // 用户取消分享后执行的回调函数
                        },
                        fail: function (res) {
                            //用户分享失败后执行的回调函数
                        }
                    });

                });
                wx.error(function(res){
                    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
                });
            }
        });
    }

    document.querySelector('#onMenuShareTimeline').onclick = function () {
        //获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
        wx.onMenuShareQZone({
            title: $scope.data.title, // 分享标题
            desc: $scope.data.desc, // 分享描述
            link: $scope.data.link, // 分享链接
            imgUrl: $scope.data.imgUrl, // 分享图标
            trigger: function (res) {
                alert('用户点击分享到好友');
            },
            success: function () {
                // 用户确认分享后执行的回调函数
                alert("成功");
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
                alert("取消");
            },
            fail: function (res) {
                //用户分享失败后执行的回调函数
                alert("失败");
            }
        });
    }

});
</script>
</body>
</html>
