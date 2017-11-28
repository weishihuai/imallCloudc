import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField} from "../../../../../../common/redux-form-ext";
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';
import {changeCommonGoodsBatchListState} from '../../../../../../common/goodsbatchselectwin/actions'
import CommonGoodsBatchList from '../../../../../../common/goodsbatchselectwin/components/CommonGoodsBatchList'
import * as types from "../constants/ActionTypes";
export const fields = [
    {
        field:'docMakerNm',
        validate:{
            fieldNm: "制单人姓名",
            maxlength:32,
            required:true
        }
    },
    {
        field:'stopSaleDateStr',
        validate:{
            fieldNm: "停售时间",
            required:true
        }
    },
    {
        field:'qualityState',
        validate:{
            fieldNm: "质量状况",
            maxlength:64,
            required:false
        }
    },
    {
        field:'stopSaleSuggest',
        validate:{
            fieldNm: "停售意见",
            maxlength:64,
            required:false
        }
    },
    {
        field:'approveManId',
        validate:{
            error:"请点击审核",
            required:true
        }
    },
];

export const validate = (values, props) => {
    const errors = {};
    const fields = props.fields;
    for (const i in fields) {
        const fieldObj = fields[i];
        const field = fieldObj.field;
        const validate = fieldObj.validate;
        const required = validate.required;
        const fieldNm = validate.fieldNm;
        const error = validate.error;

        if (required != undefined && required) {
            if (values[field] == undefined || !values[field].toString().trim()) {
                errors[field] = error?error:"请输入" + fieldNm;
                props.errorValidMessageFunction(errors[field]);
                return errors;
            }
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

        const minValue = validate.minValue;
        const maxValue = validate.maxValue;
        if (values[field] != undefined && minValue != undefined && !isNaN(values[field]) && values[field] < minValue) {
            errors[field] = fieldNm + "不能小于" + minValue;
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if (values[field] != undefined && maxValue != undefined && !isNaN(values[field]) && values[field] > maxValue) {
            errors[field] = fieldNm + "不能大于" + maxValue;
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors
};

export const hiddenInput = ({ input, type, id, hidden,meta: { touched, error } }) => (
    <input type={type} id={id}  style={{display:hidden?"none":"block"}} name={input.name} {...input}/>
);

class StopSaleNoticeAddForm extends Component{
    constructor(props) {
        super(props);
    }

    submit(data){
        const {params,page} = this.context.store.getState().todos;
        const {selectedPage} = this.context.store.getState().todos;
        if(selectedPage.content.length == 0){
            this.props.errorValidMessageFunction("请添加商品");
            return ;
        }
        this.props.errorValidMessageFunction("");
        let stopSaleGoodsInfSaveVoList = [];
        selectedPage.content.map((content,index)=>{
            let obj={
                batchId:content.id,
                goodsNm:content.goodsNm,
                goodsCode:content.goodsCode,
                commonNm:content.commonNm,
                goodsPinyin:content.pinyin,
                spec:content.spec,
                dosageForm:content.dosageForm,
                unit:content.unit,
                produceManufacturer:content.produceManufacturer,
                productionPlace:content.productionPlace,
                approvalNumber:content.approvalNumber,
                validDateStr:content.validDateString,
                batch:content.batch,
            };
            stopSaleGoodsInfSaveVoList.push(obj);
        });

        this.props.actions.saveStopSalseNotice(Object.assign({},data,{
            stopSaleGoodsInfSaveVoList:stopSaleGoodsInfSaveVoList
        }),params,page);
    }

    componentDidMount() {
        const {change} = this.props;

        $("#stopSaleDateStr").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("stopSaleDateStr",$("#stopSaleDateStr").val());
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
            change('approveManId', json.id);
            $("#reviewBtn").hide();
            $("#_approveManName").text(json.realName);
            $("#_approveManName").show();
        }
    }

    openCommonGoodsBatchSelectWin() {
        this.props.changeCommonGoodsBatchListState({isSingle:false,batchState:"NORMAL"}, (selectedGoodsList)=>{
            const {store} = this.context;
            let selectedObjs = store.getState().todos.selectedPage.content||[];
            let oldSelectedIds = store.getState().todos.selectedIds||[];
            let selectedIds = [];
            selectedGoodsList.forEach(row => {
                if(row) {
                    selectedIds.push(row.id);
                }
            });
            selectedObjs = selectedObjs.filter(obj => selectedIds.indexOf(obj.id)==-1).concat(selectedGoodsList);
            let page = {
                content: selectedObjs,
                totalElements: selectedObjs.length,
                number: 0,
                size: 1000
            };
            let isAllSelected = page.content.length == oldSelectedIds.length;
            store.dispatch({
                type:types.STOP_SALE_NOTICE_ADD_GOODS_BATCH_PAGE,
                data:page,
                isAllSelected:isAllSelected
            })
        });
    }

    onRowSelect(store,content){
        let {selectedIds,selectedContents,selectedPage,isAllSelected} = store.getState().todos;
        let newSelectedIds = [];
        let newSelectedContents = [];
        let isSelectOrCancel = selectedIds.indexOf(content.id)==-1;
        if(isSelectOrCancel){
            newSelectedIds = selectedIds.filter(id => id != content.id).concat(content.id);
            newSelectedContents = selectedContents.filter(obj => obj.id != content.id).concat(content);
            isAllSelected = newSelectedIds.length == selectedPage.content.length;
        }else{
            newSelectedIds = selectedIds.filter(id => id != content.id);
            newSelectedContents = selectedContents.filter(obj => obj.id != content.id);
            isAllSelected = false;
        }
        store.dispatch({
            type:types.STOP_SALE_NOTICE_ADD_CHANGE_SELECTED_PAGE,
            newSelectedIds:newSelectedIds,
            newSelectedContents:newSelectedContents,
            isAllSelected:isAllSelected
        })
    }

    onSelectAll(store){
        let {selectedPage,isAllSelected} = store.getState().todos;
        const contents = selectedPage.content || [];
        let newSelectedIds = [];
        let newSelectedContents = [];
        isAllSelected=!isAllSelected;
        if(isAllSelected){
            contents.forEach((content)=>{
                newSelectedIds.push(content.id);
                newSelectedContents.push(content);
            })
        }
        store.dispatch({
            type:types.STOP_SALE_NOTICE_ADD_CHANGE_SELECTED_PAGE,
            newSelectedIds:newSelectedIds,
            newSelectedContents:newSelectedContents,
            isAllSelected:isAllSelected
        })
    }

    deleteContents(){
        const {store} = this.context;
        let {selectedIds,selectedPage} = store.getState().todos;
        if(selectedIds.length ==0) return;
        let newSelectedContents = selectedPage.content.filter(obj => selectedIds.indexOf(obj.id) == -1);
        let page={
            content: newSelectedContents,
            totalElements: newSelectedContents.length,
            number: 0,
            size: 1000
        };
        store.dispatch({
            type:types.STOP_SALE_NOTICE_DELETE_GOODS_BATCH_CONTENTS,
            data:page
        })
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const {selectedPage,isAllSelected} = store.getState().todos;
        const selectedIds = store.getState().todos.selectedIds||[];
        const contents = selectedPage.content||[];
        const {handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {display} = this.context.store.getState().approveValidateTodos;
        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && errorValidMessage && <ValidForm  checkValidForm={checkValidForm}/>}
                {display && <ApproveValidateComponent callbackFunc={(json)=>this.approveValidateCallBack(json)} />}
                {store.getState().goodsBatchTodos.commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                <div className="layer" >
                    <div className="layer-box layer-info layer-order layer-receiving w1175">
                        <div className="layer-header">
                            <span>药品停售通知单</span>
                            <a href="javascript:void(0)" className="close" onClick={()=>this.props.actions.changeAddFormState(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">停售信息</div>
                                <div className="box-mc clearfix">
                                    <Field id="approveManId" name="approveManId" hidden="hidden" type="text" component={hiddenInput}/>
                                    <Field name="docMakerNm" id="docMakerNm" type="text" required="required" label="制单人" component={inputField} />
                                    <Field id="stopSaleDateStr" name="stopSaleDateStr" required="required" inputClassName="form-control datepicker" type="text" component={inputField} label="停售日期" readOnly/>
                                    <Field name="qualityState" id="qualityState" type="text" label="质量状况" component={inputField} />
                                    <Field name="stopSaleSuggest" id="stopSaleSuggest" type="text" label="停售意见" component={inputField} />
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        <a  className="review" onClick={()=>showValidateModel()} id="reviewBtn">点击审核</a>
                                        <p id="_approveManName" style={{display:'none'}}/>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={()=>this.openCommonGoodsBatchSelectWin()}>添加商品</div>
                                        <div className="item-del"  onClick={()=>this.deleteContents()}>删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc clearfix">
                                    <table className="w960">
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" onClick={()=>this.onSelectAll(store)} disabled={contents.length == 0}><input type="checkbox" disabled={contents.length == 0} checked={isAllSelected} /></th>
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
                                            <th className="approval-number">批号</th>
                                            <th className="approval-number">有效期至</th>
                                        </tr>
                                        </thead>
                                        { contents.length <= 0 &&
                                        <tbody><tr ><th colSpan="100" style={{textAlign:"center"}}>暂无数据</th></tr></tbody>
                                        }
                                        <tbody>
                                        {
                                            contents.map((content,index)=>{
                                                return (
                                                    <tr key={index}>
                                                        <td onClick={()=>this.onRowSelect(store,content)}><input type="checkbox"  checked={selectedIds.indexOf(content.id)>-1} /></td>
                                                        <td><div className="td-cont" >{index+1}</div></td>
                                                        <td><div className="td-cont">{content.goodsCode}</div></td>
                                                        <td><div className="td-cont">{content.goodsNm}</div></td>
                                                        <td><div className="td-cont">{content.commonNm}</div></td>
                                                        <td><div className="td-cont">{content.spec}</div></td>
                                                        <td><div className="td-cont">{content.dosageFormName}</div></td>
                                                        <td><div className="td-cont">{content.unit}</div></td>
                                                        <td><div className="td-cont">{content.produceManufacturer}</div></td>
                                                        <td><div className="td-cont">{content.approvalNumber}</div></td>
                                                        <td><div className="td-cont">{content.productionPlace}</div></td>
                                                        <td><div className="td-cont">{content.batch}</div></td>
                                                        <td><div className="td-cont">{content.validDateString}</div></td>
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
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} type="submit" onClick={() => {checkValidForm(true)}}>保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.changeAddFormState(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

StopSaleNoticeAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

StopSaleNoticeAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        showValidateModel,
        changeCommonGoodsBatchListState
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: {},
        fields: fields,
        state
    }
}

StopSaleNoticeAddForm = reduxForm({
    form: 'stopSaleNoticeAddForm',
    validate:validate
})(StopSaleNoticeAddForm);

StopSaleNoticeAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(StopSaleNoticeAddForm);

export default StopSaleNoticeAddForm;
