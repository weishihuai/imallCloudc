import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import GoodsSplitZeroSearchForm from "./GoodsSplitZeroSearchForm";
import GoodsSplitZeroAddForm from "./GoodsSplitZeroAddForm";
import GoodsSplitZeroDetail from "./GoodsSplitZeroDetail";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";

class GoodsSplitZeroList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["goods:goodsSplitZero:add", "goods:goodsSplitZero:detail"]);
        this.props.actions.goodsSplitZeroList(params, params.page, params.size);
    }

    componentDidUpdate() {

    }

    showAddForm(){
        this.props.actions.showAddForm(true);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.goodsSplitZeroSetSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.goodsSplitZeroList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.goodsSplitZeroSetSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.goodsSplitZeroList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {params} = store.getState().todos;
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
                {store.getState().todos.isShowAdd&&<GoodsSplitZeroAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.save(data, actions, params)} /> }
                {store.getState().todos.isShowDetail&&<GoodsSplitZeroDetail store={store} actions={this.props.actions} /> }
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>药品拆零</h5>
                            <a href="javascript:void(0);">商品管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品拆零</a>
                            <GoodsSplitZeroSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="goods:goodsSplitZero:add" className="added" onClick={this.showAddForm.bind(this)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding">商品编码</th>
                                    <th className="th-title" style={{width: '150px'}}>商品名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="batch-number">批号</th>
                                    <th className="time">有效日期</th>
                                    <th className="cargo-location" style={{width: '160px'}}>货位</th>
                                    <th className="inventory">拆零数量</th>
                                    <th className="sm-inventory" >拆后小包数量</th>
                                    <th className="units" style={{width: '65px'}}>拆零单位</th>
                                    <th className="standard" style={{width: '65px'}}>拆零规格</th>
                                    <th className="statutory">经办人</th>
                                    <th className="time" style={{width: '80px'}}>拆零时间</th>
                                    <th className="operating" style={{minWidth: '100px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { record.length <= 0 &&
                                    <tr >
                                        <th colSpan="16" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((record) => {
                                        return (
                                            <tr key={record.id}>
                                                <td>
                                                    <div className="td-cont" title={record.goodsCode}>{record.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.goodsNm}>{record.goodsNm}</div>
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
                                                    <div className="td-cont" title={record.produceManufacturer}>{record.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.batch}>{record.batch}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.validDate}>{record.validDate}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.storageSpaceNm}>{record.storageSpaceNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.splitZeroQuantity}>{record.splitZeroQuantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.splitSmallPackageQuantity}>{record.splitSmallPackageQuantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.splitZeroUnit}>{record.splitZeroUnit}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.splitZeroSpec}>{record.splitZeroSpec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.operator}>{record.operator}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={record.createDateString}>{record.createDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                        <Imalla href="javascript:void(0);" permissionCode="goods:goodsSplitZero:detail" className="gray-btn" text="查看" onClick={() => this.props.actions.showDetail(true, record.id)}  />
                                                    </div>
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

GoodsSplitZeroList.propTypes = {
    actions: PropTypes.object.isRequired
};

GoodsSplitZeroList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GoodsSplitZeroList);