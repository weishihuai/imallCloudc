/**
 * Created by ou on 2017/7/12.
 */


import React, {PropTypes} from "react";
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {checkValidForm} from '../actions';
class ValidForm extends React.Component {

    render() {
        const checkValidForm = this.props.checkValidForm;
        const {errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
        return (<div className="layer" style={{display: "block", zIndex: "10000"}}>
            <div className="layer-box layer-error w430">
                <div className="layer-header">
                    <span>提示</span>
                    <button style={{border: "none"}} onClick={() => { checkValidForm(false)}} href="javascript:void(0);" className="close"></button>
                </div>
                <div className="layer-body">
                    <span>{errorValidMessage!==""?errorValidMessage:asyncValidMessage}</span>
                </div>
            </div>
        </div>);
    }
}

ValidForm.contextTypes = {
    store: React.PropTypes.object
};
function mapStateToProps(state) {
    return {
        state
    };
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({checkValidForm}, dispatch);
}
export default connect(mapStateToProps,mapDispatchToProps)(ValidForm);

/**
 * 使用方法，在引入组件
 *import ValidForm from '../../../../../common/validForm/components/ValidForm';
 *import {checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from '../../../../../common/validForm/actions';
 * reduce中使用:
 * const {validFormState, errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
 * const validState = asyncValidMessage != "" || errorValidMessage != "";
 * {validFormState && validState && <ValidForm />}
 *
 *  rootReducer.js引入todos:
 *  import validTodos from "../../../../../common/validForm/reducers/validTodos";
 *
 *
 *  具体参考StorageSpaceUpdateForm
 **/