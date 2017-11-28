import React, {Component, PropTypes} from "react";

/*检测记录详情*/
class MeasureRecordDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.measureRecordDetailData(this.context.store.getState().todos.id);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {store} = this.context;
        let measureRecordDetailData = store.getState().todos.measureRecordDetailData || {};

        return (
            <div className="layer">
                <div className="layer-box layer-info layer-order w960">
                    <div className="layer-header">
                        <span>检查记录详情</span>
                        <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.measureRecordDetailModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">计量器具信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>计量器具编号</p>
                                        <span>{measureRecordDetailData.measuringDeviceNum}</span>
                                    </div>
                                    <div className="item">
                                        <p>出厂编号</p>
                                        <span>{measureRecordDetailData.manufacturingNum || "暂无"}</span>
                                    </div>
                                    <div className="item" style={{width:"226px"}}>
                                        <p><i>*</i>名称</p>
                                        <span>{measureRecordDetailData.deviceNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>型号</p>
                                        <span>{measureRecordDetailData.model || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>生产厂商</p>
                                        <span>{measureRecordDetailData.produceManufacturer || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>分类编号</p>
                                        <span>{measureRecordDetailData.categoryNum || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>测量范围</p>
                                        <span>{measureRecordDetailData.measureRange || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>精度等级</p>
                                        <span>{measureRecordDetailData.precisionLevel || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>负责人</p>
                                        <span>{measureRecordDetailData.responseMan}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>检测周期（天）</p>
                                        <span>{measureRecordDetailData.measurePeriod}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">检查记录信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>检测日期</p>
                                        <span>{measureRecordDetailData.measureDateString}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>开始时间</p>
                                        <span>{measureRecordDetailData.startTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>结束时间</p>
                                        <span>{measureRecordDetailData.endTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>鉴定项目</p>
                                        <span>{measureRecordDetailData.identifyPrj || "暂无"}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>技术要求</p>
                                        <span>{measureRecordDetailData.skillRequirement || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>检测方法</p>
                                        <span>{measureRecordDetailData.measureMethod || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p>鉴定结论</p>
                                        <span>{measureRecordDetailData.identifyConclusion || "暂无"}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>检测人</p>
                                        <span>{measureRecordDetailData.measureMan}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>复检人</p>
                                        <span>{measureRecordDetailData.reviewMan}</span>
                                    </div>
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{measureRecordDetailData.remark || "暂无"}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

MeasureRecordDetail.contextTypes = {
    store : React.PropTypes.object
};

export default MeasureRecordDetail