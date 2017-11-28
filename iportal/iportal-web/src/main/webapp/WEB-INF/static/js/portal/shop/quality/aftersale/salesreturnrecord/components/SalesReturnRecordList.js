import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {SALES_RETURN_RECORD_SEARCH_PARAM_CHANGE} from "../constants/ActionTypes";
import SalesReturnRecordDetail from "./SalesReturnRecordDetail";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

/**
 * 销售退货记录
 */
class SalesReturnRecordList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        require('searchableSelect');
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.salesReturnRecordList(params.page, params.size, params);
        this.props.actions.userList(() => $("#cashierId").searchableSelect());
        this.props.portalOperationalAuth(['quality:afterSale:salesReturnRecord:detail']);
    }

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

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.salesReturnRecordList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.salesReturnRecordList(page - 1, sizePerPage, params);
    }

    handleSearch() {
        const {store} = this.context;
        const newParam = {
            page: 0,
            size: 10,
            orderNum: this.refs.orderNum.value,
            cashierId:  this.refs.cashierId.value,
            fromDateString: this.refs.fromDateString.value,
            toDateString: this.refs.toDateString.value,
        };
        store.dispatch({
            type: SALES_RETURN_RECORD_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.salesReturnRecordList(0, 10, newParam);
    };


    /*重置搜索参数*/
    onResetSearchParam() {

        this.refs.orderNum.value = "";
        this.refs.cashierId.value = "";
        this.refs.fromDateString.value = "";
        this.refs.toDateString.value = "";

        const newParam = {
            searchFields: "",
            cashierId: "",
            fromDateString: "",
            toDateString: "",
        };

        this.context.store.dispatch({
            type: SALES_RETURN_RECORD_SEARCH_PARAM_CHANGE,
            data: newParam
        });

    }

    render() {
        const {store} = this.context;
        const {page, detailModal} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const salesReturnRecordList = store.getState().todos.page.content || [];
        const {salesReturnRecordDetailModal} = this.props.actions;
        const sysUserList = store.getState().todos.sysUserList||[];

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
            hideSizePerPage: page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>销售退货记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">售后管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">销售退货记录</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input placeholder="退货单号" ref="orderNum" type="text"/>
                                </div>
                                <div className="status">
                                    <select id="cashierId" className="select" ref="cashierId">
                                        <option value="">收营员</option>
                                        {sysUserList.map((sysUser,index)=>{
                                            return (
                                                <option value={sysUser.id} key={index}>{sysUser.userName}</option>
                                            )
                                        })}
                                    </select>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input ref="fromDateString" placeholder="退货日期" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input ref="toDateString" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                                    </div>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" onClick={() => this.handleSearch()} value="查询"/>
                                    <input className="sr-reset" type="button" onClick={() => this.onResetSearchParam()} value="重置"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="time">创建时间</th>
                                        <th className="order-number" style={{width: "130px"}}>退货单号</th>
                                        <th className="order-number" style={{width: "130px"}}>销售订单号</th>
                                        <th className="price">退货金额</th>
                                        <th className="price">金额总计</th>
                                        <th className="name">营业员</th>
                                        <th className="name">审核人名称</th>
                                        <th className="return-reason">退货原因</th>
                                        <th className="operating" style={{width: "430px"}}>操作</th>
                                    </tr>
                                </thead>
                                {
                                    salesReturnRecordList.length <= 0 &&
                                    <tbody>
                                        <tr>
                                            <td colSpan="100" style={{textAlign:"center"}}>
                                                <div className="empty-box">
                                                    <span>暂无数据</span>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                }
                                <tbody>
                                {
                                    salesReturnRecordList.map((item, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont" title={item.createDateString}>{item.createDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.sellReturnedPurchaseOrderNum}>{item.sellReturnedPurchaseOrderNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.sellPurchaseOrderNum}>{item.sellPurchaseOrderNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.realReturnCashAmount}>{item.realReturnCashAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.refundTotalAmount}>{item.refundTotalAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.cashierNm}>{item.cashierNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.approveManNm}>{item.approveManNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.remark}>{item.remark}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                        <ImallA href="javascript:void(0);" permissionCode="quality:afterSale:salesReturnRecord:detail" className="gray-btn" text="查看" onClick={()=>salesReturnRecordDetailModal(item.id, true)} />
                                                    </div>
                                                </td>
                                            </tr>
                                        )
                                    })
                                }

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                {detailModal && <SalesReturnRecordDetail store={store} actions={actions}/>}
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

SalesReturnRecordList.propTypes = {};

SalesReturnRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesReturnRecordList);