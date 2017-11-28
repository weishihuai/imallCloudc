import React, { Component, PropTypes } from 'react'
import {PORTAL_ORG_SEARCH} from '../constants/ActionTypes';
class OrgSearchForm extends Component {
    constructor(props) {
        super(props);
    };
    componentWillUpdate(){
        const {store} = this.context;
        var $ = require("jquery");
        if(store.getState().todos.isRefreshForm){
            $('#searchTypeMenu .icon-share').html("组织名称");
            $("#searchValue").attr("name", "orgName");
        }
    }
    onSelect(){
        var $ = require("jquery");
        $("#searchTypeMenu").find('li').click(function () {
            var type = $(this).data('type');
            var text = $(this).children('a').html();
            $(this).parent().parent().find('.icon-share').html(text);
            $(this).parent().parent().next('input').attr("name", type);
        })
    }
    handleSearch() {
        const {store} = this.context;
        const treePId = store.getState().todos.treePId;

        var $ = require("jquery");
        var isAvailable=$("#imallSearchParam").find('#isAvailable').val();
        var searchName=$("#searchTypeMenu").next('input').attr("name");
        var searchValue=$("#searchTypeMenu").next('input').val();
        store.dispatch({type:PORTAL_ORG_SEARCH, searchData:{isAvailable:isAvailable,searchName:searchName,searchValue:searchValue}});
        this.props.actions.list(isAvailable,searchName,searchValue,treePId,0,10);
    }
    handleChange(event) {
        var searchValue=""
        var isAvailable="";
        const {store} = this.context;
        var searchName=$("#searchTypeMenu").next('input').attr("name");
        if(event.target.name=="isAvailable"){
            isAvailable=event.target.value
        }else {
            var isAvailable=$("#imallSearchParam").find('#isAvailable').val();
            searchValue=event.target.value
        }
        store.dispatch({type:PORTAL_ORG_SEARCH, searchData:{isAvailable:isAvailable,searchName:searchName,searchValue:searchValue},isRefreshForm:false});
    }

    render() {
        const {store} = this.context;
        return (
            <div className="col-sm-6 text-xs-center text-right"  id="imallSearchParam">
                <form className="form-inline filter-box">
                    <div className="form-group">
                        <label className="control-label" htmlFor="isAvailable">是否可用：</label>
                        <select name="isAvailable" className="form-control" id="isAvailable" onChange={this.handleChange.bind(this)} value={store.getState().todos.searchData.isAvailable}>
                            <option value="">全部</option>
                            <option value="Y">可用</option>
                            <option value="N">不可用</option>
                        </select>
                    </div>
                    <div className="form-group mar-lft">
                        <div className="input-group">
                            <div className="input-group-btn" id="searchTypeMenu">
                                <button type="button" className="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" onClick={this.onSelect}>
                                    <span className="icon-share">组织名称</span>
                                    <span className="caret"></span>
                                </button>
                                <ul className="dropdown-menu">
                                    <li data-type="orgName"><a href="javascript:void(0)">组织名称</a></li>
                                    <li data-type="orgCode"><a href="javascript:void(0)">组织编码</a></li>
                                    <li data-type="phone"><a href="javascript:void(0)">电话号码</a></li>
                                </ul>
                            </div>
                            <input name="orgName" id="searchValue" type="text" className="form-control" onChange={this.handleChange.bind(this)} value={store.getState().todos.searchData.searchValue}/>
                            <span className="input-group-btn">
                                <a className="btn btn-default btn-labeled fa fa-search" role="button" onClick={this.handleSearch.bind(this)}>查询
                                </a>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

OrgSearchForm.contextTypes = {
    store : React.PropTypes.object
}

export default OrgSearchForm;
