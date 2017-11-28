import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ImallA from "../../../../../common/imallbutton/components/ImallA";
import PurchaseAcceptanceListSearchForm from "./PurchaseAcceptanceListSearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import PurchaseOrderDetailComponents from '../../purchaseorderdetail/components/PurchaseOrderDetailComponents';
import {portalOperationalAuth} from "../../../../../common/imallbutton/actions";
import PurchaseAcceptanceForm from "./PurchaseAcceptanceForm";
import PurchaseReceiveDetail from "./PurchaseReceiveDetail";

class PurchaseAcceptanceList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size);
        this.props.portalOperationalAuth(['purchase:check:detail', 'purchase:check:check']);
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

    renderOp(data){
        return (<td>
            <div className="td-cont" style={{"paddingLeft": "0", "paddingRight": "25px", "textAlign": "right"}}>
                <ImallA href="javascript:;" permissionCode="purchase:check:detail" className="gray-btn" text="查看收货单" onClick={() => this.props.actions.showReceiveDetail(data.id)}/>
                {"N" == data.isAllAcceptance && <ImallA href="javascript:;" permissionCode="purchase:check:check" className="gray-btn" text="采购验收" onClick={() => this.props.actions.showPurchaseAcceptance(data.id)}/>}
            </div>
        </td>);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {page} = store.getState().todos;
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
                {store.getState().purchaseOrderDetailTodos.showDetail&&<PurchaseOrderDetailComponents actions={actions} />}
                {store.getState().todos.showAcceptance&&<PurchaseAcceptanceForm actions={actions} />}
                {store.getState().todos.showReceiveDetail&&<PurchaseReceiveDetail actions={actions} />}
                <div className="main-box">
                    <div className="main">
                        <div className="mt">
                            <div className="mt-lt">
                                <h5>采购验收</h5>
                                <a href="javascript:;">采购管理</a>
                                <span>></span>
                                <a href="javascript:;">采购验收</a>
                                <PurchaseAcceptanceListSearchForm actions={actions} />
                            </div>
                        </div>
                        <div className="mc">
                            <div className="table-box">
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="order-number">收货单编号</th>
                                        <th className="supplier">供应商单位名称</th>
                                        <th className="procurement">收货人</th>
                                        <th className="purchase-amount">收货时间</th>
                                        <th className="status">状态</th>
                                        {/*<th className="order-date">备注</th>*/}
                                        <th className="operating" style={{"width": "430px"}}>操作</th>
                                    </tr>
                                    </thead>
                                    {content.length <= 0 &&
                                    <tr >
                                        <td colSpan="100" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                    }
                                    <tbody>
                                    {
                                        content.map((ct, i)=> {
                                            return (
                                                <tr key={i}>
                                                    <td>
                                                        <div className="td-cont" title={ct.receiveOrderNum}>{ct.receiveOrderNum}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.supplierNm}>{ct.supplierNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.receiver}>{ct.receiver}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.receiveTimeString}>{ct.receiveTimeString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={ct.isAllAcceptance == 'Y' ? '已验收' : '待验收'}>{ct.isAllAcceptance == 'Y' ? '已验收' : '待验收'}</div>
                                                    </td>
                                                    {/*<td>
                                                        <div className="td-cont" title={ct.remark}>{ct.remark}</div>
                                                    </td>*/}
                                                    {this.renderOp(ct)}
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

PurchaseAcceptanceList.propTypes = {
    actions: PropTypes.object.isRequired
}

PurchaseAcceptanceList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseAcceptanceList);