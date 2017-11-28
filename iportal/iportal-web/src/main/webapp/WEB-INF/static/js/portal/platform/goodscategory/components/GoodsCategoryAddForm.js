import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {inputField,validate} from "../../../../common/redux-form-ext";
import {REGEXP_SIGNLESS_INT} from "../../../../common/common-constant";
import {checkValidForm, errorValidMessageFunction,asyncErrorValidMessageFunction} from "../../../../common/validForm/actions";
import ValidForm from "../../../../common/validForm/components/ValidForm";
import {asyncValidateForSave} from "../actions/asyncValidate";
export const fields = [
    {
        field:'categoryName',
        validate:{
            fieldNm: "分类名称",
            maxlength:32,
            required:true
        }
    },{
        field:'displayPosition',
        validate:{
            fieldNm: "排序",
            maxlength:19,
            required:true,
            regx:REGEXP_SIGNLESS_INT,
            error:"排序请输入大于零的整数"
        }
    },
];


class GoodsCategoryAddForm extends Component{
    constructor(props) {
        super(props);
    }

    submit(data){
        const {store} = this.context;
        const id = store.getState().todos.id;
        this.props.actions.saveOrUpdate(Object.assign({},data,{
            parentId:id
        }),true);
    }

    render() {
        const {actions,handleSubmit, submitting} = this.props;
        const {validFormState, errorValidMessage,asyncValidMessage} = this.context.store.getState().validTodos;
        const validState = asyncValidMessage !="" || errorValidMessage !="" ;
        const {checkValidForm} = this.props;
        return(
            <form className="form-horizontal" onSubmit={handleSubmit(this.submit.bind(this))}>
                {validFormState && validState && <ValidForm  checkValidForm={checkValidForm}/>}
                <div className="layer">
                    <div className="layer-box layer-start w430">
                        <div className="layer-header">
                            <span>添加商品分类</span>
                            <a href="javascript:void(0)" onClick={()=>actions.changeAddFormState(false)} className="close"/>
                        </div>
                        <div className="layer-body">
                            <div className="item">
                                <div className="mlt"><span>*</span>分类名称</div>
                                <div className="mrt">
                                    <Field name="categoryName" type="text" component={inputField}/>
                                </div>
                            </div>
                            <div className="item">
                                <div className="mlt"><span>*</span>排序</div>
                                <div className="mrt">
                                    <Field name="displayPosition" type="text" component={inputField}/>
                                </div>
                            </div>
                        </div>
                        <div className="layer-footer">
                            <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} type="submit" onClick={() =>checkValidForm(true)}>保存</button>
                            <a href="javascript:void(0);" className="cancel" onClick={()=>actions.changeAddFormState(false)}>取消</a>
                        </div>
                    </div>
                </div>
            </form>
        );
    }
}

GoodsCategoryAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

GoodsCategoryAddForm.contextTypes = {
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
        state
    }
}

GoodsCategoryAddForm = reduxForm({
    form: 'goodsCategoryAddForm',
    validate:validate,
    asyncBlurFields: ['categoryName'],
    asyncValidate: asyncValidateForSave
})(GoodsCategoryAddForm);

GoodsCategoryAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsCategoryAddForm);

export default GoodsCategoryAddForm;
