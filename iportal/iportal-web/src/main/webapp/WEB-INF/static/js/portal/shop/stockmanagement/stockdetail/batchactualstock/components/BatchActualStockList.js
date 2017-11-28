import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {BATCH_ACTUAL_STOCK_SEARCH_PARAM_CHANGE} from "../constants/ActionTypes";

/**
 * 实时库存（批次）
 */
class BatchActualStockList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.batchActualStockList(params.page, params.size, params);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.batchActualStockList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.batchActualStockList(page - 1, sizePerPage, params);
    }

    handleSearch() {
        const {store} = this.context;
        const newParam = {
            page: 0,
            size: 10,
            searchFields: this.refs.searchFields.value,
            batch: this.refs.batch.value,
        };
        store.dispatch({
            type: BATCH_ACTUAL_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.batchActualStockList(0, 10, newParam);
    };
    componentDidMount() {
        $("#root").css("min-width", "2300px");
    }

    /*重置搜索参数*/
    onResetSearchParam() {

        this.refs.searchFields.value = "";
        this.refs.batch.value = "";

        const newParam = {
            searchFields: "",
            batch: "",
        };

        this.context.store.dispatch({
            type: BATCH_ACTUAL_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });
        this.props.actions.batchActualStockList(0, 10, newParam);
    }

    batchStateFormat(batchState) {
        switch (batchState) {
            case "NORMAL":
                return "正常";
            case "LOCK":
                return "锁定";
            case "WAIT_DESTROY":
                return "待销毁";
            case "STOP_SALE":
                return "停售";
            case "OVERDUE":
                return "过期";
            case "DELETE":
                return "已销毁";
        }
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const batchActualStockList = store.getState().todos.page.content || [];
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
                            <h5>实时库存（批次）</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存明细</a>
                            <span>></span>
                            <a href="javascript:void(0);">实时库存（批号）</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input className="batch" placeholder="拼音码|名称|编码" ref="searchFields" type="text"/>
                                </div>
                                <div className="search">
                                    <input className="batch" placeholder="批号" ref="batch" type="text"/>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" onClick={() => this.handleSearch()} value="查询"/>
                                    <input className="sr-reset" type="button" onClick={() => this.onResetSearchParam()} value="重置"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="serial-number">序号</th>
                                        <th className="th-coding" style={{width: "120px"}}>商品编码</th>
                                        <th className="commodity-name"  style={{width: "250px"}}>商品名称</th>
                                        <th className="common-name"  style={{width: "250px"}}>通用名称</th>
                                        <th className="standard" style={{width: "100px"}}>规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="inventory">库存数量</th>
                                        <th className="retail-price">零售单价</th>
                                        <th className="retail-price">采购价格</th>
                                        <th className="manufacturers" style={{width:"200px"}}>供应商名称</th>
                                        <th className="manufacturers" style={{width: "200px"}}>生产厂商</th>
                                        <th className="approval-number" style={{width: "200px"}}>批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="batch-number" style={{width:"120px"}}>批号</th>
                                        <th className="time">生产日期</th>
                                        <th className="time">有效期至</th>
                                        <th className="time">批次状态</th>
                                        <th className="cargo-location" style={{width:"120px"}}>货位</th>
                                    </tr>
                                </thead>
                                {
                                    batchActualStockList.length <= 0 &&
                                    <tr>
                                        <td colSpan="100" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                }
                                <tbody>
                                {
                                    batchActualStockList.map((item, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont">{index+1}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.goodsCode}>{item.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.goodsNm}>{item.goodsNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.commonNm}>{item.commonNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.spec}>{item.spec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.dosageForm}>{item.dosageForm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.unit}>{item.unit}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.currentStock}>{item.currentStock}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.retailPrice}>{item.retailPrice}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.purchasePrice}>{item.purchasePrice}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.supplierNm}>{item.supplierNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.produceManufacturer}>{item.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.approvalNumber}>{item.approvalNumber}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.productionPlace}>{item.productionPlace}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.batch}>{item.batch}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.produceDateString}>{item.produceDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.validDateString}>{item.validDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={this.batchStateFormat(item.batchState)}>{this.batchStateFormat(item.batchState)}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.storageSpaceNm}>{item.storageSpaceNm}</div>
                                                </td>
                                            </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

BatchActualStockList.propTypes = {};

BatchActualStockList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(BatchActualStockList);