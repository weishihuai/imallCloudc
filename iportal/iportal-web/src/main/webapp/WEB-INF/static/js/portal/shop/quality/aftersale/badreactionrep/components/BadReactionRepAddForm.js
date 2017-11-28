import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm, change} from "redux-form";
import {REGEXP_INT,REGEXP_DOUBLE_2,REGEXP_EMAIL,REGEXP_PHONE_All} from "../../../../../../common/common-constant";
import {errorValidMessageFunction,checkValidForm} from '../../../../../../common/validForm/actions';
import ValidForm from '../../../../../../common/validForm/components/ValidForm';

export const validate = (values, props) => {
    const errors = {};
    const suspectDrugList = values.suspectDrugList;
    const blendDrugList = values.blendDrugList;

    /*是否首次报告*/
    if (!values.isFirstRep) {
        errors.isFirstRep = "请选择是否首次报告";
        props.errorValidMessageFunction(errors.isFirstRep);
        return errors;
    }

    /*报告类型*/
    if (!values.repType) {
        errors.repType = "请选择报告类型";
        props.errorValidMessageFunction(errors.repType);
        return errors;
    }

    /*报告单位类别*/
    if (!values.repDepartment) {
        errors.repDepartment = "请选择报告单位类别";
        props.errorValidMessageFunction(errors.repDepartment);
        return errors;
    }

    /*编码*/
    if (values.code && values.code !== undefined) {
        if (values.code.length > 32) {
            errors.code = "编码长度最大长度为32位";
            props.errorValidMessageFunction(errors.code);
            return errors;
        }
    }

    /*患者姓名*/
    if (values.patientName && values.patientName !== undefined) {
        if (values.patientName.length > 32) {
            errors.patientName = "患者姓名最大长度为32位";
            props.errorValidMessageFunction(errors.patientName);
            return errors;
        }
    }

    /*性别*/
    if (!values.sexCode) {
        errors.sexCode = "请选择性别类型";
        props.errorValidMessageFunction(errors.sexCode);
        return errors;
    }

    /*年龄*/
    if (values.age && values.age !== undefined) {
        if (!REGEXP_INT.test(values.age)) {
            errors.age = "年龄格式不正确";
            props.errorValidMessageFunction(errors.age);
            return errors;
        }else if(parseFloat(values.age) < 1 || parseFloat(values.age) > 120) {
            errors.age = "年龄范围为1~120之间的整数";
            props.errorValidMessageFunction(errors.age);
            return errors;
        }
    }

    /*民族*/
    if (values.nation && values.nation !== undefined) {
        if (values.nation.length > 32) {
            errors.nation = "民族最大长度为32位";
            props.errorValidMessageFunction(errors.nation);
            return errors;
        }
    }

    /*体重*/
    if (values.weight && values.weight !== undefined) {
        if (!REGEXP_DOUBLE_2.test(values.weight)) {
            errors.weight = "体重格式不正确";
            props.errorValidMessageFunction(errors.weight);
            return errors;
        }else if(parseFloat(values.weight) < 1 || parseFloat(values.weight) > 250) {
            errors.weight = "体重范围为1~250之间的整数";
            props.errorValidMessageFunction(errors.weight);
            return errors;
        }
    }

    /*联系方式*/
    if (values.contactWay && values.contactWay !== undefined) {
        if (values.contactWay.length > 32) {
            errors.contactWay = "联系方式最大长度为32位";
            props.errorValidMessageFunction(errors.contactWay);
            return errors;
        }
    }

    /*原患疾病*/
    if (values.originalDisease && values.originalDisease !== undefined) {
        if (values.originalDisease.length > 32) {
            errors.originalDisease = "原患疾病最大长度为32位";
            props.errorValidMessageFunction(errors.originalDisease);
            return errors;
        }
    }

    /*医院名称*/
    if (values.hospitalNm && values.hospitalNm !== undefined) {
        if (values.hospitalNm.length > 32) {
            errors.hospitalNm = "医院名称最大长度为32位";
            props.errorValidMessageFunction(errors.hospitalNm);
            return errors;
        }
    }

    /*病历号/门诊号*/
    if (values.medicalRecordNo && values.medicalRecordNo !== undefined) {
        if (values.medicalRecordNo.length > 32) {
            errors.medicalRecordNo = "病历号/门诊号最大长度为32位";
            props.errorValidMessageFunction(errors.medicalRecordNo);
            return errors;
        }
    }

    /*不良反应/事件名称*/
    if (values.badReaction && values.badReaction !== undefined) {
        if (values.badReaction.length > 32) {
            errors.badReaction = "不良反应/事件名称最大长度为32位";
            props.errorValidMessageFunction(errors.badReaction);
            return errors;
        }
    }

    /*后遗症表现*/
    if (values.sequelaPerform && values.sequelaPerform !== undefined) {
        if (values.sequelaPerform.length > 32) {
            errors.sequelaPerform = "后遗症表现最大长度为32位";
            props.errorValidMessageFunction(errors.sequelaPerform);
            return errors;
        }
    }

    /*直接死因*/
    if (values.causeOfDeath && values.causeOfDeath !== undefined) {
        if (values.causeOfDeath.length > 32) {
            errors.causeOfDeath = "直接死因最大长度为32位";
            props.errorValidMessageFunction(errors.causeOfDeath);
            return errors;
        }
    }

    /*怀疑药品*/
    errors.suspectDrugList = [];
    for (const i in suspectDrugList){
        const item = suspectDrugList[i];
        const str = "【怀疑药品第"+ (parseInt(i) + 1) +"行】 ";
        let errorStr = "";

        if (item.suspectApprovalNumber && item.suspectApprovalNumber !== undefined) {
            if (item.suspectApprovalNumber.length > 64){
                errorStr = str + "批准文号最大长度为64位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectApprovalNumber: errorStr};
                return errors;
            }
        }

        if (item.suspectGoodsNm && item.suspectGoodsNm !== undefined) {
            if (item.suspectGoodsNm.length > 128){
                errorStr = str + "商品名称最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectGoodsNm: errorStr};
                return errors;
            }
        }

        if (item.suspectCommonNm && item.suspectCommonNm !== undefined) {
            if (item.suspectCommonNm.length > 64){
                errorStr = str + "通用名称最大长度为64位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectCommonNm: errorStr};
                return errors;
            }
        }

        if (item.suspectProduceManufacturer && item.suspectProduceManufacturer !== undefined) {
            if (item.suspectProduceManufacturer.length > 64){
                errorStr = str + "生产厂家最大长度为64位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectProduceManufacturer: errorStr};
                return errors;
            }
        }

        if (item.suspectBatch && item.suspectBatch !== undefined) {
            if (item.suspectBatch.length > 32){
                errorStr = str + "生产批号最大长度为32位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectBatch: errorStr};
                return errors;
            }
        }

        if (item.suspectUsageAndDosage && item.suspectUsageAndDosage !== undefined) {
            if (item.suspectUsageAndDosage.length > 128){
                errorStr = str + "用法用量最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectUsageAndDosage: errorStr};
                return errors;
            }
        }

        if (item.suspectDrugUseTime && item.suspectDrugUseTime !== undefined) {
            if (item.suspectDrugUseTime.length > 32){
                errorStr = str + "用药起始时间最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectDrugUseTime: errorStr};
                return errors;
                }
        }

        if (item.suspectDrugUseReason && item.suspectDrugUseReason !== undefined) {
            if (item.suspectDrugUseReason.length > 32){
                errorStr = str + "用药原因最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.suspectDrugList[i] = {suspectDrugUseReason: errorStr};
                return errors;
            }
        }
    }

    /*并用药品*/
    errors.blendDrugList = [];
    for (const i in blendDrugList){
        const item = blendDrugList[i];
        const str = "【并用药品第"+ (parseInt(i) + 1) +"行】 ";
        let errorStr = "";

        if (item.blendApprovalNumber && item.blendApprovalNumber !== undefined) {
            if (item.blendApprovalNumber.length > 64){
                errorStr = str + "批准文号最大长度为64位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendApprovalNumber: errorStr};
                return errors;
            }
        }

        if (item.blendGoodsNm && item.blendGoodsNm !== undefined) {
            if (item.blendGoodsNm.length > 128){
                errorStr = str + "商品名称最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendGoodsNm: errorStr};
                return errors;
            }
        }

        if (item.blendCommonNm && item.blendCommonNm !== undefined) {
            if (item.blendCommonNm.length > 64){
                errorStr = str + "通用名称最大长度为64位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendCommonNm: errorStr};
                return errors;
            }
        }

        if (item.blendProduceManufacturer && item.blendProduceManufacturer !== undefined) {
            if (item.blendProduceManufacturer.length > 64){
                errorStr = str + "生产厂家最大长度为64位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendProduceManufacturer: errorStr};
                return errors;
            }
        }

        if (item.blendBatch && item.blendBatch !== undefined) {
            if (item.blendBatch.length > 32){
                errorStr = str + "生产批号最大长度为32位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendBatch: errorStr};
                return errors;
            }
        }

        if (item.blendUsageAndDosage && item.blendUsageAndDosage !== undefined) {
            if (item.blendUsageAndDosage.length > 128){
                errorStr = str + "用法用量最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendUsageAndDosage: errorStr};
                return errors;
            }
        }

        if (item.blendDrugUseTime && item.blendDrugUseTime !== undefined) {
            if (item.blendDrugUseTime.length > 32){
                errorStr = str + "用药起始时间最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendDrugUseTime: errorStr};
                return errors;
            }
        }

        if (item.blendDrugUseReason && item.blendDrugUseReason !== undefined) {
            if (item.blendDrugUseReason.length > 32){
                errorStr = str + "用药原因最大长度为128位";
                props.errorValidMessageFunction(errorStr);
                errors.blendDrugList[i] = {blendDrugUseReason: errorStr};
                return errors;
            }
        }
    }

    /*报告人电话*/
    if (values.repManTel && values.repManTel !== undefined) {
        if (!REGEXP_PHONE_All.test(values.repManTel)) {
            errors.repManTel = "报告人电话格式不正确";
            props.errorValidMessageFunction(errors.repManTel);
            return errors;
        }else if (values.repManTel.length > 32) {
            errors.repManTel = "报告人电话最大长度为32位";
            props.errorValidMessageFunction(errors.repManTel);
            return errors;
        }
    }

    /*报告人邮箱*/
    if (values.repManEmail && values.repManEmail !== undefined) {
        if (!REGEXP_EMAIL.test(values.repManEmail)) {
            errors.repManEmail = "报告人邮箱格式不正确";
            props.errorValidMessageFunction(errors.repManEmail);
            return errors;
        }else if(values.repManEmail.length > 32) {
            errors.repManEmail = "报告人邮箱最大长度为32位";
            props.errorValidMessageFunction(errors.repManEmail);
            return errors;
        }
    }

    /*报告人姓名*/
    if (values.repManName && values.repManName !== undefined) {
        if (values.repManName.length > 32) {
            errors.repManName = "报告人姓名最大长度为32位";
            props.errorValidMessageFunction(errors.repManName);
            return errors;
        }
    }

    /*报告单位名称*/
    if (values.repDepartmentNm && values.repDepartmentNm !== undefined) {
        if (values.repDepartmentNm.length > 32) {
            errors.repDepartmentNm = "报告单位名称最大长度为32位";
            props.errorValidMessageFunction(errors.repDepartmentNm);
            return errors;
        }
    }

    /*联系人*/
    if (values.repDepartmentContactMan && values.repDepartmentContactMan !== undefined) {
        if (values.repDepartmentContactMan.length > 32) {
            errors.repDepartmentContactMan = "联系人最大长度为32位";
            props.errorValidMessageFunction(errors.repDepartmentContactMan);
            return errors;
        }
    }

    /*报告单位电话*/
    if (values.repDepartmentTel && values.repDepartmentTel !== undefined) {
        if (!REGEXP_PHONE_All.test(values.repDepartmentTel)) {
            errors.repDepartmentTel = "报告单位电话格式不正确";
            props.errorValidMessageFunction(errors.repDepartmentTel);
            return errors;
        }else if (values.repDepartmentTel.length > 32) {
            errors.repDepartmentTel = "报告单位电话最大长度为32位";
            props.errorValidMessageFunction(errors.repDepartmentTel);
            return errors;
        }
    }

    /*备注*/
    if (values.remark && values.remark !== undefined) {
        if (values.remark.length > 256) {
            errors.remark = "备注最大长度为256位";
            props.errorValidMessageFunction(errors.remark);
            return errors;
        }
    }

    /*不良反应过程描述*/
    if (values.badReactionProcessDescr && values.badReactionProcessDescr !== undefined) {
        if (values.badReactionProcessDescr.length > 32) {
            errors.badReactionProcessDescr = "不良反应过程描述最大长度为32位";
            props.errorValidMessageFunction(errors.badReactionProcessDescr);
            return errors;
        }
    }

    props.errorValidMessageFunction("");
    return errors
};

