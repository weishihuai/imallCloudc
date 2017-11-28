import React, {PropTypes, Component} from "react";
import {Button, Modal} from "react-bootstrap";
import * as TodoActions from "../actions";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import FileTreeModalComponent from "./FileTreeModalComponent";
import FileUploadModalComponent from "./FileUploadModalComponent";
import CommonSingleInputComponent from "../../component/CommonSingleInputComponent";
import CommonConfirmComponent from "../../component/CommonConfirmComponent";
import {niftyNoty} from "../../../common/common";
import IMallPaginationBar from '../../imallpagination/components/IMallPaginationBar';
import {
    FILE_CREATE_FILE_CATEGORY_MODAL,
    FILE_EDIT_FILE_CATEGORY_MODAL,
    FILE_DELETE_FILE_CATEGORY_MODAL,
    FILE_BATCH_DELETE_FILE_MODAL
} from "../constants/ActionTypes";
class FileMgrModalComponent extends Component{
    constructor(props){
        super(props);
    }


    getFileSelectItemsHasCallbackFunc(actions, callbackFUnc){
        var $ = require("jquery");
        var i = 0;
        var fileIdArray = [];
        $(".fileLibItem").each(function(){
            if($(this).hasClass("active")){
                fileIdArray[i] = {fileNm: $(this).attr("file_nm"),
                    sysFileLibId: parseInt($(this).attr("fileLibId")),
                    smallFileUrl:$(this).attr("smallFileUrl"),
                    fileId:$(this).attr("sysFileId"),
                    fileUrl: $(this).attr("sysFileUrl"),
                    fileTypeCode: $(this).attr("fileTypeCode")};
                i++;
            }
        });
        if(fileIdArray.length == 0){

            niftyNoty("请先选择文件！");
            return;
        }
        actions.fileSelectItems(fileIdArray);
        callbackFUnc(fileIdArray);
    }
    closeAddForm(){
        this.props.actions.showFileMgrModal()

    }
    showAddUpdateWin(title,value,fun){
        var bootbox = require("bootbox");
        bootbox.prompt({
            title: title,
            value:value,
            buttons: {
                confirm: {
                    label: "确定"
                },
                cancel: {
                    label: "取消"
                }
            },
            callback: fun
        });
    }
    getTreeSelectItem(){
        var $ = require("jquery");
        var ztree = require("ztree");

        //获取选择的树节点
        var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
        var nodes = treeObj.getSelectedNodes();
        var id = nodes!=null&&nodes.length>0?nodes[0].id:null;//没有选择则默认为1;
        var parentId = nodes!=null&&nodes.length>0?nodes[0].pId:null;
        var name = nodes!=null&&nodes.length>0?nodes[0].name:null;
        return {id: id, parentId: parentId ,name:name};
    }
    addFileCategory(isShow){
        this.context.store.dispatch({
            type : FILE_CREATE_FILE_CATEGORY_MODAL,
            isShow: isShow
        });
    };

    addFileCategoryCallback(result) {
        const {actions} = this.props;
        let node = this.getTreeSelectItem();
        if (result) {
            actions.saveCategory(node.id ==null?1:node.id,result);
        }
    }

    editFileCategory(isShow){
        if(isShow) {
            var node = this.getTreeSelectItem();
            if(node.id==null){
                niftyNoty("请选择要编辑的目录");
                return;
            }
        }

        this.context.store.dispatch({
            type : FILE_EDIT_FILE_CATEGORY_MODAL,
            isShow: isShow
        });
    }

    editFileCategoryCallback(result) {
        const {actions} = this.props;
        let node = this.getTreeSelectItem();
        if (result) {
            actions.updateCategory(node.id,result);
        }
    }

    deleteFileCategory(isShow){
        if(isShow) {
            var node = this.getTreeSelectItem();
            if(node.id==null){
                niftyNoty("请选择要删除的目录");
                return;
            }
            if(node.id==1){
                niftyNoty("根目录不允许删除");
                return;
            }
        }

        this.context.store.dispatch({
            type : FILE_DELETE_FILE_CATEGORY_MODAL,
            isShow: isShow
        });
    }

