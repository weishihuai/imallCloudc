import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugLockSearchForm from "./DrugLockSearchForm";
import DrugLockAddForm from "./DrugLockAddForm";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";

/**
 * 药品锁定列表
 */
class DrugLockList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugLockList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:problem:drugLock:add']);
    }

    componentDidMount() {
        $("#root").css("min-width", "2100px");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.drugLockList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.drugLockList(params, page - 1, sizePerPage);
    }

    drugLockSaveSubmitData(data) {
        const submitData = {
            lockManName:data.lockManName,
            drugLockGoodsSaveVoList: data.goodsList,
            lockTimeString:data.lockTimeString,
        };
        const {store} = this.context;
        const {params} = store.getState().todos;
        return this.props.actions.saveDrugLock(submitData,params);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const drugLockList = store.getState().todos.page.content || [];
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
                            <h5>药品锁定</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">问题药品</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品锁定</a>
                            <DrugLockSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:problem:drugLock:add" text="添加" className="added" onClick={()=>this.props.actions.drugLockAddModel(true)}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="name">锁定人</th>
                                    <th className="time" style={{width:"120px"}}>锁定时间</th>
                                    <th className="th-coding" style={{width:"120px"}}>商品编码</th>
                                    <th className="commodity-name" style={{width:"250px"}}>商品名称</th>
                                    <th className="common-name" style={{width:"250px"}}>通用名称</th>
                                    <th className="standard" style={{width:"110px"}}>规格</th>
                                    <th className="dosage-form">剂型</th>
                                    <th className="unit">单位</th>
                                    <th className="manufacturers" style={{width:"250px"}}>生产厂商</th>
                                    <th className="origin">产地</th>
                                    <th className="approval-number" style={{width:"200px"}}>批准文号</th>
                                    <th className="batch-number" style={{width:"120px"}}>批号</th>
                                    <th className="inventory">库存数量</th>
                                    <th className="inventory">锁定数量</th>
                                    <th className="reason">锁定原因</th>
                                    <th className="time" style={{width:"120px"}}>生产日期</th>
                                    <th className="time" style={{width:"120px"}}>有效期至</th>
                                    <th className="cargo-location" style={{width:"150px"}}>货位</th>
                                </tr>
                                </thead>
                                <tbody>
                                { drugLockList.length <= 0 &&
                                <tr >
                                    <th colSpan="18" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {drugLockList.map((drugLock, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={drugLock.lockManName}>{drugLock.lockManName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.lockTimeString}>{drugLock.lockTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.goodsCode}>{drugLock.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.goodsNm}>{drugLock.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.commonNm}>{drugLock.commonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.spec}>{drugLock.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.dosageForm}>{drugLock.dosageForm || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.unit}>{drugLock.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.produceManufacturer}>{drugLock.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.productionPlace}>{drugLock.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.approvalNumber}>{drugLock.approvalNumber}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.batch}>{drugLock.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.currentStock}>{drugLock.currentStock}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.lockQuantity}>{drugLock.lockQuantity}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.lockReason}>{drugLock.lockReason || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.produceDateString}>{drugLock.produceDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.validDateString}>{drugLock.validDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLock.storageSpaceNm}>{drugLock.storageSpaceNm}</div>
                                    </td>
                                </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {store.getState().todos.addState && <DrugLockAddForm actions={actions} options={options} onSubmit={(data) => {this.drugLockSaveSubmitData(data)}}/>}
                    <IMallPaginationBar actions={actions} options={options}/>
                </div>
            </div>
        )
    }
}

DrugLockList.propTypes = {};

DrugLockList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugLockList);