import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {FieldArray, Field, reduxForm, change as changeFieldValue} from "redux-form";
import {changeCommonGoodsBatchListState} from '../../../../../../common/goodsbatchselectwin/actions'
import CommonGoodsBatchList from '../../../../../../common/goodsbatchselectwin/components/CommonGoodsBatchList'
import {showValidateModel} from '../../../../../../common/approvevalidate/actions'
import ApproveValidateComponent from '../../../../../../common/approvevalidate/components/ApproveValidateComponent'
import {errorValidMessageFunction,checkValidForm} from '../../../../../../common/validForm/actions'
import ValidForm from '../../../../../../common/validForm/components/ValidForm'
import {REGEXP_INT} from "../../../../../../common/common-constant"

export const validate = (values, props) => {
    const errors = {};

    if (!values.moveManName) {
        errors.moveManName = "请输入移动人";
        props.errorValidMessageFunction(errors.moveManName);
        return errors;
    }

    if (!values.moveTimeString) {
        errors.moveTimeString = "请选择移动时间";
        props.errorValidMessageFunction(errors.moveTimeString);
        return errors;
    }

    if(!values.approveDataId){
        errors.approveDataId = "请审核";
        props.errorValidMessageFunction(errors.approveDataId);
        return errors;
    }

    if(values.goodsListLength === undefined || values.goodsListLength === 0){
        errors.goodsListLength= "请选择商品";
        props.errorValidMessageFunction(errors.goodsListLength);
        return errors;
    }

    props.errorValidMessageFunction("");
    errors.goodsList = [];
    if(values.goodsList !== undefined && values.goodsList.length > 0){
        for(const index in values.goodsList){
            const goods = values.goodsList[index];
            const str = "【序号"+ (parseInt(index) + 1) +"】";
            const batchMsgErrors = {};

            if (!goods.targetStorageSpaceId) {
                batchMsgErrors.targetStorageSpaceId = str + "请选择目标货位";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.targetStorageSpaceId);
                return errors;
            }
            if (!goods.quantity) {
                batchMsgErrors.quantity = str +  "请输入移动数量";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.quantity);
                return errors;
            } else if (!REGEXP_INT.test(goods.quantity) || parseFloat(goods.quantity) < 1) {
                batchMsgErrors.quantity = str + "移动数量必须为大于0的整数";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.quantity);
                return errors;
            } else if(goods.quantity > goods.currentStock) {
                batchMsgErrors.quantity = str + "移动数量必须小于等于库存数量";
                errors.goodsList[index] = batchMsgErrors;
                props.errorValidMessageFunction(batchMsgErrors.quantity);
                return errors;
            }
        }
    }
    return errors
};

/*时间输入框*/
export const timeInputField = ({ input, label, type, id, timeInput, required, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required == undefined ? '' : '*'}</i>{label}</p>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required} type={type} {...input} readOnly/>
    </div>
);

export const inputField = ({ input, label, type, className, inputClassName,style,id, required, maxLength, readOnly, meta: { touched, error } }) => (
    <div className={"item " + className}>
        <p>{required && <i>*</i>}{label}</p>
        <input type={type} id={id} name={input.name} className={inputClassName} maxLength={maxLength} readOnly={readOnly} style={style} {...input}/>
    </div>
);

//隐藏域
export const hiddenField = ({input}) => (
    <input type="text" name={input.name} style={{display: "none"}} {...input}/>
);

//货位信息
export const storageSelectField = ({input, allStorageSpace}) => (
    <div className="td-cont" style={{marginRight:"8px"}}>
        <select style={{padding: "5px 0 5px 5px"}} name={input.name} {...input}>
            <option value="">请选择</option>
            {allStorageSpace.map((storageSpace, index) => (
                <option key={index} value={storageSpace.id}>{storageSpace.storageSpaceNm}</option>
            ))}
        </select>
    </div>
);

