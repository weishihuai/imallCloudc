<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购验收单打印</title>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" href="${iportal}/static/css/base.css">
    <link rel="stylesheet" href="${iportal}/static/css/print-single2.css">
    <style media="print">
        @page {
            size: auto;  /* auto is the initial value */
            margin: 0mm; /* this affects the margin in the printer settings */
        }
        .noprint{
            display:none
        }
    </style>
</head>
<body>
<div class="main">
    <div class="main-box">
        <h4 class="dt-name">采购验收单</h4>
        <div class="dt-title">
            <div class="item"><p>订单编号：</p><span>${printTemplateVo.acceptanceOrderNum}</span></div>
            <div class="item"><p>供应商名称：</p><span>${printTemplateVo.supplierNm}</span></div>
            <div class="item"><p>制单员：</p><span>${printTemplateVo.docMaker}</span></div>
            <div class="item"><p>订单日期：</p><span><fmt:formatDate pattern="yyyy/MM/dd" value="${printTemplateVo.createDate}"/>&nbsp;${printTemplateVo.dayOfWeek}</span></div>
            <div class="item"><p>收货员：</p><span>${printTemplateVo.acceptanceMan}</span></div>
            <div class="item"><p>备注：</p><span>${printTemplateVo.remark}</span></div>
        </div>
        <table>
            <thead>
            <tr>
                <th class="serial-number">序号</th>
                <th class="th-coding">商品编码</th>
                <th class="th-title">商品通用名称</th>
                <th class="standard">商品规格</th>
                <th class="units">单位</th>
                <th class="manufacturer">生产企业</th>
                <th class="batch">批号</th>
                <th class="date">生产日期</th>
                <th class="date">有限期至</th>
                <th class="goods">货位</th>
                <th class="price">进货单价</th>
                <th class="number">入库数量</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="totalInStorageQuantity" value="0"/>
            <c:forEach items="${printTemplateVo.itemPrintVos}" var="item" varStatus="s">
                <tr>
                    <th class="serial-number">${s.count}</th>
                    <th class="th-coding">${item.goodsCode}</th>
                    <th class="th-title">${item.commonNm}</th>
                    <th class="standard">${item.spec}</th>
                    <th class="units">${item.unit}</th>
                    <th class="manufacturer">${item.produceManufacturer}</th>
                    <th class="batch">${item.goodsBatch}</th>
                    <th class="date">${item.productionDateString}</th>
                    <th class="date">${item.validityString}</th>
                    <th class="goods">${item.goodsAllocation}</th>
                    <th class="price">${item.purchaseUnitPrice}</th>
                    <th class="number">${item.inStorageQuantity}</th>
                </tr>
                <c:set var="totalInStorageQuantity" value="${totalInStorageQuantity + item.inStorageQuantity}"/>
            </c:forEach>
            <tr>
                <td colspan="11" style="text-align: left; padding-left: 10px;">合计</td>
                <td>${totalInStorageQuantity}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="foot noprint">
    <input type="button" onclick="window.print()" value="打&nbsp;&nbsp;印">
</div>
</body>
</html>
