import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {FieldArray, Field, reduxForm, change as changeFieldValue} from "redux-form";
import {changeCommonGoodsBatchListState} from '../../../../../../common/goodsbatchselectwin/actions'
import CommonGoodsBatchList from '../../../../../../common/goodsbatchselectwin/components/CommonGoodsBatchList'
import {showValidateModel} from '../../../../../../common/approvevalidate/actions'
import ApproveValidateComponent from '../../../../../../common/approvevalidate/components/ApproveValidateComponent'
import {inputField} from '../../../../../../common/redux-form-ext';
import {errorValidMessageFunction,checkValidForm} from '../../../../../../common/validForm/actions'
import ValidForm from '../../../../../../common/validForm/components/ValidForm'
import {REGEXP_INT,REGEXP_PRICE} from "../../../../../../common/common-constant"

export const validate = (values, props) => {
    const goodsList = values.goodsList;
    calRealCheckAmount(goodsList,props.changeFieldValue);
    const errors = {};
    if (!values.outStockTimeString) {
        errors.outStockTimeString = "请选择出库时间";
        props.errorValidMessageFunction(errors.outStockTimeString);
        return errors;
    }

    if (!values.typeCode) {
        errors.typeCode = "请选择出库类型";
        props.errorValidMessageFunction(errors.typeCode);
        return errors;
    }

    if(!values.approveDataId){
        errors.approveDataId = "请审核";
        props.errorValidMessageFunction(errors.approveDataId);
        return errors;
    }

    if(values.goodsListLength == undefined || values.goodsListLength == 0){
        errors.goodsListLength= "请选择商品";
        props.errorValidMessageFunction(errors.goodsListLength);
        return errors;
    }

    props.errorValidMessageFunction("");
    errors.goodsList = [];
    if(values.goodsList != undefined && values.goodsList.length > 0){
        for(const index in values.goodsList){
            const goods = values.goodsList[index];
            const str = "【序号"+ (parseInt(index) + 1) +"】";

            const batchMsgErrors = {};
            if (!goods.quantity) {
                batchMsgErrors.quantity = str + "请输入出库数量";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.quantity);
                return errors;
            } else if (!REGEXP_INT.test(goods.quantity) || parseFloat(goods.quantity) < 1 || parseFloat(goods.quantity) > 99999999) {
                batchMsgErrors.quantity = str + "出库数量必须为1~99999999之间的整数";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.quantity);
                return errors;
            }else if(goods.quantity > goods.currentStock) {
                batchMsgErrors.quantity = str + "出库数量必须小于等于库存数量";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.quantity);
                return errors;
            }

            if (!goods.unitPrice) {
                batchMsgErrors.unitPrice = str + "请输入单价";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.unitPrice);
                return errors;
            } else if (!REGEXP_PRICE.test(goods.unitPrice)) {
                batchMsgErrors.unitPrice = str + "单价格式不正确";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.unitPrice);
                return errors;
            } else if(parseFloat(goods.unitPrice) < 0 || parseFloat(goods.unitPrice) > 99999999){
                batchMsgErrors.unitPrice = str + "单价取值范围0~99999999";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.unitPrice);
                return errors;
            }
        }
    }

    return errors
};

const calRealCheckAmount = (goodsList,changeFieldValue) => {
  let realCheckAmount = 0;
  for(const i in goodsList) {
      const goods = goodsList[i];
      if (REGEXP_INT.test(goods.quantity) && REGEXP_PRICE.test(goods.unitPrice)) {
          realCheckAmount = parseFloat(goods.quantity) * parseFloat(goods.unitPrice);
          changeFieldValue("otherOutStockAddForm","goodsList[" + i + "].realCheckAmount",parseFloat(realCheckAmount).toFixed(2));
      }
  }
};

/*时间输入框*/
export const timeInputField = ({ input, label, type, id, timeInput, required, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required === undefined ? '' : '*'}</i>{label}</p>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required} type={type} {...input} readOnly/>
    </div>
);

//隐藏域
export const hiddenField = ({input,readOnly}) => (
    <input type="text" name={input.name} style={{display: "none"}} readOnly={readOnly} {...input}/>
);

//出库数量
export const quantityInputField = ({input,readOnly,disabled,style}) => (
    <input type="text" name={input.name} maxLength="19" readOnly={readOnly} style={style?style:""} disabled={disabled} {...input}/>
);

export const goodsArrayField = ({fields, allGoods, allStorageSpace}) => (
    <tbody>
    {
        allGoods.map((goods, index) => {
            return (
            <tr key={index}>
                <Field name={'goodsList[' + index + '].batchId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].goodsId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].skuId'} component={hiddenField}/>
                <td >
                    <input className="goods-box" value={goods.id} type="checkbox" style={{height: "14px"}}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field id="quantity" name={'goodsList[' + index + '].quantity'} component={quantityInputField} style={{height:"26px"}}/>
                    </div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field id="unitPrice" name={'goodsList[' + index + '].unitPrice'} component={quantityInputField} style={{height:"26px"}}/>
                    </div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field disabled="disabled"  id="realCheckAmount" name={'goodsList[' + index + '].realCheckAmount'} type="text" component={quantityInputField}  style={{height:"26px"}}/>
                    </div>
                </td>
                <td>
                    <div className="td-cont">{goods.goodsCode}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.goodsNm}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.commonNm}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.spec}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.dosageFormName}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.unit}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.produceManufacturer}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.approvalNumber || "暂无"}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.currentStock}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.storageSpaceNm}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.batch}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.produceDateString}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.validDateString}</div>
                </td>
            </tr>
            )
        })
    }
    </tbody>
);

