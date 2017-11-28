import React, { PropTypes, Component } from 'react';

class SysRoleMenuForm extends Component {
    constructor(props) {
        super(props)
    }

    componentDidMount() {
        const {store} = this.context;
        const {saveOrDeleteRoleMenu} = this.props;
        const selectId = store.getState().todos.selectId;
        var $ = require("jquery");
        var ztree = require("ztree");
        var setting = {
            check: {
                noHalfCheck:true,
                enable: true
            },
            async: {
                enable: true,
                type: "post",
                url:iportal+"/sysMenu/buildMenuTreeByRole.json",
                autoParam:["id"],
                otherParam: ["roleId", selectId]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#menu_asyn_tree").html("暂无菜单！");
                    }
                },
                onCheck: function(event, treeId, treeNode){
                    saveOrDeleteRoleMenu(selectId,treeNode.id,treeNode.checked);
                }
            }
        };
        $.fn.zTree.init($("#menu_asyn_tree"), setting);
    }

    render(){
        const {setMenuFormState} = this.props;

        return(


            <div className="layer" >
                <div className="layer-box layer-start layer-dm-menu">
                    <div className="layer-header">
                        <span>分配管理菜单</span>
                        <a href="javascript:void(0);" className="close" onClick={()=>{setMenuFormState(false)}}></a>
                    </div>
                    <div className="layer-body">
                        <div className="dm-menu-1-1">
                            <ul id="menu_asyn_tree" className="ztree"></ul>
                        </div>
                    </div>
                    <div className="layer-footer">
                        <a href="javascript:void(0);" className="close2" onClick={()=>{setMenuFormState(false)}}>关闭</a>
                    </div>
                </div>
            </div>

        );
    }
}


SysRoleMenuForm.contextTypes = {
    store : React.PropTypes.object
}

export default SysRoleMenuForm