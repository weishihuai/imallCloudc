/**
 * Created by yys on 2016/11/21.
 */
import React, { PropTypes, Component } from 'react';
import { Button, Modal } from 'react-bootstrap';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {showArticleCategoryModal} from '../actions';


//初始化默认选中节点
const defaultCheck = (ids) => {
    var $ = require("jquery");
    var ztree = require("ztree");

    let treeObj = $.fn.zTree.getZTreeObj('goods_doc_category_tree');

    ids.map((id) => {
        treeObj.getNodesByParam('id', id).map((zNode) => {
            treeObj.checkNode(zNode, true, true);
            let pNode = zNode.getParentNode();
            while(pNode != null) {
                treeObj.expandNode(pNode, true, false);
                pNode = pNode.getParentNode();
            }
        });
    });
}

//初始化默认选中节点 select
const defaultSelect = (ids) => {
    var $ = require("jquery");
    var ztree = require("ztree");

    let treeObj = $.fn.zTree.getZTreeObj('goods_doc_category_tree');

    ids.map((id) => {
        treeObj.getNodesByParam('id', id).map((zNode) => {
            treeObj.selectNode(zNode, true, true);
            let pNode = zNode.getParentNode();
        });
    });
}

//过滤选中的叶子节点
const filterCheckedLeaves = (node) => {
    return (!node.isParent && node.checked);
}

const transformToRawNode = (zNode) => {
    let pathName = '';
    zNode.getPath().map((nd) => {
        pathName += '-'+nd.name;
    });

    let rawNode = {
        id : zNode.id,
        name : zNode.name,
        nodeCode : zNode.nodeCode,
        isParent : zNode.isParent,
        checked : zNode.checked,
        nodeObject: zNode.nodeObject,
        children: zNode.children,
        pid: zNode.pid,
        path: zNode.getPath(),
        pathName: pathName.length==0? '':pathName.substring(1)
    }

    return rawNode;
}


/**
 * 运营分类选择组件
 * 使用：
 *      1、引入组件及方法
 *      2、调用方法showArticleCategoryModal(true， )
 *          showArticleCategoryModal(isShow, onSuccess=()=>{}, options={})
 *              参数 isShow true|false    是否显示该组件
 *              参数 onSuccess：可选，    选择后的回调方法
 *                      -- ids          选中的节点id
 *                      -- objs         选中的节点数据对象
 *              参数 options：可选，
 *                      -- ids:         默认选中的节点
 *
 */
class GoodsCategoryComponent extends Component {

    handleClose() {
        this.props.showArticleCategoryModal(false);
    }

    noticeSelectResult() {
        const {store} = this.context;
        const {options} = store.getState().goodsCategoryTodos;

        var $ = require("jquery");
        var ztree = require("ztree");

        let treeObj = $.fn.zTree.getZTreeObj('goods_doc_category_tree');
        let zNodes = new Object();
        if (options && options.multiSelectedEnable==false) { //默认多选，
            zNodes = treeObj.getSelectedNodes();
        } else {
            zNodes = treeObj.getNodesByFilter(filterCheckedLeaves);
        }

        let ids = new Array();
        let objs = new Array();

        zNodes.map((zNode) => {
            let node = transformToRawNode(zNode);
            ids.push(node.id);
            objs.push(node);
        });

        const {selectArticleCategoryOnSuccess} = store.getState().goodsCategoryTodos;

        selectArticleCategoryOnSuccess(ids, objs);
        this.handleClose();
    }


    constructor(props){
        super(props);
    }

    componentDidMount() {
        const {store} = this.context;
        const {options} = store.getState().goodsCategoryTodos;

        var $ = require("jquery");
        var ztree = require("ztree");


        /*初始化资源树*/
        var setting = {
            view: {
                selectedMulti: true
            },
            check: {
                noHalfCheck:true,
                enable: true
            },
            async: {
                enable: true,
                type: "get",
                url:iportal+"/goodsCategory/findByParentId.json",
                autoParam:["id"],
                otherParam: ["subMaxLayer", 100]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#goods_doc_category_tree").html("暂无商品分类！");
                    }

                    if (options!=undefined && options.ids instanceof Array) {
                        if (options.multiSelectedEnable!=undefined && options.multiSelectedEnable==false) {
                            defaultSelect(options.ids);
                        } else {
                            defaultCheck(options.ids);
                        }
                    }
                },
                onCheck: function(event, treeId, treeNode){
                    /*                    console.log(event);
                     console.log(treeId);
                     console.log(treeNode);*/
                }
            }
        };

        if (options && options.multiSelectedEnable==false) { //默认多选，
            setting = Object.assign({}, setting, {
                view: {
                    selectedMulti: false
                },
                check: {}
            });
        }

        $.fn.zTree.init($("#goods_doc_category_tree"), setting);
    }

    render(){
        return (
            <div className="layer">
                <div className="layer-box layer-info w960">
                    <div className="layer-header">
                        <span>选择分类</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>this.handleClose()}/>
                    </div>
                    <div className="layer-body">
                        <ul id="goods_doc_category_tree" className="ztree" />
                    </div>
                    <div className="layer-footer">
                        <button href="javascript:void(0);" className="confirm" style={{border:"none"}}  type="button"  onClick={()=>this.noticeSelectResult()}>确定选中</button>
                        <a href="javascript:void(0);" className="cancel" onClick={()=>this.handleClose()}>取消</a>
                    </div>
                </div>
            </div>
        );
    }
}



GoodsCategoryComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return {
        articleCategoryModal: state.goodsCategoryTodos.articleCategoryModal
    };
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        showArticleCategoryModal
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GoodsCategoryComponent);