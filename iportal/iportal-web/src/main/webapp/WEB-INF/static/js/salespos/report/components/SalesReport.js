import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../common/imallpagination/components/IMallPaginationBar";
import {
    SALES_REPORT_IS_RETURNED_PURCHASE_ORDER,
    SALES_REPORT_ORDER_INDEX_UP,
    SALES_REPORT_ORDER_INDEX_DOWN,
    SALES_REPORT_ORDER_NUM_SEARCH_CHANGE,
    SALES_REPORT_GOODS_SEARCH_CHANGE,
    SALES_REPORT_MEMBER_SEARCH_CHANGE,
    SALES_REPORT_ORDER_TYPE_CHANGE,
    SALES_REPORT_ORDER_TIME_CHANGE,
    SALES_REPORT_GOODS_SEARCH_PARAMS_SET
} from "../constants/ActionTypes";

/**
 * 销售pos - 销售报表
 */

class SalesReport extends Component {

    constructor(props) {
        super(props);
        this.reportOption = this.reportOption.bind(this);
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.orderList(params, params.page, params.size);
    }

    componentDidMount() {
        window.addEventListener('keydown', this.reportOption);

        function changeTime(store, type, formCreateDateString, toCreateDateString){
            store.dispatch({type: type, data: { formCreateDateString:formCreateDateString, toCreateDateString: toCreateDateString }});
        }

        const {store} = this.context;

        $(".datepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){
                    let startTimeString = $("#startTimeString").val().trim();
                    let endTimeString = $("#endTimeString").val().trim();
                    changeTime(store, SALES_REPORT_ORDER_TIME_CHANGE, startTimeString, endTimeString);
                }
            });
        });
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.reportOption);
    }

    reportOption(event) {
        const {store} = this.context;
        const {isSellReturnedPurchaseOrder} = store.getState().todos;
        let orderType = "SALES_ORDER";
        if(isSellReturnedPurchaseOrder){
            orderType = "RETURN_ORDER";
        }
        switch(event.keyCode){
            //up
            case 38:
                event.preventDefault();
                store.dispatch({type: SALES_REPORT_ORDER_INDEX_UP});
                this.props.actions.getOrderItemList(store.getState().todos.orderId,orderType);
                break;
            //down
            case 40:
                event.preventDefault();
                store.dispatch({type: SALES_REPORT_ORDER_INDEX_DOWN});
                this.props.actions.getOrderItemList(store.getState().todos.orderId,orderType);
                break;
            //向左上一页
            case 37:{
                event.preventDefault();
                const {params} = store.getState().todos;
                this.props.actions.orderList(params, params.page-1, params.size);
                break;
            }
            //向右下一页
            case 39:{
                event.preventDefault();
                const {params,page} = store.getState().todos;
                if(page.totalPages > params.page + 1){
                    this.props.actions.orderList(params, params.page + 1, params.size);
                }
                break;
            }
            //F1 销售
            case 112:
                event.preventDefault();
                store.dispatch({type: SALES_REPORT_ORDER_TYPE_CHANGE, data: 'SALES_ORDER'});
                break;
            //F2 退货
            case 113:
                event.preventDefault();
                store.dispatch({type: SALES_REPORT_ORDER_TYPE_CHANGE, data: 'RETURN_ORDER'});
                break;
            //F10
            case 121:
                event.preventDefault();
                this.submitSearchEvent(event);
                break;
            default:
                break;
        }
    }


    submitSearchEvent(e){
        const {store} = this.context;
        const {params} = store.getState().todos;
        let state = params.orderType === "RETURN_ORDER";
        this.context.store.dispatch({
            type:SALES_REPORT_IS_RETURNED_PURCHASE_ORDER,
            data:state
        });
        this.props.actions.orderList(params, 0, params.size);
    }

    reset(){

        const {store} = this.context;
        const {params} = store.getState().todos;

        $('#startTimeString').val('');
        $('#endTimeString').val('');
        store.dispatch({
            type:SALES_REPORT_GOODS_SEARCH_PARAMS_SET,
            data:Object.assign({},params,{
                orderNum:"",
                goodsSearchFields: "",
                memberSearchFields: "",
                formCreateDateString: "",
                toCreateDateString: ""
            })
        })
    }

    orderNumSearchFieldsChangeEvent(e){
        const {store} = this.context;
        store.dispatch({type: SALES_REPORT_ORDER_NUM_SEARCH_CHANGE, data: e.target.value});
    }

    goodsSearchFieldsChangeEvent(e){
        const {store} = this.context;
        store.dispatch({type: SALES_REPORT_GOODS_SEARCH_CHANGE, data: e.target.value});
    }

    memberSearchFieldsChangeEvent(e){
        const {store} = this.context;
        store.dispatch({type: SALES_REPORT_MEMBER_SEARCH_CHANGE, data: e.target.value});
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.orderList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.orderList(params, page - 1, sizePerPage);
    }

    changeOrderType(data){
        if(data=='SALES_ORDER'){
            $("#changeOrderType").text("订单编号");
        }else{
            $("#changeOrderType").text("退货单号");
        }
        this.context.store.dispatch({type: SALES_REPORT_ORDER_TYPE_CHANGE, data: data});
    }

    render() {
        const {store} = this.context;
        const {params, page, orderItems, selectOrderIndex, sumOrderTotalAmount,isSellReturnedPurchaseOrder} = store.getState().todos;
        const {orderType} = store.getState().todos.params;
        const number = page.number + 1;
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: page.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            paginationShowsTotal: null,
            hideSizePerPage: page.totalElements <= page.size
        };

        return (
            <div className="pos-box statement">
                <div className="pos-lt" style={{zIndex: '51', background: '#FFF'}}>
                    <div className="lt-top">
                        <div className="item">
                            <span>订单类型</span>
                            <label style={{"height" : "20px"}}><input type="radio" name="orderType" value="SALES_ORDER" onClick={()=>this.changeOrderType("SALES_ORDER")} checked={orderType === 'SALES_ORDER'}/>销售[F1]</label>
                            <label style={{"height" : "20px"}}><input type="radio" name="orderType" value="RETURN_ORDER" onClick={()=>this.changeOrderType("RETURN_ORDER")} checked={orderType === 'RETURN_ORDER'}/>退货[F2]</label>
                        </div>
                        <div className="item">
                            <span id="changeOrderType">订单编号</span>
                            <input id="orderNum" type="text" value={params.orderNum} autoFocus="autoFocus" onChange={(e) => this.orderNumSearchFieldsChangeEvent(e)}/>
                        </div>
                        <div className="item">
                            <span>商品</span>
                            <input id="goodsSearchFields" placeholder="编码/条形码/名称" type="text" value={params.goodsSearchFields} onChange={(e) => this.goodsSearchFieldsChangeEvent(e)}/>
                        </div>
                        <div className="item">
                            <span>会员</span>
                            <input id="memberSearchFields" type="text" placeholder="手机号/卡号/姓名" value={params.memberSearchFields} onChange={(e) => this.memberSearchFieldsChangeEvent(e)}/>
                        </div>
                        <div className="item frame">
                            <span>订单时段</span>
                                <input name="startTimeString" id="startTimeString" className="form-control datepicker" type="text" readOnly />
                                <i>至</i>
                                <input name="endTimeString" id="endTimeString" className="form-control datepicker" type="text" readOnly />
                        </div>
                        <a href="javascript:void(0);" className="account" onClick={(e) => this.submitSearchEvent(e)}>搜索[F10]</a>
                        <a href="javascript:void(0);" className="account" onClick={(e) => this.reset()}>重置</a>
                    </div>
                </div>
                <div className="main">
                    <div className="mt">
                        {!isSellReturnedPurchaseOrder&&<div className="amount">
                            <em>实收营业额合计：</em>
                            <i>{sumOrderTotalAmount}</i>
                        </div>}
                    </div>
                    <div className="mc" style={{paddingBottom: '81px'}}>
                        <div className="pos-table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                                    <th className="th-coding" style={{"width": "180px"}}>{isSellReturnedPurchaseOrder?"退货单号":"订单号"}</th>
                                    <th className="time">时间</th>
                                    <th className="retail-price" style={{"width": "180px"}}>{isSellReturnedPurchaseOrder?"退货金额":"商品金额"}</th>
                                    <th className="retail-price" style={{"width": "180px"}}>{isSellReturnedPurchaseOrder?"金额总计":"实收金额"}</th>
                                    <th className="statutory" style={{"width": "127px"}}>{isSellReturnedPurchaseOrder?"营业员":"开单人"}</th>
                                    <th className="card-num" style={{"width": "180px"}}>会员卡号</th>
                                    <th className="statutory" style={{"width": "127px"}}>会员名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                {page.content.map((order, index) =>{
                                    return (
                                        <tr key={index} className={index == selectOrderIndex ? "active" : ""}>
                                            <td>
                                                <div className="td-cont td-number">{index + 1}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?order.sellReturnedPurchaseOrderNum:order.orderNum}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.createDateString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?order.realReturnCashAmount:order.goodsTotalAmount}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?order.refundTotalAmount:order.realGeveCashAmount}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?order.cashierNm:order.openOrderMan}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.memberCardNum}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?order.name:order.memberName}</div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>
                        </div>
                        <p>单据明细</p>
                        <div className="pos-table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                                    <th className="th-coding">商品编码</th>
                                    <th className="common-name">通用名称</th>
                                    <th className="th-title">商品名称</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="standard">规格</th>
                                    <th className="units">单位</th>
                                    <th className="batch-number">批号</th>
                                    <th className="inventory">数量</th>
                                    <th className="retail-price">单价</th>
                                    <th className="statutory">小计</th>
                                    <th className="retail-price">营业员</th>
                                </tr>
                                </thead>
                                <tbody>
                                {orderItems.map((item, index) =>{
                                    return (
                                        <tr key={index}>
                                            <td>
                                                <div className="td-cont td-number">{index + 1}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?item.goodsCode:item.goodsCoding}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.commonNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.produceManufacturer}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.spec}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.unit}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.batch}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?item.returnedPurchaseQuantity:item.quantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?item.unitPrice:item.goodsUnitPrice}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{isSellReturnedPurchaseOrder?item.totalAmount:item.goodsTotalAmount}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.sellerName}</div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={this.props.actions}/>
            </div>
        )
    }
}

SalesReport.propTypes = {};

SalesReport.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesReport);