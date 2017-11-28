import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalResourceAddModal,portalResourceUpdateModal,portalResourceSaveUpdateData,portalResourceUrlMgrModal} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import ResourceAddForm from './ResourceAddForm';
import ResourceUpdateForm from './ResourceUpdateForm';
import ResourceUrlMgr from './ResourceUrlMgr';



class ResourceModalComponent extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const {actions,portalResourceAddModal,portalResourceUpdateModal,portalResourceSaveUpdateData,portalResourceUrlMgrModal} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.modalState} onHide={portalResourceAddModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>资源新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ResourceAddForm store={store} actions={actions} onSubmit={(data)=>portalResourceSaveUpdateData(data,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.updateModalState} onHide={portalResourceUpdateModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>资源编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ResourceUpdateForm store={store} actions={actions} onSubmit={(data)=>portalResourceSaveUpdateData(data,false,actions)} />
                    </Modal.Body>
                </Modal>
                <Modal show={store.getState().todos.resourceUrlMgrModalState} onHide={()=>portalResourceUrlMgrModal()} backdrop="static" style={{"height":"300px"}}>
                    <Modal.Header closeButton>
                        <Modal.Title>管理资源URL</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ResourceUrlMgr store={store} actions={actions} />
                        <div className="modal-footer" style={{"background":"none","border": "none"}}>
                            <button className="btn btn-default ngdialog-custom-close" type="button" onClick={()=>portalResourceUrlMgrModal()}>
                                关闭
                            </button>
                        </div>
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

ResourceModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalResourceAddModal,
        portalResourceUpdateModal,
        portalResourceSaveUpdateData,
        portalResourceUrlMgrModal
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ResourceModalComponent);