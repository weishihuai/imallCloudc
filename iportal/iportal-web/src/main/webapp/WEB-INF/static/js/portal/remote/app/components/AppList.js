import React, { PropTypes, Component } from 'react';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import AppButtonComponent from './AppButtonComponent';
import AppModalComponent from './AppModalComponent';
import AppSearchForm from './AppSearchForm';
import {PORTAL_APP_LIST_ROW_SELECT,PORTAL_APP_LIST_MULTI_ROW_SELECT,PORTAL_APP_UPDATE_EXPORT_ROWS} from '../constants/ActionTypes';
import FileMgrModalComponent from '../../../../common/filemgr/components/FileMgrModalComponent';

class AppList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.applist("","","",0,10);
    }

    componentDidUpdate (){
        const {store} = this.context;
        $('#app_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.appIds.length);
        if(store.getState().todos.appIds.length>1){
            $('#app_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }
    }
    onSizePerPageList(sizePerPage) {
        this.props.actions.applist("","","",0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.applist("","","",page-1,sizePerPage);
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

    onRowSelect(store, row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        store.dispatch({type:PORTAL_APP_LIST_ROW_SELECT, isSelected:isSelected, appObj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var appIds = [];
        if (isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                //    alert(rows[i].id);
                appIds[num] = rows[i].id;
                num++;
            }
        }else {
            appIds = [];
        }
        store.dispatch({type:PORTAL_APP_LIST_MULTI_ROW_SELECT ,appIds:appIds});
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
        return store.getState().todos.exportRows;
    }

    onExportToCSVFunc (_this, isExportAll) {
        if(isExportAll){
            const callback = ()=>{
                _this.refs.appTable.handleExportCSV();
            };
            _this.refs.appSearchForm.search(65000,  callback);//Excel2003的最大行是65536行,从Excel2007开始最大行是1048576
        }
        else{
            const {store} = _this.context;
            const exportRows = store.getState().todos.page.content;
            store.dispatch({type: PORTAL_APP_UPDATE_EXPORT_ROWS,exportRows:exportRows});
            _this.refs.appTable.handleExportCSV();
        }
    };

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const _this = this;
        const selectRowProp = {
            mode: 'checkbox',
            clickToSelect: true,
            onSelect: (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store,isSelected, rows),
            selected:store.getState().todos.appIds
        };
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
            noDataText: '暂无数据',
        };
        const fetchInfo={
            dataTotalSize: page.totalElements
        };
        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:yygl:update" className="btn btn-link" onClick={()=>actions.portalAppUpdateModal(row.id)} text="编辑" />
                </div>
            );
        }
        return(
            <div>
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">应用管理</h3>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        <div id="app_bars_btn" className="clearfix">
                            <FileMgrModalComponent store={store} actions={this.props.actions}  />
                            <AppModalComponent actions={this.props.actions}  />
                            <AppButtonComponent actions={this.props.actions} exportAllFunc={()=>this.onExportToCSVFunc(_this, true)} exportCurrentFunc={()=>this.onExportToCSVFunc(_this, false)} />
                            <AppSearchForm ref='appSearchForm' actions={this.props.actions}/>
                        </div>
                        <BootstrapTable ref='appTable' data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="appName" csvHeader='应用名称' width="70">应用名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="appCname" csvHeader='中文名称' width="70">中文名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="appKey" csvHeader='应用KEY' width="150">应用KEY</TableHeaderColumn>
                            <TableHeaderColumn dataField="appSecret" csvHeader='安全码'  width="150">安全码</TableHeaderColumn>
                            <TableHeaderColumn dataField="hostname" csvHeader='主机' width="70">主机</TableHeaderColumn>
                            <TableHeaderColumn dataField="webContext" csvHeader='应用上下文' width="70">应用上下文</TableHeaderColumn>
                            <TableHeaderColumn dataField="isAvailable" csvHeader='是否可用' width="70" csvFormat={this.enumeFormat.bind(this)}
                                               dataFormat={this.enumeFormat.bind(this)}>是否可用</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="85" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
    }
}

AppList.propTypes = {
    actions: PropTypes.object.isRequired
}

AppList.contextTypes = {
    store : React.PropTypes.object
}

export default AppList
