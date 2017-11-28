import React, {Component, PropTypes} from "react";

class PurchaseAcceptanceListSearchForm extends Component {

    componentDidMount(){
        const _this = this;
        $("#acceptanceOrderNum").bind("keydown", function (e) {
             if(e.keyCode == 13){
                 _this.search();
             }
        });
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        const acceptanceOrderNum = $('#acceptanceOrderNum').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {acceptanceOrderNum}));
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        $('#acceptanceOrderNum').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {acceptanceOrderNum: ''}));
    }

    searchLabelClickFunc(name){
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        switch(name){
            case 'acceptanceOrderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {acceptanceOrderNum: ''}));
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
                    <div className="search">
                        <input name="acceptanceOrderNum" id="acceptanceOrderNum" placeholder="验收单编号" type="text"/>
                        <a onClick={() => this.search()} href="javascript:void(0);"></a>
                    </div>
                </div>
                {/*<div className="lt-bot">
                    {params.acceptanceOrderNum && <a onClick={() => this.searchLabelClickFunc('acceptanceOrderNum')} href="javascript:;">{params.acceptanceOrderNum}</a>}
                </div>*/}
            </div>
        )
    }
}

PurchaseAcceptanceListSearchForm.contextTypes = {
    store: React.PropTypes.object
}

export default PurchaseAcceptanceListSearchForm;