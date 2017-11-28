/**
 * Created by ygw on 2016/10/31.
 */
import React from "react";

export const validate = (values, props) => {
    const errors = {};
    const fields = props.fields;
    for (const i in fields) {
        const fieldObj = fields[i];
        const field = fieldObj.field;
        const validate = fieldObj.validate;
        const required = validate.required;
        const fieldNm = validate.fieldNm;
        const regx = validate.regx;
        const error = validate.error;

        if (required != undefined && required) {
            if (values[field] == undefined || !values[field].toString().trim()) {
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

        const minValue = validate.minValue;
        const maxValue = validate.maxValue;
        if (values[field] != undefined && minValue != undefined && !isNaN(values[field]) && values[field] < minValue) {
            errors[field] = fieldNm + "不能小于" + minValue;
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
        if (values[field] != undefined && maxValue != undefined && !isNaN(values[field]) && values[field] > maxValue) {
            errors[field] = fieldNm + "不能大于" + maxValue;
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }

        if (values[field] &&regx && !regx.test(values[field])) {
            const er = error;
            errors[field] = er ? er : fieldNm + "输入错误";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }


        const compareField = fieldObj.validate.compareField;
        if(values[field] && compareField && values[field] != values[compareField]){
            errors[field] = "两次输入不一致";
            props.errorValidMessageFunction(errors[field]);
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors
};


export const inputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div className={"item " + (className ? className : "")}>
        <p>{required && <i>*</i>}{label}</p>
        <input  placeholder={placeholder || ''} type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} disabled ={disabled  ? disabled :""} readOnly={readOnly ? readOnly : ""} {...input}/>
    </div>
);

export const boolRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <label><input name={input.name} type="radio" {...input} value="Y" checked={input.value === 'Y'}/>是</label>
        <label><input name={input.name} type="radio" {...input} value="N" checked={input.value === 'N'}/>否</label>
    </div>
);


export const selectField = ({ input, className, label, id, required, items,event=()=>{}, optionValue, optionName,disabled, meta: { touched, error } }) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <select id={id ? id : ""} name={input.name} className={"select " + (className || '')} {...input} disabled ={disabled  ? disabled :""} onChange={e => {input.onChange(e);event(e.target.value)}}>
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

export const textareaField = ({ input, label, type, className, inputClassName, id, required, maxLength,disabled, meta: { touched, error } }) => (
    <div className={"item " + (className ? className : "")}>
        <span>{required && <i>*</i>}{label}</span>
        <textarea type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} disabled ={disabled  ? disabled :""} maxLength={maxLength ? maxLength : ""} {...input}></textarea>
    </div>
);

/*格式化数据
 *import {formatData} from '../../../common/redux-form-ext'
 */
export const formatData = (data) => {
    for (var i in data) {
        if(typeof data[i] == "string") {
            data[i] = data[i].trim();
        }
    }
    return data;
}
