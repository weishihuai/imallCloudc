import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugLockDealSearchForm from "./DrugLockDealSearchForm";
import DrugLockDealAddForm from "./DrugLockDealAddForm";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";

/**
 * 药品处理列表
 */
class DrugLockDealList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugLockDealList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:problem:drugLockDeal:deal']);
    }

    componentDidMount() {
        $("#root").css("min-width", "2100px");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.drugLockDealList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.drugLockDealList(params, page - 1, sizePerPage);
    }

    submitData(data){
        const {store} = this.context;
        const {params} = store.getState().todos;
        const saveDataObject = {
            approveManId: data.approveDataId,
            processTimeString:data.processTimeString,
            drugLockDealGoodsVoList: data.goodsList,
            processReason:data.processReason,
            processResultCode:$('#processResultCode').val()
        };
       return this.props.actions.updateDrugLockDeal(saveDataObject,params);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const drugLockDealList = store.getState().todos.page.content || [];
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
                            <h5>药品处理</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">问题药品</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品处理</a>
                            <DrugLockDealSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:problem:drugLockDeal:deal" text="添加" className="added" onClick={()=>this.props.actions.drugLockDealAddModel(true)}>添加</ImallA>
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
                                    <th className="standard" style={{width:"100px"}}>规格</th>
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
                                { drugLockDealList.length <= 0 &&
                                <tr >
                                    <th colSpan="18" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {drugLockDealList.map((drugLockDeal, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.lockManName}>{drugLockDeal.lockManName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.lockTimeString}>{drugLockDeal.lockTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.goodsCode}>{drugLockDeal.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.goodsNm}>{drugLockDeal.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.commonNm}>{drugLockDeal.commonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.spec}>{drugLockDeal.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.dosageForm}>{drugLockDeal.dosageForm || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.unit}>{drugLockDeal.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.produceManufacturer}>{drugLockDeal.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.productionPlace}>{drugLockDeal.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.approvalNumber}>{drugLockDeal.approvalNumber || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.batch}>{drugLockDeal.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.currentStock}>{drugLockDeal.currentStock}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.lockQuantity}>{drugLockDeal.lockQuantity}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.lockReason}>{drugLockDeal.lockReason || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.produceDateString}>{drugLockDeal.produceDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.validDateString}>{drugLockDeal.validDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugLockDeal.storageSpaceNm}>{drugLockDeal.storageSpaceNm}</div>
                                    </td>
                                </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {store.getState().todos.addState && <DrugLockDealAddForm actions={actions} options={options} onSubmit={(data) => this.submitData(data)}/>}
                    <IMallPaginationBar actions={actions} options={options}/>
                </div>
            </div>
        )
    }
}

DrugLockDealList.propTypes = {};

DrugLockDealList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugLockDealList);