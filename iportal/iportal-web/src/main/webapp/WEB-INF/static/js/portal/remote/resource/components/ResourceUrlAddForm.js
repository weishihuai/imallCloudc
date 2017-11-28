import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import {portalResourceUrlSaveUpdateData} from '../actions';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'
import {validate} from '../../../../common/common-validate';

export const fields = ['resourceId','url']

class ResourceUrlAddForm extends Component {
    submitMyForm(data) {
        const {resetForm,portalResourceUrlSaveUpdateData} = this.props;
        resetForm();
        portalResourceUrlSaveUpdateData(data);
    }

    render() {
        const {fields: { resourceId,url}, resetForm, handleSubmit, submitting} = this.props
        const {store} = this.context;
        return (
            <div id="resourceUrlModalMsg" name="resourceUrlAddForm">
                <form className="form-horizontal bord-all mar-btm">
                    <fieldset>
                        <legend>新增</legend>
                        <div className="form-group">
                            <label for="addResouUrl" className="control-label col-md-3 col-sm-3">资源URL：</label>
                            <div className="col-md-6 col-sm-6">
                                <input type="hidden"  id="resourceId" className="form-control"  {...resourceId}/>
                                <input type="text"  id="url" data-max-length="256"  className="form-control"   {...url}/>
                            </div>
                            <ImallButton permissionCode="portal:sysmgr:zygl:mgrUrl:add" className="btn btn-primary" onClick={handleSubmit(this.submitMyForm.bind(this))} text="保存" />
                        </div>
                    </fieldset>
                </form>
            </div>
        )
    }
}


ResourceUrlAddForm.propTypes = {
    fields: PropTypes.object.isRequired,
    handleSubmit: PropTypes.func.isRequired,
    resetForm: PropTypes.func.isRequired,
    submitting: PropTypes.bool.isRequired
}
ResourceUrlAddForm.contextTypes = {
    store : React.PropTypes.object
}
function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalResourceUrlSaveUpdateData
    }, dispatch);
}

function mapStateToProps(state) {
    return {
        initialValues: state.todos.resourceUrlData
    }
}

ResourceUrlAddForm = reduxForm({
    form: 'synchronousValidation',
    fields,
    validate
})(ResourceUrlAddForm);

ResourceUrlAddForm = connect(
    mapStateToProps,
    mapDispatchToProps
)(ResourceUrlAddForm);

export default ResourceUrlAddForm