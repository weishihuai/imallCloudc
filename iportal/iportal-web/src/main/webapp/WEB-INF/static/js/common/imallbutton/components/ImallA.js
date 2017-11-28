import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalOperationalAuth} from '../actions';

class ImallA extends Component{

    checkAuthPermission(permissionValue, htmlxml){
        return permissionValue==true ? htmlxml : <div></div>;
    }

    render(){
        const {store} = this.context;
        const authsMap = store.getState().authTodos.authsMap;
        return this.checkAuthPermission(authsMap.get(this.props.permissionCode), <a target={this.props.target || ''} className={this.props.className} href={this.props.href} onClick={this.props.onClick} >{this.props.text}</a>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOperationalAuth
    }, dispatch);
}

ImallA.propTypes = {
    permissionCode: PropTypes.string.isRequired,
    className: PropTypes.string.isRequired,
    text: PropTypes.string.isRequired,
    onClick: PropTypes.func.isRequired
}

ImallA.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state
}

export default connect(mapStateToProps, mapDispatchToProps)(ImallA);