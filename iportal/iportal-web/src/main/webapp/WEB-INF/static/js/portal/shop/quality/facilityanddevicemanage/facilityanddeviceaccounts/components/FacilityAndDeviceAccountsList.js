import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../../js/common/imallbutton/actions";
import Imalla from "../../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import * as types from "../constants/ActionTypes";
import FacilityAndDeviceAccountsSearchForm from "./FacilityAndDeviceAccountsSearchForm";
import FacilityAndDeviceAccountsAddForm from "./FacilityAndDeviceAccountsAddForm";
import FacilityAndDeviceAccountsEditForm from "./FacilityAndDeviceAccountsEditForm";
import FacilityAndDeviceAccountsDetail from "./FacilityAndDeviceAccountsDetail";
import FacilityAndDeviceAccountsUseForm from "./FacilityAndDeviceAccountsUseForm";
import FacilityAndDeviceAccountsMaintainingForm from "./FacilityAndDeviceAccountsMaintainingForm";

class FacilityAndDeviceAccountsList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["quality:facilityDevice:facilityDeviceAccounts:add", "quality:facilityDevice:facilityDeviceAccounts:update",
            "quality:facilityDevice:facilityDeviceAccounts:use", "quality:facilityDevice:facilityDeviceAccounts:maintain",
            "quality:facilityDevice:facilityDeviceAccounts:detail"]);
        this.props.actions.facilityAndDeviceAccountsList(params, params.page, params.size);
    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.facilityAndDeviceAccountsList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.facilityAndDeviceAccountsList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {params, page, isShowAdd, isShowDetail, isShowEdit, isShowUse, isShowMaintaining} = store.getState().todos;
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
                {isShowAdd && <FacilityAndDeviceAccountsAddForm actions={this.props.actions} formType={types.FACILITY_AND_DEVICE_ACCOUNTS_ADD_MODULE} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)}/>}
                {isShowEdit && <FacilityAndDeviceAccountsEditForm actions={this.props.actions} formType={types.FACILITY_AND_DEVICE_ACCOUNTS_EDIT_MODULE} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)}/>}
                {isShowDetail && <FacilityAndDeviceAccountsDetail actions={this.props.actions} formType={types.FACILITY_AND_DEVICE_ACCOUNTS_DETAIL_MODULE}/>}
                {isShowUse && <FacilityAndDeviceAccountsUseForm actions={this.props.actions} formType={types.FACILITY_AND_DEVICE_ACCOUNTS_USE_MODULE} onSubmit={(data) => this.props.actions.saveUseRecord(data, actions, params)}/>}
                {isShowMaintaining && <FacilityAndDeviceAccountsMaintainingForm actions={this.props.actions} formType={types.FACILITY_AND_DEVICE_ACCOUNTS_MAINTAINING_MODULE} onSubmit={(data) => this.props.actions.saveMaintainingRecord(data, actions, params)}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>设施设备台账</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">设施设备管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">设施设备台账</a>
                            <FacilityAndDeviceAccountsSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="quality:facilityDevice:facilityDeviceAccounts:add" className="added" onClick={() => this.props.actions.showOrHidden(true, types.FACILITY_AND_DEVICE_ACCOUNTS_ADD_MODULE)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="return-number">设备编号</th>
                                    <th className="type">设备类型</th>
                                    <th className="name" style={{width: '150px'}}>设备名称</th>
                                    <th className="return-model">设备型号</th>
                                    <th className="manufacturers" style={{width: '150px'}}>生产厂商</th>
                                    <th className="price">购置价格</th>
                                    <th className="time" style={{width: '90px'}}>购置时间</th>
                                    <th className="time" style={{width: '90px'}}>启用时间</th>
                                    <th className="producing">购置地点</th>
                                    <th className="use">用途</th>
                                    <th className="time" style={{width: '80px'}}>使用年限</th>
                                    <th className="name" style={{width: '80px'}}>负责人</th>
                                    <th className="note">备注</th>
                                    <th className="operating" style={{width: 200 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {
                                    record.length <= 0 &&
                                    <tr>
                                        <th colSpan="14" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((record) => {
                                      return(
                                          <tr key={record.id}>
                                              <td>
                                                  <div className="td-cont" title={record.deviceNum}>{record.deviceNum}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.deviceTypeName}>{record.deviceTypeName}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.deviceNm}>{record.deviceNm}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.model}>{record.model}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.produceManufacturer}>{record.produceManufacturer}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.purchasePrice}>{record.purchasePrice}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.purchaseDateString}>{record.purchaseDateString}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.enableTimeString}>{record.enableTimeString}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.purchasePlace}>{record.purchasePlace}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.application}>{record.application}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.serviceLife}>{record.serviceLife}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.responseMan}>{record.responseMan}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" title={record.remark}>{record.remark}</div>
                                              </td>
                                              <td>
                                                  <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                      <Imalla href="javascript:void(0);" permissionCode="quality:facilityDevice:facilityDeviceAccounts:update" className="gray-btn" text="修改" onClick={() => this.props.actions.showOrHidden(true, types.FACILITY_AND_DEVICE_ACCOUNTS_EDIT_MODULE, record)}  />
                                                      <Imalla href="javascript:void(0);" permissionCode="quality:facilityDevice:facilityDeviceAccounts:detail" className="gray-btn" text="查看" onClick={() => this.props.actions.showOrHidden(true, types.FACILITY_AND_DEVICE_ACCOUNTS_DETAIL_MODULE, record)}  />
                                                      <Imalla href="javascript:void(0);" permissionCode="quality:facilityDevice:facilityDeviceAccounts:maintain" className="gray-btn" text="维护" onClick={() => this.props.actions.showOrHidden(true, types.FACILITY_AND_DEVICE_ACCOUNTS_MAINTAINING_MODULE, record)}  />
                                                      <Imalla href="javascript:void(0);" permissionCode="quality:facilityDevice:facilityDeviceAccounts:use" className="gray-btn" text="使用" onClick={() => this.props.actions.showOrHidden(true, types.FACILITY_AND_DEVICE_ACCOUNTS_USE_MODULE, record)}  />
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
                    <IMallPaginationBar options={options} actions={actions}/>
                </div>
            </div>
        );
    }
}

FacilityAndDeviceAccountsList.propTypes = {
    actions: PropTypes.object.isRequired
};

FacilityAndDeviceAccountsList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FacilityAndDeviceAccountsList);