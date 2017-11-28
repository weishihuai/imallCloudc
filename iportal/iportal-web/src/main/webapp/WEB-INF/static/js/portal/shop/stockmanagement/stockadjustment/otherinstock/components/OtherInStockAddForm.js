import React, {PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {change as changeFieldValue, Field, FieldArray, Fields, reduxForm} from "redux-form";
import {changeCommonGoodsListState} from "../../../../../../common/goodsselectwin/actions";
import CommonGoodsList from "../../../../../../common/goodsselectwin/components/CommonGoodsList";
import {showValidateModel} from "../../../../../../common/approvevalidate/actions";
import ApproveValidateComponent from "../../../../../../common/approvevalidate/components/ApproveValidateComponent";
import SupplierSelectList from "../../../../../../common/supplierSelectwin/components/SupplierSelectList";
import {changeCommonSupplierListState} from "../../../../../../common/supplierSelectwin/actions";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {LONG_REG, REGEXP_PRICE} from "../../../../../../common/common-constant";

export const validate = (values, props) => {
    const goodsList = values.goodsList;
    calRealCheckAmount(goodsList, props.changeFieldValue);
    const errors = {};
    if (!values.inStockTimeString) {
        errors.inStockTimeString = "请选择入库时间";
        props.errorValidMessageFunction(errors.inStockTimeString);
        return errors;
    }

    if (!values.typeCode) {
        errors.typeCode = "请选择入库类型";
        props.errorValidMessageFunction(errors.typeCode);
        return errors;
    }

    if(!values.supplierId){
        props.errorValidMessageFunction("请选择供应商");
        errors.supplierId = "请选择供应商";
        return errors;
    }

    if(!values.approveDataId){
        errors.approveDataId = "请审核";
        props.errorValidMessageFunction(errors.approveDataId);
        return errors;
    }

    if (goodsList.length === 0){
        props.errorValidMessageFunction("请选择商品");
        errors.supplierId = "请选择商品";
        return errors;
    }

    errors.goodsList = [];
    for (const i in goodsList){
        const item = goodsList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";

        if (!item.quantity){
            errorStr = str + "请输入入库数量";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {quantity: errorStr};
            return errors;
        }

        if (!LONG_REG.test(item.quantity)){
            errorStr = str + "请输入正确的入库数量";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {quantity: errorStr};
            return errors;
        }

        if (!item.unitPrice){
            errorStr = str + "请输入单价";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {unitPrice: errorStr};
            return errors;
        }
        if (!REGEXP_PRICE.test(item.unitPrice)){
            errorStr = str + "请输入正确的单价";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {unitPrice: errorStr};
            return errors;
        }

        //批次
        if (!item.goodsBatch){
            errorStr = str + "请输入或选择商品批次";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {goodsBatch: errorStr};
            return errors;
        }
        if (item.goodsBatch.length > 32){
            errorStr = str + "商品批次不能大于32个字符";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {goodsBatch: errorStr};
            return errors;
        }
        if(!item.productionDateString){
            errorStr = str + "请选择生产日期";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {productionDateString: errorStr};
            return errors;
        }
        if(!item.validityString){
            errorStr = str + "请选择有效期";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {validityString: errorStr};
            return errors;
        }
        let start = new Date(item.productionDateString);
        let end = new Date(item.validityString);
        if(end <= start){
            errorStr = str + "有效期必须在生产日期之后";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {validityString: errorStr};
            return errors;
        }

        //货位
        if(!item.goodsBatchId && !item.storageSpaceId){
            errorStr = str + "请选择货位";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {storageSpaceId: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

const calRealCheckAmount = (goodsList,changeFieldValue) => {
    let realCheckAmount = 0;
    for(const i in goodsList) {
        const goods = goodsList[i];
        if (LONG_REG.test(goods.quantity) && REGEXP_PRICE.test(goods.unitPrice)) {
            realCheckAmount = parseFloat(goods.quantity) * parseFloat(goods.unitPrice);
            changeFieldValue("otherInStockAddForm","goodsList[" + i + "].realCheckAmount",parseFloat(realCheckAmount).toFixed(2));
        }
    }
};

/*时间输入框*/
export const timeInputField = ({ input, label, type, id, timeInput, required, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required == undefined ? '' : '*'}</i>{label}</p>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required} type={type} {...input} readOnly/>
    </div>
);

const inputField = ({ input, disabled, label, type, id, timeInput, required, readOnly, onClick, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required ? '*' : ''}</i>{label}</p>
        <input disabled={disabled || ''} onClick={onClick ? onClick : () => {}} id={id} readOnly={readOnly ? readOnly : ''} className={(timeInput || '') + " form-control " + (required || '')} type={type}  {...input} />
    </div>
);

const supplierInputField = ({ input, type,className,maxLength, id,required,readOnly, meta: { touched, error } }) => (
    <input type={type} id={id} style={{paddingRight: "35px", width: "135px"}} readOnly={readOnly ? readOnly : ""} maxLength={maxLength} name={input.name} {...input}/>
);

//隐藏域
export const hiddenField = ({input,readOnly}) => (
    <input type="text" name={input.name} style={{display: "none"}} readOnly={readOnly} {...input}/>
);

export const customInputField = ({input,readOnly,disabled}) => (
    <input type="text" name={input.name} maxLength="19" style={{height:"26px"}} readOnly={readOnly} disabled={disabled} {...input}/>
);

export const batchFields = (fields) => (
    <td style={{position: "relative"}} className="pink-bg ph-box">
        <select style={{width:"110px", padding: "5px 0 5px 5px", marginRight: "5px"}} {...fields.goodsList[fields.index].goodsBatchId.input} name={"goodsList["+ fields.index +"].goodsBatchId"} className="td-cont select">
            {
                fields.items.map((item,index)=>{
                    return (
                        <option key={index} value={item.id}>{item.batch}</option>
                    )
                })
            }
            <option style={{display:"none"}} value=""/>
        </select>
        <span className="index" style={{display: "none"}}>{fields.index}</span>
        <input style={{position: "absolute", marginTop: "1px", left: "10px", width: "85px", border: "none", height:"26px"}} {...fields.goodsList[fields.index].goodsBatch.input} id={"goodsList["+ fields.index +"].goodsBatch"} name={"goodsList["+ fields.index +"].goodsBatch"} />
    </td>
);

const tdField = ({ input, disabled, onInput, timeInput, className,style, type, id, readOnly, meta: { touched, error } }) => (
    <input disabled={disabled || ''} style={style} onInput={onInput ? onInput : () => {}} autoComplete="off" id={id} readOnly={readOnly ? readOnly : ''} className={(className || '') + ' td-cont ' + (timeInput || '')} type={type} {...input} />
);

export const selectField = ({ input, className, label, id, required, items, optionValue, optionName, event, meta: { touched, error } }) => (
    <select style={{padding: "5px 0 5px 5px", marginRight:"5px"}} id={id} name={input.name} className={"select " + (className || '')} {...input}>
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

export const goodsArrayField = ({fields,goodsList, allGoods, chineseGoodsStorage,instrumentsStorage,drugStorage}) => (
    <tbody>
    {
        fields.map((goods, index) => {
            return (
            <tr key={index}>
                <Field id={"goodsList[" + index + '].goodsId'} name={"goodsList[" + index + '].goodsId'} type="hidden" component={hiddenField} />
                <Field name={'goodsList[' + index + '].skuId'} component={hiddenField}/>
                <td>
                    <input className="td-cont goods-checkbox" style={{width: "inherit", height: "inherit"}} type="checkbox" value={index}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field id="quantity" name={'goodsList[' + index + '].quantity'} component={customInputField}/>
                    </div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field id="unitPrice" name={'goodsList[' + index + '].unitPrice'} component={customInputField}/>
                    </div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field disabled="disabled" id="realCheckAmount" name={'goodsList[' + index + '].realCheckAmount'} type="text" component={customInputField}/>
                    </div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].goodsCode}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].goodsNm}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].commonNm}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].spec}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].dosageFormName || "暂无"}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].unit}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].produceManufacturer}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].approvalNumber || "暂无"}</div>
                </td>
                <td>
                    <div className="td-cont">{goodsList[index].currentStock}</div>
                </td>
                <Fields index={index} items={goodsList[index].goodsBatchList} names={["goodsList[" + index + '].goodsBatch', "goodsList[" + index + '].goodsBatchId']} component={batchFields} />
                <td>
                    <Field disabled={goodsList[index].goodsBatchId ? "disabled" : ""} className="date" id={"goodsList[" + index + '].productionDateString'} timeInput="datepicker" readOnly name={"goodsList[" + index + '].productionDateString'} type="text" style={{"width":"70px",marginRight:"5px",height:"26px"}} component={tdField} />
                </td>
                <td>
                    <Field disabled={goodsList[index].goodsBatchId ? "disabled" : ""} className="date" id={"goodsList[" + index + '].validityString'} timeInput="datepicker" readOnly name={"goodsList[" + index + '].validityString'} type="text" style={{"width":"70px",marginRight:"5px",height:"26px"}} component={tdField} />
                </td>
                <td className="pink-bg">
                    {goodsList[index].goodsTypeCode === "CHINESE_MEDICINE_PIECES" && <Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={chineseGoodsStorage} id={"goodsList[" + index + '].storageSpaceId'} name={"goodsList[" + index + '].storageSpaceId'} component={selectField} />}
                    {goodsList[index].goodsTypeCode === "MEDICAL_INSTRUMENTS" && <Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={instrumentsStorage} id={"goodsList[" + index + '].storageSpaceId'} name={"goodsList[" + index + '].storageSpaceId'} component={selectField} />}
                    {(goodsList[index].goodsTypeCode != "CHINESE_MEDICINE_PIECES" && goodsList[index].goodsTypeCode != "MEDICAL_INSTRUMENTS" ) && <Field className="storageSpace" optionName="storageSpaceNm" optionValue="id" items={drugStorage} id={"goodsList[" + index + '].storageSpaceId'} name={"goodsList[" + index + '].storageSpaceId'} component={selectField} />}
                </td>
            </tr>
            )
        })
    }
    </tbody>
);

/*入库类型*/
export const inStockTypeField = ({input, label, type, id, required, items,style, optionName, meta: {touched, error}}) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <select className="select-w" id={id } name={input.name} style={style} {...input}>
            <option value="">入库类型</option>
            <option value="RECEIVE">获赠</option>
            <option value="OVERFLOW">报溢</option>
            <option value="TAKE_BACK">领用退回</option>
            <option value="OTHER">其他</option>
        </select>
    </div>
);

class OtherInStockAddForm extends React.Component {

    componentWillMount() {
        this.props.actions.listAllStorageSpace();
        this.props.actions.findCurrentLoginUserMessage();
        this.props.actions.approveValidateData(null);
    }

    componentDidMount() {
        const {change} = this.props;
        const _this = this;
        //批次下拉改变事件
        $(".fast-receive").on("change", ".ph-box .select", function (e) {
            let goodsBatch = $(this).find("option:selected").html();
            let goodsBatchId = $(this).val();
            change($(this).attr("name"), goodsBatchId);
            let inp = $(this).siblings("input");
            if(goodsBatchId){
                const index = $(this).siblings("span.index").html();
                const goodsBatchList = _this.context.store.getState().form.otherInStockAddForm.values.goodsList[index].goodsBatchList;
                goodsBatchList.map(batch => {
                    if(parseInt(goodsBatchId) === batch.id){
                        change("goodsList["+ index +"].productionDateString", batch.produceDateString);
                        change("goodsList["+ index +"].validityString", batch.validDateString);
                        change("goodsList["+ index +"].storageSpaceNm", batch.storageSpaceNm);
                        change("goodsList["+ index +"].storageSpaceId", batch.storageSpaceId);
                        return false;
                    }
                });
                inp.val(goodsBatch);
                change(inp.attr("name"), goodsBatch);
            }
        }).on("change", ".ph-box input", function () {
            let val = $(this).val();
            const index = $(this).parents("tr").find("input.goods-checkbox").val();
            let goodsBatchList = _this.context.store.getState().form.otherInStockAddForm.values.goodsList[index].goodsBatchList;
            let batchId;
            goodsBatchList.map(batch => {
                if (batch.batch === val){
                    batchId = batch.id;
                    return false;
                }
            });
            if (batchId){
                $(this).siblings("select").val(batchId).trigger("change");
            }else {
                $(this).siblings("select").val("").trigger("change");
                change("goodsList["+ index +"].storageSpaceId", "");
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
        }).on("click", ".goods-checkbox", function () {
            $("input.check-all")[0].checked = $(".goods-checkbox").length === $(".goods-checkbox:checked").length
        });

        $(".fast-receive input.check-all").click(function () {
            let checked = $(this)[0].checked;
            $(".goods-checkbox").each(function () {
                $(this)[0].checked = checked;
            });
        });

    }

    componentDidUpdate(){
    }

    componentWillUnmount(){
        $(".fast-receive").unbind();
    }

    goodsSelectedCallback(goodsData){
        if (goodsData.length > 0){
            const {store} = this.context;
            const {actions, array, change} = this.props;
            const values = store.getState().form.otherInStockAddForm.values;
            let goodsIdArr = [];
            values.goodsList.map(item => {
                goodsIdArr.push(item.goodsId);
            });
            goodsData.map((goods, index) => {
                if ($.inArray(goods.id, goodsIdArr) < 0){
                    goods.goodsId = goods.id;
                    goods.goodsBatchList = [];
                    array.push("goodsList", goods);
                    actions.loadGoodsBatchList(goods.id, (data, goodsId) => {
                        const goodsList = store.getState().form.otherInStockAddForm.values.goodsList;
                        goodsList.map((goods, index) =>{
                            if(goods.goodsId === goodsId){
                                goodsList[index].goodsBatchList = data;
                                change("goodsList", goodsList);
                            }
                        });
                    });
                }
            });
        }
    }

    deleteGoods() {
        let indexArr = [];
        $(".goods-checkbox:checked").each(function () {
            indexArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        });
        if(indexArr.length > 0){
            const {store} = this.context;
            const goodsList = store.getState().form.otherInStockAddForm.values.goodsList;
            this.props.array.removeAll("goodsList");
            goodsList.map((goods, index) => {
                if ($.inArray(index, indexArr) < 0){
                    this.props.array.push("goodsList", goods);
                }
            });
            $("input.check-all")[0].checked = false;
        }
    }

    approveValidate(data){
        this.props.change("approveDataId", data.id);
        this.props.actions.approveValidateData(data);
    }

    resetAndClose(){
        this.props.actions.otherInStockAddModel(false);
        this.props.actions.approveValidateData(null);
    }

    supplierSelectedCallback(data){
        const {change} = this.props;
        change("supplierNm", data.supplierNm);
        change("supplierId", data.id);
    }

    render() {
        const {submitting, handleSubmit,changeCommonGoodsListState,changeCommonSupplierListState} = this.props;
        const {goodsTodos, todos} = this.context.store.getState();
        const commonAddGoodsListState = goodsTodos.commonAddGoodsListState;
        const {approveData} = todos;
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.otherInStockAddForm.values;
        const  realName = todos.loginUserMessage.realName || "";
        const  allStorageSpace = todos.allStorageSpace || [];
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

        const chineseGoodsStorage = allStorageSpace.filter(storage => storage.storageSpaceType === "CHINESE_HERBAL_MEDICINE") || [];
        const instrumentsStorage = allStorageSpace.filter(storage => storage.storageSpaceType === "MEDICAL_APPARATUS_INSTRUMENTS") || [];
        const drugStorage = allStorageSpace.filter(storage => storage.storageSpaceType != "MEDICAL_APPARATUS_INSTRUMENTS" && storage.storageSpaceType != "CHINESE_HERBAL_MEDICINE") || [];

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <ApproveValidateComponent callbackFunc={(data)=>{this.approveValidate(data)}}/>
                {commonAddGoodsListState && <CommonGoodsList store={store} actions={actions}/>}
                {store.getState().supplieComponentTodos.commonAddSupplierListState && <SupplierSelectList actions={actions}/>}
                <Field name="approveDataId" component={hiddenField}/>
                <Field name="goodsIsEmpty" component={hiddenField}/>
                <Field name="operationManId" component={hiddenField}/>
                <Field name="supplierId" component={hiddenField}/>
                <div className="layer fast-receive">
                    <div className="layer-box drug-check w1290">
                        <div className="layer-header">
                            <span>其他入库</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.otherInStockAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">入库信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>入库人</p>
                                        <span>{realName}</span>
                                    </div>
                                    <div className="item">
                                        <Field id="inStockTimeString" name="inStockTimeString" timeInput="datepicker" required="required" type="text" component={timeInputField} label="入库时间" readOnly/>
                                    </div>
                                    <Field name="typeCode" id="typeCode" label="类型" required="required" style={{"marginTop":"0px"}} component={inStockTypeField}/>
                                    <div className="item" style={{position:"relative"}}>
                                        <p><i>*</i>供货单位名称<a className="item-input-a" onClick={() => changeCommonSupplierListState(true, this.supplierSelectedCallback.bind(this))}/></p>
                                        <Field id="supplierNm" name="supplierNm" type="text" component={supplierInputField}  label="供货单位名称" readOnly="readOnly"/>
                                    </div>
                                    <div className="item" style={{"clear": "both"}}>
                                        <p><i>*</i>审核人</p>
                                        {approveData !== null ? <p style={{color:"red"}}>{approveData.realName}</p> : <a href="javascript:void(0);" className="review" onClick={()=>this.props.showValidateModel()}>点击审核</a>}
                                    </div>
                                    <div className="item">
                                        <Field name="remark" component={inputField} label="备注" type="text"/>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div onClick={() => changeCommonGoodsListState({isSingle:false}, this.goodsSelectedCallback.bind(this))} className="item-add">添加商品</div>
                                        <div onClick={() => this.deleteGoods()} className="item-del">删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <table className="w1210">
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" style={{paddingRight:"5px"}}>
                                                <input style={{width: "inherit"}} className="check-all" type="checkbox" value="checkbox"/>
                                            </th>
                                            <th className="serial-number" style={{width:"40px"}}>序号</th>
                                            <th className="number">入库数量</th>
                                            <th className="price">单价</th>
                                            <th className="price">金额</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="number">库存</th>
                                            <th style={{width: "85px"}} className="serial-number">批次</th>
                                            <th className="time" style={{width: "60px"}}>生产日期</th>
                                            <th className="time" style={{width: "60px"}}>有效期至</th>
                                            <th className="cargo-location">货位</th>
                                        </tr>
                                        </thead>
                                        {goodsList.length < 1 && <tbody> <tr>  <th colSpan="18" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
                                        <FieldArray goodsList={goodsList} name="goodsList" component={goodsArrayField} chineseGoodsStorage={chineseGoodsStorage} instrumentsStorage={instrumentsStorage} drugStorage={drugStorage} allGoods={goodsList}/>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={()=>{checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>actions.otherInStockAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );

        const _this = this;
    }
}
OtherInStockAddForm.contextTypes = {
    store: React.PropTypes.object
};

OtherInStockAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

OtherInStockAddForm = reduxForm({
    form: 'otherInStockAddForm'
})(OtherInStockAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeCommonGoodsListState,
        showValidateModel,
        changeFieldValue,
        changeCommonSupplierListState,
        checkValidForm,
        errorValidMessageFunction,
    }, dispatch);
}
function mapStateToProps(state) {
    return {
        initialValues:{goodsList: []},
        state,
        validate
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(OtherInStockAddForm);