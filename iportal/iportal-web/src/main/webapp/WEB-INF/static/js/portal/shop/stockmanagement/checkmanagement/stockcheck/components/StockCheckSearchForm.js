import React, {Component} from "react";

/*库存盘点搜索表单*/
class StockCheckSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {

        $("#checkedStateCode").jSelect();

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
            checkOrderNum: $.trim($("#checkOrderNum").val()),
            checkedStateCode: $("#checkedStateCode").val(),
            createDateBeginString: $("#createDateBeginString").val(),
            createDateEndString: $("#createDateEndString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.stockCheckList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#checkOrderNum").val('');
        $("#checkedStateCode").jSelectReset();
        $("#createDateBeginString").val('');
        $("#createDateEndString").val('')
        this.props.actions.setSearchParams("");
        this.props.actions.stockCheckList({}, 0, 10);

    }

    render() {
        return (
            <div className="lt-cont">
                <input id="checkOrderNum" className="batch" placeholder="盘点单号" type="text"/>
                <div className="status">
                    <select id="checkedStateCode" className="select">
                        <option value="">全部状态</option>
                        <option value="CHECKED">已盘点</option>
                        <option value="UNCHECKED">未盘点</option>
                        <option value="UN_POST">未过账</option>
                        <option value="CANCEL">已取消</option>
                    </select>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="createDateBeginString" placeholder="操作时间" className="form-control datepicker" readOnly  type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="datepicker" id="createDateEndString" className="form-control datepicker" readOnly  type="text"/>
                    </div>
                </div>
                <a href="javascript:void(0);" className="green-btn" onClick={this.handleSearch.bind(this)}>查询</a>
                <a href="javascript:void(0);" className="gray-btn" onClick={this.onResetSearchParam.bind(this)}>重置</a>
            </div>
        )
    }
}

StockCheckSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default StockCheckSearchForm;