    deleteFileCategoryCallback() {
        const {actions} = this.props;
        let node = this.getTreeSelectItem();
        actions.deleteCategory(node.id);
    }

    showConfirmWin(fun){
        var $ = require("jquery");
        var bootbox = require("bootbox");
        bootbox.confirm({
            locale:'zh_CN',
            message : "<h4 class='text-thin'>系统提示</h4><p>您确定要删除吗？</p>",
            buttons: {
                confirm: {
                    label: "确定"
                },
                cancel: {
                    label: "取消"
                }
            },
            callback : fun,
            animateIn: 'bounceIn',
            animateOut : 'bounceOut'
        });
    }

    /*加载文件*/
    loadFileForPage(nodes,page,size){
    var fileCategoryId = nodes==0||nodes==undefined?1:nodes[0].id;
    var sort = $('#sortLabel').find("a").hasClass("time-diminishing")?"id,desc":"id,asc";
    var searchContentVal = $(searchContent).val();
    var fileTypeCode = $("#fileTypeInput").val();
    this.props.actions.loadFile(page,size,sort,fileCategoryId,fileTypeCode,searchContentVal);
}
    componentDidMount() {
        var actions = this.props.actions;
        var $ = require("jquery");
        var ztree = require("ztree");
        var searchContent = $('#searchContent');
        var sortBtn = $('#sortBtn');

        /*监听搜素事件*/
        $('#btn_submit').click(function(){
            loadFile(getSelectNode(),1);
        });

        /*切换文件分类*/
        $("#fileSearchTypeMenu").find('li').each(function(){
            $(this).on("click",function () {
                var type = $(this).data('type');
                var text = $(this).children('a').html();
                $(this).parents(".fileSearchTypeMenu").find('.icon-share').html(text);
                $("#fileTypeInput").val(type);

                /*重新加载文件*/
                loadFile(getSelectNode(),1);
            })
        });

        /*切换排序*/
        $("#sortLabel").click(function(){
            var target = $(this).find("a");
            var targetI = $(this).find("i");

            if($(target).hasClass("time-diminishing")){
                $(target).removeClass("time-diminishing");
                $(target).addClass("time-increasing");
                $(targetI).removeClass("glyphicon-arrow-down");
                $(targetI).addClass("glyphicon-arrow-up");


            }else{
                $(target).removeClass("time-increasing");
                $(target).addClass("time-diminishing");
                $(targetI).addClass("glyphicon-arrow-down");
                $(targetI).removeClass("glyphicon-arrow-up");
            }

            /*重新加载文件*/
            loadFile(getSelectNode(),1);
        });

        /*文件树初始化*/
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                type: "post",
                url:iportal+"/sysFileCategory/findFileCategoryTree.json",
                autoParam:["id"]
            },
            callback: {
                onAsyncSuccess: zTreeOnAsyncSuccess,
                onClick: zTreeOnClick
            }
        };
        $.fn.zTree.init($("#file_mgr_asyn_tree"), setting);

        /*树加载成功后回调*/
        function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {

        }

        /*树点击事件*/
        function zTreeOnClick(event, treeId, treeNode) {
            var nodes = getSelectNode();

            //获取选择的节点的路径
            // getSelectNodePath(nodes);

            //加载文件
            loadFile(nodes,1);

        }

        /*获取选择的路径*/
        function getSelectNodePath(nodes){
            var nodeList = nodes[0].getPath();

            var valueStr = "";
            for(var i=0; i<nodeList.length; i++){
                var key = i;
                var value = nodeList[i];
                if(valueStr==""){
                    valueStr = "<li><span>当前文件：</span><a href='javascript:void(0);'>"+value.name+"</a></li>";
                }else{
                    if(key==nodeList.length-1){
                        valueStr = valueStr + "<li class='active'>"+value.name+"</li>";
                    }else{
                        valueStr = valueStr + "<li><a href='javascript:void(0);'>"+value.name+"</a></li>";
                    }

                }
            }
            $("#treeSelectPath").html(valueStr);
        }

        /*加载文件*/
        function loadFile(nodes,currentPage){
            var fileCategoryId = nodes==0||nodes==undefined?1:nodes[0].id;
            var sort = $('#sortLabel').find("a").hasClass("time-diminishing")?"id,desc":"id,asc";
            var searchContentVal = $(searchContent).val();
            var fileTypeCode = $("#fileTypeInput").val();
            actions.loadFile(currentPage,10,sort,fileCategoryId,fileTypeCode,searchContentVal);
        }

        /*获取选择的节点*/
        function getSelectNode(){
            var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
            return treeObj.getSelectedNodes();
        }

        /*初始化文件*/
        loadFile(undefined,1);
    }
    uploadFile(actions){
        var $ = require("jquery");
        var ztree = require("ztree");
        require("imallUploadCore");

        var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
        var nodes = treeObj.getSelectedNodes();
        var id = nodes!=null&&nodes.length>0?nodes[0].id:null;//没有选择则默认为1;
        if(id==null){
            niftyNoty("请选择要上传的目录！");
            return;
        }
        ZYFILE.treeCategoryId = id;
        actions.showFileUploadModal();
    }
    downloadFile(actions){
        var i = 0;
        var fileIdArray = [];
        $(".fileLibItem").each(function(){
            if($(this).hasClass("active")){
                fileIdArray[i] = $(this).attr("fileLibId");
                i++;
            }
        });
        if(fileIdArray.length == 0){
            niftyNoty("请选择要下载的文件");
            return;
        }
        actions.downloadFiles(fileIdArray);
    }

    batchDeleteFile(isShow){
        var i = 0;
        var fileIdArray = [];
        $(".fileLibItem").each(function(){
            if($(this).hasClass("active")){
                fileIdArray[i] = $(this).attr("fileLibId");
                i++;
            }
        });


        if(isShow && fileIdArray.length == 0) {
            niftyNoty("请选择要删除的文件");
            return;
        }

        this.context.store.dispatch({
            type : FILE_BATCH_DELETE_FILE_MODAL,
            isShow: isShow
        });

    }

    batchDeleteFileCallback() {
        const {actions} = this.props;
        var i = 0;
        var fileIdArray = [];
        $(".fileLibItem").each(function(){
            if($(this).hasClass("active")){
                fileIdArray[i] = $(this).attr("fileLibId");
                i++;
            }
        });
        actions.deleteFile(fileIdArray);
        $("#selectNum").html("您还未选择任何文件");
    }




    onSizePerPageList(sizePerPage) {
        var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
        var nodes =  treeObj.getSelectedNodes();
        this.loadFileForPage(nodes,0,sizePerPage);

    }
    onPageChange(page,sizePerPage) {
        var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
        var nodes =  treeObj.getSelectedNodes();
        this.loadFileForPage(nodes,page,sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }
    render(){
        const {actions} = this.props;
        const {showFileTreeModal} = this.props.actions;
        const {store} = this.context;
        const {fileTreeModalState, modalState, createFileCategoryModalState, editFileCategoryModalState, deleteFileCategoryModalState, batchDeleteFileModalState} = store.getState().fileMgrTodos;
        const page = store.getState().fileMgrTodos.page;
        const number = page.number + 1;
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
            isWindow:true
        };

        return (
            <div>
                {createFileCategoryModalState && <CommonSingleInputComponent store={store} actions={actions} title="添加目录" zIndex="999" label="目录名"
                                                                   callback={(result) => this.addFileCategoryCallback(result)} close={() => this.addFileCategory(false)}/>}
                {editFileCategoryModalState && <CommonSingleInputComponent store={store} actions={actions} title="修改目录名称" zIndex="999" label="目录名" value={this.getTreeSelectItem().name}
                                                                   callback={(result) => this.editFileCategoryCallback(result)} close={() => this.editFileCategory(false)}/>}
                {deleteFileCategoryModalState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="删除目录" text={"是否删除" + this.getTreeSelectItem().name}
                                                                         callback={(result) => this.deleteFileCategoryCallback(result)} close={() => this.deleteFileCategory(false)}/>}
                {batchDeleteFileModalState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="删除文件" text="是否删除选中文件"
                                                                         callback={(result) => this.batchDeleteFileCallback(result)} close={() => this.batchDeleteFile(false)}/>}
                {fileTreeModalState&&<FileTreeModalComponent store={store} actions={actions} />}
                {modalState&&<FileUploadModalComponent store={store} actions={actions} />}
                <div className="layer" style={{ "zIndex": "888"}}>
                    <div className="layer-box layer-info layer-order layer-add-pic w1290">
                        <div className="layer-header">
                            <span>添加图片</span>
                            <a href="javascript:void(0);" className="close" onClick={()=>{this.closeAddForm()}}/>
                        </div>
                        <div className="layer-body">
                            <div className="md-box">
                                <div className="box-mc clearfix">
                                    <div className="fl">
                                        <div className="fl-td">
                                            <a onClick={()=>this.addFileCategory(true)} href="javascript:void(0);" className="gray-btn">新建目录</a>
                                            <a onClick={()=>this.editFileCategory(true)} href="javascript:void(0);" className="gray-btn">修改名称</a>
                                            <a onClick={()=>this.deleteFileCategory(true)} href="javascript:void(0);" className="gray-btn">删除</a>
                                        </div>
                                        <div className="fl-dd">
                                            <ul id="file_mgr_asyn_tree" className="ztree"/>
                                        </div>
                                    </div>
                                    <div className="fr">
                                        <div className="fr-td clearfix">
                                            <div className="item"><a onClick={()=>this.uploadFile(actions)} href="javascript:void(0);" className="file-upload">文件上传</a></div>
                                            <div className="item item-r">
                                                <label>文件名称
                                                    <input type="text" id="searchContent"/>
                                                </label>
                                                <a style={{border:"none"}} href="javascript:void(0);" id="btn_submit"className="gray-btn">查询</a>

                                            </div>

                                            <div className="item item-yxs" role="group">
                                                <a onClick={()=>showFileTreeModal()} className="add-pic-remove" href="javascript:;"><em></em>移动</a>
                                                <a onClick={()=>this.downloadFile(actions)} className="add-pic-download" href="javascript:;"><em></em>下载</a>
                                                <a onClick={()=>this.batchDeleteFile(true)} className="add-pic-del" href="javascript:;"><em></em>删除</a>
                                                <span id="selectNum">您还未选择任何文件</span>
                                            </div>
                                            <div className="item item-r">
                                                <label>
                                                    <span>类型</span>
                                                    <select name="fileTypeInput" id="fileTypeInput">
                                                        <option value="">全部</option>
                                                        <option value="IMAGE">图像</option>
                                                        <option value="TEXT">文本</option>
                                                        <option value="OFFICE">OFFICE</option>
                                                        <option value="SOUND">声音</option>
                                                        <option value="FLASH">FLASH</option>
                                                        <option value="VIDEO">视频</option>
                                                    </select>

                                                </label>
                                                <label id="sortLabel">
                                                    <a href="javascript:void(0);" className="time-successively time-diminishing" id="sortBtn">时间<em></em><i className="glyphicon glyphicon-arrow-down" /></a>
                                                </label>
                                            </div>
                                        </div>
                                        <div id="mainHtml" className="fr-dd clearfix">
                                        </div>
                                        <div className="fr-footer">
                                            <IMallPaginationBar options={options} actions={this.props.actions}/>
                                        </div>
                                    </div>
                                </div>
                                <div className="layer-footer">
                                    <button type="button" className="confirm" style={{border:"none"}} id="chooseSubmit" value="Submit" onClick={()=>this.getFileSelectItemsHasCallbackFunc(actions, store.getState().fileMgrTodos.callbackFunc)} >确定</button>
                                    <a href="javascript:void(0);" className="cancel" onClick={()=>{this.closeAddForm()}}>取消</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        );
    }
}

FileMgrModalComponent.contextTypes = {
    store : React.PropTypes.object
};

function mapStateToProps(state) {
    return {
        fileMgrModalState: state.fileMgrTodos.fileMgrModalState,
        state,
    }
}

function mapDispatchToProps(dispatch) {
    return {
        actions: bindActionCreators(TodoActions, dispatch)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(FileMgrModalComponent);