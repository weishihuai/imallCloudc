import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalResourceAddModal,portalResourceUpdateModal,portalResourceDelete} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

class ResourceButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalResourceAddModal,portalResourceUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:zygl:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={()=>portalResourceAddModal(store.getState().todos.treePId)} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:zygl:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-pencil  mar-rgt" onClick={() => actions.portalResourceDelete(store.getState().todos.ids,actions,store.getState().todos.treePId)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalResourceAddModal,portalResourceUpdateModal,portalResourceDelete
    }, dispatch);
}

ResourceButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(ResourceButtonComponent);