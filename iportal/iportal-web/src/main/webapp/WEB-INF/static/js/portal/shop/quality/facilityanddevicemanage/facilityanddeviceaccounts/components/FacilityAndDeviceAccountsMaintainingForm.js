import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, validate} from "../../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';

import {hiddenInputField} from './FieldComponents';

export const fields = [
    {
        field:'maintainDateString',
        validate:{
            fieldNm: "维护日期",
            required:true
        }
    },
    {
        field:'maintainCont',
        validate:{
            fieldNm: "维护内容",
            maxlength:32,
            required:true
        }
    },
    {
        field:'maintainResult',
        validate:{
            fieldNm: "维护结果",
            maxlength:32
        }
    },
    {
        field:'workState',
        validate:{
            fieldNm: "工作状况",
            maxlength:32
        }
    },
    {
        field:'maintainResponseMan',
        validate:{
            fieldNm: "检修负责人",
            maxlength:32
        }
    },
    {
        field:'approverNm',
        validate:{
            fieldNm: "审批人名称",
            maxlength:32
        }
    },
    {
        field:'remark',
        validate:{
            fieldNm: "备注",
            maxlength:32
        }
    }
];

class FacilityAndDeviceAccountsMaintainingForm extends Component{
    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    let target = e.target.name;
                    change(target, $($("input[name=" + target + "]")[0]).val().trim());
                }
            });
        });
    }

    /**
     * 审核回调
     * @param json
     */
    approveValidateCallBack(json){
        if(json!==undefined && json!==null){
            const {change} = this.props;
            $("#approveManId").val(json.id);
            change('approveManId', json.id);
            $("#reviewBtn").hide();
            $("#realName").text(json.realName);
            $("#realName").show();
        }
    }

    valid(data){
        let approveManId = $.trim(data.approveManId);
        if(approveManId===undefined || approveManId===null || approveManId===''){
            this.props.errorValidMessageFunction("请进行审核");
            return;
        }

        this.props.errorValidMessageFunction("");
        const {actions} = this.props;
        const {store} = this.context;
        return actions.saveMaintainingRecord(data, actions, store.getState().todos.params);
    }

    render(){
        const {handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {display} = this.context.store.getState().approveValidateTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {validFormState && errorValidMessage!=="" && <ValidForm  checkValidForm={checkValidForm}/>}
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order w960">
                        <div className="layer-header">
                            <span>设施设备台账维护</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.showOrHidden(false, this.props.formType)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">设备信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="facilityAndDeviceAccountsId"  type="text" component={hiddenInputField} readOnly/>
                                    <Field name="deviceTypeName"  type="text" component={inputField} label="设备类型" required="required" readOnly/>
                                    <Field name="deviceNum"  type="text" component={inputField} label="设备编号" required="required" maxLength="32" readOnly/>
                                    <Field name="deviceNm"  type="text" component={inputField} label="设备名称" required="required" maxLength="32" readOnly/>
                                    <Field name="model"  type="text" component={inputField} label="型号" maxLength="32" readOnly/>
                                    <Field name="enableTimeString" type="text" component={inputField} label="启用时间" readOnly />
                                    <Field name="purchasePlace"  type="text" component={inputField} label="购置地点"  maxLength="32" readOnly/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">维护信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="maintainDateString" type="text" component={inputField} label="维护日期" inputClassName="datepicker" required="required" readOnly />
                                    <Field name="maintainCont"  type="text" component={inputField} label="维护内容" required="required" maxLength="32" />
                                    <Field name="maintainResult"  type="text" component={inputField} label="维护结果" maxLength="32" />
                                    <Field name="workState"  type="text" component={inputField} label="工作状况" maxLength="32" />
                                    <Field name="maintainResponseMan"  type="text" component={inputField} label="检修负责人" maxLength="32" />
                                    <Field name="approverNm"  type="text" component={inputField} label="审批人名称" maxLength="32" />
                                    <Field name="remark"  type="text" component={inputField} label="备注" maxLength="32" />
                                    <Field name="approveManId" id="approveManId"  type="text" component={hiddenInputField}/>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        <a href="javascript:void(0);" className="review" onClick={()=>showValidateModel()} id="reviewBtn">点击审核</a>
                                        <p id="realName" style={{display: 'none'}}/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {this.props.checkValidForm(true)}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.showOrHidden(false, this.props.formType)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}


FacilityAndDeviceAccountsMaintainingForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    formType: PropTypes.string.isRequired,
    actions: PropTypes.object.isRequired
};

FacilityAndDeviceAccountsMaintainingForm.contextTypes = {
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
        initialValues: state.todos.data,
        fields: fields,
        validate: validate,
        state
    }
}

FacilityAndDeviceAccountsMaintainingForm = reduxForm({
    form: 'facilityAndDeviceAccountsMaintainingForm'
})(FacilityAndDeviceAccountsMaintainingForm);

FacilityAndDeviceAccountsMaintainingForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(FacilityAndDeviceAccountsMaintainingForm);

export default FacilityAndDeviceAccountsMaintainingForm;