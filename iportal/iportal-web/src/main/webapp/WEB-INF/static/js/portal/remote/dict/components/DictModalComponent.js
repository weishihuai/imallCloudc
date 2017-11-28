import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalDictAddModal,portalDictUpdateModal,dictSaveUpdateData,dictItemMgrModal} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import DictAddForm from './DictAddForm';
import DictUpdateForm from './DictUpdateForm';
import DictItemMgr from './DictItemMgr';


class DictModalComponent extends Component{
    constructor(props){
        super(props);
    }

    render(){
        const {actions,portalDictAddModal,portalDictUpdateModal,dictSaveUpdateData,dictItemMgrModal} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.modalState} onHide={portalDictAddModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>数据字典新增</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DictAddForm store={store} actions={actions} onSubmit={(dictData)=>dictSaveUpdateData(dictData,true,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.updateModalState} onHide={()=>portalDictUpdateModal(null)} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>数据字典编辑</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DictUpdateForm store={store} actions={actions} onSubmit={(dictData)=>dictSaveUpdateData(dictData,false,actions)} />
                    </Modal.Body>
                </Modal>

                <Modal show={store.getState().todos.dictItemMgrModalState} onHide={()=>dictItemMgrModal()} backdrop="static" bsSize="large">
                    <Modal.Header closeButton>
                        <Modal.Title>数据字典项管理</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <DictItemMgr store={store} actions={actions} />
                    </Modal.Body>
                    <Modal.Footer>
                        <div className="modal-footer" style={{"background":"none"}}>
                            <button className="btn btn-default ngdialog-custom-close" type="button" onClick={()=>dictItemMgrModal()}>
                                关闭
                            </button>
                        </div>
                    </Modal.Footer>
                </Modal>
            </div>
        );
    }
}

DictModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalDictAddModal,
        portalDictUpdateModal,
        dictSaveUpdateData,
        dictItemMgrModal
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DictModalComponent);