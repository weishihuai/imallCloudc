import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {showFileUploadModal} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';


class FileUploadModalComponent extends Component{
    constructor(props){
        super(props);
    }
    componentDidMount() {
        var actions = this.props.actions;
        var $ = require("jquery");
        var ztree = require("ztree");
        require("imallUploadCore");
        require("imallUploadCtrl");

        $("#imallFileMgr").zyUpload({
            width            :   "590px",                 // 宽度
            height           :   "400px",                 // 宽度
            itemWidth        :   "120px",                 // 文件项的宽度
            itemHeight       :   "100px",                 // 文件项的高度
            url              :   iportal+"/sysFileLib/upload.html",  // 上传文件的路径
            multiple         :   true,                    // 是否可以多个文件上传
            dragDrop         :   true,                    // 是否可以拖动上传文件
            del              :   true,                    // 是否可以删除文件
            finishDel        :   false,  				  // 是否在上传文件完成后删除预览

            /* 外部获得的回调接口 */
            onSelect: function(files, allFiles){                    // 选择文件的回调方法 files:当前选择了以下文件  allFiles:之前没上传的文件

            },
            onDelete: function(file, surplusFiles){                     // 删除一个文件的回调方法  file:当前删除了此文件    surplusFiles:当前剩余的文件

            },
            onSuccess: function(file){// 文件上传成功的回调方法   file:上传成功文件

            },
            onFailure: function(file){                    // 文件上传失败的回调方法    file:上传失败文件

            },
            onComplete: function(responseInfo){// 上传完成的回调方法    回调：responseInfo
                var treeObj = $.fn.zTree.getZTreeObj("file_mgr_asyn_tree");
                var nodes = treeObj.getSelectedNodes();

                var fileCategoryId = nodes==0||nodes==undefined?1:nodes[0].id;
                var sort = $("#sortBtn").find("i").hasClass("glyphicon-arrow-up")?"id,asc":"id,desc";
                var searchContentVal = $("#searchContent").val();
                var fileTypeCode = $("#fileTypeInput").val();
                actions.loadFile(1,10,sort,fileCategoryId,fileTypeCode,searchContentVal);
            }
        });
    }

    showFileUploadModal() {
        const {showFileUploadModal} = this.props;
        showFileUploadModal();
        //删除关闭前未上传的文件
        $(".file_del").each(function(){
            ZYFILE.funDeleteFile(parseInt($(this).attr("data-index")), true);
        })
    }

    render(){

        return (
                <div className="layer" style={{ "zIndex": "1000"}}>
                    <div className="layer-box drug-check w600">
                        <div className="layer-header">
                            <span>文件上传</span> <a href="javascript:void(0);" onClick={() => this.showFileUploadModal()} className="close"/>
                        </div>
                        <div className="layer-body">
                        <div id="imallFileMgr" className="imallFileMgr"/>
                        </div>
                        <div className="layer-footer" style={{padding: "20px 3px 19px 0"}}>
                            <a href="javascript:void(0);" className="confirm file_del_all" onClick={() => this.showFileUploadModal()}>返回</a>
                        </div>
                    </div>
                </div>
        );
    }
}

FileUploadModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        state,
        modalState: state.fileMgrTodos.modalState
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        showFileUploadModal
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FileUploadModalComponent);