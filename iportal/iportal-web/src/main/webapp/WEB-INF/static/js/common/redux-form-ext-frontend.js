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
                return errors;
            }
        }
        const minlength = validate.minlength;
        const maxlength = validate.maxlength;
        if (values[field] != undefined && minlength != undefined && values[field].length < minlength) {
            errors[field] = fieldNm + "不能少于" + minlength + "个字符";
            return errors;
        }
        if (values[field] != undefined && maxlength != undefined && values[field].length > maxlength) {
            errors[field] = fieldNm + "不能大于" + maxlength + "个字符";
            return errors;
        }

        const minValue = validate.minValue;
        const maxValue = validate.maxValue;
        if (values[field] != undefined && minValue != undefined && !isNaN(values[field]) && values[field] < minValue) {
            errors[field] = fieldNm + "不能小于" + minValue;
            return errors;
        }
        if (values[field] != undefined && maxValue != undefined && !isNaN(values[field]) && values[field] > maxValue) {
            errors[field] = fieldNm + "不能大于" + maxValue;
            return errors;
        }

        if (values[field] &&regx && !regx.test(values[field])) {
            const er = error;
            errors[field] = er ? er : fieldNm + "输入错误";
            return errors;
        }

        const compareField = fieldObj.validate.compareField;
        if(values[field] && compareField && values[field] != values[compareField]){
            errors[field] = "两次输入不一致";
            return errors;
        }
    }
    return errors
};

export const inputField = ({ input, label, type, className, id, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div className={className || ''}>
        <label>
            <span>{label}</span>
            <input {...input} id={id} maxLength={maxLength || ''} type={type} placeholder={placeholder} disabled={disabled || ''} readOnly={readOnly || ''}/>
        </label>
    </div>
);

export const hiddenField = ({ input, id, meta: { touched, error } }) => (
    <input {...input} id={id} type="hidden"/>
);

export const textareaField = ({ input, className, rows, cols, id, placeholder, meta: { touched, error } }) => (
    <textarea className={className || ''} {...input} rows={rows} id={id} cols={cols} placeholder={placeholder || ''} ></textarea>
);
