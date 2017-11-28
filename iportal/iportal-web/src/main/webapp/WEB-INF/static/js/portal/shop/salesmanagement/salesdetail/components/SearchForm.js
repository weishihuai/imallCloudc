import React, {Component, PropTypes} from "react";

class SearchForm extends Component {

    componentWillMount(){
    }

    search() {
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        const orderSourceCode = $("select[name='orderSourceCode']").val();
        const orderStateCode = $("select[name='orderStateCode']").val();
        const orderNum = $('#orderNum').val().trim();
        const keyword = $('#keyword').val().trim();
        actions.setParamsAndLoad(Object.assign({}, params, {orderSourceCode, orderStateCode, orderNum, keyword}), type);
    }

    resetParams() {
        const {actions} = this.props;
        const {store} = this.context;
        const {params, type} = store.getState().todos;
        $(".allSelect").jSelectReset();
        $('#orderNum').val("");
        $('#keyword').val("");
        actions.setParamsAndLoad(Object.assign({}, params, {
            orderSourceCode: '',
            orderStateCode: '',
            orderNum: '',
            keyword: ''
        }), type);
    }

    getSelectedHtmlByName(name){
        return $("select[name='"+ name +"']" ).find("option:selected").html();
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
            case 'orderSourceCode':
                actions.setParamsAndLoad(Object.assign({}, params, {orderSourceCode: ''}), type);
                $("select[name='orderSourceCode']").jSelectReset();
                break;
            case 'orderStateCode':
                actions.setParamsAndLoad(Object.assign({}, params, {orderStateCode: ''}), type);
                $("select[name='orderStateCode']").jSelectReset();
                break;
            case 'orderNum':
                actions.setParamsAndLoad(Object.assign({}, params, {orderNum: ''}), type);
                $('#orderNum').val("");
                break;
            case 'keyword':
                actions.setParamsAndLoad(Object.assign({}, params, {keyword: ''}), type);
                $('#keyword').val("");
                break;
            default:
                break;
        }
    }

    render() {
        const {store} = this.context;
        const {params, statOrder} = store.getState().todos;
        return (
            <div>
                <div className="lt-cont lt-cont-m">
                    <div className="lt-item lt-item-first">
                        <p>应收金额</p>
                        <span>{statOrder.amountReceivable}</span>
                    </div>
                    <div className="lt-item">
                        <p>实收金额</p>
                        <span>{statOrder.amountReceived}</span>
                    </div>
                    <div className="lt-item">
                        <p>毛利</p>
                        <span>{statOrder.profit}</span>
                    </div>
                    <div className="lt-item lt-item-last">
                        <p>数量总计</p>
                        <span>{statOrder.goodsTotalNum}</span>
                    </div>
                </div>
                <div className="lt-cont">
                    <div className="status">
                        <select name="recordType" id="recordType" className="select">
                            <option value="SUMMARY">销售汇总</option>
                            <option value="DETAIL">销售明细</option>
                        </select>
                    </div>
                    <div className="status">
                        <select name="orderSourceCode" id="orderSourceCode" className="select allSelect">
                            <option value="">全部订单</option>
                            <option value="SALES_POS">销售POS端</option>
                            <option value="WEIXIN">微信</option>
                        </select>
                    </div>
                    <div className="status">
                        <select id="orderStateCode" name="orderStateCode" className="select allSelect">
                            <option value="">全部</option>
                            <option value="WAIT_SEND">待发货</option>
                            <option value="ALREADY_SENDED">已发货</option>
                            <option value="FINISH">已完成</option>
                            <option value="CLOSE">已关闭</option>
                        </select>
                    </div>
                    <div className="search">
                        <input name="orderNum" id="orderNum" placeholder="订单编号" type="text"/>
                    </div>
                    <div className="search">
                        <input name="keyword" id="keyword" placeholder="拼音码|名称|编码" type="text"/>
                    </div>
                    <div className="search-reset">
                        <input className="sr-search" onClick={() => this.search()} type="button" value="查询"/>
                        <input className="sr-reset" onClick={() => this.resetParams()} type="button" value="重置"/>
                    </div>
                </div>
                {/*<div className="lt-bot">
                    {params.orderSourceCode && <a onClick={() => this.searchLabelClickFunc('orderSourceCode')} href="javascript:;">{this.getSelectedHtmlByName('orderSourceCode')}</a>}
                    {params.orderStateCode && <a onClick={() => this.searchLabelClickFunc('orderStateCode')} href="javascript:;">{this.getSelectedHtmlByName('orderStateCode')}</a>}
                    {params.orderNum && <a onClick={() => this.searchLabelClickFunc('orderNum')} href="javascript:;">{params.orderNum}</a>}
                    {params.keyword && <a onClick={() => this.searchLabelClickFunc('keyword')} href="javascript:;">{params.keyword}</a>}
                </div>*/}
            </div>
        )
    }
}

SearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default SearchForm;