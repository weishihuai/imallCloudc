import React, {Component} from "react";

/*其他入库详情*/
class OtherInStockDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.otherInStockDetailData(this.context.store.getState().todos.inStockCode);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    typeCodeFormat(typeCode) {
        switch (typeCode) {
            case "RECEIVE":
                return "获赠";
            case "OVERFLOW":
                return "报溢";
            case "TAKE_BACK":
                return "领用退回";
            case "OTHER":
                return "其他";
        }
    }

    render() {
        const {store} = this.context;
        let otherInStockDetailData = store.getState().todos.otherInStockDetailData || {};
        let otherInStockGoodsVoList = otherInStockDetailData.otherInStockGoodsVoList || [];

        return (
            <div className="layer">
                <div className="layer-box drug-check w1290">
                    <div className="layer-header">
                        <span>其他入库详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.otherInStockDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>入库人</p>
                                        <span>{otherInStockDetailData.operatorName}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>入库时间</p>
                                        <span>{otherInStockDetailData.inStockTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>入库类型</p>
                                        <span>{this.typeCodeFormat(otherInStockDetailData.typeCode)}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        <span>{otherInStockDetailData.approveManName}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>供应商名称</p>
                                        <span>{otherInStockDetailData.supplierNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{otherInStockDetailData.remark || "暂无"}</span>
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
                                        <th className="number">入库数量</th>
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
                                    {otherInStockGoodsVoList.map((otherInStockGoodsVo, index) => {
                                        return (<tr key={index}>
                                        <td>
                                            <div className="td-cont">{index + 1}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.quantity}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.unitPrice}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.inStockPrice}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.goodsCode}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.goodsCommonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.dosageForm || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.approvalNumber || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.currentStock}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.storageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.batch}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.produceDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{otherInStockGoodsVo.validDateString}</div>
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

OtherInStockDetail.contextTypes = {
    store : React.PropTypes.object
};

export default OtherInStockDetail