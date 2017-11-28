import React, {Component} from "react";

/*其他出库 搜索表单*/
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
            outStockCode: $.trim($("#outStockCode").val()),
            outStockTimeStartString: $("#outStockTimeStartString").val(),
            outStockTimeEndString: $("#outStockTimeEndString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.otherOutStockList(newParam, 0, 10);
    };


    /*重置搜索参数*/
    onResetSearchParam() {
        $("#typeCode").jSelectReset('');
        $("#searchFields").val('');
        $("#outStockCode").val('');
        $("#outStockTimeStartString").val('');
        $("#outStockTimeEndString").val('');

        this.props.actions.setSearchParams("");
        this.props.actions.otherOutStockList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="status">
                    <select id="typeCode" className="select allSelect1">
                        <option value="">出库类型</option>
                        <option value="REPORTED_LOSS">报损</option>
                        <option value="INTERNAL_USE">内部领用</option>
                        <option value="CHECK_OUT">抽检出库</option>
                        <option value="OTHER">其他</option>
                    </select>
                </div>
                <div className="search">
                    <input id="searchFields" className="batch" placeholder="出库人" type="text"/>
                </div>
                <div className="search">
                    <input id="outStockCode" className="batch" placeholder="出库单号" type="text"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="outStockTimeStartString" id="outStockTimeStartString" placeholder="出库时间" readOnly className="form-control datepicker" type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="outStockTimeEndString" id="outStockTimeEndString" className="form-control datepicker" readOnly type="text"/>
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