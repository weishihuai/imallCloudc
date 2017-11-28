import React, { PropTypes, Component } from 'react';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import DictSearchForm from './DictSearchForm';
import DictButtonComponent from './DictButtonComponent'
import DictModalComponent from './DictModalComponent'
import {PORTAL_DICT_LIST_ROW_SELECT,PORTAL_DICT_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';


class DictList extends Component {
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        this.props.actions.list("","","",0,10);
    }
    componentDidUpdate (){
        const {store} = this.context;
        $('#dict_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.dictIds.length);
        if(store.getState().todos.dictIds.length>1){
            $('#dict_bars_btn').find('.ui-btn-enable').each(function(){
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
        this.props.actions.list("","","",page-1,sizePerPage);
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
        store.dispatch({type:PORTAL_DICT_LIST_ROW_SELECT, isSelected:isSelected, dictObj:row});
    }
    onSelectAll(store,isSelected, rows) {
        var dictIds = [];
        if(isSelected){
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                dictIds[num] = rows[i].id;
                num++;
            }
        }else {
            dictIds = [];
        }
        store.dispatch({type:PORTAL_DICT_LIST_MULTI_ROW_SELECT ,dictIds:dictIds});
    }
    onSelect(){
        var $ = require("jquery");
        $("#searchTypeMenu").find('li').click(function () {
            var type = $(this).data('type');
            var text = $(this).children('a').html();
            $(this).parent().parent().find('.icon-share').html(text);
            $(this).parent().parent().next('input').attr("name", type);
        });
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
            selected:store.getState().todos.dictIds
        };

        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:sjzd:update" className="btn btn-link" onClick={()=>actions.portalDictUpdateModal(row.id)} text="修改" />
                    <Imalla permissionCode="portal:sysmgr:sjzd:item:list" className="btn btn-link" onClick={()=>actions.dictItemMgrModal(row.id, "","",false)} text="管理" />
                </div>
            );
        }

        return(
            <div>
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">数据字典管理</h3>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        <div id="dict_bars_btn" className="clearfix">
                            <DictModalComponent actions={this.props.actions}  />
                            <DictButtonComponent actions={this.props.actions}  />
                            <DictSearchForm  actions={this.props.actions}/>
                        </div>
                        <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false} pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="dictNm" csvHeader='名称' >名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="dictType" csvHeader='类型' >类型</TableHeaderColumn>
                            <TableHeaderColumn dataField="isInternal" csvHeader='是否内置' csvFormat={ this.enumeFormat.bind(this) } dataFormat={this.enumeFormat.bind(this)}>是否内置</TableHeaderColumn>
                            <TableHeaderColumn dataField="isAvailable" csvHeader='是否可用' csvFormat={ this.enumeFormat.bind(this) } dataFormat={this.enumeFormat.bind(this)}>是否可用</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="185" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
    }
}

DictList.propTypes = {
    actions: PropTypes.object.isRequired
}

DictList.contextTypes = {
    store : React.PropTypes.object
}


export default DictList
