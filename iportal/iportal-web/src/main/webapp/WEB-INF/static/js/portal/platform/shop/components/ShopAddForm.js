/**
 * Created by ou on 2017/7/11.
 */
import React, {PropTypes} from "react";
import {Field, reduxForm, change, Form} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import * as validMessage from "../../../../common/common-constant";
import {validate, selectField, inputField} from "../../../../common/redux-form-ext";
import ValidForm from "../../../../common/validForm/components/ValidForm";
import {
    checkValidForm,
    initValidForm,
    errorValidMessageFunction,
    asyncErrorValidMessageFunction
} from "../../../../common/validForm/actions";
import * as types from "../constants/ActionTypes";
import {asyncValidateForSaveOrUpdate} from "../actions/asyncValidate";
export const fields = [{
    field: 'entNm',
    validate: {
        fieldNm: "企业名称",
        required: true,
        maxLength: 32
    }
}, {
    field: 'isMedicalInsuranceShop',
    validate: {
        fieldNm: "是否医保门店",

    }
}, {
    field: 'regCapital',
    validate: {
        fieldNm: "注册资金",
        maxlength: 19,
        regx:validMessage.REGEXP_PRICE,
    }
}, {
    field: 'companyAddr',
    validate: {
        fieldNm: "公司地址",
        required: true,
        maxlength: 128
    }
}, {
    field: 'companyTel',
    validate: {
        fieldNm: "公司电话",
        regx:validMessage.REGEXP_PHONE_All,
        error:validMessage.REGEXP_PHONE_All_FORMAT
    }
}, {
    field: 'annualInspectionDateString',
    validate: {
        fieldNm: "年检日期",
    }
}, {
    field: 'userName',
    validate: {
        fieldNm: "用户名",
        required: true,
        regx:validMessage.REGEXP_NOT_CHINESE,
        error:validMessage.ERROR_NAME_INT_AND_LETTER,
        maxlength: 32,
        minlength: 4,

    }
}, {
    field: 'password',
    validate: {
        fieldNm: "密码",
        required: true,
        maxlength:15,
        regx:validMessage.REGEXP_LOGIN_PASSWORD,
        error:validMessage.ERROR_PASSWORD_FORMAT

    }
}, {
    field: 'email',
    validate: {
        fieldNm: "邮箱",
        required: true,
        maxlength:128,
        regx:validMessage.REGEXP_EMAIL,
        error:validMessage.ERROR_EMAIL_FORMAT
    }
}, {
    field: 'mobile',
    validate: {
        fieldNm: "手机号",
        required: true,
        maxlength:11,
        regx:validMessage.REGEXP_PHONE,
        error:validMessage.ERROR_PHONE_FORMAT

    }
}, {
    field: 'isEnable',
    validate: {
        fieldNm: "门店状态",
        required: true,

    }
}];
//企业类型
const entTypeCodeItems=[
    {
        name:"自营店",
        code:"PROPRIETARY_SHOP"

    },
    {
        name:"加盟店",
        code:"FRANCHISE_STORE"

    },
    {
        name:"联盟店",
        code:"UNION_STORE"

    }
];
//经营方式
const businessWayItems=[
    {
        name:"生产",
        code:"PRODUCTION"

    },
    {
        name:"批发",
        code:"WHOLESALE"

    },
    {
        name:"零售",
        code:"retail"

    }
];
//是否医保店
const isMedicalInsuranceShopItems=[
    {
        name:"否",
        code:"N"

    }, {
        name:"是",
        code:"Y"

    }

];

//是否启用
const isEnableItems=[
    {
        name:"禁用",
        code:"N"

    },
    {
        name:"启用",
        code:"Y"

    }
];

