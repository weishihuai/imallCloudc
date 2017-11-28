import React, {Component} from "react";

/*药品装斗搜索表单*/
class DrugInBucketSearchForm extends Component {

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
            batch: $.trim($("#batch").val()),
            inBucketManName: $.trim($("#inBucketManName").val()),
            inBucketStartTimeString: $("#inBucketStartTimeString").val(),
            inBucketEndTimeString: $("#inBucketEndTimeString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.drugInBucketList(newParam, 0, 10);
    };


    /*重置搜索参数*/
    onResetSearchParam() {
        $("#searchFields").val('');
        $("#batch").val('');
        $("#inBucketManName").val('');
        $("#inBucketStartTimeString").val('');
        $("#inBucketEndTimeString").val('')
        this.props.actions.setSearchParams("");
        this.props.actions.drugInBucketList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <input id="searchFields" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                <input id="batch" className="batch" placeholder="批号" type="text"/>
                <input id="inBucketManName" className="batch" placeholder="装斗人" type="text"/>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="inBucketStartTimeString" id="inBucketStartTimeString" placeholder="装斗日期" readOnly className="form-control datepicker" type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="inBucketEndTimeString" id="inBucketEndTimeString" className="form-control datepicker" readOnly type="text"/>
                    </div>
                </div>
                <a href="javascript:void(0);" className="green-btn" onClick={this.handleSearch.bind(this)}>查询</a>
                <a href="javascript:void(0);" className="gray-btn" onClick={this.onResetSearchParam.bind(this)}>重置</a>
            </div>
        )
    }
}

DrugInBucketSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugInBucketSearchForm;