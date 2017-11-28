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
        field:'id',
        validate:{

        }
    },
    {
        field:'categoryNm',
        validate:{
            fieldNm: "分类名称",
            required:true,
            maxlength:32
        }
    }
];

class DrugCombinationCategoryEditForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    componentDidMount() {

    }

    valid(){
        const {checkValidForm} = this.props;
        checkValidForm(true);
    }

    render() {
        const {actions} = this.props;
        const {handleSubmit, submitting, checkValidForm } = this.props;
        const {errorValidMessage, asyncValidMessage, validFormState} = this.context.store.getState().validTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit}>
                {validFormState && (errorValidMessage || asyncValidMessage) && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>编辑联合用药分类</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>{actions.showEditForm(false)}}/>
                        </div>
                        <div className="layer-body">
                            <Field id="id" name="id" type="hidden" component={inputField} required="required" isShow="false"/>
                            <Field id="categoryNm" name="categoryNm" timeInput="" type="text" component={inputField} label="分类名称" required="required"/>
                        </div>
                        <div className="layer-footer">
                            <button type="submit"  className="confirm" style={{"border":"none"}} onClick={() => {this.valid()}}  disabled={submitting}>{submitting ? <i/> : <i/>}保存</button>
                            <a href="javascript:void(0);" className="cancel" disabled={submitting} onClick={()=>{actions.showEditForm(false)}}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

DrugCombinationCategoryEditForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugCombinationCategoryEditForm.contextTypes = {
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
        initialValues: state.todos.data,
        fields: fields,
        validate: validate,
        asyncBlurFields: ['categoryNm'],
        asyncValidate: checkByCategoryNm,
        state
    }
}

DrugCombinationCategoryEditForm = reduxForm({
    form: 'drugCombinationCategoryEditForm',
    enableReinitialize: true
})(DrugCombinationCategoryEditForm);

DrugCombinationCategoryEditForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCombinationCategoryEditForm);

export default DrugCombinationCategoryEditForm;