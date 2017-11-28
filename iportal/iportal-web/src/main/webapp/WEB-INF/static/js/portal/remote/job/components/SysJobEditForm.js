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
    field: 'jobName',
    validate: {
        fieldNm: "岗位名称",
        required: true,
        maxLength: 32
    }
},
    {
    field: 'jobCode',
    validate: {
        fieldNm: "岗位代码",
        required: true,
        maxLength: 32
    }
},
    {
    field: 'description',
    validate: {
        fieldNm: "描述",
        maxLength: 256,
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




class SysJobEditForm extends React.Component {

    componentWillMount() {

    }

    componentDidMount() {
    }

    closeForm(){
        this.props.actions.showUpdate(false);
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
                            <span>岗位修改</span>
                            <a href="javascript:void(0);" className="close" onClick={() => {this.closeForm()}}></a>
                        </div>
                        <div className="layer-body">
                            <Field name="jobName" component={simpleInputField} label="岗位名称" required="required" maxLength="32" type="text"/>
                            <Field name="jobCode" component={simpleInputField} label="岗位代码" required="required" maxLength="32" type="text"/>
                            <Field name="description" component={simpleInputField} label="描述"   maxLength="256" type="text"/>
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

SysJobEditForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
SysJobEditForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({initValidForm,checkValidForm,errorValidMessageFunction,asyncErrorValidMessageFunction}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields: fields,
        initialValues:state.todos.detailObject,
        enableReinitialize:true,
        validate: validate,
        state
    };
}
SysJobEditForm = reduxForm({
    form: "sysJobEditForm",
    enableReinitialize: true,
    asyncBlurFields: ['jobCode'],
    asyncValidate: asyncValidateForSaveOrUpdate
})(SysJobEditForm);

SysJobEditForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(SysJobEditForm);

export default SysJobEditForm