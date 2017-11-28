import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalExceptionUpdateModal,exceptionSaveUpdateData}  from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import ExceptionUpdateForm from './ExceptionUpdateForm';

class ExceptionModalComponent extends Component{
    constructor(props){
        super(props);
    }
    render(){
        const {actions,portalExceptionUpdateModal,exceptionSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.updateModalState} onHide={portalExceptionUpdateModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>修改提示</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ExceptionUpdateForm store={store} actions={actions} onSubmit={(exceptionData)=>exceptionSaveUpdateData(exceptionData,false,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

ExceptionModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalExceptionUpdateModal,
        exceptionSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ExceptionModalComponent);