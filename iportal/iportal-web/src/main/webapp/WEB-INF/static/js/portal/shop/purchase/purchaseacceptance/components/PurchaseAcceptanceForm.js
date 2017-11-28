import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {Field, Fields, FieldArray, reduxForm} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {getLoginUserInfo} from "../../../../../common/common-actions";
import {DOUBLE_REG, LONG_REG} from "../../../../../common/common-constant";
import ApproveValidateComponent from "../../../../../common/approvevalidate/components/ApproveValidateComponent";
import {showValidateModel} from "../../../../../common/approvevalidate/actions";

export const validate = (values, props) => {
    const errors = {};
    const itemList = values.itemList;
    if (!values.approveManId){
        props.errorValidMessageFunction("请审核");
        errors.approveManId = "请审核";
        return errors;
    }
    if (itemList.length == 0){
        props.errorValidMessageFunction("没有可验收的商品");
        errors.approveManId = "没有可验收的商品";
        return errors;
    }
    errors.itemList = [];
    for (const i in itemList){
        const item = itemList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";
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
        if(item.goodsArrivalQuantity > item.receiveQuantity){
            errorStr = str + "到货数量不能大于采购收货时的数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {goodsArrivalQuantity: errorStr};
            return errors;
        }
        //合格数量
        if (!item.qualifiedQuantity){
            errorStr = str + "请输入合格数量";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {qualifiedQuantity: errorStr};
            return errors;
        }
        if (!/^\d{1,19}$/.test(item.qualifiedQuantity)){
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
        //验收结论
        if(item.acceptanceConclusion && item.acceptanceConclusion.length > 32){
            errorStr = str + "验收结论不能大于128个字符";
            props.errorValidMessageFunction(errorStr);
            errors.itemList[i] = {acceptanceConclusion: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
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

const inputField = ({ input, label, type, id, timeInput, required, readOnly, disabled, onClick, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required ? '*' : ''}</i>{label}</p>
        <input disabled={disabled || ''} onClick={onClick ? onClick : () => {}} id={id} readOnly={readOnly ? readOnly : ''} className={(timeInput || '') + " form-control " + (required || '')} type={type}  {...input} />
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

const fieldArray = ({ fields, goodsArrivalQuantityInputEvent, qualifiedQuantityInputEvent, itemList, meta: { touched, error } }) => (
    <tbody>
    {
        fields.map((item, index) =>
            <tr key={index}>
                <Field id={"itemList[" + index + '].purchaseReceiveRecordItemId'} name={"itemList[" + index + '].purchaseReceiveRecordItemId'} type="hidden" component={hiddenField} />
                <td>
                    <input className="td-cont goods-checkbox" style={{width: "inherit", height: "inherit"}} type="checkbox" value={index}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
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
                <td className="pink-bg">
                    <Field onInput={goodsArrivalQuantityInputEvent} className="goodsArrivalQuantity" id={"itemList[" + index + '].goodsArrivalQuantity'} name={"itemList[" + index + '].goodsArrivalQuantity'} type="text" component={tdField} />
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
                    <Field disabled="disabled" id={"itemList[" + index + '].purchaseUnitPrice'} name={"itemList[" + index + '].purchaseUnitPrice'} type="text" component={tdField} />
                </td>
                <td>
                    <Field className="amount" id={"itemList[" + index + '].acceptanceQualifiedAmount'} disabled="disabled" name={"itemList[" + index + '].acceptanceQualifiedAmount'} type="text" component={tdField} />
                </td>
                <td>
                    <Field disabled="disabled" className="amount" id={"itemList[" + index + '].totalAmount'} name={"itemList[" + index + '].totalAmount'} type="text" component={tdField} />
                </td>
                <td>
                    <Field id={"itemList[" + index + '].retailPrice'} name={"itemList[" + index + '].retailPrice'} type="text" component={tdField} />
                </td>
                <Fields index={index} items={itemList[index].goodsBatchList} names={["itemList[" + index + '].goodsBatch', "itemList[" + index + '].goodsBatchId']} component={batchFields} />
                <td className="pink-bg">
                    <Field disabled={itemList[index].goodsBatchId ? "disabled" : ""} className="date" id={"itemList[" + index + '].productionDateString'} timeInput="datepicker" name={"itemList[" + index + '].productionDateString'} readOnly="readOnly" type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field disabled={itemList[index].goodsBatchId ? "disabled" : ""} className="date" id={"itemList[" + index + '].validityString'} timeInput="datepicker" name={"itemList[" + index + '].validityString'} readOnly="readOnly" type="text" component={tdField} />
                </td>
                <td className="pink-bg">
                    <Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={itemList[index].storageSpaceList} id={"itemList[" + index + '].storageSpaceId'} name={"itemList[" + index + '].storageSpaceId'} component={selectField} />
                </td>
                <td>
                    <Field id={"itemList[" + index + '].acceptanceConclusion'} name={"itemList[" + index + '].acceptanceConclusion'} type="text" component={tdField} />
                </td>
            </tr>
        )
    }
    </tbody>
);

class PurchaseAcceptanceForm extends Component {

    componentWillMount(){
        const {change} = this.props;
        this.props.getLoginUserInfo((data) => {
            change("docMaker", data.realName);
            change("acceptanceMan", data.realName);
        });
    }

    componentDidMount() {
        const {change} = this.props;
        const _this = this;
        //批号下拉改变事件
        $(".purchase-acceptance").on("change", ".ph-box .select", function (e) {
            let goodsBatch = $(this).find("option:selected").html();
            let goodsBatchId = $(this).val();
            change($(this).attr("name"), goodsBatchId);
            let inp = $(this).siblings("input");
            if(goodsBatchId){
                const index = $(this).siblings("span.index").html();
                const goodsBatchList = _this.context.store.getState().form.purchaseAcceptanceForm.values.itemList[index].goodsBatchList;
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
            const index = $(this).parents("tr").find(".ph-box span.index").html();
            let goodsBatchList = _this.context.store.getState().form.purchaseAcceptanceForm.values.itemList[index].goodsBatchList;
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
        }).on("click", ".goods-checkbox", function () {
            if($(this)[0].checked){
                $(".goods-checkbox").each(function () {
                    $(this)[0].checked = false;
                });
                $(this)[0].checked = true;
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
        });

        const values = _this.context.store.getState().form.purchaseAcceptanceForm.values;
        change("purchaseReceiveRecordId", values.id);
        values.itemList.map((item, index) => {
            let receiveQuantity = item.receiveQuantity;
            let purchaseUnitPrice = item.purchaseUnitPrice;
            let acceptanceQualifiedAmount = parseFloat(receiveQuantity*purchaseUnitPrice).toFixed(2);
            change("itemList["+ index + "].goodsArrivalQuantity", receiveQuantity);
            change("itemList["+ index + "].qualifiedQuantity", receiveQuantity);
            change("itemList["+ index + "].inStorageQuantity", receiveQuantity);
            change("itemList["+ index + "].sampleQuantity", 1);
            change("itemList["+ index + "].rejectionQuantity", 0);
            change("itemList["+ index + "].acceptanceQualifiedAmount", acceptanceQualifiedAmount);
            change("itemList["+ index + "].totalAmount", acceptanceQualifiedAmount);
            change("itemList["+ index + "].purchaseReceiveRecordItemId", item.id);
        });

        $(".ph-box .select").trigger("change");
    }

    componentWillUnmount(){
        $(".purchase-acceptance").unbind();
    }

    addGoodsArrivalQuantityInputEvent(e){
        const {change} = this.props;
        const {store} = this.context;
        let i = $(e.target).parents("tr").find("input.goods-checkbox").val();
        let goodsArrivalQuantity = $(e.target).val();
        const item = store.getState().form.purchaseAcceptanceForm.values.itemList[i];
        if (LONG_REG.test(goodsArrivalQuantity)){
            let acceptanceQualifiedAmount = (goodsArrivalQuantity*item.purchaseUnitPrice).toFixed(2);
            change("itemList[" + i + "].qualifiedQuantity", goodsArrivalQuantity);
            change("itemList[" + i + "].inStorageQuantity", goodsArrivalQuantity);
            change("itemList[" + i + "].acceptanceQualifiedAmount", acceptanceQualifiedAmount);
            change("itemList[" + i + "].totalAmount", acceptanceQualifiedAmount);
            }
    }

    addQualifiedQuantityInputEvent(e){
        const {change} = this.props;
        const {store} = this.context;
        let i = $(e.target).parents("tr").find("input.goods-checkbox").val();
        let qualifiedQuantity  = $(e.target).val();
        if (/^\d{1,19}$/.test(qualifiedQuantity)){
            const item = store.getState().form.purchaseAcceptanceForm.values.itemList[i];
            let acceptanceQualifiedAmount = (qualifiedQuantity *item.purchaseUnitPrice).toFixed(2);
            change("itemList[" + i + "].acceptanceQualifiedAmount", acceptanceQualifiedAmount);
            change("itemList[" + i + "].rejectionQuantity", parseInt(item.goodsArrivalQuantity) - parseInt(qualifiedQuantity));
        }
    }

    removeGoods(){
        const _this = this;
        $(".goods-checkbox:checked").each(function () {
            _this.props.array.remove("itemList", parseInt($(this).val()));
            $(this)[0].checked = false;
        });
    }

    addBatch(){
        let selectIndex;
        $(".goods-checkbox:checked").each(function () {
            selectIndex = parseInt($(this).val());
        });
        if (selectIndex != undefined){
            const {store} = this.context;
            const itemList = store.getState().form.purchaseAcceptanceForm.values.itemList;
            this.props.array.push("itemList", itemList[selectIndex]);
        }
    }

    changeApproveManId(data){
        const {change} = this.props;
        change("approveManId", data.id);
        change("approveMan", data.realName);
    }

    render() {
        const {actions, handleSubmit, submitting, checkValidForm, showValidateModel} = this.props;
        const {store} = this.context;
        const {validFormState, errorValidMessage} = store.getState().validTodos;
        const values = store.getState().form.purchaseAcceptanceForm.values;
        const  approveMan =  values.approveMan||"";
        return (
            <form onSubmit={handleSubmit(data => actions.savePurchaseAcceptance(data))}>
                {validFormState && errorValidMessage && <ValidForm errorValidMessage={errorValidMessage} checkValidForm={checkValidForm}/>}
                <ApproveValidateComponent callbackFunc={(data) => this.changeApproveManId(data)}/>
                <div className="layer purchase-acceptance">
                    <div className="layer-box layer-info layer-acceptance w1210">
                        <div className="layer-header">
                            <span>采购验收</span>
                            <a href="javascript:;" onClick={() => actions.showPurchaseAcceptance()} className="close"></a>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">验收信息</div>
                                <div className="box-mc clearfix">
                                    <Field type="hidden" className="item" id="purchaseReceiveRecordId" name="purchaseReceiveRecordId"  component={hiddenField}/>
                                    <Field type="hidden" className="item" id="approveManId" name="approveManId"  component={hiddenField}/>
                                    <Field type="text" className="item" id="docMaker" name="docMaker"  component={inputField} label="制单人" />
                                    <Field type="text" className="item" id="supplierCode" name="supplierCode" disabled="disabled" component={inputField} label="供货单位编码" />
                                    <Field type="text" className="item" id="supplierNm" name="supplierNm" disabled="disabled" component={inputField} label="供货单位名称" />
                                    <Field type="text" className="item" id="acceptanceTimeString" name="acceptanceTimeString" readOnly="readOnly" timeInput="datepicker"  component={inputField} label="验收时间" />
                                    <Field type="text" className="item" id="acceptanceMan" name="acceptanceMan" disabled="disabled" component={inputField} label="验收员" />
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        {
                                            approveMan ? <span style={{color: "red"}}>{approveMan}</span> :
                                                <a href="javascript:;" onClick={() => showValidateModel()} className="review">点击审核</a>
                                        }
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div style={{minHeight: "30px"}} className="item">
                                        <div onClick={() => this.addBatch()} className="item-add">添加新批次</div>
                                        <div style={{marginLeft: "15px"}} onClick={() => this.removeGoods()} className="item-add">删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <table style={{width: "2240px"}}>
                                        <thead>
                                        <tr>
                                            <th style={{"width": "25px"}} className="th-checkbox"></th>
                                            <th style={{width: "35px"}} className="serial-number">序号</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="origin">产地</th>
                                            <th className="number">到货数量</th>
                                            <th className="number">合格数量</th>
                                            <th className="number">拒收数量</th>
                                            <th className="number">入库数量</th>
                                            <th className="number">抽样数量</th>
                                            <th className="batch">灭菌批次</th>
                                            <th className="receiving-report">验收报告</th>
                                            <th style={{width: "90px"}} className="number">进货单价（元）</th>
                                            <th style={{width: "110px"}} className="price">验收合格金额（元）</th>
                                            <th className="price">总金额（元）</th>
                                            <th style={{width: "75px"}} className="number">零售价（元）</th>
                                            <th className="serial-number">商品批号</th>
                                            <th className="date">生产日期</th>
                                            <th className="date">有效期至</th>
                                            <th className="goods" style={{width: "120px"}}>货位</th>
                                            <th className="a-conclusion">验收结论</th>
                                        </tr>
                                        </thead>
                                        <FieldArray name="itemList" goodsArrivalQuantityInputEvent={e => this.addGoodsArrivalQuantityInputEvent(e)} qualifiedQuantityInputEvent={e =>this.addQualifiedQuantityInputEvent(e)} itemList={values.itemList}   component={fieldArray}/>
                                    </table>
                                </div>
                            </div>

                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:;" className="cancel" onClick={() => actions.showPurchaseAcceptance()}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}
PurchaseAcceptanceForm.propTypex = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

PurchaseAcceptanceForm.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.receiveRecord,
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        checkValidForm, errorValidMessageFunction,
        getLoginUserInfo, showValidateModel}, dispatch);
}

PurchaseAcceptanceForm = reduxForm({
    form: 'purchaseAcceptanceForm',
    validate
})(PurchaseAcceptanceForm);

PurchaseAcceptanceForm = connect(mapStateToProps, mapDispatchToProps)(PurchaseAcceptanceForm);

export default PurchaseAcceptanceForm;
