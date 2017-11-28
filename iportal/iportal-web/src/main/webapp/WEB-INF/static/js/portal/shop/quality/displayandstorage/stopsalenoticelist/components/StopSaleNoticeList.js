import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import StopSaleNoticeAddForm from "./StopSaleNoticeAddForm";
import StopSaleNoticeDetailView from "./StopSaleNoticeDetailView";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

class StopSaleNoticeList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page,params} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size);
        this.props.portalOperationalAuth(["quality:displayStorage:stopSaleNotice:add","quality:displayStorage:stopSaleNotice:detail"]);
    }

    componentDidMount() {

        const initTime = ["#fromMakeTimeStr","#toMakeTimeStr","#toStopSaleDateStr","#fromStopSaleDateStr"];

        initTime.map((id,index)=>{
            $(id).on("click",function(e){
                e.stopPropagation();
                $(this).lqdatetimepicker({
                    css : 'datetime-day',
                    dateType : 'D',
                    selectback : function(){

                    }
                });
            });
        });
    }


    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        this.props.actions.query(params, page.number, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.query(params, page - 1, sizePerPage);
    }

    search() {
        const {store} = this.context;
        let {params,page} = store.getState().todos;
        params.stopSaleNum= $('#stopSaleNum').val().trim();
        params.docMakerNm= $('#docMakerNm').val().trim();
        params.approveManName= $('#approveManName').val().trim();
        params.fromMakeTimeStr= $('#fromMakeTimeStr').val().trim();
        params.toMakeTimeStr= $('#toMakeTimeStr').val().trim();
        params.toStopSaleDateStr= $('#toStopSaleDateStr').val().trim();
        params.fromStopSaleDateStr= $('#fromStopSaleDateStr').val().trim();

        this.props.actions.query(params, page.number, page.size,);
        this.props.actions.setParams(params);
    }

    reset(){
        const {store} = this.context;
        let {params} = store.getState().todos;
        $('#stopSaleNum').val('');
        $('#docMakerNm').val('');
        $('#approveManName').val('');
        $('#fromMakeTimeStr').val('');
        $('#toMakeTimeStr').val('');
        $('#toStopSaleDateStr').val('');
        $('#fromStopSaleDateStr').val('');

        params.stopSaleNum="";
        params.docMakerNm="";
        params.approveManName="";
        params.fromMakeTimeStr="";
        params.toMakeTimeStr="";
        params.toStopSaleDateStr="";
        params.fromStopSaleDateStr="";
        this.props.actions.setParams(params);
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {page} = store.getState().todos;
        const contents = page.content||[];
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: [10, 20, 40],
            pageStartIndex: 1,
            page: page.number + 1,
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
                            <h5>药品停售通知单</h5>
                            <a href="javascript:void(0)">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0)">陈列与存储</a>
                            <span>></span>
                            <a href="javascript:void(0)">药品停售通知单</a>
                            <div className="lt-cont">
                                <div className="search">
                                    <input placeholder="停售单编号" id="stopSaleNum" type="text"/>
                                </div>
                                <div className="search">
                                    <input placeholder="制单人" id="docMakerNm" type="text"/>
                                </div>
                                <div className="search">
                                    <input placeholder="审核人"  id="approveManName" type="text"/>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input name="datepicker" id="fromStopSaleDateStr" placeholder="停售日期" className="form-control"  type="text" readOnly="readOnly"/>
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input name="datepicker" id="toStopSaleDateStr" className="form-control"  type="text" readOnly="readOnly"/>
                                    </div>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input name="datepicker" id="fromMakeTimeStr" placeholder="制单日期" className="form-control"  type="text" readOnly="readOnly"/>
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input name="datepicker" id="toMakeTimeStr" className="form-control"  type="text" readOnly="readOnly"/>
                                    </div>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" value="查询" onClick={()=>this.search()}/>
                                        <input className="sr-reset" type="button" value="重置" onClick={()=>{this.reset()}}/>
                                </div>
                            </div>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0)" permissionCode="quality:displayStorage:stopSaleNotice:add" className="added" text="添加" onClick={()=>actions.changeAddFormState(true)}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="return-number" style={{width:"130px"}}>停售单号</th>
                                    <th className="time">制单时间</th>
                                    <th className="name">制单人</th>
                                    <th className="name">审核人</th>
                                    <th className="time">停售日期</th>
                                    <th className="quality-condition">质量状况</th>
                                    <th className="discontinued-idea">停售意见</th>
                                    <th className="operating" style={{width: "430px"}}>操作</th>
                                </tr>
                                </thead>
                                {
                                contents.length <= 0 &&
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
                                    contents.map((content,index)=>{
                                        return (
                                            <tr key={index}>
                                                <td><div className="td-cont" title={content.stopSaleNum}>{content.stopSaleNum}</div></td>
                                                <td><div className="td-cont" title={content.makeTimeStr}>{content.makeTimeStr}</div></td>
                                                <td><div className="td-cont" title={content.docMakerNm}>{content.docMakerNm}</div></td>
                                                <td><div className="td-cont" title={content.approveManName}>{content.approveManName}</div></td>
                                                <td><div className="td-cont" title={content.stopSaleDateStr}>{content.stopSaleDateStr}</div></td>
                                                <td><div className="td-cont" title={content.qualityState}>{content.qualityState}</div></td>
                                                <td><div className="td-cont" title={content.stopSaleSuggest}>{content.stopSaleSuggest}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft: "0",paddingRight: "25px",textAlign: "right"}}>
                                                        <ImallA href="javascript:void(0)" permissionCode="quality:displayStorage:stopSaleNotice:detail" text="查看订单" onClick={()=>actions.findDetailAndOpenDetailView(content.id)} className="gray-btn"/>
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
                <IMallPaginationBar options={options} actions={actions}/>
                {store.getState().todos.addFormState && <StopSaleNoticeAddForm store={store} actions={actions}/>}
                {store.getState().todos.detailViewState && <StopSaleNoticeDetailView store={store} actions={actions}/>}
            </div>
        )
    }
}

StopSaleNoticeList.propTypes = {
    actions: PropTypes.object.isRequired
}

StopSaleNoticeList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StopSaleNoticeList);