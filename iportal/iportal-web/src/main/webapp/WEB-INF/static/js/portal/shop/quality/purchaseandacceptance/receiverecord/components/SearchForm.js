import React, {Component, PropTypes} from "react";

class SearchForm extends Component {

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        const receiveOrderNum = $('#receiveOrderNum').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {receiveOrderNum}), type);
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        $('#receiveOrderNum').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {receiveOrderNum: ''}), type);
    }

    componentDidMount(){
        $(".select").jSelect();
        const _this = this;
        $("#recordType_select ul li").click(function () {
            let selectType = $("#recordType").val();
            const {type, params} = _this.context.store.getState().todos;
             if (type != selectType){
                 _this.props.actions.changeType(selectType, params);
             }
        });
    }

    searchLabelClickFunc(name){
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        switch(name){
            case 'receiveOrderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {receiveOrderNum: ''}), type);
                $('#receiveOrderNum').val("");
                break;
            default:
                break;
        }
    }

    render() {
        const {store} = this.context;
        const params = store.getState().todos.params;
        return (
            <div>
                <div className="lt-cont">
                    <div className="status">
                        <select name="recordType" id="recordType" className="select allSelect1">
                            <option value="SUMMARY">收货记录汇总</option>
                            <option value="DETAIL">收货记录明细</option>
                        </select>
                    </div>
                    <div className="search">
                        <input name="receiveOrderNum" id="receiveOrderNum" placeholder="收货单编号" type="text"/>
                    </div>
                    <div className="search-reset">
                        <input className="sr-search" onClick={() => this.search()} type="button" value="查询"/>
                        <input className="sr-reset" onClick={() => this.resetParams()} type="button" value="重置"/>
                    </div>
                </div>
                {/*<div className="lt-bot">
                    {params.receiveOrderNum && <a onClick={() => this.searchLabelClickFunc('receiveOrderNum')} href="javascript:;">{params.receiveOrderNum}</a>}
                </div>*/}
            </div>
        )
    }
}

SearchForm.contextTypes = {
    store: React.PropTypes.object
}

export default SearchForm;