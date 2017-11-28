import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {niftyNoty} from "../../../common/common";
export function fileSelectItems(fileIdArray){
    const empty = {
        type: types.FILE_SELECT_RESULT,
        fileIdArray:fileIdArray
    };
    return function (dispatch) {
        dispatch(empty);
        dispatch(showFileMgrModal());
    };
}

export function showFileMgrModal(){
    const empty = {
        type: types.FILE_MGR_MODAL
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function showFileMgrModalHasCallbackFunc(callbackFunc){
    const empty = {
        type: types.FILE_MGR_MODAL_HAS_CALL_BACK_FUNC,
        callbackFunc: callbackFunc
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function renameFileName(id,fileNm){
    return function (dispatch) {
        return fetch(iportal+'/sysFileLib/renameFileName.json',
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify({id:id,fileNm:fileNm})
            }
        ).then(function(response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }
            var $ = require("jquery");
            var ztree = require("ztree");

            var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
            var nodes = treeObj.getSelectedNodes();
            dispatch(loadFileSimple(nodes,1));

        }).catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
    }
}

export function updateCategory(id,categoryName){
    return function (dispatch) {
        return fetch(iportal+'/sysFileCategory/updateCategory.json',
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify({id:id,categoryName:categoryName})
            }
        ).then(function(response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }
            actionSuccessResult(false,true);
            dispatch({type : types.FILE_EDIT_FILE_CATEGORY_MODAL, isShow: false});
        }).catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
    }
}

export function saveCategory(id,categoryName){
    return function (dispatch) {
        return fetch(iportal+'/sysFileCategory/saveCategory.json',
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify({id:id,categoryName:categoryName})
            }
        ).then(function(response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }
            actionSuccessResult(false,false);
            dispatch({type : types.FILE_CREATE_FILE_CATEGORY_MODAL});
        }).catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
    }
}

export function deleteCategory(id){
    return function (dispatch) {
        return fetch(iportal+'/sysFileCategory/delete.json',
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify(id)
            }
        ).then(function(response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }
            actionSuccessResult(true,false);
            dispatch({type : types.FILE_DELETE_FILE_CATEGORY_MODAL, isShow: false});
        }).catch(function(err) {
            console.log('Fetch Error :-S', err);

        });
    }
}

export function actionSuccessResult(allRefresh,isUpdate){
    var $ = require("jquery");
    var ztree = require("ztree");

    var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
    var nodes = treeObj.getSelectedNodes();
    var refreshNode = !allRefresh&&nodes!=null&&nodes.length>0? (!isUpdate&&nodes[0].isParent?nodes[0]:nodes[0].getParentNode()) : null;

    treeObj.reAsyncChildNodes(refreshNode, "refresh");
    niftyNoty("系统消息：操作成功");
    // $.niftyNoty({type: "success", container : '#fileMgrPage', html : "系统消息：操作成功", timer : 3000});
}

export function showFileUploadModal(){
    const empty = {
        type: types.FILE_UPLOAD_MODAL
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function showFileTreeModal(){
    const empty = {
        type: types.FILE_TREE_MODAL
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function changeFileCategory(ids,fileCategoryId){

    return function (dispatch) {
        return fetch(iportal+'/sysFileLib/changeFileCategory.json?fileCategoryId='+fileCategoryId,
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify( ids )
            }
        )
            .then(response =>
                response.json().then(json => ({
                        status: response.status,
                        json
                    })
                ))
            .then(
                ({ status, json }) => {
                    if (status == 200) {
                        var $ = require("jquery");
                        var ztree = require("ztree");

                        var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
                        var nodes = treeObj.getSelectedNodes();
                        dispatch(loadFileSimple(nodes,1));
                    } else {

                    }
                },
                err => {

                }
            )
    }
}

export function deleteFile(ids){

    return function (dispatch) {
        return fetch(iportal+'/sysFileLib/delete.json',
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify( ids )
            }
        ).then(function(response) {
            var $ = require("jquery");
            var ztree = require("ztree");

            var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
            var nodes = treeObj.getSelectedNodes();
            dispatch(loadFileSimple(nodes,1));
            dispatch({type : types.FILE_BATCH_DELETE_FILE_MODAL, isShow: false});
        }).catch(function(ex) {
            console.log('Fetch Error :-S', err);
        });
    }
}

export function downloadFiles(ids){

    return function (dispatch) {
        return fetch(iportal+'/sysFileLib/downloadFiles.json',
            {
                mode: 'cors',
                credentials: 'include',
                method: 'POST',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body:JSON.stringify( ids )
            }
        ).then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                response.json().then(function(data) {
                    window.open(data.fileUrl);
                });
            }
            )
            .catch(function(err) {
                console.log('Fetch Error :-S', err);
            });
    }
}

