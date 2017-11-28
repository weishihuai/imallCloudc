import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class PurchaseReceiveDetail extends Component {

    formatValue(value){
        return value ? value : '无';
    }

    render() {
        const {store} = this.context;
        const data = store.getState().todos.receiveDetailData;
        return (
               <div className="layer">
                <div className="layer-box layer-info layer-receiving w1210">
                    <div className="layer-header">
                        <span>采购收货详细</span>
                        <a onClick={() => this.props.actions.showReceiveDetail(null)} href="javascript:;" className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">收货信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>收货人</p>
                                    <span>{this.formatValue(data.receiver)}</span>
                                </div>
                                <div className="item">
                                    <p>收货时间</p>
                                    <span>{this.formatValue(data.receiveTimeString)}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w1210">
                                    <thead>
                                    <tr>
                                        <th style={{width: "35px"}} className="serial-number">序号</th>
                                        <th className="number">收货数量</th>
                                        <th className="why">拒收原因</th>
                                        <th className="number">数量</th>
                                        <th className="price">采购单价（元）</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="price">零售价（元）</th>
                                        <th className="purchase-rate">采购税率</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                       data.itemList.map((item, index) => {
                                           return (
                                               <tr key={index}>
                                                   <td>
                                                       <div className="td-cont">{index + 1}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.receiveQuantity}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.rejectionReason}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.purchaseQuantity}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.purchaseUnitPrice}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.goodsCode}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.goodsNm}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.commonNm}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.spec}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.dosageForm}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.unit}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.produceManufacturer}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.approvalNumber}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.productionPlace}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.retailPrice}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont">{item.purchaseTaxRate}</div>
                                                   </td>
                                               </tr>
                                           );
                                       })
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
PurchaseReceiveDetail.propTypex = {
    actions: PropTypes.object.isRequired
}

PurchaseReceiveDetail.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseReceiveDetail);
