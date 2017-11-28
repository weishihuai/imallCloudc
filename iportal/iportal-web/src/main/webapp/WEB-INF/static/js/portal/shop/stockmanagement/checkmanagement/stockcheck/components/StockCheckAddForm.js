import React, {PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {change as changeFieldValue, Field, FieldArray, reduxForm} from "redux-form";
import {changeCommonGoodsBatchListState} from "../../../../../../common/goodsbatchselectwin/actions";
import CommonGoodsBatchList from "../../../../../../common/goodsbatchselectwin/components/CommonGoodsBatchList";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";

export const validate = (values, props) => {
    props.errorValidMessageFunction("");
    const errors = {};
    if (values.goodsListLength === undefined || values.goodsListLength === 0) {
        const errorMessage = "请选择商品";
        props.errorValidMessageFunction(errorMessage);
        errors.goodsListLength=errorMessage;
        return errors;
    }
    props.errorValidMessageFunction("");
    return errors;
};

/*隐藏域*/
export const hiddenField = ({input}) => (
    <input{...input} type="text" name={input.name} style={{display: "none"}} />
);

export const selectGoodsListFieldArray = ({fields, selectGoods}) => (
    <table className="w1075">
        <thead>
        <tr>
            <th className="th-checkbox">
                <input type="checkbox" className="select-all" style={{height:"14px"}}/>
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
        </tr>
        </thead>
        {selectGoods.length < 1 && <tbody> <tr>  <th colSpan="17" style={{textAlign: "center"}}>暂无数据</th> </tr> </tbody>}
        <tbody>
        {selectGoods.map((goodsBatch, index) => {
            return (<tr>
                    <td>
                        <input type="checkbox" className="goods-box" value={goodsBatch.id} style={{height:"14px"}}/>
                    </td>
                    <td>
                        <div className="td-cont">{index + 1}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.goodsCode}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.goodsNm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.commonNm}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.spec}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.dosageFormName || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.unit}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.produceManufacturer}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.approvalNumber || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.productionPlace || "暂无"}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.batch}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.produceDateString}</div>
                    </td>
                    <td>
                        <div className="td-cont">{goodsBatch.validDateString}</div>
                    </td>
                    <Field  index={index} name={'goodsList['+ index +'].goodsId'} type="hidden"  component={hiddenField}/>
                    <Field  index={index} name={'goodsList['+ index +'].skuId'} type="hidden" component={hiddenField}/>
                    <Field  index={index} name={'goodsList['+ index +'].goodsBatchId'} type="hidden" component={hiddenField}/>
                </tr>
            )
        })
        }
        </tbody>
    </table>
);


class StockCheckAddForm extends React.Component {

    componentWillMount() {
        this.props.actions.findCurrentLoginUserMessage();
    }

    componentDidMount() {
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

    /*选择商品回调函数*/
    openCommonGoodsSelectWin() {
        this.props.changeCommonGoodsBatchListState({isSingle:false,batchState:'NORMAL'}, (selectedGoodsList) => {
            const {store} = this.context;
            const goodsList = store.getState().form.stockCheckAddForm.values.goodsList;
            let goodsIdArr = [];
            goodsList.map(goods => {
                goodsIdArr.push(goods.id);
            });
            let newGoodsList = goodsList;
            selectedGoodsList.map(goods =>{
                if ($.inArray(goods.id, goodsIdArr) < 0){
                    const newGoods=Object.assign({},goods,{
                        goodsBatchId:goods.id
                    });
                    newGoodsList.push(newGoods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("stockCheckAddForm", "goodsList", newGoodsList);
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
            const goodsList = store.getState().form.stockCheckAddForm.values.goodsList;
            let newGoodsList = [];
            goodsList.map(goods => {
                if($.inArray(goods.id, goodsIdArr) < 0){
                    newGoodsList.push(goods);
                }
            });
            this.props.change("goodsListLength",newGoodsList.length);
            changeFieldValue("stockCheckAddForm", "goodsList", newGoodsList);
        }
        $(".select-all")[0].checked = $("input.goods-box:checked").length !== 0;
    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const {goodsBatchTodos,todos} = this.context.store.getState();
        const commonAddGoodsBatchListState = goodsBatchTodos.commonAddGoodsBatchListState;
        const actions = this.props.actions;
        const {store} = this.context;
        const {goodsList} = store.getState().form.stockCheckAddForm.values;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const  realName = todos.loginUserMessage.realName || "";

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {commonAddGoodsBatchListState && <CommonGoodsBatchList actions={actions}/>}
                <div className="layer">
                    <div className="layer-box drug-check w1175">
                        <div className="layer-header">
                            <span>库存盘点</span>
                            <a href="javascript:void(0);" className="close" onClick={(e) => actions.stockCheckAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">盘点信息</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>操作人</p>
                                        <span>{realName}</span>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">商品信息</div>
                                <div className="box-mc receiving-box clearfix">
                                    <div className="item">
                                        <div className="item-add" onClick={()=>this.openCommonGoodsSelectWin()}>添加商品</div>
                                        <div className="item-del" onClick={() => {this.deleteGoods()}}>删除商品</div>
                                    </div>
                                </div>
                                <div className="box-mc">
                                    <FieldArray name="goodsList" component={selectGoodsListFieldArray} selectGoods={goodsList}/>
                                    <Field  name="goodsListLength" type="hidden"  component={hiddenField}/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>{actions.stockCheckAddModel(false)}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

StockCheckAddForm.contextTypes = {
    store: React.PropTypes.object
};

StockCheckAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

StockCheckAddForm = reduxForm({
    form: 'stockCheckAddForm',
})(StockCheckAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeCommonGoodsBatchListState,
        changeFieldValue,
        checkValidForm,
        errorValidMessageFunction
    }, dispatch);
}
function mapStateToProps(state) {
    return {
        initialValues:{goodsList: [],goodsListLength:0},
        state,
        validate
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(StockCheckAddForm);