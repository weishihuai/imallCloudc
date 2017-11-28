import React, { PropTypes, Component } from 'react';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import ResourceSearchForm from './ResourceSearchForm';
import ResourceButtonComponent from './ResourceButtonComponent';
import ResourceModalComponent from './ResourceModalComponent';
import {PORTAL_RESOURCE_LIST_ROW_SELECT,PORTAL_RESOURCE_FIND_TREE,PORTAL_RESOURCE_LIST_MULTI_ROW_SELECT,PORTAL_RESOURCE_SEARCH,PORTAL_RESOURCE_TREE_REFRESH} from '../constants/ActionTypes';


class ResourceList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("","resourceName","","",0,10);
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
                url:iportal+"/sysResource/findResourceTree.json",
                autoParam:["id"]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#resource_asyn_tree").html("暂无数据！");
                    }
                },
                onClick:this.zTreeOnClick.bind(this)
            }
        };
        $.fn.zTree.init($("#resource_asyn_tree"), setting);
    }
    componentDidUpdate (){
        const {store} = this.context;
        if(store.getState().todos.isRefreshTree){
            var treeObj = $.fn.zTree.getZTreeObj("resource_asyn_tree");
            if( treeObj.getSelectedNodes()>0){
                var nodes = treeObj.getSelectedNodes();
                var refreshNode = nodes[0].isParent?nodes[0]:nodes[0].getParentNode();
                treeObj.reAsyncChildNodes(refreshNode, "refresh");
            }else {
                treeObj.reAsyncChildNodes(treeObj.getNodes()[0].getParentNode(), "refresh");
            }
            store.dispatch({type:PORTAL_RESOURCE_TREE_REFRESH,isRefreshTree:false});
        }
        $('#resource_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.ids.length);
        if(store.getState().todos.ids.length>1){
            $('#resource_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }
    }
    zTreeOnClick(event, treeId, treeNode) {
        const {store} = this.context;
        store.dispatch({type:PORTAL_RESOURCE_SEARCH, searchData:{isAvailable:"",searchName:"resourceName",searchValue:""},isRefreshForm:true});
        store.dispatch({type:PORTAL_RESOURCE_FIND_TREE,treePId:treeNode.id});
        this.props.actions.list("","resourceName","",treeNode.id,0,10);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const searchData = store.getState().todos.searchData;
        const treePId = store.getState().todos.treePId;
        this.props.actions.list(searchData.isAvailable,searchData.searchName,searchData.searchValue,treePId,0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const searchData = store.getState().todos.searchData;
        const treePId = store.getState().todos.treePId;
        this.props.actions.list(searchData.isAvailable,searchData.searchName,searchData.searchValue,treePId,page-1,sizePerPage);
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
    resourceTypeFormat(value){
        if(value == "ELM"){
            return "元素";
        }else if(value=="BTN"){
            return "按钮";
        }else if(value=="LNK"){
            return "链接";
        }else if(value=="MDL"){
            return "模块";
        }else if(value=="APP") {
            return "应用";
        }else{
            return "";
        }
    };
    onRowSelect(store,row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        store.dispatch({type:PORTAL_RESOURCE_LIST_ROW_SELECT, isSelected:isSelected, obj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var ids = [];
        if(isSelected){
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                //    alert(rows[i].id);
                ids[num] = rows[i].id;
                num++;
            }
        }else {
            ids = [];
        }
        store.dispatch({type:PORTAL_RESOURCE_LIST_MULTI_ROW_SELECT ,ids:ids});
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
            selected:store.getState().todos.ids
        };
        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:zygl:update" className="btn btn-link" onClick={()=>actions.portalResourceUpdateModal(row.id)} text="修改" />
                    <Imalla permissionCode="portal:sysmgr:zygl:mgrUrl" className="btn btn-link" onClick={()=>actions.portalResourceUrlMgrModal(row.id, "",false,0,10)} text="管理" />
                </div>
            );
        }
        return(
                <div className="row">
                    <div className="col-md-4 col-lg-2">
                        <div className="panel">
                            <div className="panel-heading">
                                <h4 className="panel-title">资源管理</h4>
                            </div>
                            <div className="panel-body">
                                <ul id="resource_asyn_tree" className="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8 col-lg-10">
                        <div className="panel">
                            {/*列表*/}
                            <div className="panel-body" id="resourceActionBtnParam">
                                <div id="resource_bars_btn" className="clearfix">
                                    <ResourceModalComponent actions={this.props.actions}  />
                                    <ResourceButtonComponent actions={this.props.actions}  />
                                    {/*搜索*/}
                                    <ResourceSearchForm  actions={this.props.actions}/>
                                </div>
                                {/*增加，修改，删除*/}
                                <BootstrapTable data={page.content} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                                fetchInfo={fetchInfo}
                                                options={options}
                                                selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                                    <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                                    <TableHeaderColumn dataField="resourceName" csvHeader='资源名称'  width="160">资源名称</TableHeaderColumn>
                                    <TableHeaderColumn dataField="permissionCode" csvHeader='权限编码'  width="280">权限编码</TableHeaderColumn>
                                    <TableHeaderColumn dataField="resourceType" dataFormat={this.resourceTypeFormat.bind(this)} csvHeader='资源类型' csvFormat={this.resourceTypeFormat.bind(this)} width="80">资源类型</TableHeaderColumn>
                                    <TableHeaderColumn dataField="routerKey" csvHeader='路由Key'>路由Key</TableHeaderColumn>
                                    <TableHeaderColumn dataField="routerTemplateJs" csvHeader='路由模版JS'>路由模版JS</TableHeaderColumn>
                                    <TableHeaderColumn dataField="isAvailable"  dataFormat={this.enumeFormat.bind(this)} csvHeader='是否可用' csvFormat={this.enumeFormat.bind(this)} width="80">是否可用</TableHeaderColumn>
                                    <TableHeaderColumn dataField="appCname" csvHeader='所属应用' width="80">所属应用</TableHeaderColumn>
                                    <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="185" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                                </BootstrapTable>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

ResourceList.propTypes = {
    actions: PropTypes.object.isRequired
}

ResourceList.contextTypes = {
    store : React.PropTypes.object
}


export default ResourceList
