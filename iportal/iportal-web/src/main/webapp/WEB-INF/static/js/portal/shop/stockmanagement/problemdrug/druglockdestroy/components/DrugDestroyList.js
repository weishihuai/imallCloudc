import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugDestroySearchForm from "./DrugDestroySearchForm";
import DrugLockDestroyForm from "./DrugLockDestroyForm";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";

/**
 * 药品销毁列表
 */
class DrugDestroyList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugDestroyList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:problem:drugLockDestroy:destroy']);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.drugDestroyList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.drugDestroyList(params, page - 1, sizePerPage);
    }

    //销毁提交
    drugLockDestroyData(data,params){
        return this.props.actions.drugLockDestroyData(data,this.context.store.getState().todos.id,params);
    }

    render() {
        const {store} = this.context;
        const {page,params} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const drugDestroyList = store.getState().todos.page.content || [];
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
                            <h5>药品销毁</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">问题药品</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品销毁</a>
                            <DrugDestroySearchForm actions={actions} store={store}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width: "120px"}}>商品编码</th>
                                    <th className="th-title" style={{width: "250px"}}>商品名称</th>
                                    <th className="common-name" style={{width: "200px"}}>通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturers" style={{width: "200px"}}>生产厂商</th>
                                    <th className="origin">产地</th>
                                    <th className="batch-number" style={{width: "200px"}}>批准文号</th>
                                    <th className="batch-number" style={{width: "120px"}}>批号</th>
                                    <th className="cargo-location" style={{width: "160px"}}>货位</th>
                                    <th className="inventory" style={{width: "120px"}}>待销毁数量</th>
                                    <th className="supplier-name" style={{width: "200px"}}>供应商名称</th>
                                    <th className="th-operating" style={{paddingLeft:"50px", minWidth: "120px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { drugDestroyList.length <= 0 &&
                                <tr >
                                    <th colSpan="14" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {drugDestroyList.map((drugDestroy, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.goodsCode}>{drugDestroy.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.goodsNm}>{drugDestroy.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.commonNm}>{drugDestroy.commonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.spec}>{drugDestroy.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.dosageForm}>{drugDestroy.dosageForm || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.unit}>{drugDestroy.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.produceManufacturer}>{drugDestroy.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.productionPlace}>{drugDestroy.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.approvalNumber}>{drugDestroy.approvalNumber || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.batch}>{drugDestroy.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.storageSpaceNm}>{drugDestroy.storageSpaceNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.lockQuantity}>{drugDestroy.lockQuantity}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugDestroy.supplierNm}>{drugDestroy.supplierNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{paddingRight:"65px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:problem:drugLockDestroy:destroy" text="销毁" className="gray-btn"  onClick={() => actions.drugLockDestroyModel(true,drugDestroy.id,drugDestroy)}>销毁</ImallA>
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
                    <IMallPaginationBar actions={actions} options={options}/>
                    {store.getState().todos.destroyState && <DrugLockDestroyForm store={store} actions={actions} onSubmit={(data) => this.drugLockDestroyData(data ,params)}/>}
                </div>
            </div>
        )

    }
}

DrugDestroyList.propTypes = {};

DrugDestroyList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugDestroyList);