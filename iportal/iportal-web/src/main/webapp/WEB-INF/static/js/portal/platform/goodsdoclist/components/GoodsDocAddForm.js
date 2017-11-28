import React, {Component, PropTypes} from "react";
import {Field, reduxForm} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import ValidForm from "../../../../common/validForm/components/ValidForm";
import {showFileMgrModalHasCallbackFunc} from "../../../../common/filemgr/actions";
import GoodsCategoryComponent from "../../../../common/goodsdoccategorycomponent/components/GoodsCategoryComponent";
import TimeSelect from "../../../../common/component/TimeSelect";
import {showArticleCategoryModal} from "../../../../common/goodsdoccategorycomponent/actions";
import {checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from "../../../../common/validForm/actions";
import * as types from "../constants/ActionTypes";

import {asyncValidateForSave} from "../actions/asyncValidate";
export const validate = (values, props) => {
    const errors = {};
    const fields = props.fields;
    for (const i in fields) {
        const fieldObj = fields[i];
        const field = fieldObj.field;
        const validate = fieldObj.validate;
        const required = validate.required;
        const fieldNm = validate.fieldNm;
        let isOk = true;

        if(field == "medicalInsuranceNum" && values["isMedicalInsuranceGoods"] =="N"){
            isOk = false;
        }

        if (required != undefined && required) {
            if ((values[field] == undefined || !values[field].toString().trim())&& isOk) {
                errors[field] = "请输入" + fieldNm;
                props.errorValidMessageFunction(errors[field]);
                return errors;
            }
        }
        const minlength = validate.minlength;
        const maxlength = validate.maxlength;
        if (values[field] != undefined && minlength != undefined && values[field].length < minlength) {
            errors[field] = fieldNm + "不能少于" + minlength + "个字符";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if (values[field] != undefined && maxlength != undefined && values[field].length > maxlength) {
            errors[field] = fieldNm + "不能大于" + maxlength + "个字符";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        if (values[field] && validate.regx && !validate.regx.test(values[field])) {
            const er = validate.error;
            errors[field] = er ? er : fieldNm + "输入错误";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};


export const simpleInput = ({ input, type,className,maxLength, id,required, meta: { touched, error } }) => (
        <input type={type} id={id} className={className} maxLength={maxLength} name={input.name} {...input}/>
);

export const dateField = ({ input, change,type,className,placeholder, id,required, meta: { touched, error } }) => (
    <TimeSelect readOnly="readOnly" type={type} id={id} change={change} className={className} placeholder={placeholder} name={input.name} required={required}/>
);
export const textAreaRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item w450">
        <p>{label}</p>
        <textarea id={id} name={input.name}{...input}/>
    </div>
);

export const toxicologyCodeRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item">
        <p>{label}</p>
        <label><input type="radio" {...input} value="DELETERIOUS" checked={input.value === 'DELETERIOUS'}/>有毒</label>
        <label><input type="radio" {...input} value="NON_TOXIC" checked={input.value === 'NON_TOXIC'}/>无毒</label>
    </div>
);

export const storageConditionRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item">
        <p>{label}</p>
        <label><input type="radio" {...input} value="ORDINARY_TEMPERATURE" checked={input.value === 'ORDINARY_TEMPERATURE'}/>常温</label>
        <label><input type="radio" {...input} value="SHADE" checked={input.value === 'SHADE'}/>阴凉</label>
        <label><input type="radio" {...input} value="REFRIGERATE" checked={input.value === 'REFRIGERATE'}/>冷藏</label>
    </div>
);

export const prescriptionDrugsTypeCodeRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item" >
        <p>{label}</p>
        <label style={{lineHeight:"20px"}}><input type="radio" {...input} value="RX" checked={input.value === 'RX'}/>处方药</label>
        <label style={{lineHeight:"20px"}}><input type="radio" {...input} value="OTC" checked={input.value === 'OTC'}/>非处方药</label>
        <label style={{lineHeight:"20px"}}><input type="radio" {...input} value="SG" checked={input.value === 'SG'}/>双轨制</label>
    </div>
);

export const simpleRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item">
        <p>{label}</p>
        <label><input type="radio" {...input} value="Y" checked={input.value === 'Y'}/>是</label>
        <label><input type="radio" {...input} value="N" checked={input.value === 'N'}/>否</label>
    </div>
);

export const isMedicalInsuranceGoods = ({ input, label,hideEvent,showEvent, type, id, required, meta: { touched, error } }) => (
    <div className="item">
        <p>{label}</p>
        <label><input type="radio" {...input} value="Y" checked={input.value === 'Y'} onClick={showEvent}/>是</label>
        <label><input type="radio" {...input} value="N" checked={input.value === 'N'} onClick={hideEvent}/>否</label>
    </div>
);

export const categoryField = ({ input, label, type,className, id,event, maxLength,required, meta: { touched, error } }) => (
    <div className="item">
        <p><i>*</i>{label}</p>
        <input type={type} id="goodsCategoryId" className={className} maxLength={maxLength} name="goodsCategoryId" onClick={event} readOnly="readOnly"   {...input}/>
    </div>
);

export const dosageFormSelectField = ({ input, label, type, id, required, items, event,meta: { touched, error } }) => (
    <select id={id} name={input.name} className="select" {...input}>
        <option value="">请选择</option>
        {
            items.map((item,index)=>{
                return (
                    <option key={index} value={item.dictItemCode}>{item.dictItemNm}</option>
                )
            })
        }
    </select>
);

export const goodsTypeSelectField = ({ input, label, type,clickEvent, id, required, event,meta: { touched, error } }) => (
    <select  id={id} name={input.name} className="select"  {...input} onChange={e => {input.onChange(e);clickEvent(e.target.value)}} >
        <option value="">请选择</option>
        <option value="DRUG" >药品</option>
        <option value="OTHER" >其他</option>
        <option value="CHINESE_MEDICINE_PIECES" >中药饮片</option>
        <option value="FOOD_HEALTH">食品保健品</option>
        <option value="DAILY_NECESSITIES">日用品</option>
        <option value="MEDICAL_INSTRUMENTS" >医疗器械</option>
        <option value="COSMETIC" >化妆品</option>
    </select>
);


const articleCategoryOptions = {
    ids: [],
    multiSelectedEnable: false, //是否可以多选
};

class GoodsDocAddForm extends Component {

    componentWillMount(){
        this.props.actions.getDosageFormList();
    }

    componentDidMount() {

    }

    componentDidUpdate() {

    }

    changeGoodsType(){
        const {store} = this.context;
        const {change} = this.props;
        const goodsTypeCode = $("#goodsTypeCode").val();
        store.dispatch({
            type:types.GOODS_DOC_ADD_FORM_CHANGE_GOODS_TYPE,
            data:goodsTypeCode
        });
        if(goodsTypeCode!="DRUG"||goodsTypeCode!="CHINESE_MEDICINE_PIECES"){
            change("approvalNumber","");
            change("approvalNumberTermString","");
            $("#approvalNumberTermString").val("");
        }

        if(goodsTypeCode!="FOOD_HEALTH"){
            change("productionDateString","");
            change("expirationDateString","");
            $("#productionDateString").val("");
            $("#expirationDateString").val("");
        }
    }


    submit(data){
        const {store} = this.context;
        const {params} = store.getState().todos;
        let pictFileList = store.getState().todos.pictFileList||[];
        let otherFileList = store.getState().todos.otherFileList||[];
        return this.props.actions.saveOrUpdateGoods(Object.assign({},data,{
            goodsCategoryId: $('#goodsCategoryId').attr('data-id'),
            pictFileList:pictFileList,
            otherFileList:otherFileList
        }),params,null);
    }

    hideMedicalInsuranceGoods(){
        $(".medicalInsuranceGoodsState").fadeOut();
    }

    showMedicalInsuranceGoods(){
        $(".medicalInsuranceGoodsState").fadeIn();
    }

    selectManagementCategory() {
        const {store} = this.context;
        let $target = $('#goodsCategoryId');
        let id = $target.attr('data-id');
        let ids = id == undefined || id.length == 0 ? new Array() : new Array(id);

        this.props.showArticleCategoryModal(true, (ids, objs) => {
            if (objs.length == 0) {
                $target.attr('data-id', '');
                $target.val('全部');
            } else {
                $target.attr('data-id', ids[0]);
                let goodsCategoryId = ids[0];
                let pathName = objs[0].pathName;
                let formData = store.getState().form.goodsDocAddForm.values;
                let articleData = Object.assign({}, formData, {
                    goodsCategoryId: goodsCategoryId,
                    pathName: pathName
                });
                store.dispatch({
                    type: types.GOODS_DOC_ADD_SET_GOODS_CATEGORY,
                    data: articleData
                });
            }
        }, Object.assign({}, articleCategoryOptions, {ids: ids}));
        $target.blur();
    }

    showFileView(type){
        const {store} = this.context;
        let pictFileList = store.getState().todos.pictFileList||[];
        let otherFileList = store.getState().todos.otherFileList||[];
        if(type=="PICT"){
            this.props.showFileMgrModalHasCallbackFunc((files)=> {
                let fileLibIdArr = [];
                pictFileList.map(pict=>{
                    fileLibIdArr.push(pict.sysFileLibId);
                });
                let newPictFileList = files.filter(file=>fileLibIdArr.indexOf(file.sysFileLibId)==-1);
                store.dispatch({
                    type: types.GOODS_DOC_ADD_FORM_ADD_PICTURES,
                    data: pictFileList.concat(newPictFileList)
                });
            });
        }
        if(type=="OTHER"){
            this.props.showFileMgrModalHasCallbackFunc((files)=> {
                let fileLibIdArr = [];
                otherFileList.map(pict=>{
                    fileLibIdArr.push(pict.sysFileLibId);
                });
                let newPictFileList = files.filter(file=>fileLibIdArr.indexOf(file.sysFileLibId)==-1);
                store.dispatch({
                    type: types.GOODS_DOC_ADD_FORM_ADD_OTHER_FILES,
                    data: otherFileList.concat(newPictFileList)
                });
            });
        }
    }


    deleteImg(sysFileLibId,type) {
        const {store} = this.context;
        if(type == "PICT"){
            let pictFileList = store.getState().todos.pictFileList||[];
            let newPicts = pictFileList.filter(pict=>pict.sysFileLibId!=sysFileLibId);
            store.dispatch({
                type:types.GOODS_DOC_ADD_FORM_ADD_PICTURES,
                data:newPicts
            });
        }
        if(type=="OTHER"){
            let otherFileList = store.getState().todos.otherFileList||[];
            let newPicts = otherFileList.filter(pict=>pict.sysFileLibId!=sysFileLibId);
            store.dispatch({
                type:types.GOODS_DOC_ADD_FORM_ADD_OTHER_FILES,
                data:newPicts
            });
        }

    }

