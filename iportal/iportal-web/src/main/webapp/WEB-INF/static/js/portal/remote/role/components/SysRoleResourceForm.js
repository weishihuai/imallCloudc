import React, { PropTypes, Component } from 'react';

class SysRoleResourceForm extends Component {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        var $ = require("jquery");
        var ztree = require("ztree");
        const {store} = this.context;
        const selectId = store.getState().todos.selectId;
        var setting = {
            async: {
                enable: true,
                type: "post",
                url:iportal+"/sysMenu/buildMenuTreeFilterByRole.json",
                autoParam:["id"],
                otherParam: ["roleId", selectId]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#role_menu_asyn_tree").html("暂无资源！");
                    }
                },
                onClick:this.findResourceByMenu.bind(this)
            }
        };
        $.fn.zTree.init($("#role_menu_asyn_tree"), setting);
    }
    findResourceByMenu(event, treeId, treeNode){
        const {store} = this.context;
        const {saveOrDeleteRolePermission} = this.props;
        const selectId = store.getState().todos.selectId;
        var setting = {
            check: {noHalfCheck:true,enable: true},
            async: {
                enable: true,
                type: "post",
                url:iportal+"/sysResource/buildResourceTreeByMenu.json",
                autoParam:["id"],
                otherParam: {"menuId":treeNode.id,"roleId":selectId}
            },callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#role_menu_resource_asyn_tree").html("暂无资源！");
                    }
                },
                onCheck: function(event, treeId, treeNode){
                    saveOrDeleteRolePermission(selectId,treeNode.id,treeNode.checked);
                }
            }
        };
        $.fn.zTree.init($("#role_menu_resource_asyn_tree"), setting);
    }

    render() {
        const {setResourceFormState} = this.props;
        return(


        <div className="layer"  >
            <div className="layer-box layer-start layer-dm-menu">
                <div className="layer-header">
                    <span>分配角色资源</span>
                    <a href="javascript:void(0);" className="close" onClick={()=>{setResourceFormState(false)}} ></a>
                </div>
                <div className="layer-body">
                    <div className="dm-menu-1-2">
                        <ul id="role_menu_asyn_tree" className="ztree"></ul>
                    </div>
                    <div className="dm-menu-1-2">
                        <ul id="role_menu_resource_asyn_tree" className="ztree"></ul>
                    </div>
                </div>
                <div className="layer-footer">
                    <a href="javascript:void(0);" className="close2" onClick={()=>{setResourceFormState(false)}}>关闭</a>
                </div>
            </div>
        </div>

        );
    }
}


SysRoleResourceForm.contextTypes = {
    store : React.PropTypes.object
}
export default SysRoleResourceForm
