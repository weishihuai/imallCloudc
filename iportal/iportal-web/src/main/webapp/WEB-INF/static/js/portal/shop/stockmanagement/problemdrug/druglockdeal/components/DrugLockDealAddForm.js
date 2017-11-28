import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {FieldArray, Field, reduxForm, change as changeFieldValue} from "redux-form";
import {changeDrugLockDealGoodsBatchListState} from '../actions';
import DrugLockDealGoodsBatchList from './DrugLockDealGoodsBatchList';
import {showValidateModel} from '../../../../../../common/approvevalidate/actions';
import ApproveValidateComponent from '../../../../../../common/approvevalidate/components/ApproveValidateComponent';
import {inputField} from '../../../../../../common/redux-form-ext';
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";

export const validate = (values,props) => {
    const errors = {};
    if (!values.approveDataId) {
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
    return errors;
};

//隐藏域
export const hiddenField = ({input,readOnly}) => (
    <input type="text" name={input.name} style={{display: "none"}} readOnly={readOnly} {...input}/>
);

/*时间输入框*/
export const timeInputField = ({ input, label, type, id, timeInput, required, meta: { touched, error } }) => (
    <div className="item">
        <p><i>{required == undefined ? '' : '*'}</i>{label}</p>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required} type={type} {...input} readOnly/>
    </div>
);

export const processResultCodeField = ({input, label, type, id, required, items,style, optionName, meta: {touched, error}}) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <select className="select-w" id={id} name={input.name} style={style} {...input}>
            <option value="DISQUALIFICATION">不合格</option>
            <option value="UNLOCK">解锁</option>
        </select>
    </div>
);

export const goodsArrayField = ({fields,goodsList}) => (
    <tbody>
    {
        goodsList.map((goods, index) => {
            return (
            <tr key={index}>
                <Field name={'goodsList[' + index + '].id'} component={hiddenField}/>
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
                    <div className="td-cont">{goods.dosageForm || "暂无"}</div>
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
                    <div className="td-cont">{goods.productionPlace || "暂无"}</div>
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
                <td>
                    <div className="td-cont">{goods.storageSpaceNm}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.currentStock}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.lockQuantity}</div>
                </td>
                <td>
                    <div className="td-cont">{goods.lockReason}</div>
                </td>
            </tr>
            )
        })
    }
    </tbody>
);

class DrugLockDealAddForm extends React.Component {

    componentWillMount() {
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
                    change("processTimeString", $('input[name="processTimeString"]').val());
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

    approveValidate(data){
        this.props.change("approveDataId", data.id);
        this.props.actions.approveValidateData(data);
    }

    openDrugLockDealGoodsSelectWin() {
        this.props.changeDrugLockDealGoodsBatchListState({isSingle:false},(selectedGoodsList) => {
            const {store} = this.context;
            const goodsList = store.getState().form.drugLockDealAddForm.values.goodsList;
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
            changeFieldValue("drugLockDealAddForm", "goodsList", newGoodsList);
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
            const goodsList = store.getState().form.drugLockDealAddForm.values.goodsList;
            let newGoodsList = [];
            goodsList.map(goods => {
                if($.inArray(goods.id, goodsIdArr) < 0){
                    newGoodsList.push(goods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("drugLockDealAddForm", "goodsList", newGoodsList);
        }
        $(".select-all")[0].checked = $("input.goods-box:checked").length !== 0;
    }

    resetAndClose(){
        this.props.actions.drugLockDealAddModel(false);
        this.props.actions.approveValidateData(null);
    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const {todos} = this.context.store.getState();
        const drugLockDealAddGoodsBatchListState = todos.commonAddGoodsBatchListState;
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.drugLockDealAddForm.values;
        const {approveData} = todos;
        const {checkValidForm} = this.props;
        const {errorValidMessage,validFormState} = this.context.store.getState().validTodos;

        return (
            <form onSubmit={handleSubmit}>
                <ApproveValidateComponent callbackFunc={(data)=>{this.approveValidate(data)}}/>
                {drugLockDealAddGoodsBatchListState && <DrugLockDealGoodsBatchList actions={actions}/>}
                {validFormState && errorValidMessage !== "" && <ValidForm checkValidForm={checkValidForm}/>}
                <Field name="approveDataId" component={hiddenField}/>
                <div className="layer">
                    <div className="layer-box drug-check w1175">
                        <div className="layer-header">
                            <span>药品处理</span>
                            <a href="javascript:void(0);" className="close" onClick={()=> actions.drugLockDealAddModel(false)}/>
                        </div>
                            <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">基本信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <Field id="processTimeString" name="processTimeString" timeInput="datepicker" type="text" component={timeInputField} label="处理时间" readOnly/>
                                    </div>
                                    <Field name="processResultCode" id="processResultCode" label="处理类型" style={{"marginTop":"0px"}} component={processResultCodeField}/>
                                    <Field id="processReason" name="processReason" type="text" component={inputField} label="处理原因" maxLength="256"/>
                                    <div className="item">
                                        <p><i>*</i>审核人</p>
                                        {approveData !== null ? <p style={{color:"red"}}>{approveData.realName}</p> : <a href="javascript:void(0);" className="review" onClick={()=>this.props.showValidateModel()}>点击审核</a>}
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={() => {this.openDrugLockDealGoodsSelectWin()}}>添加商品</div>
                                        <div className="item-del" onClick={() => {this.deleteGoods()}}>删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <table className="w1075" style={{width:"1300px"}}>
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" >
                                                <input className="select-all" type="checkbox"  style={{height: "14px"}}/>
                                            </th>
                                            <th className="serial-number" style={{width:"40px"}}>序号</th>
                                            <th className="commodity-code" style={{width:"90px"}}>商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers" style={{width:"85px"}}>生产厂商</th>
                                            <th className="approval-number" style={{width:"85px"}}>批准文号</th>
                                            <th className="origin">产地</th>
                                            <th className="batch-number">批号</th>
                                            <th className="time" style={{width:"110px"}}>生产日期</th>
                                            <th className="time" style={{width:"110px"}}>有效期至</th>
                                            <th className="cargo-location">货位</th>
                                            <th className="number">库存数量</th>
                                            <th className="general-name">锁定数量</th>
                                            <th className="general-name">锁定原因</th>
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
                            <a href="javascript:void(0);" className="cancel" onClick={() => actions.drugLockDealAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}
DrugLockDealAddForm.contextTypes = {
    store: React.PropTypes.object
};

DrugLockDealAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugLockDealAddForm = reduxForm({
    form: 'drugLockDealAddForm'
})(DrugLockDealAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeDrugLockDealGoodsBatchListState,
        changeFieldValue,
        showValidateModel,
        errorValidMessageFunction,
        checkValidForm
    }, dispatch);
}
function mapStateToProps(state) {
    return {
        initialValues:{goodsList: []},
        state,
        validate
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(DrugLockDealAddForm);