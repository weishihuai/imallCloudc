import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalOperationalAuth} from '../actions';
import { Button ,Modal} from 'react-bootstrap';

class ImallButton extends Component{

    componentWillMount(){
        const permissionCodes = [this.props.permissionCode];
        const {portalOperationalAuth} = this.props;
        portalOperationalAuth(permissionCodes);
    }

    checkAuthPermission(permissionValue, htmlxml){
        return permissionValue==true ? htmlxml : <div></div>;
    }

    render(){
        const {store} = this.context;
        const authsMap = store.getState().authTodos.authsMap;
        return this.checkAuthPermission(authsMap.get(this.props.permissionCode), <Button className={this.props.className} onClick={this.props.onClick} >{this.props.text}</Button>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOperationalAuth
    }, dispatch);
}

ImallButton.propTypes = {
    permissionCode: PropTypes.string.isRequired,
    className: PropTypes.string.isRequired,
    text: PropTypes.string.isRequired,
    onClick: PropTypes.func.isRequired
}

ImallButton.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state
}

export default connect(mapStateToProps, mapDispatchToProps)(ImallButton);