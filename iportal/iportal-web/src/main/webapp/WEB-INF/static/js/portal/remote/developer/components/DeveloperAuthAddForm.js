import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import {portalDeveloperAuthAddModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['userName','hostname','isAvailable','appId']

class DeveloperAuthAddForm extends Component {
    render() {
        const { portalDeveloperAuthAddModal,fields: {userName,hostname,isAvailable,appId}, resetForm, handleSubmit, submitting } = this.props;
        const {store} = this.context;
        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label className="control-label col-md-3">用户名称：</label>
                    <div className="col-md-6">
                        <input id="userName" className="form-control required" type="text" data-max-length="100"  placeholder="用户名称"  {...userName}/>
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
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalDeveloperAuthAddModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}


DeveloperAuthAddForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}
DeveloperAuthAddForm.contextTypes = {
    store : React.PropTypes.object
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDeveloperAuthAddModal
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.developerData
    }
}

DeveloperAuthAddForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(DeveloperAuthAddForm);

DeveloperAuthAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DeveloperAuthAddForm);

export default DeveloperAuthAddForm