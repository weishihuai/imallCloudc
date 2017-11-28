import React, {Component, PropTypes} from "react";

import SysJobEditForm from "./SysJobEditForm";
import SysJobAddForm from "./SysJobAddForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import SysJobRoleForm from "./SysJobRoleForm";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import CommonConfirmComponent from "../../../../common/component/CommonConfirmComponent";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import ImallA from "../../../../common/imallbutton/components/ImallA";
class SysJobList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.queryList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'portal:sysmgr:job:add',
            'portal:sysmgr:job:delete',
            'portal:sysmgr:job:update',
            'portal:sysmgr:job:fpjs',
        ]);

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.queryList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.queryList(params, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }


    submitData(data,state=false){
        const {params} = this.context.store.getState().todos;
        this.props.actions.saveAndUpdateData(data,state,params);
    }


    onSelectAll(){
        const {selectIds,page} = this.context.store.getState().todos;
        const {setSelectIds} = this.props.actions;
        const listSelectIds = page.content.length;
        const newSelectIds = [];
        if(selectIds<listSelectIds&&listSelectIds!=0){
            page.content.map((obj)=>{
                newSelectIds.push(obj.id);
            })
        }
        setSelectIds(newSelectIds);
    }

    deleteSelectIds(){
        const {deleteSelectIdsState,setDeleteFormState} = this.props.actions;
        const {selectIds,params} = this.context.store.getState().todos;
        deleteSelectIdsState(selectIds,params);
        setDeleteFormState(false);
    }

    setDeleteFormState(){
        const {setDeleteFormState} = this.props.actions;
        const {selectIds} = this.context.store.getState().todos;
        if(selectIds.length>0){
            setDeleteFormState(true);
        }
    }

    onRowSelect(select){
        const {selectIds} = this.context.store.getState().todos;
        const {setSelectIds} = this.props.actions;
        let newSelectedIds = [];
        if(selectIds.indexOf(select)>-1){
            newSelectedIds = selectIds.filter(id => id != select);
        }else {
            newSelectedIds = selectIds.concat(select);
        }

        setSelectIds(newSelectedIds);
    }

    componentDidMount(){

    }
    setRoleFormState(id){
        const {setRoleFormState} = this.props.actions;
        const sysRoles = this.context.store.getState().todos.sysRoles;
        if(sysRoles.length==0){
            //拿完数据便不再请求
            this.props.actions.findAllSysRoles();
        }
        setRoleFormState(true,id);
    }

    componentDidUpdate(){
        if($("input.select-role:checked").length>0){
            $('.guide').css("background","#2bcd73");
            $('.guide').attr("href","javascript:void(0);");
        }else{
            $('.guide').css("background","#aaa");
            $('.guide').removeAttr("href");
        }
    }
    render() {
        const actions = this.props.actions;
        const {showAddForm,showUpdate,setDeleteFormState} = this.props.actions;
        const {store} = this.context;
        const todos = store.getState().todos;
        const sysRoleList =todos.page.content || [];
        const editState =todos.editState ||"";
        const addState =todos.addState ;
        const selectIds =todos.selectIds ;
        const deleteFormState =todos.deleteFormState ;
        const roleFormState =todos.roleFormState ;
        const selectId =todos.selectId ;

        const page = store.getState().todos.page;
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
            hideSizePerPage: page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>岗位管理</h5>
                            <a  href="javascript:void(0);" >系统管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >岗位管理</a>
                        </div>
                        <div className="mt-rt">
                            <ImallA  href="javascript:void(0);"  permissionCode="portal:sysmgr:job:add"  className="added" text="添加" onClick={()=>{showAddForm(true)}}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="portal:sysmgr:job:delete"  className="guide"  text="删除" onClick={()=>{this.setDeleteFormState()}} />
                         </div>
                    </div>
                    <div className="mc" style={{paddingTop:"64px"}}>
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-checkbox2" onClick={()=>{this.onSelectAll()}}><label><input value="checkbox" type="checkbox" checked={page.content.length==selectIds.length&&page.content.length>0}/></label></th>
                                    <th className="post-name">岗位名称</th>
                                    <th className="post-code">岗位代码</th>
                                    <th className="post-describe">描述</th>
                                    <th className="inclusion-role">所包含角色</th>
                                    <th className="status">是否可用</th>
                                    <th className="operating" style={{width:"473px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { sysRoleList.length <= 0 &&
                                <tr >
                                    <th colSpan="8" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {sysRoleList.map((sysRole, index) => {
                                    return (
                                        <tr key={index}>
                                            <td onClick={()=>this.onRowSelect(sysRole.id)}><div className="td-cont"><input type="checkbox" className="select-role" checked={selectIds.indexOf(sysRole.id)>-1}/></div></td>
                                            <td><div className="td-cont">{sysRole.jobName}</div></td>
                                            <td><div className="td-cont">{sysRole.jobCode}</div></td>
                                            <td><div className="td-cont">{sysRole.description}</div></td>
                                            <td><div className="td-cont">{sysRole.roleNames}</div></td>
                                            <td><div className="td-cont">{sysRole.isAvailable=="Y"?"可用":"不可用"}</div></td>
                                            <td>
                                                <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                    <ImallA text="分配角色" permissionCode="portal:sysmgr:job:fpjs"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{this.setRoleFormState(sysRole.id)}}/>
                                                    <ImallA text="修改" permissionCode="portal:sysmgr:job:update"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{showUpdate(true,sysRole.id)}}/>
                                                </div>
                                            </td>
                                        </tr>
                                    )
                                })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                    {editState&&<SysJobEditForm  editState={editState}    actions={actions} onSubmit={(data) => this.submitData(data)}/>}
                    {addState&&<SysJobAddForm store={store} actions={actions} onSubmit={(data) => this.submitData(data,true)}/>}
                    {roleFormState&& <SysJobRoleForm  actions={actions}/>  }
                    {deleteFormState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="确认" text={"是否删除"} callback={() => this.deleteSelectIds()} close={() => setDeleteFormState(false)}/>}
                </div>
            </div>
        )
    }
}


SysJobList.contextTypes = {
    store: React.PropTypes.object
};


function mapDispatchToProps(dispatch){
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

function mapStateToProps(state) {
    return {
        state
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SysJobList);