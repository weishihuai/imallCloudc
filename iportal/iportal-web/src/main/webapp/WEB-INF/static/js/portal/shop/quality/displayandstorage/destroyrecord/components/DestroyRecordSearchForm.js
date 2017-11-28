import React, {Component} from "react";

class DestroyRecordSearchForm extends Component {

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
            keyword: $.trim($("#keyword").val()),
            batch: $.trim($("#batch").val()),
            fromDestroyTimeString: $.trim($("#fromDestroyTimeString").val()),
            toDestroyTimeString: $.trim($("#toDestroyTimeString").val()),
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.destroyRecordList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#keyword").val('');
        $("#batch").val('');
        $("#fromDestroyTimeString").val('');
        $("#toDestroyTimeString").val('');
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input id="keyword" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                </div>
                <div className="search">
                    <input id="batch" className="batch" placeholder="批号" type="text"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="fromDestroyTimeString" id="fromDestroyTimeString" placeholder="销毁时间" readOnly className="form-control datepicker" type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="toDestroyTimeString" id="toDestroyTimeString" className="form-control datepicker" readOnly type="text"/>
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

DestroyRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DestroyRecordSearchForm;