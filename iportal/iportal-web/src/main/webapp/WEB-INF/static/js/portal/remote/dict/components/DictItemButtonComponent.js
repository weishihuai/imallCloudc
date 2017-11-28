import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'
import {portalDictItemAddModal,portalDictItemUpdateModal,portalDictItemDelete} from '../actions';

class DictItemButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalDictItemAddModal,portalDictItemUpdateModal,portalDictItemDelete} = this.props;
        return (
            <div className="mar-btm">
                <ImallButton permissionCode="portal:sysmgr:sjzd:item:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={()=>portalDictItemAddModal(store.getState().todos.dictId)} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:sjzd:item:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times" onClick={() => portalDictItemDelete(store.getState().todos.dictId,store.getState().todos.dictItemIds,actions)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDictItemAddModal,portalDictItemUpdateModal,portalDictItemDelete
    }, dispatch);
}

DictItemButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(DictItemButtonComponent);