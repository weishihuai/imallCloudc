<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>

<head lang="en">
    <script type="text/javascript">
    <c:forEach var="appContext" items="${sysAppContexts}">
         var ${appContext.key}="${appContext.value}/${appContext.key}";
    </c:forEach>
    </script>
    <meta charset="utf-8">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="yes" name="apple-touch-fullscreen">
    <meta content="telephone=no,email=no" name="format-detection">

    <script src="${iportal}/static/lib/js/jquery-2.1.1.min.js"></script>
    <script src="${iportal}/static/lib/js/swiper-3.3.1.min.js"></script>
    <script src="${iportal}/static/lib/js/flexible.js"></script>
    <script src="${iportal}/static/lib/js/flexible_css.js"></script>
    <link href="${iportal}/static/css/wechat/base.css" rel="stylesheet" />
    <link href="${iportal}/static/css/wechat/header.css" rel="stylesheet" />
    <link href="${iportal}/static/css/wechat/index.css" rel="stylesheet" />
    <link rel="stylesheet" href="${iportal}/static/css/wechat/wechatmain.css" type="text/css">
    <link rel="stylesheet" href="${iportal}/static/css/wechat/swiper-3.3.1.min.css" type="text/css">
    <script type="text/javascript">
        function showError(error) {
            $("body .layer-warning").html(error).show();
            setTimeout(function () {
                $("body .layer-warning").html("").hide();
            }, 2000);
        }

        function getSpecImg(imgUrl, spec) {
            if (imgUrl && imgUrl.trim().length > 0){
                var index = imgUrl.lastIndexOf(".");
                if (index > 0){
                    return imgUrl.substring(0, index) + "_" + spec + imgUrl.substring(index, imgUrl.length);
                }else {
                    return imgUrl;
                }
            }else {
                return iportal + "/static/img/wechat/nopict_" + spec + ".png";
            }
        }

        function indexToCart(obj, src) {
            src = src ? src : iportal +"/static/img/wechat/pic66x66.png";
            $("#root").after('<div id="floatOrder" style="z-index: 20;position: absolute;" class="pay"><img style="width:1.1rem;height: 1.1rem;border-radius:0.75rem;" src="'+ src +'"/></div>');
            var myBox = $("#floatOrder");
            var height = $(obj).height();
            var width = $(obj).width();
            var offset = $(".bar-demand .pic").offset();
            var targetL = offset.left;
            var targetT = offset.top + 15;
            myBox.css({
                "position": "absolute",
                "left": $(obj).offset().left + "px",
                "top": $(obj).offset().top + height/2 + "px"
            });
            myBox.animate({
                        "left": targetL + "px",
                        "top": targetT + "px",
                        "width": width + "px",
                        "height": height + "px"
                    },
                    1200, null,
                    function () {
                        myBox.fadeTo(0, 0.1).remove();
                    });
        }

        function goodsListToCart(obj, src) {
            src = src ? src : iportal +"/static/img/wechat/pic66x66.png";
            $("#root").after('<div id="floatOrder" style="z-index: 20;position: absolute;" class="pay"><img style="width:1.1rem;height: 1.1rem;border-radius:0.75rem;" src="'+ src +'"/></div>');
            var myBox = $("#floatOrder");
            var height = $(obj).height();
            var width = $(obj).width();
            var offset = $(".fl-car").offset();
            var targetL = offset.left;
            var targetT = offset.top + 15;
            myBox.css({
                "position": "absolute",
                "left": $(obj).offset().left + "px",
                "top": $(obj).offset().top + height/2 + "px"
            });
            myBox.animate({
                        "left": targetL + "px",
                        "top": targetT + "px",
                        "width": width + "px",
                        "height": height + "px"
                    },
                    1200, null,
                    function () {
                        myBox.fadeTo(0, 0.5).remove();
                    });
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var signUrl = location.href.split('#')[0];
            signUrl = encodeURIComponent(signUrl);
            $.ajax({
                 type:'GET',
                 url: iportal + "/wechat/common/getWeiXinJsConfig.json?signUrl=" + signUrl,
                 success:function(data){
                     wx.config({
                         debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息通过log打出，仅在pc端时才会打印。
                         appId: data.appId, // 必填，公众号的唯一标识
                         timestamp: data.timestamp, // 必填，生成签名的时间戳
                         nonceStr: data.nonceStr, // 必填，生成签名的随机串
                         signature: data.signature,// 必填，签名，见附录1
                         jsApiList: ['openLocation', 'hideMenuItems', 'showMenuItems', 'onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'chooseImage', 'uploadImage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
                     });
                 },
                 error:function(XMLHttpRequest, textStatus) {
                     if (XMLHttpRequest.status == 500) {
                        showError("初始化微信jssdk失败");
                     }
                 }
             })
        })
    </script>
</head>

<body>
<div id="root">

</div>
<div style="display: none;" class="layer-warning"></div>
<script src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>
<script src="${iportal}/static/lib/js/jquery.cookie.js"></script>
<script src="${iportal}/static/lib/js/swiper-3.3.1.min.js"></script>
<script src="${iportal}/static/lib/js/jweixin-1.0.0.js"></script>
<script src="${iportal}/static/dist/wechatmain.js"></script>
</body>
</html>
