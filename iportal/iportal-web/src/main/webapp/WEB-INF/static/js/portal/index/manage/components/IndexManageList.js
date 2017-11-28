import React, { PropTypes, Component } from 'react';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import {INDEX_MANAGE_LIST,INDEX_MANAGE_LIST_ROW_SELECT,INDEX_MANAGE_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';

class  IndexManagelList extends Component {
    constructor(props) {
        super(props)
    }
    componentWillMount() {
        this.props.actions.indexManageList(0,20);
    }

    componentDidUpdate (){
        const {store} = this.context;
    }

    onSizePerPageList(sizePerPage) {
        this.props.actions.indexManageList(0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.indexManageList(page-1,sizePerPage);
    }

    onRowSelect(store, row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        store.dispatch({type:INDEX_MANAGE_LIST_ROW_SELECT, isSelected:isSelected, obj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var ids = [];
        if (isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                ids[num] = rows[i].id;
                num++;
            }
        }else {
            ids = [];
        }
        store.dispatch({type:INDEX_MANAGE_LIST_MULTI_ROW_SELECT ,ids:ids});
    }

    renderShowsTotal(start, to, total) {
        if(total!=0){
            if(to==total){
                return (
                    <p>显示第&nbsp;{start+1}&nbsp;到第&nbsp;{to}&nbsp;条记录&nbsp;,&nbsp; 总共&nbsp;{total}&nbsp;条记录</p>
                );
            }else{
                return (
                    <p>显示第&nbsp;{start+1}&nbsp;到第&nbsp;{to+1}&nbsp;条记录&nbsp;,&nbsp; 总共&nbsp;{total}&nbsp;条记录</p>
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
        const selectRowProp = {
            mode: 'checkbox',
            clickToSelect: true,
            onSelect: (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store,isSelected, rows),
            selected:store.getState().todos.ids
        };
        const page = store.getState().todos.page;
        const number=page.number+1;


        function stateFormatter(cell, row) {

            var url = iportal + "/static/img/heartbeat.gif";

            if(row.state=="FREEING"){
                return (
                    <div>空闲中</div>
                );
            }else{
                return (
                    <div>
                         <img src={url}/>
                    </div>
                );
            }
        }

        function buttonFormatter(cell, row){
            return (
                <div>
                    <Imalla permissionCode="index:indexmgr:sylb:update" className="btn btn-link" onClick={()=>actions.rebuildIndex(row.id)} title="清除索引数据并将数据插入队列表" text="初始化索引" />
                </div>
            );
        }

        return(
            <div>
                <div className="panel">
                    <div className="panel-body">
                        <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={true}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"  ></TableHeaderColumn>
                            <TableHeaderColumn dataField="indexTypeCode" csvHeader='索引编码' dataAlign="center"  width="70">索引编码</TableHeaderColumn>
                            <TableHeaderColumn dataField="indexName" csvHeader='索引名称' dataAlign="center" width="70">索引名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="state" csvHeader='工作状态' dataAlign="center" width="70" dataFormat={stateFormatter}>工作状态</TableHeaderColumn>
                            <TableHeaderColumn dataField="remainNum" csvHeader='剩余条数' dataAlign="center" width="70">剩余条数</TableHeaderColumn>
                            <TableHeaderColumn dataField="remoteUrl" csvHeader='远程地址' dataAlign="center" width="250">远程地址</TableHeaderColumn>
                            <TableHeaderColumn dataField="appKey" csvHeader='appKey' dataAlign="center" width="100">appKey</TableHeaderColumn>
                            <TableHeaderColumn dataField="appSecret" csvHeader='appSecret' dataAlign="center" width="150">appSecret</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="85" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
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
            onExportToCSV: this.onExportToCSV.bind(this)
        };
        const fetchInfo={
            dataTotalSize: page.totalElements
        };
    }
}
IndexManagelList.propTypes = {
    actions: PropTypes.object.isRequired
}

IndexManagelList.contextTypes = {
    store : React.PropTypes.object
}

export default  IndexManagelList
