/**
 * Created by ou on 2017/7/20.
 */
import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {FieldArray, Field, reduxForm} from "redux-form";
import {changeCommonGoodsBatchListState} from '../../../../../../common/goodsbatchselectwin/actions'
import CommonGoodsBatchList from '../../../../../../common/goodsbatchselectwin/components/CommonGoodsBatchList'
import {showValidateModel} from '../../../../../../common/approvevalidate/actions'
import ApproveValidateComponent from '../../../../../../common/approvevalidate/components/ApproveValidateComponent'
import {initValidForm, errorValidMessageFunction,checkValidForm} from '../../../../../../common/validForm/actions'
import ValidForm from '../../../../../../common/validForm/components/ValidForm'
import {REGEXP_INT} from "../../../../../../common/common-constant"


export const validate = (values, props) => {

    const errors = {};
    if(!values.inBucketTimeString){
        errors.inBucketTimeString = "装斗时间必填";
        props.errorValidMessageFunction(errors.inBucketTimeString);
        return errors;
    }
    if(!values.approveDataId){
        errors.approveDataId = "请审核";
        props.errorValidMessageFunction(errors.approveDataId);
        return errors;
    }
    if(values.goodsList.length == 0){
        errors.goodsIsEmpty= "请选择商品";
        props.errorValidMessageFunction(errors.goodsIsEmpty);
        return errors;
    }
    props.errorValidMessageFunction("");
    errors.goodsList = [];
    if(values.goodsList!=undefined&&values.goodsList.length>0){
    values.goodsList.forEach((goods, index) => {

        const batchMsgErrors = {};
        if (!goods.storageSpaceId) {
            batchMsgErrors.storageSpaceId =  "商品货位必填";
            errors.goodsList[index] = batchMsgErrors;
            props.errorValidMessageFunction(batchMsgErrors.storageSpaceId);
            return errors;
        }
        if (!goods.quantity) {
            batchMsgErrors.quantity = "商品装斗数量必填";
            errors.goodsList[index] = batchMsgErrors;
            props.errorValidMessageFunction(batchMsgErrors.quantity);
            return errors;
        }
        if (goods.quantity&&goods.quantity>goods.currentStock) {
            batchMsgErrors.quantity = "装斗数量须小于当前库存";
            errors.goodsList[index] = batchMsgErrors;
            props.errorValidMessageFunction(batchMsgErrors.quantity);
            return errors;
        }
        if (!REGEXP_INT.test(goods.quantity) || parseFloat(goods.quantity) < 1 || parseFloat(goods.quantity) > 99999999) {
            batchMsgErrors.quantity =  "商品数量必须为1~99999999之间的整数";
            errors.goodsList[index] = batchMsgErrors;
            props.errorValidMessageFunction(batchMsgErrors.quantity);
            return errors;
        }
        if (!goods.isQualityQualified) {
            batchMsgErrors.isQualityQualified =  "商品质量必填";
            errors.goodsList[index] = batchMsgErrors;
            props.errorValidMessageFunction(batchMsgErrors.isQualityQualified);
            return errors;
        }


    });

    }

    return errors;
};

export const hiddenInputField = ({input}) => (
    <input type="text" name={input.name} style={{display: "none"}} {...input}/>
);
//货位
export const storageSelect = ({input, allItems}) => (
    <div className="td-cont">
        <select name={input.name} {...input}>
            <option value="">请选择</option>
            {allItems.map((storageSpace, index) => (
                <option key={index} value={storageSpace.id}>{storageSpace.storageSpaceNm}</option>
            ))}
        </select>
    </div>
);
//质量情况 下拉
export const qualitySelect = ({input}) => (
    <div className="td-cont">
        <select id="isQualityQualified"  style={{height:"30px",marginRight:"5px"}} name={input.name} {...input}>
            <option value="">请选择</option>
            <option value="Y">合格</option>
            <option value="N">不合格</option>
        </select>
    </div>
);
//  装斗数量
export const inputField = ({input,style}) => (
    <input type="text" name={input.name} style={style ? style : ""} maxLength="9" {...input}/>
);


export const goodsFieldArray = ({fields, allGoods, allStorageSpace}) => (
    <tbody>
    {
        allGoods.map((goods, index) => {
            return (
                <tr key={index}>
                    <Field name={'goodsList[' + index + '].batchId'}  component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].goodsId'}  component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].skuId'}  component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].currentStock'} component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].batch'} component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].supplierId'} component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].purchasePrice'} component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].batchState'} component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].validDateString'} component={hiddenInputField}/>
                    <Field name={'goodsList[' + index + '].produceDateString'} component={hiddenInputField}/>
                    <td ><input className="goods-box" value={goods.id}   type="checkbox" style={{height: "14px",width:"22px"}}/></td>
                    <td>
                        <div className="td-cont">{index+1}</div>
                    </td>
                    <td>
                        <Field name={'goodsList[' + index + '].storageSpaceId'} component={storageSelect}  allItems={allStorageSpace}/>
                    </td>
                    <td className="pink-bg">
                        <div className="td-cont">
                            <Field name={'goodsList[' + index + '].quantity'} component={inputField} style={{height:"26px"}}/>
                        </div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.currentStock || "暂无"}</div>
                    </td>
                    <td>
                        <Field name={'goodsList[' + index + '].isQualityQualified'} component={qualitySelect}/>
                    </td>
                    <td>
                        <div className="td-cont">{goods.goodsCode || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.goodsNm || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.commonNm || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.spec || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.dosageFormName || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.unit || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.produceManufacturer || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.approvalNumber || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.productionPlace || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.batch || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.produceDateString || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goods.validDateString || "暂无"}</div>
                    </td>

                </tr>
            )
        })
    }
    </tbody>)

