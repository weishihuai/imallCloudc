import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, Fields, FieldArray, reduxForm, change as changeFieldValue} from "redux-form";
import CommonGoodsList from "../../../../../common/goodsselectwin/components/CommonGoodsList";
import {changeCommonGoodsListState} from "../../../../../common/goodsselectwin/actions";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import SupplierSelectList from "../../../../../common/supplierSelectwin/components/SupplierSelectList";
import {showSupplierComponent} from "../../../../../common/supplierSelectwin/actions";
import {getLoginUserInfo} from "../../../../../common/common-actions";
import {DOUBLE_REG, LONG_REG} from "../../../../../common/common-constant";

export const validate = (values, props) => {
    const itemList = values.itemList;
    calcTotalAmount(itemList, props.changeFieldValue);
    const errors = {};
    if(values.purchaseMan && values.purchaseMan.length > 32){
        props.errorValidMessageFunction("采购人不能大于32个字符");
        errors.purchaseMan = "采购人不能大于32个字符";
        return errors;
    }
    if(values.sellMan && values.sellMan.length > 32){
        props.errorValidMessageFunction("销售人员不能大于32个字符");
        errors.sellMan = "销售人员不能大于32个字符";
        return errors;
    }
    if(values.contactTel && values.contactTel.length > 32){
        props.errorValidMessageFunction("联系电话不能大于32个字符");
        errors.contactTel = "联系电话不能大于32个字符";
        return errors;
    }
    if(values.remark && values.remark.length > 32){
        props.errorValidMessageFunction("备注不能大于32个字符");
        errors.remark = "备注电话不能大于32个字符";
        return errors;
    }
    if(!values.supplierId){
        props.errorValidMessageFunction("请选择供应商");
        errors.supplierId = "请选择供应商";
        return errors;
    }
    if (!values.expectedArrivalTimeString){
        props.errorValidMessageFunction("请选择预计到货时间");
        errors.expectedArrivalTimeString = "请选择预计到货时间";
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
        if (!item.purchaseQuantity){
            errorStr = str + "请输入采购数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {purchaseQuantity: errorStr};
            return errors;
        }
        if (!LONG_REG.test(item.purchaseQuantity)){
            errorStr = str + "请输入正确的采购数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {purchaseQuantity: errorStr};
            return errors;
        }
        if (!item.purchaseUnitPrice){
            errorStr = str + "请输入采购单价";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {purchaseUnitPrice: errorStr};
            return errors;
        }
        if (!DOUBLE_REG.test(item.purchaseUnitPrice)){
            errorStr = str + "请输入正确的采购单价";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {purchaseUnitPrice: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

const calcTotalAmount = (itemList, changeFieldValue) => {
    let totalAmount = 0;
    for (const i in itemList){
        const item = itemList[i];
        if (LONG_REG.test(item.purchaseQuantity) && DOUBLE_REG.test(item.purchaseUnitPrice)){
            let itemTotal = (item.purchaseQuantity*item.purchaseUnitPrice).toFixed(2);
            changeFieldValue("purchaseOrderAddForm", "itemList[" + i + "].itemTotalAmount", itemTotal);
            totalAmount = parseFloat(totalAmount) + parseFloat(itemTotal);
        }
    }
    if (totalAmount){
        changeFieldValue("purchaseOrderAddForm", "purchaseTotalAmount", parseFloat(totalAmount).toFixed(2));
    }
};

const inputField = ({ input, disabled, label, type, id, timeInput, required, readOnly, onClick, meta: { touched, error } }) => (
    <div className="item">
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

const fieldArray = ({ fields, goodsData, meta: { touched, error } }) => (
    <tbody>
        {
            fields.map((item, index) =>
                <tr key={index}>
                    <td>
                        <input className="td-cont goods-checkbox" style={{width: "inherit", height: "inherit"}} type="checkbox" value={goodsData[index].goodsId}/>
                    </td>
                    <td>
                        <div className="td-cont">{index + 1}</div>
                    </td>
                    <td className="pink-bg">
                        <Field name={"itemList[" + index + '].purchaseQuantity'} type="text" component={tdField} />
                    </td>
                    <td className="pink-bg">
                        <Field name={"itemList[" + index + '].purchaseUnitPrice'} type="text" component={tdField} />
                    </td>
                    <td>
                        <Field disabled="disabled" name={"itemList[" + index + '].itemTotalAmount'} type="text" component={tdField} />
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].goodsCode}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].goodsNm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].commonNm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].spec}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].dosageFormName}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].unit}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].produceManufacturer}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].approvalNumber}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].productionPlace}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].retailPrice}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsData[index].purchaseTaxRate ? goodsData[index].purchaseTaxRate + "%" : ""}</div>
                    </td>
                </tr>
            )
        }
    </tbody>
);

class PurchaseOrderAddForm extends Component{

    componentWillMount(){
        const {change} = this.props;
        this.props.getLoginUserInfo((data) => {
            change("purchaseMan", data.realName);
        });
    }

