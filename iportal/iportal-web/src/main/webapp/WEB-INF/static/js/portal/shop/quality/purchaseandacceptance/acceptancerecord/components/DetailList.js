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
                             <th style={{"width": "135px"}} className="order-number">采购订单编号</th>
                             <th style={{"width": "135px"}} className="order-number">验收单编号</th>
                             <th className="th-title">供应商单位名称</th>
                             <th style={{width: "70px"}} className="number">验收人</th>
                             <th className="procurement">商品编码</th>
                             <th className="procurement">商品名称</th>
                             <th className="procurement">通用名称</th>
                             <th className="number">规格</th>
                             <th className="dosage">剂型</th>
                             <th className="units">单位</th>
                             <th className="manufacturer">生产厂商</th>
                             <th className="approval">批准文号</th>
                             <th className="producing">产地</th>
                             <th className="batch-number">批号</th>
                             <th className="procurement">生产日期</th>
                             <th className="procurement">有效日期</th>
                             <th style={{width: "80px"}} className="number">收货数量</th>
                             <th style={{width: "80px"}} className="number">抽样数量</th>
                             <th className="procurement">验收合格数量</th>
                             <th style={{width: "80px"}} className="procurement">验收结论</th>
                             <th style={{width: "90px"}} className="procurement">验收不合格数量</th>
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
                                             <div className="td-cont" title={ct.purchaseOrderNum}>{ct.purchaseOrderNum}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.acceptanceOrderNum}>{ct.acceptanceOrderNum}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.acceptanceMan}>{ct.acceptanceMan}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.goodsCode}>{ct.goodsCode}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.goodsNm}>{ct.goodsNm}</div>
                                         </td>
                                         <td>
                                             <div className="td-cont" title={ct.goodsCommonNm}>{ct.goodsCommonNm}</div>
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
                                         <td><div className="td-cont" title={ct.goodsArrivalQuantity}>{ct.goodsArrivalQuantity}</div></td>
                                         <td><div className="td-cont" title={ct.sampleQuantity}>{ct.sampleQuantity}</div></td>
                                         <td><div className="td-cont" title={ct.qualifiedQuantity}>{ct.qualifiedQuantity}</div></td>
                                         <td><div className="td-cont" title={ct.acceptanceConclusion}>{ct.acceptanceConclusion}</div></td>
                                         <td><div className="td-cont" title={ct.goodsArrivalQuantity - ct.qualifiedQuantity}>{ct.goodsArrivalQuantity - ct.qualifiedQuantity}</div></td>
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