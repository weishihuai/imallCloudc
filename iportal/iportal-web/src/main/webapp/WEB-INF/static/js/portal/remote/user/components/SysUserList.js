import React, {Component, PropTypes} from "react";
import SysUserPassWordUpdateForm from "./SysUserPassWordUpdateForm";
import SysUserEditForm from "./SysUserEditForm";
import SysUserAddForm from "./SysUserAddForm";
import SysUserDetailForm from "./SysUserDetailForm";
import SysUserConfirmComponent from "./SysUserConfirmComponent";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import SysUserSearchForm from "./SysUserSearchForm";
import SysUserModifyLogList from "./SysUserModifyLogList";
import IMallPaginationBar from "../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../common/imallbutton/actions";
import ImallA from "../../../../common/imallbutton/components/ImallA";


class SysUserList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.sysUserList(params, params.page, params.size);
        this.props.portalOperationalAuth([
            'portal:sysmgr:user:add',
            'portal:sysmgr:user:updateLog',
            'portal:sysmgr:user:update',
            'portal:sysmgr:user:disabled',
            'portal:sysmgr:user:enabled',
            'portal:sysmgr:user:resetPassword',
            'portal:sysmgr:user:detail',
        ]);

    }


    onSizePerPageList(sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.sysUserList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.sysUserList(params, page - 1, sizePerPage);
    }

    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }


    submitData(data,state=false){
        const {params} = this.context.store.getState().todos;
        if(state){
            require("jquery-md5");
            data=Object.assign({},data,{
                password:$.md5(data.password)
            });
        }

        return this.props.actions.saveAndUpdateData(data,state,params);
    }

    updatePassword(data){
        const {updatePassword} = this.props.actions;
        const {selectId,params} = this.context.store.getState().todos;
        const newData=Object.assign({},data,{
            id:selectId
        });
       return updatePassword(newData,params);
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

    changeSelectIdsState(isEnable){
        const {setEnableFormState,setIsEnable} = this.props.actions;
        const {selectIds} = this.context.store.getState().todos;
        if(selectIds.length>0){
        setIsEnable(isEnable=="Y"?true:false);//保存 区分禁用还是启用
        setEnableFormState(true);//打开弹窗
        }
    }
    updateSelectIdsState(){
        const {updateSelectIdsState} = this.props.actions;
        const {selectIds,params,isEnable} = this.context.store.getState().todos;
        const data = {
            ids: selectIds,
            isEnable: isEnable?"Y":"N"

        };
        updateSelectIdsState(data,params);
    }


    getSelectIdsState(){

        const {selectIds,page,isEnable} = this.context.store.getState().todos;
        let nameList = [];
        page.content.map(obj=>{
            if($.inArray(parseInt(obj.id),selectIds)!=-1){
                nameList.push(obj.userName);
            }
        })

        let name = "";
        if(isEnable){
            name = "启用";
        }else {
            name = "禁用";
        }
        name = name + nameList.join("、");
        return{name:name,isEnable:isEnable}
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
        // $(".table-box table").colResizable();

    }
    render() {
        const actions = this.props.actions;
        const {showAddForm,showUpdate,showDetail,showUpdatePassword,setEnableFormState, logListModel} = this.props.actions;
        const {store} = this.context;
        const todos = store.getState().todos;
        const sysUserList =todos.page.content || [];
        const detailObject =todos.detailObject || {};
        const detailState =todos.detailState ||"";
        const editState =todos.editState ||"";
        const addState =todos.addState ;
        const logListState =todos.logListState ;
        const selectIds =todos.selectIds ;
        const enableFormState =todos.enableFormState ;
        const { isUpdatePassWord} = todos;
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
                            <h5>员工管理</h5>
                            <a  href="javascript:void(0);" >系统管理</a>
                            <span>></span>
                            <a  href="javascript:void(0);" >员工管理</a>
                            <SysUserSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA  href="javascript:void(0);"  permissionCode="portal:sysmgr:user:add"  className="added" text="添加" onClick={()=>{showAddForm(true)}}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="portal:sysmgr:user:disabled"  className="guide"  text="启用员工" onClick={()=>{this.changeSelectIdsState("Y")}}/>
                            <ImallA  href="javascript:void(0);"  permissionCode="portal:sysmgr:user:enabled"  className="guide"  text="禁用员工" onClick={()=>{this.changeSelectIdsState("N")}}/>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box initial">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-checkbox2" style={{width:"59px"}} onClick={()=>{this.onSelectAll()}}><label><input value="checkbox" type="checkbox" checked={page.content.length==selectIds.length&&page.content&&page.content.length>0}/></label></th>
                                    <th className="username">账号名称</th>
                                    <th className="name2">姓名</th>
                                    <th className="phone-number">手机</th>
                                    <th className="age">性别</th>
                                    <th className="email" style={{width:"200px"}}>邮箱</th>
                                    <th className="time">创建时间</th>
                                    <th className="status">状态</th>
                                    <th className="operating" style={{width: "430px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { sysUserList.length <= 0 &&
                                <tr >
                                    <th colSpan="9" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {sysUserList.map((sysUser, index) => {
                                    return (
                                        <tr key={index}>
                                            <td onClick={()=>this.onRowSelect(sysUser.id)}><div className="td-cont"><input type="checkbox" checked={selectIds.indexOf(sysUser.id)>-1}/></div></td>
                                            <td><div className="td-cont">{sysUser.userName}</div></td>
                                            <td><div className="td-cont">{sysUser.realName}</div></td>
                                            <td><div className="td-cont">{sysUser.mobile}</div></td>
                                            <td><div className="td-cont">{sysUser.sex}</div></td>
                                            <td><div className="td-cont">{sysUser.email}</div></td>
                                            <td><div className="td-cont">{sysUser.createTimeString}</div></td>
                                            <td><div className="td-cont">{sysUser.isEnable=="Y"?"启用":"禁用"}</div></td>
                                            <td>
                                                <div className="td-cont" style={{paddingLeft: "0", paddingRight: "25px", textAlign: "right"}}>
                                                    <ImallA text="查看" permissionCode="portal:sysmgr:user:detail"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{showDetail(true,sysUser.id)}}/>
                                                    <ImallA text="修改" permissionCode="portal:sysmgr:user:update"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{showUpdate(true,sysUser.id)}}/>
                                                    <ImallA text="重置密码" permissionCode="portal:sysmgr:user:resetPassword"  href="javascript:void(0);"  className="gray-btn" onClick={()=>{showUpdatePassword(true,sysUser.id)}}/>
                                                    <ImallA text="修改记录" permissionCode="portal:sysmgr:user:updateLog"  href="javascript:void(0);" onClick={()=> {logListModel(true, sysUser.id)}}  className="gray-btn" />
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
                    {isUpdatePassWord&& <SysUserPassWordUpdateForm  showUpdatePassword={showUpdatePassword} actions={actions}  onSubmit={(data) => this.updatePassword(data)}/>}
                    {detailState&&<SysUserDetailForm    detailObject={detailObject}   store={store} actions={actions} />}
                    {editState&&<SysUserEditForm  editState={editState}    actions={actions} onSubmit={(data) => this.submitData(data)}/>}
                    {addState&&<SysUserAddForm store={store} actions={actions} onSubmit={(data) => this.submitData(data,true)}/>}
                    {enableFormState && <SysUserConfirmComponent store={store} actions={actions} zIndex="999" title="确认" text={this.getSelectIdsState().name}
                                                                             callback={(result) => this.updateSelectIdsState()} close={() => setEnableFormState(false)}/>}
                    {logListState&&<SysUserModifyLogList store={store} actions={actions} />}
                </div>
            </div>
        )
    }
}


SysUserList.contextTypes = {
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

export default connect(mapStateToProps, mapDispatchToProps)(SysUserList);