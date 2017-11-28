import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalAppAddModal,portalAppUpdateModal,appSaveUpdateData} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import AppAddForm from './AppAddForm';
import AppUpdateForm from './AppUpdateForm';


class AppModalComponent extends Component{
    constructor(props){
        super(props);
    }

    /*saveForm(values){
        var description = "";
        for(var i in values){
            var property=values[i];
            description+=i+" = "+property+"\n";
        }
        const {actions} = this.props;
        alert("调用函数=appSaveUpdateData");
        actions.appSaveUpdateData(values);
    }*/

    render(){
        const {actions,portalAppAddModal,portalAppUpdateModal,appSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.modalState} onHide={portalAppAddModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>应用新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <AppAddForm store={store} actions={actions} onSubmit={(appData)=>appSaveUpdateData(appData,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.updateModalState} onHide={portalAppUpdateModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>应用编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <AppUpdateForm store={store} actions={actions} onSubmit={(appData)=>appSaveUpdateData(appData,false,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

AppModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalAppAddModal,
        portalAppUpdateModal,
        appSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(AppModalComponent);