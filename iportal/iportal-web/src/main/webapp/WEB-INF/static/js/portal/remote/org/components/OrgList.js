import React, { PropTypes, Component } from 'react';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import OrgSearchForm from './OrgSearchForm';
import OrgButtonComponent from './OrgButtonComponent';
import OrgModalComponent from './OrgModalComponent';
import {PORTAL_ORG_LIST_ROW_SELECT,PORTAL_ORG_FIND_TREE,PORTAL_ORG_LIST_MULTI_ROW_SELECT,PORTAL_ORG_SEARCH,PORTAL_ORG_TREE_REFRESH} from '../constants/ActionTypes';

class OrgList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("","orgName","","",0,10);
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
                url:iportal+"/sysOrg/findOrgTree.json",
                autoParam:["id"]
            },
            callback: {
                onAsyncSuccess: function(event, treeId, treeNode, msg){
                    if(msg=="[ ]"){
                        $("#org_asyn_tree").html("暂无数据！");
                    }
                },
                onClick:this.zTreeOnClick.bind(this)
            }
        };
        $.fn.zTree.init($("#org_asyn_tree"), setting);
    }
    componentDidUpdate (){
        const {store} = this.context;

        if(store.getState().todos.isRefreshTree){
            var treeObj = $.fn.zTree.getZTreeObj("org_asyn_tree");
            if( treeObj.getSelectedNodes()>0){
                var nodes = treeObj.getSelectedNodes();
                var refreshNode = nodes[0].isParent?nodes[0]:nodes[0].getParentNode();
                treeObj.reAsyncChildNodes(refreshNode, "refresh");
            }else {
                treeObj.reAsyncChildNodes(treeObj.getNodes()[0].getParentNode(), "refresh");
            }
            store.dispatch({type:PORTAL_ORG_TREE_REFRESH,isRefreshTree:false});
        }

        $('#org_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.ids.length);
        if(store.getState().todos.ids.length>1){
            $('#org_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }

        if(store.getState().todos.pName==null||store.getState().todos.pName==undefined){
            var treeObj = $.fn.zTree.getZTreeObj("org_asyn_tree");
            var nodes = treeObj.getNodes();
            if(nodes.length>0){
                store.dispatch({type:PORTAL_ORG_FIND_TREE,treePId:nodes[0].id,pName:nodes[0].name});
            }
        }
    }
    zTreeOnClick(event, treeId, treeNode) {
        const {store} = this.context;
        store.dispatch({type:PORTAL_ORG_SEARCH,searchData:{isAvailable:"",searchName:"orgName",searchValue:""},isRefreshForm:true});
        store.dispatch({type:PORTAL_ORG_FIND_TREE,treePId:treeNode.id,pName:treeNode.name});
        this.props.actions.list("","orgName","",treeNode.id,0,10);
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
    onRowSelect(store,row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        //alert(`is selected: ${isSelected}, ${rowStr}`);
        store.dispatch({type:PORTAL_ORG_LIST_ROW_SELECT, isSelected:isSelected, obj:row});
    }
    onSelectAll(store,isSelected, rows) {
        var ids = [];
        if(isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                //    alert(rows[i].id);
                ids[num] = rows[i].id;
                num++;
            }
        }else {
            ids = [];
        }
        store.dispatch({type:PORTAL_ORG_LIST_MULTI_ROW_SELECT ,ids:ids});
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
                    <Imalla permissionCode="portal:sysmgr:zzgl:update" className="btn btn-link" onClick={()=>actions.portalOrgUpdateModal(row.id)} text="编辑" />
                </div>
            );
        }
        return(
                <div className="row">
                    <div className="col-md-4 col-lg-2">
                        <div className="panel">
                            <div className="panel-heading">
                                <h4 className="panel-title">组织管理</h4>
                            </div>
                            <div className="panel-body">
                                <ul id="org_asyn_tree" className="ztree"></ul>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8 col-lg-10">
                        <div className="panel">
                            <div className="panel-body">
                                {/*增加，修改，删除按钮区域*/}
                                <div id="org_bars_btn" className="clearfix">
                                    <OrgModalComponent actions={this.props.actions}  />
                                    <OrgButtonComponent actions={this.props.actions}  />
                                    {/*搜索*/}
                                    <OrgSearchForm  actions={this.props.actions}/>
                                </div>
                                {/*列表*/}
                                <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                                fetchInfo={fetchInfo}
                                                options={options}
                                                selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                                    <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                                    <TableHeaderColumn dataField="orgName" csvHeader='组织名称'>组织名称</TableHeaderColumn>
                                    <TableHeaderColumn dataField="orgCode" csvHeader='组织编码'>组织编码</TableHeaderColumn>
                                    <TableHeaderColumn dataField="zoneNames" csvHeader='所在区域'>所在区域</TableHeaderColumn>
                                    <TableHeaderColumn dataField="address" csvHeader='详细地址'>详细地址</TableHeaderColumn>
                                    <TableHeaderColumn dataField="phone" csvHeader='电话'>电话</TableHeaderColumn>
                                    <TableHeaderColumn dataField="fax" csvHeader='传真'>传真</TableHeaderColumn>
                                    <TableHeaderColumn dataField="isAvailable"  dataFormat={this.enumeFormat.bind(this)} csvHeader='是否可用' csvFormat={this.enumeFormat.bind(this)}>是否可用</TableHeaderColumn>
                                    <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="185" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                                </BootstrapTable>
                            </div>
                        </div>
                    </div>
                </div>
    );
    }
}

OrgList.propTypes = {
    actions: PropTypes.object.isRequired
}

OrgList.contextTypes = {
    store : React.PropTypes.object
}


export default OrgList
