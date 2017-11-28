import React, { PropTypes, Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import CacheSearchForm from './CacheSearchForm';
import { Button } from 'react-bootstrap';
import CacheButtonComponent from './CacheButtonComponent';
import CacheModalComponent from './CacheModalComponent';
import {PORTAL_CACHE_LIST_ROW_SELECT,PORTAL_CACHE_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';



class CacheList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("",0,15);
    }
    componentDidUpdate (){
        const {store} = this.context;
        $('#cache_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.cacheIds.length);
        if(store.getState().todos.cacheIds.length>1){
            $('#cache_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }
    }
    onSizePerPageList(sizePerPage) {
        this.props.actions.list("",0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.list("",page-1, sizePerPage);
    }

    onRowSelect(store, row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        store.dispatch({type:PORTAL_CACHE_LIST_ROW_SELECT, isSelected:isSelected, cacheObj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var cacheIds = [];
        if(isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                cacheIds[num] = rows[i].id;
                num++;
            }
        }else {
            cacheIds = [];
        }
        store.dispatch({type:PORTAL_CACHE_LIST_MULTI_ROW_SELECT ,cacheIds:cacheIds});
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
            start:1,
            sizePerPage: 15,
            sizePerPageList: [5, 10, 15, 20 ],
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
            onSelect:  (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store,isSelected, rows),
            selected:store.getState().todos.cacheIds
        };

        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:hcgl:update" className="btn btn-link" onClick={()=>actions.portalCacheUpdateModal(row)} text="编辑" />
                    <Imalla permissionCode="portal:sysmgr:hcgl:clear" className="btn btn-link" onClick={()=>actions.portalCacheClear(row.cacheEntityName,row.appName+":entity_*"+row.cacheEntityName+"*")} text="清除缓存" />
                </div>
            );
        }

        function rateFormat(cell, row){
            var hitRate = 0;
            if(parseInt(row.requestCount)>0){
                hitRate = (parseInt(row.hitCount) * 100/parseInt(row.requestCount));
            }
            if(hitRate>90){
                var num = new Number(hitRate).toFixed(2);
                return (
                    <div style={{"color":"red"}}>{num+"%"}</div>
                );
            }
            var num = new Number(hitRate).toFixed(2);
            return (
                <div>{num+"%"}</div>
            );
        }

        return(
            <div>
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">缓存管理</h3>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        <div id="cache_bars_btn" className="clearfix">
                            <CacheModalComponent actions={this.props.actions}  />
                            <CacheButtonComponent actions={this.props.actions}  />
                            <CacheSearchForm  actions={this.props.actions}/>
                        </div>
                        <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        >
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="cacheEntityName" csvHeader='类名'>类名</TableHeaderColumn>
                            <TableHeaderColumn dataField="cacheEntityPath" csvHeader='路径' width="350">路径</TableHeaderColumn>
                            <TableHeaderColumn dataField="appName" csvHeader='所属应用'>所属应用</TableHeaderColumn>
                            <TableHeaderColumn dataField="cacheEntityExpireSeconds" csvHeader='失效时间'>失效时间(S)</TableHeaderColumn>
                            <TableHeaderColumn dataField="requestCount" csvHeader='请求次数'>请求次数</TableHeaderColumn>
                            <TableHeaderColumn dataField="writeCount" csvHeader='写入次数'>写入次数</TableHeaderColumn>
                            <TableHeaderColumn dataField="hitCount" csvHeader='命中次数'>命中次数</TableHeaderColumn>
                            <TableHeaderColumn dataField="clearCount" csvHeader='擦除次数'>擦除次数</TableHeaderColumn>
                            <TableHeaderColumn dataField="hitRate" csvHeader='命中率' dataFormat={rateFormat}>命中率</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="250" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
    }
}

CacheList.propTypes = {
    actions: PropTypes.object.isRequired
}

CacheList.contextTypes = {
    store : React.PropTypes.object
}



export default CacheList
