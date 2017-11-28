import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";

class LimitBuyRegisterDetail extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {store} = this.props;
        const record = store.getState().todos.record;

        return(
            <div className="layer">
                <div className="layer-box layer-info layer-receiving layer-buying-out w960">
                    <div className="layer-header">
                        <span>限购登记详情</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.showDetail(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">订单信息</div>
                            <div className="box-mc clearfix">
                                <div className="item-line-box">
                                    <div className="item">
                                        <p><i>*</i>销售订单编号</p>
                                        <span>{record.sellOrderCode}</span>
                                    </div>
                                    <div className="item">
                                        <p>会员卡号码</p>
                                        <span>{record.memberCardNum}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>患者名称</p>
                                        <span>{record.patientNm}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>身份证</p>
                                        <span>{record.identityCard}</span>
                                    </div>
                                </div>
                                <div className="item-line-box">
                                    <div className="item">
                                        <p>性别</p>
                                        <span>{record.sexName}</span>
                                    </div>
                                    <div className="item">
                                        <p>手机</p>
                                        <span>{record.mobile}</span>
                                    </div>
                                    <div className="item">
                                        <p>地址</p>
                                        <span>{record.addr}</span>
                                    </div>
                                    <div className="item">
                                        <p>登记日期</p>
                                        <span>{record.registerDateString}</span>
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
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <table className="w860">
                                    <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="number">数量</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {
                                        record.limitBuyRegisterItemVoList.map((item, index)=>{
                                            return(
                                                <tr key={index}>
                                                    <td>
                                                        <div className="td-cont">{index + 1}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{item.goodsCoding}</div>
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
                                                    <td>
                                                        <div className="td-cont">{item.quantity}</div>
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

LimitBuyRegisterDetail.propTypes = {

};

LimitBuyRegisterDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        state
    }
}

LimitBuyRegisterDetail = reduxForm({
    form: 'limitBuyRegisterDetail',
    enableReinitialize: true
})(LimitBuyRegisterDetail);

LimitBuyRegisterDetail = connect(
    mapStateToProps,
    mapDispatchToProps
)(LimitBuyRegisterDetail);



export default LimitBuyRegisterDetail;