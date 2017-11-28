import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import Imalla from "../../../../../../common/imallbutton/components/ImallA";
import TemperatureMoistureMonitorRecordSearchForm from "./TemperatureMoistureMonitorRecordSearchForm";
import TemperatureMoistureMonitorRecordAddForm from "./TemperatureMoistureMonitorRecordAddForm";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../../../../js/common/imallbutton/actions";

class TemperatureMoistureMonitorRecord extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["quality:displayStorage:temperatureMoistureMonitorRecord:add"]);
        this.props.actions.temperatureMoistureMonitorRecordList(params, params.page, params.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    showAddForm(){
        this.props.actions.showAddForm(true);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.temperatureMoistureMonitorRecordList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.temperatureMoistureMonitorRecordList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {params} = store.getState().todos;
        const {page} = store.getState().todos;
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
            hideSizePerPage:page.totalElements <= page.size
        };

        return (
            <div>
                {store.getState().todos.isShowAdd&&<TemperatureMoistureMonitorRecordAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveRecord(data, actions, params)} />}
                <div className="main-box">
                    <div className="main">
                        <div className="mt">
                            <div className="mt-lt">
                                <h5>温度湿度监控记录</h5>
                                <a href="javascript:void(0);">质量管理</a>
                                <span>></span>
                                <a href="javascript:void(0);">陈列与存储</a>
                                <span>></span>
                                <a href="javascript:void(0);">温度湿度监控记录</a>
                                <TemperatureMoistureMonitorRecordSearchForm actions={actions} />
                            </div>
                            <div className="mt-rt">
                                <Imalla href="javascript:void(0);" permissionCode="quality:displayStorage:temperatureMoistureMonitorRecord:add" className="added" onClick={this.showAddForm.bind(this)} text="添加" />
                            </div>
                        </div>
                        <div className="mc">
                            <div className="table-box">
                                <table>
                                    <thead>
                                    <tr>
                                        <th className="cargo-location">货位</th>
                                        <th className="time">日期</th>
                                        <th className="statutory">记录人</th>
                                        <th className="time">时间</th>
                                        <th className="degree">温度（℃）</th>
                                        <th className="degree">湿度（%）</th>
                                        <th className="status">调控措施</th>
                                        <th className="time">调控后时间</th>
                                        <th className="degree">调控后温度（℃）</th>
                                        <th className="degree">调控后湿度（%）</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    {record.length<=0 &&
                                        <tr >
                                            <td colSpan="10" style={{textAlign:"center"}}>
                                                <div className="empty-box">
                                                    <span>暂无数据</span>
                                                </div>
                                            </td>
                                        </tr>
                                    }
                                    {
                                        record.map((record) => {
                                            return(
                                                <tr key={record.id}>
                                                    <td>
                                                        <div className="td-cont" title={record.storageSpaceNm}>{record.storageSpaceNm}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.monitorDateString}>{record.monitorDateString}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.recordMan}>{record.recordMan}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.monitorTime}>{record.monitorTime}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.temperature}>{record.temperature}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.moisture}>{record.moisture}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.controlMeasure}>{record.controlMeasure}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.timeAfterControl}>{record.timeAfterControl}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.temperatureAfterControl}>{record.temperatureAfterControl}</div>
                                                    </td>
                                                    <td>
                                                        <div className="td-cont" title={record.moistureAfterControl}>{record.moistureAfterControl}</div>
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

TemperatureMoistureMonitorRecord.propTypes = {
    actions: PropTypes.object.isRequired
};

TemperatureMoistureMonitorRecord.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(TemperatureMoistureMonitorRecord);