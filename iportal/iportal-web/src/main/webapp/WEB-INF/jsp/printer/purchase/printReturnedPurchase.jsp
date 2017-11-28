<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购进退出单打印</title>
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
        <h4 class="dt-name">购进退出单</h4>
        <div class="dt-title">
            <div class="item"><p>订单编号：</p><span>${printTemplateVo.returnedPurchaseOrderNum}</span></div>
            <div class="item"><p>供应商名称：</p><span>${printTemplateVo.supplierNm}</span></div>
            <div class="item"><p>审核人：</p><span>${printTemplateVo.approveMan}</span></div>
            <div class="item"><p>订单日期：</p><span><fmt:formatDate value="${printTemplateVo.createDate}" pattern="yyyy/MM/dd" />&nbsp;${printTemplateVo.dayOfWeek}</span></div>
            <div class="item"><p>出库员：</p><span>${printTemplateVo.outStorageMan}</span></div>
            <div class="item"><p>备注：</p><span></span></div>
        </div>
        <table>
            <thead>
            <tr>
                <th class="serial-number">序号</th>
                <th class="th-coding">商品编码</th>
                <th class="th-title">商品名称</th>
                <th class="standard">商品规格</th>
                <th class="batch">批号</th>
                <th class="date">有限期至</th>
                <th class="date">生产日期</th>
                <th class="price">单价</th>
                <th class="number">退货数量</th>
                <th class="price">退货金额</th>
                <th class="dosage">剂型</th>
                <th class="units">单位</th>
                <th class="manufacturer">生产企业</th>
            </tr>
            </thead>
            <tbody>
            <c:set var="totalReturnedPurchaseQuantity" value="0"/>
            <c:set var="totalAmount" value="0"/>
            <c:forEach items="${printTemplateVo.itemPrintVos}" varStatus="s" var="item">
                <tr>
                    <td>${s.count}</td>
                    <td>${item.goodsCode}</td>
                    <td>${item.goodsNm}</td>
                    <td>${item.spec}</td>
                    <td>${item.goodsBatch}</td>
                    <td>${item.productionDateString}</td>
                    <td>${item.validityString}</td>
                    <td>${item.purchaseUnitPrice}</td>
                    <td>${item.returnedPurchaseQuantity}</td>
                    <td>${item.amount}</td>
                    <td>${item.dosageForm}</td>
                    <td>${item.unit}</td>
                    <td>${item.produceManufacturer}</td>
                </tr>
                <c:set var="totalReturnedPurchaseQuantity" value="${totalReturnedPurchaseQuantity + item.returnedPurchaseQuantity}"/>
                <c:set var="totalAmount" value="${totalAmount + item.amount}"/>
            </c:forEach>

            <tr>
                <td colspan="8" style="text-align: left; padding-left: 10px;">合计</td>
                <td>${totalReturnedPurchaseQuantity}</td>
                <td>${totalAmount}</td>
                <td></td>
                <td></td>
                <td></td>
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
