import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form'
import {connect} from 'react-redux';
import { bindActionCreators } from 'redux';
import {portalExceptionUpdateModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['id', 'code', 'exceptionMsg', 'remark']

class ExceptionUpdateForm extends Component {

    render() {
        const {portalExceptionUpdateModal,fields: { id,code, exceptionMsg, remark}, resetForm, handleSubmit, submitting } = this.props

        return (
            <form className="form-horizontal" onSubmit={handleSubmit}>

                <input class="form-control" type="hidden" name="id"  {...id} />

                <div className="form-group">
                    <label className="control-label col-md-3">异常编码：</label>
                    <div className="col-md-6">
                        <input id="codeInput" className="form-control required" type="text" data-max-length="120"  placeholder="异常编码"  {...code}/>
                    </div>
                    {code.touched && code.error && <div className="form_hit">{code.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">异常提示：</label>
                    <div className="col-md-6">
                        <input id="exceptionMsg" className="form-control required" type="text" data-max-length="200"  placeholder="异常提示"  {...exceptionMsg}/>
                    </div>
                    {exceptionMsg.touched && exceptionMsg.error && <div className="form_hit">{exceptionMsg.error}</div>}
                </div>

                <div className="form-group">
                    <label className="control-label col-md-3">异常备注：</label>
                    <div className="col-md-6">
                        <input id="remark" className="form-control required" type="text" data-max-length="200" placeholder="异常备注"  {...remark}/>
                    </div>
                    {remark.touched && remark.error && <div className="form_hit">{remark.error}</div>}
                </div>


                <div className="modal-footer" style={{"background":"none"}}>
                    <button className="btn btn-primary" type="submit" disabled={submitting}>
                        {submitting ? <i/> : <i/>} 提交
                    </button>
                    <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={portalExceptionUpdateModal}>
                        关闭
                    </button>
                </div>
            </form>
        )
    }
}

ExceptionUpdateForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.exceptionData
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalExceptionUpdateModal
    }, dispatch);
}

ExceptionUpdateForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(ExceptionUpdateForm)

ExceptionUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ExceptionUpdateForm)

export default ExceptionUpdateForm