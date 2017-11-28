import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import FirstManageDrugApproveForm from "./FirstManageDrugApproveForm";
import FirstManageDrugApproveDetail from "./FirstManageDrugApproveDetail";
import {portalOperationalAuth} from "../../../../../../common/imallbutton/actions";
import ImallA from "../../../../../../common/imallbutton/components/ImallA";

class FirstManageDrugQualityApproveList extends Component {

    componentWillMount() {
        const {store} = this.context;
        const {page,params} = store.getState().todos;
        this.props.actions.query(params, page.number, page.size);
        this.props.portalOperationalAuth(["quality:firstManage:drugQuality:approve","quality:firstManage:drugQuality:detail"]);
    }

    componentDidMount() {
        $("#approveStateCode").jSelect();

        $(".datepicker").on("click",function(e){
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
        const {page, params} = store.getState().todos;
        this.props.actions.setParams(Object.assign({},params,{page:0, size:sizePerPage}));
        this.props.actions.query(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setParams(Object.assign({},params,{page:page - 1, size:sizePerPage}));
        this.props.actions.query(params, page - 1, sizePerPage);
    }

    buildStateCode(code){
        switch (code){
            case "WAIT_APPROVE":
                return "待审核";
            case "PASS_APPROVE":
                return "已审核";
            case "REJECTED":
                return "已驳回"
        }
    }

    search() {
        const {store} = this.context;
        let {params,page} = store.getState().todos;
        params.approveStateCode= $('#approveStateCode').val().trim();
        params.keyWords= $('#keyWords').val().trim();
        params.fromDateString= $('#fromDateString').val().trim();
        params.toDateString= $('#toDateString').val().trim();

        this.props.actions.query(params, 0, 10);
        this.props.actions.setParams(params);

    }

    reset(){
        $('#fromDateString').val('');
        $('#toDateString').val('');
        $('#keyWords').val('');
        $(".select").jSelectReset();
        let {params} = this.context.store.getState().todos;
        params.keyWords="";
        params.fromDateString="";
        params.toDateString="";
        params.approveStateCode="";
        this.props.actions.query(params, 0, 10);
        this.props.actions.setParams(params);
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const {page} = store.getState().todos;
        const contents = page.content || [];
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: [10, 20, 40],
            pageStartIndex: 1,
            page: page.number + 1,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "<",
            nextPage: ">",
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
                            <h5>首营商品质量审核</h5>
                            <a href="javascript:void(0)">质量管理</a>
                            <span>></span>
                            <a href="javascript:void(0)">首营审核</a>
                            <span>></span>
                            <a href="javascript:void(0)">首营商品质量审核</a>
                            <div className="lt-cont">
                                <div className="status">
                                    <select id="approveStateCode" className="select allSelect2">
                                        <option value="">全部</option>
                                        <option value="WAIT_APPROVE">待审核</option>
                                        <option value="PASS_APPROVE">已审核</option>
                                        <option value="REJECTED">已驳回</option>
                                    </select>
                                </div>
                                <div className="search">
                                    <input id="keyWords" placeholder="拼音码|名称|编码" type="text"/>
                                </div>
                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input name="fromDateString" id="fromDateString" placeholder="审核时间" className="form-control datepicker"  type="text" readOnly="readOnly"/>
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input name="toDateString" id="toDateString" className="form-control datepicker"  type="text" readOnly="readOnly"/>
                                    </div>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" onClick={()=>this.search()} value="查询"/>
                                    <input className="sr-reset" type="button" onClick={()=>this.reset()} value="重置"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"200px"}}>商品编码</th>
                                    <th className="th-title">商品名称</th>
                                    <th className="th-title">通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="batch-number">批准文号</th>
                                    <th className="status">审核状态</th>
                                    <th className="time">质量审核时间</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                {contents.length <= 0 &&
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
                                                <td><div className="td-cont" title={content.goodsCode}>{content.goodsCode}</div></td>
                                                <td><div className="td-cont" title={content.goodsNm}>{content.goodsNm}</div></td>
                                                <td><div className="td-cont" title={content.commonNm}>{content.commonNm}</div></td>
                                                <td><div className="td-cont" title={content.spec}>{content.spec}</div></td>
                                                <td><div className="td-cont" title={content.dosageFormName}>{content.dosageFormName}</div></td>
                                                <td><div className="td-cont" title={content.unit}>{content.unit}</div></td>
                                                <td><div className="td-cont" title={content.produceManufacturer}>{content.produceManufacturer}</div></td>
                                                <td><div className="td-cont" title={content.approvalNumber}>{content.approvalNumber}</div></td>
                                                <td><div className="td-cont" title={this.buildStateCode(content.approveStateCode)}>{this.buildStateCode(content.approveStateCode)}</div></td>
                                                <td><div className="td-cont" title={content.qualityApproveTimeStr}>{content.qualityApproveTimeStr}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingLeft:"0",paddingRight:"25px",textAlign:"right"}}>
                                                        <ImallA href="javascript:void(0)" className="gray-btn" permissionCode="quality:firstManage:drugQuality:detail" onClick={()=>actions.getFirstManageDrugQualityApproveDetail(content.id)} text="查看"/>
                                                        {content.approveStateCode=="WAIT_APPROVE"&&<ImallA href="javascript:void(0)" className="gray-btn" permissionCode="quality:firstManage:drugQuality:approve" onClick={()=>actions.getApproveInfAndOpenApproveForm(content.id)} text="审核"/>}
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
                {store.getState().todos.approveFormState && <FirstManageDrugApproveForm store={store} actions={actions}/>}
                {store.getState().todos.detailViewState && <FirstManageDrugApproveDetail store={store} actions={actions}/>}
            </div>
        )
    }
}

FirstManageDrugQualityApproveList.propTypes = {
    actions: PropTypes.object.isRequired
}

FirstManageDrugQualityApproveList.contextTypes = {
    store: React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        portalOperationalAuth
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FirstManageDrugQualityApproveList);