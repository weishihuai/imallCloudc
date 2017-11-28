import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalParamInfAddModal,portalParamInfUpdateModal,paramInfSaveUpdateData} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import ParamInfAddForm from './ParamInfAddForm';
import ParamInfUpdateForm from './ParamInfUpdateForm';


class ParamInfModalComponent extends Component{
    constructor(props){
        super(props);
    }


    render(){
        const {actions,portalParamInfAddModal,portalParamInfUpdateModal,paramInfSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.modalState} onHide={portalParamInfAddModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>系统参数新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ParamInfAddForm store={store} actions={actions} onSubmit={(paramInfData)=>paramInfSaveUpdateData(paramInfData,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.updateModalState} onHide={portalParamInfUpdateModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>系统参数编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <ParamInfUpdateForm store={store} actions={actions} onSubmit={(paramInfData)=>paramInfSaveUpdateData(paramInfData,false,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

ParamInfModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalParamInfAddModal,
        portalParamInfUpdateModal,
        paramInfSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ParamInfModalComponent);