import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import PurchaseSaleStockSearchForm from "./PurchaseSaleStockSearchForm";
import PurchaseSaleStockItem from "./PurchaseSaleStockItem";

/**
 * 进销存列表
 */
class PurchaseSaleStockList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.purchaseSaleStockList(params.page, params.size, params);
    }
    componentDidMount() {
        $("#root").css("min-width", "2300px");
    }
    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.purchaseSaleStockList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.purchaseSaleStockList(page - 1, sizePerPage, params);
    }
    
    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const purchaseSaleStockList = store.getState().todos.page.content || [];
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
                            <h5>进销存台账</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存明细</a>
                            <span>></span>
                            <a href="javascript:void(0);">进销存台账</a>
                            <PurchaseSaleStockSearchForm store={store} actions={actions}/>
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
                                        <th className="standard" style={{width: "120px"}}>规格</th>
                                        <th className="dosage-form">剂型</th>
                                        <th className="unit">单位</th>
                                        <th className="manufacturers" style={{width: "200px"}}>生产厂商</th>
                                        <th className="approval-number" style={{width: "200px"}}>批准文号</th>
                                        <th className="origin">产地</th>
                                        <th className="inventory">初期结存数量</th>
                                        <th className="retail-price">初期结存金额</th>
                                        <th className="inventory">本期入库数量</th>
                                        <th className="retail-price">本期入库金额</th>
                                        <th className="inventory">本期出库数量</th>
                                        <th className="retail-price">本期出库金额</th>
                                        <th className="inventory">期末结存数量</th>
                                        <th className="retail-price">期末结存金额</th>
                                    </tr>
                                </thead>
                                {
                                    purchaseSaleStockList.length <= 0 &&
                                    <tr >
                                        <td colSpan="100" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                }
                                <tbody>
                                    {
                                        purchaseSaleStockList.map((item, index) => {
                                            return (
                                                <PurchaseSaleStockItem actions={actions} store={store} index={index} item={item} key={index}/>
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

PurchaseSaleStockList.propTypes = {};

PurchaseSaleStockList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PurchaseSaleStockList);