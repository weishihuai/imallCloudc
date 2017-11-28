import React, {Component, PropTypes} from "react";
import {Field, reduxForm, change} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {validate, inputField} from "../../../../../../common/redux-form-ext";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import {REGEXP_INT,REGEXP_PHONE,ERROR_PHONE_FORMAT} from "../../../../../../common/common-constant";

export const fields = [{
    field:'customerName',
    validate:{
        required:true,
        maxlength:32,
        fieldNm: "顾客姓名"
    }
},{
    field:'complainDateString',
    validate:{
        required:true,
        fieldNm: "投诉日期"
    }
},{
    field:'mobile',
    validate:{
        required:false,
        maxlength:11,
        fieldNm: "手机",
        regx: REGEXP_PHONE,
        error: ERROR_PHONE_FORMAT,
    }
},{
    field:'addr',
    validate:{
        required:false,
        maxlength:64,
        fieldNm: "地址"
    }
},{
    field:'complainCont',
    validate:{
        required:true,
        maxlength:256,
        fieldNm: "投诉内容"
    }
},{
    field:'goodsCode',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "商品编码"
    }
},{
    field:'goodsNm',
    validate:{
        required:false,
        maxlength:128,
        fieldNm: "商品名称"
    }
},{
    field:'goodsPinyin',
    validate:{
        required:false,
        maxlength:64,
        fieldNm: "商品拼音"
    }
},{
    field:'spec',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "规格"
    }
},{
    field:'dosageForm',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "剂型"
    }
},{
    field:'produceManufacturer',
    validate:{
        required:false,
        maxlength:64,
        fieldNm: "生产厂商"
    }
},{
    field:'approvalNumber',
    validate:{
        required:false,
        maxlength:64,
        fieldNm: "批准文号"
    }
},{
    field:'batch',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "批号"
    }
},{
    field:'validDateString',
    validate:{
        required:false,
        fieldNm: "有效期至"
    }
},{
    field:'quantity',
    validate:{
        required:false,
        maxlength:8,
        regx: REGEXP_INT,
        fieldNm: "数量"
    }
},{
    field:'surveyCondition',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "调查情况"
    }
},{
    field:'surveyManName',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "调查人姓名"
    }
},{
    field:'surveyDateString',
    validate:{
        required:false,
        fieldNm: "调查日期"
    }
},{
    field:'suggest',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "建议"
    }
},{
    field:'processMeasure',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "处理措施"
    }
},{
    field:'processManName',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "处理人姓名"
    }
},{
    field:'processDateString',
    validate:{
        required:false,
        fieldNm: "处理日期"
    }
}];


class ComplainRecordAddForm extends Component {
    
    componentDidMount() {
        const {change} = this.props;
        $("#complainDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("complainDateString", $('input[name="complainDateString"]').val());
                }
            });
        });
        $("#validDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("validDateString", $('input[name="validDateString"]').val());
                }
            });
        });
        $("#surveyDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("surveyDateString", $('input[name="surveyDateString"]').val());
                }
            });
        });
        $("#processDateString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("processDateString", $('input[name="processDateString"]').val());
                }
            });
        });
    }

    render() {
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {complainAddFormModal} = this.props.actions;
        const {errorValidMessage, validFormState} = store.getState().validTodos;

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info complaints-record w960">
                        <div className="layer-header">
                            <span>投诉记录</span>
                            <a href="javascript:void(0);" className="close" onClick={() => complainAddFormModal(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">投诉信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="customerName" id="customerName" type="text" label="顾客姓名" required="required" maxLength="32" component={inputField} />
                                    <Field name="complainDateString" id="complainDateString" inputClassName="datepicker" type="text" label="投诉日期" required="required" readOnly="readOnly" component={inputField} />
                                    <Field name="mobile" id="mobile" type="text" label="手机" maxLength="32" component={inputField} />
                                    <Field name="addr" id="addr" type="text" label="地址" maxLength="64" component={inputField} />
                                    <Field name="complainCont" id="complainCont" type="text" className="item-1-2" label="投诉内容" required="required" maxLength="256" component={inputField} />
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">投诉商品</div>
                                <div className="box-mc clearfix">
                                    <Field name="goodsCode" id="goodsCode" type="text" label="商品编码" maxLength="32" component={inputField} />
                                    <Field name="goodsNm" id="goodsNm" type="text" label="商品名称" maxLength="128" component={inputField} />
                                    <Field name="spec" id="spec" type="text" label="规格" maxLength="32" component={inputField} />
                                    <Field name="dosageForm" id="dosageForm" type="text" label="剂型" maxLength="32" component={inputField} />
                                    <Field name="produceManufacturer" id="produceManufacturer" type="text" label="生产厂商" maxLength="64" component={inputField} />
                                    <Field name="approvalNumber" id="approvalNumber" type="text" label="批准文号" maxLength="64" component={inputField} />
                                    <Field name="batch" id="batch" type="text" label="批号" maxLength="32" component={inputField} />
                                    <Field name="validDateString" id="validDateString" inputClassName="datepicker" type="text" label="有效期至" readOnly="readOnly" component={inputField} />
                                    <Field name="quantity" id="quantity" type="text" label="数量" maxLength="8" component={inputField} />
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">处理措施</div>
                                <div className="box-mc clearfix">
                                    <Field name="surveyCondition" id="surveyCondition" type="text" label="调查情况" maxLength="32" component={inputField} />
                                    <Field name="surveyManName" id="surveyManName" type="text" label="调查人姓名" maxLength="32" component={inputField} />
                                    <Field name="surveyDateString" id="surveyDateString" inputClassName="datepicker" type="text" label="调查日期" readOnly="readOnly" component={inputField} />
                                    <Field name="suggest" id="suggest" type="text" label="投诉意见或建议" maxLength="32" component={inputField} />
                                    <Field name="processMeasure" id="processMeasure" type="text" label="处理措施" maxLength="32" component={inputField} />
                                    <Field name="processManName" id="processManName" type="text" label="处理人姓名" maxLength="32" component={inputField} />
                                    <Field name="processDateString" id="processDateString" inputClassName="datepicker" type="text" label="处理日期" readOnly="readOnly" component={inputField} />
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>complainAddFormModal(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

ComplainRecordAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

ComplainRecordAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({checkValidForm, errorValidMessageFunction}, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate:validate,
        state
    }
}

ComplainRecordAddForm = reduxForm({
    form: 'complainRecordAddForm',
    validate
})(ComplainRecordAddForm);

export default connect(mapStateToProps, mapDispatchToProps)(ComplainRecordAddForm);