import React, { PropTypes, Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import ExceptionSearchForm from './ExceptionSearchForm';
import { Button } from 'react-bootstrap';
import ExceptionButtonComponent from './ExceptionButtonComponent';
import ExceptionModalComponent from './ExceptionModalComponent';
import {PORTAL_EXCEPTION_LIST_ROW_SELECT,PORTAL_EXCEPTION_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';



class ExceptionList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("","",0,10);
    }
    componentDidUpdate (){
        const {store} = this.context;
        $('#exception_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.exceptionIds.length);
        if(store.getState().todos.exceptionIds.length>1){
            $('#exception_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }
    }
    onSizePerPageList(sizePerPage) {
        this.props.actions.list("","",0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.list("","",page-1, sizePerPage);
    }

    onRowSelect(store, row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        store.dispatch({type:PORTAL_EXCEPTION_LIST_ROW_SELECT, isSelected:isSelected, exceptionObj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var exceptionIds = [];
        if(isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                exceptionIds[num] = rows[i].id;
                num++;
            }
        }else {
            exceptionIds = [];
        }
        store.dispatch({type:PORTAL_EXCEPTION_LIST_MULTI_ROW_SELECT ,exceptionIds:exceptionIds});
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
            sizePerPage: 10,
            sizePerPageList: [5, 10, 20 ],
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
            selected:store.getState().todos.exceptionIds
        };
        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:ycmb:update" className="btn btn-link" onClick={()=>actions.portalExceptionUpdateModal(row.id)} text="编辑" />
                </div>
            );
        }

        return(
            <div>
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">异常码表</h3>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        <div id="exception_bars_btn" className="clearfix">
                            <ExceptionModalComponent actions={this.props.actions}  />
                            <ExceptionButtonComponent actions={this.props.actions}  />
                            <ExceptionSearchForm  actions={this.props.actions}/>
                        </div>
                        <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="code" csvHeader='异常编码'>异常编码</TableHeaderColumn>
                            <TableHeaderColumn dataField="exceptionMsg" csvHeader='异常提示'>异常提示</TableHeaderColumn>
                            <TableHeaderColumn dataField="remark" csvHeader='备注'>备注</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="185" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
    }
}

ExceptionList.propTypes = {
    actions: PropTypes.object.isRequired
}

ExceptionList.contextTypes = {
    store : React.PropTypes.object
}



export default ExceptionList
