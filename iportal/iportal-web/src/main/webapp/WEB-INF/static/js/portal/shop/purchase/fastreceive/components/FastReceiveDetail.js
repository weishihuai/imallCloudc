import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";

class FastReceiveDetail extends Component {

    formatValue(value){
        return value ? value : '无';
    }

    render() {
        const {store} = this.context;
        const data = store.getState().todos.detailData;
        return (
            <div className="layer">
                <div className="layer-box layer-info layer-order w1210">
                    <div className="layer-header">
                        <span>验收单</span>
                        <a href="javascript:;" onClick={() => this.props.actions.showDetail(null)} className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">订单信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
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
                                        <p>制单人员</p>
                                        <span>{data.docMaker}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>订单时间</p>
                                        <span>{data.orderCreateTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>收货人员</p>
                                        <span>{this.formatValue(data.receiver)}</span>
                                    </div>
                                    <div className="item">
                                        <p>收货时间</p>
                                        <span>{data.receiveTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>验收员</p>
                                        <span>{this.formatValue(data.acceptanceMan)}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>验收时间</p>
                                        <span>{data.acceptanceTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>合计总金额（元）</p>
                                        <span>{data.acceptanceTotalAmount}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc clearfix">
                                <table style={{width: "2100px"}}>
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th style={{width: "70px"}} className="serial-number">批号</th>
                                        <th className="date">生产日期</th>
                                        <th className="date">有效期至</th>
                                        <th className="number">到货数量</th>
                                        <th style={{width: "75px"}} className="price">进货单价（元）</th>
                                        <th style={{width: "55px"}} className="price">小计（元）</th>
                                        <th style={{width: "65px"}} className="price">零售价（元）</th>
                                        <th className="goods">货位</th>
                                        <th className="number">合格数量</th>
                                        <th className="number">拒收数量</th>
                                        <th className="number">入库数量</th>
                                        <th className="number">抽样数量</th>
                                        <th className="batch">灭菌批次</th>
                                        <th className="receiving-report">验收报告</th>
                                        <th style={{width: "90px"}} className="price">验收合格金额（元）</th>
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
                                            return(
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
                                                        <div className="td-cont">{item.goodsArrivalQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.purchaseUnitPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.purchaseUnitPrice*item.goodsArrivalQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.retailPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.storageSpaceNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.qualifiedQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.rejectionQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.inStorageQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.sampleQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.sterilizationBatch}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.acceptanceRep}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.acceptanceQualifiedAmount}</div>
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
                </div>
            </div>
        );
    }
}
FastReceiveDetail.propTypex = {
    actions: PropTypes.object.isRequired
}

FastReceiveDetail.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FastReceiveDetail);
