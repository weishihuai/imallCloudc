import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm,change} from "redux-form";
import {validate,inputField,selectField,boolRadioField} from '../../../../../common/redux-form-ext'
import ValidForm from '../../../../../common/validForm/components/ValidForm';
import {checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from '../../../../../common/validForm/actions';
import {REGEXP_PHONE,ERROR_PHONE_FORMAT} from "../../../../../common/common-constant";
import TimeSelect from "../../../../../common/component/TimeSelect.js";
import {asyncValidateForSave} from "../actions/asyncValidate";

//会员档案信息 - 发卡状态
const fields_timeValid = [{
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
            regx: REGEXP_PHONE,
            error: ERROR_PHONE_FORMAT,
            fieldNm: "手机号"
        }
    }, {
        field: 'memberCardNum',
        validate: {
            required: false,
            maxlength: 32,
            fieldNm: "会员卡号",
        }
    }, {
        field: 'sexCode',
        validate: {
            required: true,
            maxlength: 32,
            fieldNm: "性别",
        }
    }, {
        field: 'isMedicalInsuranceCard',
        validate: {
            required: false,
            maxlength: 2,
            fieldNm: "医保卡",
        }
    }, {
        field: 'profession',
        validate: {
            required: false,
            maxlength: 32,
            fieldNm: "职业",
        }
    }, {
        field: 'birthdayString',
        validate: {
            required: false,
            fieldNm: "生日",
        }
    }, {
        field: 'diseaseHistory',
        validate: {
            required: false,
            maxlength: 512,
            fieldNm: "病史",
        }
    }, {
        field: 'commonlyUsedDrugs',
        validate: {
            required: false,
            maxlength: 512,
            fieldNm: "常用药",
        }
    }, {
        field: 'remark',
        validate: {
            required: false,
            maxlength: 512,
            fieldNm: "备注",
        }
    }, {
        field: 'homeAddr',
        validate: {
            required: false,
            maxlength: 128,
            fieldNm: "住址",
        }
    }, {
        field: 'isGiveCard',
        validate: {
            required: false,
            maxlength: 2,
            fieldNm: "是否发卡",
        }
    }, {
        field: 'cardUseStateCode',
        validate: {
            required: false,
            maxlength: 32,
            fieldNm: "卡使用状态",
        }
    }, {
        field: 'giveCardMan',
        validate: {
            required: false,
            maxlength: 32,
            fieldNm: "发卡人",
        }
    }, {
        field: 'giveCardTimeString',
        validate: {
            required: false,
            fieldNm: "发卡时间",
        }
    }, {
        field: 'effectTimeString',
        validate: {
            required: false,
            fieldNm: "生效时间",
        }
    }, {
        field: 'expireTimeString',
        validate: {
            required: false,
            fieldNm: "失效时间",
        }
    }
];

//会员档案信息 - 不发卡状态
const fields_noTimeValid = [{
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
            error: ERROR_PHONE_FORMAT
        }
    }, {
        field: 'memberCardNum',
        validate: {
            required: false,
            maxlength: 32,
            fieldNm: "会员卡号",
        }
    }, {
        field: 'sexCode',
        validate: {
            required: true,
            maxlength: 32,
            fieldNm: "性别",
        }
    }, {
        field: 'isMedicalInsuranceCard',
        validate: {
            required: false,
            maxlength: 2,
            fieldNm: "医保卡",
        }
    }, {
        field: 'profession',
        validate: {
            required: false,
            maxlength: 32,
            fieldNm: "职业",
        }
    }, {
        field: 'birthdayString',
        validate: {
            required: false,
            fieldNm: "生日",
        }
    }, {
        field: 'diseaseHistory',
        validate: {
            required: false,
            maxlength: 512,
            fieldNm: "病史",
        }
    }, {
        field: 'commonlyUsedDrugs',
        validate: {
            required: false,
            maxlength: 512,
            fieldNm: "常用药",
        }
    }, {
        field: 'remark',
        validate: {
            required: false,
            maxlength: 512,
            fieldNm: "备注",
        }
    }, {
        field: 'homeAddr',
        validate: {
            required: false,
            maxlength: 128,
            fieldNm: "住址",
        }
    }, {
        field: 'isGiveCard',
        validate: {
            required: true,
            maxlength: 2,
            fieldNm: "是否发卡",
        }
}];

