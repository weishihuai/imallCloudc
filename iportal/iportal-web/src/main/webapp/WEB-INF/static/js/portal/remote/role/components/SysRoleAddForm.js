/**
 * Created by ou on 2017/7/11.
 */
import {checkValidForm,initValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from '../../../../common/validForm/actions';
import React, {PropTypes}from "react";
import {Field, reduxForm, change} from 'redux-form';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {validate} from '../../../../common/redux-form-ext'
import ValidForm from '../../../../common/validForm/components/ValidForm';
import * as validMessage from '../../../../common/common-constant'
import {asyncValidateForSaveOrUpdate} from "../actions/asyncValidate";
export const fields = [
    {
    field: 'roleName',
    validate: {
        fieldNm: "角色名称",
        required: true,
        maxLength: 99
    }
},
    {
    field: 'roleCode',
    validate: {
        fieldNm: "角色编码",
        required: true,
        maxLength: 32
    }
},
    {
    field: 'description',
    validate: {
        fieldNm: "角色描述",
        required: true,
        maxLength: 500,
    }
},
    {
    field: 'orderby',
    validate: {
        fieldNm: "顺序(数字)",
        regx:validMessage.REGEXP_SIGNLESS_INT,
        error:validMessage.ERROR_INT,
        required: true,
        maxLength: 9
    }
},
 ];


export const simpleInputField = ({ required,input,label, type, maxLength,  meta: { touched, error } }) => (

    <div className="item">
        <div className="mlt">{required && <i>*</i>}{label}：</div>
        <div className="mrt">
            <input  type={type}  name={input.name}  maxLength={maxLength ? maxLength : ""}  {...input}/>
        </div>
    </div>
);

export const simpleBoolRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="item">
        <div className="mlt">{required && <i>*</i>}{label}：</div>
        <div className="mrt">
            <label><input name={input.name} type="radio" {...input} value="Y" checked={input.value === 'Y'}/>可用</label>
            <label><input name={input.name} type="radio" {...input} value="N" checked={input.value === 'N'}/>不可用</label>
        </div>
    </div>
);




class SysRoleAddForm extends React.Component {

    componentWillMount() {

    }

    componentDidMount() {
    }

    closeForm(){
        this.props.actions.showAddForm(false);
        this.props.reset();
        this.props.initValidForm();
    }
    render() {

        const {handleSubmit, submitting} = this.props;
        const {store} = this.context;
        const {errorValidMessage, validFormState,asyncValidMessage} = this.context.store.getState().validTodos;
        const messageState = errorValidMessage  || asyncValidMessage;
        return (
            <form onSubmit={handleSubmit}>
                {validFormState && messageState && <ValidForm />}
                <div className="layer" >
                    <div className="layer-box layer-start layer-post-add w430">
                        <div className="layer-header">
                            <span>角色新增</span>
                            <a href="javascript:void(0);" className="close" onClick={() => {this.closeForm()}}></a>
                        </div>
                        <div className="layer-body">
                            <Field name="roleName" component={simpleInputField} label="角色名称" required="required" maxLength="99" type="text"/>
                            <Field name="roleCode" component={simpleInputField} label="角色编码" required="required" maxLength="31" type="text"/>
                            <Field name="description" component={simpleInputField} label="角色描述" required="required" maxLength="499" type="text"/>
                            <Field name="orderby" component={simpleInputField} label="顺序" required="required" maxLength="9" type="text"/>
                            <Field name="isAvailable" label="是否可用" required="required" component={simpleBoolRadioField}/>
                        </div>
                        <div className="layer-footer">
                            <button type="submit" style={{border: "none"}} className="confirm" onClick={() => {this.props.checkValidForm(true)}} disabled={submitting}>{submitting ? <i/> : <i/>} 保存
                            </button>
                            <a  href="javascript:void(0);"  className="cancel" onClick={() => {this.closeForm()}}>取消</a>
                        </div>
                    </div>
                </div>

            </form>
        );

    }
}

SysRoleAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
SysRoleAddForm.contextTypes =  {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({initValidForm,checkValidForm,errorValidMessageFunction,asyncErrorValidMessageFunction}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        initialValues:{isAvailable:"Y"},
        enableReinitialize:true,
        validate: validate,
        state
    };
}
SysRoleAddForm = reduxForm({
    form: "sysRoleAddForm",
    enableReinitialize: true,
    asyncBlurFields: ['roleCode'],
    asyncValidate: asyncValidateForSaveOrUpdate
})(SysRoleAddForm);

SysRoleAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SysRoleAddForm);

export default SysRoleAddForm