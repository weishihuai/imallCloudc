/**
 * Created by ou on 2017/7/11.
 */

import React, {Component, PropTypes} from "react";
import {reduxForm, Field} from 'redux-form';
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux';
import {validate,inputField,selectField} from '../../../../../../common/redux-form-ext'
import {showValidateModel} from '../../../../../../common/approvevalidate/actions'
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {initValidForm, errorValidMessageFunction,checkValidForm} from '../../../../../../common/validForm/actions'
import ValidForm from '../../../../../../common/validForm/components/ValidForm';
export const fields = [

   ];

//采购审核状态
const firstApproveTypeCodeEnumItems=[
    {
        name:"已审核",
        code:"PASS_APPROVE"
    },
    {
        name:"已驳回",
        code:"REJECTED"
    }
];

export const hiddenField = ({type,input}) => (
    <input name={input.name} type={type} style={{display:"none"}} {...input} />
);

class SupplierQualityApproveForm extends React.Component {

    //设置审核对象
    approveValidateCallBack(data){
        const {approveUserNames} = this.context.store.getState().todos;
        const {setApproNames} = this.props.actions;
        if (data.approveType && data.approveType === "purchase") {
            fields.push(
                {
                    field: 'purchaseApproveState',
                    validate: {
                        fieldNm: "采购审核状态",
                        required:true
                    }
                });
            this.props.change("purchaseApproveManId", data.id);
            setApproNames(Object.assign({},approveUserNames,{
                purchaseApproveMan:data.realName
                }
            ))
        }
        if (data.approveType && data.approveType === "quality") {
            fields.push(
                {
                    field: 'qualityApproveState',
                    validate: {
                        fieldNm: "质量审核状态",
                        required:true
                    }
                });
            this.props.change("qualityApproveManId", data.id);
            setApproNames(Object.assign({},approveUserNames,{
                qualityApproveMan:data.realName
                }
            ))
        }
        if (data.approveType && data.approveType === "ent") {
            fields.push(
                {
                    field: 'entApproveState',
                    validate: {
                        fieldNm: "企业审核状态",
                        required:true
                    }
                });
            this.props.change("entApproveManId", data.id);
            setApproNames(Object.assign({},approveUserNames,{
                entApproveMan:data.realName
                }
            ))
        }
    }
    //获取时间
    componentDidMount() {
        const change = this.props.change;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            let name = this.name;
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                   switch(name){
                       case"purchaseApproveTimeString":
                           change("purchaseApproveTimeString", $('input[name="purchaseApproveTimeString"]').val());
                           break;
                       case "qualityApproveTimeString":
                           change("qualityApproveTimeString",  $('input[name="qualityApproveTimeString"]').val());
                           break;
                       case "entApproveTimeString":
                           change("entApproveTimeString",  $('input[name="entApproveTimeString"]').val());
                           break
                   }

                }
            });
        });
    }
    //提交校验和处理检验
    submitDataAndIniTData(){
        this.props.checkValidForm(true);

    }
    //关闭处理数据和清除校验
    closeAddForm(){
        this.props.showApproveFormState();
        this.props.actions.showApproveFormState({});
        this.props.reset();
        this.props.initValidForm();
        this.props.actions.initValue();
    }
    render() {
        const {showValidateModel,handleSubmit, submitting} = this.props;
        const showApproveFormState = this.props.showApproveFormState;
        const {display} = this.context.store.getState().approveValidateTodos;
        const {approveFormStateObject} = this.context.store.getState().todos;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {approveUserNames} = this.context.store.getState().todos;
        return (
            <form onSubmit={handleSubmit} >
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)}/>}
                {validFormState && errorValidMessage != "" && <ValidForm />}
                <div className="layer">
                    <div className="layer-box layer-info layer-order w1175">
                        <div className="layer-header">
                            <span>审核</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => { this.closeAddForm() }} ></a>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p><i>*</i>采购审核人</p>
                                        {approveUserNames.purchaseApproveMan != undefined||approveFormStateObject.purchaseApproveMan!= null ? <p className="purchaseApproveMan" style={{color: "red"}}>{approveFormStateObject.purchaseApproveMan!=null?approveFormStateObject.purchaseApproveMan:approveUserNames.purchaseApproveMan}</p> : <button onClick={(e)=>{e.preventDefault();showValidateModel("purchase")}} className="review">点击审核</button>}
                                    </div>
                                    <Field  name="purchaseApproveManId" type="text" component={hiddenField}  />
                                    <Field  name="purchaseApproveTimeString" label="采购审核时间" disabled={approveFormStateObject.purchaseApproveMan!=null?"disabled":""} type="text" component={inputField} readOnly="readOnly"  inputClassName="form-control datepicker"  />
                                    <Field  name="purchaseApproveState" label="采购审核状态" disabled={approveFormStateObject.purchaseApproveMan!=null?"disabled":""} required="required" component={selectField}items={firstApproveTypeCodeEnumItems} optionValue="code" optionName="name"/>
                                    <Field  name="purchaseApproveRemark"label="采购审核备注" maxLength="128" disabled={approveFormStateObject.purchaseApproveMan!=null?"disabled":""} type="text" component={inputField}   className="item-note" maxlength="128"/>

                                    <div className="item">
                                        <p><i>*</i>质量审核人</p>
                                        {approveUserNames.qualityApproveMan != undefined||approveFormStateObject.qualityApproveMan!= null ? <p className="qualityApproveMan"style={{color: "red"}}>{approveFormStateObject.qualityApproveMan!=null?approveFormStateObject.qualityApproveMan:approveUserNames.qualityApproveMan}</p> : <button onClick={(e)=>{e.preventDefault();showValidateModel("quality")}} className="review">点击审核</button>}
                                    </div>
                                    <Field  name="qualityApproveManId" type="text" component={hiddenField}  />
                                    <Field  name="qualityApproveTimeString" label="质量审核时间" disabled={approveFormStateObject.qualityApproveMan!=null?"disabled":""} type="text"  component={inputField} readOnly="readOnly"  inputClassName="form-control datepicker"  />
                                    <Field name="qualityApproveState" label="质量审核状态" disabled={approveFormStateObject.qualityApproveMan!=null?"disabled":""} required="required"component={selectField} items={firstApproveTypeCodeEnumItems} optionValue="code" optionName="name"/>
                                    <Field  name="qualityApproveRemark"label="质量审核备注" maxLength="128" disabled={approveFormStateObject.qualityApproveMan!=null?"disabled":""}  type="text" component={inputField}    className="item-note"   maxlength="128"/>

                                    <div className="item">
                                        <p><i>*</i>企业审核人</p>
                                        {approveUserNames.entApproveMan != undefined||approveFormStateObject.entApproveMan!= null ? <p className="entApproveMan" style={{color: "red"}}>{approveFormStateObject.entApproveMan!=null?approveFormStateObject.entApproveMan:approveUserNames.entApproveMan}</p> : <button onClick={(e)=>{e.preventDefault();showValidateModel("ent")}} className="review">点击审核</button>}
                                    </div>
                                    <Field  name="entApproveManId" type="text" component={hiddenField}  />
                                    <Field  name="entApproveTimeString" label="企业审核时间" disabled={approveFormStateObject.entApproveMan!=null?"disabled":""} type="text" component={inputField} readOnly="readOnly"  inputClassName="form-control datepicker"  />
                                    <Field name="entApproveState" label="企业审核状态" disabled={approveFormStateObject.entApproveMan!=null?"disabled":""} required="required" component={selectField} items={firstApproveTypeCodeEnumItems} optionValue="code" optionName="name"/>
                                    <Field  name="entApproveRemark"label="企业审核备注" maxLength="128" disabled={approveFormStateObject.entApproveMan!=null?"disabled":""} type="text" component={inputField}    className="item-note"   maxlength="128" />

                                </div>
                                <div className="layer-footer">
                                    <button onClick={() => { this.submitDataAndIniTData() }} type="submit" style={{border: "none"}} disabled={submitting} className="confirm" >{submitting ? <i/> :<i/>}保存</button>
                                    <a  href="javascript:void(0);"  className="cancel" onClick={() => { this.closeAddForm() }}>取消</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>)


    }
}

SupplierQualityApproveForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
SupplierQualityApproveForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({showValidateModel,initValidForm, errorValidMessageFunction,checkValidForm}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        initialValues: state.todos.approveFormStateObject,
        state,
        validate
    };
}
SupplierQualityApproveForm = reduxForm({
    form: 'supplierQualityApproveForm',
})(SupplierQualityApproveForm);

SupplierQualityApproveForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SupplierQualityApproveForm);

export default SupplierQualityApproveForm
