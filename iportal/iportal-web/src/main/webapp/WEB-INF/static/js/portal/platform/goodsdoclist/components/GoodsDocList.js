import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import GoodsDocAddForm from "./GoodsDocAddForm";
import GoodsDocUpdateForm from "./GoodsDocUpdateForm";
import CommonConfirmComponent from "../../../../common/component/CommonConfirmComponent";
import GoodsDocApproveAndDetailView from "./GoodsDocApproveAndDetailView";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import ImallA from "../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import * as types from "../constants/ActionTypes";
import FileMgrModalComponent from "../../../../common/filemgr/components/FileMgrModalComponent";
import {showFileMgrModalHasCallbackFunc} from "../../../../common/filemgr/actions";
import {niftyNoty} from "../../../../common/common";
class GoodsDocList extends Component {

    componentWillMount() {
        require('searchableSelect');
        const {store} = this.context;
        this.props.actions.queryGoodsDocList(0, 10, store.getState().todos.params);
        this.props.actions.getGoodsCategoryList(() => $("#goodsCategoryId").searchableSelect());
        this.props.portalOperationalAuth(["goods:portalGoodsDoc:add","goods:portalGoodsDoc:update","goods:portalGoodsDoc:detail","goods:portalGoodsDoc:approve","goods:portalGoodsDoc:delete","goods:portalGoodsDoc:import","goods:portalGoodsDoc:export","goods:portalGoodsDoc:download"]);
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
        this.props.actions.queryGoodsDocList(0, sizePerPage,store.getState().todos.params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        this.props.actions.queryGoodsDocList(page - 1, sizePerPage,store.getState().todos.params,);
    }

    approveStatCode(code){
        switch (code){
            case "WAIT_APPROVE":
                return "待审核";
            case "DENY_APPROVE":
                return "已拒绝";
            case "PASS_APPROVE":
                return "已审核";
        }
    }

    search() {
        const {store} = this.context;
        let {params} = store.getState().todos;
        let fromDateString = $('#fromDateString').val();
        let toDateString = $('#toDateString').val();
        params.approveStateCode= $('#approveStateCode').val();
        params.keyWords= $('#keyWords').val().trim();
        params.fromDateString= fromDateString;
        params.toDateString= toDateString;
        params.goodsCategoryId= $('#goodsCategoryId').val();
        this.props.actions.queryGoodsDocList(0, params.size,store.getState().todos.params);
        this.props.actions.setSearchParam(params);

    }

    reset(){
        $('#fromDateString').val('');
        $('#toDateString').val('');
        $('#keyWords').val('');
        $(".select").jSelectReset();
        $('#goodsCategoryId').val('');
        $('#goodsCategoryId-clear').text("商品分类");
        let {params} = this.context.store.getState().todos;
        params.keyWords="";
        params.fromDateString="";
        params.toDateString="";
        params.approveStateCode="";
        params.goodsCategoryId="";
        this.props.actions.setSearchParam(params);
        this.props.actions.queryGoodsDocList(0, params.size,params);
    }

    openApproveOrDetail(goodsId,isApproveOrDetail){
        this.context.store.dispatch({
            type:types.GOODS_DOC_IS_APPROVE_OR_DETAIL,
            isApproveOrDetail:isApproveOrDetail
        });
        this.props.actions.getGoodsDocDetail(goodsId,false);
    }

    exportExcel(){
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.exportExcel(params);
    }


    importExcel() {
        const {store} = this.context;
        this.props.showFileMgrModalHasCallbackFunc((files) => {
            if (files.length > 0) {
                const fileName = files[0].fileId.split(".")[1];
                if (fileName==="xls"||fileName==="xlsx"||fileName==="xlsm") {
                    this.props.actions.importExcel( files[0].fileId, store.getState().todos.params);
                    niftyNoty("导入成功！");
                } else {
                    niftyNoty("请选择正确的excel文件！");
                }
            }

        });
    }

    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const page = store.getState().todos.page;
        const goodsCategoryList = store.getState().todos.goodsCategoryList||[];
        const goodsId = store.getState().todos.goodsId||"";
        let goodsList = page.content || [];
        const number = page.number + 1;
        const display = store.getState().fileMgrTodos.fileMgrModalState;
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
            hideSizePerPage: page.totalElements <= page.size,
        };
        return (
            <div className="main-box">
                {display&& <FileMgrModalComponent store={store} actions={this.props.actions} />}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>商品档案库</h5>
                            <a href="javascript:void(0);">商品管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">商品档案库</a>
                            <div className="lt-cont">
                                <div className="status">
                                    <select id="approveStateCode" className="select">
                                        <option value="">全部状态</option>
                                        <option value="WAIT_APPROVE">待审核</option>
                                        <option value="PASS_APPROVE">已审核</option>
                                        <option value="DENY_APPROVE">已拒绝</option>
                                    </select>
                                </div>

                                <div className="status">
                                    <select id="goodsCategoryId" className="select">
                                        <option value="">商品分类</option>
                                        {goodsCategoryList.map((goodsCategory,index)=>{
                                            return (
                                                <option value={goodsCategory.id} key={index}>{goodsCategory.categoryName}</option>
                                            )
                                        })}
                                    </select>
                                </div>

                                <div className="sel-date">
                                    <div className="form-group float-left w140">
                                        <input name="datepicker" id="fromDateString" placeholder="创建时间" className="form-control datepicker" type="text" readOnly="readOnly"/>
                                    </div>
                                    <div className="float-left form-group-txt">至</div>
                                    <div className="form-group float-left w140">
                                        <input name="datepicker" id="toDateString" className="form-control datepicker" type="text" readOnly="readOnly"/>
                                    </div>
                                </div>
                                <div className="search">
                                    <input type="text" id="keyWords" placeholder="拼音码|商品编码|名称"/>
                                </div>
                                <div className="search-reset">
                                    <input className="sr-search" type="button" value="查询" onClick={()=>this.search()}/>
                                    <input className="sr-reset" type="button" value="重置" onClick={()=>this.reset()}/>
                                </div>
                            </div>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="goods:portalGoodsDoc:add" className="added" text="添加" onClick={()=>actions.changeGoodsDocAddFormState(true)}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="goods:portalGoodsDoc:import" className="guide" text="导入" onClick={(e)=>this.importExcel()}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="goods:portalGoodsDoc:export" className="guide" text="导出" onClick={(e)=>this.exportExcel()}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="goods:portalGoodsDoc:download" text="下载模板" className="guide" onClick={(e) => window.open(iportal + "/docInfo/goodsDocModule.zip")}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding">商品编码</th>
                                    <th className="th-title" style={{width:"263px"}}>商品名称</th>
                                    <th className="common-name">通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturer" style={{width:"250px"}}>生产厂商</th>
                                    <th className="producing">产地</th>
                                    <th className="time">创建时间</th>
                                    <th className="status">审核状态</th>
                                    <th className="operating" style={{width:"250px"}}>操作</th>
                                </tr>
                                </thead>
                                { goodsList.length < 1 &&
                                <tr >
                                    <td colSpan="18" style={{textAlign:"center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </td>
                                </tr>
                                }
                                <tbody>
                                {goodsList.map((goods,index)=>{
                                    return (
                                        <tr key={index}>
                                            <td><div className="td-cont">{goods.goodsCode}</div></td>
                                            <td><div className="td-cont" >{goods.goodsNm}</div></td>
                                            <td><div className="td-cont">{goods.commonNm}</div></td>
                                            <td><div className="td-cont">{goods.spec}</div></td>
                                            <td><div className="td-cont">{goods.dosageForm}</div></td>
                                            <td><div className="td-cont">{goods.unit}</div></td>
                                            <td><div className="td-cont">{goods.produceManufacturer}</div></td>
                                            <td><div className="td-cont">{goods.productionPlace}</div></td>
                                            <td><div className="td-cont">{goods.createDateString}</div></td>
                                            <td><div className="td-cont">{this.approveStatCode(goods.approveStateCode)}</div></td>
                                            <td><
                                                div className="td-cont" style={{paddingLeft: "0",paddingRight:"25px",textAlign: "right"}}>
                                                {goods.approveStateCode=="WAIT_APPROVE"&&<ImallA href="javascript:void(0);" permissionCode="goods:portalGoodsDoc:approve" className="gray-btn" text="审核" onClick={()=>this.openApproveOrDetail(goods.id,false)}/>}
                                                <ImallA href="javascript:void(0);" permissionCode="goods:portalGoodsDoc:update" className="gray-btn" text="修改" onClick={()=>actions.getGoodsDocDetail(goods.id,true)}/>
                                                <ImallA href="javascript:void(0);" permissionCode="goods:portalGoodsDoc:detail" className="gray-btn" text="查看" onClick={()=>this.openApproveOrDetail(goods.id,true)}/>
                                                <ImallA href="javascript:void(0);" permissionCode="goods:portalGoodsDoc:delete" className="gray-btn" text="删除" onClick={()=>actions.changeConfirmState(true,goods.id)}/>
                                            </div>
                                            </td>
                                        </tr>
                                    )
                                })}

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
                {store.getState().todos.confirmModelState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="删除商品档案" text={"是否删除?"} confirmBtn="确定" callback={(result) => actions.updateDelete(goodsId,store.getState().todos.params)} close={() => actions.changeConfirmState(false,"")}/>}
                {store.getState().todos.goodsDocAddFormState&&<GoodsDocAddForm store={store} actions={actions} />}
                {store.getState().todos.goodsDocUpdateFormState&&<GoodsDocUpdateForm store={store} actions={actions} />}
                {store.getState().todos.goodsDocApproveAndDetailState&&<GoodsDocApproveAndDetailView store={store} actions={actions} />}
            </div>
        );
    }
}
GoodsDocList.propTypes = {
    actions: PropTypes.object.isRequired
};

GoodsDocList.contextTypes = {
    store: React.PropTypes.object
};

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        portalOperationalAuth,showFileMgrModalHasCallbackFunc
    }, dispatch);
}

function mapStateToProps(state) {
    return state;

}

GoodsDocList = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsDocList);

export default GoodsDocList
