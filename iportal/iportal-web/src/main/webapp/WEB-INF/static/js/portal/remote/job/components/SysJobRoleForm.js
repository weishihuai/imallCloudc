import React, { PropTypes, Component } from 'react';

class SysJobRoleForm extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount(){

    }
    componentDidMount() {
        const {store} = this.context;

        const selectId = store.getState().todos.selectId;

    }

    onRowSelect(roleSelect){
        const {selectRoleIds,selectId} = this.context.store.getState().todos;
        const {deleteJobRole,saveJobRole} = this.props.actions;
        var roleIds= [];
        roleIds.push(roleSelect);
        if(selectRoleIds.indexOf(roleSelect)>-1){
            //删除
            deleteJobRole(selectId,roleIds);
        }else {
            //保存
            saveJobRole(selectId,roleIds);
        }


    }

    onSelectAll(){
        const {selectRoleIds,sysRoles,selectId} = this.context.store.getState().todos;
        const {deleteJobRole,saveJobRole} = this.props.actions;


        if(selectRoleIds.length==sysRoles.length&&sysRoles.length!=0){
            //去除全部
            const roleIds= [];
            sysRoles.map((obj)=>{
                if(selectRoleIds.indexOf(obj.id)>=-1){
                    //删除 选中
                    roleIds.push(obj.id);
                }
            });
            deleteJobRole(selectId,roleIds);
        }else {
            // 选中全部
            const roleIds= [];
            sysRoles.map((obj)=>{
                if(selectRoleIds.indexOf(obj.id)==-1){
                    //保存 未选中

                    roleIds.push(obj.id);
                }
            });
            saveJobRole(selectId,roleIds);
        }


    }

    render(){

        const {setRoleFormState} = this.props.actions;
        const {store} = this.context;
        const sysRoles = store.getState().todos.sysRoles;
        const selectRoleIds = store.getState().todos.selectRoleIds;
        return(
           <div className="layer">
               <div className="layer-box drug-check layer-assign-roles w598" >
                   <div className="layer-header">
                       <span>分配管理角色</span>
                       <a href="javascript:void(0);" className="close" onClick={()=>{setRoleFormState(false)}}></a>
                   </div>
                   <div className="layer-body">
                       <div className="md-box">
                           <div className="box-mt"></div>
                           <div className="box-mc">
                               <table>
                                   <thead>
                                   <tr>
                                       <th className="post-checkbox"></th>
                                       <th className="post-name">角色名称</th>
                                       <th className="post-describe">描述</th>
                                   </tr>
                                   </thead>
                                   <tbody>
                                   {sysRoles.map((sysRole,index)=>{
                                       return(
                                           <tr key={index}>
                                               <td onClick={()=>this.onRowSelect(sysRole.id)}>
                                                   <div className="td-cont"><label><input type="checkbox" checked={selectRoleIds.indexOf(sysRole.id)>-1}/></label></div>
                                               </td>
                                               <td>
                                                   <div className="td-cont">{sysRole.roleName}</div>
                                               </td>
                                               <td>
                                                   <div className="td-cont">{sysRole.description}</div>
                                               </td>
                                           </tr>
                                       )
                                   })}


                                   </tbody>
                               </table>
                           </div>
                       </div>
                   </div>
                   <div className="layer-footer">
                       <a href="javascript:void(0);" className="close2" onClick={()=>{setRoleFormState()}}>关闭</a>
                   </div>
               </div>
           </div>


        );
    }
}


SysJobRoleForm.contextTypes = {
    store : React.PropTypes.object
}

export default SysJobRoleForm