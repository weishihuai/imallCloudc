import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import OrderListModel from "./OrderListModel";
import {
    SHIFT_RECORD_RECORD_LIST,
    SHIFT_RECORD_SALES_TIME_CHANGE,
    SHIFT_RECORD_RECORD_SEARCH_SUBMIT,
    SHIFT_RECORD_RECORD_SEARCH_RESET,
    SHIFT_RECORD_ORDER_LIST_SHOW,
    SHIFT_RECORD_ORDER_LIST
} from "../constants/ActionTypes";

/**
 * 交班记录
 */

class ShiftRecordList extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        const {store} = this.context;
        const {recordParams} = store.getState().todos;
        this.props.actions.shiftRecordList(recordParams, recordParams.page, recordParams.size);
    }

    componentDidMount() {
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
                    changeTime(store, SHIFT_RECORD_SALES_TIME_CHANGE, startTimeString, endTimeString);
                }
            });
        });

        // $(function() {
        //     $(".table-box table").colResizable();
        //     $(".template-table table").colResizable();
        // });

        $(".select").jSelect();
    }

    componentWillUnmount() {
    }

    submitSearchEvent(e){
        const {store} = this.context;
        store.dispatch({type: SHIFT_RECORD_RECORD_SEARCH_SUBMIT, data: $("#posManName").val()});
        const {recordParams} = store.getState().todos;
        this.props.actions.shiftRecordList(Object.assign({},recordParams,{
            posManName:this.refs.posManName.value
        }), 0, recordParams.size);
    }

    resetEvent(e){
        const {store} = this.context;
        store.dispatch({type: SHIFT_RECORD_RECORD_SEARCH_RESET});
        this.refs.posManName.value = "";
        $("#startTimeString").val("");
        $("#endTimeString").val("");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {recordParams} = store.getState().todos;
        this.props.actions.shiftRecordList(Object.assign({},recordParams,{
            posManName:this.refs.posManName.value
        }), 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {recordParams} = store.getState().todos;

        this.props.actions.shiftRecordList(Object.assign({},recordParams,{
            posManName:this.refs.posManName.value
        }), page - 1, sizePerPage);
    }

    orderListModelShow(shiftRecord){
        const {store} = this.context;
        store.dispatch({type: SHIFT_RECORD_ORDER_LIST_SHOW, isShow:true, data: shiftRecord});
    }

    render() {
        const {store} = this.context;
        const { recordPage, orderListModelStat} = store.getState().todos;
        const number = recordPage.number + 1;
        const options = {
            sizePerPage: recordPage.size > 0 ? recordPage.size : 10,
            sizePerPageList: recordPage.totalElements > 0 ? [10, 20, 40] : [],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: recordPage.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: recordPage.totalPages, //分页的页码
            paginationShowsTotal: null,
            hideSizePerPage: recordPage.totalElements <= recordPage.size
        };

        return (
            <div>
                <div className="main-box">
                    <div className="main">
                        <div className="mt">
                            <div className="mt-lt">
                                <h5>交班记录</h5>
                                <a href="##">销售管理</a>
                                <span>></span>
                                <a href="/main.html#/sales/shiftRecord-list">交班记录</a>
                                <div className="lt-cont">
                                    <div className="search">
                                        <input id="posManName" type="text" ref="posManName" autoFocus="autoFocus" placeholder="收款员" />
                                    </div>
                                    <div className="sel-date">
                                        <div className="form-group float-left w140">
                                            <input name="startTimeString" id="startTimeString" placeholder="交班日期" className="form-control datepicker" type="text" readOnly />
                                        </div>
                                        <div className="float-left form-group-txt">至</div>
                                        <div className="form-group float-left w140">
                                            <input name="endTimeString" id="endTimeString" className="form-control datepicker" type="text" readOnly />
                                        </div>
                                    </div>
                                    <div className="search-reset">
                                        <a href="javascript:void(0);" className="sr-search" onClick={(e) => this.submitSearchEvent(e)}>查询</a>
                                        <a href="javascript:void(0);" className="sr-reset" onClick={(e) => this.resetEvent(e)}>重置</a>
                                        {/*<input className="sr-search" value="查询" type="button">*/}
                                        {/*<input className="sr-reset" value="重置" type="button">*/}
                                    </div>
                                </div>
                            </div>
                            <div className="mt-rt">
                            </div>
                        </div>
                        <div className="mc">
                            <div className="table-box">
                                <table>
                                    <thead>
                                    <tr >
                                        <th className="time" style={{width:"300px"}}>接班时间</th>
                                        <th className="time" style={{width:"300px"}}>交班时间</th>
                                        <th className="name">营业员</th>
                                        <th className="sales-number">销售单数</th>
                                        <th className="price">销售金额</th>
                                        <th className="price">退货金额</th>
                                        <th className="price">收款金额</th>
                                        <th className="price">现金</th>
                                        <th className="price">银行卡</th>
                                        <th className="price">微信</th>
                                        <th className="price">支付宝</th>
                                        <th className="price">医保</th>
                                        <th className="price">接班时备用金额</th>
                                        <th className="price">留用备用金额</th>
                                        <th className="price">交接现金金额</th>
                                        <th className="price">接谁的班</th>
                                        <th className="price">是否已交班</th>
                                        <th className="operating" style={{minWidth:"200px"}}>操作</th>
                                    </tr>
                                    </thead>
                                    { recordPage.content.length <= 0 &&
                                    <tr >
                                        <td colSpan="18" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                    }
                                    <tbody>
                                    {recordPage.content.map((shiftRecord, index) =>{
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.succeedTimeString}>{shiftRecord.succeedTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.shiftTimeString}>{shiftRecord.shiftTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.posManName}>{shiftRecord.posManName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.orderQuantity}>{shiftRecord.orderQuantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.receiptAmount}>{shiftRecord.receiptAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.returnedPurchaseAmount}>{shiftRecord.returnedPurchaseAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.addUpAmount}>{shiftRecord.addUpAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.cashAmount}>{shiftRecord.cashAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.bankCardAmount}>{shiftRecord.bankCardAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.wechatAmount}>{shiftRecord.wechatAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.alipayAmount}>{shiftRecord.alipayAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.medicalInsuranceAmount}>{shiftRecord.medicalInsuranceAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.succeedReadyAmount}>{shiftRecord.succeedReadyAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.keepReadyAmount}>{shiftRecord.keepReadyAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.handoverCashAmount}>{shiftRecord.handoverCashAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.succeedWhoName}>{shiftRecord.succeedWhoName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={shiftRecord.isHasShiftString}>{shiftRecord.isHasShiftString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{"paddingLeft": "0px", "paddingRight": "25px", "textAlign": "right"}}>
                                                        <a href="javascript:void(0)" className="gray-btn" onClick={()=>this.orderListModelShow(shiftRecord)} >查看</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        )
                                    })}
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={this.props.actions}/>

                {orderListModelStat && <OrderListModel store={store} actions={this.props.actions}/>}
            </div>
        )
    }
}

ShiftRecordList.propTypes = {};

ShiftRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShiftRecordList);