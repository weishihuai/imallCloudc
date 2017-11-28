import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../../js/common/imallbutton/actions";
import Imalla from "../../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";

import UseRecordSearchForm from './UseRecordSearchForm';
import UseRecordDetail from './UseRecordDetail';

class UseRecordList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["quality:facilityDevice:facilityDeviceUseRecord:detail"]);
        this.props.actions.useRecordList(params, params.page, params.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.useRecordList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.useRecordList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {page, isShowDetail} = store.getState().todos;
        const number=page.number+1;
        const record = store.getState().todos.page.content || [];
        const options = {
            sizePerPage: page.size>0?page.size:10,
            sizePerPageList: page.totalElements>0?[10, 20, 40]:[],
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
            paginationShowsTotal: page.totalElements > page.size?this.renderShowsTotal:null,
            hideSizePerPage:page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                {isShowDetail && <UseRecordDetail actions={actions}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>使用记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">设施设备</a>
                            <span>></span>
                            <a href="javascript:void(0);">使用记录</a>
                            <UseRecordSearchForm actions={actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="return-number" style={{width: 50 + 'px'}}>设备编号</th>
                                    <th className="name">设备名称</th>
                                    <th className="type" style={{width: 100 + 'px'}}>设备类型</th>
                                    <th className="return-model" style={{width: 50 + 'px'}}>型号</th>
                                    <th className="producing">购置地点</th>
                                    <th className="name" style={{width: 55 + 'px'}}>使用人</th>
                                    <th className="time" style={{width: 55 + 'px'}}>使用时间</th>
                                    <th className="operating" style={{width: 100 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    record.length <= 0 &&
                                    <tr>
                                        <th colSpan="8" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((record) => {
                                        return (
                                            <tr key={record.id}>
                                                <td>
                                                    <div className="td-cont"  title={record.deviceNum}>{record.deviceNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.deviceNm}>{record.deviceNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.deviceTypeName}>{record.deviceTypeName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.model}>{record.model}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.purchasePlace}>{record.purchasePlace}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.operationMan}>{record.operationMan}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.useDateString}>{record.useDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                        <Imalla href="javascript:void(0);" permissionCode="quality:facilityDevice:facilityDeviceUseRecord:detail" className="gray-btn" text="查看" onClick={() => this.props.actions.showUseRecordDetail(record.id)}  />
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
        );
    }
}

UseRecordList.propTyes = {
  actions: PropTypes.object.isRequired
};

UseRecordList.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(UseRecordList);
