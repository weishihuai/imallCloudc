import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

class FacilityAndDeviceAccountsDetail extends Component{
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
                <div className="layer-box layer-info layer-order layer-adjust01 w960">
                    <div className="layer-header">
                        <span>设施设备台账详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.showOrHidden(false, this.props.formType)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>设备类型</p>
                                        <span>{record.deviceTypeName}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>设备编号</p>
                                        <span>{record.deviceNum}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>设备名称</p>
                                        <span>{record.deviceNm}</span>
                                    </div>
                                    <div className="item">
                                        <p>型号</p>
                                        <span>{record.model}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>生产厂商</p>
                                        <span>{record.produceManufacturer}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>负责人</p>
                                        <span>{record.responseMan}</span>
                                    </div>
                                    <div className="item">
                                        <p>购置价格</p>
                                        <span>{record.purchasePrice}</span>
                                    </div>
                                    <div className="item">
                                        <p>购买日期</p>
                                        <span>{record.purchaseDateString}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>启用时间</p>
                                        <span>{record.enableTimeString}</span>
                                    </div>
                                    <div className="item">
                                        <p>购置地点</p>
                                        <span>{record.purchasePlace}</span>
                                    </div>
                                    <div className="item">
                                        <p>用途</p>
                                        <span>{record.application}</span>
                                    </div>
                                    <div className="item">
                                        <p>使用年限</p>
                                        <span>{record.serviceLife}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
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

FacilityAndDeviceAccountsDetail.propTypes = {
    formType: PropTypes.string.isRequired,
    actions: PropTypes.object.isRequired
};

FacilityAndDeviceAccountsDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({

    }, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

FacilityAndDeviceAccountsDetail = connect(
    mapDispatchToProps,
    mapStateToProps
)(FacilityAndDeviceAccountsDetail);

export default FacilityAndDeviceAccountsDetail;