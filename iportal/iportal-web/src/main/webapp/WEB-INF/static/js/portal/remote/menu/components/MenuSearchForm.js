import React, { Component, PropTypes } from 'react'
import {PORTAL_MENU_SEARCH} from '../constants/ActionTypes';
class MenuSearchForm extends Component {
    constructor(props) {
        super(props);
    };

    handleSearch() {
        const {store} = this.context;
        const treePId = store.getState().todos.treePId;

        var $ = require("jquery");
        var menuName=$("#imallSearchParam").find('#menuNameSearch').val();

        store.dispatch({type:PORTAL_MENU_SEARCH, searchData:{menuName:menuName}});
        this.props.actions.list(menuName,treePId,0,10);
    }

    render() {
        return (
            <div className="col-sm-6 text-xs-center text-right"  id="imallSearchParam">
                <form className="form-inline filter-box">
                    <div className="form-group">
                        <div className="input-group">
                            <span className="input-group-addon">名称：</span>
                            <input name="menuName" type="text" className="form-control" id="menuNameSearch"/>
                            <span className="input-group-btn">
                                <a className="btn btn-default btn-labeled fa fa-search" role="button" onClick={this.handleSearch.bind(this)}>
                                    查询
                                </a>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

MenuSearchForm.contextTypes = {
    store : React.PropTypes.object
}

export default MenuSearchForm;
