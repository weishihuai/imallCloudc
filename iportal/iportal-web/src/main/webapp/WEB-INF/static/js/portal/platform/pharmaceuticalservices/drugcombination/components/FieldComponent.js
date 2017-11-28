import React from "react";

export const selectField = ({ input, label, id, required, items, optionValue, optionName, event, meta: { touched, error } }) => (
    <div className="item item-1-2" style={{'width': 410 + 'px'}}>
        <p><i>*</i>{label}</p>
        <select id={id} name={input.name} className="select" style={{'width': 100 + '%'}} {...input} >
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