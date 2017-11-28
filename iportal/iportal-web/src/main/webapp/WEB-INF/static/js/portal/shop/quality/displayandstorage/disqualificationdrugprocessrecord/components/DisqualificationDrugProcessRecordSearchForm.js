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
            recordDateStartTimeString: $.trim($("#recordDateStartTimeString").val()),
            recordDateEndTimeString: $.trim($("#recordDateEndTimeString").val()),
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.disqualificationDrugProcessRecordList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#keyword").val('');
        $("#recordDateStartTimeString").val('');
        $("#recordDateEndTimeString").val('');
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input id="keyword" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="recordDateStartTimeString" id="recordDateStartTimeString" placeholder="日期" readOnly className="form-control datepicker" type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="recordDateEndTimeString" id="recordDateEndTimeString" className="form-control datepicker" readOnly type="text"/>
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