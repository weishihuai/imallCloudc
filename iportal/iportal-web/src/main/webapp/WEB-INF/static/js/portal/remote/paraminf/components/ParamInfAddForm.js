import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import {portalParamInfAddModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = [ 'paramGroupTypeCode', 'paramTypeCode', 'innerCode' ,'paramNm','paramDescr','paramValue']

class ParamInfAddForm extends Component {
    render() {
        const { portalParamInfAddModal,fields: { paramGroupTypeCode, paramTypeCode, innerCode ,paramNm,paramDescr,paramValue}, resetForm, handleSubmit, submitting } = this.props
        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>
                <div className="form-group">
                    <label className="control-label col-md-4" for="paramGroupTypeCode">参数分组类型代码：</label>
                    <div className="col-md-5">
                        <select name="paramGroupTypeCode" id="paramGroupTypeCode" className="form-control" {...paramGroupTypeCode}>
                            <option value="USER_DEFINED" selected>用户自定义</option>
                            <option value="OTHER_PROMOTION">其他参数</option>
                            <option value="FOR_STORAGE">仅存储</option>
                        </select>
                    </div>
                </div>
                <div className="form-group">
                    <label className="control-label col-md-4" for="paramTypeCode">参数类型代码：</label>
                    <div className="col-md-5">
                        <select name="className" id="paramTypeCode" className="form-control" {...paramTypeCode}>
                            <option value="FILE" selected>文件</option>
                            <option value="PASSWORD">密码</option>
                            <option value="TEXT">单行文本</option>
                            <option value="MULTI_TEXT">多行文本</option>
                            <option value="HTML">html</option>
                            <option value="NUMBER">数字</option>
                            <option value="MONEY">金额</option>
                            <option value="DATE">日期</option>
                            <option value="TIME">时间</option>
                        </select>
                    </div>
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">内部代码：</label>
                    <div className="col-md-6">
                        <input id="innerCode" className="form-control required" type="text" data-max-length="128" placeholder="内部代码"  {...innerCode}/>
                    </div>
                    {innerCode.touched && innerCode.error && <div className="form_hit">{innerCode.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">参数名称：</label>
                    <div className="col-md-6">
                        <input id="paramNm" className="form-control required" type="text" data-max-length="64" placeholder="参数名称"  {...paramNm}/>
                    </div>
                    {paramNm.touched && paramNm.error && <div className="form_hit">{paramNm.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">参数描述：</label>
                    <div className="col-md-6">
                        <input id="paramDescr" className="form-control required" type="text" data-max-length="512" placeholder="参数描述"  {...paramDescr}/>
                    </div>
                    {paramDescr.touched && paramDescr.error && <div className="form_hit">{paramDescr.error}</div>}
                </div>
                <div className="form-group">
                    <label className="control-label col-md-3">参数值：</label>
                    <div className="col-md-6">
                        <input id="paramValue" className="form-control required" type="text" data-max-length="2147483647" placeholder="参数值" {...paramValue}/>
                    </div>
                    {paramValue.touched && paramValue.error && <div className="form_hit">{paramValue.error}</div>}
                </div>

                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalParamInfAddModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}


ParamInfAddForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalParamInfAddModal
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.paramInfData
    }
}

ParamInfAddForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(ParamInfAddForm);

ParamInfAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ParamInfAddForm);

export default ParamInfAddForm