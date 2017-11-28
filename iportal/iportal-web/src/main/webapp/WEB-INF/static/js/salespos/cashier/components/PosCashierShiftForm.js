import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import ValidForm from "../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction} from "../../../common/validForm/actions";
import {validate} from "../../../common/redux-form-ext";
import {REGEXP_DOUBLE_1} from "../../../common/common-constant";

const shiftFields = [{
        field: 'succeedReadyAmount',
        validate: {
            required: true,
            maxlength: 12,
            regx:REGEXP_DOUBLE_1,
            error:"接班时备用金为最多一位的小数的数字",
            fieldNm: "接班时备用金",
        }
    },
    {
        field: 'keepReadyAmount',
        validate: {
            required: true,
            maxlength: 12,
            regx:REGEXP_DOUBLE_1,
            error:"留用备用金为最多一位的小数的数字",
            fieldNm: "留用备用金",
        }
    },
    {
        field: 'handoverCashAmount',
        validate: {
            required: true,
            maxlength: 12,
            regx:REGEXP_DOUBLE_1,
            error:"交接现金为最多一位的小数的数字",
            fieldNm: "交接现金",
        }
    },
];


export const inputField = ({input, label, type, id, required, maxLength, meta: {touched, error}}) => (
        <input id={id} name={input.name} type={type} maxLength={maxLength} {...input}/>
);

export const disabledInputField = ({input, label, type, id, required, disabled, meta: {touched, error}}) => (
        <input id={id} name={input.name} type={type} disabled={disabled} {...input}/>
);

class PosCashierShiftForm extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.getShiftRecord();
    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting} = this.props;
        const {validFormState, errorValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage != "" && <ValidForm checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-team w715">
                        <div className="layer-header">
                            <span>交班</span>
                            <a href="javascript:void(0);" className="close"
                               onClick={(e) => actions.shiftModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>收款员</p>
                                        <Field id="posManName" name="posManName" component={disabledInputField} label=""
                                               required="required" type="text" disabled="true" actions={actions}/>
                                    </div>
                                    <div className="item">
                                        <p>接班时间</p>
                                        <Field id="succeedTimeString" name="succeedTimeString" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"
                                               actions={actions}/>
                                    </div>
                                    <div className="item">
                                        <p>交班时间</p>
                                        <Field id="shiftTimeString" name="shiftTimeString" component={disabledInputField} label=""
                                               required="required" type="text" disabled="true" actions={actions}/>
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">销售情况</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>收款金额</p>
                                        <Field id="receiptAmount" name="receiptAmount" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"/>
                                    </div>
                                    <div className="item">
                                        <p>退货金额</p>
                                        <Field id="returnedPurchaseAmount" name="returnedPurchaseAmount"
                                               component={disabledInputField} label="" required="required" type="text"
                                               disabled="true" />
                                    </div>
                                    <div className="item">
                                        <p>合计金额</p>
                                        <Field id="addUpAmount" name="addUpAmount" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"/>
                                    </div>
                                    <div className="item">
                                        <p>现金</p>
                                        <Field id="cashAmount" name="cashAmount" component={disabledInputField} label=""
                                               required="required" type="text" disabled="true" />
                                    </div>
                                    <div className="item">
                                        <p>银行卡</p>
                                        <Field id="bankCardAmount" name="bankCardAmount" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"/>
                                    </div>
                                    <div className="item">
                                        <p>微信</p>
                                        <Field id="wechatAmount" name="wechatAmount" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"/>
                                    </div>
                                    <div className="item">
                                        <p>支付宝</p>
                                        <Field id="alipayAmount" name="alipayAmount" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"
                                              />
                                    </div>
                                    <div className="item">
                                        <p>医保</p>
                                        <Field id="medicalInsuranceAmount" name="medicalInsuranceAmount"
                                               component={disabledInputField} label="" required="required" type="text"
                                               disabled="true" />
                                    </div>
                                    <div className="item">
                                        <p>订单数量</p>
                                        <Field id="orderQuantity" name="orderQuantity" component={disabledInputField}
                                               label="" required="required" type="text" disabled="true"
                                               />
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">交接数据</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p><i style={{color: "#ff0000",paddingRight: "2px"}}>*</i>接班时备用金</p>
                                        <Field id="succeedReadyAmount" name="succeedReadyAmount" component={inputField}
                                               label="" required="required" type="text" maxLength="12"/>
                                    </div>
                                    <div className="item">
                                        <p><i style={{color: "#ff0000",paddingRight: "2px"}}>*</i>留用备用金</p>
                                        <Field id="keepReadyAmount" name="keepReadyAmount" component={inputField}
                                               label="" required="required" type="text" maxLength="12"
                                               />
                                    </div>
                                    <div className="item">
                                        <p><i style={{color: "#ff0000",paddingRight: "2px"}}>*</i>交接现金</p>
                                        <Field id="handoverCashAmount" name="handoverCashAmount" component={inputField}
                                               label="" required="required" type="text" maxLength="12"
                                              />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border: "none"}} disabled={submitting}
                                    onClick={() => {
                                        checkValidForm(true)
                                    }}>{submitting ? <i/> : <i/>}交班
                            </button>
                            <a href="javascript:void(0);" className="cancel"
                               onClick={(e) => actions.shiftModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

PosCashierShiftForm.contextTypes = {
    store: React.PropTypes.object
};

PosCashierShiftForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

PosCashierShiftForm = reduxForm({
    form: "posCashierShiftForm",
    enableReinitialize: true,
    validate
})(PosCashierShiftForm);


function mapStateToProps(state) {
    return {
        initialValues: state.todos.shiftData,
        fields: shiftFields,
        validate: validate,
        state
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PosCashierShiftForm);