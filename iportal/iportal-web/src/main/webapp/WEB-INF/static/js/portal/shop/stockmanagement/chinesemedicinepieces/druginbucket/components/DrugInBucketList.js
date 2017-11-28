import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugInBucketSearchForm from "./DrugInBucketSearchForm";
import DrugInBucketDetail from "./DrugInBucketDetail";
import DrugInAddForm from "./DrugInAddForm";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
/**
 * 药品装斗列表
 */
class DrugInBucketList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugInBucketList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:zyyp:drugInBucket:add','stock:zyyp:drugInBucket:detail']);
    }

    componentDidMount() {
        $("#root").css("min-width", "2200px");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0, sizePerPage:sizePerPage}));
        this.props.actions.drugInBucketList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page - 1, sizePerPage:sizePerPage}));

        this.props.actions.drugInBucketList(params, page - 1, sizePerPage);
    }

    showDrugInAddFormAndInitDate(){
        this.props.actions.drugInBucketAddModel(true);

    }
    addFormSave(data){
        const {loginUserMessage,params} = this.context.store.getState().todos;
        const saveDataObject = {
            approveManId: data.approveDataId,
            inBucketTimeString:data.inBucketTimeString,
            inBucketManName:loginUserMessage.realName,
            shopId:loginUserMessage.shopId,
            drugInBucketGoodsSaveVoList: data.goodsList
        };
        return this.props.actions.saveData(saveDataObject,params);

    }

    render() {
        const {store} = this.context;
        const {addState,page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const drugInBucketList = store.getState().todos.page.content || [];
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
                {addState&&<DrugInAddForm actions={actions}  onSubmit={(data) => {this.addFormSave(data)}}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>药品装斗</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">中药饮片</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品装斗</a>
                            <DrugInBucketSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:zyyp:drugInBucket:add" className="added" text="添加" onClick={()=> this.showDrugInAddFormAndInitDate()}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width: "120px"}}>商品编码</th>
                                    <th className="commodity-name" style={{width:"250"}} >商品名称</th>
                                    <th className="common-name" style={{width:"250"}}>通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage-form">剂型</th>
                                    <th className="unit">单位</th>
                                    <th className="manufacturers" style={{width:"250px"}}>生产厂商</th>
                                    <th className="approval-number" style={{width:"200"}}>批准文号</th>
                                    <th className="origin" style={{width:"120px"}}>产地</th>
                                    <th className="batch-number" style={{width:"150"}}>批号</th>
                                    <th className="time" style={{width:"136"}}>生产日期</th>
                                    <th className="time" style={{width:"136"}}>有效期至</th>
                                    <th className="cargo-location" style={{width:"200"}}>货位</th>
                                    <th className="quality">质量状况</th>
                                    <th className="name" style={{width:"120px"}}>装斗人</th>
                                    <th className="inventory">装斗数量</th>
                                    <th className="time" style={{width:"136px"}}>装斗日期</th>
                                    <th className="name" style={{width:"120px"}}>审核人</th>
                                    <th className="operating" style={{minWidth:"100px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { drugInBucketList.length <= 0 &&
                                <tr >
                                    <th colSpan="100" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {drugInBucketList.map((drugInBucket, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont">{drugInBucket.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.commonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugInBucket.dosageForm || "暂无"}>{drugInBucket.dosageForm || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugInBucket.approvalNumber || "暂无"}>{drugInBucket.approvalNumber || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={drugInBucket.productionPlace || "暂无"}>{drugInBucket.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.produceDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.validDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.storageSpaceNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.isQualityQualified === "Y" ? "合格" : "不合格"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.inBucketManName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.quantity}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.inBucketTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont">{drugInBucket.approveManNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{paddingRight:"20px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:zyyp:drugInBucket:detail" className="gray-btn" text="查看" onClick={()=>this.props.actions.drugInBucketDetailModal(true,drugInBucket.id)}>查看</ImallA>
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
                    {store.getState().todos.drugInBucketDetailState && <DrugInBucketDetail store={store} actions={this.props.actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

DrugInBucketList.propTypes = {};

DrugInBucketList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugInBucketList);