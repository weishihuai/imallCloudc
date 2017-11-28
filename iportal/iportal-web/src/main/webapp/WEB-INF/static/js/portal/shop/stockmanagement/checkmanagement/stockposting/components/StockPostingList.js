import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import StockPostingSearchForm from "./StockPostingSearchForm";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";
import {STOCK_POSTING_CONFIRM_POST_MODAL} from "../constants/ActionTypes";
import CommonConfirmComponent from "../../../../../../common/component/CommonConfirmComponent";

/**
 * 盘点过账列表
 */
class StockPostingList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.stockPostingList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:check:posting:posting']);
    }

    componentDidMount() {
        $("#root").css("min-width", "2200px");
    }

    onSizePerPageList(sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.stockPostingList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.stockPostingList(params, page - 1, sizePerPage);
    }



    stockCheckPostConfirmModel(isConfirmModelShow,id){
        this.context.store.dispatch({
            type : STOCK_POSTING_CONFIRM_POST_MODAL,
            isConfirmModelShow: isConfirmModelShow,
            id:id
        });
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const page = store.getState().todos.page;
        const params = store.getState().todos.params;
        const number = page.number + 1;
        const stockPostingList = store.getState().todos.page.content || [];
        let id = this.context.store.getState().todos.id;
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
                            <h5>盘点过账</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">盘点管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">盘点过账</a>
                            <StockPostingSearchForm store={store} actions={this.props.actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"120px"}}>商品编码</th>
                                    <th className="commodity-name" style={{width:"250px"}}>商品名称</th>
                                    <th className="common-name" style={{width:"250px"}}>通用名称</th>
                                    <th className="standard" style={{width:"150px"}}>规格</th>
                                    <th className="dosage-form">剂型</th>
                                    <th className="unit">单位</th>
                                    <th className="manufacturers" style={{width:"120px"}}>生产厂商</th>
                                    <th className="approval-number" style={{width:"200"}}>批准文号</th>
                                    <th className="origin" style={{width:"120px"}}>产地</th>
                                    <th className="batch-number" style={{width:"150"}}>批号</th>
                                    <th className="time" style={{width:"120px"}}>生产日期</th>
                                    <th className="time" style={{width:"120px"}}>有效期至</th>
                                    <th className="cargo-location" style={{width:"150px"}}>货位</th>
                                    <th className="inventory">库存数量</th>
                                    <th className="retail-price">库存金额</th>
                                    <th className="inventory">实盘数量</th>
                                    <th className="retail-price">实盘金额</th>
                                    <th className="inventory">损益数量</th>
                                    <th className="retail-price">损益金额</th>
                                    <th className="th-operating" style={{paddingRight:"35px",textAlign:"right"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { stockPostingList.length <= 0 &&
                                <tr >
                                    <th colSpan="20" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {stockPostingList.map((stockPosting, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={stockPosting.goodsCode}>{stockPosting.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.goodsNm}>{stockPosting.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.goodsCommonNm}>{stockPosting.goodsCommonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.spec}>{stockPosting.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.lossAmount || "暂无"}>{stockPosting.dosageForm  || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.unit}>{stockPosting.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.produceManufacturer}>{stockPosting.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.approvalNumber  || "暂无"}>{stockPosting.approvalNumber  || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.productionPlace || "暂无"}>{stockPosting.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.batch}>{stockPosting.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.produceDateString}>{stockPosting.produceDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.validDateString}>{stockPosting.validDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.storageSpaceNm}>{stockPosting.storageSpaceNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.currentStock}>{stockPosting.currentStock}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.stockPrice}>{stockPosting.stockPrice}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.realCheckQuantity || 0}>{stockPosting.realCheckQuantity || 0}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.realCheckAmount || 0}>{stockPosting.realCheckAmount || 0}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.lossQuantity || 0}>{stockPosting.lossQuantity || 0}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockPosting.lossAmount || 0}>{stockPosting.lossAmount || 0}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingRight": "20px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:check:posting:posting" text="过账" className="gray-btn" onClick={() => this.stockCheckPostConfirmModel(true,stockPosting.id)}>过账</ImallA>
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
                    {store.getState().todos.confirmModelState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="盘点过账" text={"是否过账?"} confirmBtn="确定" callback={(result) => actions.stockPosting(id,params)} close={() => this.stockCheckPostConfirmModel(false,null)}/>}
                    <IMallPaginationBar options={options} actions={actions}/>
                </div>
            </div>
        )
    }
}

StockPostingList.propTypes = {};

StockPostingList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StockPostingList);