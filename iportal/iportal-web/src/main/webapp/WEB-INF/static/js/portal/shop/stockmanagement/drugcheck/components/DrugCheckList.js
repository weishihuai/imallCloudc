import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";
import DrugCheckSearchForm from './DrugCheckSearchForm';
import DrugCheckAddForm from './DrugCheckAddForm';
import DrugCheckDetail from './DrugCheckDetail';
import DrugCheckEditForm from './DrugCheckEditForm';

class DrugCheckList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["stock:drugExamine:add:default", "stock:drugExamine:check:default", "stock:drugExamine:detail:default"]);
        this.props.actions.drugCheckList(params, params.page, params.size);
    }

    componentDidMount(){

    }

    componentDidUpdate() {

    }

    showAddForm(){
        this.props.actions.showAddForm(true);
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCheckSetSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.drugCheckList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCheckSetSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.drugCheckList(params, page - 1, sizePerPage);
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
                {store.getState().todos.isShowAdd && <DrugCheckAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)}/>}
                {store.getState().todos.isShowDetail && <DrugCheckDetail store={store} actions={this.props.actions}/>}
                {store.getState().todos.isShowEdit && <DrugCheckEditForm actions={this.props.actions}/>}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>药品检查</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">药品检查</a>
                            <DrugCheckSearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="stock:drugExamine:add:default" className="added" onClick={this.showAddForm.bind(this)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="time" style={{width: 60 + 'px'}}>计划检查时间</th>
                                    <th className="time" style={{width: 60 + 'px'}}>检查完成时间</th>
                                    <th className="th-coding">陈列检查单号</th>
                                    <th className="dosage" style={{width: 50 + 'px'}}>检查类型</th>
                                    <th className="state" style={{width: 50 + 'px'}}>检查状态</th>
                                    <th className="operating" style={{width: 100 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { record.length <= 0 &&
                                <tr >
                                    <th colSpan="6" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {
                                    record.map((item, index) => {
                                        return (
                                            <tr key={index}>
                                                <td>
                                                    <div className="td-cont" title={item.planCheckTimeString}>{item.planCheckTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.checkFinishTimeString}>{item.checkFinishTimeString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.checkDocumentNum}>{item.checkDocumentNum}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.checkTypeName}>{item.checkTypeName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={item.checkStateName}>{item.checkStateName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign':'right'}}>
                                                        <Imalla href="javascript:void(0);" permissionCode="stock:drugExamine:detail:default" className="gray-btn" text="查看" onClick={() => this.props.actions.showDetail(true, item.id)}  />
                                                        {item.checkStateCode=='NOT_CHECKED' && <Imalla href="javascript:void(0);" permissionCode="stock:drugExamine:check:default" className="gray-btn" text="检查" onClick={() => this.props.actions.showEditForm(true, item.id)}  />}
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
        );
    }
}

DrugCheckList.propTypes = {
    actions: PropTypes.object.isRequired
};

DrugCheckList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugCheckList);