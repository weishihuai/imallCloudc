import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {showDetail, purchaseOrderDetail} from '../actions'

class PurchaseOrderDetailComponents extends Component {

    componentWillMount() {
        const {store} = this.context;
        this.props.purchaseOrderDetail(store.getState().purchaseOrderDetailTodos.id);
    }

    formatValue(value){
        return value ? value : '无';
    }

    closeDetail(){
        this.props.showDetail(null);
    }

    render() {
        const {store} = this.context;
        const data = store.getState().purchaseOrderDetailTodos.data;
        return (
            <div className="layer">
                <div className="layer-box layer-info layer-receiving w1290">
                    <div className="layer-header">
                        <span>采购订单详情</span>
                        <a href="javascript:;" onClick={() => this.closeDetail()} className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>采购人</p>
                                    <span>{this.formatValue(data.purchaseMan)}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>供货单位编码</p>
                                    <span>{data.supplierCode}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>供货单位名称</p>
                                    <span>{data.supplierNm}</span>
                                </div>
                                <div className="item">
                                    <p>销售人员</p>
                                    <span>{this.formatValue(data.sellMan)}</span>
                                </div>
                                <div className="item">
                                    <p>联系电话</p>
                                    <span>{this.formatValue(data.contactTel)}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">其他信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p><i>*</i>预计到货时间</p>
                                    <span>{data.expectedArrivalTimeString}</span>
                                </div>
                                <div className="item">
                                    <p>采购总金额（元）</p>
                                    <span>{data.purchaseTotalAmount}</span>
                                </div>
                                <div className="item">
                                    <p>备注</p>
                                    <span>{this.formatValue(data.remark)}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w980">
                                    <thead>
                                    <tr>
                                        <th style={{width: "35px"}} className="serial-number">序号</th>
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
                                        data.orderItemVoList.map((item, index) => {
                                            return (
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont">{index + 1}</div>
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
                                                        <div className="td-cont">{item.purchaseTaxRate!=null?item.purchaseTaxRate+"%":""}</div>
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
                    <div className="layer-footer">
                    </div>
                </div>
            </div>
        );
    }
}
PurchaseOrderDetailComponents.prorTypes = {
    actions: PropTypes.object.isRequired
}

PurchaseOrderDetailComponents.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({showDetail, purchaseOrderDetail}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseOrderDetailComponents);