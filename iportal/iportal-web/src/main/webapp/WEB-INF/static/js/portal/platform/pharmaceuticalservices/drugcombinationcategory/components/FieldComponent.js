import React from "react";

export const inputField = ({ input, label, type, id, timeInput, required, isShow, meta: { touched, error } }) => (
    <div className="item" style={{'display': isShow==='false' ? 'none' : 'block'}}>
        <div className="mlt"><span >{required===undefined ? '' : '*'}</span>{label}：</div>
        <div className="mrt">
            <input id={id} name={input.name} className={timeInput + " form-control " + required} type={type} {...input} />
        </div>
    </div>
);