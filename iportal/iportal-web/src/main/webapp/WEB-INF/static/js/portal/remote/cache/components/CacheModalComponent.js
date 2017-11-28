import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {portalCacheUpdateModal,cacheSaveUpdateData}  from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import CacheUpdateForm from './CacheUpdateForm';

class CacheModalComponent extends Component{
    constructor(props){
        super(props);
    }
    render(){
        const {actions,portalCacheUpdateModal,cacheSaveUpdateData} = this.props;
        const {store} = this.context;
        return (
            <div  >
                <Modal show={store.getState().todos.updateModalState} onHide={portalCacheUpdateModal} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>修改失效时间</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <CacheUpdateForm store={store} actions={actions} onSubmit={(cacheData)=>cacheSaveUpdateData(cacheData,actions)} />
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

CacheModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalCacheUpdateModal,
        cacheSaveUpdateData
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(CacheModalComponent);