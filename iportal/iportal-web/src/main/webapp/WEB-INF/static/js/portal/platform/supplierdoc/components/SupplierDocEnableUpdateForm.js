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

class SupplierDocEnableUpdateForm extends React.Component {


    changeDiscountWayCode(value) {

        const change = this.props.change;
        change("state", value);
    }

    render() {
        const {handleSubmit, submitting} = this.props;
        const isEnableObject = this.props.isEnableObject;
        const showIsEnableState = this.props.showIsEnableState;

        return (
            <form onSubmit={handleSubmit} style={{display:"block"}}>

                <div className="layer" >
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>启用/禁用供应商账号</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => { showIsEnableState() }} ></a>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt"><span>*</span>供应商名称</div>
                                <div className="mrt"><span>{isEnableObject.supplierNm}</span></div>
                            </div>
                            <Field  name="state"       component={stateRadioField}  />
                            <Field  name="id" type="text" component={hiddenField}  />
                        </div>
                        <div className="layer-footer">
                            <button onClick={() => { this.props.actions.checkValidForm(true) }} type="submit" style={{border: "none"}} className="confirm" disabled={submitting}>{submitting ? <i/> :<i/>}保存</button>
                            <a  href="javascript:void(0);"  className="cancel" onClick={() => { showIsEnableState() }}>取消</a>
                        </div>
                    </div>
                </div>
            </form>)


    }
}

SupplierDocEnableUpdateForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
SupplierDocEnableUpdateForm.contextTypes = {
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
SupplierDocEnableUpdateForm = reduxForm({
    form: 'supplierDocEnableUpdateForm',
})(SupplierDocEnableUpdateForm);

SupplierDocEnableUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SupplierDocEnableUpdateForm);

export default SupplierDocEnableUpdateForm
