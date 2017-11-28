import React, { Component, PropTypes } from 'react'

class ExceptionSearchForm extends Component {
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
        this.props.actions.list(searchName,searchValue,0,10);
    }

    render() {
        return (
            <div className="col-sm-6 text-xs-center text-right"  id="imallSearchParam">
                <form className="form-inline filter-box">
                    <div className="form-group">
                        <div className="input-group">
                            <div className="input-group-btn" id="searchTypeMenu">
                                <button type="button" className="btn btn-default dropdown-toggle"
                                        data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false" onClick={this.onSelect}>
                                    <span className="icon-share">异常编码</span>
                                    <span className="caret"></span>
                                </button>
                                <ul className="dropdown-menu">
                                    <li data-type="code"><a href="javascript:void(0)">异常编码</a></li>
                                    <li data-type="exceptionMsg"><a href="javascript:void(0)">异常提示</a></li>
                                </ul>
                            </div>
                            <input name="code" type="text" className="form-control"/>
                            <span className="input-group-btn">
                              <a className="btn btn-default btn-labeled fa fa-search" role="button" onClick={this.handleSearch.bind(this)}>查询</a>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
        )
    }
}

ExceptionSearchForm.contextTypes = {
    store : React.PropTypes.object
}

export default ExceptionSearchForm;
