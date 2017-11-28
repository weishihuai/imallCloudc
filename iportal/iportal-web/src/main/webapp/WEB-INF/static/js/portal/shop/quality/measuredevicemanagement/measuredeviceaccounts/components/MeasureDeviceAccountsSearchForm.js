import React, {Component} from "react";

class MeasureDeviceAccountsSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        $("#useStateCode").jSelect();
    }

    handleSearch() {
        const {store} = this.context;
        var params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            measuringDeviceNum: $.trim($("#measuringDeviceNum").val()),
            manufacturingNum: $.trim($("#manufacturingNum").val()),
            useStateCode: $("#useStateCode").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.measuringDeviceAccountsList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#measuringDeviceNum").val('');
        $("#manufacturingNum").val('');
        $("#useStateCode").jSelectReset();

        this.props.actions.setSearchParams("");
        this.props.actions.measuringDeviceAccountsList({}, 0, 10);
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
                <div className="status">
                    <select id="useStateCode" className="select allSelect3">
                        <option value="">使用状态</option>
                        <option value="ENABLED">启用</option>
                        <option value="DISABLED">禁用</option>
                        <option value="SEAL_UP">封存</option>
                        <option value="SCRAP">报废</option>
                    </select>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

MeasureDeviceAccountsSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default MeasureDeviceAccountsSearchForm;