    componentDidMount() {
        const {change} = this.props;
        $(".purchase-order .datepicker").on("click",function(e){
            e.stopPropagation();
            const _this = $(this);
            _this.lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change( _this.attr("name"), _this.val());
                }
            });
        });

        $(".purchase-order input.check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".goods-checkbox").each(function () {
                $(this)[0].checked = checked;
            });
        });
        $(".purchase-order").on("click", ".goods-checkbox", function () {
            $("input.check-all")[0].checked = $(".goods-checkbox").length == $(".goods-checkbox:checked").length
        });
    }

    componentWillUnmount(){
        $(".purchase-order").unbind();
    }

    goodsSelectedCallback(goodsData){
        if (goodsData.length > 0){
            const {store} = this.context;
            const values = store.getState().form.purchaseOrderAddForm.values;
            let goodsIdArr = [];
            values.itemList.map(item => {
                goodsIdArr.push(item.goodsId);
            });
            goodsData.map(goods => {
                if ($.inArray(goods.id, goodsIdArr) < 0){
                    goods.goodsId = goods.id;
                    this.props.array.push("itemList", goods);
                }
            });
        }
    }

    supplierSelectedCallback(data){
        const {change} = this.props;
        change("supplierNm", data.supplierNm);
        change("supplierId", data.id);
        change("supplierCode", data.supplierCode);
    }

    removeGoods(){
        let goodsIdArr = [];
        $(".goods-checkbox:checked").each(function () {
            goodsIdArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        });
        if (goodsIdArr.length != 0){
            const {store} = this.context;
            const values = store.getState().form.purchaseOrderAddForm.values;
            this.props.array.removeAll("itemList");
            values.itemList.map((item, index) => {
                if ($.inArray(item.id, goodsIdArr) < 0){
                    this.props.array.push("itemList", item);
                }
            });
            $("input.check-all")[0].checked = false;
        }
    }

    render(){
        const {actions, changeCommonGoodsListState, handleSubmit, submitting, checkValidForm, showSupplierComponent} = this.props;
        const {store} = this.context;
        const {validFormState, errorValidMessage} = store.getState().validTodos;
        const values = store.getState().form.purchaseOrderAddForm.values;
        const goodsData = values.itemList;
        return (
            <form onSubmit={handleSubmit((data) => actions.savePurchaseOrder(data))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage} checkValidForm={checkValidForm}/>}
                {store.getState().goodsTodos.commonAddGoodsListState && <CommonGoodsList actions={actions}/>}
                {store.getState().supplieComponentTodos.commonAddSupplierListState && <SupplierSelectList actions={actions}/>}
                <div className="layer purchase-order">
                <div className="layer-box layer-info layer-receiving w1290">
                    <div className="layer-header">
                        <span>新增采购订单</span>
                        <a href="javascript:;" onClick={() => actions.showAdd()} className="close"></a>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">单据信息</div>
                            <div className="box-mc clearfix">
                                <Field id="purchaseMan" name="purchaseMan" type="text" component={inputField} label="采购人" />
                                <Field id="supplierId" name="supplierId" type="hidden" component={hiddenField} label="供货单位ID" />
                                <Field id="supplierCode" name="supplierCode" type="text" component={inputField} required="required" label="供货单位编码" disabled="disabled" />
                                <Field onClick={() => showSupplierComponent(true, values.supplierNm, this.supplierSelectedCallback.bind(this))} required="required" id="supplierNm" name="supplierNm" type="text" component={inputSearchField} label="供货单位名称"/>
                                <Field id="sellMan" name="sellMan" type="text" component={inputField} label="销售人员" />
                                <Field id="contactTel" name="contactTel" type="text" component={inputField}  label="联系电话" />
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">其他信息</div>
                            <div className="box-mc clearfix">
                                <Field id="expectedArrivalTimeString" name="expectedArrivalTimeString" timeInput="datepicker" type="text" readOnly="readOnly" component={inputField} label="预计到货时间" required="required"/>
                                <Field disabled="disabled" id="purchaseTotalAmount" name="purchaseTotalAmount" type="text" component={inputField} label="采购总金额（元）" />
                                <Field id="remark" name="remark" type="text" component={inputField} label="备注" />
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <div onClick={() => changeCommonGoodsListState({isSingle: false}, this.goodsSelectedCallback.bind(this))} className="item-add">添加商品</div>
                                    <div onClick={() => this.removeGoods()} className="item-del">删除商品</div>
                                </div>
                            </div>
                            <div className="box-mc">
                                <table>
                                    <thead>
                                    <tr>
                                        <th style={{width: "25px"}} className="th-checkbox"><label><input style={{width: "inherit"}} className="check-all" type="checkbox" value="checkbox"/></label></th>
                                        <th style={{width: "35px"}} className="serial-number">序号</th>
                                        <th className="number">数量</th>
                                        <th className="price">采购单价（元）</th>
                                        <th className="price">金额（元）</th>
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
                                    {
                                        goodsData.length == 0 ? <tbody><tr><th colSpan="16" style={{textAlign: "center"}} >暂无数据</th></tr></tbody> : <FieldArray name="itemList" goodsData={goodsData} component={fieldArray}/>
                                    }
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

PurchaseOrderAddForm.propTypex = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
}

PurchaseOrderAddForm.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        initialValues: {itemList: [], purchaseTotalAmount:0},
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({changeCommonGoodsListState,
        checkValidForm, errorValidMessageFunction,
            showSupplierComponent, getLoginUserInfo, changeFieldValue}
        , dispatch);
}

PurchaseOrderAddForm = reduxForm({
    form: 'purchaseOrderAddForm',
    validate,
})(PurchaseOrderAddForm);

PurchaseOrderAddForm = connect(mapStateToProps, mapDispatchToProps)(PurchaseOrderAddForm);

export default PurchaseOrderAddForm;

