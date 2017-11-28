import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import MeasureDeviceAccountsSearchForm from "./MeasureDeviceAccountsSearchForm";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import ImallA from '../../../../../../common/imallbutton/components/ImallA';
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';
import MeasureDeviceAccountsAddForm from "./MeasureDeviceAccountsAddForm";
import MeasureDeviceAccountsUpdateForm from "./MeasureDeviceAccountsUpdateForm";
import MeasureDeviceAccountsCheckForm from "./MeasureDeviceAccountsCheckForm";

/**
 * 计量器具列表
 */
class MeasureDeviceAccountsList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.measuringDeviceAccountsList(params, params.page, params.size);
        this.props.portalOperationalAuth(['quality:measureDevice:measureDeviceAccounts:add','quality:measureDevice:measureDeviceAccounts:update','quality:measureDevice:measureDeviceAccounts:check']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.measuringDeviceAccountsList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.measuringDeviceAccountsList(params, page - 1, sizePerPage);
    }

    measuringDeviceAccountsUpdate(row) {
        this.props.actions.measuringDeviceAccountsUpdateModel(true,row);
    }

    measuringDeviceAccountsAdd() {
        this.props.actions.measuringDeviceAccountsAddModel(true);
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

    //检测数据提交
    measuringDeviceAccountsCheckData(data){
        const {store} = this.context;
        const {params} = store.getState().todos;
        return this.props.actions.measuringDeviceAccountsCheckData(data,this.context.store.getState().todos.id,params);
    }

    render() {
        const {store} = this.context;
        const page = store.getState().todos.page;
        const {params} = store.getState().todos;
        const number = page.number + 1;
        const measureDeviceAccountsList = store.getState().todos.page.content || [];
        const {actions} = this.props;

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
                            <h5>计量器具台账</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">计量器具管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">计量器具台账</a>
                            <MeasureDeviceAccountsSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="quality:measureDevice:measureDeviceAccounts:add" className="added" text="添加" onClick={()=> this.measuringDeviceAccountsAdd()}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="return-number" style={{width:"200px"}}>计量器具编号</th>
                                    <th className="return-number" style={{width:"150px"}}>出厂编号</th>
                                    <th className="name" style={{width:"300px"}}>名称</th>
                                    <th className="type">型号</th>
                                    <th className="manufacturers" style={{width:"150px"}}>生产厂商</th>
                                    <th className="class-number">分类编号</th>
                                    <th className="measuring-range">测量范围</th>
                                    <th className="precision-grade">精度等级</th>
                                    <th className="name">负责人</th>
                                    <th className="testing-cycle" style={{width:"120px"}}>检测周期(天)</th>
                                    <th className="status">使用状态</th>
                                    <th className="operating" style={{"width": "430px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { measureDeviceAccountsList.length <= 0 &&
                                <tr >
                                    <th colSpan="12" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {measureDeviceAccountsList.map((measureDeviceAccounts, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.measuringDeviceNum}>{measureDeviceAccounts.measuringDeviceNum}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.manufacturingNum || "暂无"}>{measureDeviceAccounts.manufacturingNum || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.deviceNm}>{measureDeviceAccounts.deviceNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.model || "暂无"}>{measureDeviceAccounts.model || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.produceManufacturer || "暂无"}>{measureDeviceAccounts.produceManufacturer || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.categoryNum || "暂无"}>{measureDeviceAccounts.categoryNum || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.measureRange || "暂无"}>{measureDeviceAccounts.measureRange || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.precisionLevel || "暂无"}>{measureDeviceAccounts.precisionLevel || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.responseMan}>{measureDeviceAccounts.responseMan}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={measureDeviceAccounts.measurePeriod}>{measureDeviceAccounts.measurePeriod}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={this.measuringDeviceAccountsUseStateFormat(measureDeviceAccounts.useStateCode)}>{this.measuringDeviceAccountsUseStateFormat(measureDeviceAccounts.useStateCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingLeft":0,"paddingRight":"25px","textAlign":"right"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="quality:measureDevice:measureDeviceAccounts:update" className="gray-btn" text="修改" onClick={()=> this.measuringDeviceAccountsUpdate(measureDeviceAccounts)}>修改</ImallA>
                                            <ImallA href="javascript:void(0);" permissionCode="quality:measureDevice:measureDeviceAccounts:check" className="gray-btn" text="检测" onClick={() => actions.measuringDeviceAccountsCheckModel(true,measureDeviceAccounts.id)}>检测</ImallA>
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
                    {store.getState().todos.checkState && <MeasureDeviceAccountsCheckForm actions={actions} store={store} onSubmit={(data) => this.measuringDeviceAccountsCheckData(data)}/>}
                    {store.getState().todos.editState && <MeasureDeviceAccountsUpdateForm actions={actions} store={store} onSubmit={(data) => actions.measuringDeviceAccountsUpdateData(data,params)}/>}
                    {store.getState().todos.addState && <MeasureDeviceAccountsAddForm actions={actions} store={store} onSubmit={(data) => actions.measuringDeviceAccountsAddData(data,params)}/>}
                    <IMallPaginationBar options={options} actions={actions}/>
                </div>
            </div>
        )
    }
}

MeasureDeviceAccountsList.propTypes = {};

MeasureDeviceAccountsList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(MeasureDeviceAccountsList);