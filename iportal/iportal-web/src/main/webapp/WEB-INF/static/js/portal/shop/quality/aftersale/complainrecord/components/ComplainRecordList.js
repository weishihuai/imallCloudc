import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {COMPLAIN_RECORD_SEARCH_PARAM_CHANGE} from "../constants/ActionTypes";
import ComplainRecordAddForm from "./ComplainRecordAddForm";
import ComplainRecordDetail from "./ComplainRecordDetail";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

/**
 * 投诉记录
 */
class ComplainRecordList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.complainRecordList(params.page, params.size, params);
        this.props.portalOperationalAuth(['quality:afterSale:complainRecord:add', 'quality:afterSale:complainRecord:detail']);
    }

    componentDidMount() {
        $(".datetimepicker").on("click",function(e){
            e.stopPropagation();
            $(this).lqdatetimepicker({
                css : 'datetime-day',
                dateType : 'D',
                selectback : function(){

                }
            });
        });
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.complainRecordList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.complainRecordList(page - 1, sizePerPage, params);
    }

    handleSearch() {
        const {store} = this.context;
        const newParam = {
            page: 0,
            size: 10,
            searchFields: $.trim(this.refs.searchFields.value),
            customerName: $.trim(this.refs.customerName.value),
            fromDateString: this.refs.fromDateString.value,
            toDateString: this.refs.toDateString.value
        };
        store.dispatch({
            type: COMPLAIN_RECORD_SEARCH_PARAM_CHANGE,
            data: newParam
        });

        this.props.actions.complainRecordList(0, 10, newParam);
    };


    /*重置搜索参数*/
    onResetSearchParam() {

        this.refs.searchFields.value = "";
        this.refs.customerName.value = "";
        this.refs.fromDateString.value = "";
        this.refs.toDateString.value = "";

        const newParam = {
            searchFields: "",
            customerName: "",
            fromDateString: "",
            toDateString: "",
        };

        this.context.store.dispatch({
            type: COMPLAIN_RECORD_SEARCH_PARAM_CHANGE,
            data: newParam
        });

    }

    render() {
        const {store} = this.context;
        const {page, addFormModal, detailModal} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const complainRecordList = store.getState().todos.page.content || [];
        const {complainAddFormModal, complainDetailModal, saveComplainRecord} = this.props.actions;

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
                            <h5>投诉记录</h5>
                            <a href="javascript:void(0);">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">售后管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">投诉记录</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input placeholder="拼音码|名称|编码" ref="searchFields" type="text"/>
                                </div>
                                <div className="search">
                                    <input placeholder="投诉人" ref="customerName" type="text"/>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input ref="fromDateString" placeholder="投诉时间" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input ref="toDateString" className="form-control datetimepicker" readOnly="readOnly" type="text" />
                                    </div>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" onClick={() => this.handleSearch()} value="查询"/>
                                    <input className="sr-reset" type="button" onClick={() => this.onResetSearchParam()} value="重置"/>
                                </div>
                            </div>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="quality:afterSale:complainRecord:add" className="added" text="添加" onClick={()=>complainAddFormModal(true)} />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="time">投诉日期</th>
                                        <th className="complainant">投诉人</th>
                                        <th className="th-title">商品编码</th>
                                        <th className="th-title">商品名称</th>
                                        <th className="standard">规格</th>
                                        <th className="manufacturer">生产厂商</th>
                                        <th className="batch-number">批号</th>
                                        <th className="complaint-content">投诉内容</th>
                                        <th className="treatment-measures">处理措施</th>
                                        <th className="operating" style={{width: "430px"}}>操作</th>
                                    </tr>
                                </thead>
                                {
                                    complainRecordList.length <= 0 &&
                                    <tbody>
                                        <tr>
                                            <td colSpan="100" style={{textAlign:"center"}}>
                                                <div className="empty-box">
                                                    <span>暂无数据</span>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                }
                                <tbody>
                                {
                                    complainRecordList.map((item, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont" title={item.complainDateString}>{item.complainDateString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.customerName}>{item.customerName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.goodsCode}>{item.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.goodsNm}>{item.goodsNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.spec}>{item.spec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.produceManufacturer}>{item.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.batch}>{item.batch}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.complainCont}>{item.complainCont}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.processMeasure}>{item.processMeasure}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                        <ImallA href="javascript:void(0);" permissionCode="quality:afterSale:complainRecord:detail" className="gray-btn" text="查看" onClick={()=>complainDetailModal(item, true)} />
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
                </div>
                {addFormModal && <ComplainRecordAddForm store={store} actions={actions} onSubmit={(data) => saveComplainRecord(data)} />}
                {detailModal && <ComplainRecordDetail store={store} actions={actions}/>}
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

ComplainRecordList.propTypes = {};

ComplainRecordList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ComplainRecordList);