/*出库类型*/
export const outStockTypeField = ({input, label, type, id, required,style, items, optionName, meta: {touched, error}}) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
            <select className="select-w" id={id } style={style} name={input.name} {...input}>
                <option value="">出库类型</option>
                <option value="REPORTED_LOSS">报损</option>
                <option value="INTERNAL_USE">内部领用</option>
                <option value="CHECK_OUT">抽检出库</option>
                <option value="OTHER">其他</option>
            </select>
    </div>
);

class OtherOutStockAddForm extends React.Component {

    componentWillMount() {
        this.props.actions.findCurrentLoginUserMessage();
        this.props.actions.approveValidateData(null);
    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("outStockTimeString", $('input[name="outStockTimeString"]').val());
                }
            });
        });
    }

    componentDidUpdate(){
        $(".select-all").click(function () {
            let checked = $(this)[0].checked;
            $("input.goods-box").each(function () {
                $(this)[0].checked = checked;
            });
        });

        $("input.goods-box").click(function () {
            let checked = $(this)[0].checked;
            if(checked){
                $(".select-all")[0].checked = $("input.goods-box:checked").length == $("input.goods-box").length;
            }else {
                $(".select-all")[0].checked = false;
            }
        });

    }

    openCommonGoodsSelectWin() {
        this.props.changeCommonGoodsBatchListState({isSingle:false,isBiggerThanCurrentStock:"Y",isAllowOutStock:"Y"}, (selectedGoodsList) => {
            const {store} = this.context;
            const goodsList = store.getState().form.otherOutStockAddForm.values.goodsList;
            let goodsIdArr = [];
            goodsList.map(goods => {
                goodsIdArr.push(goods.id);
            });
            let newGoodsList = goodsList;
            selectedGoodsList.map(goods =>{
                if ($.inArray(goods.id, goodsIdArr) < 0){
                    const newGoods=Object.assign({},goods,{
                        batchId:goods.id
                    });
                    newGoodsList.push(newGoods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("otherOutStockAddForm", "goodsList", newGoodsList);
            $(".select-all")[0].checked = false;
        });
    }

    deleteGoods() {
        const {store} = this.context;
        const {changeFieldValue} = this.props;
        let goodsIdArr = [];
        $("input.goods-box:checked").each(function () {
            goodsIdArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        });
        if (goodsIdArr.length != 0){
            const goodsList = store.getState().form.otherOutStockAddForm.values.goodsList;
            let newGoodsList = [];
            goodsList.map(goods => {
                if($.inArray(goods.id, goodsIdArr) < 0){
                    newGoodsList.push(goods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("otherOutStockAddForm", "goodsList", newGoodsList);
        }
    }

    approveValidate(data){
        this.props.change("approveDataId", data.id);
        this.props.actions.approveValidateData(data);
    }

    resetAndClose(){
        this.props.actions.otherOutStockAddModel(false);
        this.props.actions.approveValidateData(null);
    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const {goodsBatchTodos, todos} = this.context.store.getState();
        const commonAddGoodsBatchListState = goodsBatchTodos.commonAddGoodsBatchListState;
        const {approveData} = todos;
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.otherOutStockAddForm.values;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const  realName = todos.loginUserMessage.realName || "";

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                <ApproveValidateComponent callbackFunc={(data)=>{this.approveValidate(data)}}/>
                <Field name="approveDataId" component={hiddenField}/>
                <Field name="goodsIsEmpty" component={hiddenField}/>
                <Field name="operationManId" component={hiddenField}/>
                <div className="layer">
                    <div className="layer-box drug-check w1290">
                        <div className="layer-header">
                            <span>其他出库</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>actions.otherOutStockAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">出库信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>出库人</p>
                                        <span>{realName}</span>
                                    </div>
                                    <div className="item">
                                        <Field id="outStockTimeString" name="outStockTimeString" timeInput="datepicker" required="required" type="text" component={timeInputField} label="出库时间" readOnly/>
                                    </div>
                                        <Field name="typeCode" id="typeCode" label="类型" required="required" style={{marginTop:"0px"}} component={outStockTypeField}/>
                                    <div className="item" style={{"clear": "both"}}>
                                        <p><i>*</i>审核人</p>
                                        {approveData != null ? <p style={{color:"red"}}>{approveData.realName}</p>:<a href="javascript:void(0);" className="review" onClick={()=>this.props.showValidateModel()}>点击审核</a>}
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
                                        <div className="item-add" onClick={()=>this.openCommonGoodsSelectWin()}>添加商品</div>
                                        <div className="item-del" onClick={()=>this.deleteGoods()}>删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <table className="w1210">
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" >
                                                <input className="select-all" type="checkbox"  style={{height: "14px"}}/>
                                            </th>
                                            <th className="serial-number">序号</th>
                                            <th className="number">出库数量</th>
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
                                            <th className="cargo-location">货位</th>
                                            <th className="batch-number">批次</th>
                                            <th className="time" style={{width:"80px"}}>生产日期</th>
                                            <th className="time" style={{width:"80px"}}>有效期至</th>
                                        </tr>
                                        </thead>
                                        {goodsList.length < 1 && <tbody> <tr>  <th colSpan="18" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
                                        <FieldArray name="goodsList" component={goodsArrayField}   allGoods={goodsList}/>
                                        <Field name="goodsListLength" type="hidden" component={hiddenField}/>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button  className="confirm" style={{border:"none"}} disabled={submitting} onClick={()=>{checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.resetAndClose()}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}
OtherOutStockAddForm.contextTypes = {
    store: React.PropTypes.object
};

OtherOutStockAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

OtherOutStockAddForm = reduxForm({
    form: 'otherOutStockAddForm'
})(OtherOutStockAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeCommonGoodsBatchListState,
        showValidateModel,
        changeFieldValue,
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
export default connect(mapStateToProps, mapDispatchToProps)(OtherOutStockAddForm);