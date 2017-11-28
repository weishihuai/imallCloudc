import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {portalOperationalAuth} from '../actions';
import { Button ,Modal} from 'react-bootstrap';

class ImallButtonExport extends Component{

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
        const html = (<div className="input-group-btn mar-rgt" style={{display:"inline"}}>
            <button type="button" className="btn btn-success hidden-print"
                    data-toggle="dropdown" aria-haspopup="true"
                    aria-expanded="false">
                <i className="glyphicon glyphicon-export"></i>{this.props.text}
            </button>
            <ul className="dropdown-menu">
                <li><a href="javascript:void(0)" onClick={()=> this.props.exportAllFunc(this.props.tableId)}>导出全部</a></li>
                <li><a href="javascript:void(0)" onClick={()=> this.props.exportCurrentFunc(this.props.tableId)}>导出当前页</a></li>
            </ul>
        </div>);
        return this.checkAuthPermission(authsMap.get(this.props.permissionCode), html);
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOperationalAuth
    }, dispatch);
}

ImallButtonExport.propTypes = {
    tableId: PropTypes.string.isRequired,
    permissionCode: PropTypes.string.isRequired,
    text: PropTypes.string.isRequired,
    exportAllFunc: PropTypes.func.isRequired,
    exportCurrentFunc: PropTypes.func.isRequired
}

ImallButtonExport.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state
}

export default connect(mapStateToProps, mapDispatchToProps)(ImallButtonExport);