class DrugInAddForm extends React.Component {


    componentWillMount() {
        //获取登陆信息，
        if(this.context.store.getState().todos.loginUserMessage.id==undefined){
            this.props.actions.findCurrentLoginUserMessage();
        }
        //获取货位信息
        if(this.context.store.getState().todos.allStorageSpace.length==0){
            this.props.actions.findAllStorageSpace();
        }

    }

    componentDidMount() {
        const change = this.props.change;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("inBucketTimeString", $('input[name="drugTime"]').val());
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

        this.props.changeCommonGoodsBatchListState({isSingle:false,isChineseMedicinePieces:"Y",isBiggerThanCurrentStock:"Y",isVirtualWarehouse:"Y"}, (selectedGoodsList) => {
            const {store} = this.context;
            const goodsList = store.getState().form.drugInAddForm.values.goodsList;
            let goodsIdArr = [];
            goodsList.map(goods => {
                goodsIdArr.push(goods.id);
            });
            selectedGoodsList.map(goods =>{
                if ($.inArray(goods.id, goodsIdArr) < 0){
                    const newGoods=Object.assign({},goods,{
                        batchId:goods.id
                    });
                    this.props.array.push("goodsList", newGoods);
                }
            });
            $(".select-all")[0].checked = false;
        });
    }

    deleteGoods() {
        const {store} = this.context;
        let goodsIdArr = [];
        $("input.goods-box:checked").each(function () {
            goodsIdArr.push(parseInt($(this).val()));
            $(this)[0].checked = false;
        })
        if (goodsIdArr.length != 0){
            let i=0;
            const goodsList = store.getState().form.drugInAddForm.values.goodsList;
            goodsList.map((goods, index) => {
                if($.inArray(goods.id, goodsIdArr) >= 0){
                    this.props.array.remove("goodsList", index-i);
                    i =i+1;
                }
            });
        }
        $(".select-all")[0].checked = !$("input.goods-box:checked").length == 0;
    }



    findApproveValidateData(data){
        this.props.change("approveDataId", data.id);
        this.props.actions.findApproveValidateData(data);
    }

    resetAndClose(){
        this.props.actions.drugInBucketAddModel(false);
        this.props.reset();
        this.props.actions.findApproveValidateData({});
    }
    saveAndReset(){
        this.props.checkValidForm(true);
    }

    render() {

        const {showValidateModel, submitting, handleSubmit} = this.props;
        const {goodsBatchTodos, todos,validTodos} = this.context.store.getState();
        const commonAddGoodsBatchListState = goodsBatchTodos.commonAddGoodsBatchListState;
        const {approveData, loginUserMessage} = todos;
        const name = loginUserMessage.realName||"";
        const {validFormState,errorValidMessage} = validTodos;
        const allStorageSpace = todos.allStorageSpace || [];
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.drugInAddForm.values;
        return (
            <form  onSubmit={handleSubmit}>
                {errorValidMessage!=""&&validFormState&&<ValidForm />}
                {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                <ApproveValidateComponent callbackFunc={(data)=>{this.findApproveValidateData(data)}}/>
                <Field name={'approveDataId'} component={hiddenInputField}/>
                <Field name={'goodsIsEmpty'} component={hiddenInputField}/>
                <Field name={'inBucketTimeString'} component={hiddenInputField}/>
                <div className="layer">
                    <div className="layer-box drug-check w1290">
                        <div className="layer-header">
                            <span>药品装斗</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.resetAndClose(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">单据信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>装斗人</p>
                                        <span>{name}</span>
                                    </div>
                                    <div className="item">
                                        <p><i>*</i>装斗时间</p>
                                        <input type="text" name="drugTime" className="form-control datepicker" readOnly/>
                                    </div>
                                    <div className="item" style={{clear: "both"}}>
                                        <p><i>*</i>审核人</p>
                                        {approveData.id != undefined ? <p style={{color: "red"}}>{approveData.realName}</p> : <button onClick={(e)=>{e.preventDefault();showValidateModel()}} className="review">点击审核</button>}
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
                                <div className="box-mc maxh407">
                                    <table className="w1175">
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" >
                                                <input className="select-all" type="checkbox"  style={{height: "14px",width:"22px"}}/>
                                            </th>
                                            <th className="serial-number">序号</th>
                                            <th className="cargo-location">货位</th>
                                            <th className="th-ipt">装斗数量</th>
                                            <th className="quality">当前库存</th>
                                            <th className="quality">质量情况</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="origin">产地</th>
                                            <th className="batch-number">批号</th>
                                            <th className="time">生产日期</th>
                                            <th className="time">有效期至</th>
                                        </tr>
                                        </thead>
                                        {goodsList.length < 1 && <tbody> <tr>  <th colSpan="18" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
                                        <FieldArray name="goodsList" component={goodsFieldArray}   allGoods={goodsList}
                                                    allStorageSpace={allStorageSpace}/>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  disabled={submitting} style={{border: "none"}} onClick={() => {this.saveAndReset()}} href="javascript:void(0);" className="confirm">{submitting ? <i/> : <i/>}保存 </button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => {this.resetAndClose()}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}
DrugInAddForm.contextTypes = {
    store: React.PropTypes.object
};

DrugInAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugInAddForm = reduxForm({
    form: 'drugInAddForm'
})(DrugInAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeCommonGoodsBatchListState,
        showValidateModel,
        initValidForm,
        checkValidForm,
        errorValidMessageFunction
    }, dispatch);
}
function mapStateToProps(state) {
    return {
        initialValues:{goodsList: []},
        state,
        validate
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(DrugInAddForm);