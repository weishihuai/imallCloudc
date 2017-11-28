import React, {Component} from "react";


class MeasureRecordSearchForm extends Component {

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
            measuringDeviceNum: $.trim($("#measuringDeviceNum").val()),
            manufacturingNum: $.trim($("#manufacturingNum").val()),
            measureDateStartString: $("#measureDateStartString").val(),
            measureDateEndString: $("#measureDateEndString").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.measureRecordList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#measuringDeviceNum").val('');
        $("#manufacturingNum").val('');
        $("#measureDateStartString").val('');
        $("#measureDateEndString").val('');

        this.props.actions.setSearchParams("");
        this.props.actions.measureRecordList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input id="measuringDeviceNum" name="measuringDeviceNum" placeholder="计量器具编号" type="text"/>
                </div>
                <div className="search">
                    <input id="manufacturingNum" name="manufacturingNum" placeholder="出厂编号" type="text"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input name="measureDateStartString" id="measureDateStartString" placeholder="检测日期" className="form-control datepicker" readOnly type="text"/>
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input name="measureDateEndString" id="measureDateEndString" readOnly className="form-control datepicker" type="text"/>
                    </div>
                </div>
                <a href="javascript:void(0);" className="green-btn" onClick={this.handleSearch.bind(this)}>查询</a>
                <a href="javascript:void(0);" className="gray-btn" onClick={this.onResetSearchParam.bind(this)}>重置</a>
            </div>
        )
    }
}

MeasureRecordSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default MeasureRecordSearchForm;