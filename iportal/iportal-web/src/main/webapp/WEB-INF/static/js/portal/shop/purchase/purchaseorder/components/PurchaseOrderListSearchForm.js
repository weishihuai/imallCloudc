import React, {Component, PropTypes} from "react";

class PurchaseOrderListSearchForm extends Component {
    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        const purchaseOrderType = $("select[name='purchaseOrderType']").val();
        const purchaseOrderState = $("select[name='purchaseOrderState']").val();
        const purchaseOrderNum = $('#purchaseOrderNum').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {purchaseOrderType, purchaseOrderState, purchaseOrderNum}));
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const params = store.getState().todos.params;
        $(".select").jSelectReset();
        $('#purchaseOrderNum').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {
            purchaseOrderType: '',
            purchaseOrderState: '',
            purchaseOrderNum: ''
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
            case 'purchaseOrderType':
                actions.setParamsAndLoad(Object.assign({}, params, {purchaseOrderType: ''}));
                $("select[name='purchaseOrderType']").jSelectReset();
                break;
            case 'purchaseOrderState':
                actions.setParamsAndLoad(Object.assign({}, params, {purchaseOrderState: ''}));
                $("select[name='purchaseOrderState']").jSelectReset();
                break;
            case 'purchaseOrderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {purchaseOrderNum: ''}));
                $('#purchaseOrderNum').val("");
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
                        <select name="purchaseOrderType" id="purchaseOrderType" className="select allSelect1">
                            <option value="">全部订单</option>
                            <option value="OFF_LINE_ORDER">线下订单</option>
                            <option value="ON_LINE_ORDER">线上订单</option>
                        </select>
                    </div>
                    <div className="status">
                        <select id="purchaseOrderState" name="purchaseOrderState" className="select allSelect3">
                            <option value="">全部状态</option>
                            <option value="WAIT_RECEIVE">待收货</option>
                            <option value="WAIT_ACCEPTANCE">待验收</option>
                            <option value="CLEAR">已清</option>
                            <option value="CANCEL">已取消</option>
                        </select>
                    </div>
                    <div className="search">
                        <input name="purchaseOrderNum" id="purchaseOrderNum" placeholder="采购订单编号" type="text"/>
                    </div>
                    <div className="search-reset">
                        <input className="sr-search" onClick={() => this.search()} type="button" value="查询"/>
                        <input className="sr-reset" onClick={() => this.resetParams()} type="button" value="重置"/>
                    </div>
                </div>
               {/* <div className="lt-bot">
                    {params.purchaseOrderType && <a onClick={() => this.searchLabelClickFunc('purchaseOrderType')} href="javascript:;">{this.getSelectedHtmlByName('purchaseOrderType')}</a>}
                    {params.purchaseOrderState && <a onClick={() => this.searchLabelClickFunc('purchaseOrderState')} href="javascript:;">{this.getSelectedHtmlByName('purchaseOrderState')}</a>}
                    {params.purchaseOrderNum && <a onClick={() => this.searchLabelClickFunc('purchaseOrderNum')} href="javascript:;">{params.purchaseOrderNum}</a>}
                </div>*/}
            </div>
        )
    }
}

PurchaseOrderListSearchForm.contextTypes = {
    store: React.PropTypes.object
}

export default PurchaseOrderListSearchForm;