import React, {Component, PropTypes} from "react";
import SupplierQualityApproveForm from "./SupplierQualityApproveForm";
import SupplierQualityApproveDetailForm from "./SupplierQualityApproveDetailForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import SupplierQualityApproveSearchForm from "./SupplierQualityApproveSearchForm";
import IMallPaginationBar from '../../../../../../common/imallpagination/components/IMallPaginationBar';
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';
import ImallA from '../../../../../../common/imallbutton/components/ImallA';
class SupplierQualityApproveList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.supplierList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'quality:firstManage:supplierQuality:approve',
            'quality:firstManage:supplierQuality:detail',
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

    /**
     * 弹出 启动/禁用 窗口
     * 初始化 表单
     * @param supplier 供应商信息
     */
    changeApproveFormStateAndInitValidForm(id){
        this.props.actions.findFirstManageSupplierQualityApproveInf(id);

    }
    saveApproveForm(data){
        const {approveFormStateObject,approveUserNames,params} = this.context.store.getState().todos;
        data=Object.assign({},data,{
            firstSupplierDrugQualityApproveId:approveFormStateObject.id,
            shopId:approveFormStateObject.id,
            supplierId:approveFormStateObject.supplierId
        })
       this.props.actions.updateApproveFormState(approveUserNames,data,params);

    }

    render() {
        const actions = this.props.actions;
        const {showDetail,showApproveFormState} = this.props.actions;
        const {store} = this.context;
        const todos = store.getState().todos;
        const supplierList =todos.page.content || [];
        const detailObject =todos.detailObject || {};
        const detailState =todos.detailState ||"";
        const {approveFormState, approveFormStateObject} = todos;


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

        const fetchInfo = {
            dataTotalSize: page.totalElements
        };

        return (
            <div className="main-box">

                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>首营供应商质量审核</h5>
                            <a  href="javascript:void(0);" >质量管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >首营审核</a>
                            <span>></span>
                            <a href="javascript:void(0);" >首营供应商质量审核</a>

                            <SupplierQualityApproveSearchForm store={store} actions={this.props.actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width: "9.2%"}}>编码</th>
                                    <th className="supplier-name">供应商名称</th>
                                    <th className="name">法定代表人</th>
                                    <th className="name">质量负责人</th>
                                    <th className="name">业务负责人</th>
                                    <th className="tr-number">税务登记证号</th>
                                    <th className="bl-number" style={{width:"135px"}}>营业执照号</th>
                                    <th className="time">营业执照有效期</th>
                                    <th className="status">审核状态</th>
                                    <th className="time">质量审核时间</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { supplierList.length <= 0 &&
                                <tr >
                                    <th colSpan="11" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {supplierList.map((supplier, index) => {
                                        return (<tr key={index}>
                                                <td><div className="td-cont" title={supplier.supplierCode}>{supplier.supplierCode}</div></td>
                                                <td><div className="td-cont" title={supplier.supplierNm}>{supplier.supplierNm}</div></td>
                                                <td><div className="td-cont" title={supplier.legalRepresentative}>{supplier.legalRepresentative}</div></td>
                                                <td><div className="td-cont" title={supplier.qualityResponseManName}>{supplier.qualityResponseManName}</div></td>
                                                <td><div className="td-cont" title={supplier.businessResponseManName}>{supplier.businessResponseManName}</div></td>
                                                <td><div className="td-cont" title={supplier.taxRegisterCertNum}>{supplier.taxRegisterCertNum}</div></td>
                                                <td><div className="td-cont" title={supplier.businessLicense}>{supplier.businessLicense}</div></td>
                                                <td><div className="td-cont" title={supplier.businessLicenseTimeString}>{supplier.businessLicenseTimeString}</div></td>
                                                <td><div className="td-cont" title={supplier.approveStateName}>{supplier.approveStateName}</div></td>
                                                <td><div className="td-cont" title={supplier.qualityApproveTimeString}>{supplier.qualityApproveTimeString}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                        <ImallA  text="查看" permissionCode="quality:firstManage:supplierQuality:detail" href="javascript:void(0);"  className="gray-btn"onClick={()=>{showDetail(true,supplier.id)}}/>
                                                        {supplier.approveStateCode =="WAIT_APPROVE"&&
                                                        <ImallA  text="审核" permissionCode="quality:firstManage:supplierQuality:approve"  href="javascript:void(0);"  className="gray-btn" onClick={()=> this.changeApproveFormStateAndInitValidForm(supplier.id)}/>
                                                        }
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
                    {approveFormState&& <SupplierQualityApproveForm   approveFormStateObject={approveFormStateObject} showApproveFormState={showApproveFormState} actions={actions}  onSubmit={(data) => this.saveApproveForm(data)}/>}
                    {detailState&&<SupplierQualityApproveDetailForm    detailObject={detailObject}   store={store} actions={actions} />}
                    </div>
            </div>
        )
    }
}


SupplierQualityApproveList.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SupplierQualityApproveList);