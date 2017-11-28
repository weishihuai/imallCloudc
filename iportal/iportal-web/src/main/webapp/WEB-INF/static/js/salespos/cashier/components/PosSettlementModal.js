/**
 * Created by HWJ on 2017/7/21
 */
import React, {Component} from "react";
import {
    POS_CASHIER_HEALTH_INSURANCE_PAY_CHANGE,
    POS_CASHIER_CASH_PAY_CHANGE,
    POS_CASHIER_PAYMENT_WAY_CHANGE,
    POS_CASHIER_SETTLEMENT_MODAL
} from "../constants/ActionTypes";
import {REGEXP_DOUBLE_1} from "../../../common/common-constant";
import {niftyNoty} from "../../../common/common";

/*结算*/
class PosSettlementModal extends Component {

    constructor(props) {
        super(props);
        this.settlementOp = this.settlementOp.bind(this);
        this.changePayWay = this.changePayWay.bind(this);
    }

    componentDidMount() {
        window.addEventListener('keydown', this.settlementOp);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.settlementOp);
    }

    settlementOp(event) {

        switch(event.keyCode){
            //enter
            case 13:
                const {uniqueKey, healthInsurancePayAmount, returnCash,cashPayAmount, isCashPay, paymentWayCode, shoppingCart,loginCashierData} = this.context.store.getState().todos;
                event.preventDefault();
                const resultPaymentWayCode = isCashPay ? "CASH_PAY" : paymentWayCode;
                if(shoppingCart.orderTotalAmount < healthInsurancePayAmount) {
                    niftyNoty("医保金额应小于应付金额！");
                    return;
                }
                if(isCashPay) {
                    if((shoppingCart.orderTotalAmount - healthInsurancePayAmount - cashPayAmount) > 0) {
                        niftyNoty("支付金额小于应付金额！");
                        return;
                    }
                    if(loginCashierData.cashAmount<returnCash){
                        niftyNoty("现金不足以找零！");
                        return;
                    }
                }
                this.props.actions.saveSalesPosOrder(uniqueKey, healthInsurancePayAmount, cashPayAmount, resultPaymentWayCode);
                break;
            //esc
            case 27:
                event.preventDefault();
                let oldjs = document.getElementById("notyLayer");
                if(oldjs) {
                    oldjs.parentNode.removeChild(oldjs);
                    break;
                }

                this.context.store.dispatch({
                    type: POS_CASHIER_SETTLEMENT_MODAL,
                    isShow: false,
                });
                break;
            //F1
            case 112:
                event.preventDefault();
                this.changePayWay(true, "");
                this.refs.cashPay.select();
                break;
            //F2
            case 113:
                event.preventDefault();
                this.refs.alipay.checked = true;
                this.changePayWay(false, this.refs.alipay.value);
                break;
            //F3
            case 114:
                event.preventDefault();
                this.refs.webChatPay.checked = true;
                this.changePayWay(false, this.refs.webChatPay.value);
                break;
            //F4
            case 115:
                event.preventDefault();
                this.refs.bankCardPay.checked = true;
                this.changePayWay(false, this.refs.bankCardPay.value);
                break;
            //F5
            case 116:
                event.preventDefault();
                this.refs.healthInsurancePay.select();
                break;
        }

    }

    changePayWay(isCashPay, paymentWayCode) {
        this.refs.cashPay.value = '';
        this.refs.healthInsurancePay.value = '';
        this.refs.returnCash.value = '';
        this.context.store.dispatch({
            type: POS_CASHIER_PAYMENT_WAY_CHANGE,
            isCashPay: isCashPay,
            paymentWayCode: paymentWayCode
        });
    }

    cashPayChange(value) {
        const {store} = this.context;
        let {cashPayAmount, healthInsurancePayAmount, returnCash, shoppingCart} = store.getState().todos;
        if(healthInsurancePayAmount == ""){
            healthInsurancePayAmount = 0;
        }
        const orderTotalAmount = shoppingCart.orderTotalAmount;
        value = value.trim();
        let newReturnCash = returnCash;
        const patt = new RegExp(REGEXP_DOUBLE_1);
        const point = new RegExp(/^\d+\.$/);
        const zero = new RegExp(/^0\d$/);

        if(value == "") {
            healthInsurancePayAmount = 0;
            newReturnCash = parseFloat(healthInsurancePayAmount) - orderTotalAmount;
        } else if(patt.test(value)) {
            newReturnCash = parseFloat(value) + parseFloat(healthInsurancePayAmount) - orderTotalAmount;
        } else if(point.test(value)) {

        } else {
            value = cashPayAmount;
        }

        if(zero.test(value)){
            value = value - 0;
        }

        if(value > 999999) {
            return;
        }

        this.context.store.dispatch({
            type: POS_CASHIER_CASH_PAY_CHANGE,
            cashPayAmount: value,
            returnCash: parseFloat(newReturnCash.toFixed(1))
        })
    }


    healthInsurancePayChange(value) {
        const {store} = this.context;
        let {cashPayAmount, healthInsurancePayAmount, returnCash, shoppingCart, isCashPay} = store.getState().todos;
        if (cashPayAmount == "") {
            cashPayAmount = 0;
        }
        const orderTotalAmount = shoppingCart.orderTotalAmount;
        value = value.trim();
        let newReturnCash = returnCash;
        const patt = new RegExp(REGEXP_DOUBLE_1);
        const point = new RegExp(/^\d+\.$/);
        const zero = new RegExp(/^0\d$/);


        if (value == "") {
            cashPayAmount = 0;
            newReturnCash = parseFloat(cashPayAmount) - orderTotalAmount;
        } else if (patt.test(value)) {
            newReturnCash = parseFloat(value) + parseFloat(cashPayAmount) - orderTotalAmount;
        } else if (point.test(value)) {

        } else {
            value = healthInsurancePayAmount;
        }

        //如果不是现金支付，应退金额都为0
        if(!isCashPay) {
            newReturnCash = 0;
        }

        if (zero.test(value)) {
            value = value - 0;
        }

        if(value > 999999) {
            return;
        }

        this.context.store.dispatch({
            type: POS_CASHIER_HEALTH_INSURANCE_PAY_CHANGE,
            healthInsurancePayAmount: value,
            returnCash: parseFloat(newReturnCash.toFixed(1))
        })


    }


    saveSalesPosOrder(uniqueKey, healthInsurancePayAmount, cashPayAmount, resultPaymentWayCode,isCashPay,shoppingCart,returnCash,loginCashierData){
        const {saveSalesPosOrder} = this.props.actions;
        if(shoppingCart.orderTotalAmount < healthInsurancePayAmount) {
            niftyNoty("医保金额应小于应付金额！");
            return;
        }
        if(isCashPay) {
            if((shoppingCart.orderTotalAmount - healthInsurancePayAmount - cashPayAmount) > 0) {
                niftyNoty("支付金额小于应付金额！");
                return;
            }
            if(loginCashierData.cashAmount<returnCash){
                niftyNoty("现金不足以找零！");
                return;
            }
        }
        saveSalesPosOrder(uniqueKey, healthInsurancePayAmount, cashPayAmount, resultPaymentWayCode);
    }


    render() {
        const {store} = this.context;
        const {shoppingCart, cashPayAmount,loginCashierData, healthInsurancePayAmount, returnCash, isCashPay, paymentWayCode, uniqueKey} = store.getState().todos;
        const {settlementModal} = this.props.actions;
        const resultPaymentWayCode = isCashPay ? "CASH_PAY" : paymentWayCode;

        return (
            <div className="layer">
                <div className="layer-box layer-result w430">
                    <div className="layer-header">
                        <span>结算</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>settlementModal(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="mc-top">
                            <div className="item">
                                <div className="mlt">销售合计金额</div>
                                <div className="mrt">
                                    <input type="text" value={shoppingCart.goodsTotalAmount} disabled="disabled"/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">应收金额</div>
                                <div className="mrt">
                                    <input type="text" value={shoppingCart.orderTotalAmount} disabled="disabled"/>
                                </div>
                            </div>
                        </div>
                        <div className="mc-bot">
                            <div className="item">
                                <div className="mlt">医保[F5]</div>
                                <div className="mrt">
                                    <input type="text" ref="healthInsurancePay" defaultValue="0" value={healthInsurancePayAmount} onChange={(e) => this.healthInsurancePayChange(e.target.value)}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">实收现金[F1]</div>
                                <div className="mrt">
                                    <input type="text" value={cashPayAmount} autoFocus={isCashPay ? "autoFocus" : ""} onChange={(e)=>this.cashPayChange(e.target.value)} ref="cashPay" disabled={isCashPay ? "" : "disabled"}/>
                                </div>
                            </div>
                            <div className="item" style={{textAlign: "center"}} id="paymentWay">
                                <label><input type="radio" name="paymentWayCode" value="ALIPAY_PAY" ref="alipay" checked={paymentWayCode == "ALIPAY_PAY" ? "checked" : ""} onClick={() => this.changePayWay(false, "ALIPAY_PAY")}/>支付宝[F2]</label>
                                <label><input type="radio" name="paymentWayCode" value="WEBCHAT_PAY" ref="webChatPay" checked={paymentWayCode == "WEBCHAT_PAY" ? "checked" : "" } onClick={() => this.changePayWay(false, "WEBCHAT_PAY")}/>微信[F3]</label>
                                <label><input type="radio" name="paymentWayCode" value="BANK_CARD_PAY" ref="bankCardPay"  checked={paymentWayCode == "BANK_CARD_PAY" ? "checked" : ""} onClick={() => this.changePayWay(false, "BANK_CARD_PAY")}/>银行卡[F4]</label>
                            </div>
                            <div className="item">
                                <div className="mlt">找零</div>
                                <div className="mrt">
                                    <input type="text" ref="returnCash" value={returnCash} disabled="disabled"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <a href="javascript:void(0);" className="confirm" onClick={() => this.saveSalesPosOrder(uniqueKey, healthInsurancePayAmount, cashPayAmount, resultPaymentWayCode,isCashPay,shoppingCart,returnCash,loginCashierData)}>确定[Enter]</a>
                        <a href="javascript:void(0);" className="cancel" onClick={()=>settlementModal(false)}>取消[Esc]</a>
                    </div>
                </div>
            </div>
        )
    }
}

PosSettlementModal.contextTypes = {
    store: React.PropTypes.object
};

export default PosSettlementModal;
