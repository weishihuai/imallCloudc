import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";

import DrugCuringSearchForm from './DrugCuringSearchForm';
import DrugCuringAddForm from './DrugCuringAddForm';
import DrugCuringDetail from './DrugCuringDetail';
import DrugCuringEditForm from './DrugCuringEditForm';

class DrugCuringList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["stock:drugConserve:add:default", "stock:drugConserve:conserve:default", "stock:drugConserve:detail:default"]);
        this.props.actions.drugCuringList(params, params.page, params.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCuringSetSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.drugCuringList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCuringSetSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.drugCuringList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {params} = store.getState().todos;
        const {page} = store.getState().todos;
        const number=page.number+1;
        const record = store.getState().todos.page.content || [];
        const options = {
            sizePerPage: page.size>0?page.size:10,
            sizePerPageList: page.totalElements>0?[10, 20, 40]:[],
            pageStartIndex: 1,
            page: number,
            onPageChange: this.onPageChange.bind(this),
            onSizePerPageList: this.onSizePerPageList.bind(this),
            prePage: "上一页",
            nextPage: "下一页",
            firstPage: "<<",
            lastPage: ">>",
            dataTotalSize: page.totalElements,  //renderShowsTotal 中的共几条
            paginationSize: page.totalPages, //分页的页码
            hideSizePerPage:page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                {store.getState().todos.isShowAdd && <DrugCuringAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)}/>}
                {store.getState().todos.isShowDetail && <DrugCuringDetail store={store} actions={this.props.actions}/>}
                {store.getState().todos.isShowEdit && <DrugCuringEditForm actions={this.props.actions}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>药品养护</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品养护</a>
                            <DrugCuringSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="stock:drugConserve:add:default" className="added" onClick={()=>this.props.actions.showAddForm(true)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="time" style={{width: 60 + 'px'}}>计划养护时间</th>
                                    <th className="time" style={{width: 60 + 'px'}}>养护完成时间</th>
                                    <th className="tr-number">养护计划单号</th>
                                    <th className="dosage" style={{width: 50 + 'px'}}>养护类型</th>
                                    <th className="state" style={{width: 50 + 'px'}}>检查状态</th>
                                    <th className="operating" style={{width: 100 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { record.length <= 0 &&
                                    <tr >
                                        <th colSpan="17" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((item) => {
                                        return (
                                            <tr key={item.id}>
                                                <td>
                                                    <div className="td-cont" title={item.planCuringTimeString}>{item.planCuringTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.curingFinishTimeString}>{item.curingFinishTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.curingDocumentNum}>{item.curingDocumentNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.curingTypeName}>{item.curingTypeName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.curingStateName}>{item.curingStateName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign':'right'}}>
                                                        <Imalla href="javascript:void(0);" permissionCode="stock:drugConserve:detail:default" className="gray-btn" text="查看" onClick={() => this.props.actions.showDetail(true, item.id)}  />
                                                        {item.curingStateCode=='NOT_CURED' && <Imalla href="javascript:void(0);" permissionCode="stock:drugConserve:conserve:default" className="gray-btn" text="养护" onClick={() => this.props.actions.showEditForm(true, item.id)}  />}
                                                    </div>
                                                </td>
                                            </tr>
                                        );
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        )
    }
}

DrugCuringList.propTypes = {
    actions: PropTypes.object.isRequired
};

DrugCuringList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugCuringList);