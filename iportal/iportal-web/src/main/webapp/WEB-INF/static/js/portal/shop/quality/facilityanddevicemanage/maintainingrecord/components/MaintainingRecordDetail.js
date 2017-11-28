import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class MaintainingRecordDetail extends Component{
    constructor(props){
        super(props);
    }

    componentWillMount(){

    }

    componentDidUpdate(){

    }

    componentDidMount(){

    }

    render(){
        const {store} = this.context;
        const record = store.getState().todos.data;

        return(
            <div className="layer">
                <div className="layer-box layer-info layer-order w960">
                    <div className="layer-header">
                        <span>设施设备维护记录详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.hiddenDetailWin()}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">设备信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>设备编号</p>
                                        <span>{record.deviceNum}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>设备名称</p>
                                        <span>{record.deviceNm}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>设备类型</p>
                                        <span>{record.deviceTypeName}</span>
                                    </div>
                                    <div className="item">
                                        <p>型号</p>
                                        <span>{record.model}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>购置地点</p>
                                        <span>{record.purchasePlace}</span>
                                    </div>
                                    <div className="item">
                                        <p>启用时间</p>
                                        <span>{record.enableTimeString}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">维护记录信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>维护日期</p>
                                        <span>{record.maintainDateString}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>维护内容</p>
                                        <span>{record.maintainCont}</span>
                                    </div>
                                    <div className="item">
                                        <p>维护结果</p>
                                        <span>{record.maintainResult}</span>
                                    </div>
                                    <div className="item">
                                        <p>工作状况</p>
                                        <span>{record.workState}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>检修负责人</p>
                                        <span>{record.maintainResponseMan}</span>
                                    </div>
                                    <div className="item">
                                        <p>审批人</p>
                                        <span>{record.approverNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>备注</p>
                                        <span>{record.remark}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

MaintainingRecordDetail.propTypes = {
    actions: PropTypes.object.isRequired
};

MaintainingRecordDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

function mapStateToProps(state) {
    return {state}
}

MaintainingRecordDetail = connect(
    mapDispatchToProps,
    mapStateToProps
)(MaintainingRecordDetail);

export default MaintainingRecordDetail;