import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField} from "./FieldComponent";
import {checkValidForm, errorValidMessageFunction, asyncErrorValidMessageFunction} from "../../../../../common/validForm/actions";
import {validate} from "../../../../../common/redux-form-ext";
import ValidForm from "../../../../../common/validForm/components/ValidForm";

import {checkByCategoryNm} from '../actions/index';

export const fields = [
    {
        field:'categoryNm',
        validate:{
            fieldNm: "分类名称",
            required:true,
            maxlength:32
        }
    }
];

class DrugCombinationCategoryAddForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    valid(){
        const {checkValidForm} = this.props;
        checkValidForm(true);
    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, asyncValidMessage, validFormState} = this.context.store.getState().validTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit} id="addForm">
                {validFormState && (errorValidMessage || asyncValidMessage) && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>新增联合用药分类</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>{actions.showAddForm(false)}}/>
                        </div>
                        <div className="layer-body">
                            <Field id="categoryNm" name="categoryNm" timeInput="" type="text" component={inputField} label="分类名称" required="required"/>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{border:"none"}} onClick={() => {this.valid()}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={()=>{actions.showAddForm(false)}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

DrugCombinationCategoryAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugCombinationCategoryAddForm.contextTypes = {
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
        fields: fields,
        validate: validate,
        asyncBlurFields: ['categoryNm'],
        asyncValidate: checkByCategoryNm,
        state
    }
}

DrugCombinationCategoryAddForm = reduxForm({
    form: 'drugCombinationCategoryAddForm',
    enableReinitialize: true
})(DrugCombinationCategoryAddForm);

DrugCombinationCategoryAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCombinationCategoryAddForm);

export default DrugCombinationCategoryAddForm;