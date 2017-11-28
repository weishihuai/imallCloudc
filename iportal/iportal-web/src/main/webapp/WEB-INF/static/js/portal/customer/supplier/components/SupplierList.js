import React, {Component, PropTypes} from "react";
import SupplierEnableUpdateForm from "./SupplierEnableUpdateForm";
import SupplierEditForm from "./SupplierEditForm";
import SupplierAddForm from "./SupplierAddForm";
import SupplierDetailForm from "./SupplierDetailForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import SupplierSearchForm from "./SupplierSearchForm";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import ImallA from "../../../../common/imallbutton/components/ImallA";
import FileMgrModalComponent from "../../../../common/filemgr/components/FileMgrModalComponent";
import {showFileMgrModalHasCallbackFunc} from "../../../../common/filemgr/actions";
import {niftyNoty} from "../../../../common/common";
class SupplierList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.supplierList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'customer:supplier:add',
            'customer:supplier:update',
            'customer:supplier:enable',
            'customer:supplier:detail',
            'customer:supplier:import',
            'customer:supplier:export',
            'customer:supplier:download'
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
        // 获取资质文件
        const supplierCertificatesFileList=this.context.store.getState().todos.supplierCertificatesFileList;
        const params=this.context.store.getState().todos.params;
        data = Object.assign({}, data, {
            supplierCertificatesFileList: supplierCertificatesFileList
        });
        // 获取上传文件
        const imgList =this.context.store.getState().todos.fileMngs;
        const fileMngs = [];

        for ( let i = 0; i < imgList.length; i++) {
            const fileMng = {

                objectTypeCode:"",
                objectId:"",
                fileTypeCode:"",
                customTypeCode:"",
                sysFileLibId: imgList[i].sysFileLibId,
                fileId: imgList[i].fileId
            }
            fileMngs.push(fileMng);
        }
        data=Object.assign({},data,{
            fileMngs: fileMngs
        });
        return this.props.actions.saveAndUpdateData(data,state,params);
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
                            <SupplierSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA  href="javascript:void(0);"  permissionCode="customer:supplier:add"  className="added" text="添加" onClick={()=>{showAddForm(true)}}/>
                            <ImallA text="导入" permissionCode="customer:supplier:import"   href="javascript:void(0);"  className="guide" onClick={(e)=>this.importExcel()}/>
                            <ImallA text="导出" permissionCode="customer:supplier:export"   href="javascript:void(0);"  className="guide" onClick={(e)=>this.exportExcel()}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="customer:supplier:download" text="下载模板" className="guide" onClick={(e) => window.open(iportal + "/docInfo/supplierModule.xls")}/>
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
                                    <th className="status">启用状态</th>
                                    <th className="status">状态</th>
                                    <th className="time">创建时间</th>
                                    <th className="operating" style={{width: "250px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { supplierList.length <= 0 &&
                                <tr >
                                    <th colSpan="9" style={{textAlign: "center"}}>
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
                                            <td><div className="td-cont">{supplier.approveStateName}</div></td>
                                            <td><div className="td-cont">{supplier.createTimeString}</div></td>
                                            <td>
                                                <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                    <ImallA text="查看" permissionCode="customer:supplier:detail"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{showDetail(true,supplier.id)}}/>
                                                    <ImallA text="修改" permissionCode="customer:supplier:update"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{showUpdate(true,supplier.id)}}/>
                                                    <ImallA text="启用/禁用" permissionCode="customer:supplier:enable"  href="javascript:void(0);"  className="gray-btn" onClick={()=> this.changeIsEnableStateAndInitValidForm(supplier)}/>
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
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                    {isEnable&& <SupplierEnableUpdateForm   isEnableObject={isEnableObject} showIsEnableState={showIsEnableState} actions={actions}  onSubmit={(data) => updateSupplierEnable(data,params)}/>}
                    {detailState&&<SupplierDetailForm    detailObject={detailObject}   store={store} actions={actions} />}
                    {editState&&<SupplierEditForm  editState={editState}    actions={actions} onSubmit={(data) => this.submitData(data)}/>}
                    {addState&&<SupplierAddForm store={store} actions={actions} onSubmit={(data) => this.submitData(data,true)}/>}
                </div>
            </div>
        )
    }
}


SupplierList.contextTypes = {
    store: React.PropTypes.object
};


function mapDispatchToProps(dispatch){
    return bindActionCreators({portalOperationalAuth, showFileMgrModalHasCallbackFunc}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SupplierList);