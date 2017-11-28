import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, Fields, FieldArray, reduxForm, change as changeFieldValue} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import SupplierSelectList from "../../../../../common/supplierSelectwin/components/SupplierSelectList";
import {showSupplierComponent} from "../../../../../common/supplierSelectwin/actions";
import {getLoginUserInfo} from "../../../../../common/common-actions";
import {LONG_REG} from "../../../../../common/common-constant";
import {selectField} from "../../../../../common/redux-form-ext";
import ApproveValidateComponent from "../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from "../../../../../common/approvevalidate/actions";
import PurchaseReturnGoodsSelectComponent from "./PurchaseReturnGoodsSelectComponent";

export const validate = (values, props) => {
    const itemList = values.itemList;
    const errors = {};
    calcAmount(itemList, props.changeFieldValue);
    if(!values.supplierId){
        props.errorValidMessageFunction("请选择供应商");
        errors.supplierId = "请选择供应商";
        return errors;
    }
    if (values.purchaseMan && values.purchaseMan.length > 32){
        props.errorValidMessageFunction("采购员不能大于32个字符");
        errors.purchaseMan = "采购员不能大于32个字符";
        return errors;
    }
    if (values.outStorageMan && values.outStorageMan.length > 32){
        props.errorValidMessageFunction("出库员不能大于32个字符");
        errors.outStorageMan = "出库员不能大于32个字符";
        return errors;
    }
    if (!values.returnedPurchaseType){
        props.errorValidMessageFunction("请选择退货类型");
        errors.returnedPurchaseType = "请选择退货类型";
        return errors;
    }
    if (!values.returnedPurchaseReason){
        props.errorValidMessageFunction("请选择退货原因");
        errors.returnedPurchaseReason = "请选择退货原因";
        return errors;
    }
    if (values.remark && values.remark.length > 64){
        props.errorValidMessageFunction("备注不能大于64个字符");
        errors.remark = "备注不能大于64个字符";
        return errors;
    }
    if (!values.approveManId){
        props.errorValidMessageFunction("请审核");
        errors.approveManId = "请审核";
        return errors;
    }
    if(itemList.length == 0){
        props.errorValidMessageFunction("请添加商品");
        errors.supplierId = "请添加商品";
        return errors;
    }
    errors.itemList = [];
    for (const i in itemList){
        const item = itemList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";
        if (!item.returnedPurchaseQuantity){
            errorStr = str + "请输入退货数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {returnedPurchaseQuantity: errorStr};
            return errors;
        }
        if (!LONG_REG.test(item.returnedPurchaseQuantity)){
            errorStr = str + "请输入正确的退货数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {returnedPurchaseQuantity: errorStr};
            return errors;
        }
        if(item.returnedPurchaseQuantity > item.returnableQuantity){
            errorStr = str + "退货数量不能大于可退数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {returnedPurchaseQuantity: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

const calcAmount = (itemList, changeFieldValue) => {
    for (const i in itemList){
        const item = itemList[i];
        if(LONG_REG.test(item.returnedPurchaseQuantity)){
            changeFieldValue("purchaseReturnAddForm", "itemList["+ i + "].amount", (item.returnedPurchaseQuantity*item.purchaseUnitPrice).toFixed(2));
        }
    }
};

export const boolRadioField = ({ input, label, id, required, meta: { touched, error } }) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <label><input name={input.name} type="radio" {...input} value="NORMAL_RETURNED" checked={input.value === 'NORMAL_RETURNED'}/>正常退货</label>
        <label><input name={input.name} type="radio" {...input} value="NOT_QUALIFIED_RETURNED" checked={input.value === 'NOT_QUALIFIED_RETURNED'}/>不合格退货</label>
    </div>
);

const inputField = ({ input, className, disabled, label, type, id, timeInput, required, readOnly, onClick, meta: { touched, error } }) => (
    <div className={"item " + (className || '')}>
        <p><i>{required ? '*' : ''}</i>{label}</p>
        <input disabled={disabled || ''} onClick={onClick ? onClick : () => {}} id={id} readOnly={readOnly ? readOnly : ''} className={(timeInput || '') + " form-control " + (required || '')} type={type}  {...input} />
    </div>
);

const inputSearchField = ({ input, disabled, label, type, id, timeInput, required, readOnly, onClick, meta: { touched, error } }) => (
    <div style={{position: "relative"}} className="item">
        <p><i>{required ? '*' : ''}</i>{label}<a onClick={onClick ? onClick : () => {}} className="item-input-a"></a></p>
        <input disabled={disabled || ''} id={id} readOnly={readOnly ? readOnly : ''} className={(timeInput || '') + " form-control " + (required || '')} type={type}  {...input} />
    </div>
);

const hiddenField = ({ input, type, id, readOnly, meta: { touched, error } }) => (
    <input id={id} readOnly={readOnly ? readOnly : ''} type={type} {...input} />
);

const tdField = ({ input, disabled, type, id, readOnly, meta: { touched, error } }) => (
    <input disabled={disabled || ''} autoComplete="off" id={id} readOnly={readOnly ? readOnly : ''} className='td-cont' type={type} {...input} />
);

const fieldArray = ({ fields, itemList, meta: { touched, error } }) => (
    <tbody>
        {
            fields.map((item, index) =>
                <tr key={index}>
                    <Field name={"itemList[" + index + '].purchaseAcceptanceRecordItemId'} type="hidden" component={hiddenField} />
                    <td>
                        <input className="td-cont r-goods-checkbox" style={{width: "inherit", height: "inherit"}} type="checkbox" value={index}/>
                    </td>
                    <td>
                        <div className="td-cont">{index + 1}</div>
                    </td>
                    <td className="pink-bg">
                        <Field name={"itemList[" + index + '].returnedPurchaseQuantity'} type="text" component={tdField} />
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].purchaseUnitPrice}</div>
                    </td>
                    <td>
                        <Field name={"itemList[" + index + '].amount'} disabled="disabled" type="text" component={tdField} />
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].returnableQuantity}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].goodsBatch}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].productionDateString}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].validityString}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].goodsCode}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].goodsNm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].goodsCommonNm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].spec}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].dosageForm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].unit}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].produceManufacturer}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].approvalNumber}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].productionPlace}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].retailPrice}</div>
                    </td>
                    <td>
                        <div className="td-cont">{itemList[index].purchaseTaxRate ? itemList[index].purchaseTaxRate + "%" : ""}</div>
                    </td>
                </tr>
            )
        }
    </tbody>
);

class PurchaseReturnAddForm extends Component{

    componentWillMount(){
        const {change} = this.props;
        this.props.getLoginUserInfo((data) => {
            change("purchaseMan", data.realName);
            change("outStorageMan", data.realName);
        });
    }

    componentDidMount() {
        $(".purchase-return").on("click", "input.r-check-all", function () {
            let checked = $(this)[0].checked;
            $(".r-goods-checkbox").each(function () {
                $(this)[0].checked = checked;
            });
        }).on("click", ".r-goods-checkbox", function () {
            $("input.r-check-all")[0].checked = $(".r-goods-checkbox").length == $(".r-goods-checkbox:checked").length
        });
    }

    componentWillUnmount(){
        $(".purchase-return").unbind();
    }

    supplierSelectedCallback(data){
        const {change} = this.props;
        change("supplierNm", data.supplierNm);
        change("supplierId", data.id);
        change("supplierCode", data.supplierCode);
    }

    removeGoods(){
        let indexArr = [];
        $(".r-goods-checkbox:checked").each(function () {
            indexArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        });
        if(indexArr.length > 0){
            const values = this.context.store.getState().form.purchaseReturnAddForm.values;
            this.props.array.removeAll("itemList");
            values.itemList.map((item, index) => {
                if($.inArray(index, indexArr) < 0){
                    this.props.array.push("itemList", item);
                }
            });
            $("input.r-check-all")[0].checked = false;
        }
    }

    changeApproveManId(data){
        const {change} = this.props;
        change("approveManId", data.id);
        change("approveMan", data.realName);
    }

    showGoodsSelect(){
        const values = this.context.store.getState().form.purchaseReturnAddForm.values;
        if (!values.supplierId){
            showSuccess("请先选择供应商");
            return false;
        }
        this.props.actions.showGoodsSelect(values.supplierId);
    }

    goodsSelectCallback(data){
        const values = this.context.store.getState().form.purchaseReturnAddForm.values;
        let purchaseAcceptanceRecordItemIdArr = [];
        values.itemList.map(item => {
            purchaseAcceptanceRecordItemIdArr.push(item.purchaseAcceptanceRecordItemId);
        });
        data.map(acceptanceRecord => {
           if($.inArray(acceptanceRecord.id, purchaseAcceptanceRecordItemIdArr) < 0){
               acceptanceRecord.purchaseAcceptanceRecordItemId = acceptanceRecord.id;
               this.props.array.push("itemList", acceptanceRecord);
           }
        });
    }

