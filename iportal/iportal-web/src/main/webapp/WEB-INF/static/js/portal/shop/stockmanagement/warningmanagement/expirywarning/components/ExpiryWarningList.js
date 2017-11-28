import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import ExpiryWarningSearchForm from "./ExpiryWarningSearchForm";
/**
 * 近效期催销 列表
 */
class ExpiryWarningList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.expiryWarningList(params, params.page, params.size);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, { page:0, size : sizePerPage}));
        this.props.actions.expiryWarningList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page : page - 1, size : sizePerPage}));
        this.props.actions.expiryWarningList(params, page - 1, sizePerPage);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const expiryWarningList = store.getState().todos.page.content || [];
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
                            <h5>近效期预警</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">预警管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">近效期预警</a>
                            <ExpiryWarningSearchForm store={store} actions={actions}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="serial-number" >序号</th>
                                    <th className="th-coding" style={{width: "150px"}}>商品编码</th>
                                    <th className="commodity-name" style={{width: "250px"}}>商品名称</th>
                                    <th className="common-name"  style={{width: "250px"}}>通用名称</th>
                                    <th className="standard" style={{width: "120px"}}>规格</th>
                                    <th className="dosage-form">剂型</th>
                                    <th className="unit">单位</th>
                                    <th className="manufacturers" style={{width: "250px"}}>生产厂商</th>
                                    <th className="approval-number" style={{width: "250px"}}>批准文号</th>
                                    <th className="origin">产地</th>
                                    <th className="batch-number" style={{width: "120px"}}>批号</th>
                                    <th className="time" style={{width: "100px"}}>生产日期</th>
                                    <th className="time" style={{width: "100px"}}>有效期至</th>
                                    <th className="inventory">库存数量</th>
                                    <th className="retail-price">库存金额</th>
                                </tr>
                                </thead>
                                <tbody>
                                { expiryWarningList.length <= 0 &&
                                <tr >
                                    <th colSpan="16" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {expiryWarningList.map((expiryWarning, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont">{index + 1}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.goodsCode}>{expiryWarning.goodsCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.goodsNm}>{expiryWarning.goodsNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.goodsCommonNm}>{expiryWarning.goodsCommonNm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.spec}>{expiryWarning.spec}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.dosageForm}>{expiryWarning.dosageForm}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.unit}>{expiryWarning.unit}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.produceManufacturer}>{expiryWarning.produceManufacturer}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.approvalNumber}>{expiryWarning.approvalNumber || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.productionPlace}>{expiryWarning.productionPlace || "暂无"}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.batch}>{expiryWarning.batch}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.produceDateString}>{expiryWarning.produceDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.validDateString}>{expiryWarning.validDateString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.currentStock}>{expiryWarning.currentStock}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={expiryWarning.stockMoney}>{expiryWarning.stockMoney}</div>
                                    </td>
                                </tr>
                                    )
                                })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <IMallPaginationBar options={options} actions={actions}/>
                </div>
            </div>
        )
    }
}

ExpiryWarningList.propTypes = {};

ExpiryWarningList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ExpiryWarningList);