import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import {portalDictUpdateModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['id' , 'dictNm', 'dictType', 'orderby' ,'isAvailable']

class DictUpdateForm extends Component {
    render() {
        const { portalDictUpdateModal,fields: {id, dictNm, dictType,orderby,isAvailable}, resetForm, handleSubmit, submitting } = this.props
        return (
            <div id="dictModalMsg">
                <form className="form-horizontal" onSubmit={handleSubmit}>

                    <input class="form-control" type="hidden" name="id"  {...id} />

                    <div className="form-group">
                        <label className="control-label col-md-3">名称：</label>
                        <div className="col-md-6">
                            <input id="dictNm" className="form-control required" type="text" data-max-length="128" placeholder="名称"  {...dictNm}/>
                        </div>
                        {dictNm.touched && dictNm.error && <div className="form_hit">{dictNm.error}</div>}
                    </div>
                    <div className="form-group">
                        <label className="control-label col-md-3">字典类型：</label>
                        <div className="col-md-6">
                            <input id="dictType" className="form-control required" type="text" data-max-length="32" placeholder="字典类型"  {...dictType}/>
                        </div>
                        {dictType.touched && dictType.error && <div className="form_hit">{dictType.error}</div>}
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
                        <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={()=>portalDictUpdateModal(null)}>
                            关闭
                        </button>
                    </div>
                </form>
            </div>
            
        )
    }
}


DictUpdateForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDictUpdateModal
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.dictData
    }
}

DictUpdateForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(DictUpdateForm);

DictUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DictUpdateForm);

export default DictUpdateForm