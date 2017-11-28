import React, {PropTypes, Component} from "react";
import {Button, Modal} from "react-bootstrap";
import {showFileTreeModal} from "../actions";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {niftyNoty} from "../../../common/common";

class FileTreeModalComponent extends Component{
    constructor(props){
        super(props);
    }

    componentDidMount() {
        var $ = require("jquery");
        var ztree = require("ztree");

        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                type: "post",
                url:iportal+"/sysFileCategory/findFileCategoryTree.json",
                autoParam:["id"]
            }
        };
        $.fn.zTree.init($("#file_tree_asyn_tree"), setting);
    }

    chooseFileTree(actions){
        var $ = require("jquery");
        var ztree = require("ztree");
        
        var treeObj = $.fn.zTree.getZTreeObj("file_tree_asyn_tree");
        var nodes = treeObj.getSelectedNodes();
        if(nodes.length!=0){
            var treeId = nodes[0].id;
            var i = 0;
            var fileIdArray = [];
            $(".fileLibItem").each(function(){
                if($(this).hasClass("active")){
                    fileIdArray[i] = $(this).attr("fileLibId");
                    i++;
                }
            });
            if(fileIdArray.length == 0){
                niftyNoty("系统消息：请先选择文件！");
                return;
            }

            actions.changeFileCategory(fileIdArray,treeId);
        }else{
            niftyNoty("系统消息：请先选择文件目录！");
            return;
        }
        actions.showFileTreeModal();
    }

    render(){
        const {actions,showFileTreeModal} = this.props;
        const {store} = this.context;
        return (

        <div className="layer" style={{ "zIndex": "1000"}}>
            <div className="layer-box drug-check " style={{width:"450px"}}>
                <div className="layer-header">
                    <span>选择文件分类</span> <a href="javascript:void(0);" onClick={() => showFileTreeModal()} className="close"/>
                </div>
                <div className="layer-body">
                    <div>
                        <div id="treeSelectNoty"></div>
                        <ul id="file_tree_asyn_tree" className="ztree"/>
                    </div>
                </div>
                <div className="layer-footer" style={{padding: "70px 3px 19px 0"}}>
                    <a href="javascript:void(0);" className="confirm"  onClick={()=>this.chooseFileTree(actions)} >保存</a>
                    <a href="javascript:void(0);" className="cancel" onClick={()=>showFileTreeModal()} >关闭</a>
                </div>
            </div>
        </div>

        );
    }
}

FileTreeModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        fileTreeModalState: state.fileMgrTodos.fileTreeModalState
    }
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        showFileTreeModal
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(FileTreeModalComponent);