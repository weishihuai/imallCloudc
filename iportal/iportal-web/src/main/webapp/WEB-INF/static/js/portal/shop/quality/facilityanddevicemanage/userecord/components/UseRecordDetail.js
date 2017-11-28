import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class UseRecordDetail extends Component{
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
                        <span>设施设备使用记录详情</span>
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
                            <div className="box-mt">使用记录信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>使用目的</p>
                                        <span>{record.purposes}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>使用日期</p>
                                        <span>{record.useDateString}</span>
                                    </div>
                                    <div className="item">
                                        <p>停止时间</p>
                                        <span>{record.stopTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>记录日期</p>
                                        <span>{record.recordDateString}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>使用情况</p>
                                        <span>{record.serviceCondition}</span>
                                    </div>
                                    <div className="item">
                                        <p>操作人</p>
                                        <span>{record.operationMan}</span>
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

UseRecordDetail.propTypes = {
    actions: PropTypes.object.isRequired
};

UseRecordDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

function mapStateToProps(state) {
    return {state}
}

UseRecordDetail = connect(
    mapDispatchToProps,
    mapStateToProps
)(UseRecordDetail);

export default UseRecordDetail;