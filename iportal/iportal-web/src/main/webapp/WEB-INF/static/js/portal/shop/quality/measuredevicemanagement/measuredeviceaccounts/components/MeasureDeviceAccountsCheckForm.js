import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField,validate} from "../../../../../../common/redux-form-ext";
import {checkValidForm,errorValidMessageFunction} from "../../../../../../common/validForm/actions";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";

const fields = [{
        field: 'measureDateString',
        validate: {
            required: true,
            fieldNm: "检测时间",
            }
    }, {
        field: 'startTimeString',
        validate: {
            required: true,
            fieldNm: "开始时间",
        }
    }, {
        field: 'endTimeString',
        validate: {
            required: true,
            fieldNm: "结束时间",
        }
    }, {
        field: 'identifyPrj',
        validate: {
            required: false,
            fieldNm: "鉴定项目",
            maxlength:32,
        }
    }, {
        field: 'skillRequirement',
        validate: {
            required: false,
            fieldNm: "技术要求",
            maxlength:32,
        }
    },{
        field: 'measureMethod',
        validate: {
            required: false,
            fieldNm: "检测方法",
            maxlength:32,
        }
    }, {
        field: 'identifyConclusion',
        validate: {
            required: false,
            fieldNm: "鉴定结论",
            maxlength:32,
        }
    },{
        field: 'measureMan',
        validate: {
            required: true,
            fieldNm: "检测人",
            maxlength:32,
        }
    },{
        field: 'reviewMan',
        validate: {
            required: true,
            maxlength:32,
            fieldNm: "复检人",
        }
    },{
        field: 'remark',
        validate: {
            required: false,
            fieldNm: "备注",
            maxlength:32
        }
    }
];

class MeasureDeviceAccountsCheckForm extends Component {

    constructor(props) {
        super(props);
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        const {change} = this.props;
        $("#measureDateString").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("measureDateString", $('input[name="measureDateString"]').val());
                }
            });
        });

        $("#startTimeString").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("startTimeString", $('input[name="startTimeString"]').val());
                }
            });
        });

        $("#endTimeString").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("endTimeString", $('input[name="endTimeString"]').val());
                }
            });
        });
    }

    render() {
        const {actions} = this.props;
        const { handleSubmit, submitting } = this.props;
        const {validFormState, errorValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order w960">
                        <div className="layer-header">
                            <span>检测计量器具</span>
                            <a href="javascript:void(0);" className="close" onClick={() => actions.measuringDeviceAccountsCheckModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <Field id="measureDateString" name="measureDateString" required="required" inputClassName="form-control datepicker" type="text" component={inputField} label="检测时间" readOnly/>
                                    <Field id="startTimeString" name="startTimeString" required="required" inputClassName="form-control datepicker" type="text" component={inputField} label="开始时间" readOnly/>
                                    <Field id="endTimeString" name="endTimeString" required="required" inputClassName="form-control datepicker" type="text" component={inputField} label="结束时间" readOnly/>
                                    <Field id="identifyPrj" name="identifyPrj" type="text" component={inputField} label="鉴定项目" maxLength="32"/>
                                    <Field id="skillRequirement" name="skillRequirement" type="text" component={inputField} label="技术要求" maxLength="32"/>
                                    <Field id="measureMethod" name="measureMethod" type="text" component={inputField} label="检测方法" maxLength="32"/>
                                    <Field id="identifyConclusion" name="identifyConclusion" type="text" component={inputField} label="鉴定结论" maxLength="32"/>
                                    <Field id="measureMan" name="measureMan" type="text" required="required" component={inputField} label="检测人" maxLength="32"/>
                                    <Field id="reviewMan" name="reviewMan" type="text" required="required" component={inputField} label="复检人" maxLength="32"/>
                                    <Field id="remark" name="remark" type="text" component={inputField} label="备注" maxLength="32"/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => actions.measuringDeviceAccountsCheckModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

MeasureDeviceAccountsCheckForm.contextTypes = {
    store: React.PropTypes.object
};

MeasureDeviceAccountsCheckForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

MeasureDeviceAccountsCheckForm = reduxForm({
    form: "measureDeviceAccountsCheckForm",
    enableReinitialize: true,
})(MeasureDeviceAccountsCheckForm);


function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        fields: fields,
        state,
        validate
    }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm,errorValidMessageFunction}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(MeasureDeviceAccountsCheckForm);