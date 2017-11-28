import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField, selectField, validate} from "../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import CommonGoodsBatchList from "../../../../../../js/common/goodsbatchselectwin/components/CommonGoodsBatchList";
import {changeCommonGoodsBatchListState} from "../../../../../../js/common/goodsbatchselectwin/actions";

export const fields = [
    {
        field:'planCuringTimeString',
        validate:{
            fieldNm: "计划养护时间",
            maxlength:19,
            required:true
        }
    },
    {
        field:'curingTypeCode',
        validate:{
            fieldNm: "养护类型",
            maxlength:32,
            required:true
        }
    }
];

class DrugCuringAddForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    let planCuringTimeString = $("#planCuringTimeString").val().trim();
                    change('planCuringTimeString', planCuringTimeString);
                }
            });
        });
    }

    valid(data){
        const {store} = this.context;
        const goods = store.getState().todos.goods || [];
        if(goods.length==0){
            this.props.errorValidMessageFunction("请选择商品！");
            return;
        }
        this.props.errorValidMessageFunction("");

        let itemList = [];
        goods.map((item)=>{
            itemList.push({'goodsId':item.goodsId, 'goodsBatchId':item.id});
        });

        const {params} = store.getState().todos;
        const {actions} = this.props;
        data = Object.assign({},data,{
            itemList: itemList
        });
        return this.props.actions.saveOrUpdate(data, actions, params);
    }

    /**
     * 打开药品选择窗口
     */
    openGoodsSelectWin(){
        this.props.changeCommonGoodsBatchListState({isSingle: true, gtInStockDays: 90, isBiggerThanCurrentStock: 'Y', isInEffective: 'Y', batchState: 'NORMAL'}, (goods) => this.selectGoods(goods));
    }

    /**
     * 药品选择回调
     * @param selectedGoodsList
     */
    selectGoods(goods){
        this.props.actions.showAddForm(true, goods);
    }

    /**
     * 商品全选或取消全选
     * @param goods
     */
    checkOrUncheckAll(goods){
        const {store} = this.context;
        const checkGoodsIdMap = store.getState().todos.checkGoodsIdMap;
        const isCheckAll = store.getState().todos.isCheckAll;
        let goodsIds = [];
        let index = 0;
        goods.map((item)=>{
            goodsIds[index] = item.goodsId;
            index = index + 1;
        });

        if(isCheckAll){//取消全选
            this.props.actions.uncheckGoods(goodsIds, checkGoodsIdMap);
        }else{//全选
            this.props.actions.checkGoods(goodsIds, checkGoodsIdMap, goods);
        }
    }

    /**
     * 单一选中或者取消选中
     * @param item
     */
    checkOrUncheckGoods(item, goods){
        const {store} = this.context;
        const checkGoodsIdMap = store.getState().todos.checkGoodsIdMap;
        let goodsId = item.goodsId;
        let goodsIds = [goodsId];
        if(checkGoodsIdMap.has(goodsId)){//取消选中
            this.props.actions.uncheckGoods(goodsIds, checkGoodsIdMap);
        }else{//选中
            this.props.actions.checkGoods(goodsIds, checkGoodsIdMap, goods);
        }
    }

    /**
     * 删除商品
     */
    removeGoods(){
        const {store} = this.context;
        const goodsMap = store.getState().todos.goodsMap || new Map();
        const checkGoodsIdMap = store.getState().todos.checkGoodsIdMap || new Map();
        this.props.actions.removeGoods(checkGoodsIdMap, goodsMap);
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        let curingTypeCode = [{code: 'FOCUS', name: '重点'}, {code: 'NORMAL', name: '常规'}];
        const goods = store.getState().todos.goods || [];
        const checkGoodsIdMap = store.getState().todos.checkGoodsIdMap || new Map();
        const isCheckAll = store.getState().todos.isCheckAll || false;
        const {commonAddGoodsBatchListState} = this.context.store.getState().goodsBatchTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                {validFormState && errorValidMessage!="" && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box drug-check w1175">
                        <div className="layer-header">
                            <span>新增药品养护</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.showAddForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">盘点信息</div>
                                <div className="box-mc clearfix">
                                    <Field id="planCuringTimeString" name="planCuringTimeString"  type="text" component={inputField} label="计划养护时间" inputClassName="datepicker" readOnly="readOnly" required="required"/>
                                    <Field id="curingTypeCode" name="curingTypeCode"  type="text" component={selectField} label="养护类型" className="select-w" optionValue="code" optionName="name" items={curingTypeCode} required="required"/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={()=>this.openGoodsSelectWin()}>添加商品</div>
                                        <div className="item-del" onClick={()=>this.removeGoods()}>删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <table className="w1075">
                                        <thead>
                                        <tr>
                                            <th className="serial-number">
                                                <input type="checkbox" id="allCheckbox" style={{'width': 15 + 'px'}} onClick={()=>this.checkOrUncheckAll(goods)} checked={isCheckAll}/>
                                            </th>
                                            <th className="serial-number">序号</th>
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
                                            <th className="cargo-location">货位</th>
                                            <th className="number">库存数量</th>
                                            <th className="price">采购单价</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        {
                                            goods.length===0 &&
                                            <tr><td colSpan="17" style={{textAlign: "center"}}>暂无数据</td></tr>
                                        }
                                        {
                                            goods.map((item, index)=>{
                                                return(
                                                    <tr key={item.id}>
                                                        <td >
                                                            <div className="td-cont">
                                                                <input type="checkbox" className="itemCheckbox" style={{'width': 15 + 'px'}} checked={checkGoodsIdMap.has(item.goodsId)} onClick={()=>this.checkOrUncheckGoods(item, goods)}/>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{index + 1}</div>
                                                        </td>
                                                        <td >
                                                            <div className="td-cont">{item.goodsCode}</div>
                                                        </td>
                                                        <td >
                                                            <div className="td-cont">{item.goodsNm}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.commonNm}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.spec}</div>
                                                        </td>
                                                        <td >
                                                            <div className="td-cont">{item.dosageFormName}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.unit}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.produceManufacturer}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.approvalNumber}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.productionPlace}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.batch}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.produceDateString}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.validDateString}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.storageSpaceNm}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.currentStock}</div>
                                                        </td>
                                                        <td>
                                                            <div className="td-cont">{item.purchasePrice}</div>
                                                        </td>
                                                    </tr>
                                                );
                                            })
                                        }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {checkValidForm(true)}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.showAddForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

DrugCuringAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugCuringAddForm.contextTypes = {
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

DrugCuringAddForm = reduxForm({
    form: 'drugCuringAddForm'
})(DrugCuringAddForm);

DrugCuringAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCuringAddForm);

export default DrugCuringAddForm;