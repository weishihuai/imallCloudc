import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'
import {portalResourceUrlDelete} from '../actions';

class ResourceUrlButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalResourceUrlDelete} = this.props;
        return (
            <div className="mar-btm">
                <ImallButton permissionCode="portal:sysmgr:zygl:mgrUrl:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times fa-lg single" onClick={() => portalResourceUrlDelete(store.getState().todos.id,store.getState().todos.resourceUrlIds,actions)} text="删除" />
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalResourceUrlDelete
    }, dispatch);
}

ResourceUrlButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(ResourceUrlButtonComponent);