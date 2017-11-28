import React from "react";

export const inputField = ({ input, label, type, id, required, isShow, meta: { touched, error } }) => (
    <div className="item" style={{'display': isShow==='false' ? 'none' : 'block'}}>
        <div className="mlt"><span >{required ? '*' : ''}</span>{label}{label ? '：' : ''}</div>
        <div className="mrt">
            <input id={id} name={input.name} className={"form-control " + required} type={type}  {...input}/>
        </div>
    </div>
);

export const boolRadioField = ({ input, label, type, id, required, defaultValue, meta: { touched, error } }) => (
    <div className="item">
        <div className="mlt"><span>{required ? '*' : ''}</span>{label}：</div>
        <div className="mrt" id={id} name={input.name}>
            <label>
                <input type="radio" {...input} value="Y" checked={input.value === 'Y'}/> 是
            </label>
            <label>
                <input type="radio" {...input} value="N" checked={input.value === 'N'}/> 否
            </label>
        </div>
    </div>
);