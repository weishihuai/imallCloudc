import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, selectField, validate} from "../../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction, asyncErrorValidMessageFunction} from "../../../../../../common/validForm/actions";
import {REGEXP_DOUBLE_2, REGEXP_INT} from "../../../../../../common/common-constant";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";

import {checkDeviceNum} from '../actions/index';
import {hiddenInputField} from './FieldComponents';

export const fields = [
    {
        field:'deviceTypeCode',
        validate:{
            fieldNm: "设备类型",
            maxlength:32,
            required:true
        }
    },
    {
        field:'deviceNum',
        validate:{
            fieldNm: "设备编号",
            maxlength:32,
            required:true
        }
    },
    {
        field:'deviceNm',
        validate:{
            fieldNm: "设备名称",
            maxlength:128,
            required:true
        }
    },
    {
        field:'model',
        validate:{
            fieldNm: "型号",
            maxlength:32
        }
    },
    {
        field:'produceManufacturer',
        validate:{
            fieldNm: "生产厂商",
            maxlength:64
        }
    },
    {
        field:'responseMan',
        validate:{
            fieldNm: "负责人",
            maxlength:32,
            required:true
        }
    },
    {
        field:'purchasePrice',
        validate:{
            fieldNm: "购置价格",
            regx: REGEXP_DOUBLE_2,
            maxlength:20
        }
    },
    {
        field:'purchasePlace',
        validate:{
            fieldNm: "购置地点",
            maxlength:128
        }
    },
    {
        field:'application',
        validate:{
            fieldNm: "用途",
            maxlength:128
        }
    },
    {
        field:'serviceLife',
        validate:{
            fieldNm: "使用年限",
            regx: REGEXP_INT,
            maxlength:10
        }
    },
    {
        field:'remark',
        validate:{
            fieldNm: "备注",
            maxlength:128
        }
    }
];

class FacilityAndDeviceAccountsEditForm extends Component{
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
        let purchaseDateString = $.trim(data.purchaseDateString);
        let enableTimeString = $.trim(data.enableTimeString);
        if(purchaseDateString!==undefined && purchaseDateString!==null && purchaseDateString!==''
            &&enableTimeString!==undefined && enableTimeString!==null && enableTimeString!==''){
            let purchaseDate = new Date(purchaseDateString + ' 00:00:00');
            let enableTime = new Date(enableTimeString + ' 00:00:00');
            if(enableTime<purchaseDate){
                this.props.errorValidMessageFunction("启用时间不能小于购买日期");
                return;
            }
        }

        this.props.errorValidMessageFunction("");
        const {actions} = this.props;
        const {store} = this.context;
        return this.props.actions.saveOrUpdate(data, actions, store.getState().todos.params)
    }

    render() {
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, asyncValidMessage, validFormState} = this.context.store.getState().validTodos;
        const deviceTypeCodeItems = [
            {code: 'TEMPERATURE_AND_HUMIDITY_TESTING', name: '温湿度检测设备'},
            {code: 'REFRIGERATION', name: '冷藏设备'},
            {code: 'CHINESE_HERBAL_PIECES_DISPENSING', name: '中药饮片调配设备'},
            {code: 'PHARMACEUTICAL_DISMANTLE', name: '药品拆零设备'},
            {code: 'SPECIAL_FOR_DRUG_MANAGEMENT', name: '特殊管理药品专用设备'},
            {code: 'LIGHT_AVOIDING', name: '避光设备'},
            {code: 'VENTILATION', name: '通风设备'},
            {code: 'FIRE_FIGHTING', name: '消防设备'},
            {code: 'LIGHTING', name: '照明设备'},
            {code: 'ACCEPTANCE_MAINTENANCE', name: '验收养护设备'}
        ];

        return (
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {validFormState && (errorValidMessage!=="" || asyncValidMessage!=="") && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order w960">
                        <div className="layer-header">
                            <span>编辑设施设备台账</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.showOrHidden(false, this.props.formType)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <Field name="id" type="text" component={hiddenInputField} />
                                    <Field name="deviceTypeCode"  type="text" component={selectField} label="设备类型" required="required" optionValue="code" optionName="name" items={deviceTypeCodeItems}/>
                                    <Field name="deviceNum"  type="text" component={inputField} label="设备编号" required="required" maxLength="32" />
                                    <Field name="deviceNm"  type="text" component={inputField} label="设备名称" required="required" maxLength="128" />
                                    <Field name="model"  type="text" component={inputField} label="型号" maxLength="32" />
                                    <Field name="produceManufacturer"  type="text" component={inputField} label="生产厂商" maxLength="64" />
                                    <Field name="responseMan"  type="text" component={inputField} label="负责人" maxLength="64" required="required" />
                                    <Field name="purchasePrice"  type="text" component={inputField} label="购置价格" maxLength="20"/>
                                    <Field name="purchaseDateString" type="text" component={inputField} label="购买日期" inputClassName="datepicker" readOnly />
                                    <Field name="enableTimeString" type="text" component={inputField} label="启用时间" inputClassName="datepicker" readOnly />
                                    <Field name="purchasePlace"  type="text" component={inputField} label="购置地点"  maxLength="128" />
                                    <Field name="application"  type="text" component={inputField} label="用途"  maxLength="128" />
                                    <Field name="serviceLife"  type="text" component={inputField} label="使用年限"  maxLength="10"/>
                                    <Field name="remark"  type="text" component={inputField} label="备注"  maxLength="128" />
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

FacilityAndDeviceAccountsEditForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    formType: PropTypes.string.isRequired,
    actions: PropTypes.object.isRequired
};

FacilityAndDeviceAccountsEditForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        asyncErrorValidMessageFunction
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        fields: fields,
        validate: validate,
        state,
        asyncBlurFields: ['deviceNum'],
        asyncValidate: checkDeviceNum
    }
}

FacilityAndDeviceAccountsEditForm = reduxForm({
    form: 'facilityAndDeviceAccountsEditForm'
})(FacilityAndDeviceAccountsEditForm);

FacilityAndDeviceAccountsEditForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(FacilityAndDeviceAccountsEditForm);

export default FacilityAndDeviceAccountsEditForm;