    render() {
        const {actions} = this.props;
        const { handleSubmit, submitting } = this.props;
        const {store} = this.context;

        const dosageFormList = store.getState().todos.dosageFormList||[];
        const otherFileList = store.getState().todos.otherFileList||[];
        const pictFileList = store.getState().todos.pictFileList||[];
        const {validFormState, errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
        const {IS_DRUG,IS_OTHER,IS_CHINESE_MEDICINE_PIECES,IS_FOOD_HEALTH,IS_DAILY_NECESSITIES,IS_MEDICAL_INSTRUMENTS,IS_COSMETIC} = store.getState().todos;
        const {checkValidForm} = this.props;
        const {change} = this.props;
       const validState = asyncValidMessage != "" || errorValidMessage != "";
        return (
            <form onSubmit={handleSubmit(this.submit.bind(this))}>
            <div className="layer">
                {validFormState && validState && <ValidForm  checkValidForm={checkValidForm}/>}
                {store.getState().goodsCategoryTodos.articleCategoryModal && <GoodsCategoryComponent store={store} actions={this.props.actions}/>}
                <div className="layer-box layer-info w960">
                    <div className="layer-header">
                        <span>添加商品档案</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>actions.changeGoodsDocAddFormState(false)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mt">基础信息</div>
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <p><i>*</i>商品编码</p>
                                    <Field id="goodsCode" name="goodsCode" type="text" className=""  component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p><i>*</i>商品名称</p>
                                    <Field id="goodsNm" name="goodsNm" type="text" className="" component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p><i>*</i>商品类型</p>
                                    <Field id="goodsTypeCode" name="goodsTypeCode" type="text" clickEvent={this.changeGoodsType.bind(this)} component={goodsTypeSelectField}  required="required"/>
                                </div>

                                <Field id="pathName" name="pathName" label="商品分类" type="text"   event={this.selectManagementCategory.bind(this)} component={categoryField} required="required"/>

                                <div className="item">
                                    <p><i>*</i>生产厂商</p>
                                    <Field id="produceManufacturer" name="produceManufacturer" type="text" className=""  component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p><i>*</i>通用名称</p>
                                    <Field id="commonNm" name="commonNm" type="text" className="" component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p><i>*</i>规格</p>
                                    <Field id="spec" name="spec" type="text" className="" component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p><i>*</i>单位</p>
                                    <Field id="unit" name="unit" type="text" className="" component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p>品牌</p>
                                    <Field id="brandNm" name="brandNm" type="text" className="" component={simpleInput} required="required"/>
                                </div>
                                <div className="item">
                                    <p>条形码</p>
                                    <Field id="barCode" name="barCode" type="text" className=""  maxLength="32" component={simpleInput} />
                                </div>

                                <Field id="toxicologyCode" name="toxicologyCode" type="text" component={toxicologyCodeRadioField} label="毒理代码" required="required"/>

                                <Field id="storageCondition" name="storageCondition" type="text" component={storageConditionRadioField} label="储存条件" required="required"/>

                                {(IS_OTHER||IS_DRUG ||IS_COSMETIC||IS_CHINESE_MEDICINE_PIECES||IS_DAILY_NECESSITIES) &&
                                <div className="item">
                                    <p>{(IS_OTHER||IS_DRUG ||IS_COSMETIC||IS_DAILY_NECESSITIES) &&<i>*</i>}批准文号</p>
                                    <Field id="approvalNumber" name="approvalNumber" type="text" component={simpleInput} required="required"/>
                                </div>}

                                {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&
                                <div className="item">
                                    <p>{IS_DRUG&&<i>*</i>}批准文号期限</p>
                                    <Field id="approvalNumberTermString" name="approvalNumberTermString" type="text"   className="form-control datepicker"  component={dateField} change={change} />
                                </div>}

                                {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&<Field id="isImportGoods" name="isImportGoods" type="text" component={simpleRadioField} label="是否进口商品" required="required"/>}

                                {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&<Field id="isChineseMedicineProtect" name="isChineseMedicineProtect" type="text" component={simpleRadioField} label="是否中药保护" required="required"/>}

                                {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES)&&
                                <div className="item">
                                    <p>批准日期</p>
                                    <Field id="approveDateString" name="approveDateString" type="text"   className="form-control datepicker"  component={dateField} change={change}/>
                                </div>}

                                {(IS_DRUG ||IS_CHINESE_MEDICINE_PIECES)&&
                                <div className="item">
                                    <p><i>*</i>剂型</p>
                                    <Field id="dosageForm" name="dosageForm" label="剂型" type="text" items={dosageFormList} className="" component={dosageFormSelectField} required="required"/>
                                </div>}

                                {IS_CHINESE_MEDICINE_PIECES &&
                                <div className="item">
                                    <p><i>*</i>产地</p>
                                    <Field id="productionPlace" name="productionPlace"  type="text" className=""  component={simpleInput} required="required"/>
                                </div>}

                                {IS_CHINESE_MEDICINE_PIECES &&
                                <div className="item">
                                    <p>功效</p>
                                    <Field id="effects" name="effect"  type="text" className="" component={simpleInput}/>
                                </div>}

                                {IS_MEDICAL_INSTRUMENTS &&
                                <div className="item">
                                    <p><i>*</i>注册号</p>
                                    <Field id="regNum" name="regNum"  type="text" className="" component={simpleInput} required="required"/>
                                </div>}

                                {IS_MEDICAL_INSTRUMENTS &&
                                <div className="item">
                                    <p><i>*</i>注册登记表号</p>
                                    <Field id="regRegistrationFormNum" name="regRegistrationFormNum"  type="text"  className="" component={simpleInput} required="required"/>
                                </div>}

                                {(IS_OTHER||IS_DAILY_NECESSITIES||IS_COSMETIC||IS_MEDICAL_INSTRUMENTS) &&
                                <div className="item">
                                    <p>厂家地址</p>
                                    <Field id="manufacturerAddr" name="manufacturerAddr"  type="text" className=""  component={simpleInput}/>
                                </div>}

                                {IS_MEDICAL_INSTRUMENTS &&
                                <div className="item">
                                    <p>适用范围</p>
                                    <Field id="applyRange" name="applyRange"  type="text"  className="" component={simpleInput}/>
                                </div>}

                                {IS_MEDICAL_INSTRUMENTS &&
                                <div className="item">
                                    <p>产品标准号码</p>
                                    <Field id="productStandardNum" name="productStandardNum"  type="text"  className="" component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p><i>*</i>食品卫生许可证号</p>
                                    <Field id="foodHygieneLicenceNum" name="foodHygieneLicenceNum"  type="text" required="required" className=""  component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p><i>*</i>生产日期</p>
                                    <Field id="productionDateString" name="productionDateString" type="text"  change={change} component={dateField} className="form-control datepicker" required="require" />
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p><i>*</i>保质期</p>
                                    <Field id="expirationDateString" name="expirationDateString" type="text"  change={change} component={dateField} className="form-control datepicker" required="require" />
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p><i>*</i>保健功能</p>
                                    <Field id="healthCareFunc" name="healthCareFunc"  type="text" required="required" className="" component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>适宜人群</p>
                                    <Field id="appropriateCrowd" name="appropriateCrowd"  type="text"  className="" component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>不适宜人群</p>
                                    <Field id="notAppropriateCrowd" name="notAppropriateCrowd"  type="text" className=""  component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>食用方法及用量</p>
                                    <Field id="edibleMethodAndDosage" name="edibleMethodAndDosage"  type="text" className=""  component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>贮藏方法</p>
                                    <Field id="storageMethod" name="storageMethod"  type="text" className=""  component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>执行标准</p>
                                    <Field id="execStandard" name="execStandard"  type="text" className=""  component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>功效成分</p>
                                    <Field id="effectComposition" name="effectComposition"  type="text"  className="" component={simpleInput}/>
                                </div>}

                                {IS_FOOD_HEALTH &&
                                <div className="item">
                                    <p>注意事项</p>
                                    <Field id="notice" name="notice"  type="text" className=""  component={simpleInput}/>
                                </div>}


                            </div>

                        </div>

                        {(IS_DRUG || IS_CHINESE_MEDICINE_PIECES) &&
                        <div className="md-box">
                            <div className="box-mt">GSP设置</div>
                            <div className="box-mc clearfix">

                                <Field id="prescriptionDrugsTypeCode" name="prescriptionDrugsTypeCode" type="text" component={prescriptionDrugsTypeCodeRadioField} label="处方药" required="required"/>

                                <Field id="isEphedrine" name="isEphedrine" type="text" component={simpleRadioField} label="麻黄碱" required="required"/>

                                <Field id="isKeyCuring" name="isKeyCuring" type="text" component={simpleRadioField} label="重点养护" required="required"/>

                                <Field id="isMedicalInsuranceGoods" name="isMedicalInsuranceGoods" type="text" hideEvent={this.hideMedicalInsuranceGoods.bind(this)} showEvent={this.showMedicalInsuranceGoods.bind(this)} component={isMedicalInsuranceGoods} label="医保商品" required="required"/>

                                <div className="item medicalInsuranceGoodsState" style={{display:"none"}}>
                                    <p><i>*</i>医保号</p>
                                    <Field id="medicalInsuranceNum" name="medicalInsuranceNum" type="text" className=""  component={simpleInput} required="required"/>
                                </div>
                            </div>
                        </div>}

                        <div className="md-box">
                            <div className="box-mt">其他</div>
                            <div className="box-mc clearfix">
                                <Field id="instructions" name="instructions" type="text" label="说明书" component={textAreaRadioField} required="required"/>
                                <Field id="medicationGuide" name="medicationGuide" type="text" label="用药指导" component={textAreaRadioField} required="required"/>
                                <div className="item w450">
                                    <p>图片</p>
                                    <a href="javascript:void(0);" className="gray-btn uploading"  onClick={()=>this.showFileView("PICT")}>添加</a>
                                </div>
                                <div className="item-upload">
                                    {pictFileList.map((pict,index)=>{
                                        if(pict.fileTypeCode == "IMAGE"){
                                            return (
                                                <div key={index} className="upload-operation">
                                                    <img src={pict.smallFileUrl}  alt=""/>
                                                    <em onClick={()=>{this.deleteImg(pict.sysFileLibId,"PICT")}}/>
                                                </div>
                                            )
                                        }
                                        return (
                                            <div key={index} className="upload-operation">
                                                <a target="_blank"  href={pict.fileUrl}>{pict.fileNm}</a>
                                                <em onClick={()=>{this.deleteImg(pict.sysFileLibId,"PICT")}}/>
                                            </div>
                                        )
                                    })}
                                </div>
                                <div className="item w450">
                                    <p>附件</p>
                                    <a href="javascript:void(0);" className="gray-btn uploading" onClick={()=>this.showFileView("OTHER")}>添加</a>
                                </div>
                                <div className="item-upload">
                                    {otherFileList.map((pict,index)=>{
                                        if(pict.fileTypeCode == "IMAGE"){
                                            return (
                                                <div key={index} className="upload-operation">
                                                    <img src={pict.smallFileUrl}  alt=""/>
                                                    <em onClick={()=>{this.deleteImg(pict.sysFileLibId,"OTHER")}}/>
                                                </div>
                                            )
                                        }
                                        return (
                                            <div key={index} className="upload-operation">
                                                <a target="_blank"  href={pict.fileUrl}>{pict.fileNm}</a>
                                                <em onClick={()=>{this.deleteImg(pict.sysFileLibId,"OTHER")}}/>
                                            </div>
                                        )
                                    })}
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} type="submit" onClick={() => {checkValidForm(true)}}>保存</button>
                        <a href="javascript:void(0);" className="cancel" onClick={()=>actions.changeGoodsDocAddFormState(false)}>取消</a>
                    </div>

                    </div>

                </div>
            </form>
        )
    }
}


GoodsDocAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
GoodsDocAddForm.contextTypes = {
    store: React.PropTypes.object
};
function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        showArticleCategoryModal,
        showFileMgrModalHasCallbackFunc,
        asyncErrorValidMessageFunction
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.initSaveData,
        fields: state.todos.fields,
        state
    }
}

GoodsDocAddForm = reduxForm({
    form: 'goodsDocAddForm',
    enableReinitialize: true,
    validate,
    asyncBlurFields: ['goodsCode'],
    asyncValidate: asyncValidateForSave
})(GoodsDocAddForm);

GoodsDocAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsDocAddForm);

export default GoodsDocAddForm