import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalDeveloperAuthAddModal,portalDeveloperAuthUpdateModal,portalDeveloperAuthDelete} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

class DeveloperAuthButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalDeveloperAuthAddModal,portalDeveloperAuthUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:kfzsq:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={portalDeveloperAuthAddModal} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:kfzsq:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times mar-rgt" onClick={() => actions.portalDeveloperAuthDelete(store.getState().todos.developerIds,actions)} text="删除" />
            </div>
        );
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDeveloperAuthAddModal,portalDeveloperAuthUpdateModal,portalDeveloperAuthDelete
    }, dispatch);
}

DeveloperAuthButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

DeveloperAuthButtonComponent.contextTypes = {
    store : React.PropTypes.object
}
export default connect(null, mapDispatchToProps)(DeveloperAuthButtonComponent);