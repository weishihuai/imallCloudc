import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DestroyRecordSearchForm from "./DestroyRecordSearchForm";

/**
 * 药品销毁记录列表
 */
class DestroyRecordList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.destroyRecordList(params, params.page, params.size);
    }

    componentDidMount() {
        $("#root").css("min-width", "2500px");
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.destroyRecordList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.destroyRecordList(params, page - 1, sizePerPage);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const destroyRecordList = store.getState().todos.page.content || [];

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
                            <h5>销毁记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">陈列与存储</a>
                            <span>></span>
                            <a href="javascript:void(0);">销毁记录</a>
                            <DestroyRecordSearchForm actions={actions} store={store}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width: "120px"}}>商品编码</th>
                                    <th className="th-title"  style={{width:"250px"}}>商品名称</th>
                                    <th className="common-name"  style={{width:"250px"}}>通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturers">生产厂商</th>
                                    <th className="origin">产地</th>
                                    <th className="batch-number" style={{width: "200px"}}>批准文号</th>
                                    <th className="supplier-name">供应商名称</th>
                                    <th className="batch-number">批号</th>
                                    <th className="inventory">库存数量</th>
                                    <th className="inventory">锁定数量</th>
                                    <th className="reason">锁定原因</th>
                                    <th className="time">生产日期</th>
                                    <th className="time">有效期至</th>
                                    <th className="cargo-location">货位</th>
                                    <th className="origin">销毁地点</th>
                                    <th className="name">销毁人</th>
                                    <th className="testing-cycle">销毁时间</th>
                                    <th className="name">保管员</th>
                                    <th className="reason">销毁原因</th>
                                    <th className="name">审核人</th>
                                    <th className="name">复核人</th>
                                </tr>
                                </thead>
                                <tbody>
                                { destroyRecordList.length <= 0 &&
                                <tr >
                                    <th colSpan="24" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {destroyRecordList.map((destroyRecord, index) => {
                                    return (<tr key={index}>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.goodsCode}>{destroyRecord.goodsCode}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.goodsNm}>{destroyRecord.goodsNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.commonNm}>{destroyRecord.commonNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.spec}>{destroyRecord.spec}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.dosageForm}>{destroyRecord.dosageForm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.unit}>{destroyRecord.unit}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.produceManufacturer}>{destroyRecord.produceManufacturer}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.productionPlace || "暂无"}>{destroyRecord.productionPlace || "暂无"}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.approvalNumber}>{destroyRecord.approvalNumber}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.supplierNm}>{destroyRecord.supplierNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.batch}>{destroyRecord.batch}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.stockQuantity}>{destroyRecord.stockQuantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.lockQuantity}>{destroyRecord.lockQuantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.lockReason}>{destroyRecord.lockReason}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.produceDateString}>{destroyRecord.produceDateString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.validDateString}>{destroyRecord.validDateString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.storageSpaceNm}>{destroyRecord.storageSpaceNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.destroyPlace || "暂无"}>{destroyRecord.destroyPlace || "暂无"}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.destroyMan}>{destroyRecord.destroyMan}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.destroyTimeString}>{destroyRecord.destroyTimeString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.keeper || "暂无"}>{destroyRecord.keeper || "暂无"}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.destroyReason || "暂无"}>{destroyRecord.destroyReason || "暂无"}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.approveMan}>{destroyRecord.approveMan}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={destroyRecord.reviewer}>{destroyRecord.reviewer}</div>
                                            </td>
                                        </tr>
                                    )
                                })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <IMallPaginationBar actions={actions} options={options}/>
                </div>
            </div>
        )
    }
}

DestroyRecordList.propTypes = {};

DestroyRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DestroyRecordList);