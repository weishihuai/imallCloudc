import React, { Component, PropTypes } from 'react'
import { reduxForm } from 'redux-form';
import { bindActionCreators } from 'redux';
import {connect} from 'react-redux';
import ResourceUrlButtonComponent from  './ResourceUrlButtonComponent';
import ResourceUrlAddForm from './ResourceUrlAddForm';
import {PORTAL_RESOURCE_URL_LIST_ROW_SELECT,PORTAL_RESOURCE_URL_LIST_MULTI_ROW_SELECT} from '../constants/ActionTypes';


class ResourceUrlMgr extends Component {

    constructor(props) {
        super(props)
    }

    onSizePerPageList(sizePerPage) {
        this.props.actions.portalResourceUrlMgrModal(this.props.store.getState().todos.id,"",true,0,sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        this.props.actions.portalResourceUrlMgrModal(this.props.store.getState().todos.id,"",true,page-1,sizePerPage);
    }
    onRowSelect(store,row, isSelected, e) {
        store.dispatch({type:PORTAL_RESOURCE_URL_LIST_ROW_SELECT, isSelected:isSelected,resourceUrlObj:row});
    }
    onSelectAll(store,isSelected, rows) {
        var resourceUrlIds = [];
        var num = 0;
        for (let i = 0; i < rows.length; i++) {
            resourceUrlIds[num] = rows[i].id;
            num++;
        }
        store.dispatch({type:PORTAL_RESOURCE_URL_LIST_MULTI_ROW_SELECT ,resourceUrlIds:resourceUrlIds});
    }

    handleSearch(id) {
        var $ = require("jquery");
        var url = $("#urlInput").val();
        this.props.actions.portalResourceUrlMgrModal(this.props.store.getState().todos.id,url,true,0,10);
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
            selected:store.getState().todos.resourceUrlIds
        };
        const page = store.getState().todos.resourceUrlMgrPage;
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

        return (
            <div id="resourceUrlMgrMsg">
                <div>
                    <div>
                        <div className="form-horizontal mar-btm">
                            <div className="form-group">
                                <label for="resourceName" className="col-md-2 col-sm-2 control-label">资源名称：</label>
                                <div className="col-md-5 col-sm-5">
                                    <label  className="control-label">{store.getState().todos.resourceName}</label>
                                </div>
                            </div>
                        </div>
                        <ResourceUrlAddForm store={store} actions={actions} />
                    </div>
                </div>
                <div className="panel">
                    <div className="panel-body bord-all">
                        <div className="form-group" id="resourceUrlSearchParam">
                            <div className="col-md-10 col-sm-10">
                                <input type="text" name="url"  id="urlInput" className="form-control" placeholder="资源URL"  />
                            </div>
                            <button className="btn btn-default btn-labeled fa fa-search" id="resourceUrlSearchBtn" role="button" onClick={()=>this.handleSearch(store.getState().todos.id)}>查询</button>
                        </div>
                        <div id="app_bars_btn" className="bars mar-hor">
                            <ResourceUrlButtonComponent actions={this.props.actions}  />
                        </div>
                        <BootstrapTable data={page.content} dataSort={true} remote={true} pagination={page.totalElements > page.size}
                                        fetchInfo={fetchInfo}
                                        options={options}
                                        selectRow={selectRowProp} striped={true} hover={true} condensed={true}>
                            <TableHeaderColumn dataField="id" isKey={true} hidden={true} width="70"></TableHeaderColumn>
                            <TableHeaderColumn dataField="url" width="70">资源url</TableHeaderColumn>
                        </BootstrapTable>
                    </div>
                </div>
            </div>
        )
    }
}

ResourceUrlMgr.propTypes = {
    actions: PropTypes.object.isRequired
}

ResourceUrlMgr.contextTypes = {
    store : React.PropTypes.object
}

function mapStateToProps(state) {
    return state;
}
function mapDispatchToProps(dispatch){
    return bindActionCreators({
    }, dispatch);
}

export default  ResourceUrlMgr = connect(
    mapStateToProps,
    mapDispatchToProps
)(ResourceUrlMgr);
