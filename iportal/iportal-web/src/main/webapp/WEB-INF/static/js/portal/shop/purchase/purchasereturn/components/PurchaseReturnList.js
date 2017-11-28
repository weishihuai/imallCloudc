import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ImallA from "../../../../../common/imallbutton/components/ImallA";
import PurchaseReturnListSearchForm from "./PurchaseReturnListSearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import PurchaseReturnAddForm from "./PurchaseReturnAddForm";
import PurchaseReturnDetail from "./PurchaseReturnDetail";
import {portalOperationalAuth} from "../../../../../common/imallbutton/actions";

class PurchaseReturnList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size);
        this.props.portalOperationalAuth(['purchase:return:add', 'purchase:return:detail']);
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
                {store.getState().todos.showDetail&& <PurchaseReturnDetail actions={actions}/>}
                {store.getState().todos.showAdd&& <PurchaseReturnAddForm actions={actions}/>}
                <div className="main-box">
                    <div className="main">
                        <div className="mt">
                            <div className="mt-lt">
                                <h5>购进退出</h5>
                                <a href="javascript:;">采购管理</a>
                                <span>></span>
                                <a href="javascript:;">购进退出</a>
                                <PurchaseReturnListSearchForm actions={actions} />
                            </div>
                            <div className="mt-rt">
                                <ImallA href="javascript:;" permissionCode="purchase:return:add" className="added" text="添加" onClick={()=> actions.showAdd()}/>
                            </div>
                        </div>
                        <div className="mc">
                            <div className="table-box">
                                <table>
                                    <thead>
                                    <tr>
                                        <th style={{width: "220px"}} className="return-number">退货单编号</th>
                                        <th className="return-type">退货类型</th>
                                        <th className="supplier" style={{width: "220px"}}>供应单位名称</th>
                                        <th className="return-reason">退货原因</th>
                                        <th className="procurement">采购员</th>
                                        <th className="return-date" style={{width: "220px"}} >退货时间</th>
                                        <th className="assessor">审核员</th>
                                        <th className="ck-assistant">出库员</th>
                                        <th className="status">状态</th>
                                        <th className="return-money">退货金额（元）</th>
                                        <th className="operating" style={{width: "150px"}}>操作</th>
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
                                       content.map((item, index) => {
                                           return (
                                               <tr key={index}>
                                                   <td>
                                                       <div className="td-cont" title={item.returnedPurchaseOrderNum}>{item.returnedPurchaseOrderNum}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.returnedPurchaseType == 'NORMAL_RETURNED' ? '正常退货' : '不合格退货'}>{item.returnedPurchaseType == 'NORMAL_RETURNED' ? '正常退货' : '不合格退货'}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.supplierNm}>{item.supplierNm}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.returnedPurchaseReason}>{item.returnedPurchaseReason}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.purchaseMan}>{item.purchaseMan}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.createDateString}>{item.createDateString}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.approveMan}>{item.approveMan}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.outStorageMan}>{item.outStorageMan}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.isPayed == 'Y' ? '已结款': '未结款'}>{item.isPayed == 'Y' ? '已结款': '未结款'}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" title={item.returnedPurchaseTotalAmount}>{item.returnedPurchaseTotalAmount}</div>
                                                   </td>
                                                   <td>
                                                       <div className="td-cont" style={{"paddingLeft": "0", "paddingRight": "25px", "textAlign": "right"}}>
                                                          <ImallA href="javascript:;" permissionCode="purchase:return:detail" className="gray-btn" text="查看" onClick={() => actions.showDetail(item.id)}/>
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

PurchaseReturnList.propTypes = {
    actions: PropTypes.object.isRequired
}

PurchaseReturnList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseReturnList);