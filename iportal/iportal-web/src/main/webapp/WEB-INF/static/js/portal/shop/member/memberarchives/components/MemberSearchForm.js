import React, {Component} from "react";

/*会员档案搜索表单*/
class MemberSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
    }

    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            searchFields: $.trim($("#searchFields").val()),
            name: $.trim($("#name").val()),
            createDateBeginString: $("#createDateBeginString").val(),
            createDateEndString: $("#createDateEndString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.memberList(newParam, 0, 10);
    };


    /*重置搜索参数*/
    onResetSearchParam() {
        $("#searchFields").val('');
        $("#name").val('');
        $("#createDateBeginString").val('');
        $("#createDateEndString").val('');
        this.props.actions.setSearchParams("");
        this.props.actions.memberList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input placeholder="会员卡号/手机号码" type="text" id="searchFields"/>
                </div>
                <div className="search">
                    <input placeholder="姓名" type="text" id="name"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="createDateBeginString" id="createDateBeginString" readOnly="readOnly" placeholder="创建时间" className="form-control datepicker" type="text"/>
                    </div>
                    <span className="float-left form-group-txt">至</span>
                    <div className="form-group float-left w140">
                        <input name="createDateEndString" id="createDateEndString" readOnly="readOnly" className="form-control datepicker"  type="text"/>
                    </div>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

MemberSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default MemberSearchForm;