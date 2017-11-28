import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';

export const validate = (values, props) => {
    const errors = {};
    const fields = props.fields;
    for (const i in fields) {
        const fieldObj = fields[i];
        const field = fieldObj.field;
        const validate = fieldObj.validate;
        const fieldNm = validate.fieldNm;

        //至少审核一项 否则提交无意义
        if((!values["purchaseApproveManId"])&&(!values["purchaseApproveStateCode"])&&(!values["qualityApproveManId"])&&(!values["qualityApproveStateCode"])&&(!values["enterpriseApproveManId"])&&(!values["enterpriseApproveManId"])){
            errors[field] = "请至少审核一项";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        //采购的非必填项有值时  必须完善采购审核的必填信息 保持一个整体
        if((values["purchaseApproveTimeStr"]||values["purchaseApproveRemark"])&&(!values["purchaseApproveManId"])){
            errors[field] = "请点击采购审核";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if((values["purchaseApproveTimeStr"]||values["purchaseApproveRemark"])&&(!values["purchaseApproveStateCode"])){
            errors[field] = "请选择采购审核状态";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        //采购审核状态与采购审核人必须保持都填或者都不填
        if(values["purchaseApproveStateCode"] && (!values["purchaseApproveManId"])){
            errors[field] = "请点击采购审核";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if(values["purchaseApproveManId"]&& (!values["purchaseApproveStateCode"])){
            errors[field] = "请选择采购审核状态";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }


        //以下情况与上面采购雷同 不做注释
        if((values["qualityApproveTimeStr"]||values["qualityApproveRemark"])&&(!values["qualityApproveManId"])){
            errors[field] = "请点击质量审核";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if((values["qualityApproveTimeStr"]||values["qualityApproveRemark"])&&(!values["qualityApproveStateCode"])){
            errors[field] = "请选择质量审核状态";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        if(values["qualityApproveStateCode"]&& (!values["qualityApproveManId"])){
            errors[field] = "请点击质量审核";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if(values["qualityApproveManId"]&& (!values["qualityApproveStateCode"])){
            errors[field] = "请选择质量审核状态";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }


        if((values["enterpriseApproveTimeStr"]||values["enterpriseApproveRemark"])&&(!values["enterpriseApproveManId"])){
            errors[field] = "请点击企业审核";
            props.errorValidMessageFunction( errors[field]);
            return errors;
        }
        if((values["enterpriseApproveTimeStr"]||values["enterpriseApproveRemark"])&&(!values["enterpriseApproveStateCode"])){
            errors[field] = "请选择企业审核状态";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        if(values["enterpriseApproveStateCode"]&& (!values["enterpriseApproveManId"])){
            errors[field] = "请点击企业审核";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if(values["enterpriseApproveManId"]&& (!values["enterpriseApproveStateCode"])){
            errors[field] = "请选择企业审核状态";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        const minlength = validate.minlength;
        const maxlength = validate.maxlength;
        if (values[field] != undefined && minlength != undefined && values[field].length < minlength) {
            errors[field] = fieldNm + "不能少于" + minlength + "个字符";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if (values[field] != undefined && maxlength != undefined && values[field].length > maxlength) {
            errors[field] = fieldNm + "不能大于" + maxlength + "个字符";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors
};

export const fields = [
    {
        field:'firstManageDrugQualityApproveId',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'purchaseApproveManId',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'purchaseApproveTimeStr',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'purchaseApproveStateCode',
        validate:{
            fieldNm: "采购审核状态",
            maxlength:32,
        }
    },
    {
        field:'purchaseApproveRemark',
        validate:{
            fieldNm: "采购审核备注",
            maxlength:128,
        }
    },
    {
        field:'qualityApproveManId',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'qualityApproveTimeStr',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'qualityApproveStateCode',
        validate:{
            fieldNm: "质量审核状态",
            maxlength:32,
        }
    },
    {
        field:'qualityApproveRemark',
        validate:{
            fieldNm: "质量审核备注",
            maxlength:128,
        }
    },
    {
        field:'enterpriseApproveManId',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'enterpriseApproveTimeStr',
        validate:{
            fieldNm: "",
        }
    },
    {
        field:'enterpriseApproveStateCode',
        validate:{
            fieldNm: "企业审核状态",
            maxlength:128,
        }
    },
    {
        field:'enterpriseApproveRemark',
        validate:{
            fieldNm: "企业审核备注",
            maxlength:128,
        }
    },
];


export const simpleInput = ({ input, type, id, display,disabled,readOnly,meta: { touched, error } }) => (
    <input type={type} id={id} disabled={disabled} style={{display:display?"none":"block"}} name={input.name} readOnly={readOnly} {...input}/>
);

export const approveStateField = ({ input, id,className,disabled,meta: { touched, error } }) => (
    <select  id={id} name={input.name} className={className} disabled={disabled} {...input}>
        <option value="">请选择</option>
        <option value="PASS_APPROVE">已审核</option>
        <option value="REJECTED">已驳回</option>
    </select>
);

class FirstManageDrugApproveForm extends Component{
    constructor(props) {
        super(props);
    }

    submit(data){
        const {params,page,approveInf} = this.context.store.getState().todos;
        if(approveInf.purchaseApproveStateCode && approveInf.purchaseApproveManId){
            data = Object.assign({},data,{
                purchaseApproveStateCode:"",
                purchaseApproveManId:""
            });
        }

        if(approveInf.qualityApproveStateCode && approveInf.qualityApproveManId){
            data = Object.assign({},data,{
                qualityApproveStateCode:"",
                qualityApproveManId:""
            });
        }

        if(approveInf.enterpriseApproveStateCode && approveInf.enterpriseApproveManId){
            data = Object.assign({},data,{
                enterpriseApproveStateCode:"",
                enterpriseApproveManId:""
            });
        }
        this.props.actions.approveFirstManageDrugQualityInf(data,params,page);
    }

    componentDidMount() {
        const {change} = this.props;

        $("#purchaseApproveTimeStr").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("purchaseApproveTimeStr",$("#purchaseApproveTimeStr").val());
                }
            });
        });

        $("#qualityApproveTimeStr").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("qualityApproveTimeStr",$("#qualityApproveTimeStr").val());
                }
            });
        });

        $("#enterpriseApproveTimeStr").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("enterpriseApproveTimeStr",$("#enterpriseApproveTimeStr").val());
                }
            });
        });
    }

    /**
     * 审核回调
     * @param json
     */
    approveValidateCallBack(json){
        if(json!=undefined && json!=null){
            const {change} = this.props;
            if(json.approveType && json.approveType=="purchase"){
                change('purchaseApproveManId', json.id);
                $("#purchaseReviewBtn").hide();
                $("#purchaseRealName").text(json.realName);
                $("#purchaseRealName").show();
            }
            if(json.approveType && json.approveType=="quality"){
                change('qualityApproveManId', json.id);
                $("#qualityReviewBtn").hide();
                $("#qualityRealName").text(json.realName);
                $("#qualityRealName").show();
            }
            if(json.approveType && json.approveType=="enterprise"){
                change('enterpriseApproveManId', json.id);
                $("#enterpriseReviewBtn").hide();
                $("#enterpriseRealName").text(json.realName);
                $("#enterpriseRealName").show();
            }
        }
    }

    render() {
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {display} = this.context.store.getState().approveValidateTodos;
        const {approveInf} = store.getState().todos;
        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)} />}
                <Field name="purchaseApproveManId" display="none" component={simpleInput} id="purchaseApproveManId"/>
                <Field name="qualityApproveManId" display="none" component={simpleInput} id="qualityApproveManId"/>
                <Field name="enterpriseApproveManId" display="none" component={simpleInput} id="enterpriseApproveManId"/>
                <Field name="firstManageDrugQualityApproveId" display="none" component={simpleInput} id="firstManageDrugQualityApproveId"/>
                <div className="layer" >
                    <div className="layer-box layer-info layer-order w1175">
                        <div className="layer-header">
                            <span>审核</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.changeFirstManageDrugQualityApproveFormState(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <div className="item" style={{'clear':'both'}}>
                                        <p><i>*</i>采购审核人</p>
                                        {!approveInf.purchaseApproveStateCode && <a href="javascript:void(0);" className="review" onClick={()=>showValidateModel("purchase")} id="purchaseReviewBtn">点击审核</a>}
                                        <p id="purchaseRealName" style={{display:approveInf.purchaseApproveStateCode?"block":'none',color:"red"}}>{approveInf.purchaseApproveManName}</p>
                                    </div>
                                    <div className="item">
                                        <p>采购审核时间</p>
                                        <Field name="purchaseApproveTimeStr" type="text" readOnly="readOnly" disabled={approveInf.purchaseApproveStateCode?"disabled":""} component={simpleInput} id="purchaseApproveTimeStr"/>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>采购审核状态</p>
                                        <Field id="purchaseApproveStateCode" name="purchaseApproveStateCode" disabled={approveInf.purchaseApproveStateCode?"disabled":""} className="select-w" component={approveStateField}/>
                                    </div>
                                    <div className="item item-note">
                                        <p>采购申请备注</p>
                                        <Field name="purchaseApproveRemark" type="text" disabled={approveInf.purchaseApproveStateCode?"disabled":""} component={simpleInput} id="purchaseApproveRemark"/>
                                    </div>
                                    <div className="item" style={{'clear':'both'}}>
                                        <p><i>*</i>质量审核人</p>
                                        {!approveInf.qualityApproveStateCode && <a href="javascript:void(0);" className="review" onClick={()=>showValidateModel("quality")} id="qualityReviewBtn">点击审核</a>}
                                        <p id="qualityRealName" style={{display:approveInf.qualityApproveStateCode?"block":'none',color:"red"}}>{approveInf.qualityApproveManName}</p>
                                    </div>
                                    <div className="item">
                                        <p>质量审核时间</p>
                                        <Field name="qualityApproveTimeStr" type="text" disabled={approveInf.qualityApproveStateCode?"disabled":""} readOnly="readOnly" component={simpleInput} id="qualityApproveTimeStr"/>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>质量审核状态</p>
                                        <Field id="qualityApproveStateCode" disabled={approveInf.qualityApproveStateCode?"disabled":""} name="qualityApproveStateCode" className="select-w" component={approveStateField}/>
                                    </div>
                                    <div className="item item-note">
                                        <p>质量申请备注</p>
                                        <Field name="qualityApproveRemark" type="text" disabled={approveInf.qualityApproveStateCode?"disabled":""} component={simpleInput} id="qualityApproveRemark"/>
                                    </div>
                                    <div className="item" style={{'clear':'both'}}>
                                        <p><i>*</i>企业审核人</p>
                                        {!approveInf.enterpriseApproveStateCode && <a href="javascript:void(0);" className="review" onClick={()=>showValidateModel("enterprise")} id="enterpriseReviewBtn">点击审核</a>}
                                        <p id="enterpriseRealName" style={{display:approveInf.enterpriseApproveStateCode?"block":'none',color:"red"}}>{approveInf.enterpriseApproveManName}</p>
                                    </div>
                                    <div className="item">
                                        <p>企业审核时间</p>
                                        <Field name="enterpriseApproveTimeStr" type="text" readOnly="readOnly" disabled={approveInf.enterpriseApproveStateCode?"disabled":""} component={simpleInput} id="enterpriseApproveTimeStr"/>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>企业审核状态</p>
                                        <Field id="enterpriseApproveStateCode" name="enterpriseApproveStateCode" disabled={approveInf.enterpriseApproveStateCode?"disabled":""} className="select-w" component={approveStateField}/>
                                    </div>
                                    <div className="item item-note">
                                        <p>企业申请备注</p>
                                        <Field name="enterpriseApproveRemark" type="text" component={simpleInput} disabled={approveInf.enterpriseApproveStateCode?"disabled":""} id="enterpriseApproveRemark"/>
                                    </div>
                                </div>
                                <div className="layer-footer">
                                    <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} type="submit" onClick={() => {checkValidForm(true)}}>保存</button>
                                    <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.changeFirstManageDrugQualityApproveFormState(false)}>取消</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

FirstManageDrugApproveForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

FirstManageDrugApproveForm.contextTypes = {
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
        initialValues: state.todos.approveInf,
        fields: fields,
        state
    }
}

FirstManageDrugApproveForm = reduxForm({
    form: 'firstManageDrugApproveForm',
    validate:validate
})(FirstManageDrugApproveForm);

FirstManageDrugApproveForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(FirstManageDrugApproveForm);

export default FirstManageDrugApproveForm;
