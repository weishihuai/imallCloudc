import React, {Component, PropTypes} from "react";
import {Field, reduxForm} from "redux-form";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {portalResourceUpdateModal} from "../actions";
import {validate} from "../../../../common/common-validate";

export const fields = ['parentId','id','resourceName', 'permissionCode', 'resourceType' ,'routerKey','routerTemplateJs','orderby','isAvailable','appId','nodeCode']
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
                        return (<option key={index} value={item.id}>{item.appName}</option>);
                    })
                }
            </select>
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


class ResourceUpdateForm extends Component {
    render() {
        const { portalResourceUpdateModal,fields: {parentId,id,resourceName, permissionCode, resourceType ,routerKey,routerTemplateJs,orderby,isAvailable,appId,nodeCode}, resetForm, handleSubmit, submitting } = this.props
        const {store} = this.context;

        function resourceTypeCodeValue(){
            return [
                {
                    appName:"元素",
                    id:"ELM",
                },
                {
                    appName:"按钮",
                    id:"BTN",
                },
                {
                    appName:"链接",
                    id:"LNK",
                },
                {
                    appName:"模块",
                    id:"MDL",
                },
                {
                    appName:"应用",
                    id:"APP",
                }
            ];
        }
        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>

                <Field id="parentId" name="parentId" type="hidden" component="input" />
                <Field id="parentId" name="id" type="hidden" component="input" />
                <Field id="parentId" name="nodeCode" type="hidden" component="input" />
                <Field id="resourceName" name="resourceName" type="text" component={inputField} label="资源名称" required="required" maxlength="100" />
                <Field id="permissionCode" name="permissionCode" type="text" component={inputField} label="权限编码" required="required" maxlength="100" />
                <Field id="resourceType" name="resourceType" type="text" component={selectField} label="资源类型"  items={resourceTypeCodeValue()}/>
                <Field id="routerKey" name="routerKey" type="text" component={inputField} label="路由Key" required="required" maxlength="200" />
                <Field id="routerTemplateJs" name="routerTemplateJs" type="text" component={inputField} label="路由模版JS" required="required" maxlength="200" />
                <Field id="orderby" name="orderby" type="text" component={inputField} label="顺序(数字)" required="required" maxlength="19" />
                <Field id="isAvailable" name="isAvailable" type="text" component={boolRadioField} label="是否可用" />
                <Field id="appId" name="appId" type="text" component={selectField} label="app应用"  required="required" items={store.getState().todos.apps}/>

                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalResourceUpdateModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}


ResourceUpdateForm.propTypes = {
    handleSubmit: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
};

ResourceUpdateForm.contextTypes = {
    store : React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalResourceUpdateModal
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.data,
        fields: fields
    }
}

ResourceUpdateForm = reduxForm({
    form: 'resourceUpdateForm',
    enableReinitialize: true,
    validate
})(ResourceUpdateForm);

ResourceUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ResourceUpdateForm);

export default ResourceUpdateForm