//是否医保店
export const isMedicalInsuranceShopSelectField = ({ input, className, label, id, required, items,event=()=>{}, optionValue, optionName,disabled, meta: { touched, error } }) => (
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


//经营品种 经营 范围
export const businessRangeRadioField = ({label,required,input,allItems,changeEven=()=>{}}) => {
    return (<div className="item item-1-1">
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


class ShopAddForm extends React.Component {

    componentWillMount() {
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
                    change("annualInspectionDateString",$('input[name="annualInspectionDateString"]').val());
                }
            });
        });
    }



    businessRangeChangeEven(e){
        const {form} = this.context.store.getState();
        var $target = $(e.currentTarget);

        var businessRange = form.ShopAddForm.values.businessRange||"";
        if(businessRange==""){
            var rangeItemSelects = [];
        }else{
            var rangeItemSelects = businessRange.split(',');
        }

        var newRangeItemSelects =[];
        var newRangeItemSelect = $target.val();
        if($target.is(":checked")) {
            newRangeItemSelects = rangeItemSelects.concat(newRangeItemSelect);
        } else {
            newRangeItemSelects = rangeItemSelects.filter(item => item != newRangeItemSelect);
        }
        const {change} = this.props;
        change("businessRange", newRangeItemSelects.join());
        this.context.store.dispatch({
            type: types.SHOP_SET_BUSINESS_SELECT_RANGE,
            businessRangeDataSelect:newRangeItemSelects.join()

        });
    }

    //处理
    getSupCerFileDate() {
        var list = [];

        //营业执照号
        const businessLicense = {
            certificatesType: "BUSINESS_LICENSE",
            certificatesNum: $('input[name="BUSINESS_LICENSE"]').val(),
            certificatesValidityString: $('input[name="BUSINESS_LICENSETIME"]').val(),
        }
        list=list.concat(businessLicense);
        //组织机构代码证号
        const organizationCertificate = {
            certificatesType: "ORGANIZATION_CERTIFICATE",
            certificatesNum: $('input[name="ORGANIZATION_CERTIFICATE"]').val(),
            certificatesValidityString: $('input[name="ORGANIZATION_CERTIFICATETIME"]').val(),
        }
        list=list.concat(organizationCertificate);
        //GSP证书号
        const gspCertificate = {
            certificatesType: "GSP_CERTIFICATE",
            certificatesNum: $('input[name="GSP_CERTIFICATE"]').val(),
            certificatesValidityString: $('input[name="GSP_CERTIFICATETIME"]').val(),
        }
        list=list.concat(gspCertificate);
        //商品经营许可证号
        const commodityLicense = {
            certificatesType: "COMMODITY_LICENSE",
            certificatesNum: $('input[name="COMMODITY_LICENSE"]').val(),
            certificatesValidityString: $('input[name="COMMODITY_LICENSETIME"]').val(),
        }
        list=list.concat(commodityLicense);
        //质量协议书号
        const qualityAgreement = {
            certificatesType: "QUALITY_AGREEMENT",
            certificatesNum: $('input[name="QUALITY_AGREEMENT"]').val(),
            certificatesValidityString: $('input[name="QUALITY_AGREEMENTTIME"]').val(),
        }
        list=list.concat(qualityAgreement);
        //食品流通许可证号
        const foodCirculationLicense = {
            certificatesType: "FOOD_CIRCULATION_LICENSE",
            certificatesNum: $('input[name="FOOD_CIRCULATION_LICENSE"]').val(),
            certificatesValidityString: $('input[name="FOOD_CIRCULATION_LICENSETIME"]').val(),
        }
        list=list.concat(foodCirculationLicense);
        //食品卫生许可证号
        const foodHygieneLicense = {
            certificatesType: "FOOD_HYGIENE_LICENSE",
            certificatesNum: $('input[name="FOOD_HYGIENE_LICENSE"]').val(),
            certificatesValidityString: $('input[name="FOOD_HYGIENE_LICENSETIME"]').val(),
        }
        list=list.concat(foodHygieneLicense);
        //保健品卫生许可证号
        const healthProductHygieneLicense = {
            certificatesType: "HEALTH_PRODUCT_HYGIENE_LICENSE",
            certificatesNum: $('input[name="HEALTH_PRODUCT_HYGIENE_LICENSE"]').val(),
            certificatesValidityString: $('input[name="HEALTH_PRODUCT_HYGIENE_LICENSETIME"]').val(),
        }
        list=list.concat(healthProductHygieneLicense);
        //医疗器械许可证号
        const medicDeviceManufacturePermiss = {
            certificatesType: "MEDIC_DEVICE_MANUFACTURE_PERMISS",
            certificatesNum: $('input[name="MEDIC_DEVICE_MANUFACTURE_PERMISS"]').val(),
            certificatesValidityString: $('input[name="MEDIC_DEVICE_MANUFACTURE_PERMISSTIME"]').val(),
        }
        list=list.concat(medicDeviceManufacturePermiss);
        //化妆品经营许可证号
        const cosmeticsBusinessLicense = {
            certificatesType: "COSMETICS_BUSINESS_LICENSE",
            certificatesNum: $('input[name="COSMETICS_BUSINESS_LICENSE"]').val(),
            certificatesValidityString: $('input[name="COSMETICS_BUSINESS_LICENSEETIMETIME"]').val(),
        }
        list=list.concat(cosmeticsBusinessLicense);
        //化妆品卫生许可证号
        const cosmeticsHygienicLicense = {
            certificatesType: "COSMETICS_HYGIENIC_LICENSE",
            certificatesNum: $('input[name="COSMETICS_HYGIENIC_LICENSE"]').val(),
            certificatesValidityString: $('input[name="COSMETICS_HYGIENIC_LICENSETIME"]').val(),
        }
        list=list.concat(cosmeticsHygienicLicense);

        this.props.actions.updateSHOPCERFILE(list);

    }
    //收集资质文件同时检验
    getSupCerFileDateAndValid(){
        this.getSupCerFileDate();
        this.props.checkValidForm(true);
    }
    closeForm(){
        this.props.reset();
        const actions = this.props.actions;
        actions.showAddForm(false);
        this.props.initValidForm();

        this.props.actions.initValue();
    }


    render() {
        const {handleSubmit, submitting} = this.props;
        const todos = this.context.store.getState().todos;
        const businessRangeAllData = todos.businessRangeAllData || [];
        const {errorValidMessage, validFormState,asyncValidMessage} = this.context.store.getState().validTodos;
        const validState = asyncValidMessage != "" || errorValidMessage != "";
        return (
            <Form onSubmit={handleSubmit}>
                {validFormState && validState && <ValidForm />}
                <div className="layer" >
                    <div className="layer-box layer-info layer-order layer-stores layer-stores-modify">
                        <div className="layer-header">
                            <span>门店信息</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => {this.closeForm()}}></a>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mt">门店信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="entNm" component={inputField} label="企业名称" type="text" required="required" maxLength="32" />
                                    <Field name="legalRepresentativeMan"  component={inputField} label="法定代表人"  type="text"  maxLength="32" />
                                    <Field name="entResponseMan" component={inputField} label="企业负责人" type="text" maxLength="32"  />
                                    <Field name="taxRegisterCertNum"component={inputField} label="税务登记证号"  type="text" maxLength="32"  />
                                    <Field name="entTypeCode" label="企业类型" component={selectField} items={entTypeCodeItems} optionValue="code" optionName="name"/>
                                    <Field name="isMedicalInsuranceShop"  label="是否医保店" component={isMedicalInsuranceShopSelectField} items={isMedicalInsuranceShopItems} optionValue="code" optionName="name"  />
                                    <Field name="businessWay" label="经营方式" component={selectField} items={businessWayItems} optionValue="code" optionName="name"/>
                                    <Field name="regCapital" component={inputField}  label="注册资金(万)" type="text" />
                                    <Field name="regAddr" component={inputField}  label="注册地址" type="text" maxLength="32" />
                                    <Field name="companyAddr" component={inputField}  label="公司地址"   type="text" required="required" maxLength="32" />
                                    <Field name="storageAddr" component={inputField}   type="text" label="仓库地址" maxLength="32"  />
                                    <Field name="companyTel"  component={inputField} type="text"  label="公司电话" maxLength="128"  />
                                    <Field name="companyFax"  component={inputField}   type="text"label="公司传真"  maxLength="128"  />
                                    <Field name="annualInspectionDateString" component={inputField} type="text" label="年检日期"  inputClassName="form-control datepicker" readOnly="readOnly" />
                                    <Field name="companyBrief"  component={inputField} className="item-1-2"type="text" label="公司简介" maxLength="1024"  />
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt"> 经营范围</div>
                                <div className="box-mc clearfix">
                                   <Field  name="businessRange"  component={businessRangeRadioField} changeEven={(e)=>{this.businessRangeChangeEven(e)}}  allItems={businessRangeAllData}/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">资质文件</div>
                                <field name=""/>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <p>营业执照号</p>
                                        <input name="BUSINESS_LICENSE" maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input type="text" name="BUSINESS_LICENSETIME"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>组织机构代码证号</p>
                                        <input name="ORGANIZATION_CERTIFICATE" maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input type="text" name="ORGANIZATION_CERTIFICATETIME"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>GSP证书号</p>
                                        <input name="GSP_CERTIFICATE" maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input type="text" name="GSP_CERTIFICATETIME"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>商品经营许可证号</p>
                                        <input name="COMMODITY_LICENSE" maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="COMMODITY_LICENSETIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>质量协议书号</p>
                                        <input name="QUALITY_AGREEMENT"  maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="QUALITY_AGREEMENTTIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>食品流通许可证号</p>
                                        <input name="FOOD_CIRCULATION_LICENSE"  maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="FOOD_CIRCULATION_LICENSETIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>食品卫生许可证号</p>
                                        <input name="FOOD_HYGIENE_LICENSE"  maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="FOOD_HYGIENE_LICENSETIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>保健品卫生许可证号</p>
                                        <input name="HEALTH_PRODUCT_HYGIENE_LICENSE"  maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="HEALTH_PRODUCT_HYGIENE_LICENSETIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>医疗器械许可证号</p>
                                        <input name="MEDIC_DEVICE_MANUFACTURE_PERMISS"  maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="MEDIC_DEVICE_MANUFACTURE_PERMISSTIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>化妆品经营许可证号</p>
                                        <input name="COSMETICS_BUSINESS_LICENSE"  maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="COSMETICS_BUSINESS_LICENSETIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                    <div className="item">
                                        <p>化妆品卫生许可证号</p>
                                        <input name="COSMETICS_HYGIENIC_LICENSE" maxLength="32" type="text"/>
                                    </div>
                                    <div className="item">
                                        <p>有效期至</p>
                                        <input name="COSMETICS_HYGIENIC_LICENSETIME" type="text"
                                               className="form-control datepicker " readOnly />
                                    </div>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">账号信息</div>
                                <div className="box-mc clearfix">
                                    <Field name="userName"id="userName" component={inputField} label="用户名" required="required"  type="text" />
                                    <Field name="password" id="password"component={inputField} label="密码" required="required"  type="password" />
                                    <Field name="email" id="email"component={inputField} label="E-Mail" required="required"  type="text" />
                                    <Field name="mobile" id="mobile"component={inputField} label="手机号" required="required"  type="text" maxLength="32"/>
                                </div>
                            </div>
                            <div className="md-box">
                                <div className="box-mt">门店状态</div>
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <Field name="isEnable" id="isEnable" label="门店状态"component={selectField} items={isEnableItems} optionValue="code" optionName="name"  required="required"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                             <button  style={{border: "none"}} className="confirm" onClick={() => {this.getSupCerFileDateAndValid()}} disabled={submitting}>{submitting ? <i/> : <i/>} 保存 </button>
                             <a   href="javascript:void(0);"  className="cancel" onClick={() => {this.closeForm()}}>取消</a>
                        </div>
                    </div>
                </div>

            </Form>
        );

    }
}

ShopAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
ShopAddForm.contextTypes =  {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({initValidForm,checkValidForm,asyncErrorValidMessageFunction, errorValidMessageFunction}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        initialValues:{isEnable:"Y",isMedicalInsuranceShop:"N"},
        state
    };
}
ShopAddForm = reduxForm({
    form: "ShopAddForm",
    enableReinitialize: true,
    asyncBlurFields: ['userName'],
    asyncValidate: asyncValidateForSaveOrUpdate
})(ShopAddForm);

ShopAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ShopAddForm);

export default ShopAddForm