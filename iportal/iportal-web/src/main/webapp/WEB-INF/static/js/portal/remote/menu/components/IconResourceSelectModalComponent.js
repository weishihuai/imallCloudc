import React, { PropTypes, Component } from 'react';
import { Button,Modal } from 'react-bootstrap';
import {iconSelectModal,resourceSelectModal,reloadMenuAddUpdateForm} from '../actions';
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';

class IconResourceSelectModalComponent extends Component{
    constructor(props){
        super(props);
    }

    componentDidUpdate() {
        const {store} = this.context;
        var $ = require("jquery");
        var ztree = require("ztree");

        /*初始化资源树*/
        var setting = {
            view: {
                selectedMulti: false
            },
            async: {
                enable: true,
                type: "post",
                url:iportal+"/sysResource/buildResourceTreeByResourceType.json",
                autoParam:["id"],
                otherParam: ["resourceType", store.getState().todos.menuData.menuType=="MENU"?"LNK":store.getState().todos.menuData.menuType]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#resource_menu_asyn_tree").html("暂无此类型资源！");
                    }
                }
            }
        };
        $.fn.zTree.init($("#resource_menu_asyn_tree"), setting);
        /*初始化资源树  end*/

        var targetVal = store.getState().todos.menuData.icon;
        $("#icon_select_div").find("input[value='" + targetVal +"']").attr("checked",true);
    }


    static chooseResource(actions){
        var $ = require("jquery");
        var ztree = require("ztree");

        var treeObj = $.fn.zTree.getZTreeObj("resource_menu_asyn_tree");
        var nodes = treeObj.getSelectedNodes();
        if(nodes!=0){
            actions.reloadMenuAddUpdateForm(true,nodes[0].id,nodes[0].name,null);
        }
    }

    static chooseIcon(actions){
        var targetVal = $('#icon_select_div input[name="iconItem"]:checked ').val();
        $("#menu_icon_select").attr("xlink:href", "#" + targetVal);
        actions.reloadMenuAddUpdateForm(false,null,null,targetVal);
    }

    render(){
        const {actions,iconSelectModal,resourceSelectModal,reloadMenuAddUpdateForm} = this.props;
        const {store} = this.context;
        return (
            <div>
                <Modal show={store.getState().todos.resourceSelectModalState} onHide={()=>resourceSelectModal()} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>资源选择</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                            <div className="modal-body">
                                <ul id="resource_menu_asyn_tree" className="ztree" />
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-primary" value="Submit" onClick={()=> IconResourceSelectModalComponent.chooseResource(actions)} >确定</button>
                                <button type="button" className="btn btn-default" onClick={()=>resourceSelectModal()}>关闭</button>
                            </div>
                    </Modal.Body>
                </Modal>
                <Modal show={store.getState().todos.iconSelectModalState} onHide={()=>iconSelectModal()} backdrop="static">
                    <Modal.Header closeButton>
                        <Modal.Title>图标选择</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <div className="row" id="icon_select_div">
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-kehuguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-kehuguanli"></use>
                                    </svg>
                                客户管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-jifendingdan"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-jifendingdan"></use>
                                    </svg>
                                    积分订单</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-jianyandan"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-jianyandan"></use>
                                    </svg>
                                    检验单</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-minganguanjianzi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-minganguanjianzi"></use>
                                    </svg>
                                    敏感关键字</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-kefuzhichi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-kefuzhichi"></use>
                                    </svg>
                                    客服支持</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-peisongguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-peisongguanli"></use>
                                    </svg>
                                    配送管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-pinpai"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-pinpai"></use>
                                    </svg>
                                    品牌</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-qiyeleixing"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-qiyeleixing"></use>
                                    </svg>
                                    企业类型</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-pingjiaguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-pingjiaguanli"></use>
                                    </svg>
                                    评价管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-shangyeyunying"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-shangyeyunying"></use>
                                    </svg>
                                    商业运营</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-suoyinguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-suoyinguanli"></use>
                                    </svg>
                                    索引管理2</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-tuisongxiaoxi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-tuisongxiaoxi"></use>
                                    </svg>
                                    推送消息</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-wanglaidanwei"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-wanglaidanwei"></use>
                                    </svg>
                                    往来单位</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-xitongcanshushezhi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-xitongcanshushezhi"></use>
                                    </svg>
                                    系统参数设置</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-tuihuoguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-tuihuoguanli"></use>
                                    </svg>
                                    退货管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-xitongxiaoxi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-xitongxiaoxi"></use>
                                    </svg>
                                    系统消息</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-suoyinguanli1"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-suoyinguanli1"></use>
                                    </svg>
                                    索引管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-xitongpeizhi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-xitongpeizhi"></use>
                                    </svg>
                                    系统配置</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-xiaoximoban"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-xiaoximoban"></use>
                                    </svg>
                                    消息模板</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-xuqiudengji"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-xuqiudengji"></use>
                                    </svg>
                                    需求登记</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-xiaoxizhongxin"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-xiaoxizhongxin"></use>
                                    </svg>
                                    消息中心</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-yemianzhuangxiu"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-yemianzhuangxiu"></use>
                                    </svg>
                                    页面装修</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-youjian"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-youjian"></use>
                                    </svg>
                                    邮件</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zhaoshangdaili"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zhaoshangdaili"></use>
                                    </svg>
                                    招商代理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zhaoshangdailifenlei"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zhaoshangdailifenlei"></use>
                                    </svg>
                                    招商代理分类</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zhaoshanghezuo"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zhaoshanghezuo"></use>
                                    </svg>
                                    招商合作</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zixun"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zixun"></use>
                                    </svg>
                                    资讯</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zixun1"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zixun1"></use>
                                    </svg>
                                    资讯2</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zixunlanmu"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zixunlanmu"></use>
                                    </svg>
                                    资讯栏目</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-chanpin"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-chanpin"></use>
                                    </svg>
                                    产品</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-zigongsi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-zigongsi"></use>
                                    </svg>
                                    子公司</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-chanpinguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-chanpinguanli"></use>
                                    </svg>
                                    产品管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-chanpinpeizhi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-chanpinpeizhi"></use>
                                    </svg>
                                    产品配置</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-dayinmoban"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-dayinmoban"></use>
                                    </svg>
                                    打印模板</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-dailihezuo"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-dailihezuo"></use>
                                    </svg>
                                    代理合作</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-danjuguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-danjuguanli"></use>
                                    </svg>
                                    单据管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-dingdan"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-dingdan"></use>
                                    </svg>
                                    订单</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-duanxin"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-duanxin"></use>
                                    </svg>
                                    短信</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-dingdanguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-dingdanguanli"></use>
                                    </svg>
                                    订单管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-fankuiguanli"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-fankuiguanli"></use>
                                    </svg>
                                    反馈管理</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-fenleishezhi"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-fenleishezhi"></use>
                                    </svg>
                                    分类设置</label>
                            </div>
                            <div className="fa-hover col-md-3">
                                <label className="radio-inline">
                                    <input name="iconItem" type="radio" value="icon-guoqitixing"  />
                                    <svg className="imall-custom-big-icon" aria-hidden="true">
                                        <use xlinkHref="#icon-guoqitixing"></use>
                                    </svg>
                                    过期提醒</label>
                            </div>
                        </div>
                        <div  className="modal-footer"  style={{padding: "10px 15px"}}>
                           <div style={{float:"right"}}>
                               <button type="submit" className="btn btn-primary" id="submit" value="Submit" onClick={()=> IconResourceSelectModalComponent.chooseIcon(actions)} >确定</button>
                               <button type="button" className="btn btn-default" onClick={()=>iconSelectModal()} >关闭</button>
                           </div>
                        </div>
                    </Modal.Body>
                </Modal>
            </div>
        );
    }
}

IconResourceSelectModalComponent.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch){
    return bindActionCreators({
        resourceSelectModal,reloadMenuAddUpdateForm,iconSelectModal
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(IconResourceSelectModalComponent);