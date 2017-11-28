import React,{Component} from 'react';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalCacheUpdateModal,portalCacheClear} from '../actions';
import { Button ,Modal} from 'react-bootstrap';
import ImallButton from '../../../../common/imallbutton/components/ImallButton'

class CacheButtonComponent extends Component{
    render(){
        const {store} = this.context;
        const {actions,portalCacheUpdateModal} = this.props;
        return (
            <div className="mar-btm col-sm-6">

            </div>);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalCacheUpdateModal,portalCacheClear
    }, dispatch);
}

CacheButtonComponent.contextTypes = {
    store : React.PropTypes.object
}

export default connect(null, mapDispatchToProps)(CacheButtonComponent);