//移动数量
export const quantityInputField = ({input,style}) => (
    <input type="text" name={input.name} maxLength="19" style={style?style:""} {...input}/>
);

export const goodsArrayField = ({fields, allGoods,chineseGoodsStorage,instrumentsStorage,drugStorage}) => (
    <tbody>
    {
        allGoods.map((goods, index) => {
            return (
            <tr key={index}>
                <Field name={'goodsList[' + index + '].batchId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].goodsId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].skuId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].storageSpaceId'} component={hiddenField}/>
                <td >
                    <input className="goods-box" value={goods.id} type="checkbox" style={{height: "14px"}}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.storageSpaceNm}</div>
                </td>
                <td className="pink-bg">
                    {goods.goodsTypeCode === "CHINESE_MEDICINE_PIECES" && <Field name={'goodsList[' + index + '].targetStorageSpaceId'} component={storageSelectField} allStorageSpace={chineseGoodsStorage}/>}
                    {goods.goodsTypeCode === "MEDICAL_INSTRUMENTS" && <Field name={'goodsList[' + index + '].targetStorageSpaceId'} component={storageSelectField} allStorageSpace={instrumentsStorage}/>}
                    {(goods.goodsTypeCode != "CHINESE_MEDICINE_PIECES" && goods.goodsTypeCode != "MEDICAL_INSTRUMENTS" ) && <Field name={'goodsList[' + index + '].targetStorageSpaceId'} component={storageSelectField} allStorageSpace={drugStorage}/>}
                </td>
                <td>
                    <div className="td-cont">{goods.currentStock}</div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field name={'goodsList[' + index + '].quantity'} component={quantityInputField} style={{height:"26px"}}/>
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
                    <div className="td-cont">{goods.dosageFormName || "暂无"}</div>
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

class StorageSpaceMoveAddForm extends React.Component {

    componentWillMount() {
        this.props.actions.approveValidateData(null);
        this.props.actions.listAllStorageSpace();
        this.props.actions.generateStorageSpaceMoveNum();
    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("moveTimeString", $('input[name="moveTimeString"]').val());
                }
            });
        });

        $(".select-all").click(function () {
            let checked = $(this)[0].checked;
            $("input.goods-box").each(function () {
                $(this)[0].checked = checked;
            });
        });

        $("input.goods-box").click(function () {
            let checked = $(this)[0].checked;
            if(checked){
                $(".select-all")[0].checked = $("input.goods-box:checked").length === $("input.goods-box").length;
            }else {
                $(".select-all")[0].checked = false;
            }
        });

    }

    componentDidUpdate(){

    }

    openCommonGoodsSelectWin() {
        this.props.changeCommonGoodsBatchListState({isSingle:false,isBiggerThanCurrentStock:"Y",batchState:"NORMAL"},(selectedGoodsList) => {
            const {store} = this.context;
            const goodsList = store.getState().form.storageSpaceMoveAddForm.values.goodsList;
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
            changeFieldValue("storageSpaceMoveAddForm", "goodsList", newGoodsList);
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
        if (goodsIdArr.length !== 0){
            const goodsList = store.getState().form.storageSpaceMoveAddForm.values.goodsList;
            let newGoodsList = [];
            goodsList.map(goods => {
                if($.inArray(goods.id, goodsIdArr) < 0){
                    newGoodsList.push(goods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("storageSpaceMoveAddForm", "goodsList", newGoodsList);
        }
        $(".select-all")[0].checked = $("input.goods-box:checked").length !== 0;
    }

    approveValidate(data){
        this.props.change("approveDataId", data.id);
        this.props.actions.approveValidateData(data);
    }

    resetAndClose(){
        this.props.actions.storageSpaceMoveAddModel(false);
        this.props.actions.approveValidateData(null);
    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const {goodsBatchTodos, todos} = this.context.store.getState();
        const commonAddGoodsBatchListState = goodsBatchTodos.commonAddGoodsBatchListState;
        const {approveData} = todos;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const allStorageSpace = todos.allStorageSpace || [];
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.storageSpaceMoveAddForm.values;
        const num = todos.moveNum.moveNum || "";

        const chineseGoodsStorage = allStorageSpace.filter(storage => storage.storageSpaceType === "CHINESE_HERBAL_MEDICINE") || [];
        const instrumentsStorage = allStorageSpace.filter(storage => storage.storageSpaceType === "MEDICAL_APPARATUS_INSTRUMENTS") || [];
        const drugStorage = allStorageSpace.filter(storage =>  storage.storageSpaceType != "MEDICAL_APPARATUS_INSTRUMENTS" && storage.storageSpaceType != "CHINESE_HERBAL_MEDICINE") || [];

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                <ApproveValidateComponent callbackFunc={(data)=>{this.approveValidate(data)}}/>
                <Field name="approveDataId" component={hiddenField}/>
                <Field name="goodsIsEmpty" component={hiddenField}/>
                <div className="layer">
                    <div className="layer-box drug-check w1290">
                        <div className="layer-header">
                            <span>货位移动</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.storageSpaceMoveAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">单据信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>移动单号</p>
                                        <span>{num}</span>
                                    </div>
                                    <div className="item">
                                        <Field id="moveManName" name="moveManName" component={inputField} label="移动人" required="required" type="text"/>
                                    </div>
                                    <div className="item">
                                        <Field id="moveTimeString" name="moveTimeString" timeInput="datepicker" required="required" type="text" component={timeInputField} label="移动时间" readOnly/>
                                    </div>
                                    <div className="item">
                                        <Field name="remark"  component={inputField} label="备注" type="text" style={{width:"460px"}}/>
                                    </div>
                                    <div className="item" style={{"clear": "both"}}>
                                        <p><i>*</i>审核人</p>
                                        {approveData !== null ? <p style={{color: "red"}}>{approveData.realName}</p> : <a href="javascript:void(0);" onClick={()=>{this.props.showValidateModel()}} className="review">点击审核</a>}
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={() => {this.openCommonGoodsSelectWin()}}>添加商品</div>
                                        <div className="item-del" onClick={() => {this.deleteGoods()}}>删除商品</div>
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
                                            <th className="cargo-location">原货位</th>
                                            <th className="cargo-location">目标货位</th>
                                            <th className="number">库存数量</th>
                                            <th className="number">移动数量</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="batch-number">批号</th>
                                            <th className="time" style={{width:"80px"}}>生产日期</th>
                                            <th className="time" style={{width:"80px"}}>有效期至</th>
                                        </tr>
                                        </thead>
                                        {goodsList.length < 1 && <tbody> <tr>  <th colSpan="17" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
                                        <FieldArray name="goodsList" component={goodsArrayField} allGoods={goodsList} chineseGoodsStorage={chineseGoodsStorage} drugStorage={drugStorage} instrumentsStorage={instrumentsStorage}/>
                                        <Field name="goodsListLength" type="hidden" component={hiddenField}/>
                                        <Field name="moveNum" type="hidden" component={hiddenField}/>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => {this.resetAndClose()}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}
StorageSpaceMoveAddForm.contextTypes = {
    store: React.PropTypes.object
};

StorageSpaceMoveAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

StorageSpaceMoveAddForm = reduxForm({
    form: 'storageSpaceMoveAddForm'
})(StorageSpaceMoveAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeCommonGoodsBatchListState,
        showValidateModel,
        checkValidForm,
        errorValidMessageFunction,
        changeFieldValue
    }, dispatch);
}
function mapStateToProps(state) {
    return {
        initialValues:Object.assign({},state.todos.data,{
            goodsList: [],
            moveNum:state.todos.moveNum.moveNum
        }),
        state,
        validate
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(StorageSpaceMoveAddForm);