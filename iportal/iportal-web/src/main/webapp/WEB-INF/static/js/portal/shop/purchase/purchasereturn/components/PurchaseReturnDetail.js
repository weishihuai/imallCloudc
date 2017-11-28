import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class PurchaseReturnDetail extends Component {

    formatValue(value){
        return value ? value : '无';
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const data = store.getState().todos.detailData;
        return (
            <div className="layer">
                <div className="layer-box layer-info layer-receiving w1210">
                    <div className="layer-header">
                        <span>购进退出详情</span>
                        <a href="javascript:;" onClick={() => actions.showDetail()} className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>供货单位编码</p>
                                        <span>{data.supplierCode}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>供货单位名称</p>
                                        <span>{data.supplierNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>采购员</p>
                                        <span>{data.purchaseMan}</span>
                                    </div>
                                    <div className="item">
                                        <p>出库员</p>
                                        <span>{this.formatValue(data.outStorageMan)}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>退货类型</p>
                                        <span>{data.returnedPurchaseType == 'NORMAL_RETURNED' ? '正常退货' : '不合格退货'}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>退货原因</p>
                                        <span>{data.returnedPurchaseReason}</span>
                                    </div>
                                    <div className="item item-note">
                                        <p>备注</p>
                                        <span>{this.formatValue(data.remark)}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        <span>{data.approveMan}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table style={{width: "1600px"}}>
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="commodity-code">批号</th>
                                        <th className="commodity-code">生产日期</th>
                                        <th className="commodity-code">有效期至</th>
                                        <th className="number">退货数量</th>
                                        <th className="number">可退数量</th>
                                        <th className="price">单价（元）</th>
                                        <th className="price">金额（元）</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
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
                                                        <div className="td-cont">{item.goodsBatch}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.productionDateString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.validityString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.returnedPurchaseQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.returnableQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.purchaseUnitPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.amount}</div>
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
PurchaseReturnDetail.prorTypes = {
    actions: PropTypes.object.isRequired
}

PurchaseReturnDetail.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseReturnDetail);