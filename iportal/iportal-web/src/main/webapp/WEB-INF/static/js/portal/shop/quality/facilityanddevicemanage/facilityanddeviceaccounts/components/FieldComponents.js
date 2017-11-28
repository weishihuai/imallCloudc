import React from 'react';

export const hiddenInputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, disabled , placeholder,  meta: { touched, error } }) => (
    <div className={"item " + (className ? className : "")} style={{display: 'none'}}>
        <p>{required && <i>*</i>}{label}</p>
        <input  placeholder={placeholder || ''} type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} disabled ={disabled  ? disabled :""} readOnly={readOnly ? readOnly : ""} {...input}/>
    </div>
);