/*时间输入框*/
export const timeInputField = ({input, label, type, id, timeInput, required, meta: {touched, error}}) => (
    <div className="item">
        <p><i>{required == undefined ? '' : '*'}</i>{label}</p>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required}
               type={type} {...input} readOnly/>
    </div>
);

/*会员卡使用状态*/
export const cardUseStateSelectField = ({ input, className, label, id, required, items,event=()=>{}, optionValue, optionName,disabled, meta: { touched, error } }) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <select id={id ? id : ""} name={input.name} className={"select " + (className || '')} {...input} disabled ={disabled  ? disabled :""} onChange={e => {input.onChange(e);event(e.target.value)}}>
            {
                items.map((item,index)=>{
                    return (
                        <option key={index} value={item[optionValue]}>{item[optionName]}</option>
                    )
                })
            }
        </select>
    </div>
);

class ShopMemberAddForm extends Component {

    constructor(props) {
        super(props);
    }

    componentDidUpdate() {
        $("#memberCardNum").val($("#mobile").val());
    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("birthdayString", $('input[name="birthdayString"]').val());
                    if ($('input[name="giveCardTimeString"]').val() != null) {
                        change("giveCardTimeString", $('input[name="giveCardTimeString"]').val());
                    }
                    if ($('input[name="effectTimeString"]').val() != null) {
                        change("effectTimeString", $('input[name="effectTimeString"]').val());
                    }
                    if ($('input[name="expireTimeString"]').val() != null) {
                        change("expireTimeString", $('input[name="expireTimeString"]').val());
                    }
                }
            });
        });
    }

    //提交数据
    submit(data) {
        const isGiveCard = this.context.store.getState().form.shopMemberAddForm.values.isGiveCard;
        const {params} = this.context.store.getState().todos;
        if(isGiveCard === "Y") {
            if(!$("#effectTimeString").val()){
                this.props.errorValidMessageFunction("生效时间不能为空");
                return;
            }

            if(!$("#expireTimeString").val()){
                this.props.errorValidMessageFunction("失效时间不能为空");
                return;
            }
            data = Object.assign({},data,{
                giveCardTimeString:$("#giveCardTimeString").val(),
                effectTimeString:$("#effectTimeString").val(),
                expireTimeString:$("#expireTimeString").val()
            });
        }
        return this.props.actions.memberAddData(data, params);
    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting} = this.props;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState,asyncValidMessage} = this.context.store.getState().validTodos;
        let {isGiveCard} = this.context.store.getState().form.shopMemberAddForm.values;
        const {change} = this.props;
        const validState = asyncValidMessage != "" || errorValidMessage != "";

        const sexCodeSelectFieldValue = [
            {
                name:"男",
                code:"MALE"
            },
            {
                name:"女",
                code:"FEMALE"
            },
            {
                name:"保密",
                code:"SECRET"
            }
        ];

        const cardUseStateCodeSelectFieldValue = [
            {
                name:"正常",
                code:"NORMAL"
            },
            {
                name:"禁用",
                code:"DISABLED"
            }
        ];

        function isGiveCardFieldRender(value) {
            if (value === "Y") {
                return (
                    <div>
                        <Field name="cardUseStateCode" id="cardUseStateCode" label="使用状态" component={cardUseStateSelectField} items={cardUseStateCodeSelectFieldValue} optionName="name" optionValue="code"/>
                        <Field id="giveCardMan" name="giveCardMan" type="text" component={inputField} label="发卡人"/>
                        <div className="item">
                            <p>发卡时间</p>
                            <TimeSelect id="giveCardTimeString" change={change} name="giveCardTimeString" type="text" className="form-control datepicker"  label="发卡时间" readOnly="readOnly"/>
                        </div>
                        <div className="item">
                            <p><i>*</i>生效时间</p>
                            <TimeSelect id="effectTimeString" change={change} name="effectTimeString" className="form-control datepicker" type="text"  label="生效时间" required="required" readOnly="readOnly"/>
                        </div>
                        <div className="item">
                            <p><i>*</i>失效时间</p>
                            <TimeSelect id="expireTimeString" change={change} name="expireTimeString" className="form-control datepicker"  type="text" label="失效时间" required="required" readOnly="readOnly"/>
                        </div>
                    </div>
                )
            }
            else {
                return (<div/>)
            }
        }

        return (
            <form className="form-horizontal" onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && validState && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order layer-consulting-register w960">
                        <div className="layer-header">
                            <span>添加会员档案</span>
                            <a href="javascript:void(0);" className="close" onClick={(e) => actions.memberAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">基本信息</div>
                                <div className="box-mc clearfix">
                                    <Field id="name" name="name" type="text" component={inputField} label="姓名" maxLength="32"  required="required"/>
                                    <Field id="mobile" name="mobile" type="text" component={inputField} label="手机号" maxLength="32" required="required"/>
                                    <Field id="memberCardNum" name="memberCardNum" type="text" component={inputField} label="会员卡号"  maxLength="32" readOnly="readOnly"/>
                                    <Field name="sexCode" id="sexCode" label="性别" required="required" component={selectField} items={sexCodeSelectFieldValue} optionName="name" optionValue="code"/>
                                    <Field id="isMedicalInsuranceCard" name="isMedicalInsuranceCard" type="text" component={boolRadioField} label="医保卡"/>
                                    <Field id="profession" name="profession" type="text" component={inputField} maxLength="32" label="职业"/>
                                    <Field id="birthdayString" name="birthdayString" timeInput="datepicker" actions={actions} type="text" component={timeInputField} label="生日"/>
                                    <Field id="diseaseHistory" name="diseaseHistory" type="text" component={inputField} label="病史" maxLength="512"/>
                                    <Field id="commonlyUsedDrugs" name="commonlyUsedDrugs" type="text" component={inputField} label="常用药" maxLength="512"/>
                                    <Field id="remark" name="remark" type="text" component={inputField} label="备注" maxLength="512"/>
                                    <Field id="homeAddr" name="homeAddr" type="text" component={inputField} className="item-problem-description" label="地址" maxLength="128"/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">发卡信息</div>
                                <div className="box-mc clearfix">
                                    <Field id="isGiveCard" name="isGiveCard" type="text" component={boolRadioField} label="是否发卡"/>
                                    {isGiveCardFieldRender(isGiveCard)}
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" type="submit" style={{border: "none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={(e) => actions.memberAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

ShopMemberAddForm.contextTypes = {
    store: React.PropTypes.object
};

ShopMemberAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

ShopMemberAddForm = reduxForm({
    form: "shopMemberAddForm",
    enableReinitialize: true,
    validate,
    asyncBlurFields: ['mobile'],
    asyncValidate: asyncValidateForSave
})(ShopMemberAddForm);


function mapStateToProps(state) {
    let fields = [];
    const shopMemberAddForm = state.form.shopMemberAddForm || {};
    const values = shopMemberAddForm.values || {};
    const isGiveCard = values.isGiveCard || "Y";
    switch (isGiveCard) {
        case "Y":
            fields = fields_timeValid;
            break;
        case "N":
            fields = fields_noTimeValid;
            break;
        default:
            fields = fields_timeValid;
    }
    return {
        initialValues: Object.assign({},state.todos.data,{
            cardUseStateCode:"NORMAL"
        }),
        fields: fields,
        state
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShopMemberAddForm);