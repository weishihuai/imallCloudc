import React from "react";

export const clickInputField = ({ input, label, type, className, inputClassName, id, required, maxLength, readOnly, onClick, meta: { touched, error } }) => (
    <div className={"item " + (className ? className : "")}>
        <p>{required && <i>*</i>}{label}</p>
        <input type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} maxLength={maxLength ? maxLength : ""} readOnly={readOnly ? readOnly : ""} onClick={onClick ? onClick : ()=>{}} {...input}/>
    </div>
);

export const selectField = ({ input, className, label, id, required, items, optionValue, optionName, onChange, event, meta: { touched, error } }) => (
    <div className="item">
        <p>{required && <i>*</i>}{label}</p>
        <select id={id ? id : ""} name={input.name} className={"select " + (className || '')} onChange={onChange ? onChange : ()=>{}} {...input}>
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