import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, validate} from "../../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';

export const fields = [
    {
        field:'dispensingManName',
        validate:{
            fieldNm: "调剂员",
            maxlength:32,
            required:true
        }
    },
    {
        field:'grantDrugManName',
        validate:{
            fieldNm: "发药人",
            maxlength:32,
            required:true
        }
    }
];

class PrescriptionDispensingForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    valid(data){
        let repeatApproveManId = $("#repeatApproveManId").val().trim();
        if(repeatApproveManId===''){
            this.props.errorValidMessageFunction("请进行复核");
            return;
        }

        this.props.errorValidMessageFunction("");

        const {actions} = this.props;
        const {store} = this.context;
        return actions.dispensingPrescription(data, actions, store.getState().prescriptionTodos.params);
    }

    /**
     * 审核回调
     * @param json
     */
    approveValidateCallBack(json){
        if(json!=undefined && json!=null){
            const {change} = this.props;
            $("#repeatApproveManId").val(json.id);
            change('repeatApproveManId', json.id);
            $("#realName").val(json.realName);
        }
    }

    render() {
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {display} = this.context.store.getState().approveValidateTodos;
        const data = store.getState().prescriptionTodos.data;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)}/>}
                <input type="hidden" name="id"/>
                <input type="hidden" name="repeatApproveManId" id="repeatApproveManId"/>
                <div className="layer">
                    <div className="layer-box layer-info layer-receiving layer-buying-out w960">
                        <div className="layer-header">
                            <span>处方调剂</span>
                            <a href="javascript:void(0);" className="close" onClick={() => this.props.actions.showDispensingForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">调剂信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="dispensingManName"  type="text" component={inputField} label="调剂员" required="required" maxLength="32"/>
                                    <div className="item">
                                        <p><i>*</i>复核员</p>
                                        <input type="text" readOnly onClick={()=>showValidateModel()} id="realName"/>
                                    </div>
                                    <Field name="grantDrugManName"  type="text" component={inputField} label="发药人" required="required" maxLength="32"/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">处方信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p>处方销售单号</p>
                                            <span>{data.prescriptionSellOrderCode}</span>
                                        </div>
                                        <div className="item">
                                            <p>处方日期</p>
                                            <span>{data.prescriptionDateString}</span>
                                        </div>
                                        <div className="item">
                                            <p>医疗机构</p>
                                            <span>{data.medicalOrg}</span>
                                        </div>
                                        <div className="item">
                                            <p>医师姓名</p>
                                            <span>{data.doctorName}</span>
                                        </div>
                                    </div>
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p>临床诊断</p>
                                            <span>{data.clinicDiagnosis}</span>
                                        </div>
                                        <div className="item">
                                            <p>医嘱</p>
                                            <span>{data.doctorsAdvice}</span>
                                        </div>
                                        <div className="item">
                                            <p>会员</p>
                                            <span>{data.memberCardNum}</span>
                                        </div>
                                        <div className="item">
                                            <p>患者名称</p>
                                            <span>{data.patientNm}</span>
                                        </div>
                                    </div>
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p>性别</p>
                                            <span>{data.sexTypeName}</span>
                                        </div>
                                        <div className="item">
                                            <p>年龄</p>
                                            <span>{data.age}</span>
                                        </div>
                                        <div className="item">
                                            <p>证件类型</p>
                                            <span>{data.certTypeName}</span>
                                        </div>
                                        <div className="item">
                                            <p>证件号码</p>
                                            <span>{data.certNum}</span>
                                        </div>
                                    </div>
                                    <div className="item-line-box">
                                        <div className="item">
                                            <p>手机</p>
                                            <span>{data.mobile}</span>
                                        </div>
                                        <div className="item">
                                            <p>地址</p>
                                            <span>{data.addr}</span>
                                        </div>
                                        <div className="item">
                                            <p>备注</p>
                                            <span>{data.remark}</span>
                                        </div>
                                        <div className="item">
                                            <p>创建用户名称</p>
                                            <span>{data.createBy}</span>
                                        </div>
                                    </div>
                                    <div className="item">
                                        <p>创建时间</p>
                                        <span>{data.createDateString}</span>
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
                                            data.itemList.map((item, index)=> {
                                                return (
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
                                                )
                                            })
                                        }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {checkValidForm(true)}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => this.props.actions.showDispensingForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

PrescriptionDispensingForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

PrescriptionDispensingForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        showValidateModel
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.prescriptionTodos.data,
        fields: fields,
        validate: validate,
        state
    }
}

PrescriptionDispensingForm = reduxForm({
    form: 'prescriptionDispensingForm'
})(PrescriptionDispensingForm);

PrescriptionDispensingForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(PrescriptionDispensingForm);

export default PrescriptionDispensingForm;
