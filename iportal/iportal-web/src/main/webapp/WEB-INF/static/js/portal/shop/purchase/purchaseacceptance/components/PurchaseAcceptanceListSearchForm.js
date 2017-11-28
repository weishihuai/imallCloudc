import React, {Component, PropTypes} from "react";

class PurchaseAcceptanceListSearchForm extends Component {

    componentDidMount(){
        const _this = this;
        $("#receiveOrderNum").bind("keydown", function (e) {
            if(e.keyCode == 13){
                _this.search();
            }
        });
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        const receiveOrderNum = $('#receiveOrderNum').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {receiveOrderNum}));
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        $('#receiveOrderNum').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {receiveOrderNum: ''}));
    }

    searchLabelClickFunc(name){
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        switch(name){
            case 'receiveOrderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {receiveOrderNum: ''}));
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
                    <div className="search">
                        <input name="receiveOrderNum" id="receiveOrderNum" placeholder="收货单编号" type="text"/>
                        <a onClick={() => this.search()} href="javascript:void(0);"></a>
                    </div>
                </div>
                {/*<div className="lt-bot">
                    {params.receiveOrderNum && <a onClick={() => this.searchLabelClickFunc('receiveOrderNum')} href="javascript:;">{params.receiveOrderNum}</a>}
                </div>*/}
            </div>
        )
    }
}

PurchaseAcceptanceListSearchForm.contextTypes = {
    store: React.PropTypes.object
}

export default PurchaseAcceptanceListSearchForm;