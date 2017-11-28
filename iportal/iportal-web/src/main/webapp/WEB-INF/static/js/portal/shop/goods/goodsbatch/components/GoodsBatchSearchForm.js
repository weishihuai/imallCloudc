import React, {Component, PropTypes} from 'react';

/*商品批次搜索表单*/
class GoodsBatchSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    handleSearch() {
        const {store} = this.context;
        let params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            batch:$.trim($("#batch").val()),
            searchFields: $.trim($("#searchFields").val())
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.goodsBatchList(newParam,0,10);
    };

    /*重置搜索参数*/
    onResetSearchParam() {
        $("#batch").val('');
        $("#searchFields").val('');

        this.props.actions.setSearchParams("");
        this.props.actions.goodsBatchList({},0,10);
    }

    render() {
        return (
            <div className="lt-cont">
                <input type="text" className="batch" placeholder="批号" id="batch"/>
                <div className="search">
                    <input type="text" placeholder="拼音码|名称|编码" id="searchFields"/>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

GoodsBatchSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default GoodsBatchSearchForm;