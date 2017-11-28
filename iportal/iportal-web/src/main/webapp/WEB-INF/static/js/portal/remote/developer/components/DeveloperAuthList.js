import React, { PropTypes, Component } from 'react';
import { Button } from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import Imalla from '../../../../common/imallbutton/components/ImallA';
import DeveloperAuthSearchForm from './DeveloperAuthSearchForm';
import DeveloperAuthButtonComponent from './DeveloperAuthButtonComponent';
import DeveloperAuthModalComponent from './DeveloperAuthModalComponent';
import {PORTAL_DEVELOPER_AUTH_LIST_ROW_SELECT,PORTAL_DEVELOPER_AUTH_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';

class DeveloperAuthList extends Component {
    constructor(props) {
        super(props)
    }
    componentWillMount() {
        this.props.actions.list("","","",0,10);
    }
    componentDidUpdate (){
        const {store} = this.context;
        $('#developer_bars_btn').find('.ui-btn-enable').prop('disabled', !store.getState().todos.developerIds.length);
        if(store.getState().todos.developerIds.length>1){
            $('#developer_bars_btn').find('.ui-btn-enable').each(function(){
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

    onRowSelect(store, row, isSelected, e) {
        let rowStr = '';
        for (const prop in row) {
            rowStr += prop + ': "' + row[prop] + '"';
        }
        store.dispatch({type:PORTAL_DEVELOPER_AUTH_LIST_ROW_SELECT, isSelected:isSelected, developerAuthObj:row});
    }

    onSelectAll(store,isSelected, rows) {
        var developerIds = [];
        if(isSelected){
            var num = 0;
            for (let i = 0; i < rows.length; i++) {
                developerIds[num] = rows[i].id;
                num++;
            }
        }else {
            developerIds = [];
        }
        store.dispatch({type:PORTAL_DEVELOPER_AUTH_LIST_MULTI_ROW_SELECT ,developerIds:developerIds});
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
        const {actions} = this.props;
        const {store} = this.context;
        const selectRowProp = {
            mode: 'checkbox',
            clickToSelect: true,
            onSelect: (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store, isSelected, rows),
            selected: store.getState().todos.developerIds
        };
        const page = store.getState().todos.page;
        const number = page.number + 1;
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
        const fetchInfo = {
            dataTotalSize: page.totalElements
        };

        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:kfzsq:update" className="btn btn-link" onClick={()=>actions.portalDeveloperAuthUpdateModal(row.id)} text="编辑"/>
                </div>
            );
        }

        return (
            <div>
                <div className="panel">
                    <div className="panel-heading">
                        <h3 className="panel-title">开发者授权</h3>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        {/*增加，修改，删除按钮区域*/}
                        <div id="developer_bars_btn" className="clearfix">
                            <DeveloperAuthModalComponent actions={this.props.actions}/>
                            <DeveloperAuthButtonComponent actions={this.props.actions}/>
                            <DeveloperAuthSearchForm actions={this.props.actions}/>
                        </div>
                        {/*列表区域*/}
                        <BootstrapTable data={page.content} dataSort={true} remote={true} exportCSV={false}
                                        pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="userName" csvHeader='用户名称'>用户名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="appName" csvHeader='应用名称'>应用名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="hostname" csvHeader='hostname'>hostname</TableHeaderColumn>
                            <TableHeaderColumn dataField="isAvailable" dataFormat={this.enumeFormat.bind(this)} csvHeader='是否可用' csvFormat={this.enumeFormat.bind(this)}>是否可用</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        );
    }

}
DeveloperAuthList.propTypes = {
    actions: PropTypes.object.isRequired
}

DeveloperAuthList.contextTypes = {
    store : React.PropTypes.object
}
export default DeveloperAuthList