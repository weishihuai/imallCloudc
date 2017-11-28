import React,{PropTypes, Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalAppAddModal,portalAppUpdateModal,portalAppDelete} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'
import ImallButtonExport from '../../../../common/imallbutton/components/ImallButtonExport'
import {showFileMgrModal} from '../../../../common/filemgr/actions';

class AppButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalAppAddModal,portalAppUpdateModal,showFileMgrModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">
                <ImallButton permissionCode="portal:sysmgr:yygl:add" className="btn btn-success btn-labeled fa fa-plus mar-rgt" onClick={portalAppAddModal} text="新增" />
                <ImallButton permissionCode="portal:sysmgr:yygl:delete" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times mar-rgt" onClick={() => actions.portalAppDelete(store.getState().todos.appIds,actions)} text="删除" />
                {/*<ImallButton permissionCode="portal:sysmgr:yygl:wjgl" className="ui-btn-enable btn btn-danger btn-labeled fa fa-times" onClick={() => actions.portalAppDelete(store.getState().todos.appIds,actions)} text="删除" />*/}
                <ImallButtonExport permissionCode="portal:sysmgr:yygl:add" exportAllFunc={this.props.exportAllFunc} exportCurrentFunc={this.props.exportCurrentFunc} text="导出CSV"/>
            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalAppAddModal,portalAppUpdateModal,portalAppDelete,showFileMgrModal
    }, dispatch);
}

AppButtonComponent.contextTypes = {
    store : PropTypes.object,
    exportAllFunc: PropTypes.func.isRequired,
    exportCurrentFunc: PropTypes.func.isRequired
}

export default connect(null, mapDispatchToProps)(AppButtonComponent);