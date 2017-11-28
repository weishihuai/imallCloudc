import React, {Component} from "react";

/*其他入库 搜索表单*/
class OtherOutStockSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        $("#typeCode").jSelect();

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
            typeCode: $.trim($("#typeCode").val()),
            searchFields: $.trim($("#searchFields").val()),
            inStockCode: $.trim($("#inStockCode").val()),
            inStockTimeStartString: $("#inStockTimeStartString").val(),
            inStockTimeEndString: $("#inStockTimeEndString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.otherInStockList(newParam, 0, 10);
    };


    /*重置搜索参数*/
    onResetSearchParam() {
        $("#typeCode").jSelectReset('');
        $("#searchFields").val('');
        $("#inStockCode").val('');
        $("#inStockTimeStartString").val('');
        $("#inStockTimeEndString").val('');

        this.props.actions.setSearchParams("");
        this.props.actions.otherInStockList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="status">
                    <select id="typeCode" className="select allSelect1">
                        <option value="">入库类型</option>
                        <option value="RECEIVE">获赠</option>
                        <option value="OVERFLOW">报溢</option>
                        <option value="TAKE_BACK">领用退回</option>
                        <option value="OTHER">其他</option>
                    </select>
                </div>
                <div className="search">
                    <input id="searchFields" className="batch" placeholder="入库人" type="text"/>
                </div>
                <div className="search">
                    <input id="inStockCode" className="batch" placeholder="入库单号" type="text"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="inStockTimeStartString" id="inStockTimeStartString" placeholder="入库时间" readOnly className="form-control datepicker" type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="inStockTimeEndString" id="inStockTimeEndString" className="form-control datepicker" readOnly type="text"/>
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

OtherOutStockSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default OtherOutStockSearchForm;