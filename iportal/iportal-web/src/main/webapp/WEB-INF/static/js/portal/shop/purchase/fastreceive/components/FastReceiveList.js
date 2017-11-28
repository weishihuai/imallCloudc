import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import ImallA from "../../../../../common/imallbutton/components/ImallA";
import FastReceiveListSearchForm from "./FastReceiveListSearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../../common/imallbutton/actions";
import FastReceiveDetail from "./FastReceiveDetail";
import FastReceiveAddForm from "./FastReceiveAddForm";

class FastReceiveList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size);
        this.props.portalOperationalAuth(['purchase:fastReceive:add', 'purchase:fastReceive:detail']);
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
                {store.getState().todos.showDetail && <FastReceiveDetail actions={actions}/>}
                {store.getState().todos.showAdd && <FastReceiveAddForm actions={actions}/>}
                <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>快速收货</h5>
                            <a href="javascript:;">采购管理</a>
                            <span>></span>
                            <a href="javascript:;">快速收货</a>
                            <FastReceiveListSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <a onClick={() => actions.showAdd()} href="javascript:;" className="added">添加</a>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="acceptance-number">验收单编号</th>
                                    <th className="supplier">供应商单位名称</th>
                                    <th className="acceptor">验收人</th>
                                    <th className="acceptance-amount">验收金额（元）</th>
                                    <th className="acceptance-date">验收日期</th>
                                    <th className="rk-time">入库时间</th>
                                    <th className="operating" style={{width: "430px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {content.length <= 0 &&
                                <tr >
                                    <td colSpan="7" style={{textAlign:"center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </td>
                                </tr>
                                }
                                {
                                    content.map((item, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont" title={item.acceptanceOrderNum}>{item.acceptanceOrderNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.supplierNm}>{item.supplierNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.acceptanceMan}>{item.acceptanceMan}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.acceptanceTotalAmount}>{item.acceptanceTotalAmount}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.acceptanceTimeString}>{item.acceptanceTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.inStorageTimeString}>{item.inStorageTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                        <ImallA href="javascript:;" permissionCode="purchase:fastReceive:detail" className="gray-btn" text="查看验收单" onClick={() => actions.showDetail(item.purchaseAcceptanceRecordId)}/>
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
                <IMallPaginationBar options={options} actions={actions}/>
                </div>
            </div>
        )
    }
}

FastReceiveList.propTypes = {
    actions: PropTypes.object.isRequired
}

FastReceiveList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FastReceiveList);