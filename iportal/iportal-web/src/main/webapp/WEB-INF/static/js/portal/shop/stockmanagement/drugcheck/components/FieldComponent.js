import React from "react";

export const inputField = ({ input, type, className, id, maxLength, tdClassName, display, meta: { touched, error } }) => (
    <td style={{'display': display ? display : ''}} className={tdClassName}>
        <div className={className ? className : ""}>
            <input style={{height: '26px'}} type={type} name={input.name} maxLength={maxLength ? maxLength : ""} {...input}/>
        </div>
    </td>
);