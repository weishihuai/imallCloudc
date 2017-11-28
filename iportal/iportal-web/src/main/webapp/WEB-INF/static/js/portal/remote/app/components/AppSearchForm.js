import React, { Component, PropTypes } from 'react'

class AppSearchForm extends Component {
    constructor(props) {
        super(props);
    };

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
        var $ = require("jquery");
        var isAvailable=$("#imallSearchParam").find('#isAvailable').val();
        var searchName=$("#searchTypeMenu").next('input').attr("name");
        var searchValue=$("#searchTypeMenu").next('input').val();
        this.props.actions.applist(isAvailable,searchName,searchValue,0, 10);
    }

    search(size, callback){
        var $ = require("jquery");
        var isAvailable=$("#imallSearchParam").find('#isAvailable').val();
        var searchName=$("#searchTypeMenu").next('input').attr("name");
        var searchValue=$("#searchTypeMenu").next('input').val();
        this.props.actions.exportSearch(isAvailable,searchName,searchValue,0, size, callback);
    }

    render() {
        return (
            <div className="col-sm-6 text-xs-center text-right"  id="imallSearchParam">
                <form className="form-inline filter-box">
                    <div className="form-group">
                        <label className="control-label" htmlFor="isAvailable">是否可用：</label>
                        <select name="isAvailable" className="form-control" id="isAvailable">
                            <option value="">全部</option>
                            <option value="Y">可用</option>
                            <option value="N">不可用</option>
                        </select>
                    </div>

                    <div className="form-group mar-lft">
                        <div className="input-group">
                            <div className="input-group-btn" id="searchTypeMenu">
                                <button type="button" className="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false" onClick={this.onSelect}>
                                    <span className="icon-share">应用名称</span>
                                    <span className="caret"></span>
                                </button>
                                <ul className="dropdown-menu">
                                    <li data-type="appName"><a href="javascript:void(0)">应用名称</a></li>
                                    <li data-type="appCname"><a href="javascript:void(0)">中文名称</a></li>
                                    <li data-type="hostname"><a href="javascript:void(0)">主机</a></li>
                                </ul>
                            </div>
                            <input type="text" name="appName" className="form-control"/>
                            <span className="input-group-btn">
                             <a className="btn btn-default btn-labeled fa fa-search"
                                     role="button" onClick={this.handleSearch.bind(this)} >查询
                             </a>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

AppSearchForm.contextTypes = {
    store : React.PropTypes.object
}

export default AppSearchForm;
