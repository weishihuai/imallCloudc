import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import {portalDictItemUpdateModal} from '../actions';
import {validate} from '../../../../common/common-validate';

export const fields = ['id' ,'dataDictId','dictItemNm' ,'dictItemCode','orderby' ,'isAvailable' ,'isDefault']

class DictItemUpdateForm extends Component {
    render() {
        const { portalDictItemUpdateModal,fields: {id,dataDictId, dictItemNm,dictItemCode,orderby,isAvailable,isDefault}, resetForm, handleSubmit, submitting } = this.props
        return (
            <div id="dictItemModalMsg">
                <form className="form-horizontal" onSubmit={handleSubmit}>

                    <input class="form-control" type="hidden" name="id"  {...id} />
                    <input class="form-control" type="hidden" name="dataDictId"  {...dataDictId} />


                    <div className="form-group">
                        <label className="control-label col-md-3">名称：</label>
                        <div className="col-md-6">
                            <input id="dictItemNm" className="form-control required" data-max-length="128" type="text" placeholder="名称"  {...dictItemNm}/>
                        </div>
                        {dictItemNm.touched && dictItemNm.error && <div className="form_hit">{dictItemNm.error}</div>}
                    </div>
                    <div className="form-group">
                        <label className="control-label col-md-3">编码：</label>
                        <div className="col-md-6">
                            <input id="dictItemCode" className="form-control required" data-max-length="32" type="text" placeholder="编码"  {...dictItemCode}/>
                        </div>
                        {dictItemCode.touched && dictItemCode.error && <div className="form_hit">{dictItemCode.error}</div>}
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
                    <div className="form-group">
                        <label className="control-label col-md-3">是否默认：</label>
                        <div className="col-md-6" id="isDefault">
                            <label className="radio-inline">
                                <input type="radio" {...isDefault} value="Y" checked={isDefault.value === 'Y'}/> 是
                            ：</label>
                            <label className="radio-inline">
                                <input type="radio" {...isDefault} value="N" checked={isDefault.value === 'N'}/> 否
                            ：</label>
                            {isDefault.touched && isDefault.error && <div className="form_hit">{isDefault.error}</div>}
                        </div>
                    </div>

                    <div className="modal-footer" style={{"background":"none"}}>
                        <button className="btn btn-primary" type="submit" disabled={submitting}>
                            {submitting ? <i/> : <i/>} 提交
                        </button>
                        <button className="btn btn-default ngdialog-custom-close" type="button" disabled={submitting} onClick={()=>portalDictItemUpdateModal(null)}>
                            关闭
                        </button>
                    </div>
                </form>
            </div>
        )
    }
}


DictItemUpdateForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDictItemUpdateModal
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.dictItemData
    }
}

DictItemUpdateForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(DictItemUpdateForm);

DictItemUpdateForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(DictItemUpdateForm);

export default DictItemUpdateForm