    render(){
        const {actions, handleSubmit, submitting, checkValidForm, showSupplierComponent, showValidateModel} = this.props;
        const {store} = this.context;
        const {validFormState, errorValidMessage} = store.getState().validTodos;
        const values = store.getState().form.purchaseReturnAddForm.values;
        return (
            <form onSubmit={handleSubmit((data) => actions.savePurchaseReturn(data))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage} checkValidForm={checkValidForm}/>}
                {store.getState().supplieComponentTodos.commonAddSupplierListState && <SupplierSelectList actions={actions}/>}
                {store.getState().todos.showGoodsSelect && <PurchaseReturnGoodsSelectComponent callbackFunc={(data) => this.goodsSelectCallback(data)} actions={actions}/>}
                <ApproveValidateComponent callbackFunc={(data) => this.changeApproveManId(data)}/>
                <div className="layer purchase-return">
                <div className="layer-box layer-info layer-receiving w1210">
                    <div className="layer-header">
                        <span>新增购进退出</span>
                        <a href="javascript:;" onClick={() => actions.showAdd()} className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <Field id="supplierId" name="supplierId" type="hidden" component={hiddenField} label="供货单位ID" readOnly="readOnly" />
                                <Field id="supplierCode" name="supplierCode" type="text" component={inputField} required="required" label="供货单位编码" disabled="disabled" />
                                <Field onClick={() => showSupplierComponent(true, values.supplierNm, this.supplierSelectedCallback.bind(this))} required="required" id="supplierNm" name="supplierNm" type="text" component={inputSearchField} label="供货单位名称"/>
                                <Field id="purchaseMan" name="purchaseMan" type="text" component={inputField} label="采购员" />
                                <Field id="outStorageMan" name="outStorageMan" type="text" component={inputField}  label="出库员" />
                                <Field id="returnedPurchaseType" name="returnedPurchaseType" component={boolRadioField} required="required" label="退货类型" />
                                <Field items={[{value: '破损'},{value: '找回'},{value: '滞销'},{value: '过期失效'},{value: '近效期'},{value: '其他'}]} optionName="value" optionValue="value" id="returnedPurchaseReason" name="returnedPurchaseReason" component={selectField} required="required" label="退货原因" />
                                <Field className="item-note" id="remark" type="text" name="remark" component={inputField} label="备注" />
                                <Field id="approveManId" type="hidden" name="approveManId" component={hiddenField} />
                                <div className="item">
                                    <p><i>*</i>审核人</p>
                                    {
                                        values.approveMan ? <span style={{color: "red"}}>{values.approveMan}</span> :
                                            <a href="javascript:;" onClick={() => showValidateModel()} className="review">点击审核</a>
                                    }
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <div title="先选择供应商再添加商品" onClick={() => this.showGoodsSelect()} className="item-add">添加商品</div>
                                    <div onClick={() => this.removeGoods()} className="item-del">删除商品</div>
                                </div>
                            </div>
                            <div className="box-mc">
                                <table style={{width:"1600px"}}>
                                    <thead>
                                    <tr>
                                        <th style={{width: "25px"}} className="th-checkbox"><label><input style={{width: "inherit"}} className="r-check-all" type="checkbox" value="checkbox"/></label></th>
                                        <th style={{width: "35px"}} className="serial-number">序号</th>
                                        <th className="number">数量</th>
                                        <th className="price">采购单价（元）</th>
                                        <th className="price">金额（元）</th>
                                        <th className="number">可退数量</th>
                                        <th className="commodity-code">批号</th>
                                        <th className="commodity-code">生产日期</th>
                                        <th className="commodity-code">有效期至</th>
                                        <th className="commodity-code">商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="general-name">通用名称</th>
                                        <th className="specifications">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number">批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="price">零售价（元）</th>
                                        <th className="purchase-rate">采购税率</th>
                                    </tr>
                                    </thead>
                                    <FieldArray name="itemList" itemList={values.itemList} component={fieldArray}/>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div className="layer-footer">
                        <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                        <a href="javascript:;" className="cancel" onClick={() => actions.showAdd()}>取消</a>
                    </div>
                </div>
            </div>
            </form>
        );
    }
}

PurchaseReturnAddForm.propTypex = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
}

PurchaseReturnAddForm.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        initialValues: {returnedPurchaseType: "NORMAL_RETURNED", itemList: []},
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        checkValidForm, errorValidMessageFunction,
            showSupplierComponent, getLoginUserInfo, changeFieldValue,
            showValidateModel}
        , dispatch);
}

PurchaseReturnAddForm = reduxForm({
    form: 'purchaseReturnAddForm',
    validate,
})(PurchaseReturnAddForm);

PurchaseReturnAddForm = connect(mapStateToProps, mapDispatchToProps)(PurchaseReturnAddForm);

export default PurchaseReturnAddForm;

