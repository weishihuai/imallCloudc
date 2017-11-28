import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {REGEXP_UNSIGNED_INT,REGEXP_PRICE,ERROR_INT,ERROR_REGEXP_PRICE_DOUBLE_COMMON,ERROR_UNSIGNED_INT} from "../../../../../../common/common-constant";
import {inputField,validate,selectField} from "../../../../../../common/redux-form-ext";
import ValidForm from "../../../../../../common/validForm/components/ValidForm";
import {checkValidForm,errorValidMessageFunction} from "../../../../../../common/validForm/actions";

const fields = [{
    field: 'measuringDeviceNum',
    validate: {
        required: true,
        maxlength: 32,
        fieldNm: "计量器具编号",
    }
    }, {
        field: 'manufacturingNum',
        validate: {
            required: false,
            fieldNm: "出厂编号",
            maxlength:32,
        }
    }, {
        field: 'deviceNm',
        validate: {
            required: true,
            maxlength: 128,
            fieldNm: "名称",
        }
    }, {
        field: 'model',
        validate: {
            required: false,
            fieldNm: "型号",
            maxlength:32,
        }
    }, {
        field: 'produceManufacturer',
        validate: {
            required: false,
            fieldNm: "生产厂商",
            maxlength:64,
        }
    },{
        field: 'categoryNum',
        validate: {
            required: false,
            fieldNm: "分类编号",
            maxlength:32,
        }
    }, {
        field: 'measureRange',
        validate: {
            required: false,
            fieldNm: "测量范围",
            maxlength:32,
        }
    },{
        field: 'precisionLevel',
        validate: {
            required: false,
            fieldNm: "精度等级",
            maxlength:32,
        }
    },{
        field: 'responseMan',
        validate: {
            required: true,
            maxlength:32,
            fieldNm: "负责人",
        }
    },{
        field: 'measurePeriod',
        validate: {
            required: true,
            maxlength:10,
            fieldNm: "检测周期",
            regx:REGEXP_UNSIGNED_INT,
            error:  "检测周期" +　ERROR_INT,
        }
    }, {
        field: 'purchasePrice',
        validate: {
            required: false,
            regx:REGEXP_PRICE,
            fieldNm: "购置价格",
            error:ERROR_REGEXP_PRICE_DOUBLE_COMMON,
            maxlength:22
        }
    },{
        field: 'purchaseDateString',
        validate: {
            required: false,
            fieldNm: "购买日期",
        }
    },{
        field: 'enableTimeString',
        validate: {
            required: false,
            fieldNm: "启用时间",
        }
    }, {
        field: 'purchasePlace',
        validate: {
            required: false,
            fieldNm: "购置地点",
            maxlength:128
        }
    },{
        field: 'application',
        validate: {
            required: false,
            fieldNm: "用途",
            maxlength:128
        }
    },{
        field: 'serviceLife',
        validate: {
            regx:REGEXP_UNSIGNED_INT,
            maxlength:10,
            minValue:0,
            maxValue:100,
            fieldNm: "使用年限",
            error:"使用年限" +　ERROR_UNSIGNED_INT,
        }
    },{
        field: 'useStateCode',
        validate: {
            required: false,
            fieldNm: "使用状态代码",
        }
    },{
        field: 'remark',
        validate: {
            required: false,
            fieldNm: "备注",
            maxlength:128
        }
    }
];

class MeasureDeviceAccountsUpdateForm extends Component {

    constructor(props) {
        super(props);
    }

    componentDidUpdate() {

    }

    componentDidMount() {
        const {change} = this.props;
        $("#purchaseDateString").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("purchaseDateString", $('input[name="purchaseDateString"]').val());
                }
            });
        });
        $("#enableTimeString").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    change("enableTimeString", $('input[name="enableTimeString"]').val());
                }
            });
        });
    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting} = this.props;
        const {validFormState,errorValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;
        const useStateCodeValue = [
            {
                name: "启用",
                code: "ENABLED",
            },
            {
                name: "禁用",
                code: "DISABLED",
            },
            {
                name: "封存",
                code: "SEAL_UP",
            },
            {
                name: "报废",
                code: "SCRAP",
            }
        ];

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order w960">
                        <div className="layer-header">
                            <span>修改计量器具</span>
                            <a href="javascript:void(0);" className="close" onClick={() => actions.measuringDeviceAccountsUpdateModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <Field id="measuringDeviceNum" name="measuringDeviceNum" type="text" required="required" component={inputField} label="计量器具编号" maxLength="32"/>
                                    <Field id="manufacturingNum" name="manufacturingNum" type="text" component={inputField} label="出厂编号" maxLength="32"/>
                                    <Field id="deviceNm" name="deviceNm" type="text" required="required" component={inputField} label="名称" maxLength="128"/>
                                    <Field id="model" name="model" type="text" component={inputField} label="型号" maxLength="32"/>
                                    <Field id="produceManufacturer" name="produceManufacturer" type="text" component={inputField} label="生产厂商" maxLength="64"/>
                                    <Field id="categoryNum" name="categoryNum" type="text" component={inputField} label="分类编号" maxLength="32"/>
                                    <Field id="measureRange" name="measureRange" type="text" component={inputField} label="测量范围" maxLength="32"/>
                                    <Field id="precisionLevel" name="precisionLevel" type="text" component={inputField} label="精度等级" maxLength="32"/>
                                    <Field id="responseMan" name="responseMan" type="text" required="required" component={inputField} label="负责人" maxLength="32"/>
                                    <Field id="measurePeriod" name="measurePeriod" type="text" required="required" component={inputField} label="检测周期（天）" maxLength="10"/>
                                    <Field id="purchasePrice" name="purchasePrice" type="text" component={inputField} label="购置价格" maxLength="22"/>
                                    <Field id="purchaseDateString" name="purchaseDateString"  inputClassName="form-control datepicker" type="text" label="购买日期" readOnly="readOnly" component={inputField} />
                                    <Field id="enableTimeString" name="enableTimeString" inputClassName="form-control datepicker" type="text" label="启用时间" readOnly="readOnly" component={inputField} />
                                    <Field id="purchasePlace" name="purchasePlace" type="text" component={inputField} label="购置地点" maxLength="128"/>
                                    <Field id="application" name="application" type="text" component={inputField} label="用途" maxLength="128"/>
                                    <Field id="serviceLife" name="serviceLife" type="text" component={inputField} label="使用年限" maxLength="10"/>
                                    <Field id="useStateCode" name="useStateCode" type="text" label="使用状态"  component={selectField} items={useStateCodeValue} optionName="name" optionValue="code"/>
                                    <Field id="remark" name="remark" type="text" component={inputField} label="备注" maxLength="128"/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => actions.measuringDeviceAccountsUpdateModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        )
    }
}

MeasureDeviceAccountsUpdateForm.contextTypes = {
    store: React.PropTypes.object
};

MeasureDeviceAccountsUpdateForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

MeasureDeviceAccountsUpdateForm = reduxForm({
    form: "measureDeviceAccountsUpdateForm",
    enableReinitialize: true,
})(MeasureDeviceAccountsUpdateForm);


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

export default connect(mapStateToProps, mapDispatchToProps)(MeasureDeviceAccountsUpdateForm);