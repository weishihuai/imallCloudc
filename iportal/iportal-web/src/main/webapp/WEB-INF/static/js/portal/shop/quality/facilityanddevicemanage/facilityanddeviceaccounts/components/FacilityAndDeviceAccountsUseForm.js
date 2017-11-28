import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, validate} from "../../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";

import {hiddenInputField} from './FieldComponents';

export const fields = [
    {
        field:'purposes',
        validate:{
            fieldNm: "使用目的",
            maxlength:32,
            required:true
        }
    },
    {
        field:'useDateString',
        validate:{
            fieldNm: "使用日期",
            required:true
        }
    },
    {
        field:'serviceCondition',
        validate:{
            fieldNm: "使用情况",
            maxlength:32
        }
    },
    {
        field:'operationMan',
        validate:{
            fieldNm: "操作人",
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

class FacilityAndDeviceAccountsUseForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

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

    valid(data){
        let stopTimeString = $.trim(data.stopTimeString);
        if(stopTimeString!==undefined && stopTimeString!==null && stopTimeString!==''){
            let stopTime = new Date(stopTimeString + ' 00:00:00');
            let useDate = new Date($.trim(data.useDateString) + ' 00:00:00');
            if(stopTime<useDate){
                this.props.errorValidMessageFunction("停止时间不能小于使用日期");
                return;
            }
        }

        this.props.errorValidMessageFunction("");
        const {actions} = this.props;
        const {store} = this.context;
        return this.props.actions.saveUseRecord(data, actions, store.getState().todos.params)
    }

    render() {
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {validFormState && errorValidMessage!=="" && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order w960">
                        <div className="layer-header">
                            <span>设施设备台账使用</span>
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
                                <div className="box-mt">使用信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="purposes"  type="text" component={inputField} label="使用目的" required="required" maxLength="32" />
                                    <Field name="useDateString" type="text" component={inputField} label="使用日期" inputClassName="datepicker" required="required" readOnly />
                                    <Field name="stopTimeString" type="text" component={inputField} label="停止时间" inputClassName="datepicker" readOnly />
                                    <Field name="serviceCondition"  type="text" component={inputField} label="使用情况" maxLength="32" />
                                    <Field name="operationMan"  type="text" component={inputField} label="操作人" maxLength="32" />
                                    <Field name="remark"  type="text" component={inputField} label="备注"  maxLength="32" value=""/>
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
        )
    }
}

FacilityAndDeviceAccountsUseForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    formType: PropTypes.string.isRequired,
    actions: PropTypes.object.isRequired
};

FacilityAndDeviceAccountsUseForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction
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

FacilityAndDeviceAccountsUseForm = reduxForm({
    form: 'facilityAndDeviceAccountsUseForm'
})(FacilityAndDeviceAccountsUseForm);

FacilityAndDeviceAccountsUseForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(FacilityAndDeviceAccountsUseForm);

export default FacilityAndDeviceAccountsUseForm;