import React, {Component} from "react";

/*商品货位搜索表单*/
class StorageSpaceSearchForm extends Component {

    constructor(props) {
        super(props);
    };

    componentWillMount() {
    }

    componentDidUpdate() {
    }

    componentDidMount() {
        $("#storageSpaceTypeSearch").jSelect();
        $("#enableStatCodeSearch").jSelect();
    }

    handleSearch() {
        const {store} = this.context;
        var params = store.getState().todos.params || {page: 0, size: 10};
        const newParam = {
            storageSpaceNm: $.trim($("#storageSpaceNm").val()),
            storageSpaceType: $("#storageSpaceTypeSearch").val(),
            enableStateCode: $("#enableStatCodeSearch").val()
        };
        this.props.actions.setSearchParams(Object.assign({}, params, newParam));
        this.props.actions.storageSpaceList(newParam, 0, 10);
    };


    /*重置搜索参数*/
    onResetSearchParam() {
        $("#storageSpaceNm").val('');
        $("#storageSpaceTypeSearch").jSelectReset();
        $("#enableStatCodeSearch").jSelectReset();

        this.props.actions.setSearchParams("");
        this.props.actions.storageSpaceList({}, 0, 10);
    }

    render() {
        return (
            <div className="lt-cont">
                <div className="status">
                    <select id="storageSpaceTypeSearch" className="select">
                        <option value="">全部类型</option>
                        <option value="GOODS_STORAGE_SPACE">商品货位</option>
                        <option value="CHINESE_HERBAL_MEDICINE">中药饮片</option>
                        <option value="MEDICAL_APPARATUS_INSTRUMENTS">医疗器械</option>
                    </select>
                </div>
                <div className="status">
                    <select id="enableStatCodeSearch" className="select">
                        <option value="">全部状态</option>
                        <option value="Y">启用</option>
                        <option value="N">未启用</option>
                    </select>
                </div>
                <div className="search">
                    <input type="text" placeholder="货位名称" id="storageSpaceNm"/>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={this.handleSearch.bind(this)}/>
                    <input className="sr-reset" type="button" value="重置" onClick={this.onResetSearchParam.bind(this)}/>
                </div>
            </div>
        )
    }
}

StorageSpaceSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default StorageSpaceSearchForm;