<%--
  User: lxd
  Date: 2015/8/10
  Time: 14:41
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>平台</title>

    <script src="${iportal}/static/js/common/lazyload.js"></script>

    <!--jQuery [ REQUIRED ]-->
    <script src="${iportal}/static/lib/js/jquery-2.1.1.min.js"></script>
    <script src="${iportal}/static/js/common/login-listener.js"></script>


</head>


<body>
<script type="text/javascript">
    <c:forEach var="appContext" items="${sysAppContexts}">
    var ${appContext.webContext}="${appContext.hostname}/${appContext.webContext}";
    </c:forEach>

    function showSuccess(tip) {
        $("#tip").html(tip);
        $("#tipLayer").show();
    }
</script>

<div id="root">
</div>
<div id="tipLayer" class="layer" style="z-index: 1000;display: none;">
    <div class="layer-box layer-text w430">
    <div class="layer-header">
      <span>系统提示</span><a onclick="$('#tipLayer').hide()" class="close" href="javascript:void(0)"></a>
    </div>
    <div class="layer-body">
        <span>
          <div id="tip"></div>
        </span>
    </div>
    </div>
</div>
<script src="${iportal}/static/dist/common.js"></script>
<script src="${iportal}/static/dist/main.js"></script>
</body>

    <!--[if lt IE 9]>
    <script async  src="${iportal}/static/lib/plugins/es5-shim/es5-shim.js"></script>
    <script async  src="${iportal}/static/lib/plugins/json3/lib/json3.min.js"></script>
    <![endif]-->
<link rel="stylesheet" href="${iportal}/static/lib/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

<link rel="stylesheet" href="${iportal}/static/lib/plugins/html5_fileupload/control/css/imallUpload.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/lib/plugins/chosen/chosen.min.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/lib/plugins/jquery.fancybox-1.3.1/fancybox/jquery.fancybox-1.3.1.css" type="text/css">

<link rel="stylesheet" href="${iportal}/static/css/lq.datetimepick.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/j-select.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/base.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/header.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/management.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/index-decoration.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/wechat/header.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/wechat/swiper-3.3.1.min.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/lib/plugins/searchableSelect/jquery.searchableSelect.css">
<link rel="stylesheet" href="${iportal}/static/lib/plugins/nestable/css/nestable.css">

<script src="${iportal}/static/lib/plugins/colResizable/colResizable-1.6.js"></script>
<script src="${iportal}/static/lib/plugins/iconfont/iconfont.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/selectUi.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-nicescroll/jquery-nicescroll.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/jquery-jSelect.min.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/lq.datetimepick.js"></script>
<script src="${iportal}/static/lib/plugins/chosen/chosen.jquery.js"></script>
<script src="${iportal}/static/lib/plugins/nestable/js/jquery.nestable.js"></script>
<script src="${iportal}/static/lib/js/swiper-3.3.1.min.js"></script>

</html>
