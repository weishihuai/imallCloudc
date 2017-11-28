<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>采购收货单打印</title>
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
        <h4 class="dt-name">采购收货单</h4>
        <div class="dt-title">
            <div class="item"><p>订单编号：</p><span>${printTemplateVo.receiveOrderNum}</span></div>
            <div class="item"><p>供应商名称：</p><span>${printTemplateVo.supplierNm}</span></div>
            <div class="item"><p>收货员：</p><span>${printTemplateVo.receiver}</span></div>
            <div class="item"><p>订单日期：</p><span><fmt:formatDate value="${printTemplateVo.createDate}" pattern="yyyy/MM/dd" />&nbsp;${printTemplateVo.dayOfWeek}</span></div>
            <div class="item"><p>备注：</p><span>${printTemplateVo.remark}</span></div>
        </div>
        <table>
            <thead>
            <tr>
                <th class="serial-number">序号</th>
                <th class="th-coding">商品编码</th>
                <th class="th-title">商品名称</th>
                <th class="dosage">剂型</th>
                <th class="standard">商品规格</th>
                <th class="units">单位</th>
                <th class="manufacturer">生产企业</th>
                <th class="number">订购数量</th>
                <th class="number">收货数量</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="totalPurchaseQuantity" value="0"/>
            <c:set var="totalReceiveQuantity" value="0"/>
            <c:forEach items="${printTemplateVo.itemPrintVos}" var="item" varStatus="s">
                <tr>
                    <td>${s.count}</td>
                    <td>${item.goodsCode}</td>
                    <td>${item.commonNm}</td>
                    <td>${item.dosageForm}</td>
                    <td>${item.spec}</td>
                    <td>${item.unit}</td>
                    <td>${item.produceManufacturer}</td>
                    <td>${item.purchaseQuantity}</td>
                    <td>${item.receiveQuantity}</td>
                </tr>
                <c:set var="totalPurchaseQuantity" value="${totalPurchaseQuantity + item.purchaseQuantity}"/>
                <c:set var="totalReceiveQuantity" value="${totalReceiveQuantity + item.receiveQuantity}"/>
            </c:forEach>

            <tr>
                <td colspan="7" style="text-align: left; padding-left: 10px;">合计</td>
                <td>${totalPurchaseQuantity}</td>
                <td>${totalReceiveQuantity}</td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="foot print">
    <input type="button" onclick="window.print()" value="打&nbsp;&nbsp;印">
</div>
</body>
</html>
