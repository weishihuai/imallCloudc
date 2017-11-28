import React, { PropTypes, Component } from 'react';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import ParamInfSearchForm from './ParamInfSearchForm';
import { Button } from 'react-bootstrap';
import ParamInfButtonComponent from './ParamInfButtonComponent';
import ParamInfModalComponent from './ParamInfModalComponent';
import {PORTAL_PARAMINF_LIST_ROW_SELECT,PORTAL_PARAMINF_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';

class ParamInfList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("","","",0,10);
    }
    componentDidUpdate (){
        const {store} = this.context;
        $('#param_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.paramInfIds.length);
        if(store.getState().todos.paramInfIds.length>1){
            $('#param_bars_btn').find('.ui-btn-enable').each(function(){
                if($(this).hasClass("single")){
                    $(this).prop('disabled',"true");
                }
            });
        }
    }
    onSizePerPageList(sizePerPage) {
        this.props.actions.list("","","",0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.list("","","",page-1, sizePerPage);
    }

    onRowSelect(store,row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        //alert(`is selected: ${isSelected}, ${rowStr}`);
        store.dispatch({type:PORTAL_PARAMINF_LIST_ROW_SELECT, isSelected:isSelected, paramInfObj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var paramInfIds = [];
        if(isSelected) {
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                paramInfIds[num] = rows[i].id;
                num++;
            }
        }else {
            paramInfIds = [];
        }
        store.dispatch({type:PORTAL_PARAMINF_LIST_MULTI_ROW_SELECT ,paramInfIds:paramInfIds});
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
        const {actions} = this.props;
        const {store} = this.context;
        const page = store.getState().todos.page;
        const number=page.number+1;
        const options = {
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
            onSelect: (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store,isSelected, rows),
            selected:store.getState().todos.paramInfIds
        };
        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:xtcs:update" className="btn btn-link" onClick={()=>actions.portalParamInfUpdateModal(row.id)} text="编辑" />
                </div>
            );
        }
        return(
            <div>
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">系统参数管理</h3>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        <div id="param_bars_btn" className="clearfix">
                            <ParamInfModalComponent actions={this.props.actions}  />
                            <ParamInfButtonComponent actions={this.props.actions}  />
                            <ParamInfSearchForm  actions={this.props.actions}/>
                        </div>
                        <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="paramGroupTypeCode" csvHeader='参数分组类型代码'>参数分组类型代码</TableHeaderColumn>
                            <TableHeaderColumn dataField="paramTypeCode" csvHeader='参数类型代码'>参数类型代码</TableHeaderColumn>
                            <TableHeaderColumn dataField="innerCode" csvHeader='内部代码'>内部代码</TableHeaderColumn>
                            <TableHeaderColumn dataField="paramNm" csvHeader='参数名称'>参数名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="paramDescr" csvHeader='参数描述'>参数描述</TableHeaderColumn>
                            <TableHeaderColumn dataField="paramValue" csvHeader='参数值'>参数值</TableHeaderColumn>
                            <TableHeaderColumn dataField="sysOrgId" csvHeader='机构Id'>机构Id</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="185" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
    }
}

ParamInfList.propTypes = {
    actions: PropTypes.object.isRequired
}

ParamInfList.contextTypes = {
    store : React.PropTypes.object
}

export default ParamInfList
