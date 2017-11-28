import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import StockOutWarningSearchForm from "./StockOutWarningSearchForm"
/**
 * 缺货预警列表
 */
class StockOutWarningList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.stockOutWarningList(params, params.page, params.size);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page: 0, size : sizePerPage}));
        this.props.actions.stockOutWarningList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page : page - 1,size : sizePerPage}));
        this.props.actions.stockOutWarningList(params, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const stockOutWarningList = store.getState().todos.page.content || [];
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
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>缺货预警</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">预警管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">缺货预警</a>
                            <StockOutWarningSearchForm store={store} actions={actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="serial-number">序号</th>
                                    <th className="th-coding" style={{width: "120px"}}>商品编码</th>
                                    <th className="commodity-name" style={{width: "250px"}}>商品名称</th>
                                    <th className="common-name" style={{width: "250px"}}>通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage-form">剂型</th>
                                    <th className="unit">单位</th>
                                    <th className="manufacturers" style={{width: "250px"}}>生产厂商</th>
                                    <th className="approval-number" style={{width: "250px"}}>批准文号</th>
                                    <th className="origin">产地</th>
                                    <th className="inventory">库存数量</th>
                                    <th className="inventory">安全库存</th>
                                    <th className="retail-price">库存金额</th>
                                    <th className="inventory">缺货数量</th>
                                </tr>
                                </thead>
                                <tbody>
                                { stockOutWarningList.length <= 0 &&
                                <tr >
                                    <th colSpan="15" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {stockOutWarningList.map((stockOutWarning, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont">{index + 1}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.goodsCode}>{stockOutWarning.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.goodsNm}>{stockOutWarning.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.commonNm}>{stockOutWarning.commonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.spec}>{stockOutWarning.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.dosageForm}>{stockOutWarning.dosageForm || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.unit}>{stockOutWarning.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.produceManufacturer}>{stockOutWarning.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.approvalNumber}>{stockOutWarning.approvalNumber || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.productionPlace}>{stockOutWarning.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.currentStock}>{stockOutWarning.currentStock}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.securityStock}>{stockOutWarning.securityStock}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.stockMoney}>{stockOutWarning.stockMoney}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockOutWarning.outOfStockQuantity}>{stockOutWarning.outOfStockQuantity}</div>
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
                </div>
            </div>
        )
    }
}

StockOutWarningList.propTypes = {};

StockOutWarningList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StockOutWarningList);