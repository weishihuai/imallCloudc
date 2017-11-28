import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import GoodsAddForm from "./GoodsAddForm";
import GoodsUpdateForm from "./GoodsUpdateForm";
import GoodsDetailView from "./GoodsDetailView";
import EnableForm from "./EnableForm";
import GoodsEnableRecordList from "./GoodsEnableRecordList";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import ImallA from "../../../../../common/imallbutton/components/ImallA";
import {portalOperationalAuth} from "../../../../../common/imallbutton/actions";
import FileMgrModalComponent from "../../../../../common/filemgr/components/FileMgrModalComponent";
import {showFileMgrModalHasCallbackFunc} from "../../../../../common/filemgr/actions";
import {niftyNoty} from "../../../../../common/common";

class GoodsList extends Component {

    componentWillMount() {
        require('searchableSelect');
        const {store} = this.context;
        this.props.actions.queryGoodsList(0, 10, store.getState().todos.params);
        this.props.actions.getStorageSpaceIdList(() => $("#storageSpaceId").searchableSelect());

        this.props.portalOperationalAuth(["goods:goods:add","goods:goods:update","goods:goods:detail","goods:goods:enable","goods:goods:log","goods:goods:import","goods:goods:export","goods:goods:download"]);
    }

    componentDidMount() {
        $("#isEnable").jSelect();
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
        const {params} = store.getState().todos;
        this.props.actions.setSearchParam(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.queryGoodsList(0, sizePerPage,params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParam(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.queryGoodsList(page - 1, sizePerPage,params);
    }

    approveStatCode(code){
        switch (code){
            case "WAIT_APPROVE":
                return "待审核";
            case "DENY_APPROVE":
                return "已拒绝";
            case "PASS_APPROVE":
                return "已通过";
        }
    }

    isFirstSell(code){
        let str = "否";
        if(code=="Y")
            str = "是";
        return str;
    }

    buildIsEnable(isEnable){
        return isEnable == "Y"?"禁用":"启用";
    }

    search() {
        const {store} = this.context;
        let {params} = store.getState().todos;
        let fromDateString = $('#fromDateString').val();
        let toDateString = $('#toDateString').val();
        params.isEnable= $('#isEnable').val();
        params.approveStateCode= $('#approveStateCode').val();
        params.storageSpaceId= $('#storageSpaceId').val();
        params.keyWords= $('#keyWords').val().trim();
        params.fromDateString= fromDateString;
        params.toDateString= toDateString;
        this.props.actions.queryGoodsList(0, params.size,store.getState().todos.params);
        this.props.actions.setSearchParam(params);
    }

    reset(){
        const {store} = this.context;
        let {params} = store.getState().todos;
        $('#fromDateString').val('');
        $('#toDateString').val('');
        $('#isEnable').jSelectReset();
        $('#approveStateCode').jSelectReset();
        $('#keyWords').val('');
        $('#storageSpaceId-clear').text("货位");
        $('#storageSpaceId').val('');
        params.keyWords="";
        params.fromDateString="";
        params.toDateString="";
        params.isEnable="";
        params.approveStateCode="";
        params.storageSpaceId="";
        this.props.actions.setSearchParam(params);
        this.props.actions.queryGoodsList(0, params.size,params);
    }

    openEnableForm(id,isEnable,goodsNm){
        this.props.actions.setEnableFormInitData({id:id,operationState:isEnable,goodsNm:goodsNm});
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
        const storageSpaceList = store.getState().todos.storageSpaceList||[];
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
            paginationShowsTotal: page.totalElements > page.size ? this.renderShowsTotal : null,
            hideSizePerPage: page.totalElements <= page.size,
        };
        return (
            <div className="main-box">
                {display&& <FileMgrModalComponent store={store} actions={this.props.actions} />}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>商品管理</h5>
                            <a href="javascript:void(0);">商品管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">商品管理</a>
                            <div className="lt-cont">
                                <div className="status">
                                    <select id="isEnable" className="select ">
                                        <option value="">商品状态</option>
                                        <option value="N">禁用</option>
                                        <option value="Y">启用</option>
                                    </select>
                                </div>
                                <div className="status">
                                    <select id="approveStateCode" className="select ">
                                        <option value="">审核状态</option>
                                        <option value="WAIT_APPROVE">待审核</option>
                                        <option value="PASS_APPROVE">已通过</option>
                                        <option value="DENY_APPROVE">已拒绝</option>
                                    </select>
                                </div>
                                <div className="status">
                                    <select id="storageSpaceId" className="select">
                                        <option value="">货位</option>
                                        {storageSpaceList.map((storageSpace,index)=>{
                                            return (
                                                <option value={storageSpace.id} key={index}>{storageSpace.storageSpaceNm}</option>
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
                            <ImallA href="javascript:void(0);" permissionCode="goods:goods:add" className="added" text="添加" onClick={()=>actions.changeAddGoodFormState(true)}/>
                            <ImallA  href="javascript:void(0);" permissionCode="goods:goods:import" className="guide" text="导入" onClick={(e)=>this.importExcel()}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="goods:goods:export" className="guide" text="导出" onClick={(e)=>this.exportExcel()}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="goods:goods:download" text="下载模板" className="guide" onClick={(e) => window.open(iportal + "/docInfo/goodsModule.zip")}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width: '160px'}}>商品编码</th>
                                    <th className="th-title">商品名称</th>
                                    <th className="common-name">通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturer">生产厂商</th>
                                    <th className="inventory">库存</th>
                                    <th className="th-sy">首营</th>
                                    <th className="producing">产地</th>
                                    <th className="retail-price">零售价</th>
                                    <th className="member-price">会员价</th>
                                    <th className="time">创建时间</th>
                                    <th className="status">审核状态</th>
                                    <th className="operating" style={{width:"330px"}}>操作</th>
                                </tr>
                                </thead>
                                { goodsList.length <= 0 &&
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
                                            <td><div className="td-cont" title={goods.goodsCode}>{goods.goodsCode}</div></td>
                                            <td><div className="td-cont" title={goods.goodsNm}>{goods.goodsNm}</div></td>
                                            <td><div className="td-cont" title={goods.commonNm}>{goods.commonNm}</div></td>
                                            <td><div className="td-cont" title={goods.spec}>{goods.spec}</div></td>
                                            <td><div className="td-cont" title={goods.dosageForm}>{goods.dosageForm}</div></td>
                                            <td><div className="td-cont" title={goods.unit}>{goods.unit}</div></td>
                                            <td><div className="td-cont" title={goods.produceManufacturer}>{goods.produceManufacturer}</div></td>
                                            <td><div className="td-cont" title={goods.currentStock}>{goods.currentStock}</div></td>
                                            <td><div className="td-cont" title={this.isFirstSell(goods.isFirstSell)}>{this.isFirstSell(goods.isFirstSell)}</div></td>
                                            <td><div className="td-cont" title={goods.productionPlace}>{goods.productionPlace}</div></td>
                                            <td><div className="td-cont" title={goods.retailPrice}>{goods.retailPrice}</div></td>
                                            <td><div className="td-cont" title={goods.memberPrice}>{goods.memberPrice}</div></td>
                                            <td><div className="td-cont" title={goods.createDateString}>{goods.createDateString}</div></td>
                                            <td><div className="td-cont" title={this.approveStatCode(goods.approveStateCode)}>{this.approveStatCode(goods.approveStateCode)}</div></td>
                                            <td>
                                                <div className="td-cont" style={{paddingLeft: "0",paddingRight:"25px",textAlign: "right"}}>
                                                <ImallA href="javascript:void(0);" permissionCode="goods:goods:detail" className="gray-btn" text="查看" onClick={()=>actions.getGoodsDetail(goods.id,false)}/>
                                                <ImallA href="javascript:void(0);" permissionCode="goods:goods:update" className="gray-btn" text="修改" onClick={()=>actions.getGoodsDetail(goods.id,true)}/>
                                                <ImallA href="javascript:void(0);" permissionCode="goods:goods:enable" className="gray-btn" text={this.buildIsEnable(goods.isEnable)} onClick={()=>this.openEnableForm(goods.id,goods.isEnable,goods.goodsNm,)}/>
                                                {/*修改记录信息不完善,暂时屏蔽  <ImallA href="javascript:void(0);"  className="gray-btn" text="修改记录" permissionCode="goods:goods:log" onClick={()=>actions.queryRecordList(0,10,goods.id)}/>*/}
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
                {store.getState().todos.goodsAddFormState&&<GoodsAddForm store={store} actions={actions} />}
                {store.getState().todos.goodsUpdateFormState&&<GoodsUpdateForm store={store} actions={actions} />}
                {store.getState().todos.goodsDetailViewState&&<GoodsDetailView store={store} actions={actions} />}
                {store.getState().todos.enableFormState&&<EnableForm store={store} actions={actions} />}
                {store.getState().todos.recordPageView&&<GoodsEnableRecordList store={store} actions={actions} />}
            </div>
        );
    }
}
GoodsList.propTypes = {
    actions: PropTypes.object.isRequired
};

GoodsList.contextTypes = {
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

GoodsList = connect(
    mapStateToProps,
    mapDispatchToProps
)(GoodsList);

export default GoodsList
