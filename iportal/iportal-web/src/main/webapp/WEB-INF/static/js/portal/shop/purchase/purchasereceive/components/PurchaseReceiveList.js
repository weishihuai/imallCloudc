import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ImallA from "../../../../../common/imallbutton/components/ImallA";
import PurchaseReceiveListSearchForm from "./PurchaseReceiveListSearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import PurchaseOrderDetailComponents from "../../purchaseorderdetail/components/PurchaseOrderDetailComponents";
import {showDetail} from "../../purchaseorderdetail/actions";
import {portalOperationalAuth} from "../../../../../common/imallbutton/actions";
import PurchaseReceiveForm from '../../purchasereceiveform/components/PurchaseReceiveForm';
import {showPurchaseReceive} from "../../purchasereceiveform/actions";
import CommonConfirmComponent from "../../../../../common/component/CommonConfirmComponent";

class PurchaseReceiveList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size);
        this.props.portalOperationalAuth(['purchase:receive:cancel', 'purchase:receive:receive', 'purchase:receive:detail']);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        this.props.actions.query(params, page.number, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.query(params, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    render() {
        const {actions, showPurchaseReceive, showDetail}=this.props;
        const {store} = this.context;
        const todos = store.getState().todos;
        const {page, params, showConfirm, selectId,selectPurchaseOrderNum} = todos;
        const content = store.getState().todos.page.content;
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: [10, 20, 40],
            pageStartIndex: 1,
            page: page.number + 1,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            paginationShowsTotal: page.totalElements > page.size ? this.renderShowsTotal : null,
            hideSizePerPage:page.totalElements <= page.size
        };

        return (
            <div>
                {store.getState().purchaseReceiveTodos.showPurchaseReceive&&<PurchaseReceiveForm actions={actions} callback={() => actions.query(params, 0, page.size)} />}
                {store.getState().purchaseOrderDetailTodos.showDetail&&<PurchaseOrderDetailComponents actions={actions}/>}
                {showConfirm && <CommonConfirmComponent store={store} actions={actions} zIndex="999" confirmBtn="确认" title="提示" text={"确定取消 "+selectPurchaseOrderNum+" 的订单？"} callback={() => actions.cancelOrder(selectId, todos, () => {actions.setShowConfirm(false, null, "")})} close={() => actions.setShowConfirm(false, null, "")}/>}
                <div className="main-box">
                    <div className="main">
                        <div className="mt">
                            <div className="mt-lt">
                                <h5>采购收货</h5>
                                <a href="javascript:;">采购管理</a>
                                <span>></span>
                                <a href="javascript:;">采购收货</a>
                                <PurchaseReceiveListSearchForm actions={actions} />
                            </div>
                        </div>
                        <div className="mc">
                            <div className="table-box">
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="order-number" style={{"width": "135px"}} >采购订单编号</th>
                                        <th className="supplier">供应商单位名称</th>
                                        <th className="procurement">采购人名称</th>
                                        <th className="purchase-amount">采购金额（元）</th>
                                        <th className="order-date">订单日期</th>
                                        <th className="estimated-time">预计到货时间</th>
                                        <th className="status">状态</th>
                                        <th className="operating" style={{"width": "430px"}}>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {content.length <= 0 &&
                                    <tr >
                                        <td colSpan="8" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                    }
                                    {
                                        content.map((ct, i)=> {
                                            return (
                                                <tr key={i}>
                                                    <td>
                                                        <div className="td-cont" title={ct.purchaseOrderNum}>{ct.purchaseOrderNum}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.purchaseMan}>{ct.purchaseMan}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.purchaseTotalAmount}>{ct.purchaseTotalAmount}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.createDateString}>{ct.createDateString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.expectedArrivalTimeString}>{ct.expectedArrivalTimeString}</div>
                                                    </td>
                                                    <td><div className="td-cont" title={ct.purchaseOrderStateName}>{ct.purchaseOrderStateName}</div></td>
                                                    <td>
                                                        <div className="td-cont" style={{"paddingLeft": "0", "paddingRight": "25px", "textAlign": "right"}}>
                                                            <ImallA href="javascript:;" permissionCode="purchase:receive:cancel" className="gray-btn" text="取消" onClick={() => actions.setShowConfirm(true, ct.id, ct.purchaseOrderNum)}/>
                                                            <ImallA href="javascript:;" permissionCode="purchase:receive:detail" className="gray-btn" text="查看" onClick={() => showDetail(ct.id)}/>
                                                            <ImallA href="javascript:;" permissionCode="purchase:receive:receive" className="gray-btn" text="采购收货" onClick={() => showPurchaseReceive(ct.id)}/>
                                                        </div>
                                                    </td>
                                                </tr>
                                            );
                                        })
                                    }
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

PurchaseReceiveList.propTypes = {
    actions: PropTypes.object.isRequired
}

PurchaseReceiveList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({showDetail, portalOperationalAuth, showPurchaseReceive}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseReceiveList);