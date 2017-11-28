import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {validate} from "../../../../../common/redux-form-ext";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import {LONG_REG} from "../../../../../common/common-constant";
import CommonGoodsBatchList from "../../../../../../js/common/goodsbatchselectwin/components/CommonGoodsBatchList";
import {changeCommonGoodsBatchListState} from "../../../../../../js/common/goodsbatchselectwin/actions";

export const fields = [
    {
        field:'goodsCode',
        validate:{
            fieldNm: "商品信息",
            required:true
        }
    },
    {
        field:'splitZeroQuantity',
        validate:{
            fieldNm: "拆零数量",
            regx: LONG_REG,
            required:true
        }
    },
    {
        field:'splitSmallPackageQuantity',
        validate:{
            fieldNm: "拆后小包装数量",
            regx: LONG_REG,
            required:true
        }
    },
    {
        field:'usageText',
        validate:{
            fieldNm: "用法",
            maxlength:64
        }
    },
    {
        field:'dosage',
        validate:{
            fieldNm: "用量",
            maxlength:32
        }
    },
    {
        field:'operator',
        validate:{
            fieldNm: "经办人",
            required:true,
            maxlength:32
        }
    }
];

export const noChangeInputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div className={"item " + (className ? className : "")} style={{disabled: 'none'}}>
        <p>{required && <i>*</i>}{label}</p>
        <input  placeholder={placeholder || ''} type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} disabled ={disabled  ? disabled :""} readOnly={readOnly ? readOnly : ""}/>
    </div>
);

export const inputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div className={"item " + (className ? className : "")}>
        <p>{required && <i>*</i>}{label}</p>
        <input  placeholder={placeholder || ''} type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} disabled ={disabled  ? disabled :""} readOnly={readOnly ? readOnly : ""} {...input} onChange={input.onChange}/>
    </div>
);

class GoodsSplitZeroAddForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    /**
     * 打开药品选择窗口
     */
    openGoodsSelectWin(){
        this.props.changeCommonGoodsBatchListState({isSingle: true, isSplitZero: 'Y',isBiggerThanCurrentStock:"Y", batchState: 'NORMAL', isInEffective: 'Y'}, (selectedGoodsList) => this.selectGoods(selectedGoodsList));
    }

    /**
     * 药品选择回调
     * @param selectedGoodsList
     */
    selectGoods(selectedGoodsList){
        const {change} = this.props;
        let goods = selectedGoodsList[0];

        $("#goodsCode").val(goods.goodsCode);
        $("#goodsNm").val(goods.goodsNm);
        $("#commonNm").val(goods.commonNm);
        $("#spec").val(goods.spec);
        $("#dosageForm").val(goods.dosageFormName);
        $("#unit").val(goods.unit);
        $("#produceManufacturer").val(goods.produceManufacturer);
        $("#supplierNm").val(goods.supplierNm);
        $("#storageSpaceNm").val(goods.storageSpaceNm);
        $("#batch").val(goods.batch);
        $("#validDateString").val(goods.validDateString);
        $("#produceDateString").val(goods.produceDateString);
        $("#createDateString").val(goods.createDateString);
        $("#splitZeroUnit").val(goods.splitZeroUnit);
        $("#splitZeroSpec").val(goods.splitZeroSpec);
        $("#splitZeroRetailPrice").val(goods.splitZeroRetailPrice);
        $("#splitZeroMemberPrice").val(goods.splitZeroMemberPrice);
        $("#currentStock").val(goods.currentStock);

        change('goodsCode', goods.goodsCode);
        change('goodsNm', goods.goodsNm);
        change('goodsCommonNm', goods.commonNm);
        change('goodsId', goods.goodsId);
        change('skuId', goods.skuId);
        change('goodsBatchId', goods.id);
        change('pinyin', goods.pinyin);

        let splitZeroQuantity = $("#splitZeroQuantity").val();
        if(LONG_REG.test(splitZeroQuantity) && splitZeroQuantity>0){
            splitZeroQuantity = parseInt(splitZeroQuantity);
            $("#splitSmallPackageQuantity").val(splitZeroQuantity * goods.splitZeroQuantity);
            change('splitSmallPackageQuantity', splitZeroQuantity * goods.splitZeroQuantity);
        }

        this.props.actions.refreshGoodsInfo(goods);
    }

    splitZeroQuantityChange(event){
        const {change} = this.props;
        const {goods} = this.context.store.getState().todos;
        let splitZeroQuantity = parseInt(event.target.value);
        if(goods && LONG_REG.test(splitZeroQuantity) && splitZeroQuantity>0){
            $("#splitSmallPackageQuantity").val(splitZeroQuantity * goods.splitZeroQuantity);
            change('splitSmallPackageQuantity', splitZeroQuantity * goods.splitZeroQuantity);
        }
    }

    valid(data){
        const {actions} = this.props;
        const {goods, params} = this.context.store.getState().todos;
        let splitZeroQuantity = parseInt($("#splitZeroQuantity").val());
        if(splitZeroQuantity>goods.currentStock){
            this.props.errorValidMessageFunction("拆零数量不能超过" + goods.currentStock);
            return;
        }
        this.props.errorValidMessageFunction("");
        return this.props.actions.save(data, actions, params);
    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {commonAddGoodsBatchListState} = this.context.store.getState().goodsBatchTodos;

        return(
            <div>
                <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))} id="addForm">
                    {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                    {validFormState && errorValidMessage && <ValidForm  checkValidForm={checkValidForm}/>}
                    <div className="layer">
                        <div className="layer-box layer-info layer-order w960">
                            <div className="layer-header">
                                <span>新增药品拆零</span>
                                <a href="javascript:void(0);" className="close" onClick={()=>{actions.showAddForm(false)}}/>
                            </div>
                            <div className="layer-body">
                                <div className="md-box">
                                    <div className="box-mc receiving-box clearfix">
                                        <div className="item">
                                            <div className="item-add" onClick={()=>this.openGoodsSelectWin()}>选择商品</div>
                                        </div>
                                    </div>
                                    <div className="box-mc clearfix" id="goodsInfo">
                                        <Field id="goodsCode" name="goodsCode"  type="text" component={noChangeInputField} label="商品编码" required="required" disabled/>
                                        <div className="item">
                                            <p><i>*</i>商品名称</p>
                                            <input type="text" id="goodsNm" name="goodsNm" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>通用名称</p>
                                            <input type="text" id="commonNm" name="goodsCommonNm" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>规格</p>
                                            <input type="text" id="spec" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>剂型</p>
                                            <input type="text" id="dosageForm" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>单位</p>
                                            <input type="text" id="unit" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>生产厂商</p>
                                            <input type="text" id="produceManufacturer" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>货位</p>
                                            <input type="text" id="storageSpaceNm" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>批号</p>
                                            <input type="text" id="batch" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>有效日期</p>
                                            <input type="text" id="validDateString" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>生产日期</p>
                                            <input type="text" id="produceDateString" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>入库日期</p>
                                            <input type="text" id="createDateString" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>库存</p>
                                            <input type="text" id="currentStock" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div className="md-box">
                                    <div className="box-mt">拆零信息</div>
                                    <div className="box-mc clearfix">
                                        <Field id="splitZeroQuantity" name="splitZeroQuantity"  type="text" component={inputField} label="拆零数量" required="required" onChange={(event)=>this.splitZeroQuantityChange(event)}/>
                                        <div className="item">
                                            <p><i>*</i>拆后小包装数量</p>
                                            <input type="text" id="splitSmallPackageQuantity" name="splitSmallPackageQuantity" disabled/>
                                        </div>
                                        <Field id="usageText" name="usageText"  type="text" component={inputField} label="用法" maxLength="64" />
                                        <Field id="dosage" name="dosage"  type="text" component={inputField} label="用量" maxLength="32" />
                                        <Field id="operator" name="operator"  type="text" component={inputField} label="经办人" maxLength="32" required="required" />
                                        <div className="item">
                                            <p>拆零单位</p>
                                            <input type="text" id="splitZeroUnit" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>拆零规格</p>
                                            <input type="text" id="splitZeroSpec" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>拆零零售价格</p>
                                            <input type="text" id="splitZeroRetailPrice" disabled/>
                                        </div>
                                        <div className="item">
                                            <p>拆零会员价格</p>
                                            <input type="text" id="splitZeroMemberPrice" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div className="layer-footer">
                                    <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {checkValidForm(true)}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                                    <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={()=>{actions.showAddForm(false)}}>取消</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

GoodsSplitZeroAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

GoodsSplitZeroAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        changeCommonGoodsBatchListState
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        state
    }
}

GoodsSplitZeroAddForm = reduxForm({
    form: 'goodsSplitZeroAddForm'
})(GoodsSplitZeroAddForm);

GoodsSplitZeroAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsSplitZeroAddForm);

export default GoodsSplitZeroAddForm;