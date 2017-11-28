import React, {Component, PropTypes} from "react";

class PurchaseReturnListSearchForm extends Component {
    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        const returnedPurchaseType = $("select[name='returnedPurchaseType']").val();
        const isPayed = $("select[name='isPayed']").val();
        const returnedPurchaseOrderNum = $('#returnedPurchaseOrderNum').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {returnedPurchaseType, isPayed, returnedPurchaseOrderNum}));
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        $(".select").jSelectReset();
        $('#returnedPurchaseOrderNum').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {
            returnedPurchaseType: '',
            isPayed: '',
            returnedPurchaseOrderNum: ''
        }));
    }

    getSelectedHtmlByName(name){
        return $("select[name='"+ name +"']" ).find("option:selected").html();
    }

    componentDidMount(){
        $(".select").jSelect();
    }

    searchLabelClickFunc(name){
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        switch(name){
            case 'returnedPurchaseType':
                actions.setParamsAndLoad(Object.assign({}, params, {returnedPurchaseType: ''}));
                $("select[name='returnedPurchaseType']").jSelectReset();
                break;
            case 'isPayed':
                actions.setParamsAndLoad(Object.assign({}, params, {isPayed: ''}));
                $("select[name='isPayed']").jSelectReset();
                break;
            case 'returnedPurchaseOrderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {returnedPurchaseOrderNum: ''}));
                $('#returnedPurchaseOrderNum').val("");
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
                        <select name="returnedPurchaseType" id="returnedPurchaseType" className="select allSelect1">
                            <option value="">全部订单</option>
                            <option value="NORMAL_RETURNED">正常退货</option>
                            <option value="NOT_QUALIFIED_RETURNED">不合格品退货</option>
                        </select>
                    </div>
                    <div className="status">
                        <select id="isPayed" name="isPayed" className="select allSelect3">
                            <option value="">全部</option>
                            <option value="N">未结算</option>
                            <option value="Y">已结算</option>
                        </select>
                    </div>
                    <div className="search">
                        <input name="returnedPurchaseOrderNum" id="returnedPurchaseOrderNum" placeholder="退货单编号" type="text"/>
                    </div>
                    <div className="search-reset">
                        <input className="sr-search" onClick={() => this.search()} type="button" value="查询"/>
                        <input className="sr-reset" onClick={() => this.resetParams()} type="button" value="重置"/>
                    </div>
                </div>
                {/*<div className="lt-bot">
                    {params.returnedPurchaseType && <a onClick={() => this.searchLabelClickFunc('returnedPurchaseType')} href="javascript:;">{this.getSelectedHtmlByName('returnedPurchaseType')}</a>}
                    {params.isPayed && <a onClick={() => this.searchLabelClickFunc('isPayed')} href="javascript:;">{this.getSelectedHtmlByName('isPayed')}</a>}
                    {params.returnedPurchaseOrderNum && <a onClick={() => this.searchLabelClickFunc('returnedPurchaseOrderNum')} href="javascript:;">{params.returnedPurchaseOrderNum}</a>}
                </div>*/}
            </div>
        )
    }
}

PurchaseReturnListSearchForm.contextTypes = {
    store: React.PropTypes.object
}

export default PurchaseReturnListSearchForm;