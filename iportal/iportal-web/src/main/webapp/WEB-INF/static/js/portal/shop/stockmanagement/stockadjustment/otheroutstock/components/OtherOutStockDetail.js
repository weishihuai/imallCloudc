import React, {Component, PropTypes} from "react";

/*其他出库详情*/
class OtherOutStockDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.otherOutStockDetailData(this.context.store.getState().todos.outStockCode);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    typeCodeFormat(typeCode) {
        switch (typeCode) {
            case "REPORTED_LOSS":
                return "报损";
            case "INTERNAL_USE":
                return "内部领用";
            case "CHECK_OUT":
                return "抽检出库";
            case "OTHER":
                return "其他";
        }
    }

    render() {
        const {store} = this.context;
        let otherOutStockDetailData = store.getState().todos.otherOutStockDetailData || {};
        let otherOutStockGoodsVoList = otherOutStockDetailData.otherOutStockGoodsVoList || [];

        return (
            <div className="layer">
                <div className="layer-box drug-check w1290">
                    <div className="layer-header">
                        <span>其他出库详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.otherOutStockDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>出库人</p>
                                        <span>{otherOutStockDetailData.operatorName}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>出库时间</p>
                                        <span>{otherOutStockDetailData.outStockTimeTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>出库类型</p>
                                        <span>{this.typeCodeFormat(otherOutStockDetailData.typeCode)}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        <span>{otherOutStockDetailData.approveManName}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{otherOutStockDetailData.remark || "暂无"}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w1210">
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="number">出库数量</th>
                                        <th className="price">单价</th>
                                        <th className="price">金额</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="number">库存</th>
                                        <th className="cargo-location">货位</th>
                                        <th className="batch-number">批号</th>
                                        <th className="time">生产日期</th>
                                        <th className="time">有效期至</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {otherOutStockGoodsVoList.map((otherOutStockGoodsVo, index) => {
                                        return (<tr key={index}>
                                        <td>
                                            <div className="td-cont">{index + 1}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.quantity}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.unitPrice}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.outStockPrice}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.goodsCode}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.goodsCommonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.dosageForm || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.approvalNumber || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.currentStock}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.storageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.batch}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.produceDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherOutStockGoodsVo.validDateString}</div>
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
        )
    }
}

OtherOutStockDetail.contextTypes = {
    store : React.PropTypes.object
};

export default OtherOutStockDetail