import React, {Component, PropTypes} from "react";

/*药品清斗详情*/
class DrugClearBucketDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.drugClearBucketDetailData(this.context.store.getState().todos.id);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    /*是否质量合格*/
    isQualityQualifiedFormat(isQualityQualified) {
        let isQualityQualifiedString = "不合格";
        if (isQualityQualified === "Y") {
            isQualityQualifiedString = "合格";
        }
        return isQualityQualifiedString;
    }

    render() {
        const {store} = this.context;
        let drugClearBucketDetailData = store.getState().todos.drugClearBucketDetailData;
        return (
            <div className="layer">
                <div className="layer-box drug-check w1290">
                    <div className="layer-header">
                        <span>药品清斗</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.drugClearBucketDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>清斗人</p>
                                    <span>{drugClearBucketDetailData.clearBucketManName}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>清斗时间</p>
                                    <span>{drugClearBucketDetailData.clearBucketTimeString}</span>
                                </div>
                                <div className="item" style={{"clear": "both"}}>
                                    <p><i>*</i>审核人</p>
                                    <span>{drugClearBucketDetailData.approveManNm}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc maxh407">
                                <table className="w1175">
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="cargo-location">货位</th>
                                        <th className="th-ipt">清斗数量</th>
                                        <th className="quality">质量情况</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="batch-number">批号</th>
                                        <th className="time">生产日期</th>
                                        <th className="time">有效期至</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>
                                            <div className="td-cont">1</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.storageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.quantity}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{this.isQualityQualifiedFormat(drugClearBucketDetailData.isQualityQualified)}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.goodsCode}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.commonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.dosageForm || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.approvalNumber || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.productionPlace || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.batch}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.produceDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugClearBucketDetailData.validDateString}</div>
                                        </td>
                                    </tr>
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

DrugClearBucketDetail.contextTypes = {
    store : React.PropTypes.object
};

export default DrugClearBucketDetail