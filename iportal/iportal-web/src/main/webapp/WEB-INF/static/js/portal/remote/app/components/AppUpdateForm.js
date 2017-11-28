import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form'
import {connect} from 'react-redux';
import { bindActionCreators } from 'redux';
import {portalAppUpdateModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['id', 'appName', 'appCname', 'appKey' ,'appSecret','hostname','webContext','orderby','isAvailable']

class AppUpdateForm extends Component {

    render() {
        const {portalAppUpdateModal,fields: { id,appName, appCname, appKey ,appSecret,hostname,webContext,orderby,isAvailable}, resetForm, handleSubmit, submitting } = this.props

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>

                <input class="form-control" type="hidden" name="id"  {...id} />

                <div className="form-group">
                    <label className="control-label col-md-3">应用名称：</label>
                    <div className="col-md-6">
                        <input id="appName" className="form-control required" type="text" data-max-length="100"  placeholder="应用名称"  {...appName}/>
                    </div>
                    {appName.touched && appName.error && <div className="form_hit">{appName.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">应用中文名称：</label>
                    <div className="col-md-6">
                        <input id="appCname" className="form-control required" type="text" data-max-length="100" placeholder="应用中文名称"  {...appCname}/>
                    </div>
                    {appCname.touched && appCname.error && <div className="form_hit">{appCname.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">应用KEY：</label>
                    <div className="col-md-6">
                        <input id="appKey" className="form-control required" type="text" data-max-length="100" placeholder="应用KEY"  {...appKey}/>
                    </div>
                    {appKey.touched && appKey.error && <div className="form_hit">{appKey.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">APP_SECRET：</label>
                    <div className="col-md-6">
                        <input id="appSecret" className="form-control required" type="text" data-max-length="100" placeholder="APP_SECRET" {...appSecret}/>
                    </div>
                    {appSecret.touched && appSecret.error && <div className="form_hit">{appSecret.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">hostname：</label>
                    <div className="col-md-6">
                        <input id="hostname" className="form-control required" type="text" data-max-length="100" placeholder="hostname" {...hostname}/>
                    </div>
                    {hostname.touched && hostname.error && <div className="form_hit">{hostname.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">应用上下文：</label>
                    <div className="col-md-6">
                        <input id="webContext" className="form-control required" type="text" data-max-length="100" placeholder="应用上下文"  {...webContext}/>
                    </div>
                    {webContext.touched && webContext.error && <div className="form_hit">{webContext.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">顺序(数字)：</label>
                    <div className="col-md-6">
                        <input id="orderby" className="form-control required" data-max-length="19" data-number-input type="text" placeholder="顺序"  {...orderby}/>
                    </div>
                    {orderby.touched && orderby.error && <div className="form_hit">{orderby.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">是否可用：</label>
                    <div className="col-md-6" id="isAvailable">
                        <label className="radio-inline">
                            <input type="radio" {...isAvailable} value="Y" checked={isAvailable.value === 'Y'}/> 可用
                        </label>
                        <label className="radio-inline">
                            <input type="radio" {...isAvailable} value="N" checked={isAvailable.value === 'N'}/> 不可用
                        </label>
                        {isAvailable.touched && isAvailable.error && <div className="form_hit">{isAvailable.error}</div>}
                    </div>
                </div>

                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalAppUpdateModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}

AppUpdateForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.appData
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalAppUpdateModal
    }, dispatch);
}

AppUpdateForm = reduxForm({
        form: 'synchronousValidation',
        fields,
        validate
    })(AppUpdateForm)

AppUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(AppUpdateForm)

export default AppUpdateForm