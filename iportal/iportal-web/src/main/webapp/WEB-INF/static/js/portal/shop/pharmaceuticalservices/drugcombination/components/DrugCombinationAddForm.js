import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {checkValidForm, errorValidMessageFunction} from "../../../../../common/validForm/actions";
import {inputField, textareaField, validate} from "../../../../../common/redux-form-ext";
import ValidForm from "../../../../../common/validForm/components/ValidForm";

import {selectField} from './FieldComponent';

export const fields = [
    {
        field:'drugCombinationCategoryId',
        validate:{
            fieldNm: "联合用药分类",
            required:true
        }
    },
    {
        field:'disease',
        validate:{
            fieldNm: "病症",
            required:true,
            maxlength:64
        }
    },
    {
        field:'symptom',
        validate:{
            fieldNm: "症状",
            required:true,
            maxlength:256
        }
    },
    {
        field:'commonSense',
        validate:{
            fieldNm: "常识判断",
            required:true,
            maxlength:256
        }
    },
    {
        field:'drugUsePrinciple',
        validate:{
            fieldNm: "用药原则",
            maxlength:256
        }
    },
    {
        field:'leadingDrug',
        validate:{
            fieldNm: "主导用药",
            required:true,
            maxlength:256
        }
    },
    {
        field:'drugCombination',
        validate:{
            fieldNm: "联合用药",
            maxlength:256
        }
    },
    {
        field:'majorConcern',
        validate:{
            fieldNm: "专业关怀",
            maxlength:256
        }
    }
];

class DrugCombinationAddForm extends Component{
    constructor(props) {
        super(props);
    }

    componentWillMount() {
        this.props.actions.initCategories();
        $("#drugCombinationCategoryId").jSelect();
    }

    componentDidUpdate() {

    }

    valid(){
        const {checkValidForm} = this.props;
        checkValidForm(true);
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {handleSubmit, submitting, checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;

        return(
            <form className="form-horizontal" onSubmit={handleSubmit} id="addForm">
                {validFormState && errorValidMessage && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-info layer-medication layer-medication-add w960">
                        <div className="layer-header">
                            <span>新增联合用药</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>{actions.showAddForm(false)}}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <Field id="drugCombinationCategoryId" name="drugCombinationCategoryId" type="text" component={selectField} label="分类名称" items={store.getState().todos.categories} optionValue="id" optionName="categoryNm" required="required"/>
                                    <Field id="disease" name="disease" type="text" className="item-1-2" component={inputField} label="病症" maxLength="64" required="required"/>
                                    <Field id="symptom" name="symptom" type="text" className="item-1-2" component={textareaField} label="症状" maxLength="256" required="required"/>
                                    <Field id="commonSense" name="commonSense" className="item-1-2" component={textareaField} label="常识判断" maxLength="256" required="required"/>
                                    <Field id="drugUsePrinciple" name="drugUsePrinciple" className="item-1-2" component={textareaField} label="用药原则" maxLength="256" />
                                    <Field id="leadingDrug" name="leadingDrug" className="item-1-2" component={textareaField} label="主导用药" maxLength="256" required="required" />
                                    <Field id="drugCombination" name="drugCombination" className="item-1-2" component={textareaField} label="联合用药" maxLength="256" />
                                    <Field id="majorConcern" name="majorConcern" className="item-1-2" component={textareaField} label="专业关怀" maxLength="256" />
                                </div>
                            </div>
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

DrugCombinationAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

DrugCombinationAddForm.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({checkValidForm, errorValidMessageFunction}, dispatch);
}

function mapStateToProps(state) {
    return {
        fields: fields,
        validate: validate,
        state
    }
}

DrugCombinationAddForm = reduxForm({
    form: 'drugCombinationAddForm',
    enableReinitialize: true
})(DrugCombinationAddForm);

DrugCombinationAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DrugCombinationAddForm);

export default DrugCombinationAddForm;