import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalDictItemAddModal,portalDictItemUpdateModal,dictItemSaveUpdateData} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import DictItemAddForm from './DictItemAddForm';
import DictItemUpdateForm from './DictItemUpdateForm';


class DictItemModalComponent extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const {actions,portalDictItemAddModal,portalDictItemUpdateModal,dictItemSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.dictItemModalState} onHide={portalDictItemAddModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>字典项新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DictItemAddForm store={store} actions={actions} onSubmit={(dictItemData)=>dictItemSaveUpdateData(dictItemData,store.getState().todos.dictId,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.dictItemUpdateModalState} onHide={()=>portalDictItemUpdateModal(null)} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>字典项编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DictItemUpdateForm store={store} actions={actions} onSubmit={(dictItemData)=>dictItemSaveUpdateData(dictItemData,store.getState().todos.dictId,false,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

DictItemModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDictItemAddModal,
        portalDictItemUpdateModal,
        dictItemSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DictItemModalComponent);