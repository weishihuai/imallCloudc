import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalOrgAddModal,portalOrgUpdateModal,portalOrgDelete} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

class OrgButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalOrgAddModal,portalOrgUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:zzgl:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={portalOrgAddModal} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:zzgl:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times" onClick={() => actions.portalOrgDelete(store.getState().todos.ids,actions,store.getState().todos.treePId)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOrgAddModal,portalOrgUpdateModal,portalOrgDelete
    }, dispatch);
}

OrgButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(OrgButtonComponent);