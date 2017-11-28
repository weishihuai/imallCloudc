/**
 * Created by ou on 2017/7/11.
 */
import {checkValidForm, errorValidMessageFunction} from '../../../../common/validForm/actions';
import React, {Component, PropTypes} from "react";
import {reduxForm, Field} from 'redux-form';
import {bindActionCreators} from 'redux'
import {connect} from 'react-redux';
import {validate} from '../../../../common/redux-form-ext'
import * as validMessage from '../../../../common/common-constant'
import ValidForm from '../../../../common/validForm/components/ValidForm';
export const inputField = ({type, input}) => (
    <input name={input.name} type={type}   {...input} />
)
export const hiddenInputField = ({type, input}) => (
    <input name={input.name} type={type} style={{display: "none"}} {...input} />
)


export const fields = [{
    field: 'password',
    validate: {
        fieldNm: "新密码",
        required: true,
        maxlength:15,
        regx:validMessage.REGEXP_LOGIN_PASSWORD,
        error:validMessage.ERROR_PASSWORD_FORMAT

    }
},{
    field: 'newPassword',
    validate: {
        fieldNm: "新密码",
        required: true,
        maxlength:15,
        regx:validMessage.REGEXP_LOGIN_PASSWORD,
        error:validMessage.ERROR_PASSWORD_FORMAT,
        compareField: "password"
    }
}];



export const hiddenField = ({type,input}) => (
    <input name={input.name} type={type} style={{display:"none"}} {...input} />
)

class ShopPassWordUpdateForm extends React.Component {




    reSetAndClose(){
        this.props.reset();
        this.props.showUpdatePassword(false)
    }
    render() {
        const {handleSubmit, submitting} = this.props;
        const isUpdatePassWordObject = this.props.isUpdatePassWordObject;
        const showUpdatePassword = this.props.showUpdatePassword;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        return (
            <form onSubmit={handleSubmit} style={{display:"block"}}>
                {validFormState && errorValidMessage != "" && <ValidForm />}
                <Field  name="userId"  type="text"   component={hiddenInputField}  />
                <div className="layer" >
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>修改密码</span>
                            <a  href="javascript:void(0);"  className="close" onClick={() => { showUpdatePassword(false) }} ></a>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt">用户名：</div>
                                <div className="mrt"><span>{isUpdatePassWordObject.userName}</span></div>
                            </div>
                            <div className="item">
                                <div className="mlt">新密码：</div>
                                <div className="mrt">
                                    <Field  name="password"  type="password"   component={inputField}  />
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt">新密码确认：</div>
                                <div className="mrt">
                                    <Field  name="newPassword"  type="password"   component={inputField}  />
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button onClick={() => { this.props.checkValidForm(true) }}   style={{border: "none"}} className="confirm" disabled={submitting}>{submitting ? <i/> :<i/>}保存</button>
                            <a  href="javascript:void(0);"  className="cancel" onClick={() => { this.reSetAndClose()}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>)
    }
}

ShopPassWordUpdateForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
ShopPassWordUpdateForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({errorValidMessageFunction,checkValidForm}, dispatch);
}
function mapStateToProps(state) {
    return {
        fields,
        initialValues: state.todos.isUpdatePassWordObject,
        validate,
        state
    };
}
ShopPassWordUpdateForm = reduxForm({
    form: 'shopPassWordUpdateForm',
})(ShopPassWordUpdateForm);

ShopPassWordUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ShopPassWordUpdateForm);

export default ShopPassWordUpdateForm
