import React, { PropTypes, Component } from 'react';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import MenuSearchForm from './MenuSearchForm';
import MenuButtonComponent from './MenuButtonComponent'
import MenuModalComponent from './MenuModalComponent'
import IconResourceSelectModalComponent from './IconResourceSelectModalComponent'
import {PORTAL_MENU_LIST_ROW_SELECT,PORTAL_MENU_FIND_TREE,PORTAL_MENU_LIST_MULTI_ROW_SELECT,PORTAL_MENU_TREE_REFRESH} from '../constants/ActionTypes';

class MenuList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("","",0,10);
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
                url:iportal+"/sysMenu/findMenuTree.json",
                autoParam:["id"]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#menu_asyn_tree").html("暂无数据！");
                    }
                },
                onClick:this.zTreeOnClick.bind(this)
            }
        };
        $.fn.zTree.init($("#menu_asyn_tree"), setting);
    }

    componentDidUpdate (){
        const {store} = this.context;

        if(store.getState().todos.isRefreshTree){
            var treeObj = $.fn.zTree.getZTreeObj("menu_asyn_tree");
            if( treeObj.getSelectedNodes()>0){
                var nodes = treeObj.getSelectedNodes();
                var refreshNode = nodes[0].isParent?nodes[0]:nodes[0].getParentNode();
                treeObj.reAsyncChildNodes(refreshNode, "refresh");
            }else {
                treeObj.reAsyncChildNodes(treeObj.getNodes()[0].getParentNode(), "refresh");
            }
            store.dispatch({type:PORTAL_MENU_TREE_REFRESH,isRefreshTree:false});
        }


        $('#menu_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.menuIds.length);
        if(store.getState().todos.menuIds.length>1){
            $('#menu_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }
    }

    zTreeOnClick(event, treeId, treeNode) {
        const {store} = this.context;
        const searchData = store.getState().todos.searchData;

        store.dispatch({type:PORTAL_MENU_FIND_TREE,treePId:treeNode.id});
        this.props.actions.list(searchData.menuName,treeNode.id,0,10);
    }
    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const searchData = store.getState().todos.searchData;
        const treePId = store.getState().todos.treePId;
        this.props.actions.list(searchData.menuName,treePId,0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const searchData = store.getState().todos.searchData;
        const treePId = store.getState().todos.treePId;
        this.props.actions.list(searchData.menuName,treePId,page-1,sizePerPage);
    }
    enumeFormat(value) {
        if (value == "N") {
            return "否";
        } else if (value == "Y") {
            return "是";
        } else {
            return "";
        }
    };
    menuTypeFormat(value){
        if(value == "APP"){
            return "应用";
        }else if(value=="MDL"){
            return "模块";
        }else if(value=="MENU"){
            return "菜单";
        }else if(value=="BTN"){
            return "按钮";
        }else{
            return "";
        }
    };
    onRowSelect(store,row, isSelected, e) {
        store.dispatch({type:PORTAL_MENU_LIST_ROW_SELECT, isSelected:isSelected, menuObj:row});
    }
    onSelectAll(store,isSelected, rows) {
        var menuIds = [];
        if(isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                menuIds[num] = rows[i].id;
                num++;
            }
        }else {
            menuIds = [];
        }
        store.dispatch({type:PORTAL_MENU_LIST_MULTI_ROW_SELECT ,menuIds:menuIds});
    }
    renderShowsTotal(start, to, total) {
        if(total!=0){
            if(to==total){
                return (
                    <span>显示第&nbsp;{start}&nbsp;到第&nbsp;{to}&nbsp;条记录&nbsp;,&nbsp; 总共&nbsp;{total}&nbsp;条记录</span>
                );
            }else{
                return (
                    <span>显示第&nbsp;{start}&nbsp;到第&nbsp;{to}&nbsp;条记录&nbsp;,&nbsp; 总共&nbsp;{total}&nbsp;条记录</span>
                );
            }
        }
    }
    onExportToCSV() {
        const {store} = this.context;
        return store.getState().todos.page.content;
    }
    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const page = store.getState().todos.page;
        const number=page.number+1;
        const options = {
            sizePerPage: 10,
            sizePerPageList: [5, 10, 20],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "<",
            nextPage: ">",
            firstPage: "<<",
            lastPage: ">>",
            paginationShowsTotal: this.renderShowsTotal,
            onExportToCSV: this.onExportToCSV.bind(this),
            noDataText: '暂无数据'
        };
        const fetchInfo={
            dataTotalSize: page.totalElements
        };
        const selectRowProp = {
            mode: 'checkbox',
            clickToSelect: true,
            onSelect: (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store,isSelected, rows),
            selected:store.getState().todos.menuIds
        };
        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:cdgl:update" className="btn btn-link" onClick={()=>actions.portalMenuUpdateModal(row.id)} text="编辑" />
                </div>
            );
        }
        return(
            <div className="row">
                <div className="col-md-4 col-lg-2">
                    <div className="panel">
                        <div className="panel-heading">
                            <h4 className="panel-title">菜单管理</h4>
                        </div>
                        <div className="panel-body">
                            <ul id="menu_asyn_tree" className="ztree"></ul>
                        </div>
                    </div>
                </div>
                <div className="col-md-8 col-lg-10">
                    <div className="panel">
                        {/*增加，修改，删除*/}
                        <div className="panel-body">
                            <div id="menu_bars_btn" className="clearfix">
                                <IconResourceSelectModalComponent actions={this.props.actions}  />
                                <MenuModalComponent actions={this.props.actions}  />
                                <MenuButtonComponent actions={this.props.actions}  />
                                {/*搜索*/}
                                <MenuSearchForm  actions={this.props.actions}/>
                            </div>
                            {/*列表*/}
                            <BootstrapTable data={page.content} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                            fetchInfo={fetchInfo}
                                            options={options}
                                            selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                                <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                                <TableHeaderColumn dataField="menuName" csvHeader='名称'>名称</TableHeaderColumn>
                                <TableHeaderColumn dataField="menuType"  dataFormat={this.menuTypeFormat.bind(this)} csvHeader='类型' csvFormat={this.menuTypeFormat.bind(this)}>类型</TableHeaderColumn>
                                <TableHeaderColumn dataField="resourceName" csvHeader='关联资源'>关联资源</TableHeaderColumn>
                                <TableHeaderColumn dataField="isAvailable"  dataFormat={this.enumeFormat.bind(this)} csvHeader='是否可用' csvFormat={this.enumeFormat.bind(this)}>是否可用</TableHeaderColumn>
                                <TableHeaderColumn dataField="orderby" csvHeader='顺序号'>顺序号</TableHeaderColumn>
                                <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="185" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                            </BootstrapTable>
                        </div>
                    </div>
                </div>
            </div>
        );

    }
}

MenuList.propTypes = {
    actions: PropTypes.object.isRequired
}

MenuList.contextTypes = {
    store : React.PropTypes.object
}


export default MenuList
