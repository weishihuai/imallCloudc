import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import ValidForm from "../../../common/validForm/components/ValidForm";
import {checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from "../../../common/validForm/actions";
import {validate} from "../../../common/redux-form-ext";
import {REGEXP_PHONE,ERROR_PHONE_FORMAT} from "../../../common/common-constant";
import {asyncValidateForSave} from "../actions/asyncValidate";

const fields = [{
    field: 'name',
    validate: {
        required: true,
        maxlength: 32,
        fieldNm: "姓名",
    }
}, {
    field: 'mobile',
    validate: {
        required: true,
        maxlength: 32,
        fieldNm: "手机号",
        regx: REGEXP_PHONE,
        error: ERROR_PHONE_FORMAT,
    }
}, {
    field: 'memberStateCode',
    validate: {
        required: true,
        fieldNm: "状态",
        maxlength: 32,
    }
}, {
    field: 'memberCardNum',
    validate: {
        required: false,
        fieldNm: "会员卡号",
        maxlength: 32,
    }
}, {
    field: 'giveCardMan',
    validate: {
        required: false,
        fieldNm: "发卡人",
        maxlength: 32,
    }
}, {
    field: 'homeAddr',
    validate:{
        required: false,
        fieldNm: "住址",
        maxlength: 128,
    }
}
];

export const boolRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div>
        <label>
            <input type="radio" {...input} value="NORMAL" checked={input.value === 'NORMAL'} style={{width:"13px",height:"13px"}}/> 启用
        </label>
        <label>
            <input type="radio" {...input} value="DISABLED" checked={input.value === 'DISABLED'} style={{width:"13px",height:"13px"}}/> 未启用
        </label>
    </div>
);

export const inputField = ({ input, label, type, id, required, disabled,meta: { touched, error } }) => (
    <div className="mrt">
        <input id={id} name={input.name} type={type}  {...input} disabled={disabled}/>
    </div>
);

class PosMemberAddForm extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.getLoginCashier();
    }

    componentDidUpdate() {
        $("#memberCardNum").val($("#mobile").val());
    }

    componentDidMount() {

    }

    render() {
        const {actions} = this.props;
        const { handleSubmit, submitting } = this.props;
        const {validFormState, errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;
        const validState = asyncValidMessage != "" || errorValidMessage != "";

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && validState && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-member w430">
                        <div className="layer-header">
                            <span>新增会员</span>
                            <a href="javascript:void(0);" className="close" onClick={(e) => actions.memberAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt"><i style={{color: "#ff0000",paddingRight: "2px"}}>*</i>姓&nbsp;&nbsp;名</div>
                                <div className="mrt">
                                    <Field name="name" component={inputField} label="" required="required"  maxLength="32" type="text" actions={actions}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt"><i style={{color: "#ff0000",paddingRight: "2px"}}>*</i>手 机 号</div>
                                <div className="mrt">
                                    <Field name="mobile" component={inputField} label="" required="required" maxLength="32" type="text" id="mobile" actions={actions}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt"><i style={{color: "#ff0000",paddingRight: "2px"}}>*</i>状态</div>
                                <div className="mrt">
                                    <Field id="memberStateCode" name="memberStateCode" type="text" component={boolRadioField} label="" required="required" actions={actions}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">发 卡 人</div>
                                <div className="mrt">
                                    <Field name="giveCardMan" component={inputField} label="" maxLength="32" type="text" disabled="disabled"/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">会员卡号</div>
                                <div className="mrt">
                                    <Field name="memberCardNum" component={inputField} label="" type="text" id="memberCardNum" disabled="disabled"/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">住&nbsp;&nbsp;址</div>
                                <div className="mrt">
                                    <Field name="homeAddr" component={inputField} label="" maxLength="128" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={(e) => actions.memberAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

PosMemberAddForm.contextTypes = {
    store: React.PropTypes.object
};

PosMemberAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

PosMemberAddForm = reduxForm({
    form: "posMemberAddForm",
    enableReinitialize: true,
    validate,
    asyncBlurFields: ['mobile'],
    asyncValidate: asyncValidateForSave
})(PosMemberAddForm);


function mapStateToProps(state) {
    return {
        initialValues: Object.assign({},state.todos.data,{
            giveCardMan:state.todos.loginCashierData.userName || "",
            memberStateCode:"NORMAL",
        }),
        fields: fields,
        validate:validate,
        state
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PosMemberAddForm);