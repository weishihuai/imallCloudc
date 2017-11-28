import React, {Component} from "react";
import {PURCHASE_SALE_STOCK_SEARCH_PARAM_CHANGE} from "../constants/ActionTypes";

/*进销存台帐 搜索*/
class PurchaseSaleStockSearchForm extends Component {

    componentDidMount() {
        $(".datetimepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
    }

    handleSearch() {
        const {store} = this.context;
        const newParam = {
            page: 0,
            size: 10,
            searchFields: this.refs.searchFields.value,
            fromDateString: this.refs.fromDateString.value,
            toDateString: this.refs.toDateString.value
        };
        store.dispatch({
            type: PURCHASE_SALE_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.purchaseSaleStockList(0, 10, newParam);
    };


    /*重置搜索参数*/
    onResetSearchParam() {

        this.refs.searchFields.value = "";
        this.refs.fromDateString.value = "";
        this.refs.toDateString.value = "";

        const newParam = {
            page: 0,
            size: 10,
            searchFields: "",
            batch: "",
            fromDateString: "",
            toDateString: "",
        };

        this.context.store.dispatch({
            type: PURCHASE_SALE_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.purchaseSaleStockList(0, 10, newParam);

    }

    render() {
        return (
            <div className="lt-cont">
                <div className="search">
                    <input className="batch" placeholder="拼音码|名称|编码" type="text" ref="searchFields"/>
                </div>
                <div className="sel-date">
                    <div className="form-group float-left w140">
                        <input ref="fromDateString" placeholder="统计时间" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                    </div>
                    <div className="float-left form-group-txt">至</div>
                    <div className="form-group float-left w140">
                        <input ref="toDateString" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                    </div>
                </div>
                <div className="search-reset">
                    <input className="sr-search" type="button" value="查询" onClick={() => this.handleSearch()}/>
                    <input className="sr-reset" type="button" value="重置" onClick={() => this.onResetSearchParam()}/>
                </div>
            </div>
        )
    }
}

PurchaseSaleStockSearchForm.contextTypes = {
    store: React.PropTypes.object
};

export default PurchaseSaleStockSearchForm;