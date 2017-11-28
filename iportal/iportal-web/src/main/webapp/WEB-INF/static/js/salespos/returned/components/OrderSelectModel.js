import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../common/imallpagination/components/IMallPaginationBar";
import {
    RETURNED_PURCHASE_ORDER_INDEX_UP,
    RETURNED_PURCHASE_ORDER_INDEX_DOWN,
    RETURNED_PURCHASE_ORDER_NUM_SEARCH_CHANGE,
    RETURNED_PURCHASE_GOODS_SEARCH_CHANGE,
    RETURNED_PURCHASE_ORDER_TIME_CHANGE,
    RETURNED_PURCHASE_GOODS_SEARCH_PARAMS_INIT,
    RETURNED_PURCHASE_ORDER_SELECT_MODEL_SELECTED_OK
} from "../constants/ActionTypes";

/**
 * 订单选择
 */

class OrderSelectModel extends Component {

    constructor(props) {
        super(props);
        this.reportOption = this.reportOption.bind(this);
    }

    componentWillMount() {
        this.context.store.dispatch({
            type:RETURNED_PURCHASE_GOODS_SEARCH_PARAMS_INIT
        })
    }

    componentDidMount() {
        window.addEventListener('keydown', this.reportOption);

        function changeTime(store, type, formCreateDateString, toCreateDateString) {
            store.dispatch({
                type: type,
                data: {formCreateDateString: formCreateDateString, toCreateDateString: toCreateDateString}
            });
        }

        const {store} = this.context;

        $(".datepicker").on("click", function (e) {
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css: 'datetime-day',
                dateType: 'D',
                selectback: function () {
                    let startTimeString = $("#startTimeString").val().trim();
                    let endTimeString = $("#endTimeString").val().trim();
                    changeTime(store, RETURNED_PURCHASE_ORDER_TIME_CHANGE, startTimeString, endTimeString);
                }
            });
        });

         //const {store} = this.context;
         const {params} = store.getState().todos;
         this.props.actions.orderList(params, params.page, params.size);
    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.reportOption);
    }

    reportOption(event) {
        const oldjs = document.getElementById("notyLayer");
        const {store} = this.context;
        switch (event.keyCode) {
            //up
            case 38:
                event.preventDefault();
                store.dispatch({type: RETURNED_PURCHASE_ORDER_INDEX_UP});
                this.props.actions.getOrderItemList(store.getState().todos.orderId);
                break;
            //down
            case 40:
                event.preventDefault();
                store.dispatch({type: RETURNED_PURCHASE_ORDER_INDEX_DOWN});
                this.props.actions.getOrderItemList(store.getState().todos.orderId);
                break;
            //向左上一页
            case 37: {
                event.preventDefault();
                const {params} = store.getState().todos;
                this.props.actions.orderList(params, params.page - 1, params.size);
                break;
            }
            //向右下一页
            case 39: {
                event.preventDefault();
                const {params,page} = store.getState().todos;
                if(page.totalPages > params.page + 1){
                    this.props.actions.orderList(params, params.page + 1, params.size);
                }
                break;
            }
            //Enter 确定选中，关闭
            case 13: {
                event.preventDefault();
                store.dispatch({type: RETURNED_PURCHASE_ORDER_SELECT_MODEL_SELECTED_OK, isShow: false});
                break;
            }
            //F9
            case 120:
                event.preventDefault();
                this.submitSearchEvent(event);
                break;
            //esc
            case 27:
                event.preventDefault();
                if (oldjs) {
                    oldjs.parentNode.removeChild(oldjs);
                    break;
                }
                this.props.actions.orderSelectModel(false);
                break;
            default:
                break;
        }
    }

    submitSearchEvent(e) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.orderList(params, 0, params.size);
    }

    paramInit() {
        this.context.store.dispatch({
            type:RETURNED_PURCHASE_GOODS_SEARCH_PARAMS_INIT
        });
        $("#startTimeString").val("");
        $("#endTimeString").val("");
    }


    orderNumSearchFieldsChangeEvent(e) {
        const {store} = this.context;
        store.dispatch({type: RETURNED_PURCHASE_ORDER_NUM_SEARCH_CHANGE, data: e.target.value});
    }

    goodsSearchFieldsChangeEvent(e) {
        const {store} = this.context;
        store.dispatch({type: RETURNED_PURCHASE_GOODS_SEARCH_CHANGE, data: e.target.value});
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

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const {params, page, orderItems, selectOrderIndex} = store.getState().todos;
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
            isWindow: true,
            hideSizePerPage: page.totalElements <= page.size
        };

        return (
            <div className="layer">
                <div className="layer-box od-choice w1075">
                    <div className="layer-header">
                        <span>订单选择</span>
                        <a href="javascript:void(0);" className="close" onClick={(e) => actions.orderSelectModel(false)}/>
                    </div>
                    <div className="search" style={{width:"1045px"}}>
                        <span>销售时间段</span>
                        <input name="startTimeString" id="startTimeString" className="form-control datepicker" style={{"width": "80px"}} type="text" readOnly/>
                        <span>至</span>
                        <input name="endTimeString" id="endTimeString" className="form-control datepicker" style={{"width": "80px", "marginRight": "20px"}} type="text" readOnly/>
                        <span>销售订单</span>
                        <input id="orderNum" type="text" value={params.orderNum} autoFocus="autoFocus" style={{"width": "140px", "marginRight": "20px"}} onChange={(e) => this.orderNumSearchFieldsChangeEvent(e)}/>
                        <span>商品</span>
                        <input id="goodsSearchFields" type="text" value={params.goodsSearchFields} style={{"width": "190px"}} placeholder="编号|条码|名称|厂家|通用名称" onChange={(e) => this.goodsSearchFieldsChangeEvent(e)}/>
                        <a href="javascript:void(0);" className="account" style={{display: "inline-block",
                            padding: "0 25px",
                            height: "30px", position: "static", width:"auto", background: "#ddd"
                        }} onClick={(e) => this.submitSearchEvent(e)}>搜索[F9]</a>
                        &nbsp;&nbsp;
                        <a href="javascript:void(0);" className="account" style={{display: "inline-block",
                            padding: "0 25px",
                            height: "30px", position: "static", width:"auto", background: "#ddd"
                        }} onClick={(e) => this.paramInit(e)}>重置</a>
                    </div>
                    <div className="layer-body">
                        <div className="pos-table-box" style={{maxHeight: 'fit-content'}}>
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                                    <th className="th-coding">订单号</th>
                                    <th className="time">销售时间</th>
                                {/*    <th className="retail-price">商品金额</th>*/}
                                    <th className="member-price">订单金额</th>
                                    <th className="operator">开单人</th>
                                    <th className="operator">会员卡号</th>
                                    <th className="operator">会员名称</th>
                                </tr>
                                </thead>
                                <tbody>
                                {page.content.map((order, index) => {
                                    return (
                                        <tr key={index} className={index == selectOrderIndex ? "active" : ""}>
                                            <td>
                                                <div className="td-cont td-number">{index + 1}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.orderNum}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.createDateString}</div>
                                            </td>
                                            {/*<td>
                                                <div className="td-cont">{order.goodsTotalAmount}</div>
                                            </td>*/}
                                            <td>
                                                <div className="td-cont">{order.orderTotalAmount}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.openOrderMan}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.memberCardNum}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{order.memberName}</div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>

                            <IMallPaginationBar options={options} actions={this.props.actions}/>
                        </div>
                        <p>单据明细</p>
                        <div className="pos-table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                             {/*       <th className="th-coding">商品编码</th>*/}
                                    <th className="common-name">通用名称</th>
                                    <th className="th-title">商品名称</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="standard">规格</th>
                                  {/*  <th className="units">单位</th>*/}
                                    <th className="batch-number">批号</th>
                                    <th className="inventory">数量</th>
                                    <th className="retail-price">单价</th>
                                    <th className="member-price">小计金额</th>
                              {/*      <th className="retail-price">营业员</th>*/}
                                    <th className="inventory">可退数量</th>
                                </tr>
                                </thead>
                                <tbody>
                                {orderItems.map((item, index) => {
                                    return (
                                        <tr key={index}>
                                            <td>
                                                <div className="td-cont td-number">{index + 1}</div>
                                            </td>
                                           {/* <td>
                                                <div className="td-cont">{item.goodsCoding}</div>
                                            </td>*/}
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
                                         {/*   <td>
                                                <div className="td-cont">{item.unit}</div>
                                            </td>*/}
                                            <td>
                                                <div className="td-cont">{item.batch}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.quantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsUnitPrice}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont">{item.goodsTotalAmount}</div>
                                            </td>
                                            {/*<td>
                                                <div className="td-cont">{item.sellerName}</div>
                                            </td>*/}
                                            <td>
                                                <div className="td-cont">{item.allowReturnedQuantity}</div>
                                            </td>
                                        </tr>
                                    )
                                })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div className="layer-footer"></div>
                </div>
            </div>
        )
    }
}

OrderSelectModel.propTypes = {};

OrderSelectModel.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OrderSelectModel);