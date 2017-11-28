import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form'
import {connect} from 'react-redux';
import { bindActionCreators } from 'redux';
import {portalCacheUpdateModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['key','cacheEntityName', 'cacheEntityPath', 'cacheEntityExpireSeconds','appName','requestCount','writeCount','hitCount','clearCount']

class CacheUpdateForm extends Component {

    render() {
        const {portalCacheUpdateModal,fields: {key,cacheEntityName, cacheEntityPath, appName,cacheEntityExpireSeconds,requestCount,writeCount,hitCount,clearCount}, resetForm, handleSubmit, submitting } = this.props

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>

                <input class="form-control" type="hidden" name="key"  {...key} />

                <div className="form-group">
                    <label className="control-label col-md-3">所属应用：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false"  id="appName" className="form-control required" type="text" data-max-length="200" placeholder="所属应用"  {...appName}/>
                    </div>
                    {appName.touched && appName.error && <div className="form_hit">{appName.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">类名：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false" id="cacheEntityNameInput" className="form-control required" type="text" data-max-length="120"  placeholder="类名"  {...cacheEntityName}/>
                    </div>
                    {cacheEntityName.touched && cacheEntityName.error && <div className="form_hit">{cacheEntityName.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">路径：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false" id="cacheEntityPath" className="form-control required" type="text" data-max-length="200"  placeholder="路径"  {...cacheEntityPath}/>
                    </div>
                    {cacheEntityPath.touched && cacheEntityPath.error && <div className="form_hit">{cacheEntityPath.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">失效时间：</label>
                    <div className="col-md-6">
                        <input id="cacheEntityExpireSeconds" className="form-control required" type="text" data-max-length="200" placeholder="失效时间"  {...cacheEntityExpireSeconds}/>
                    </div>
                    {cacheEntityExpireSeconds.touched && cacheEntityExpireSeconds.error && <div className="form_hit">{cacheEntityExpireSeconds.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">请求次数：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false" id="cacheEntityExpireSeconds" className="form-control required" type="text" data-max-length="200" placeholder="请求次数"  {...requestCount}/>
                    </div>
                    {requestCount.touched && requestCount.error && <div className="form_hit">{requestCount.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">写入次数：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false"  id="cacheEntityExpireSeconds" className="form-control required" type="text" data-max-length="200" placeholder="写入次数"  {...writeCount}/>
                    </div>
                    {writeCount.touched && writeCount.error && <div className="form_hit">{writeCount.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">命中次数：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false"  id="cacheEntityExpireSeconds" className="form-control required" type="text" data-max-length="200" placeholder="命中次数"  {...hitCount}/>
                    </div>
                    {hitCount.touched && hitCount.error && <div className="form_hit">{hitCount.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">擦除次数：</label>
                    <div className="col-md-6">
                        <input style={{"background-color":"gray"}} disabled="false"  id="cacheEntityExpireSeconds" className="form-control required" type="text" data-max-length="200" placeholder="擦除次数"  {...clearCount}/>
                    </div>
                    {clearCount.touched && clearCount.error && <div className="form_hit">{clearCount.error}</div>}
                </div>


                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalCacheUpdateModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}

CacheUpdateForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.cacheData
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalCacheUpdateModal
    }, dispatch);
}

CacheUpdateForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(CacheUpdateForm)

CacheUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(CacheUpdateForm)

export default CacheUpdateForm