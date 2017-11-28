/**
 * Created by ou on 2017/7/11.
 */

import React, {Component, PropTypes} from "react";
import {reduxForm, Field} from 'redux-form';
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux';
import {validate} from '../../../../common/redux-form-ext'


export const fields = [{
    field:'state',
    validate:{
        required:true
    }
},{
    field:'id',
    validate:{
        required:true
    }
}];



export const stateRadioField  = ({ input, required, meta: { touched, error } }) => (
    <div className="item">
        <div className="mlt"><span>*</span>账户状态</div>
        <div className="mrt "   >
            <label  >
                <input checked={input.value === 'Y'}  type="radio" {...input} value="Y" />启用
            </label>
            <label  >
                <input checked={input.value === 'N'} type="radio"  {...input} value="N"  />禁用

            </label>
        </div>
    </div>
);
export const hiddenField = ({type,input}) => (
    <input name={input.name} type={type} style={{display:"none"}} {...input} />
)

class ShopEnableUpdateForm extends React.Component {


    closeForm(){

        fields.splice(0, fields.length);
        this.props.actions.showIsEnableState();
        this.props.actions.initValue();
    }
    render() {
        const {handleSubmit, submitting} = this.props;
        const isEnableObject = this.props.isEnableObject;


        return (
            <form onSubmit={handleSubmit} style={{display:"block"}}>

                <div className="layer" >
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>启用/禁用账号</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => { this.closeForm() }} ></a>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt"><span>*</span>企业名称</div>
                                <div className="mrt"><span>{isEnableObject.shopName}</span></div>
                            </div>
                            <Field  name="state"       component={stateRadioField}  />
                            <Field  name="id" type="text" component={hiddenField}  />
                        </div>
                        <div className="layer-footer">
                            <button  type="submit" style={{border: "none"}} className="confirm" disabled={submitting}>{submitting ? <i/> :<i/>}保存</button>
                            <a  href="javascript:void(0);"  className="cancel" onClick={() => { this.closeForm() }}>取消</a>
                        </div>
                    </div>
                </div>
            </form>)


    }
}

ShopEnableUpdateForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
ShopEnableUpdateForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        initialValues: state.todos.isEnableObject,
        state
    };
}
ShopEnableUpdateForm = reduxForm({
    form: 'ShopEnableUpdateForm',
})(ShopEnableUpdateForm);

ShopEnableUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ShopEnableUpdateForm);

export default ShopEnableUpdateForm
