import React from "react";

export const radioField = ({input, label, type, id, required, meta: { touched, error } }) => (
    <div className="layer-body">
        <label><input type="radio" name={input.name} checked={input.value === '用户拒收'} {...input}   value="用户拒收"  />用户拒收</label>
        <label><input type="radio" name={input.name}  checked={input.value === '支付超时'} {...input} value="支付超时" />支付超时</label>
        <label><input type="radio" name={input.name} checked={input.value === '用户取消，订单信息有误'} {...input}   value="用户取消，订单信息有误"  />用户取消，订单信息有误</label>
        <label><input type="radio" name={input.name}  checked={input.value === '管理员取消订单'} {...input} value="管理员取消订单"/>管理员取消订单</label>
    </div>
);

export const selectField = ({ input, className, id, required, items, limitQuantity, optionValue, optionName, onChange, event, meta: { touched, error } }) => (
    <td style={{paddingRight: '24px'}}>
        <select style={{width: '95px'}} id={id ? id : ""} name={input.name} className={"select " + (className || '')} onChange={onChange ? onChange : ()=>{}} {...input}>
            <option value="">请选择</option>
            {
                items.map((item,index)=>{
                    return (
                        <option key={index} value={item[optionValue]} >{item[optionName]}</option>
                    )
                })
            }
        </select>
        <span className="residue-number">{limitQuantity && '(剩余' + limitQuantity + '件)'}</span>
    </td>
);

export const inputField = ({ input, type, readOnly, onChange, meta: { touched, error } }) => (
    <input  type={type} name={input.name} readOnly={readOnly ? readOnly : ""} onChange={onChange ? onChange : ()=>{}} {...input}/>
);