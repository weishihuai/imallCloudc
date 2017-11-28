import React, {Component, PropTypes} from "react";


class ComplainRecordDetail extends Component {

    render() {
        const {store} = this.context;
        const detail = store.getState().todos.data;
        const {salesReturnRecordDetailModal} = this.props.actions;

        return (
            <div className="layer">
                <div className="layer-box layer-info layer-order layer-return w1075">
                    <div className="layer-header">
                        <span>销售退货单</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>salesReturnRecordDetailModal(null, false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">退货信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>退货单号</p>
                                        <span>{detail.sellReturnedPurchaseOrderNum}</span>
                                    </div>
                                    <div className="item">
                                        <p>退货时间</p>
                                        <span>{detail.createDate}</span>
                                    </div>
                                    <div className="item">
                                        <p>销售单号</p>
                                        <span>{detail.sellPurchaseOrderNum}</span>
                                    </div>
                                    <div className="item">
                                        <p>退货原因</p>
                                        <span>{detail.remark}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>营业员</p>
                                        <span>{detail.cashierNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>审核人</p>
                                        <span>{detail.approveManNm}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc receiving-box clearfix">
                                <div className="item item-text">退货总计：{detail.refundTotalAmount}</div>
                            </div>
                            <div className="box-mc clearfix">
                                <table className="w960">
                                    <thead>
                                        <tr>
                                            <th className="serial-number">序号</th>
                                            <th className="number">退货数量</th>
                                            <th className="price">退货单价</th>
                                            <th className="price">退货小计</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="commodity-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="origin">产地</th>
                                            <th className="batch">批号</th>
                                            <th className="date">有效期至</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        detail.itemList.map((item, index) => {
                                            return (
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont">{index+1}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.returnedPurchaseQuantity}>{item.returnedPurchaseQuantity}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.unitPrice}>{item.unitPrice}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.totalAmount}>{item.totalAmount}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.goodsCode}>{item.goodsCode}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.goodsNm}>{item.goodsNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.commonNm}>{item.commonNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.spec}>{item.spec}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.unit}>{item.unit}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.produceManufacturer}>{item.produceManufacturer}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.productionPlace}>{item.productionPlace}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.batch}>{item.batch}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={item.validDateString}>{item.validDateString}</div>
                                                    </td>
                                                </tr>
                                            )
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

ComplainRecordDetail.contextTypes = {
    store: React.PropTypes.object
};

export default ComplainRecordDetail