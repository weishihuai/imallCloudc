import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import ValidForm from "../../../../../common/validForm/components/ValidForm";

import {selectField, inputField} from './FieldComponent';
import {sendOrderValidate} from '../actions/index';

class OrderSendForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    valid(data){
        const sendTotal = new Map();
        const waitSendTotal = new Map();
        data.itemList.map((item)=>{
            if(sendTotal.has(item.id)){
                const send = Object.assign({}, sendTotal.get(item.id), {
                    totalQuantity: (parseInt(sendTotal.get(item.id).totalQuantity) + parseInt(item.sendQuantity))
                });
                sendTotal.set(item.id, send);
            }else{
                sendTotal.set(item.id, {totalQuantity: item.sendQuantity, commonNm: item.commonNm});
            }

            if(!waitSendTotal.has(item.id)){
                waitSendTotal.set(item.id, {quantity: item.quantity, commonNm: item.commonNm});
            }
        });

        for (let [key, value] of waitSendTotal) {
            let totalQuantity = sendTotal.get(key).totalQuantity;
            if(totalQuantity>value.quantity){
                this.props.errorValidMessageFunction(value.commonNm + "总的发货数量不能超过" + value.quantity);
                return;
            }else if(totalQuantity<value.quantity){
                this.props.errorValidMessageFunction(value.commonNm + "总的发货数量不能少于" + value.quantity);
                return;
            }
        }

        return this.props.actions.updateOrderToSend(data);
    }

    render(){
        const {actions, handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        const {sendData} = this.context.store.getState().orderTodos;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit(this.valid.bind(this))}>
                {validFormState && errorValidMessage && <ValidForm  checkValidForm={checkValidForm}/>}
                <Field name="id" type="hidden" component={inputField} readOnly />
                <div className="layer">
                    <div className="layer-box layer-start layer-immediate-delivery">
                        <div className="layer-header">
                            <span>立即发货</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>actions.showOrderSend(false)}/>
                        </div>
                        <div className="layer-body">
                            <table>
                                <thead>
                                <tr>
                                    <th className="sp-name">产品名称</th>
                                    <th className="batch">发货批号</th>
                                    <th className="number">发货数量</th>
                                    <th className="number">待发货数量</th>
                                    <th className="jiajian">&nbsp;</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    sendData.itemList.map((item, index)=>{
                                        return(
                                            <tr key={index}>
                                                <td style={{display: 'none'}}><Field name={'itemList[' + index + '].id'} type="hidden" component={inputField} readOnly /></td>
                                                <td>{item.commonNm}</td>
                                                {
                                                    item.batchList.length>0 &&
                                                    <Field name={'itemList[' + index + '].batchId'} type="text" component={selectField} optionValue="id" optionName="batch" items={item.batchList} limitQuantity={item.batchCurrentStock} onChange={(e)=>actions.batchChange(e.target.value, item, sendData)}/>
                                                }
                                                {item.batchList.length===0 && <td style={{color: 'red', paddingLeft: 15 + 'px'}}>缺货</td>}
                                                <td>
                                                    <Field name={'itemList[' + index + '].sendQuantity'} type="text" component={inputField} onChange={(e)=>actions.quantityChange(e.target.value, item, sendData)} readOnly={item.batchList.length===0 ? 'readOnly' : ''} />
                                                </td>
                                                <td>
                                                    {!item.add && <span className="span-number">{item.quantity}</span>}
                                                </td>
                                                <td>
                                                    {item.add && <a className="reduction" href="javascript:void(0);" onClick={()=>actions.removeBatch(item, sendData)}>-</a>}
                                                    {!item.add && item.batchList.length>1 && <a className="add" href="javascript:void(0);" onClick={()=>actions.addBatch(item, sendData)}>+</a>}
                                                </td>
                                            </tr>
                                        );
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                        <div className="layer-footer" style={{paddingLeft:0, textAlign: 'center'}}>
                            {sendData.canSend && <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {checkValidForm(true)}} disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>}
                            {!sendData.canSend &&  <a href="javascript:void(0);" className="confirm" style={{backgroundColor: '#b5bac5'}}>发货</a>}
                            <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={()=>actions.showOrderSend(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

OrderSendForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    actions: PropTypes.object.isRequired
};

OrderSendForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.orderTodos.sendData,
        validate: sendOrderValidate,
        state
    }
}

OrderSendForm = reduxForm({
    form: 'orderSendForm',
    enableReinitialize: true
})(OrderSendForm);

OrderSendForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(OrderSendForm);

export default OrderSendForm;