import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import DictItemModalComponent from  './DictItemModalComponent';
import DictItemButtonComponent from  './DictItemButtonComponent';
import {PORTAL_DICT_ITEM_LIST_ROW_SELECT,PORTAL_DICT_ITEM_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';
import Imalla from '../../../../common/imallbutton/components/ImallA';


class DictItemMgr extends Component {

    constructor(props) {
        super(props)
    }

    onSizePerPageList(sizePerPage) {
        this.props.actions.dictItemMgrModal();
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.dictItemMgrModal();
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
        store.dispatch({type:PORTAL_DICT_ITEM_LIST_ROW_SELECT, isSelected:isSelected, dictItemObj:row});
    }
    onSelectAll(store,isSelected, rows) {
        var dictItemIds = [];
        var num = 0;
        for (let i = 0; i < rows.length; i++) {
            dictItemIds[num] = rows[i].id;
            num++;
        }
        store.dispatch({type:PORTAL_DICT_ITEM_LIST_MULTI_ROW_SELECT ,dictItemIds:dictItemIds});
    }

    handleSearch(dictId) {
        var $ = require("jquery");
        var dictItemNm = $("#dictItemNm").val();
        var dictItemCode = $("#dictItemCode").val();
        this.props.actions.dictItemMgrModal(dictId,dictItemNm,dictItemCode,true);
    }
    renderShowsTotal(start, to, total) {
        if(total!=0){
            if(to==total){
                return (
                    <p>
                        显示第&nbsp;{start}&nbsp;到第&nbsp;{to}&nbsp;条记录&nbsp;,&nbsp; 总共&nbsp;{total}&nbsp;条记录
                    </p>
                );
            }else{
                return (

                    <p>
                        显示第&nbsp;{start}&nbsp;到第&nbsp;{to}&nbsp;条记录&nbsp;,&nbsp; 总共&nbsp;{total}&nbsp;条记录
                    </p>
                );
            }
        }
    }
    render() {
        const {actions} = this.props;
        const {store} = this.context;
        const selectRowProp = {
            mode: 'checkbox',
            clickToSelect: true,
            onSelect: (row, isSelected, e) => this.onRowSelect(store, row, isSelected, e),
            onSelectAll: (isSelected, rows) => this.onSelectAll(store,isSelected, rows),
            selected:store.getState().todos.dictItemIds
        };
        const page = store.getState().todos.dictItemMgrPage;
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
            noDataText: '暂无数据'
        };
        const fetchInfo={
            dataTotalSize: page.totalElements
        };
        function buttonFormatter(cell, row) {
            return (
                <div>
                    <Imalla permissionCode="portal:sysmgr:sjzd:item:update" className="btn btn-link" onClick={()=>actions.portalDictItemUpdateModal(row.id)} text="编辑" />
                </div>
            );
        }
        return (
            <div id="dictItemMgrMsg">
                <div className="panel">

                    <div className="panel-body" id="dictItemSearchParam">
                        <div className="col-md-5">
                            <div className="form-group">
                                <label className="control-label col-md-3 pad-no" for="dictItemNm">名称：</label>
                                <div className="col-md-8">
                                    <input type="text" name="dictItemNm" className="form-control" id="dictItemNm" />
                                </div>
                            </div>
                        </div>
                        <div className="col-md-5">
                            <div className="form-group">
                                <label className="control-label col-md-3 pad-no" for="dictItemCode">编码：</label>
                                <div className="col-md-8">
                                    <input type="text" name="dictItemCode" className="form-control" id="dictItemCode" />
                                </div>
                            </div>
                        </div>
                        <div className="col-md-2 text-right">
                            <button className="btn btn-default btn-labeled fa fa-search" id="dictItemSearchBtn" role="button" onClick={()=>this.handleSearch(store.getState().todos.dictId)}>查询</button>
                        </div>
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body">
                        <div id="app_bars_btn" className="bars mar-hor">
                            <DictItemModalComponent actions={this.props.actions}  />
                            <DictItemButtonComponent actions={this.props.actions}  />
                        </div>
                        <BootstrapTable data={page.content} dataSort={true} remote={true} pagination={page.totalElements>page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="dictItemNm" width="70">名称</TableHeaderColumn>
                            <TableHeaderColumn dataField="dictItemCode" width="70">编码</TableHeaderColumn>
                            <TableHeaderColumn dataField="isAvailable" width="70"
                                               dataFormat={this.enumeFormat.bind(this)}>是否可用</TableHeaderColumn>
                            <TableHeaderColumn dataField="operation" csvHeader='操作' dataAlign="center" width="70" dataFormat={buttonFormatter}>操作</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        )
    }
}

DictItemMgr.propTypes = {
    actions: PropTypes.object.isRequired
}

DictItemMgr.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}

export default connect(mapStateToProps, null)(DictItemMgr);