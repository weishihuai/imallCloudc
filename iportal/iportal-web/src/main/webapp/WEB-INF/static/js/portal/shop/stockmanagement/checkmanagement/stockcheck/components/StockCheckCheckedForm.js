import React, {Component, PropTypes} from "react";
import {Field,change,reduxForm, FieldArray} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {REGEXP_INT_ZERO} from "../../../../../../common/common-constant";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../../../../common/validForm/actions";

export const validate = (values, props) => {
    const stockCheckRealCheckQuantityUpdateVoList = values.stockCheckRealCheckQuantityUpdateVoList;
    const errors = {};
    props.errorValidMessageFunction("");
    errors.stockCheckRealCheckQuantityUpdateVoList = [];
    for (const i in stockCheckRealCheckQuantityUpdateVoList) {
        const item = stockCheckRealCheckQuantityUpdateVoList[i];
        const str = "【序号"+ (parseInt(i) + 1) +"】";
        let errorStr = "";
        if (!item.realCheckQuantity) {
            errorStr = str + "商品实盘数量必填";
            props.errorValidMessageFunction(errorStr);
            errors.stockCheckRealCheckQuantityUpdateVoList[i] = {realCheckQuantity: errorStr};
            return errors;
        } else if (!REGEXP_INT_ZERO.test(item.realCheckQuantity) || parseFloat(item.realCheckQuantity) < 0 || parseFloat(item.realCheckQuantity) > 99999999) {
            errorStr = str + "商品实盘数量必须为0~99999999之间的整数";
            errors.stockCheckRealCheckQuantityUpdateVoList[i] = {realCheckQuantity: errorStr};
            props.errorValidMessageFunction(errorStr);
            return errors;
        }
    }
    return errors
};


export const inputField = ({ input, type, id,required, maxLength,style}) => (
    <div className="td-cont">
        <input type={type} id={id} name={input.name}  maxLength={maxLength} style={style? style:""} required={required} {...input}/>
    </div>
);

/*隐藏域*/
export const hiddenField = ({ input, type}) => (
    <span>
        <input name={input.name} type={type} {...input}/>
    </span>
);



export const stockCheckGoodsArrayFields=({field,_this,stockCheckGoodsVoList=[]})=>(
    <table className="w1075">
        <thead>
        <tr>
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
            <th className="number">实盘数量</th>
        </tr>
        </thead>
        <tbody>
        {
            stockCheckGoodsVoList.map((stockCheckGoodsVo,index)=>{
                return(
                    <tr key={index}>
                        <td><div className="td-cont">{index + 1}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.goodsCode}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.goodsNm}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.goodsCommonNm}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.spec}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.dosageForm || "暂无"}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.unit}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.produceManufacturer}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.approvalNumber || "暂无"}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.productionPlace || "暂无"}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.batch}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.produceDateString}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.validDateString}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.storageSpaceNm}</div></td>
                        <td><div className="td-cont">{stockCheckGoodsVo.currentStock}</div></td>
                        <td style={{display:"none"}}>
                            <Field index={index} name={'stockCheckRealCheckQuantityUpdateVoList['+ index +'].id'} type="hidden" component={hiddenField}/>
                        </td>
                        <td className="pink-bg">
                            <Field index={index} name={'stockCheckRealCheckQuantityUpdateVoList['+ index +'].realCheckQuantity'} component={inputField} style={{height:"26px"}}/>
                        </td>
                    </tr>
                )
            })
        }
        </tbody>
    </table>
);

class StockCheckCheckedForm extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        const {store} = this.context;
        let checkOrderNum = store.getState().todos.checkOrderNum;
        this.props.actions.stockCheckUpdateDetailData(checkOrderNum);
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {store} = this.context;
        const {actions,handleSubmit,submitting} = this.props;
        let stockCheckUpdateRealQuantityData = store.getState().todos.stockCheckUpdateRealQuantityData;
        let stockCheckGoodsVoList = stockCheckUpdateRealQuantityData.stockCheckGoodsVoList;
        let checkOrderNum = store.getState().todos.checkOrderNum;
        const _this = this;
        const stockCheckFormDataList = this.context.store.getState().form.stockCheckCheckedForm.values.stockCheckRealCheckQuantityUpdateVoList;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
            <div className="layer">
                <div className="layer-box drug-check w1175">
                    <div className="layer-header">
                        <span>库存盘点</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.props.actions.stockCheckUpdateRealQuantityModal(false,null)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">盘点信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p>盘点单号</p>
                                    <span>{checkOrderNum}</span>
                                </div>
                                <div className="item">
                                    <p>操作人</p>
                                    <span>{stockCheckUpdateRealQuantityData.operatorName}</span>
                                </div>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mt">商品信息</div>
                            <div className="box-mc">
                                <FieldArray name="stockCheckRealCheckQuantityUpdateVoList" formValue={stockCheckFormDataList} _this={_this}
                                            stockCheckGoodsVoList={stockCheckGoodsVoList} actions={actions} component={stockCheckGoodsArrayFields}/>
                                <Field  name="realCheckQuantityLength" type="hidden"  component={hiddenField}/>
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <button href="javascript:void(0);" className="confirm" type="submit" style={{border: "none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                        <a href="javascript:void(0);" className="cancel" onClick={()=>this.props.actions.stockCheckUpdateRealQuantityModal(false,null)}>取消</a>
                    </div>
                </div>
            </div>
        </form>
    )
    }
}

StockCheckCheckedForm.contextTypes = {
    store : React.PropTypes.object
};

StockCheckCheckedForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    actions: PropTypes.object.isRequired
};

StockCheckCheckedForm = reduxForm({
    form: "stockCheckCheckedForm",
    enableReinitialize: true,
})(StockCheckCheckedForm);


function mapStateToProps(state) {
    const data = state.todos.stockCheckUpdateRealQuantityData;
    return {
        initialValues: {
            stockCheckRealCheckQuantityUpdateVoList:data.stockCheckGoodsVoList,
            operationTimeString:data.operationTimeString
        },
        state,
        validate
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StockCheckCheckedForm);
