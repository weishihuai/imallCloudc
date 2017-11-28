import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import MeasureRecordSearchForm from "./MeasureRecordSearchForm";
import MeasureRecordDetail from "./MeasureRecordDetail";
import ImallA from '../../../../../../common/imallbutton/components/ImallA';
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';

/**
 * 计量器具 检测记录列表
 */
class MeasureRecordList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.measureRecordList(params, params.page, params.size);
        this.props.portalOperationalAuth(['quality:measureDevice:measureDeviceRecord:detail']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.measureRecordList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.measureRecordList(params, page - 1, sizePerPage);
    }

    measuringDeviceAccountsUseStateFormat(useStateCode){
        switch (useStateCode){
            case "ENABLED":
                return "启用";
            case "DISABLED":
                return "禁用";
            case "SEAL_UP":
                return "封存";
            case "SCRAP":
                return "报废";
            default:
                return "暂无";
        }
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const measureRecordList = store.getState().todos.page.content || [];

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
            <div className="main-box m-record">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>检查记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">计量器具管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">检查记录</a>
                            <MeasureRecordSearchForm actions={actions} store={store}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="return-number" style={{width:"175px"}}>计量器具编号</th>
                                    <th className="return-number" style={{width:"125px"}}>出厂编号</th>
                                    <th className="name" style={{width:"165px"}}>名称</th>
                                    <th className="type">类型</th>
                                    <th className="manufacturers"  style={{width:"130px"}}>生产厂商</th>
                                    <th className="class-number">分类编号</th>
                                    <th className="measuring-range">测量范围</th>
                                    <th className="precision-grade">精度等级</th>
                                    <th className="name">负责人</th>
                                    <th className="testing-cycle" style={{width:"120px"}}>检测周期(天)</th>
                                    <th className="status">使用状态</th>
                                    <th className="name">检测人</th>
                                    <th className="name">复检人</th>
                                    <th className="testing-cycle">检测日期</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { measureRecordList.length <= 0 &&
                                <tr >
                                    <th colSpan="15" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {measureRecordList.map((measureRecord, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={measureRecord.measuringDeviceNum}>{measureRecord.measuringDeviceNum}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.manufacturingNum || "暂无"}>{measureRecord.manufacturingNum || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.deviceNm}>{measureRecord.deviceNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.model || "暂无"}>{measureRecord.model || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.produceManufacturer || "暂无"}>{measureRecord.produceManufacturer || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.categoryNum || "暂无"}>{measureRecord.categoryNum || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.measureRange || "暂无"}>{measureRecord.measureRange || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.precisionLevel || "暂无"}>{measureRecord.precisionLevel || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.responseMan}>{measureRecord.responseMan}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.measurePeriod}>{measureRecord.measurePeriod}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.measuringDeviceAccountsUseStateFormat(measureRecord.useStateCode)}>{this.measuringDeviceAccountsUseStateFormat(measureRecord.useStateCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.measureMan}>{measureRecord.measureMan}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.reviewMan}>{measureRecord.reviewMan}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureRecord.measureDateString}>{measureRecord.measureDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingLeft": "0", "paddingRight": "25px","textAlign": "right"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="quality:measureDevice:measureDeviceRecord:detail" className="gray-btn" text="查看" onClick={()=>this.props.actions.measureRecordDetailModal(true,measureRecord.id)}>查看</ImallA>
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
                    {store.getState().todos.measureRecordDetailState && <MeasureRecordDetail store={store} actions={this.props.actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

MeasureRecordList.propTypes = {};

MeasureRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(MeasureRecordList);