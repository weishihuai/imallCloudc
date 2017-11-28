import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {portalOperationalAuth} from "../../../../../../../js/common/imallbutton/actions";
import Imalla from "../../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";

import PrescriptionSearchForm from './PrescriptionSearchForm';
import PrescriptionRegisterAddForm from './PrescriptionRegisterAddForm';
import PrescriptionRegisterEditForm from './PrescriptionRegisterEditForm';
import PrescriptionDetail from './PrescriptionDetail';
import PrescriptionDispensingForm from './PrescriptionDispensingForm';

class PrescriptionList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {actions, modelType}=this.props;
        const {params} = store.getState().prescriptionTodos;
        this.props.portalOperationalAuth(["sales:prescription:prescriptionRegister:add", "sales:prescription:prescriptionRegister:update","sales:prescription:prescriptionRegister:detail",
            "sales:prescription:prescriptionDispensing:dispensing", "sales:prescription:prescriptionDispensing:detail",
            "sales:prescription:prescriptionRecord:detail"]);
        const searchParams = Object.assign({}, params, {
            modelType: modelType,
            prescriptionRegisterState: this.props.prescriptionRegisterState
        });
        actions.prescriptionList(searchParams, searchParams.page, searchParams.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {
        $("#root").css('min-width', '2000px');
    }

    showAddForm(){
        this.props.actions.showAddForm(true);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().prescriptionTodos;
        this.props.actions.prescriptionList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().prescriptionTodos;
        this.props.actions.prescriptionList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions, modelType}=this.props;
        const {store} = this.context;
        const {params, page, isShowAdd, isShowDetail, isShowEdit, isShowDispensing} = store.getState().prescriptionTodos;
        const number=page.number+1;
        const record = page.content || [];
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
            <div className="main-box">
                {modelType==='prescriptionRegister' && isShowAdd && <PrescriptionRegisterAddForm actions={actions} onSubmit={(data) => actions.saveOrUpdate(data, actions, params)}/>}
                {modelType==='prescriptionRegister' && isShowEdit && <PrescriptionRegisterEditForm actions={actions} onSubmit={(data) => actions.saveOrUpdate(data, actions, params)}/>}
                {isShowDetail && <PrescriptionDetail actions={actions}/>}
                {modelType==='prescriptionDispensing' && isShowDispensing && <PrescriptionDispensingForm actions={actions} onSubmit={(data) => actions.dispensingPrescription(data, actions, params)}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            {modelType==='prescriptionRegister' && <h5>处方登记</h5>}
                            {modelType==='prescriptionDispensing' && <h5>处方调剂</h5>}
                            {modelType==='prescriptionRecord' && <h5>处方记录</h5>}
                            <a href="javascript:void(0);">销售管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">处方管理</a>
                            <span>></span>
                            {modelType==='prescriptionRegister' && <a href="javascript:void(0);">处方登记</a>}
                            {modelType==='prescriptionDispensing' && <a href="javascript:void(0);">处方调剂</a>}
                            {modelType==='prescriptionRecord' && <a href="javascript:void(0);">处方记录</a>}
                            <PrescriptionSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            {modelType==='prescriptionRegister' && <Imalla href="javascript:void(0);" permissionCode="sales:prescription:prescriptionRegister:add" className="added" onClick={this.showAddForm.bind(this)} text="添加" />}
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="odd-number">处方销售单号</th>
                                    <th className="name">患者名称</th>
                                    <th className="phone-number">手机</th>
                                    <th className="medical-institution">医疗机构</th>
                                    <th className="id-number">证件号码</th>
                                    {modelType!=='prescriptionRecord' && <th className="name">医生名称</th>}
                                    {modelType==='prescriptionRecord' && <th className="name">调剂人</th>}
                                    {modelType==='prescriptionRecord' && <th className="name">审核人</th>}
                                    {modelType==='prescriptionRecord' && <th className="name">发药人</th>}
                                    {modelType==='prescriptionRecord' && <th className="name">复核人</th>}
                                    <th className="time">处方日期</th>
                                    {modelType!=='prescriptionRecord' && <th className="time">创建时间</th>}
                                    {modelType==='prescriptionRecord' && <th className="time">调剂时间</th>}
                                    <th className="operating" style={{width: 150 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {record.length <= 0 &&
                                <tr>
                                    <th colSpan={modelType==='prescriptionRecord' ? 12 : 9} style={{textAlign: "center"}}>
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
                                                    <div className="td-cont" title={record.prescriptionSellOrderCode}>{record.prescriptionSellOrderCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.patientNm}>{record.patientNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.mobile}>{record.mobile}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.medicalOrg}>{record.medicalOrg}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.certNum}>{record.certNum}</div>
                                                </td>
                                                {
                                                    modelType!=='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.doctorName}>{record.doctorName}</div>
                                                    </td>
                                                }
                                                {
                                                    modelType==='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.dispensingManName}>{record.dispensingManName}</div>
                                                    </td>
                                                }
                                                {
                                                    modelType==='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.approveManRealName}>{record.approveManRealName}</div>
                                                    </td>
                                                }
                                                {
                                                    modelType==='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.grantDrugManName}>{record.grantDrugManName}</div>
                                                    </td>
                                                }
                                                {
                                                    modelType==='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.repeatApproveManRealName}>{record.repeatApproveManRealName}</div>
                                                    </td>
                                                }
                                                <td>
                                                    <div className="td-cont" title={record.prescriptionDateString}>{record.prescriptionDateString}</div>
                                                </td>
                                                {
                                                    modelType!=='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.createDateString}>{record.createDateString}</div>
                                                    </td>
                                                }
                                                {
                                                    modelType==='prescriptionRecord' &&
                                                    <td>
                                                        <div className="td-cont" title={record.dispensingDateString}>{record.dispensingDateString}</div>
                                                    </td>
                                                }
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                        {
                                                            //处方登记、待调剂
                                                            modelType==='prescriptionRegister' && record.prescriptionRegisterState==='WAIT_DISPENSING' &&
                                                            <Imalla href="javascript:void(0);" permissionCode="sales:prescription:prescriptionRegister:update" className="gray-btn" text="修改" onClick={() => actions.showEditForm(true, record.id)}  />
                                                        }
                                                        {
                                                            //处方登记
                                                            modelType==='prescriptionRegister' &&
                                                            <Imalla href="javascript:void(0);" permissionCode="sales:prescription:prescriptionRegister:detail" className="gray-btn" text="查看" onClick={() => actions.showDetail(true, record.id)}  />
                                                        }
                                                        {
                                                            //处方调剂、待调剂
                                                            modelType==='prescriptionDispensing' && record.prescriptionRegisterState==='WAIT_DISPENSING' &&
                                                            <Imalla href="javascript:void(0);" permissionCode="sales:prescription:prescriptionDispensing:dispensing" className="gray-btn" text="调剂" onClick={() => actions.showDispensingForm(true, record.id)}  />
                                                        }
                                                        {
                                                            //处方调剂
                                                            modelType==='prescriptionDispensing' &&
                                                            <Imalla href="javascript:void(0);" permissionCode="sales:prescription:prescriptionDispensing:detail" className="gray-btn" text="查看" onClick={() => actions.showDetail(true, record.id)}  />
                                                        }
                                                        {
                                                            //处方记录
                                                            modelType==='prescriptionRecord' &&
                                                            <Imalla href="javascript:void(0);" permissionCode="sales:prescription:prescriptionRecord:detail" className="gray-btn" text="查看" onClick={() => actions.showDetail(true, record.id)}  />
                                                        }
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

PrescriptionList.propTypes = {
    actions: PropTypes.object.isRequired,
    prescriptionRegisterState: PropTypes.string.isRequired,
    modelType: PropTypes.string.isRequired
};

PrescriptionList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PrescriptionList);