import React, {Component, PropTypes} from "react";
import {Field, reduxForm} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import ValidForm from "../../../../../common/validForm/components/ValidForm";
import {validate} from "../../../../../common/redux-form-ext";
import {checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from "../../../../../common/validForm/actions";


export const inputField = ({ input, type, className,  meta: { touched, error } }) => (
        <input type={type} name={input.name} {...input}/>
);


class EnableForm extends Component {

    submit(data){
        const {store} = this.context;
        const {params,enableData} = store.getState().todos;
        this.props.actions.updateIsEnable(Object.assign({},data,{
            id:enableData.id,
            operationState:enableData.operationState
        }),params);
    }


    render() {
        const {actions} = this.props;
        const { handleSubmit, submitting } = this.props;
        const {store} = this.context;
        const {enableData} = store.getState().todos;
        const {validFormState, errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
        const {checkValidForm} = this.props;
        const validState = asyncValidMessage !="" || errorValidMessage !="";
        return (
            <form onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && validState && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer" >
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>{enableData.operationState=="Y"?"禁用":"启用"}商品</span>
                            <a href="javascript:void(0);" onClick={()=>actions.changeEnableFormState(false)} className="close"/>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt"><span>*</span>商品名称:</div>
                                <div className="mrt"><span>{enableData.goodsNm}</span></div>
                            </div>
                            <div className="item">
                                <div className="mlt"><span>*</span>{enableData.operationState=="Y"?"禁用":"启用"}原因:</div>
                                <div className="mrt">
                                    <Field type="text" name="reason" component={inputField}/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} type="submit" onClick={() => {checkValidForm(true)}}>保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>actions.changeEnableFormState(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>

        )
    }
}


EnableForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};
EnableForm.contextTypes = {
    store: React.PropTypes.object
};
function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
        asyncErrorValidMessageFunction
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.enableData,
        fields: state.todos.enableField,
        state
    }
}

EnableForm = reduxForm({
    form: 'enableForm',
    validate,
})(EnableForm);

EnableForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(EnableForm);

export default EnableForm