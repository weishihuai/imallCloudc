import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'
import {portalDictAddModal,portalDictUpdateModal,portalDictDelete,dictItemMgrModal} from '../actions';

class DictButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalDictAddModal,portalDictUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:sjzd:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={portalDictAddModal} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:sjzd:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times mar-rgt" onClick={() => actions.portalDictDelete(store.getState().todos.dictIds,actions)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDictAddModal,portalDictUpdateModal,portalDictDelete,dictItemMgrModal
    }, dispatch);
}

DictButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(DictButtonComponent);