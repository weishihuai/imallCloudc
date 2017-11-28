import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

import {portalMenuAddModal,portalMenuUpdateModal,portalMenuDelete} from '../actions';

class MenuButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalMenuAddModal,portalMenuUpdateModal,portalMenuDelete} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:cdgl:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={()=>portalMenuAddModal(store.getState().todos.treePId)} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:cdgl:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times" onClick={() => portalMenuDelete(store.getState().todos.menuIds,actions,store.getState().todos.treePId)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalMenuAddModal,portalMenuUpdateModal,portalMenuDelete
    }, dispatch);
}

MenuButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(MenuButtonComponent);