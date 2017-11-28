import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {Field, Fields, FieldArray, reduxForm, change as changeFieldValue} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {getLoginUserInfo} from "../../../../../common/common-actions";
import {DOUBLE_REG, LONG_REG} from "../../../../../common/common-constant";
import CommonGoodsList from "../../../../../common/goodsselectwin/components/CommonGoodsList";
import SupplierSelectList from "../../../../../common/supplierSelectwin/components/SupplierSelectList";
import {showSupplierComponent} from "../../../../../common/supplierSelectwin/actions";
import {changeCommonGoodsListState} from "../../../../../common/goodsselectwin/actions";
import ApproveValidateComponent from "../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from "../../../../../common/approvevalidate/actions";

export const validate = (values, props) => {
    const itemList = values.itemList;
    calcTotalAmount(itemList, props.changeFieldValue);
    const errors = {};
    if(!values.supplierId){
        props.errorValidMessageFunction("请选择供应商");
        errors.supplierId = "请选择供应商";
        return errors;
    }
    if(!values.approveManId){
        props.errorValidMessageFunction("请审核");
        errors.approveManId = "请审核";
        return errors;
    }
    if (itemList.length == 0){
        props.errorValidMessageFunction("请选择商品");
        errors.supplierId = "请选择商品";
        return errors;
    }
    errors.itemList = [];
    for (const i in itemList){
        const item = itemList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";
        //批号
        if (!item.goodsBatch){
            errorStr = str + "请输入或选择商品批号";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {goodsBatch: errorStr};
            return errors;
        }
        if (item.goodsBatch.length > 32){
            errorStr = str + "商品批号不能大于32个字符";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {goodsBatch: errorStr};
            return errors;
        }
        if(!item.productionDateString){
            errorStr = str + "请选择生产日期";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {productionDateString: errorStr};
            return errors;
        }
        if(!item.validityString){
            errorStr = str + "请选择有效期";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {validityString: errorStr};
            return errors;
        }
        let start = new Date(item.productionDateString);
        let end = new Date(item.validityString);
        if(end <= start){
            errorStr = str + "有效期必须在生产日期之后";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {validityString: errorStr};
            return errors;
        }
        //货位
        if(!item.goodsBatchId && !item.storageSpaceId){
            errorStr = str + "请选择货位";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {storageSpaceId: errorStr};
            return errors;
        }
        //到货数量
        if (!item.goodsArrivalQuantity){
            errorStr = str + "请输入到货数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {goodsArrivalQuantity: errorStr};
            return errors;
        }
        if (!LONG_REG.test(item.goodsArrivalQuantity)){
            errorStr = str + "请输入正确的到货数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {goodsArrivalQuantity: errorStr};
            return errors;
        }
        //采购单价
        if (!item.purchaseUnitPrice){
            errorStr = str + "请输入进货单价";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {purchaseUnitPrice: errorStr};
            return errors;
        }
        if (!DOUBLE_REG.test(item.purchaseUnitPrice)){
            errorStr = str + "请输入正确的进货单价";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {purchaseUnitPrice: errorStr};
            return errors;
        }
        //零售价
        if (!item.retailPrice){
            errorStr = str + "请输入零售价";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {retailPrice: errorStr};
            return errors;
        }
        if(!DOUBLE_REG.test(item.retailPrice)){
            errorStr = str + "请输入正确的零售价";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {retailPrice: errorStr};
            return errors;
        }
        //合格数量
        if (!item.qualifiedQuantity){
            errorStr = str + "请输入合格数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {qualifiedQuantity: errorStr};
            return errors;
        }
        if (!/^\d{1,18}$/.test(item.qualifiedQuantity)){
            errorStr = str + "请输入正确的合格数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {qualifiedQuantity: errorStr};
            return errors;
        }
        if(item.qualifiedQuantity > item.goodsArrivalQuantity){
            errorStr = str + "合格数量不能大于到货的数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {qualifiedQuantity: errorStr};
            return errors;
        }
        //抽样数量
        if (!item.sampleQuantity){
            errorStr = str + "请输入抽样数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {sampleQuantity: errorStr};
            return errors;
        }
        if (!/^\d{1,19}$/.test(item.sampleQuantity)){
            errorStr = str + "请输入正确的抽样数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {sampleQuantity: errorStr};
            return errors;
        }
        if(item.sampleQuantity > item.goodsArrivalQuantity){
            errorStr = str + "抽样数量不能大于到货的数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {sampleQuantity: errorStr};
            return errors;
        }
        //灭菌批次
        if(item.sterilizationBatch && item.sterilizationBatch.length > 32){
            errorStr = str + "灭菌批次不能大于32个字符";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {sterilizationBatch: errorStr};
            return errors;
        }
        //验收报告
        if(item.acceptanceRep && item.acceptanceRep.length > 32){
            errorStr = str + "验收报告不能大于32个字符";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {acceptanceRep: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

const calcTotalAmount = (itemList, changeFieldValue) => {
    let total = 0;
    for (const i in itemList){
        const item = itemList[i];
        if(DOUBLE_REG.test(item.subTotal)){
            total = parseFloat(total) + parseFloat(item.subTotal);
        }
    }
    if(total){
        changeFieldValue("fastReceiveAddForm", "acceptanceTotalAmount", parseFloat(total).toFixed(2));
    }
};

export const selectField = ({ input, className, label, id, required, items, optionValue, optionName, event, meta: { touched, error } }) => (
     <select id={id} name={input.name} className={"select " + (className || '')} {...input}>
         <option value="">请选择</option>
         {
             items.map((item,index)=>{
                 return (
                     <option key={index} value={item[optionValue]}>{item[optionName]}</option>
                 )
             })
         }
     </select>
);

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

export const batchFields = (fields) => (
    <td className="pink-bg ph-box">
        <select style={{width:"110px"}} {...fields.itemList[fields.index].goodsBatchId.input} name={"itemList["+ fields.index +"].goodsBatchId"} className="td-cont select">
            {
                fields.items.map((item,index)=>{
                    return (
                        <option key={index} value={item.id}>{item.batch}</option>
                    )
                })
            }
            <option style={{display:"none"}} value=""></option>
        </select>
        <span className="index" style={{display: "none"}}>{fields.index}</span>
        <input {...fields.itemList[fields.index].goodsBatch.input} id={"itemList["+ fields.index +"].goodsBatch"} name={"itemList["+ fields.index +"].goodsBatch"} />
    </td>
);

const hiddenField = ({ input, type, id, readOnly, meta: { touched, error } }) => (
    <input id={id} readOnly={readOnly ? readOnly : ''} type={type} {...input} />
);

const tdField = ({ input, disabled, onInput, timeInput, className, type, id, readOnly, meta: { touched, error } }) => (
    <input disabled={disabled || ''} onInput={onInput ? onInput : () => {}} autoComplete="off" id={id} readOnly={readOnly ? readOnly : ''} className={(className || '') + ' td-cont ' + (timeInput || '')} type={type} {...input} />
);

const fieldArray = ({ fields, goodsArrivalQuantityInputEvent, qualifiedQuantityInputEvent, itemList, chineseGoodsStorage,instrumentsStorage,drugStorage, meta: { touched, error } }) => (
    <tbody>
    {
        fields.map((item, index) =>
            <tr key={index}>
                <Field id={"itemList[" + index + '].goodsId'} name={"itemList[" + index + '].goodsId'} type="hidden" component={hiddenField} />
                <td>
                    <input className="td-cont goods-checkbox" style={{width: "inherit", height: "inherit"}} type="checkbox" value={index}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
                </td>
                <Fields index={index} items={itemList[index].goodsBatchList} names={["itemList[" + index + '].goodsBatch', "itemList[" + index + '].goodsBatchId']} component={batchFields} />
                <td className="pink-bg">
                    <Field disabled={itemList[index].goodsBatchId ? "disabled" : ""} className="date" id={"itemList[" + index + '].productionDateString'} timeInput="datepicker" readOnly="readOnly" name={"itemList[" + index + '].productionDateString'} type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field disabled={itemList[index].goodsBatchId ? "disabled" : ""} className="date" id={"itemList[" + index + '].validityString'} timeInput="datepicker" readOnly="readOnly" name={"itemList[" + index + '].validityString'} type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    {itemList[index].goodsTypeCode === "CHINESE_MEDICINE_PIECES" && <Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={chineseGoodsStorage} id={"itemList[" + index + '].storageSpaceId'} name={"itemList[" + index + '].storageSpaceId'} component={selectField} />}
                    {itemList[index].goodsTypeCode === "MEDICAL_INSTRUMENTS" && <Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={instrumentsStorage} id={"itemList[" + index + '].storageSpaceId'} name={"itemList[" + index + '].storageSpaceId'} component={selectField} />}
                    {(itemList[index].goodsTypeCode != "CHINESE_MEDICINE_PIECES" && itemList[index].goodsTypeCode != "MEDICAL_INSTRUMENTS" ) &&<Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={drugStorage} id={"itemList[" + index + '].storageSpaceId'} name={"itemList[" + index + '].storageSpaceId'} component={selectField} />}
                </td>
                <td className="pink-bg">
                    <Field onInput={goodsArrivalQuantityInputEvent} className="goodsArrivalQuantity" id={"itemList[" + index + '].goodsArrivalQuantity'} name={"itemList[" + index + '].goodsArrivalQuantity'} type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field className="purchaseUnitPrice" id={"itemList[" + index + '].purchaseUnitPrice'} name={"itemList[" + index + '].purchaseUnitPrice'} type="text" component={tdField} />
                </td>
                <td>
                    <Field disabled="disabled" id={"itemList[" + index + '].subTotal'} name={"itemList[" + index + '].subTotal'} type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field id={"itemList[" + index + '].retailPrice'} name={"itemList[" + index + '].retailPrice'} type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field onInput={qualifiedQuantityInputEvent} className="qualifiedQuantity" id={"itemList[" + index + '].qualifiedQuantity'} name={"itemList[" + index + '].qualifiedQuantity'} type="text" component={tdField} />
                </td>
                <td>
                    <Field disabled="disabled" id={"itemList[" + index + '].rejectionQuantity'} name={"itemList[" + index + '].rejectionQuantity'} type="text" component={tdField} />
                </td>
                <td>
                    <Field disabled="disabled" id={"itemList[" + index + '].inStorageQuantity'} name={"itemList[" + index + '].inStorageQuantity'} type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field id={"itemList[" + index + '].sampleQuantity'} name={"itemList[" + index + '].sampleQuantity'} type="text" component={tdField} />
                </td>
                <td>
                    <Field id={"itemList[" + index + '].sterilizationBatch'} name={"itemList[" + index + '].sterilizationBatch'} type="text" component={tdField} />
                </td>
                <td>
                    <Field id={"itemList[" + index + '].acceptanceRep'} name={"itemList[" + index + '].acceptanceRep'} type="text" component={tdField} />
                </td>
                <td>
                    <Field disabled="disabled" className="amount" id={"itemList[" + index + '].acceptanceQualifiedAmount'} name={"itemList[" + index + '].acceptanceQualifiedAmount'} type="text" component={tdField} />
                </td>
                <td>
                    <div className="td-cont">{itemList[index].goodsCode}</div>
                </td>
                <td>
                    <div className="td-cont">{itemList[index].goodsNm}</div>
                </td>
                <td>
                    <div className="td-cont">{itemList[index].commonNm}</div>
                </td>
                <td>
                    <div className="td-cont">{itemList[index].spec}</div>
                </td>
                <td>
                    <div className="td-cont">{itemList[index].dosageFormName}</div>
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
            </tr>
        )
    }
    </tbody>
);

class FastReceiveAddForm extends Component {

    componentWillMount(){
        const {change} = this.props;
        this.props.getLoginUserInfo((data) => {
            change("docMaker", data.realName);
            change("acceptanceMan", data.realName);
            change("purchaseMan", data.realName);
            change("receiver", data.realName);
        });
        this.props.actions.loadStorageSpace();

        let now = new Date();
        let str = now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate();
        change("orderCreateTimeString", str);
        change("receiveTimeString", str);
        change("acceptanceTimeString", str);
    }

    componentDidMount() {
        const {change} = this.props;
        const _this = this;
        //批号下拉改变事件
        $(".fast-receive").on("change", ".ph-box .select", function (e) {
            let goodsBatch = $(this).find("option:selected").html();
            let goodsBatchId = $(this).val();
            change($(this).attr("name"), goodsBatchId);
            let inp = $(this).siblings("input");
            if(goodsBatchId){
                const index = $(this).siblings("span.index").html();
                const goodsBatchList = _this.context.store.getState().form.fastReceiveAddForm.values.itemList[index].goodsBatchList;
                goodsBatchList.map(batch => {
                    if(parseInt(goodsBatchId) == batch.id){
                        change("itemList["+ index +"].productionDateString", batch.produceDateString);
                        change("itemList["+ index +"].validityString", batch.validDateString);
                        change("itemList["+ index +"].storageSpaceNm", batch.storageSpaceNm);
                        change("itemList["+ index +"].storageSpaceId", batch.storageSpaceId);
                        return false;
                    }
                });
                inp.val(goodsBatch);
                change(inp.attr("name"), goodsBatch);
            }
        }).on("change", ".ph-box input", function () {
            let val = $(this).val();
            const index = $(this).parents("tr").find("input.goods-checkbox").val();
            let goodsBatchList = _this.context.store.getState().form.fastReceiveAddForm.values.itemList[index].goodsBatchList;
            let batchId;
            goodsBatchList.map(batch => {
                if (batch.batch == val){
                    batchId = batch.id;
                    return false;
                }
            });
            if (batchId){
                $(this).siblings("select").val(batchId).trigger("change");
            }else {
                $(this).siblings("select").val("").trigger("change");
                change("itemList["+ index +"].storageSpaceId", "");
            }
        }).on("click", ".datepicker", function (e) {
            e.stopPropagation();
            const _this = $(this);
            _this.lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change(_this.attr("name"), _this.val());
                }
            });
        }).on("input", "input.purchaseUnitPrice", function (e) {
             let val = $(e.target).val();
            if(DOUBLE_REG.test(val)){
                const index = $(this).parents("tr").find("input.goods-checkbox").val();
                const item = _this.context.store.getState().form.fastReceiveAddForm.values.itemList[index];
                if(LONG_REG.test(item.qualifiedQuantity)){
                    change("itemList["+ index +"].acceptanceQualifiedAmount", (val*item.qualifiedQuantity).toFixed(2));
                }
                if(LONG_REG.test(item.goodsArrivalQuantity)){
                    change("itemList["+ index +"].subTotal", (val*item.goodsArrivalQuantity).toFixed(2));
                }
            }
        }).on("click", ".goods-checkbox", function () {
            $("input.check-all")[0].checked = $(".goods-checkbox").length == $(".goods-checkbox:checked").length
        });

        $(".fast-receive input.check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".goods-checkbox").each(function () {
                $(this)[0].checked = checked;
            });
        });
    }

    componentWillUnmount(){
        $(".fast-receive").unbind();
    }

    addGoodsArrivalQuantityInputEvent(e){
        const {change} = this.props;
        const {store} = this.context;
        let i = $(e.target).parents("tr").find("input.goods-checkbox").val();
        let goodsArrivalQuantity = $(e.target).val();
        const item = store.getState().form.fastReceiveAddForm.values.itemList[i];
        if (LONG_REG.test(goodsArrivalQuantity)){
            change("itemList[" + i + "].inStorageQuantity", goodsArrivalQuantity);
            let qualifiedQuantity = parseInt(goodsArrivalQuantity) - parseInt(item.rejectionQuantity);
            change("itemList[" + i + "].qualifiedQuantity", qualifiedQuantity);
            if (DOUBLE_REG.test(item.purchaseUnitPrice)){
                change("itemList[" + i + "].subTotal", (goodsArrivalQuantity*item.purchaseUnitPrice).toFixed(2));
                change("itemList[" + i + "].acceptanceQualifiedAmount", (qualifiedQuantity*item.purchaseUnitPrice).toFixed(2));
            }
        }
    }

    addQualifiedQuantityInputEvent(e){
        const {change} = this.props;
        const {store} = this.context;
        let i = $(e.target).parents("tr").find("input.goods-checkbox").val();
        let qualifiedQuantity  = $(e.target).val();
        if (/^\d{1,19}$/.test(qualifiedQuantity)){
            const item = store.getState().form.fastReceiveAddForm.values.itemList[i];
            if (DOUBLE_REG.test(item.purchaseUnitPrice)){
                change("itemList[" + i + "].acceptanceQualifiedAmount", (qualifiedQuantity *item.purchaseUnitPrice).toFixed(2));
            }
            change("itemList[" + i + "].rejectionQuantity", parseInt(item.goodsArrivalQuantity) - parseInt(qualifiedQuantity));
        }
    }

    removeGoods(){
        let indexArr = [];
        $(".goods-checkbox:checked").each(function () {
            indexArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        });
        if(indexArr.length > 0){
            const {store} = this.context;
            const itemList = store.getState().form.fastReceiveAddForm.values.itemList;
            this.props.array.removeAll("itemList");
            itemList.map((item, index) => {
                if ($.inArray(index, indexArr) < 0){
                    this.props.array.push("itemList", item);
                }
            });
            $("input.check-all")[0].checked = false;
        }
    }

    supplierSelectedCallback(data){
        const {change} = this.props;
        change("supplierNm", data.supplierNm);
        change("supplierId", data.id);
        change("supplierCode", data.supplierCode);
    }

    goodsSelectedCallback(goodsData){
        if (goodsData.length > 0){
            const {store} = this.context;
            const {actions, array, change} = this.props;
            const values = store.getState().form.fastReceiveAddForm.values;
            let goodsIdArr = [];
            values.itemList.map(item => {
                goodsIdArr.push(item.goodsId);
            });
            goodsData.map((goods, index) => {
                if ($.inArray(goods.id, goodsIdArr) < 0){
                    goods.goodsId = goods.id;
                    goods.goodsBatchList = [];
                    goods.rejectionQuantity = 0;
                    goods.sampleQuantity = 1;
                    array.push("itemList", goods);
                    actions.loadGoodsBatchList(goods.id, (data, goodsId) => {
                        const itemList = store.getState().form.fastReceiveAddForm.values.itemList;
                        itemList.map((item, index) =>{
                            if(item.goodsId == goodsId){
                                itemList[index].goodsBatchList = data;
                                change("itemList", itemList);
                            }
                        });
                    });
                }
            });
        }
    }

    changeApproveManId(data){
        const {change} = this.props;
        change("approveManId", data.id);
        change("approveMan", data.realName);
    }

    render() {
        const {actions, handleSubmit, submitting, checkValidForm, changeCommonGoodsListState, showSupplierComponent, showValidateModel} = this.props;
        const {store} = this.context;
        const {validFormState, errorValidMessage} = store.getState().validTodos;
        const values = store.getState().form.fastReceiveAddForm.values;
        const storageSpaceList = store.getState().todos.storageSpaceList || [];

        const chineseGoodsStorage = storageSpaceList.filter(storage => storage.storageSpaceType === "CHINESE_HERBAL_MEDICINE") || [];
        const instrumentsStorage = storageSpaceList.filter(storage => storage.storageSpaceType === "MEDICAL_APPARATUS_INSTRUMENTS") || [];
        const drugStorage = storageSpaceList.filter(storage => storage.storageSpaceType != "MEDICAL_APPARATUS_INSTRUMENTS" && storage.storageSpaceType != "CHINESE_HERBAL_MEDICINE") || [];

        return (
            <form onSubmit={handleSubmit(data => actions.saveFastReceive(data))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage} checkValidForm={checkValidForm}/>}
                {store.getState().goodsTodos.commonAddGoodsListState && <CommonGoodsList actions={actions}/>}
                {store.getState().supplieComponentTodos.commonAddSupplierListState && <SupplierSelectList actions={actions}/>}
                <ApproveValidateComponent callbackFunc={(data) => this.changeApproveManId(data)}/>
                <div className="layer fast-receive">
                    <div className="layer-box layer-info layer-order w1210">
                        <div className="layer-header">
                            <span>快速收货</span>
                            <a onClick={() => actions.showAdd(null)} href="javascript:;" className="close"/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">单据信息</div>
                                <div className="box-mc clearfix">
                                    <Field type="hidden" className="item" id="approveManId" name="approveManId"  component={hiddenField}/>
                                    <Field id="purchaseMan" name="purchaseMan" type="text" component={inputField}  label="采购人" />
                                    <Field id="supplierId" name="supplierId" type="hidden" component={hiddenField} readOnly="readOnly" />
                                    <Field id="supplierCode" name="supplierCode" type="text" required="required" component={inputField} label="供货单位编码" disabled="disabled" />
                                    <Field onClick={() => showSupplierComponent(true, values.supplierNm, this.supplierSelectedCallback.bind(this))} required="required" id="supplierNm" name="supplierNm" type="text" component={inputSearchField} label="供货单位名称"/>
                                    <Field id="docMaker" name="docMaker" type="text" component={inputField}  label="制单人员"/>
                                    <div className="item"></div>
                                    <Field id="orderCreateTimeString" name="orderCreateTimeString" type="text" component={inputField} readOnly="readOnly" label="订单时间" timeInput="datepicker"/>
                                    <Field id="receiver" name="receiver" type="text" component={inputField}  label="收货人员"/>
                                    <Field id="receiveTimeString" name="receiveTimeString" type="text" component={inputField} readOnly="readOnly" label="收货时间" timeInput="datepicker"/>
                                    <Field id="acceptanceMan" name="acceptanceMan" type="text" component={inputField}  label="验收员"/>
                                    <div className="item"></div>
                                    <Field id="acceptanceTimeString" name="acceptanceTimeString" type="text" component={inputField} readOnly="readOnly" label="验收时间" timeInput="datepicker"/>
                                    <Field disabled="disabled" id="acceptanceTotalAmount" name="acceptanceTotalAmount" type="text" component={inputField}  label="合计总金额（元）"/>
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
                                        <div onClick={() => changeCommonGoodsListState({isSingle: false}, this.goodsSelectedCallback.bind(this))} className="item-add">添加商品</div>
                                        <div onClick={() => this.removeGoods()} className="item-del">删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <table style={{width: "2100px"}}>
                                        <thead>
                                        <tr>
                                            <th style={{width: "25px"}} className="th-checkbox"><label><input style={{width: "inherit"}} className="check-all" type="checkbox" value="checkbox"/></label></th>
                                            <th style={{width: "35px"}} className="serial-number">序号</th>
                                            <th style={{width: "70px"}} className="serial-number">批号</th>
                                            <th className="date">生产日期</th>
                                            <th className="date">有效期至</th>
                                            <th className="goods">货位</th>
                                            <th className="number">到货数量</th>
                                            <th style={{width: "80px"}} className="price">进货单价（元）</th>
                                            <th className="price">小计</th>
                                            <th style={{width: "70px"}} className="price">零售价（元）</th>
                                            <th className="number">合格数量</th>
                                            <th className="number">拒收数量</th>
                                            <th className="number">入库数量</th>
                                            <th className="number">抽样数量</th>
                                            <th className="batch">灭菌批次</th>
                                            <th className="receiving-report">验收报告</th>
                                            <th style={{width: "105px"}} className="price">验收合格金额（元）</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="origin">产地</th>
                                        </tr>
                                        </thead>
                                        {
                                            values.itemList.length == 0 ? <tbody><tr><th style={{textAlign: "center"}} colSpan="26" >暂无数据</th></tr></tbody> : <FieldArray name="itemList" itemList={values.itemList} chineseGoodsStorage={chineseGoodsStorage} instrumentsStorage={instrumentsStorage} drugStorage={drugStorage} goodsArrivalQuantityInputEvent={e => this.addGoodsArrivalQuantityInputEvent(e)} qualifiedQuantityInputEvent={e => this.addQualifiedQuantityInputEvent(e)} component={fieldArray} />
                                        }
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:;" className="cancel" onClick={() => actions.showAdd(null)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

FastReceiveAddForm.propTypex = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

FastReceiveAddForm.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        initialValues: {itemList: [], acceptanceTotalAmount: 0.00},
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        checkValidForm, errorValidMessageFunction,
        getLoginUserInfo, showSupplierComponent,
        changeCommonGoodsListState, changeFieldValue, showValidateModel}, dispatch);
}

FastReceiveAddForm = reduxForm({
    form: 'fastReceiveAddForm',
    validate
})(FastReceiveAddForm);

FastReceiveAddForm = connect(mapStateToProps, mapDispatchToProps)(FastReceiveAddForm);

export default FastReceiveAddForm;
