import React, {Component, PropTypes} from "react";

class DetailList extends Component {

    render() {
        const {store} = this.context;
        const content = store.getState().todos.page.content;
        return (
                <div className="mc">
                 <div className="table-box">
                     <table style={{width: "3000px"}}>
                         <thead>
                         <tr>
                             <th style={{"width": "135px"}} className="order-number">退货单编号</th>
                             <th className="order-date">退货日期</th>
                             <th className="supplier">供应商单位名称</th>
                             <th style={{"width": "80px"}} className="procurement">出库人名称</th>
                             <th className="goods">商品编码</th>
                             <th className="goods">商品名称</th>
                             <th className="goods">通用名称</th>
                             <th className="number">规格</th>
                             <th className="dosage">剂型</th>
                             <th className="units">单位</th>
                             <th className="manufacturer">生产厂商</th>
                             <th className="approval">批准文号</th>
                             <th className="producing">产地</th>
                             <th className="batch-number">批号</th>
                             <th className="rk-time">生产日期</th>
                             <th className="rk-time">有效日期</th>
                             <th className="procurement">退货原因</th>
                             <th className="number">数量</th>
                             <th className="price">单价</th>
                             <th className="price">金额</th>
                             <th style={{"width": "135px"}} className="procurement">入库单号</th>
                         </tr>
                         </thead>
                         {content.length <= 0 &&
                         <tr >
                             <td colSpan="100" style={{textAlign:"center"}}>
                                 <div className="empty-box">
                                     <span>暂无数据</span>
                                 </div>
                             </td>
                         </tr>
                         }
                         <tbody>
                         {
                             content.map((ct, i)=> {
                                 return (
                                     <tr key={i}>
                                         <td>
                                             <div className="td-cont" title={ct.returnedPurchaseOrderNum}>{ct.returnedPurchaseOrderNum}</div>
                                         </td>
                                         <td><div className="td-cont" title={ct.createDateString}>{ct.createDateString}</div></td>
                                         <td>
                                             <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                         </td>
                                         <td><div className="td-cont" title={ct.outStorageMan}>{ct.outStorageMan}</div></td>
                                         <td>
                                             <div className="td-cont" title={ct.goodsCode}>{ct.goodsCode}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.goodsNm}>{ct.goodsNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.commonNm}>{ct.commonNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.spec}>{ct.spec}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.dosageForm}>{ct.dosageForm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.unit}>{ct.unit}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.produceManufacturer}>{ct.produceManufacturer}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.approvalNumber}>{ct.approvalNumber}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.productionPlace}>{ct.productionPlace}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.goodsBatch}>{ct.goodsBatch}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.productionDateString}>{ct.productionDateString}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.validityString}>{ct.validityString}</div>
                                         </td>
                                         <td><div className="td-cont" title={ct.returnedPurchaseReason}>{ct.returnedPurchaseReason}</div></td>
                                         <td><div className="td-cont" title={ct.returnedPurchaseQuantity}>{ct.returnedPurchaseQuantity}</div></td>
                                         <td><div className="td-cont" title={ct.purchaseUnitPrice}>{ct.purchaseUnitPrice}</div></td>
                                         <td><div className="td-cont" title={ct.amount}>{ct.amount}</div></td>
                                         <td><div className="td-cont" title={ct.acceptanceOrderNum}>{ct.acceptanceOrderNum}</div></td>
                                     </tr>
                                 );
                             })
                         }
                         </tbody>
                     </table>
                 </div>
                </div>
        )
    }
}

DetailList.contextTypes = {
    store: React.PropTypes.object
};

export default DetailList;