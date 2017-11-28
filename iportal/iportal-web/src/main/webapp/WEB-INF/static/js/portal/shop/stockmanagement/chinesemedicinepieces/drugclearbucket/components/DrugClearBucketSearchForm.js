import React, {Component} from "react";

/*药品清斗搜索表单*/
class DrugClearBucketSearchForm extends Component {

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

    /*搜索*/
    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            searchFields: $.trim($("#searchFields").val()),
            batch: $.trim($("#batch").val()),
            clearBucketManName: $.trim($("#clearBucketManName").val()),
            clearBucketStartTimeString: $("#clearBucketStartTimeString").val(),
            clearBucketEndTimeString: $("#clearBucketEndTimeString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.drugClearBucketList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#searchFields").val('');
        $("#batch").val('');
        $("#clearBucketManName").val('');
        $("#clearBucketStartTimeString").val('');
        $("#clearBucketEndTimeString").val('')
        this.props.actions.setSearchParams("");
        this.props.actions.drugClearBucketList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <input id="searchFields" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                <input id="batch" className="batch" placeholder="批号" type="text"/>
                <input id="clearBucketManName" className="batch" placeholder="清斗人" type="text"/>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="clearBucketStartTimeString" id="clearBucketStartTimeString" placeholder="清斗日期" readOnly className="form-control datepicker" type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="clearBucketEndTimeString" id="clearBucketEndTimeString" className="form-control datepicker" readOnly type="text"/>
                    </div>
                </div>
                <a href="javascript:void(0);" className="green-btn" onClick={this.handleSearch.bind(this)}>查询</a>
                <a href="javascript:void(0);" className="gray-btn" onClick={this.onResetSearchParam.bind(this)}>重置</a>
            </div>
        )
    }
}

DrugClearBucketSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugClearBucketSearchForm;