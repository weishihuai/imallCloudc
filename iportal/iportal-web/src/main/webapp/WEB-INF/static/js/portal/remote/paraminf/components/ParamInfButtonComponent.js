import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalParamInfAddModal,portalParamInfUpdateModal,portalParamInfDelete} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

class ParamInfButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalParamInfAddModal,portalParamInfUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:xtcs:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={portalParamInfAddModal} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:xtcs:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times" onClick={() => actions.portalParamInfDelete(store.getState().todos.paramInfIds,actions)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalParamInfAddModal,portalParamInfUpdateModal,portalParamInfDelete
    }, dispatch);
}

ParamInfButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(ParamInfButtonComponent);