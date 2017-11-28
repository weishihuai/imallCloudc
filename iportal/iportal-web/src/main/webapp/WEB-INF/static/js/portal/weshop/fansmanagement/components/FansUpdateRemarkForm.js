import React, {Component, PropTypes} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {Field, reduxForm} from "redux-form";
import {errorValidMessageFunction,checkValidForm} from '../../../../common/validForm/actions';
import ValidForm from '../../../../common/validForm/components/ValidForm';

export const validate = (values, props) => {
    const errors = {};
    if (values.remark !== undefined && values.remark !== "" && values.remark !== null && values.remark.length > 0) {
        if (values.remark.length > 128) {
            errors.remark = "备注最大长度为128位";
            props.errorValidMessageFunction(errors.remark);
            return errors;
        }
    }
    props.errorValidMessageFunction("");
    return errors;
};

/*文本域*/
export const textareaField = ({ input, label, type, className, inputClassName,placeholder, id, required, maxLength,disabled, meta: { touched, error } }) => (
    <div>
        <textarea type={type} id={id ? id : ""} name={input.name} className={inputClassName ? inputClassName : ""} placeholder={placeholder ? placeholder : ""}  style={{color:"#333"}} disabled ={disabled  ? disabled :""} maxLength={maxLength ? maxLength : ""} {...input}/>
    </div>
);

class FansUpdateRemarkForm extends React.Component {

    componentWillMount() {

    }

    componentDidMount() {

    }

    componentDidUpdate(){

    }

    render() {
        const {submitting, handleSubmit} = this.props;
        const actions = this.props.actions;
        const {checkValidForm} = this.props;
        const {errorValidMessage, validFormState} = this.context.store.getState().validTodos;
        let index = this.props.index;
        let totalLength = this.props.totalLength;  //数据总条数
        let totalPages = this.props.totalPages;  //总页数
        let indexNum = index + 1;  //标识每条数据的排序号
        if (totalPages > 1) {
            indexNum = indexNum - (totalLength / 10) * 10;
        }
        //计算数据在该页的位置(每页的倒数后三条数据采用向上弹窗，其他采用向下弹窗)
        let num;
        if (totalLength > 10) {
            num = (totalLength - (totalLength / 10) * 10) - indexNum;
        }else {
            num = totalLength - indexNum;
        }

        function formatModel(actions,className,checkValidForm) {
            return <div className={className}>
                <Field id="remark" name="remark" placeholder="不要超过128字" type="text" component={textareaField} label=""/>
                <div className="layer-note-footer">
                    <button href="javascript:void(0);" className="confirm" style={{border:"none"}} disabled={submitting} onClick={()=>{checkValidForm(true)}}>{submitting ? <i/> : <i/>}保存</button>
                    <a href="javascript:void(0);" className="cancel" onClick={() => actions.fansUpdateRemarkModel(false)}>取消</a>
                </div>
                <em/>
            </div>
        }

        return (
            <form onSubmit={handleSubmit}>
                {validFormState && errorValidMessage !== "" && <ValidForm  checkValidForm={checkValidForm}/>}
                {num > 2 ? formatModel(actions,"layer-note",checkValidForm) : formatModel(actions,"layer-note layer-note2",checkValidForm)}
            </form>
        );
    }
}

FansUpdateRemarkForm.contextTypes = {
    store: React.PropTypes.object
};

FansUpdateRemarkForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

FansUpdateRemarkForm = reduxForm({
    form: 'fansUpdateRemarkForm',
    enableReinitialize: true,
})(FansUpdateRemarkForm);

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        checkValidForm,
        errorValidMessageFunction,
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        state,
        validate
    };
}

export default connect(mapStateToProps, mapDispatchToProps)(FansUpdateRemarkForm);