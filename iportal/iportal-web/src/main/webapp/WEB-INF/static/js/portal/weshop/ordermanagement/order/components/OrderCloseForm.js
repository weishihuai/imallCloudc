import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import {validate} from "../../../../../common/redux-form-ext";
import ValidForm from "../../../../../common/validForm/components/ValidForm";

import {radioField} from "./FieldComponent";

export const fields = [
    {
        field:'closeReasonCode',
        validate:{
            fieldNm: "关闭原因",
            required:true
        }
    }
];

class OrderCloseForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    render(){
        const {actions, handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && errorValidMessage && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-start layer-close-order w430">
                        <div className="layer-header">
                            <span>选择关闭原因</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>actions.showCloseForm(false)}/>
                        </div>
                        <input type="hidden" name="id"/>
                        <input type="hidden" name="orderStateCode"/>
                        <Field name="cancelOrderReason" component={radioField} required="required"/>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {this.props.checkValidForm(true)}} disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={()=>actions.showCloseForm(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

OrderCloseForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired,
    actions: PropTypes.object.isRequired
};

OrderCloseForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.orderTodos.data,
        fields: fields,
        validate: validate,
        state
    }
}

OrderCloseForm = reduxForm({
    form: 'orderCloseForm',
    enableReinitialize: true
})(OrderCloseForm);

OrderCloseForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(OrderCloseForm);

export default OrderCloseForm;