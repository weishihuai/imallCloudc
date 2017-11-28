import React, {Component, PropTypes} from "react";
import SupplierDocEnableUpdateForm from "./SupplierDocEnableUpdateForm";
import SupplierDocEditForm from "./SupplierDocEditForm";
import SupplierDocAddForm from "./SupplierDocAddForm";
import SupplierDocDetailForm from "./SupplierDocDetailForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import SupplierDocSearchForm from "./SupplierDocSearchForm";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import {initValidForm} from "../../../../common/validForm/actions";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import ImallA from "../../../../common/imallbutton/components/ImallA";
import FileMgrModalComponent from "../../../../common/filemgr/components/FileMgrModalComponent";
import {showFileMgrModalHasCallbackFunc} from "../../../../common/filemgr/actions";
import {niftyNoty} from "../../../../common/common";

class SupplierDocList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.supplierList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'customer:portalSupplier:add',
            'customer:portalSupplier:update',
            'customer:portalSupplier:detail',
            'customer:portalSupplier:enable',
            'customer:portalSupplier:import',
            'customer:portalSupplier:export',
            'customer:portalSupplier:download'
        ]);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.supplierList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.supplierList(params, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    exportExcel(){
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.exportExcel(params);
    }

    importExcel() {

        this.props.showFileMgrModalHasCallbackFunc((files) => {
            if (files.length > 0) {
                const fileName = files[0].fileId.split(".")[1];
                if (fileName==="xls"||fileName==="xlsx"||fileName==="xlsm") {
                    this.props.actions.importExcel({fileId: files[0].fileId});
                    niftyNoty("导入成功！");
                } else {
                    niftyNoty("请选择正确的excel文件！");
                }
            }

        });
    }


    submitData(data,state=false){
        const {params} = this.context.store.getState().todos;
        // 获取资质文件
        const supplierDocCertificatesFileList=this.context.store.getState().todos.supplierDocCertificatesFileList;
        data = Object.assign({}, data, {
            supplierDocCertificatesFileList: supplierDocCertificatesFileList
        });
        this.props.actions.saveAndUpdateData(data,state,params);
    }

    /**
     * 弹出 启动/禁用 窗口
     * 初始化 表单
     * @param supplier 供应商信息
     */
    changeIsEnableStateAndInitValidForm(supplier){
        this.props.actions.showIsEnableState({
            id: supplier.id,
            state: supplier.state,
            supplierNm: supplier.supplierNm
        });
        this.props.initValidForm();
    }

    render() {
        const actions = this.props.actions;
        const {showAddForm,showUpdate,showDetail,showIsEnableState,updateSupplierEnable} = this.props.actions;
        const {store} = this.context;
        const todos = store.getState().todos;
        const supplierList =todos.page.content || [];
        const detailObject =todos.detailObject || {};
        const detailState =todos.detailState ||"";
        const editState =todos.editState ||"";
        const addState =todos.addState ;
        const {isEnable, isEnableObject,params} = todos;
        const display = store.getState().fileMgrTodos.fileMgrModalState;

        const page = store.getState().todos.page;
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
            paginationShowsTotal: page.totalElements > page.size ? this.renderShowsTotal : null,
            hideSizePerPage: page.totalElements <= page.size
        };


        return (
            <div className="main-box">
                {display&& <FileMgrModalComponent store={store} actions={this.props.actions} />}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>供应商管理</h5>
                            <a  href="javascript:void(0);" >客户管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >供应商管理</a>
                            <SupplierDocSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA text="添加" permissionCode="customer:portalSupplier:add"  href="javascript:void(0);"  className="added" onClick={()=>{showAddForm(true)}}/>
                            <ImallA text="导入" permissionCode="customer:portalSupplier:import"   href="javascript:void(0);"  className="guide" onClick={(e)=>this.importExcel()}/>
                            <ImallA text="导出" permissionCode="customer:portalSupplier:export"   href="javascript:void(0);"  className="guide" onClick={(e)=>this.exportExcel()}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="customer:portalSupplier:download" text="下载模板" className="guide" onClick={(e) => window.open(iportal + "/docInfo/supplierDocModule.xls")}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"118"}}>供应商编码</th>
                                    <th className="manufacturer" style={{width:"260"}}>供应商名称</th>
                                    <th className="status">供应商单位性质</th>
                                    <th className="statutory">业务负责人</th>
                                    <th className="phone">业务负责人电话</th>
                                    <th className="status">审核状态</th>
                                    <th className="time">创建时间</th>
                                    <th className="operating" style={{width: "250px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { supplierList.length <= 0 &&
                                <tr >
                                    <th colSpan="8" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {supplierList.map((supplier, index) => {
                                        return (<tr key={index}>
                                                <td><div className="td-cont">{supplier.supplierCode}</div></td>
                                                <td><div className="td-cont">{supplier.supplierNm}</div></td>
                                                <td><div className="td-cont">{supplier.unitNatureName}</div></td>
                                                <td><div className="td-cont">{supplier.businessResponseManName}</div></td>
                                                <td><div className="td-cont">{supplier.businessResponseManTel}</div></td>
                                                <td><div className="td-cont">{supplier.state=="Y"?"启用":"禁用"}</div></td>
                                                <td><div className="td-cont">{supplier.createTimeString}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                        <ImallA text="查看" permissionCode="customer:portalSupplier:detail"   href="javascript:void(0);"  className="gray-btn"onClick={()=>{showDetail(true,supplier.id)}}/>
                                                        <ImallA text="修改" permissionCode="customer:portalSupplier:update"   href="javascript:void(0);"  className="gray-btn"onClick={()=>{showUpdate(true,supplier.id)}}/>
                                                        <ImallA text="启用/禁用" permissionCode="customer:portalSupplier:enable"  href="javascript:void(0);"  className="gray-btn" onClick={()=> this.changeIsEnableStateAndInitValidForm(supplier)}/>
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
                    <IMallPaginationBar options={options} actions={actions}/>
                    {isEnable&& <SupplierDocEnableUpdateForm   isEnableObject={isEnableObject} showIsEnableState={showIsEnableState} actions={actions}  onSubmit={(data) => updateSupplierEnable(data,params)}/>}
                    {detailState&&<SupplierDocDetailForm    detailObject={detailObject}   store={store} actions={actions} />}
                    {editState&&<SupplierDocEditForm  editState={editState}    actions={actions} onSubmit={(data) => this.submitData(data)}/>}
                    {addState&&<SupplierDocAddForm store={store} actions={actions} onSubmit={(data) => this.submitData(data,true)}/>}
                    </div>
            </div>
        )
    }
}


SupplierDocList.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({initValidForm,portalOperationalAuth, showFileMgrModalHasCallbackFunc}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SupplierDocList);