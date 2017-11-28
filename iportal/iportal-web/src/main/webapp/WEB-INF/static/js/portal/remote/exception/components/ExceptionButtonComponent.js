import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalExceptionUpdateModal,portalExceptionDelete} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

class ExceptionButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalExceptionUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">

            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalExceptionUpdateModal,portalExceptionDelete
    }, dispatch);
}

ExceptionButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(ExceptionButtonComponent);