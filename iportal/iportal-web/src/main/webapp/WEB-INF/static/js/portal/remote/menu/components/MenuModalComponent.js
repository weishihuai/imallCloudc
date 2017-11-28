import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalMenuAddModal,portalMenuUpdateModal,menuSaveUpdateData} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import MenuAddForm from './MenuAddForm';
import MenuUpdateForm from './MenuUpdateForm';


class MenuModalComponent extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const {actions,portalMenuAddModal,portalMenuUpdateModal,menuSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div>
                <Modal show={store.getState().todos.modalState} onHide={()=>portalMenuAddModal()} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>菜单新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <MenuAddForm store={store} actions={actions} onSubmit={(menuData)=>menuSaveUpdateData(menuData,true)} />
                    </Modal.Body>
                </Modal>


                <Modal show={store.getState().todos.updateModalState} onHide={()=>portalMenuUpdateModal()} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>菜单编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <MenuUpdateForm store={store} actions={actions} onSubmit={(menuData)=>menuSaveUpdateData(menuData,false)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

MenuModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalMenuAddModal,portalMenuUpdateModal,menuSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(MenuModalComponent);