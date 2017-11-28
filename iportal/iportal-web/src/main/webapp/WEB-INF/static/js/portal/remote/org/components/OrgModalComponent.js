import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalOrgAddModal,portalOrgUpdateModal,portalOrgSaveUpdateData} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import OrgAddForm from './OrgAddForm';
import OrgUpdateForm from './OrgUpdateForm';


class OrgModalComponent extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const {actions,portalOrgAddModal,portalOrgUpdateModal,portalOrgSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.modalState} onHide={portalOrgAddModal} backdrop="static" bsSize={"large"}>
                    <Modal.Header closeButton>
                        <Modal.Title>组织添加</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <OrgAddForm store={store} actions={actions} onSubmit={(data)=>portalOrgSaveUpdateData(data,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.updateModalState} onHide={portalOrgUpdateModal} backdrop="static" bsSize={"large"}>
                    <Modal.Header closeButton>
                        <Modal.Title>组织修改</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <OrgUpdateForm store={store} actions={actions} onSubmit={(data)=>portalOrgSaveUpdateData(data,false,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

OrgModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOrgAddModal,
        portalOrgUpdateModal,
        portalOrgSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OrgModalComponent);