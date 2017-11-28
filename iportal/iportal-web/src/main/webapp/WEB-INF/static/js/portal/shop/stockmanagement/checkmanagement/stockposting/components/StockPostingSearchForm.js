import React, {Component} from "react";

/*盘点过账搜索表单*/
class StockPostingSearchForm extends Component {

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
            checkOrderNum: $.trim($("#checkOrderNum").val()),
            searchFields: $.trim($("#searchFields").val())
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.stockPostingList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#checkOrderNum").val('');
        $("#searchFields").val('');
        this.props.actions.setSearchParams("");
        this.props.actions.stockPostingList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input id="searchFields" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                </div>
                <div className="search">
                    <input id="checkOrderNum" className="batch" placeholder="盘点单号" type="text"/>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

StockPostingSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default StockPostingSearchForm;