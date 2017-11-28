import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form'
import {connect} from 'react-redux';
import { bindActionCreators } from 'redux';
import {portalDeveloperAuthUpdateModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['id','userName','hostname','isAvailable','appId']

class DeveloperAuthUpdateForm extends Component {

    render() {
        const {portalDeveloperAuthUpdateModal,fields: { id,userName,hostname,isAvailable,appId}, resetForm, handleSubmit, submitting } = this.props
        const {store} = this.context;
        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>

                <input class="form-control" type="hidden" name="id"  {...id} />

                <div className="form-group">
                    <label className="control-label col-md-3">用户名称：</label>
                    <div className="col-md-6">
                        <input id="userName" className="form-control required" type="text" data-max-length="100" placeholder="用户名称"  {...userName}/>
                    </div>
                    {userName.touched && userName.error && <div className="form_hit">{userName.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">hostname：</label>
                    <div className="col-md-6">
                        <input id="hostname" className="form-control required" type="text" data-max-length="32" placeholder="hostname"  {...hostname}/>
                    </div>
                    {hostname.touched && hostname.error && <div className="form_hit">{hostname.error}</div>}
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
                <div className="form-group">
                    <label className="control-label col-md-3">app应用：</label>
                    <div className="col-md-6">
                        <select name="appId" id="appId" className="form-control" {...appId}>
                            {store.getState().todos.apps.map(appOption => <option value={appOption.id} key={appOption.id}>{appOption.appName}</option>)}
                        </select>
                    </div>
                    {appId.touched && appId.error && <div className="form_hit">{appId.error}</div>}
                </div>
                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalDeveloperAuthUpdateModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}

DeveloperAuthUpdateForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}
DeveloperAuthUpdateForm.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.developerData
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDeveloperAuthUpdateModal
    }, dispatch);
}

DeveloperAuthUpdateForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(DeveloperAuthUpdateForm)

DeveloperAuthUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DeveloperAuthUpdateForm)

export default DeveloperAuthUpdateForm