import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugClearBucketSearchForm from "./DrugClearBucketSearchForm";
import DrugClearBucketDetail from "./DrugClearBucketDetail";
import DrugClearAddForm from "./DrugClearAddForm";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
/**
 * 药品清斗列表
 */
class DrugClearBucketList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugClearBucketList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:zyyp:drugClearBucket:add','stock:zyyp:drugClearBucket:detail']);
    }

    componentDidMount() {
        $("#root").css("min-width", "2200px");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0 , sizePerPage:sizePerPage}));
        this.props.actions.drugClearBucketList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page - 1,sizePerPage: sizePerPage}));
        this.props.actions.drugClearBucketList(params, page - 1, sizePerPage);
    }

    showDrugClearAddFormAndInitDate(){
        this.props.actions.drugClearBucketAddModel(true);

    }

    saveAddForm(data){
        const {loginUserMessage,params} = this.context.store.getState().todos;
        const saveDataObject = {
            approveManId: data.approveDataId,
            clearBucketTimeString:data.clearBucketTimeString,
            clearBucketManName:loginUserMessage.realName,
            shopId:loginUserMessage.shopId,
            drugClearBucketGoodsSaveVoList: data.goodsList
        };
        this.props.actions.saveData(saveDataObject,params);
        return this.props.actions.fingApproveValidateData({});

    }

    render() {
        const {store} = this.context;
        const {addState,page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const drugClearBucketList = store.getState().todos.page.content || [];
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
                {addState&&<DrugClearAddForm actions={actions}  onSubmit={(data) => {this.saveAddForm(data)}}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>药品清斗</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">中药饮片</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品清斗</a>
                            <DrugClearBucketSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:zyyp:drugClearBucket:add" className="added" text="添加" onClick={()=>this.showDrugClearAddFormAndInitDate()}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width: "120px"}}>商品编码</th>
                                    <th className="commodity-name" style={{width:"300px"}}>商品名称</th>
                                    <th className="common-name" style={{width:"300px"}}>通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage-form">剂型</th>
                                    <th className="unit">单位</th>
                                    <th className="manufacturers" style={{width:"250px"}}>生产厂商</th>
                                    <th className="approval-number" style={{width:"300px"}}>批准文号</th>
                                    <th className="origin" style={{width:"120px"}}>产地</th>
                                    <th className="batch-number" style={{width:"150px"}}>批号</th>
                                    <th className="time" style={{width:"136px"}}>生产日期</th>
                                    <th className="time" style={{width:"136px"}}>有效期至</th>
                                    <th className="cargo-location" style={{width:"150px"}}>货位</th>
                                    <th className="quality">质量状况</th>
                                    <th className="name">清斗人</th>
                                    <th className="inventory">清斗数量</th>
                                    <th className="time" style={{width:"120px"}}>清斗日期</th>
                                    <th className="name">审核人</th>
                                    <th className="operating" style={{minWidth:"100px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { drugClearBucketList.length <= 0 &&
                                <tr >
                                    <th colSpan="19" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {drugClearBucketList.map((drugClearBucket, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.commonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugClearBucket.dosageForm || "暂无"}>{drugClearBucket.dosageForm || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugClearBucket.approvalNumber || "暂无"}>{drugClearBucket.approvalNumber || "暂无" }</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugClearBucket.productionPlace || "暂无"}>{drugClearBucket.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.produceDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.validDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.storageSpaceNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.isQualityQualified === "Y" ? "合格" : "不合格"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.clearBucketManName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.quantity}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.clearBucketTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugClearBucket.approveManNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{paddingRight:"20px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:zyyp:drugClearBucket:detail" className="gray-btn" text="查看" onClick={()=>this.props.actions.drugClearBucketDetailModal(true,drugClearBucket.id)}>查看</ImallA>
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
                    {store.getState().todos.drugClearBucketDetailState && <DrugClearBucketDetail store={store} actions={this.props.actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

DrugClearBucketList.propTypes = {};

DrugClearBucketList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugClearBucketList);