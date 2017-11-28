import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugCheckRecordSearchForm from "./DrugCheckRecordSearchForm";

class DrugCheckRecordList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCheckRecordList(params, params.page, params.size);
    }

    componentDidMount(){
        $("#root").css("min-width", "2500px");
    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCheckRecordList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCheckRecordList(params, page - 1, sizePerPage);
    }
    
    render(){
        const {actions}=this.props;
        const {store} = this.context;
        const {page} = store.getState().todos;
        const number=page.number+1;
        const record = store.getState().todos.page.content || [];
        const options = {
            sizePerPage: page.size>0?page.size:10,
            sizePerPageList: page.totalElements>0?[10, 20, 40]:[],
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
            hideSizePerPage:page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>药品检查记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">陈列与存储</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品检查记录</a>
                            <DrugCheckRecordSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="checklists-code" style={{width: 120 + 'px'}}>检查单码</th>
                                    <th className="time" >检查时间</th>
                                    <th className="name" style={{width: 100 + 'px'}}>审核人</th>
                                    <th className="order-number">商品编码</th>
                                    <th className="th-title" style={{width: '250px'}}>商品名称</th>
                                    <th className="common-name" style={{width: '250px'}}>通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="origin">产地</th>
                                    <th className="batch-number">批准文号</th>
                                    <th className="batch-number" style={{width: '85px'}}>批号</th>
                                    <th className="time">生产日期</th>
                                    <th className="time">有效期至</th>
                                    <th className="goods">货位</th>
                                    <th className="goods">库存数量</th>
                                    <th className="goods">检查数量</th>
                                    <th className="check-project">检查项目</th>
                                    <th className="goods">不合格数量</th>
                                    <th className="processing-opinion">处理意见</th>
                                    <th className="conclusion">结论</th>
                                    <th className="note">备注</th>
                                </tr>
                                </thead>
                                <tbody>
                                {record.length <= 0 &&
                                    <tr>
                                        <th colSpan="23" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((record, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont" title={record.checkNum}>{record.checkNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.checkTimeString}>{record.checkTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.approveMan}>{record.approveMan}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.goodsCode}>{record.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.goodsNm}>{record.goodsNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.commonNm}>{record.commonNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.spec}>{record.spec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.dosageForm}>{record.dosageForm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.unit}>{record.unit}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.manufacture}>{record.manufacture}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.productionPlace}>{record.productionPlace}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.approvalNumber}>{record.approvalNumber}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.batch}>{record.batch}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.produceTimeString}>{record.produceTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.validDateString}>{record.validDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.storageSpaceNm}>{record.storageSpaceNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.stockQuantity}>{record.stockQuantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.checkQuantity}>{record.checkQuantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.checkPrj}>{record.checkPrj}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.disqualificationQuantity}>{record.disqualificationQuantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.processSuggest}>{record.processSuggest}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.verdict}>{record.verdict}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.remark}>{record.remark}</div>
                                                </td>
                                            </tr>
                                        );
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        );
    }
}

DrugCheckRecordList.propTypes = {
    actions: PropTypes.object.isRequired
};

DrugCheckRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugCheckRecordList);