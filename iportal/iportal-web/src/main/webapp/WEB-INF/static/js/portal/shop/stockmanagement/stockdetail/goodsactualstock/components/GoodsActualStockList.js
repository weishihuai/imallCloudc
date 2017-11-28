import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {GOODS_ACTUAL_STOCK_SEARCH_PARAM_CHANGE} from "../constants/ActionTypes";

/**
 * 进销存列表
 */
class GooodsActualStockList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.gooodsActualStockList(params.page, params.size, params);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.gooodsActualStockList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.gooodsActualStockList(page - 1, sizePerPage, params);
    }

    handleSearch() {
        const {store} = this.context;
        const newParam = {
            page: 0,
            size: 10,
            searchFields: this.refs.searchFields.value,
        };
        store.dispatch({
            type: GOODS_ACTUAL_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.gooodsActualStockList(0, 10, newParam);
    };


    /*重置搜索参数*/
    onResetSearchParam() {

        this.refs.searchFields.value = "";

        const newParam = {
            searchFields: ""
        };

        this.context.store.dispatch({
            type: GOODS_ACTUAL_STOCK_SEARCH_PARAM_CHANGE,
            data: newParam
        });
        this.props.actions.gooodsActualStockList(0, 10, newParam);
    }


    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const gooodsActualStockList = store.getState().todos.page.content || [];
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
                            <h5>实时库存（商品）</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存明细</a>
                            <span>></span>
                            <a href="javascript:void(0);">实时库存（商品）</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input className="batch" placeholder="拼音码|名称|编码" ref="searchFields" type="text"/>
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
                                        <th className="th-coding" >商品编码</th>
                                        <th className="commodity-name">商品名称</th>
                                        <th className="common-name">通用名称</th>
                                        <th className="standard">规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="inventory">库存数量</th>
                                        <th className="retail-price">零售单价</th>
                                        <th className="manufacturers">生产厂商</th>
                                        <th className="approval-number" style={{width: "200px"}}>批准文号</th>
                                        <th className="origin">产地</th>
                                    </tr>
                                </thead>
                                {
                                    gooodsActualStockList.length <= 0 &&
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
                                    gooodsActualStockList.map((item, index) => {
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
                                                    <div className="td-cont" title={item.produceManufacturer}>{item.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.approvalNumber}>{item.approvalNumber}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.productionPlace}>{item.productionPlace}</div>
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

GooodsActualStockList.propTypes = {};

GooodsActualStockList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GooodsActualStockList);