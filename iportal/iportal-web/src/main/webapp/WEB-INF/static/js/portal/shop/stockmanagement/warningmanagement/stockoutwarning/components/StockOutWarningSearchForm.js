import React, {Component} from "react";

/*缺货预警搜索表单*/
class StockOutWarningSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {

    }

    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            searchFields: $.trim($("#searchFields").val())
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.stockOutWarningList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#searchFields").val('');
        this.props.actions.setSearchParams("");
        this.props.actions.stockOutWarningList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                     <input id="searchFields" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                     <a href="javascript:void(0);" onClick={this.handleSearch.bind(this)}/>
                </div>
            </div>
        )
    }
}

StockOutWarningSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default StockOutWarningSearchForm;