/*下拉框*/
export const selectField = ({ input, className, label, id, required, items, optionValue, optionName, event,disabled, meta: { touched, error } }) => (
    <div>
        <select id={id ? id : ""} style={{height:"26px",padding:"0px"}} name={input.name} className="select-w" {...input} disabled ={disabled  ? disabled :""}>
            <option value="">请选择</option>
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

/*文本域*/
export const textareaField = ({ input, label, type, className, inputClassName, id, required, maxLength,disabled, meta: { touched, error } }) => (
    <div>
        <textarea type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} disabled ={disabled  ? disabled :""} maxLength={maxLength ? maxLength : ""} {...input}/>
    </div>
);

/*文本输入框*/
export const inputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div>
        <input style={{width:"93%",height:"26px",lineHeight:"26px",paddingLeft:"4%",border:"1px solid #c3c3c3",color: "#666",paddingBottom: "0px",marginBottom:"0px",marginLeft:"0px"}}  placeholder={placeholder || ''} type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} disabled ={disabled  ? disabled :""} readOnly={readOnly ? readOnly : ""} {...input}/>
    </div>
);

/*时间输入框*/
export const timeInputField = ({ input, label, type, id, timeInput, required, meta: { touched, error } }) => (
    <div>
        <input id = {input.id} name = {input.name} className = {timeInput + " form-control " + required} type={type} {...input} readOnly/>
    </div>
);

