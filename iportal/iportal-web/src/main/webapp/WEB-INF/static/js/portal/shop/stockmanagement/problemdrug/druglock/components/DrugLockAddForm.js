import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {FieldArray, Field, reduxForm, change as changeFieldValue} from "redux-form";
import {changeCommonGoodsBatchListState} from '../../../../../../common/goodsbatchselectwin/actions';
import CommonGoodsBatchList from '../../../../../../common/goodsbatchselectwin/components/CommonGoodsBatchList';
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {LONG_REG} from "../../../../../../common/common-constant";
import {inputField} from "../../../../../../common/redux-form-ext";

export const validate = (values, props) => {
    const goodsList = values.goodsList;
    const errors = {};

    if(!values.lockManName){
        errors.lockManName = "请输入锁定人";
        props.errorValidMessageFunction(errors.lockManName);
        return errors;
    }

    if (!values.lockTimeString) {
        errors.lockTimeString = "请选择锁定时间";
        props.errorValidMessageFunction(errors.lockTimeString);
        return errors;
    }

    if(values.goodsListLength === undefined || values.goodsListLength === 0){
        errors.goodsListLength= "请选择商品";
        props.errorValidMessageFunction(errors.goodsListLength);
        return errors;
    }
    errors.goodsList = [];
    for (const i in goodsList){
        const item = goodsList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";

        if (!item.lockQuantity){
            errorStr = str + "请输入锁定数量";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {lockQuantity: errorStr};
            return errors;
        }

        if (!LONG_REG.test(item.lockQuantity)) {
            errorStr = str + "锁定数量必须为大于0的整数";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {lockQuantity: errorStr};
            return errors;
        }

        if (item.lockQuantity > item.currentStock) {
            errorStr = str + "锁定数量必须小于等于库存数量";
            props.errorValidMessageFunction(errorStr);
            errors.goodsList[i] = {lockQuantity: errorStr};
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

//隐藏域
export const hiddenField = ({input,readOnly}) => (
    <input type="text" name={input.name} style={{display: "none"}} readOnly={readOnly} {...input}/>
);

export const customInputField = ({input,readOnly,disabled,style}) => (
    <input type="text" name={input.name} maxLength="19" readOnly={readOnly} disabled={disabled} style={style ? style : ""} {...input}/>
);

/*时间输入框*/
export const timeInputField = ({ input, label, type, id, timeInput, required, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required == undefined ? '' : '*'}</i>{label}</p>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required} type={type} {...input} readOnly/>
    </div>
);

export const goodsArrayField = ({fields,goodsList}) => (
    <tbody>
    {
        goodsList.map((goods, index) => {
            return (
            <tr key={index}>
                <Field name={'goodsList[' + index + '].batchId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].goodsId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].skuId'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].goodsCode'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].commonNm'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].pinyin'} component={hiddenField}/>
                <Field name={'goodsList[' + index + '].goodsNm'} component={hiddenField}/>
                <td >
                    <input className="goods-box" value={goods.id} type="checkbox" style={{height: "14px"}}/>
                </td>
                <td>
                    <div className="td-cont">{index + 1}</div>
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
                    <div className="td-cont">{goods.productionPlace || "暂无"}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.approvalNumber || "暂无"}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.batch}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.currentStock}</div>
                </td>
                <td className="pink-bg">
                    <div className="td-cont">
                        <Field id="lockQuantity" name={'goodsList[' + index + '].lockQuantity'} component={customInputField} style={{height:"26px"}}/>
                    </div>
                </td>
                <td>
                    <div className="td-cont">
                        <Field id="lockReason" name={'goodsList[' + index + '].lockReason'} component={customInputField} style={{height:"26px"}}/>
                    </div>
                </td>
                <td>
                    <div className="td-cont">{goods.produceDateString}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.validDateString}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.storageSpaceNm}</div>
                </td>
                <td>
                    <div className="td-cont">
                        <Field id="remark" name={'goodsList[' + index + '].remark'} component={customInputField} style={{height:"26px"}}/>
                    </div>
                </td>
            </tr>
            )
        })
    }
    </tbody>
);

class DrugLockAddForm extends React.Component {

    componentWillMount() {
    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("lockTimeString", $('input[name="lockTimeString"]').val());
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
        this.props.changeCommonGoodsBatchListState({isSingle:false,isBiggerThanCurrentStock:"Y",isAllowOutStock:"Y"},(selectedGoodsList) => {
            const {store} = this.context;
            const goodsList = store.getState().form.drugLockAddForm.values.goodsList;
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
            changeFieldValue("drugLockAddForm", "goodsList", newGoodsList);
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
            const goodsList = store.getState().form.drugLockAddForm.values.goodsList;
            let newGoodsList = [];
            goodsList.map(goods => {
                if($.inArray(goods.id, goodsIdArr) < 0){
                    newGoodsList.push(goods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("drugLockAddForm", "goodsList", newGoodsList);
        }
        $(".select-all")[0].checked = $("input.goods-box:checked").length !== 0;
    }

    resetAndClose(){
        this.props.actions.drugLockAddModel(false);
    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const {goodsBatchTodos} = this.context.store.getState();
        const commonAddGoodsBatchListState = goodsBatchTodos.commonAddGoodsBatchListState;
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.drugLockAddForm.values;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                <div className="layer">
                    <div className="layer-box drug-check w1290">
                        <div className="layer-header">
                            <span>药品锁定</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>actions.drugLockAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">基本信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <Field name="lockManName" required="required" component={inputField} label="锁定人" type="text" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <Field id="lockTimeString" name="lockTimeString" timeInput="datepicker" required="required" type="text" component={timeInputField} label="锁定时间" readOnly/>
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
                                            <th className="th-checkbox">
                                                <input className="select-all" type="checkbox"  style={{height: "14px"}}/>
                                            </th>
                                            <th className="serial-number" style={{width:"40px"}}>序号</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers">生产厂商</th>
                                            <th className="origin">产地</th>
                                            <th className="approval-number">批准文号</th>
                                            <th className="batch-number">批号</th>
                                            <th className="number">库存数量</th>
                                            <th className="number">锁定数量</th>
                                            <th className="reason">锁定原因</th>
                                            <th className="time" style={{width:"100px"}}>生产日期</th>
                                            <th className="time" style={{width:"100px"}}>有效期至</th>
                                            <th className="cargo-location">货位</th>
                                            <th className="remark">备注</th>
                                        </tr>
                                        </thead>
                                        {goodsList.length < 1 && <tbody> <tr>  <th colSpan="19" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
                                        <FieldArray goodsList={goodsList} name="goodsList" component={goodsArrayField}/>
                                        <Field  name="goodsListLength" type="hidden"  component={hiddenField}/>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => actions.drugLockAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}
DrugLockAddForm.contextTypes = {
    store: React.PropTypes.object
};

DrugLockAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugLockAddForm = reduxForm({
    form: 'drugLockAddForm'
})(DrugLockAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeCommonGoodsBatchListState,
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
export default connect(mapStateToProps, mapDispatchToProps)(DrugLockAddForm);