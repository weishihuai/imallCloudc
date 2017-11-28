import React, {Component, PropTypes} from "react";
import {Field, reduxForm} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {
    portalMenuAddModal,
    resourceSelectModal,
    iconSelectModal,
    bindFormValue,
    resourceFormChangeToState
} from "../actions";
import {validate} from "../../../../common/common-validate";

export const fields = [ 'parentId' , 'menuName' , 'menuType' , 'resourceName' , 'resourceId', 'isAvailable' , 'icon' ,'orderby'];

export const inputField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="form-group">
        <label className="control-label  col-md-3">
            {required && <i className="text-danger"/>}
            {label}：
        </label>
        <div className="col-md-6 form-content">
            <input id={input.id} name={input.name} className={"form-control " + required}  type={type} data-max-length={input.maxlength} {...input}/>
            {touched && error && <div className="form_hit">{error}</div>}
        </div>
    </div>
);

export const selectField = ({ input, label, type, id, required, items, optionName,meta: { touched, error } }) => (
    <div className="form-group">
        <label className="control-label col-md-3">
            {required && <i className="text-danger"/>}
            {label}：
        </label>
        <div className="form-content col-md-6">
            <select id={id} name={input.name} className={"form-control " + required} {...input}>
                {
                    items.map(function (item, index) {
                        return (<option key={index} value={item.value}>{item.optionName}</option>);
                    })
                }
            </select>
        </div>
        {touched && error && <div className="form_hit">{error}</div>}
    </div>
);


export const resourceField = ({ input, label, type, id, required, clickEvent, meta: { touched, error } }) => (
    <div className="form-group">
        <label className="control-label col-md-3">
            {required&& <i className="text-danger"/>}
            {label}：
        </label>
        <div className="col-md-6">
            <input id={id} name={input.name}  className="form-control " type={type} readOnly="readonly" {...input} />
        </div>
        <div className="col-md-2">
            <button type="button" onClick={()=>clickEvent()} className="btn btn-default" >选择</button>
        </div>
        {touched && error && <div className="form_hit">{error}</div>}

    </div>
);

export const boolRadioField = ({ input, label, type, id, required, meta: { touched, error } }) => (
    <div className="form-group">
        <label className="control-label col-md-3">{label}：</label>
        <div className="col-md-9" id={id} name={input.name} >
            <label className="radio-inline">
                <input type="radio" {...input} value="Y" checked={input.value === 'Y'}/> 可用
            </label>
            <label className="radio-inline">
                <input type="radio" {...input} value="N" checked={input.value === 'N'}/> 不可用
            </label>
            {touched && error && <div className="form_hit">{error}</div>}
        </div>
    </div>
);


export const iconField = ({ input, label, type, id, required, clickEvent, meta: { touched, error } }) => (
    <div className="form-group">
        <label className="control-label col-md-3">
            {required&& <i className="text-danger"/>}
            {label}：
        </label>
        <div className="col-md-1">
            <svg className="imall-custom-big-icon" aria-hidden="true">
                <use xlinkHref="#" id="menu_icon_select"></use>
            </svg>
        </div>
        <div className="col-md-5">
            <button type="button" onClick={()=>clickEvent()} className="btn btn-default" >选择</button>
            <input className="form-control" type="hidden" name={input.name} id={input.id} {...input} />
        </div>
        {touched && error && <div className="form_hit">{error}</div>}
    </div>
);

class MenuAddForm extends Component {

    render() {
        const {store} = this.context;
        const {portalMenuAddModal,resourceFormChangeToState,resourceSelectModal,iconSelectModal, handleSubmit, submitting } = this.props;

        function menuTypeCodeValue(){
            return [
                {
                    optionName:"应用",
                    value:"APP",
                },
                {
                    optionName:"模块",
                    value:"MDL",
                },
                {
                    optionName:"菜单",
                    value:"MENU",
                },
                {
                    optionName:"按钮",
                    value:"BTN",
                }
            ];
        }

        function getFormData(){
            const formValues = store.getState().form.menuAddForm.values;
            return {
                parentId: formValues.parentId,
                menuName: formValues.menuName,
                menuType: formValues.menuType,
                resourceId: formValues.resourceId,
                resourceName: formValues.resourceName,
                isAvailable: formValues.isAvailable,
                icon: formValues.icon,
                orderby: formValues.orderby,
            };
        }

        return (
            <div>
                <form className="form-horizontal" onSubmit={handleSubmit}>
                    <Field id="parentId" name="parentId" type="hidden" component="input" />

                    <Field id="menuName" name="menuName" type="text" component={inputField} label="名称" required="required" maxlength="100" />
                    <Field id="menuType" name="menuType" type="text" component={selectField} label="类型"  items={menuTypeCodeValue()} optionName="menuType"/>
                    <Field id="resourceName" name="resourceName" type="text" component={resourceField} label="资源" required="required" clickEvent={()=>{resourceFormChangeToState(getFormData());resourceSelectModal()}} />
                    <Field id="isAvailable" name="isAvailable" type="text" component={boolRadioField} label="是否可用" />
                    <Field id="alertIcon" name="icon" type="text" component={iconField} label="图标" required="required" clickEvent={()=>{resourceFormChangeToState(getFormData());iconSelectModal()}} />
                    <Field id="orderby" name="orderby" type="text" component={inputField} label="顺序(数字)" required="required"/>

                    <div className="modal-footer" style={{"background":"none"}}>
                        <button className="btn btn-primary" type="submit" disabled={submitting}>
                            {submitting ? <i/> : <i/>} 提交
                        </button>
                        <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalMenuAddModal}>
                            关闭
                        </button>
                    </div>
                </form>
            </div>
        )
    }
}

MenuAddForm.contextTypes = {
    store : React.PropTypes.object
};


MenuAddForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalMenuAddModal,resourceSelectModal,iconSelectModal,bindFormValue,resourceFormChangeToState
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.menuData,
        fields: fields
    }
}


MenuAddForm = reduxForm({
    form: 'menuAddForm',
    enableReinitialize: true,
    validate
})(MenuAddForm);

MenuAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(MenuAddForm);

export default MenuAddForm