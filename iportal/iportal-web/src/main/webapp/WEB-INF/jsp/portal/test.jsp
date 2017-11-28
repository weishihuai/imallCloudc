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
    <!--Bootstrap Stylesheet [ REQUIRED ]-->
    <link href="${iportal}/static/lib/css/bootstrap.min.css" rel="stylesheet">
    <!--Nifty Stylesheet [ REQUIRED ]-->
    <link href="${iportal}/static/lib/css/nifty.min.css" rel="stylesheet">



    <!--Page Load Progress Bar [ OPTIONAL ]-->
    <link href="${iportal}/static/lib/plugins/pace/pace.min.css" rel="stylesheet">
    <script src="${iportal}/static/lib/plugins/pace/pace.min.js"></script>

    <%--程序入口--%>
    <%--    <script src="${iportal}/static/lib/plugins/react-15.1.0/react-dom.js"></script>
        <script src="${iportal}/static/lib/plugins/react-15.1.0/react.js"></script>--%>
    <%-- <script src="${iportal}/static/build/common.js"></script>--%>

    <script src="${iportal}/static/js/common/lazyload.js"></script>

    <!--jQuery [ REQUIRED ]-->
    <script src="${iportal}/static/lib/js/jquery-2.1.1.min.js"></script>

    <!-- iconfont -->
    <style type="text/css">
        .imall-custom-small-icon {
            width: 1em; height: 1em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
        .imall-custom-big-icon {
            width: 2em; height: 2em;
            vertical-align: -0.15em;
            fill: currentColor;
            overflow: hidden;
        }
    </style>
</head>


<body>
<script type="text/javascript">
    <c:forEach var="appContext" items="${sysAppContexts}">
    var ${appContext.webContext}="${appContext.hostname}/${appContext.webContext}";
    </c:forEach>
</script>

<div id="lazyLoadView">
</div>
<script src="${iportal}/static/dist/common.js"></script>
<script src="${iportal}/static/dist/doc.js"></script>
</body>

    <!--[if lt IE 9]>
    <script async  src="${iportal}/static/lib/plugins/es5-shim/es5-shim.js"></script>
    <script async  src="${iportal}/static/lib/plugins/json3/lib/json3.min.js"></script>
    <![endif]-->



    <!--Font Awesome [ OPTIONAL ]-->
    <link href="${iportal}/static/lib/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">

    <!--Animate.css [ OPTIONAL ]-->
    <link href="${iportal}/static/lib/plugins/animate-css/animate.min.css" rel="stylesheet">
    <!--Bootstrap Select [ OPTIONAL ]-->
    <link href="${iportal}/static/lib/plugins/bootstrap-select/bootstrap-select.min.css" rel="stylesheet">

    <%-- <link href="${iportal}/static/lib/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">--%>

    <%--w5c验证框架Css--%>
    <link href="${iportal}/static/lib/plugins/angular-w5c-validator/style.css" rel="stylesheet">


    <link href="${iportal}/static/lib/css/ngDialog.css" rel="stylesheet">
    <link href="${iportal}/static/lib/css/ngDialog-theme-default.css" rel="stylesheet">
    <link href="${iportal}/static/lib/css/ngDialog-theme-plain.css" rel="stylesheet">

    <link href="${iportal}/static/lib/plugins/bootstrap-table/bootstrap-table.css" rel="stylesheet">

    <link rel="stylesheet" href="${iportal}/static/lib/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">

    <link href="${iportal}/static/css/fileManager.css" rel="stylesheet">

    <link rel="stylesheet" href="${iportal}/static/lib/plugins/html5_fileupload/control/css/imallUpload.css" type="text/css">
    <link rel="stylesheet" href="${iportal}/static/lib/plugins/jquery.fancybox-1.3.1/fancybox/jquery.fancybox-1.3.1.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="${iportal}/static/lib/plugins/bs_pagination-master/jquery.bs_pagination.min.css">

    <link rel="stylesheet" type="text/css" href="${iportal}/static/lib/plugins/bootstrap-datetimepicker-0.0.11/css/bootstrap-datetimepicker.min.css">

    <link rel="stylesheet" href="${iportal}/static/lib/plugins/bootstrap-multiselect/css/bootstrap-multiselect.css" type="text/css">

    <style type="text/css">
        .bootbox {z-index:99999;}
    </style>


<!--BootstrapJS [ RECOMMENDED ]-->
<script src="${iportal}/static/lib/js/bootstrap.min.js"></script>
<script src="${iportal}/static/lib/plugins/bootstrap-modal/bootstrap-modalmanager.js"></script>
<script src="${iportal}/static/lib/plugins/bootstrap-modal/bootstrap-modal.js"></script>

<%--<script>
    lazyload('js', '${iportal}/static/lib/js/nifty.js', 1000, false);
</script>--%>

<!--Morris.js [ OPTIONAL ]-->
<script src="${iportal}/static/lib/plugins/morris-../lib/js/morris.min.js"></script>
<script src="${iportal}/static/lib/plugins/morris-../lib/js/raphael-../lib/js/raphael.min.js"></script>

<link href="${iportal}/static/lib/css/react-bootstrap-table.css" rel="stylesheet">


<link href="${iportal}/static/css/forms-css3-html5-validation/styles.css" rel="stylesheet">
<link rel="stylesheet" href="${iportal}/static/css/page-base.css" type="text/css">
<link rel="stylesheet" href="${iportal}/static/css/im-cover.css" type="text/css">

<script src="${iportal}/static/lib/plugins/iconfont/iconfont.js"></script>

<!--Morris.js [ OPTIONAL ] http://localhost:8080/custss/static/dist/sensitiveKeywordsList.js -->
<%--<script src="http://localhost:8080/custss/static/dist/sensitiveKeywordsList.js"></script>--%>

<%--新增--%>
<link href="${iportal}/static/lib/css/lq.datetimepick.css" rel="stylesheet">
<script src="${iportal}/static/lib/plugins/iconfont/iconfont.js"></script>
</html>
