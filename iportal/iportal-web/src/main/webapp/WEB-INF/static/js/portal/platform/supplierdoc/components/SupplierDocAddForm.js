/**
 * Created by ou on 2017/7/11.
 */
import {checkValidForm, initValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from "../../../../common/validForm/actions";
import React, {PropTypes} from "react";
import {Field, reduxForm, change} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {validate, inputField} from "../../../../common/redux-form-ext";
import ValidForm from "../../../../common/validForm/components/ValidForm";
import * as types from "../constants/ActionTypes";
import * as validMessage from "../../../../common/common-constant";
import {asyncValidateForSaveOrUpdate} from "../actions/asyncValidate";
export const fields = [{
    field: 'supplierNm',
    validate: {
        fieldNm: "供应商名称",
        required: true,
        maxlength: 32
    }
}, {
    field: 'unitNature',
    validate: {
        fieldNm: "供货单位性质",
    }
}, {
    field: 'businessResponseManTel',
    validate: {
        fieldNm: "业务负责人电话",
        required: true,
        maxlength: 32,
        regx:validMessage.REGEXP_PHONE_All,
        error:validMessage.REGEXP_PHONE_All_FORMAT
    }
}, {
    field: 'businessResponseManEmail',
    validate: {
        fieldNm: "邮箱",
        maxlength:128,
        regx:validMessage.REGEXP_EMAIL,
        error:validMessage.ERROR_EMAIL_FORMAT
    }
}, {
    field: 'regCapital',
    validate: {
        fieldNm: "注册资本",
        maxlength: 9,
        regx:validMessage.REGEXP_PRICE,
        error: validMessage.ERROR_REGEXP_PRICE_DOUBLE
    }
}, {
    field: 'regAddr',
    validate: {
        fieldNm: "注册地址",
        maxlength: 128,
    }
},{
    field: 'businessCategory',
    validate: {
        fieldNm: "经营品种",
        required: true,
    }
}, {
    field: 'businessRange',
    validate: {
        fieldNm: "经营范围",
        required: true,
    }
},{
    field: 'BUSINESS_LICENSE',
    validate: {
        fieldNm: "营业执照号",
        required: true,
        maxlength: 32
    }
}, {
    field: 'BUSINESS_LICENSETIME',
    validate: {
        fieldNm: "营业执照号有效期",
        required: true,
        maxlength: 32
    }
}];



//供货单位性质
export const unitNatureSelectField = ({input, label, type, id, required, items, optionName, meta: {touched, error}}) => (
    <div className="item">
        <p>{label}</p>
        <select id={id } name={input.name}   {...input}>
            <option value="">请选择</option>
            <option value="WHOLESALE_ENTERPRISE">批发企业</option>
            <option value="MANUFACTURER">生产厂商</option>
        </select>

    </div>
);


//经营品种 经营 范围
export const businessCategoryRadioField = ({label,required,input,allItems,changeEven=()=>{}}) => {
    return (<div className="item w860">
        <p>{required&&<i>*</i>}{label}</p>
        {
            allItems.map((allItem,index)=>{

                return (<label  key={index}>
                        <input  value={allItem.dictItemCode}  name={input.name} onChange={changeEven} type="checkbox"/>
                        {allItem.dictItemNm}
                    </label>
                );

            })
        }
    </div>);
}

export const hiddenField = ({type, input}) => (
    <input name={input.name} type={type} style={{display: "none"}} {...input} />
)


class SupplierDocAddForm extends React.Component {

    componentWillMount() {
        this.props.actions.findByAvailableAndDictType("BUSINESS_CATEGORY");
        this.props.actions.findByAvailableAndDictType("BUSINESS_RANGE");
    }

    componentDidMount() {
        const {change} = this.props;

        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("BUSINESS_LICENSETIME", $('input[name="BUSINESS_LICENSETIME"]').val());
                    change("applyDateString", $('input[name="applyDateString"]').val());
                }
            });
        });
    }

    businessCategoryChangeEven(e){
        const {form} = this.context.store.getState();
        var $target = $(e.currentTarget);
        var newCategoryItemSelect = $target.val();
        var values = form.supplierDocAddForm.values||"";
        var businessCategory = values.businessCategory||"";
        if(businessCategory==""){
            var  categoryItemSelects = [];
        }else{
        var categoryItemSelects = businessCategory.split(',');
        }
        var newCategoryItemSelects =[];
        if($target.is(":checked")) {
            newCategoryItemSelects = categoryItemSelects.concat(newCategoryItemSelect);
        } else {
            categoryItemSelects = categoryItemSelects
            newCategoryItemSelects = categoryItemSelects.filter(item => item != newCategoryItemSelect);
        }
        const {change} = this.props;
        change("businessCategory", newCategoryItemSelects.join(','));
        this.context.store.dispatch({
            type: types.SUPPLIER_DOC_SET_BUSINESS_SELECT_CATEGORY,
            businessCategorySelect:newCategoryItemSelects.join(',')

        });

    }
    businessRangeChangeEven(e){
        const {form} = this.context.store.getState();
        var $target = $(e.currentTarget);
        var newRangeItemSelect = $target.val();
        var businessRange = form.supplierDocAddForm.values.businessRange||"";
        if(businessRange==""){
            var  RangeItemSelects = [];
        }else{
            var RangeItemSelects = businessRange.split(',');
        }

        var newRangeItemSelects =[];
        if($target.is(":checked")) {
            newRangeItemSelects = RangeItemSelects.concat(newRangeItemSelect);
        } else {
            newRangeItemSelects = RangeItemSelects.filter(item => item!=newRangeItemSelect);
        }
        const {change} = this.props;
        change("businessRange", newRangeItemSelects.join(','));
        this.context.store.dispatch({
            type: types.SUPPLIER_DOC_SET_BUSINESS_SELECT_RANGE,
            businessRangeDataSelect:newRangeItemSelects.join(',')

        });
    }

    //收集资质数据
    getSupCerFileDate() {
        var list = [];

        //营业执照号
        const businessLicense = {
            certificatesType: "BUSINESS_LICENSE",
            certificatesNum: $('input[name="BUSINESS_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="BUSINESS_LICENSETIME"]').val().trim(),
        }
        list=list.concat(businessLicense);
        //组织机构代码证号
        const organizationCertificate = {
            certificatesType: "ORGANIZATION_CERTIFICATE",
            certificatesNum: $('input[name="ORGANIZATION_CERTIFICATE"]').val().trim(),
            certificatesValidityString: $('input[name="ORGANIZATION_CERTIFICATETIME"]').val().trim(),
        }
        list=list.concat(organizationCertificate);
        //GSP证书号
        const gspCertificate = {
            certificatesType: "GSP_CERTIFICATE",
            certificatesNum: $('input[name="GSP_CERTIFICATE"]').val().trim(),
            certificatesValidityString: $('input[name="GSP_CERTIFICATETIME"]').val().trim(),
        }
        list=list.concat(gspCertificate);
        //商品经营许可证号
        const commodityLicense = {
            certificatesType: "COMMODITY_LICENSE",
            certificatesNum: $('input[name="COMMODITY_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="COMMODITY_LICENSETIME"]').val().trim(),
        }
        list=list.concat(commodityLicense);
        //质量协议书号
        const qualityAgreement = {
            certificatesType: "QUALITY_AGREEMENT",
            certificatesNum: $('input[name="QUALITY_AGREEMENT"]').val().trim(),
            certificatesValidityString: $('input[name="QUALITY_AGREEMENTTIME"]').val().trim(),
        }
        list=list.concat(qualityAgreement);
        //食品流通许可证号
        const foodCirculationLicense = {
            certificatesType: "FOOD_CIRCULATION_LICENSE",
            certificatesNum: $('input[name="FOOD_CIRCULATION_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="FOOD_CIRCULATION_LICENSETIME"]').val().trim(),
        }
        list=list.concat(foodCirculationLicense);
        //食品卫生许可证号
        const foodHygieneLicense = {
            certificatesType: "FOOD_HYGIENE_LICENSE",
            certificatesNum: $('input[name="FOOD_HYGIENE_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="FOOD_HYGIENE_LICENSETIME"]').val().trim(),
        }
        list=list.concat(foodHygieneLicense);
        //保健品卫生许可证号
        const healthProductHygieneLicense = {
            certificatesType: "HEALTH_PRODUCT_HYGIENE_LICENSE",
            certificatesNum: $('input[name="HEALTH_PRODUCT_HYGIENE_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="HEALTH_PRODUCT_HYGIENE_LICENSETIME"]').val().trim(),
        }
        list=list.concat(healthProductHygieneLicense);
        //医疗器械许可证号
        const medicDeviceManufacturePermiss = {
            certificatesType: "MEDIC_DEVICE_MANUFACTURE_PERMISS",
            certificatesNum: $('input[name="MEDIC_DEVICE_MANUFACTURE_PERMISS"]').val().trim(),
            certificatesValidityString: $('input[name="MEDIC_DEVICE_MANUFACTURE_PERMISSTIME"]').val().trim(),
        }
        list=list.concat(medicDeviceManufacturePermiss);
        //化妆品经营许可证号
        const cosmeticsBusinessLicense = {
            certificatesType: "COSMETICS_BUSINESS_LICENSE",
            certificatesNum: $('input[name="COSMETICS_BUSINESS_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="COSMETICS_BUSINESS_LICENSETIME"]').val().trim(),
        }
        list=list.concat(cosmeticsBusinessLicense);
        //化妆品卫生许可证号
        const cosmeticsHygienicLicense = {
            certificatesType: "COSMETICS_HYGIENIC_LICENSE",
            certificatesNum: $('input[name="COSMETICS_HYGIENIC_LICENSE"]').val().trim(),
            certificatesValidityString: $('input[name="COSMETICS_HYGIENIC_LICENSETIME"]').val().trim(),
        }
        list=list.concat(cosmeticsHygienicLicense);

        this.props.actions.updateSUPCERFILE(list);

    }
    //收集资质文件同时检验
    getSupCerFileDateAndValid(){
        this.getSupCerFileDate();
        this.props.checkValidForm(true);

    }
    closeAddForm(){
        this.props.actions.showAddForm(false);
        this.props.reset();
        this.props.initValidForm();
    }
    render() {
        const props = this.props;
        const actions = this.props.actions;
        const {handleSubmit, submitting} = this.props;
        const {store} = this.context;
        const todos = store.getState().todos;
        const businessCategoryAllData = todos.businessCategoryAllData || [];
        const businessRangeAllData = todos.businessRangeAllData || [];

        const {errorValidMessage, validFormState,asyncValidMessage} = this.context.store.getState().validTodos;
        const validState = asyncValidMessage != "" || errorValidMessage != "";
        return (
            <form onSubmit={handleSubmit}>
                {validFormState && validState && <ValidForm />}
                <div className="layer" >
                    <div className="layer-box layer-info layer-order layer-stores layer-stores-modify">
                        <div className="layer-header">
                            <span>供应商信息</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => {actions.showAddForm(false)}}></a>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">基础信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="id" type="text" component={hiddenField}/>
                                    <Field name="supplierCode" type="text" component={hiddenField}/>
                                    <Field name="supplierNm" component={inputField} label="供应商名称" required="required"maxLength="32"  type="text" actions={actions}/>
                                    <Field name="unitNature" id="unitNature" label="供货单位性质" component={unitNatureSelectField}/>
                                    <Field name="qualityResponseManName" component={inputField} actions={actions}maxLength="32"  label="质量负责人" type="text"/>
                                    <Field name="legalRepresentative" component={inputField} actions={actions} maxLength="32" type="text" label="法定代表人"/>
                                    <Field name="businessResponseManName" component={inputField} actions={actions}maxLength="32"type="text"  label="业务负责人"/>
                                    <Field name="businessResponseManTel" component={inputField} actions={actions} maxLength="32" type="text" required="true" label="业务负责人电话"/>
                                    <Field name="businessResponseManEmail" component={inputField} actions={actions}  maxLength="128" type="text" label="业务负责人邮箱"/>
                                    <Field name="regCapital" component={inputField} actions={actions} type="text"  maxLength="22"label="注册资本(万)"/>
                                    <Field name="regAddr" component={inputField} actions={actions} type="text"  maxLength="128"  label="注册地址"/>
                                    <Field name="fax" component={inputField} actions={actions} type="text" label="传真" maxLength="32"/>
                                    <Field name="returnedPurchaseAddr" component={inputField} className="item-1-2"  actions={actions} type="text" label="退货地址" style={{width: "395px"}}/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">
                                    经营范围
                                </div>
                                <div className="box-mc clearfix">
                                    <Field label="经营品种" name="businessCategory" required="required" component={businessCategoryRadioField} changeEven={(e)=>{this.businessCategoryChangeEven(e)}} allItems={businessCategoryAllData}/>
                                    <Field label="经营范围" name="businessRange" required="required" component={businessCategoryRadioField} changeEven={(e)=>{this.businessRangeChangeEven(e)}}  allItems={businessRangeAllData}/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">资质文件</div>
                                <field name=""/>
                                <div className="box-mc clearfix">

                                    <Field name="BUSINESS_LICENSE" component={inputField} type="text" label="营业执照号" required="required" />
                                    <Field name="BUSINESS_LICENSETIME" component={inputField} type="text" label="有效期至" inputClassName="form-control datepicker" readOnly="readOnly" required="required" />
                                    <div className="item">
                                        <p>组织机构代码证号</p>
                                        <input name="ORGANIZATION_CERTIFICATE" type="text" className="certificateInput " maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input type="text" name="ORGANIZATION_CERTIFICATETIME" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>GSP证书号</p>
                                        <input name="GSP_CERTIFICATE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input type="text" name="GSP_CERTIFICATETIME" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>商品经营许可证号</p>
                                        <input name="COMMODITY_LICENSE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="COMMODITY_LICENSETIME" type="text" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>质量协议书号</p>
                                        <input name="QUALITY_AGREEMENT" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="QUALITY_AGREEMENTTIME" type="text"  className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>食品流通许可证号</p>
                                        <input name="FOOD_CIRCULATION_LICENSE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="FOOD_CIRCULATION_LICENSETIME" type="text" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>食品卫生许可证号</p>
                                        <input name="FOOD_HYGIENE_LICENSE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="FOOD_HYGIENE_LICENSETIME" type="text"  className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>保健品卫生许可证号</p>
                                        <input name="HEALTH_PRODUCT_HYGIENE_LICENSE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="HEALTH_PRODUCT_HYGIENE_LICENSETIME" type="text" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>医疗器械许可证号</p>
                                        <input name="MEDIC_DEVICE_MANUFACTURE_PERMISS" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="MEDIC_DEVICE_MANUFACTURE_PERMISSTIME" type="text" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>化妆品经营许可证号</p>
                                        <input name="COSMETICS_BUSINESS_LICENSE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="COSMETICS_BUSINESS_LICENSETIME" type="text" className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>化妆品卫生许可证号</p>
                                        <input name="COSMETICS_HYGIENIC_LICENSE" type="text" className="certificateInput" maxLength="32"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="COSMETICS_HYGIENIC_LICENSETIME" type="text"  className="form-control datepicker certificateInput" readOnly id="" maxLength="32"/>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div className="layer-footer">
                            <button type="submit" style={{border: "none"}} className="confirm" onClick={() => {this.getSupCerFileDateAndValid()}} disabled={submitting}>{submitting ? <i/> : <i/>} 保存
                            </button>
                            <a  href="javascript:void(0);"  className="cancel" onClick={() => {this.closeAddForm()}}>取消</a>
                        </div>
                    </div>
                </div>

            </form>
        );

    }
}

SupplierDocAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
SupplierDocAddForm.contextTypes =  {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({checkValidForm, asyncErrorValidMessageFunction,initValidForm,errorValidMessageFunction}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        state
    };
}
SupplierDocAddForm = reduxForm({
    form: "supplierDocAddForm",
    enableReinitialize: true,
    asyncBlurFields: ['supplierNm'],
    asyncValidate: asyncValidateForSaveOrUpdate
})(SupplierDocAddForm);

SupplierDocAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SupplierDocAddForm);

export default SupplierDocAddForm