import React, {Component, PropTypes} from "react";
import {Field, reduxForm} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {validate, inputField, boolRadioField, selectField} from "../../../../../common/redux-form-ext";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import GoodsDescDetail from "./GoodsDescDetail";
import {REGEXP_DOUBLE, REGEXP_INT, REGEXP_PHONE} from "../../../../../common/common-constant";
import CommonGoodsList from "../../../../../common/goodsselectwin/components/CommonGoodsList";
import {changeCommonGoodsListState} from "../../../../../common/goodsselectwin/actions";
import {CONSULT_GOODS_CHANGE, CONSULT_SELECT_GOODS} from "../constants/ActionTypes";

export const fields = [{
    field:'memberCardNum',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "会员卡号"
    }
},{
    field:'patientName',
    validate:{
        required:true,
        maxlength:32,
        fieldNm: "患者姓名"
    }
},{
    field:'age',
    validate:{
        required:false,
        regx: REGEXP_INT,
        minValue: 0,
        maxValue: 120,
        fieldNm: "年龄"
    }
},{
    field:'sex',
    validate:{
        required:false,
        fieldNm: "性别"
    }
},{
    field:'mobile',
    validate:{
        required:false,
        regx: REGEXP_PHONE,
        maxlength:11,
        fieldNm: "手机号"
    }
},{
    field:'identityCard',
    validate:{
        required:false,
        minlength:15,
        maxlength:18,
        fieldNm: "身份证号"
    }
},{
    field:'height',
    validate:{
        required:false,
        regx: REGEXP_INT,
        maxValue: 240,
        fieldNm: "身高"
    }
},{
    field:'weight',
    validate:{
        required:false,
        regx: REGEXP_DOUBLE,
        fieldNm: "体重",
        maxValue: 250,
    }
},{
    field:'addr',
    validate:{
        required:false,
        maxlength:128,
        fieldNm: "地址"
    }
},{
    field:'rebakFunction',
    validate:{
        required:false,
        maxlength:64,
        fieldNm: "肾功能"
    }
},{
    field:'isPregnant',
    validate:{
        required:false,
        fieldNm: "是否怀孕"
    }
},{
    field:'prevMedicalHistory',
    validate:{
        required:false,
        maxlength:128,
        fieldNm: "过往病史"
    }
},{
    field:'consultPharmacist',
    validate:{
        required:false,
        maxlength:32,
        fieldNm: "咨询药师"
    }
},{
    field:'consultTimeString',
    validate:{
        required:false,
        fieldNm: "咨询时间"
    }
},{
    field:'questionDescr',
    validate:{
        required:false,
        maxlength:128,
        fieldNm: "问题描述"
    }
},{
    field:'expertAnswer',
    validate:{
        required:false,
        maxlength:128,
        fieldNm: "专家解答"
    }
}];


class ConsultServiceAddForm extends Component {

    componentWillMount(){
    }

    componentDidMount() {
        $("#consultTimeString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
    }

    componentDidUpdate() {
    }

    openCommonGoodsSelectWin(){
        let params = {isSingle: false};
        this.props.changeCommonGoodsListState(params, (selectedGoodsList)=>{
            const {store} = this.context;
            let goods = store.getState().todos.goods;
            let goodsIds = store.getState().todos.goodsIds;

            let newGoods, newGoodsIds;
            let selectedIds = [];
            selectedGoodsList.forEach(row => {
                if(row) {
                    selectedIds.push(row.id);
                }
            });
            newGoods = goods.filter(obj => selectedIds.indexOf(obj.id)==-1).concat(selectedGoodsList);
            newGoodsIds = goodsIds.filter(id => selectedIds.indexOf(id)==-1).concat(selectedIds);

            let isSelectAll = newGoods.length == goods.length;

            this.props.dispatch({
                type : CONSULT_GOODS_CHANGE,
                goods: newGoods,
                goodsIds: newGoodsIds,
                isSelectAll: isSelectAll
            });
        });
    }

    onSelectAll() {
        const {store} = this.context;
        const {goodsIds, isSelectAll} = store.getState().todos;
        this.props.dispatch({type:CONSULT_SELECT_GOODS , isSelectAll: !isSelectAll, ids: isSelectAll ? [] : goodsIds});
    }

    onRowSelect(goodsId) {
        const {store} = this.context;
        const {selectIds, goodsIds} = store.getState().todos;

        let newSelectedIds = [];
        let isSelected = selectIds.indexOf(goodsId)==-1;
        let isSelectAll = false;
        if (isSelected) {
            newSelectedIds = selectIds.filter(id => id != goodsId).concat(goodsId);
            if(goodsIds.length == newSelectedIds.length) {
                isSelectAll = true;
            }
        } else {
            newSelectedIds = selectIds.filter(id => id != goodsId);
        }

        this.props.dispatch({type:CONSULT_SELECT_GOODS , isSelectAll: isSelectAll, ids: newSelectedIds});
    }

    delSelectGoods() {
        const {store} = this.context;
        let {goods, goodsIds, selectIds} = store.getState().todos;
        goods = goods.filter(obj => selectIds.indexOf(obj.id)==-1);
        goodsIds = goodsIds.filter(id => selectIds.indexOf(id)==-1);

        store.dispatch({
            type : CONSULT_GOODS_CHANGE,
            goods: goods,
            goodsIds: goodsIds,
        });

    }


    submit(data){
        const {store} = this.context;
        let goodsIds = store.getState().todos.goodsIds;
        if(goodsIds.length < 1) {
            this.props.errorValidMessageFunction("请选择商品！");
            return;
        }
        let consultTimeString = $("#consultTimeString").val();

        data = Object.assign({},data,{
            goodsIdList: store.getState().todos.goodsIds,
            consultTime: consultTimeString
        });

        return this.props.actions.saveConsultService(data);
    }

    render() {
        const {store} = this.context;
        const {actions, handleSubmit, submitting, checkValidForm} = this.props;
        const {consultAddForm, goodsDetail} = this.props.actions;
        const {errorValidMessage, validFormState} = store.getState().validTodos;
        const {consultGoodsDetailModal, goods, isSelectAll, selectIds} = store.getState().todos;

        const sexCodeValue =
            [
                {
                    name:"保密",
                    code:"SECRET",
                },
                {
                    name:"男",
                    code:"MALE",
                },
                {
                    name:"女",
                    code:"FEMALE",
                }
            ];

        return (
            <form onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && errorValidMessage != "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {consultGoodsDetailModal && <GoodsDescDetail store={store} actions={actions}/>}
                {store.getState().goodsTodos.commonAddGoodsListState && <CommonGoodsList store={store} actions={actions}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order layer-consulting-register w1075">
                        <div className="layer-header">
                            <span>添加咨询登记</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>consultAddForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">顾客信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="memberCardNum" id="memberCardNum" type="text" label="会员卡号" maxLength="11" component={inputField} />
                                    <Field name="patientName" id="patientName" type="text" label="患者姓名" maxLength="32" required="required"  component={inputField} />
                                    <Field name="age" id="age" type="text" label="年龄"  component={inputField} />
                                    <Field name="sex" id="sex" type="text" label="性别"  component={selectField} items={sexCodeValue} optionName="name" optionValue="code"/>
                                    <Field name="mobile" id="mobile" type="text" label="手机号" maxLength="11" component={inputField} />
                                    <Field name="identityCard" id="identityCard" type="text" label="身份证号" maxLength="32" component={inputField} />
                                    <Field name="height" id="height" type="text" label="身高(cm)"  component={inputField} />
                                    <Field name="weight" id="weight" type="text" label="体重(kg)"  component={inputField} />
                                    <Field name="addr" id="addr" type="text" label="地址" maxLength="128" component={inputField} />
                                    <Field name="rebakFunction" id="rebakFunction" type="text" label="肾功能" maxLength="64"  component={inputField} />
                                    <Field name="isPregnant" id="isPregnant" type="text" label="是否怀孕"  component={boolRadioField} />
                                    <Field name="prevMedicalHistory" id="prevMedicalHistory" type="text" label="过往病史" maxLength="128" component={inputField} />
                                    <Field name="consultPharmacist" id="consultPharmacist" type="text" label="咨询药师" maxLength="32" component={inputField} />
                                    <Field name="consultTimeString" id="consultTimeString" inputClassName="form-control datepicker" type="text" label="咨询时间" readOnly="readOnly" component={inputField} />
                                    <Field name="questionDescr" id="questionDescr" className="item-problem-description" type="text" label="问题描述" maxLength="128" component={inputField} />
                                    <Field name="expertAnswer" id="expertAnswer" className="item-problem-description" type="text" label="专家解答"  maxLength="128" component={inputField} />
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={()=>this.openCommonGoodsSelectWin()}>添加商品</div>
                                        <div className="item-del" onClick={()=>this.delSelectGoods()}>删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc clearfix">
                                    <table>
                                        <thead>
                                            <tr>
                                                <th className="th-checkbox" style={{height: "14px",width:"22px",paddingLeft:"15px"}}>
                                                    <input type="checkbox" style={{height:"13px"}} checked={isSelectAll} onClick={()=>this.onSelectAll()}/>
                                                </th>
                                                <th className="commodity-code">商品编码</th>
                                                <th className="commodity-name">商品名称</th>
                                                <th className="general-name">通用名称</th>
                                                <th className="specifications">规格</th>
                                                <th className="dosage-form">剂型</th>
                                                <th className="unit">单位</th>
                                                <th className="manufacturers">生产厂商</th>
                                                <th className="approval-number">批准文号</th>
                                                <th className="origin">产地</th>
                                                <th className="price">零售价</th>
                                                <th className="number">库存</th>
                                                <th className="instructions">说明书</th>
                                                <th className="guidelines">用药指导</th>
                                            </tr>
                                        </thead>
                                        {goods.length < 1 && <tbody> <tr>  <th colSpan="14" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
                                        <tbody>
                                        {goods.map((goods,index)=> {
                                            return (
                                                <tr key={index}>
                                                    <td>
                                                        <input type="checkbox" style={{height:"13px",marginLeft:"7px"}}  checked={selectIds.indexOf(goods.id)>-1} onClick={()=>this.onRowSelect(goods.id)}/>
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
                                                        <div className="td-cont">{goods.approvalNumber}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.goodsCode}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.goodsCode}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">{goods.goodsCode}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">
                                                            {goods.instructionsStr === "" ? "暂无" : <a href="javascript:void(0);" onClick={()=> goodsDetail(true, "说明书", goods.instructionsStr)}>查看</a>}
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont">
                                                            {goods.medicationGuideStr === "" ? "暂无" : <a href="javascript:void(0);" onClick={()=> goodsDetail(true, "用药指导", goods.medicationGuideStr)}>查看</a>}
                                                        </div>
                                                    </td>
                                                </tr>
                                            )
                                        })}
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting}  onClick={() => {checkValidForm(true)}}>保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>consultAddForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

ConsultServiceAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

ConsultServiceAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({checkValidForm, errorValidMessageFunction, changeCommonGoodsListState}, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.initSaveData,
        fields: fields,
        validate:validate,
        state
    }
}

ConsultServiceAddForm = reduxForm({
    form: 'consultServiceAddForm',
    validate
})(ConsultServiceAddForm);

export default connect(mapStateToProps, mapDispatchToProps)(ConsultServiceAddForm);