import React, {Component, PropTypes} from "react";

class SearchForm extends Component {

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        const acceptanceOrderNum = $('#acceptanceOrderNum').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {acceptanceOrderNum}), type);
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        $('#acceptanceOrderNum').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {acceptanceOrderNum: ''}), type);
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
            case 'acceptanceOrderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {acceptanceOrderNum: ''}), type);
                $('#acceptanceOrderNum').val("");
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
                            <option value="SUMMARY">验收记录汇总</option>
                            <option value="DETAIL">验收记录明细</option>
                        </select>
                    </div>
                    <div className="search">
                        <input name="acceptanceOrderNum" id="acceptanceOrderNum" placeholder="验收单编号" type="text"/>
                    </div>
                    <div className="search-reset">
                        <input className="sr-search" onClick={() => this.search()} type="button" value="查询"/>
                        <input className="sr-reset" onClick={() => this.resetParams()} type="button" value="重置"/>
                    </div>
                </div>
                {/*<div className="lt-bot">
                    {params.acceptanceOrderNum && <a onClick={() => this.searchLabelClickFunc('acceptanceOrderNum')} href="javascript:;">{params.acceptanceOrderNum}</a>}
                </div>*/}
            </div>
        )
    }
}

SearchForm.contextTypes = {
    store: React.PropTypes.object
}

export default SearchForm;