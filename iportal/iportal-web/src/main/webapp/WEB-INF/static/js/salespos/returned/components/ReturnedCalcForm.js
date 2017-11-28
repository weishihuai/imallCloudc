import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import ValidForm from "../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../common/validForm/actions";
import {validate} from "../../../common/redux-form-ext";

import {
    RETURNED_PURCHASE_REAL_RETURN_CASH_AMOUNT_CHANGE
} from "../constants/ActionTypes";

const calcFields = [{
        field: 'refundTotalAmount',
        validate: {
            required: false
        }
    },
    {
        field: 'realReturnCashAmount',
        validate: {
            required: true,
            maxlength: 12,
            fieldNm: "实退金额",
        }
    },
    {
        field: 'returnSmall',
        validate: {
            required: false
        }
    }
];


export const inputField = ({input, label, type, id,value, required,event, maxLength, meta: {touched, error}}) => (
        <input id={id} name={input.name} type={type} maxLength={maxLength} value={value} {...input} onChange={event} />
);

export const disabledInputField = ({input, label, type, id, required, disabled, meta: {touched, error}}) => (
        <input id={id} name={input.name} type={type} disabled={disabled} {...input}/>
);

class ReturnedCalcForm extends Component {

    constructor(props) {
        super(props);
        this.listenKeyBoard = this.listenKeyBoard.bind(this);

    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {
        window.addEventListener('keydown', this.listenKeyBoard);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.listenKeyBoard);
    }

    listenKeyBoard(event){
        const oldjs = document.getElementById("notyLayer");
        const {returnedCalcForm} = this.props.actions;
        switch(event.keyCode) {
            //esc
            case 27:
                event.preventDefault();
                if (oldjs) {
                    oldjs.parentNode.removeChild(oldjs);
                    break;
                }
                returnedCalcForm(false);
                break;
        }
    }

    realReturnCashAmountChangeEvent(e){
        const {store} = this.context;
        let value = e.target.value.trim();
        let calcData = store.getState().todos.calcData;
        let newReturnSmall = calcData.returnSmall;
        const patt = new RegExp(/^\d+(\.\d{1})?$/);
        const point = new RegExp(/^\d+\.$/);
        const zero = new RegExp(/^0\d$/);

        if(value == "") {
            newReturnSmall =  calcData.refundTotalAmount;
        } else if(patt.test(value)) {
            newReturnSmall = parseFloat(value)  - calcData.refundTotalAmount;
        } else if(point.test(value)) {

        } else {
            value = calcData.realReturnCashAmount;
        }
        if(zero.test(value)){
            value = value - 0;
        }
        store.dispatch({type: RETURNED_PURCHASE_REAL_RETURN_CASH_AMOUNT_CHANGE, data: Object.assign({},calcData,{
            realReturnCashAmount:value,
            returnSmall:parseFloat(newReturnSmall.toFixed(1))
        })});
    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting} = this.props;
        const {validFormState, errorValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;
        const realReturnCashAmount = this.context.store.getState().todos.calcData.realReturnCashAmount;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage != "" && <ValidForm checkValidForm={checkValidForm}/>}

                <div className="layer">
                    <div className="layer-box layer-result w430">
                        <div className="layer-header">
                            <span>结算</span>
                            <a href="javascript:void(0);" className="close"
                               onClick={(e) => actions.returnedCalcForm(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt">应退金额:</div>
                                <div className="mrt">
                                    <Field id="refundTotalAmount" name="refundTotalAmount" component={disabledInputField} label=""
                                           required="required" type="text" disabled="true" actions={actions}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">实退金额:</div>
                                <div className="mrt">
                                    <Field id="realReturnCashAmount" name="realReturnCashAmount" component={inputField} value={realReturnCashAmount}
                                           label="" required="required" type="text" maxLength="12"
                                            event={this.realReturnCashAmountChangeEvent.bind(this)}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">找零:</div>
                                <div className="mrt">
                                    <Field id="returnSmall" name="returnSmall" component={disabledInputField} label=""
                                           required="required" type="text" disabled="true" actions={actions}/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" type="submit" className="confirm" style={{border: "none"}} disabled={submitting}
                                    onClick={(e) => {
                                        checkValidForm(true)
                                    }}>{submitting ? <i/> : <i/>}确定
                            </button>
                            <a href="javascript:void(0);" className="cancel"
                               onClick={(e) => actions.returnedCalcForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

ReturnedCalcForm.contextTypes = {
    store: React.PropTypes.object
};

ReturnedCalcForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

ReturnedCalcForm = reduxForm({
    form: "returnedCalcForm",
    enableReinitialize: true,
    validate
})(ReturnedCalcForm);


function mapStateToProps(state) {
    return {
        initialValues: state.todos.calcData,
        fields: calcFields,
        validate: validate,
        state
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ReturnedCalcForm);