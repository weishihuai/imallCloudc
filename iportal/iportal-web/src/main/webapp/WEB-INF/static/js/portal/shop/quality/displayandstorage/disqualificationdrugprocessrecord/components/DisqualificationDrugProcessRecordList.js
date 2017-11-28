import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DisqualificationDrugProcessRecordSearchForm from "./DisqualificationDrugProcessRecordSearchForm";

/**
 * 不合格药品 处理记录列表
 */
class DisqualificationDrugProcessRecordList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.disqualificationDrugProcessRecordList(params, params.page, params.size);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.disqualificationDrugProcessRecordList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.disqualificationDrugProcessRecordList(params, page - 1, sizePerPage);
    }

    documentTypeFormat(documentType) {
        switch (documentType) {
            case "DRUG_LOCK_DEAL":
                return "药品处理";
            case "DRUG_LOCK_DESTROY":
                return "药品销毁";
            case "PURCHASE_RETURNED":
                return "采购退货";
            case "PURCHASE_IN_STOCK":
                return "采购入库";
        }
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const disqualificationDrugProcessRecordList = store.getState().todos.page.content || [];

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
                            <h5>不合格药品处理记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">陈列与存储</a>
                            <span>></span>
                            <a href="javascript:void(0);">不合格药品处理记录</a>
                            <DisqualificationDrugProcessRecordSearchForm actions={actions} store={store}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="time">日期</th>
                                    <th className="time">单据类型</th>
                                    <th className="th-coding" style={{width:"130px"}}>商品编码</th>
                                    <th className="th-title">商品名称</th>
                                    <th className="common-name">通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturers" style={{width:"110px"}}>生产厂商</th>
                                    <th className="batch-number">批准文号</th>
                                    <th className="origin">产地</th>
                                    <th className="batch-number">批号</th>
                                    <th className="time">有效期至</th>
                                    <th className="inventory">数量</th>
                                    <th className="reason">处理措施</th>
                                    <th className="remark">备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                { disqualificationDrugProcessRecordList.length <= 0 &&
                                <tr >
                                    <th colSpan="16" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {disqualificationDrugProcessRecordList.map((disqualificationDrugProcessRecord, index) => {
                                    return (<tr key={index}>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.recordDateString}>{disqualificationDrugProcessRecord.recordDateString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={this.documentTypeFormat(disqualificationDrugProcessRecord.documentType)}>{this.documentTypeFormat(disqualificationDrugProcessRecord.documentType)}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.goodsCode}>{disqualificationDrugProcessRecord.goodsCode}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.goodsNm}>{disqualificationDrugProcessRecord.goodsNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.commonNm}>{disqualificationDrugProcessRecord.commonNm}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.spec}>{disqualificationDrugProcessRecord.spec}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.dosageForm || "暂无"}>{disqualificationDrugProcessRecord.dosageForm || "暂无"}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.unit}>{disqualificationDrugProcessRecord.unit}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.produceManufacturer}>{disqualificationDrugProcessRecord.produceManufacturer}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.approvalNumber}>{disqualificationDrugProcessRecord.approvalNumber}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.productionPlace || "暂无"}>{disqualificationDrugProcessRecord.productionPlace || "暂无"}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.batch}>{disqualificationDrugProcessRecord.batch}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.validDateString}>{disqualificationDrugProcessRecord.validDateString}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.quantity}>{disqualificationDrugProcessRecord.quantity}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.processMeasure}>{disqualificationDrugProcessRecord.processMeasure}</div>
                                            </td>
                                            <td>
                                                <div className="td-cont" title={disqualificationDrugProcessRecord.remark || "暂无"}>{disqualificationDrugProcessRecord.remark || "暂无"}</div>
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

DisqualificationDrugProcessRecordList.propTypes = {};

DisqualificationDrugProcessRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DisqualificationDrugProcessRecordList);