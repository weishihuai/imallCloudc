import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalDeveloperAuthAddModal,portalDeveloperAuthUpdateModal,developerAuthSaveUpdateData} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import DeveloperAuthAddForm from './DeveloperAuthAddForm';
import DeveloperAuthUpdateForm from './DeveloperAuthUpdateForm';

class DeveloperAuthModalComponent extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const {actions,portalDeveloperAuthAddModal,portalDeveloperAuthUpdateModal,developerAuthSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div>
                <Modal show={store.getState().todos.modalState} onHide={portalDeveloperAuthAddModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>开发者授权新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DeveloperAuthAddForm store={store} actions={actions} onSubmit={(data)=>developerAuthSaveUpdateData(data,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.updateModalState} onHide={portalDeveloperAuthUpdateModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>开发者授权编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DeveloperAuthUpdateForm store={store} actions={actions} onSubmit={(data)=>developerAuthSaveUpdateData(data,false,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

DeveloperAuthModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDeveloperAuthAddModal,
        portalDeveloperAuthUpdateModal,
        developerAuthSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DeveloperAuthModalComponent);