export function loadFileSimple(nodes,currentPage){
    return function (dispatch) {
        var fileCategoryId = nodes==0||nodes==undefined?1:nodes[0].id;
        var sort = $("#sortBtn").find("i").hasClass("glyphicon-arrow-up")?"id,asc":"id,desc";
        var searchContentVal = $("#searchContent").val();
        var fileTypeCode = $("#fileTypeInput").val();
        dispatch(loadFile(currentPage,10,sort,fileCategoryId,fileTypeCode,searchContentVal));
    };
}

/*加载文件*/
export function loadFile(pageNumber,pageSize,sort,fileCategoryId,fileTypeCode,searchContent){
    return function (dispatch) {
        return fetch(iportal+"/sysFileLib/findFileLib.json?page="+(pageNumber-1)+"&size="+pageSize+"&sort="+sort+"&fileCategoryId="+fileCategoryId+"&fileTypeCode="+fileTypeCode+"&searchContent="+searchContent,
            {
                mode: 'cors',
                credentials: 'include',
                method: 'GET'
            }
        ).then(response =>
                response.json().then(json => ({
                        status: response.status,
                        json
                    })
                ))
            .then(
                ({ status, json }) => {
                    if (status == 200) {
                        require("bs-pagination-local");
                        require("bs-pagination");
                        require("ztree");
                        require("fancybox");


                        var obj = json;
                        var mainHtml="";
                        for(var i=0; i<obj.content.length; i++){
                            var key = i;
                            var value = obj.content[i];
                           const name= value.fileNm.length>9?value.fileNm.substring(0,9)+"...":value.fileNm

                            var viewLarge = '';
                            var imgClass = '';
                            if(value.fileTypeCode=='IMAGE'){
                                imgClass= "smallImg";
                                viewLarge = ' <a class="pic-popup-f imgLargeItem" href="javascript:;"  rel="item'+key+'" title="查看大图"></a>';

                            }

                            mainHtml+='' +
                                '<div class="fileLibItem item" fileTypeCode="'+ value.fileTypeCode +'" FILE_NM="'+ value.fileNm +'" sysFileUrl="'+ value.sysFileUrl +'"  fileLibId="'+value.id+'" smallFileUrl="'+value.smallFileUrl+'" sysFileId="'+value.sysFileId+'">'+
                                    '<div class="caption thumbnail">' +
                                        '<div class="thumbnail-img">' +
                                            '<a id="item'+key+'" class="'+imgClass+'" href="'+value.sysFileUrl+'" title="'+value.fileNm+'"  >' +
                                                '<img width="160" height="160" class="img-responsive" src="'+filterFileUrl(value.sysFileUrl, value.smallFileUrl, value.fileTypeCode)+'" alt="">' +
                                            '</a>' +
                                            '<p class="pic-text">'+name+'</p>'+
                                            '<p class="pic-date">'+value.fileDate+'</p>'+
                                            '<p class="file-name-inp">' +
                                                '<input fileLibId="'+value.id+'" type="text" placeholder="" value="'+value.fileNm+'" >' +
                                            '</p>' +
                                            '<p class="file-name-op  pic-name-mo">' +
                                                '<a class="pic-name-moc cancelRename" href="javascript:;">×</a>' +
                                                '<a class="pic-name-mod renameCommit" href="javascript:;">√</a>' +
                                            '</p>' +

                                            '<div class="pic-popup ">' + viewLarge +
                                                '<a class="pic-popup-l imgCopyItem" href="javascript:;"  imgUrl="'+value.sysFileUrl+'" title="复制链接"></a>'+
                                                '<a class="pic-popup-x renameItem" href="javascript:;" fileNm="'+value.fileNm+'" title="重命名"></a>'+
                                                '<a class="pic-popup-s deleteItem" href="javascript:;" fileLibId="'+value.id+'" title="删除"></a>'+
                                            '</div>'+
                                        '</div>' +
                                        '<span class="selected-icon"></span>' +
                                    '</div>' +
                                '</div>';
                        }
                        //将图片数据拼装显示到页面上
                        $("#mainHtml").html(mainHtml);

                        //查看大图  暂不需要点击图片显示大图
                        $(".smallImg").fancybox({
                            'titlePosition' : 'inside',
                            overlayOpacity:0.5
                        });

                        //点击查看大图按钮显示大图
                        $(".imgLargeItem").each(function(){
                            $(this).bind("click",function(event){
                                event.stopPropagation();
                                $("#"+$(this).attr("rel")).trigger("click");
                            })
                        });

                        //选择文件
                        $(".fileLibItem").each(function(){
                            $(this).bind("click",function(event){
                                console.log("选择文件函数被触发了2");
                                event.stopPropagation();
                                if($(this).hasClass("active")){
                                    $(this).removeClass("active");
                                }else{
                                    $(this).addClass("active");
                                }

                                var selectNum = $(".item.active").length;
                                if(selectNum==0){
                                    $("#selectNum").html("您还未选择任何文件");
                                }else{
                                    $("#selectNum").html("您已选择"+selectNum+"个文件");
                                }
                            });
                        });


                        //点击查看大图按钮显示大图
                        if ( window.clipboardData ) {
                            $(".imgCopyItem").each(function(){
                                $(this).bind("click",function(event){
                                    event.stopPropagation();
                                    window.clipboardData.setData("Text", $(this).attr("imgUrl"));
                                    niftyNoty("链接已复制，Ctrl+V粘贴");
                                })
                            });
                        } else {
                            $(".imgCopyItem").each(function(){
                                $(this).bind("click",function(event){
                                    event.stopPropagation();
                                    niftyNoty("浏览器不支持一键复制，请手动复制链接："+$(this).attr("imgUrl"));
                                })
                            });
                        }

                        //点击重命名
                        $(".renameItem").each(function(){
                            $(this).bind("click",function(event){
                                event.stopPropagation();
                                $(this).parents(".thumbnail").addClass("edit");
                            });

                        });

                        //重命名取消
                        $(".cancelRename").each(function(){
                            $(this).bind("click",function(event){
                                event.stopPropagation();
                                $(this).parents(".thumbnail").removeClass("edit");
                            });
                        });

                        //重命名提交
                        $(".renameCommit").each(function(){
                            $(this).bind("click",function(event){
                                event.stopPropagation();
                                var input = $(this).parents(".thumbnail").find("input");
                                var id = $(input).attr("fileLibId");
                                var name = $(input).val();
                                if(name==""){
                                    return;
                                }
                                dispatch(renameFileName(id,name));
                            });
                        });

                        //删除单个文件
                        $(".deleteItem").each(function(){
                            $(this).bind("click",function(event){
                                event.stopPropagation();
                                var id = $(this).attr("fileLibId");
                                var ids = new Array(id);
                                dispatch(deleteFile(ids));
                            });
                            $("#selectNum").html("您还未选择任何文件");
                        });


                        /*若是图片则返回图片，若不是图片则返回对应文件的封面*/
                        function filterFileUrl(sysFileUrl,smallFileUrl,fileTypeCode){
                            if(fileTypeCode=='IMAGE'){
                                return smallFileUrl==null||smallFileUrl==""?iportal+"/static/img/100.jpg":smallFileUrl;
                            }

                            var fileType = sysFileUrl.substring(sysFileUrl.lastIndexOf(".")).toUpperCase();
                            if(fileType==".DOC"||fileType==".DOCX"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/docx_win.png";
                            }
                            if(fileType==".SWF"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/fla.png";
                            }
                            if(fileType==".TXT"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/text.png";
                            }
                            if(fileType==".XLS"||fileType==".XLSX"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/xlsx_win.png";
                            }
                            if(fileType==".ZIP"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/zip.png";
                            }
                            if(fileType==".RAR"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/rar.png";
                            }
                            if(fileType==".PDF"){
                                return iportal+"/static/lib/plugins/html5_fileupload/control/images/icon/pdf.png";
                            }
                        }
                        dispatch({
                            type:types.FILE_BATCH_SET_FILE_LIST,
                            json:json
                            })
                    } else {

                    }
                },
                err => {

                }
            )
    }

}



