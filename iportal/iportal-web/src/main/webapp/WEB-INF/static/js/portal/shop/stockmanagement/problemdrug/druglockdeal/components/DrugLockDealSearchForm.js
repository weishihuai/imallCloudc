import React, {Component} from "react";

class DrugLockDealSearchForm extends Component {

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
            keyword: $.trim($("#keyword").val()),
            batch: $.trim($("#batch").val()),
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.drugLockDealList(newParam, 0, 10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#keyword").val('');
        $("#batch").val('');
        this.props.actions.setSearchParams("");
        this.props.actions.drugLockDealList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input id="keyword" className="batch" placeholder="拼音码|名称|编码" type="text"/>
                </div>
                <div className="search">
                    <input id="batch" className="batch" placeholder="批号" type="text"/>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

DrugLockDealSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default DrugLockDealSearchForm;