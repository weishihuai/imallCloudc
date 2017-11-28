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
    <title>销售POS-退货</title>
    <link rel="stylesheet" href="${iportal}/static/css/lq.datetimepick.css" type="text/css">
    <link rel="stylesheet" href="${iportal}/static/css/base.css" type="text/css">
    <link rel="stylesheet" href="${iportal}/static/css/header.css" type="text/css">
    <link rel="stylesheet" href="${iportal}/static/css/pos-front.css" type="text/css">

    <script src="${iportal}/static/js/common/lazyload.js"></script>

    <!--jQuery [ REQUIRED ]-->
    <script src="${iportal}/static/lib/js/jquery-2.1.1.min.js"></script>

</head>


<body>
<script type="text/javascript">
    <c:forEach var="appContext" items="${sysAppContexts}">
    var ${appContext.webContext}="${appContext.hostname}/${appContext.webContext}";
    </c:forEach>
</script>
<div class="pos-header">智慧药店系统v1.0</div>

<div id="root">
</div>
<script src="${iportal}/static/dist/common.js"></script>
<script src="${iportal}/static/dist/posreturned.js"></script>
</body>

<!--[if lt IE 9]>
<script async  src="${iportal}/static/lib/plugins/es5-shim/es5-shim.js"></script>
<script async  src="${iportal}/static/lib/plugins/json3/lib/json3.min.js"></script>
<![endif]-->

<script src="${iportal}/static/lib/plugins/iconfont/iconfont.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/selectUi.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-nicescroll/jquery-nicescroll.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/jquery-jSelect.min.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/jselect.js"></script>
<script src="${iportal}/static/lib/plugins/jquery-time/lq.datetimepick.js"></script>
<script src="${iportal}/static/lib/plugins/chosen/chosen.jquery.js"></script>
<script src="${iportal}/static/lib/plugins/colResizable/colResizable-1.6.js"></script>

</html>