class BadReactionRepAddForm extends React.Component {

    componentWillMount() {
        this.addSuspectDrugArray(3); //初始化怀疑药品Field
        this.addBlendDrugArray(3);   //初始化并用药品Field
    }

    componentDidMount() {
        const {change} = this.props;
        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    change("badReactionOccurTimeString", $('input[name="badReactionOccurTimeString"]').val());
                    change("birthDateString", $('input[name="birthDateString"]').val());
                    change("deadTimeString", $('input[name="deadTimeString"]').val());
                    change("repDateString", $('input[name="repDateString"]').val());
                }
            });
        });
    }

    componentDidUpdate(){

    }

    /*增加怀疑药品Field*/
    addSuspectDrugArray(count){
        const newSuspectDrugArray = {
            suspectApprovalNumber:"",
            suspectGoodsNm:"",
            suspectCommonNm:"",
            suspectProduceManufacturer:"",
            suspectBatch:"",
            suspectUsageAndDosage:"",
            suspectDrugUseTime:"",
            suspectDrugUseReason:"",
        };
        for (let i = 0; i < count; i++) {
            this.props.array.push("suspectDrugList", newSuspectDrugArray);
        }
    }

    /*增加并用药品Field*/
    addBlendDrugArray(count){
        const newBlendDrugArray = {
            blendApprovalNumber:"",
            blendGoodsNm:"",
            blendCommonNm:"",
            blendProduceManufacturer:"",
            blendBatch:"",
            blendUsageAndDosage:"",
            blendDrugUseTime:"",
            blendDrugUseReason:"",
        };
        for (let i = 0; i < count; i++) {
            this.props.array.push("blendDrugList", newBlendDrugArray);
        }
    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const actions = this.props.actions;
        const {store} = this.context;
        const {suspectDrugList} = store.getState().form.badReactionRepAddForm.values;
        const {blendDrugList} = store.getState().form.badReactionRepAddForm.values;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

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

        const isFirstRepFieldValue = [
            {
                name:"首次报告",
                code:"FIRST_REG"
            },
            {
                name:"跟踪报告",
                code:"FOLLOW_REG"
            }
        ];

        const repTypeFieldValue = [
            {
                name:"新的",
                code:"NOVEL"
            },
            {
                name:"严重",
                code:"SERIOUS"
            },
            {
                name:"一般",
                code:"COMMON"
            }
        ];

        const repDepartmentFieldValue = [
            {
                name:"医疗机构",
                code:"MEDICAL_INSTITUTION"
            },
            {
                name:"经营企业",
                code:"OPERATE_ENTERPRISE"
            },
            {
                name:"生产企业",
                code:"MANUFACTURING_ENTERPRISE"
            },
            {
                name:"个人",
                code:"PERSONAL"
            },
            {
                name:"其他",
                code:"OTHER"
            }
        ];

        const drugBadReactionFieldValue = [
            {
                name:"有",
                code:"HAS"
            },
            {
                name:"无",
                code:"NO"
            },
            {
                name:"不详",
                code:"UNSPECIFIED"
            }
        ];

        const badReactionReferImportantInfFieldValue = [
            {
                name:"吸烟史",
                code:"SMOKE_HISTORY"
            },
            {
                name:"饮酒史",
                code:"DRINK_HISTORY"
            },
            {
                name:"妊娠期",
                code:"GESTATION_PERIOD"
            },
            {
                name:"肝病史",
                code:"HEPATOPATHY_HISTORY"
            },
            {
                name:"肾病史",
                code:"NEPHROPATHY_HISTORY"
            },
            {
                name:"过敏史",
                code:"ALLERGIC_HISTORY"
            },
            {
                name:"其他",
                code:"OTHER"
            }
        ];

        const badReactionResultFieldValue = [
            {
                name:"痊愈",
                code:"RECOVERY"
            },
            {
                name:"好转",
                code:"GETTING_BETTER"
            },
            {
                name:"未好转",
                code:"NOT_GETTING_BETTER"
            },
            {
                name:"不详",
                code:"UNKNOWN"
            },
            {
                name:"有后遗症",
                code:"REMNANT_SYMPTOM"
            },
            {
                name:"死亡",
                code:"DEATH"
            }
        ];

        const responseIsEaseFieldValue = [
            {
                name:"是",
                code:"Y"
            },
            {
                name:"否",
                code:"N"
            },
            {
                name:"不明",
                code:"UNKNOWN"
            },
            {
                name:"未停药或未减量",
                code:"WITHOUT_OR_WITHOUT_REDUCTION"
            }
        ];

        const isAppearAgainFieldValue = [
            {
                name:"是",
                code:"Y"
            },
            {
                name:"否",
                code:"N"
            },
            {
                name:"不明",
                code:"UNKNOWN"
            },
            {
                name:"未再使用",
                code:"NO_LONGER_USED"
            }
        ];

        const effectToOriginalDiseaseFieldValue = [
            {
                name:"不明显",
                code:"NON_CLEAR"
            },
            {
                name:"病程延长",
                code:"PROLONGE_COURSE"
            },
            {
                name:"病情加重",
                code:"ILLNESS_WORSE"
            },
            {
                name:"导致后遗症",
                code:"CAUSE_SEQUEL"
            },
            {
                name:"导致死亡",
                code:"CAUSE_DEATH"
            }
        ];

        const repEvaluateFieldValue = [
                {
                    name:"肯定",
                    code:"CERTAIN"
                },
                {
                    name:"很可能",
                    code:"IN_ALL_PROBABILITY"
                },
                {
                    name:"可能",
                    code:"PROBABLY"
                },
                {
                    name:"可能无关",
                    code:"PROBABLY_IRRELEVANT"
                },
                {
                    name:"待评价",
                    code:"WAIT_APPRAISE"
                },
                {
                    name:"无法评价",
                    code:"CANNOT_APPRAISE"
                }
            ];

        const repManProfessionFieldValue = [
                {
                    name:"医生",
                    code:"DOCTOR"
                },
                {
                    name:"药师",
                    code:"APOTHECARY"
                },
                {
                    name:"护士",
                    code:"NURSE"
                },
                {
                    name:"其他",
                    code:"OTHER"
                }
            ];

        const infSourceFieldValue = [
            {
                name:"医疗机构",
                code:"MEDICAL_INSTITUTION"
            },
            {
                name:"经营企业",
                code:"OPERATE_ENTERPRISE"
            },
            {
                name:"个人",
                code:"PERSONAL"
            },
            {
                name:"上市后研究",
                code:"POST_MARKETING_STUDY"
            },
            {
                name:"文献报道",
                code:"DOCUMENT_REPORT"
            },
            {
                name:"其他",
                code:"OTHER"
            }
        ];

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-order layer-adverse-reactions w1617">
                        <div className="layer-header">
                            <span>添加不良反应报告</span>
                            <a href="javascript:void(0);" className="close" onClick={() => actions.badReactionRepAddModel(false)}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <table>
                                        <thead>
                                        <tr><th colSpan="9">药品不良反应/事件报告</th></tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td><span>是否首次报告：</span></td>
                                            <td>
                                                <Field name="isFirstRep" id="isFirstRep" label="" component={selectField} items={isFirstRepFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>报告类型：</span></td>
                                            <td>
                                                <Field name="repType" id="repType" label="" component={selectField} items={repTypeFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>报告单位类别：</span></td>
                                            <td>
                                                <Field name="repDepartment" id="repDepartment" label="" component={selectField} items={repDepartmentFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>编码：</span></td>
                                            <td><Field id="code" name="code" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>患者姓名：</span></td>
                                            <td><Field id="patientName" name="patientName" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>性别：</span></td>
                                            <td>
                                                <Field name="sexCode" id="sexCode" label="" component={selectField} items={sexCodeSelectFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>出生日期：</span></td>
                                            <td><Field id="birthDateString" name="birthDateString" timeInput="datepicker" type="text" component={timeInputField} label="" readOnly/></td>
                                            <td><span>年龄：</span></td>
                                            <td><Field id="age" name="age" type="text" component={inputField} label="" maxLength="10"/></td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>民族：</span></td>
                                            <td><Field id="nation" name="nation" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>体重(kg)：</span></td>
                                            <td><Field id="weight" name="weight" type="text" component={inputField} label="" maxLength="22"/></td>
                                            <td><span>联系方式：</span></td>
                                            <td><Field id="contactWay" name="contactWay" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>原患疾病：</span></td>
                                            <td><Field id="originalDisease" name="originalDisease" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>医院名称：</span></td>
                                            <td><Field id="hospitalNm" name="hospitalNm" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>病例号/门诊号：</span></td>
                                            <td><Field id="medicalRecordNo" name="medicalRecordNo" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>既往药品不良反应/事件：</span></td>
                                            <td>
                                                <Field name="pastDrugBadReaction" id="pastDrugBadReaction" label="" component={selectField} items={drugBadReactionFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>家族药品不良反应/事件：</span></td>
                                            <td>
                                                <Field name="familyDrugBadReaction" id="familyDrugBadReaction" label="" component={selectField} items={drugBadReactionFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>相关重要信息：</span></td>
                                            <td>
                                                <Field name="referImportantInf" id="referImportantInf" label="" component={selectField} items={badReactionReferImportantInfFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>不良反应/事件名称：</span></td>
                                            <td><Field id="badReaction" name="badReaction" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>不良反应/事件发生时间：</span></td>
                                            <td><Field id="badReactionOccurTimeString" name="badReactionOccurTimeString" timeInput="datepicker" type="text" component={timeInputField} label="" readOnly/></td>
                                            <td><span>不良反应/事件结果：</span></td>
                                            <td>
                                                <Field name="badReactionResult" id="badReactionResult" label="" component={selectField} items={badReactionResultFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>报告日期：</span></td>
                                            <td><Field id="repDateString" name="repDateString" timeInput="datepicker" type="text" component={timeInputField} label="" readOnly/></td>
                                            <td><span>后遗症表现：</span></td>
                                            <td><Field id="sequelaPerform" name="sequelaPerform" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>直接死因：</span></td>
                                            <td><Field id="causeOfDeath" name="causeOfDeath" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>死亡时间：</span></td>
                                            <td><Field id="deadTimeString" name="deadTimeString" timeInput="datepicker" type="text" component={timeInputField} label="" readOnly/></td>
                                            <td/>
                                        </tr>
                                        <tr className="tr-title">
                                            <td>药品</td>
                                            <td>批准文号</td>
                                            <td>商品名称</td>
                                            <td>通用名称<br/>（含剂型）</td>
                                            <td>生产厂家</td>
                                            <td>生产批号</td>
                                            <td>用法用量<br/>（次剂量、途径、日次数）</td>
                                            <td>用药起止时间</td>
                                            <td>用药原因</td>
                                        </tr>
                                        {
                                            suspectDrugList.map((field, index) => {
                                                return (
                                                    <tr key={index}>
                                                        {index === 0 && <td rowSpan={suspectDrugList.length}><span style={{textAlign: "center"}}>怀疑药品</span></td>}
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectApprovalNumber'} component={inputField}  maxLength="64"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectGoodsNm'} component={inputField} maxLength="128"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectCommonNm'} component={inputField} maxLength="64"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectProduceManufacturer'} component={inputField} maxLength="64"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectBatch'} component={inputField} maxLength="32"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectUsageAndDosage'} component={inputField} maxLength="128"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectDrugUseTime'} component={inputField} maxLength="32"/></td>
                                                        <td><Field name={'suspectDrugList[' + index + '].suspectDrugUseReason'} component={inputField} maxLength="32"/></td>
                                                    </tr>
                                                )
                                            })
                                        }

                                        {
                                            blendDrugList.map((field, index) => {
                                                return (
                                                    <tr key={index}>
                                                        {index === 0 && <td rowSpan={blendDrugList.length}><span style={{textAlign: "center"}}>并用药品</span></td>}
                                                        <td><Field name={'blendDrugList[' + index + '].blendApprovalNumber'} component={inputField} maxLength="64"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendGoodsNm'} component={inputField} maxLength="128"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendCommonNm'} component={inputField} maxLength="64"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendProduceManufacturer'} component={inputField} maxLength="64"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendBatch'} component={inputField} maxLength="32"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendUsageAndDosage'} component={inputField} maxLength="128"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendDrugUseTime'} component={inputField} maxLength="32"/></td>
                                                        <td><Field name={'blendDrugList[' + index + '].blendDrugUseReason'} component={inputField} maxLength="32"/></td>
                                                    </tr>
                                                )
                                            })
                                        }
                                        <tr>
                                            <td colSpan="2"><span>停药或减量后，反应/事件是否消失或减轻：</span></td>
                                            <td>
                                                <Field name="responseIsEase" id="responseIsEase" label="" component={selectField} items={responseIsEaseFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td colSpan="2"><span>再次使用可疑药品后是否再次出现同样反应/事件：</span></td>
                                            <td>
                                                <Field name="isAppearAgain" id="isAppearAgain" label="" component={selectField} items={isAppearAgainFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>对原患疾病的影响：</span></td>
                                            <td>
                                                <Field name="effectToOriginalDisease" id="effectToOriginalDisease" label="" component={selectField} items={effectToOriginalDiseaseFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>报告评价：</span></td>
                                            <td colSpan="8">
                                                <Field name="repManEvaluate" id="repManEvaluate" label="" component={selectField} items={repEvaluateFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><span>报告人电话：</span></td>
                                            <td><Field id="repManTel" name="repManTel" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>报告人职业：</span></td>
                                            <td>
                                                <Field name="repManProfession" id="repManProfession" label="" component={selectField} items={repManProfessionFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>报告人邮箱：</span></td>
                                            <td><Field id="repManEmail" name="repManEmail" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>报告人姓名：</span></td>
                                            <td><Field id="repManName" name="repManName" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>报告单位评价：</span></td>
                                            <td colSpan="8">
                                                <Field name="repDepartmentEvaluate" id="repDepartmentEvaluate" label="" component={selectField} items={repEvaluateFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><span>报告单位名称：</span></td>
                                            <td><Field id="repDepartmentNm" name="repDepartmentNm" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>联系人：</span></td>
                                            <td><Field id="repDepartmentContactMan" name="repDepartmentContactMan" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td><span>报告单位电话：</span></td>
                                            <td><Field id="repDepartmentTel" name="repDepartmentTel" type="text" component={inputField} label="" maxLength="32"/></td>
                                            <td/>
                                            <td/>
                                            <td/>
                                        </tr>
                                        <tr>
                                            <td><span>生产企业请填写信息来源：</span></td>
                                            <td>
                                                <Field name="infSource" id="infSource" label="" component={selectField} items={infSourceFieldValue} optionName="name" optionValue="code"/>
                                            </td>
                                            <td><span>备注：</span></td>
                                            <td colSpan="5">
                                                <Field id="remark" name="remark" type="text" className="item-1-2" component={textareaField} label="" maxLength="256"/>
                                            </td>
                                        </tr>
                                        <tr><td colSpan="9">不良反应/时间过程描述（包括症状、体征、临床体验等）及处理情况(可附页)：</td></tr>
                                        <tr>
                                            <td colSpan="9">
                                                <Field id="badReactionProcessDescr" name="badReactionProcessDescr" type="text" className="item-1-2" component={textareaField} label="" maxLength="256"/>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div className="md-box add-blank-box">
                                <div className="box-mc clearfix">
                                    <div className="item">
                                        <a id="suspectDrugAdd" className="review" onClick={() => {this.addSuspectDrugArray(1)}}><i>+</i>为怀疑药品添加空白行</a>
                                    </div>
                                    <div className="item">
                                        <a id="blendDrugAdd" className="review" onClick={() => {this.addBlendDrugArray(1)}}><i>+</i>为并用药品添加空白行</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button  className="confirm" style={{border:"none"}} disabled={submitting} onClick={() => {checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={() => actions.badReactionRepAddModel(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

BadReactionRepAddForm.contextTypes = {
    store: React.PropTypes.object
};

BadReactionRepAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

BadReactionRepAddForm = reduxForm({
    form: 'badReactionRepAddForm',
    enableReinitialize: true,
})(BadReactionRepAddForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues:{
            suspectDrugList: [],
            blendDrugList:[],
            isFirstRep:"FIRST_REG",
            repType:"NOVEL",
            repDepartment:"MEDICAL_INSTITUTION",
            sexCode:"SECRET",
            pastDrugBadReaction:"HAS",
            familyDrugBadReaction:"HAS",
            referImportantInf:"SMOKE_HISTORY",
            badReactionResult:"RECOVERY"
        },
        state,
        validate
    };
}
export default connect(mapStateToProps, mapDispatchToProps)(BadReactionRepAddForm);