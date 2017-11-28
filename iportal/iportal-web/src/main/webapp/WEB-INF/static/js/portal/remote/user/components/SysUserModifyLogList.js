import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import SysUserLogDataList from "./SysUserLogDataList";

/**
 * 日志
 */

class SysUserModifyLogList extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
    }

    componentDidMount() {
        const {store} = this.context;
        const params = store.getState().todos.logParams;
        this.props.actions.sysLogList(params, params.page, params.size);
    }

    componentWillUnmount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const params = store.getState().todos.logParams;
        this.props.actions.sysLogList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const params = store.getState().todos.logParams;
        this.props.actions.sysLogList(params, page - 1, sizePerPage);
    }

    search(){
        const {store} = this.context;
        const params = store.getState().todos.logParams;
        params.searchText = this.refs.searchText.value;
        this.props.actions.sysLogList(params, 0, params.size);
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const page = store.getState().todos.logPage;
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

        const logDataListState = store.getState().todos.logDataListState;

        return (
            <div>
                <div className="layer">
                    <div className="layer-box layer-record w1075">
                        <div className="layer-header">
                            <span>修改记录</span>
                            <a href="javascript:void(0);" className="close" onClick={(e) => actions.logListModel(false, null)}></a>
                        </div>
                        <div className="layer-body">
                            <div className="search" style={{marginBottom: "10px"}}>
                                <input name="searchText" placeholder="日志ID、修改人" type="text" ref="searchText" style={{border: "1px solid #ccc"}}/>
                                <a  href="javascript:void(0);" onClick={(e) => this.search()}></a>
                            </div>
                            <div className="table-box">
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="th-number">序号</th>
                                        <th className="name">日志ID</th>
                                        <th className="name">修改人</th>
                                        <th className="time">修改时间</th>
                                        <th className="time">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {page.content.map((log, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont td-number">{index + 1}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{log.logInnerCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{log.lastModifiedBy}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{log.operationTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">
                                                        <a href="javascript:void(0);" className="button" onClick={(e) => actions.logDataListModel(true, log.id)}>查看详情</a>
                                                    </div>
                                                </td>
                                            </tr>
                                        )
                                    })}
                                    </tbody>
                                </table>
                                <IMallPaginationBar options={options} actions={this.props.actions}/>
                            </div>
                        </div>
                        <div className="layer-footer"></div>
                    </div>
                </div>
                {logDataListState&&<SysUserLogDataList store={store} actions={actions} />}
            </div>
        )
    }
}

SysUserModifyLogList.propTypes = {};

SysUserModifyLogList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SysUserModifyLogList);