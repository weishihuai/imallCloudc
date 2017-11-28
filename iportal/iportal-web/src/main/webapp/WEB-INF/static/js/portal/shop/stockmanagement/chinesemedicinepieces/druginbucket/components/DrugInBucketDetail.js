import React, {Component, PropTypes} from "react";

/*药品装斗详情*/
class DrugInBucketDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.drugInBucketDetailData(this.context.store.getState().todos.id);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {store} = this.context;
        let drugInBucketDetailData = store.getState().todos.drugInBucketDetailData || {};

        return (
            <div className="layer">
                <div className="layer-box drug-check w1290">
                    <div className="layer-header">
                        <span>药品装斗</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.drugInBucketDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>装斗人</p>
                                    <span>{drugInBucketDetailData.inBucketManName}</span>
                                </div>
                                <div className="item">
                                    <p><i>*</i>装斗时间</p>
                                    <span>{drugInBucketDetailData.inBucketTimeString}</span>
                                </div>
                                <div className="item" style={{"clear": "both"}}>
                                    <p><i>*</i>审核人</p>
                                    <span>{drugInBucketDetailData.approveManNm}</span>
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
                                        <th className="th-ipt">装斗数量</th>
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
                                            <div className="td-cont">{drugInBucketDetailData.storageSpaceNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.quantity}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.isQualityQualified === "Y" ? "合格" : "不合格"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.goodsCode}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.goodsNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.commonNm}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.spec}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.dosageForm || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.unit}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.produceManufacturer}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.approvalNumber || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.productionPlace || "暂无"}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.batch}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.produceDateString}</div>
                                        </td>
                                        <td>
                                            <div className="td-cont">{drugInBucketDetailData.validDateString}</div>
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

DrugInBucketDetail.contextTypes = {
    store : React.PropTypes.object
};

export default DrugInBucketDetail