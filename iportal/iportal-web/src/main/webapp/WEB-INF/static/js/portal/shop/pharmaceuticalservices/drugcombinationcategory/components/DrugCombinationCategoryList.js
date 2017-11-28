import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import DrugCombinationCategoryAddForm from "./DrugCombinationCategoryAddForm";
import DrugCombinationCategoryEditForm from "./DrugCombinationCategoryEditForm";
import DrugCombinationCategorySearchForm from "./DrugCombinationCategorySearchForm";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";
import CommonConfirmComponent from "../../../../../../js/common/component/CommonConfirmComponent";

class DrugCombinationCategoryList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["pharmaceuticalCare:combination:combinationCategory:add", "pharmaceuticalCare:combination:combinationCategory:delete", "pharmaceuticalCare:combination:combinationCategory:update"]);
        this.props.actions.drugCombinationCategoryList(params, params.page, params.size);
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
        this.props.actions.drugCombinationCategoryList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.drugCombinationCategoryList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {params, isShowAdd, isShowEdit, isShowConfirm, page, id} = store.getState().todos;
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
                {isShowAdd && <DrugCombinationCategoryAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)} /> }
                {isShowEdit && <DrugCombinationCategoryEditForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.saveOrUpdate(data, actions, params)} /> }
                {isShowConfirm && <CommonConfirmComponent title="删除联合用药分类" text="确认删除？" callback={()=>actions.del(id, params)} close={()=>actions.showDelConfirm(false)} zIndex="1000" />}
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>联合用药分类</h5>
                            <a href="javascript:void(0);">药学服务</a>
                            <span>></span>
                            <a href="javascript:void(0);">联合用药</a>
                            <span>></span>
                            <a href="javascript:void(0);">联合用药分类</a>
                            <DrugCombinationCategorySearchForm actions={actions} />
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="pharmaceuticalCare:combination:combinationCategory:add" className="added" onClick={this.showAddForm.bind(this)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="class-name">分类名称</th>
                                    <th className="operating" style={{"width": 430 + 'px'}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { record.length <= 0 &&
                                    <tr >
                                        <th colSpan="2" style={{textAlign: "center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </th>
                                    </tr>
                                }
                                {
                                    record.map((record) => {
                                        return (
                                            <tr key={record.id}>
                                                <td>
                                                    <div className="td-cont" title={record.categoryNm}>{record.categoryNm}</div>
                                                </td>
                                                <td>
                                                    {
                                                        record.orgId===record.currentOrgId &&
                                                        <div className="td-cont" style={{"paddingLeft" : 0, "paddingRight" : 25 + "px", "textAlign" : "right"}}>
                                                            <Imalla href="javascript:void(0);" permissionCode="pharmaceuticalCare:combination:combinationCategory:delete" className="gray-btn" text="删除" onClick={()=>this.props.actions.showDelConfirm(true, record.id)}  />
                                                            <Imalla href="javascript:void(0);" permissionCode="pharmaceuticalCare:combination:combinationCategory:update" className="gray-btn" text="修改" onClick={()=>this.props.actions.showEditForm(true, record)} />
                                                        </div>
                                                    }
                                                </td>
                                            </tr>
                                        );
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <IMallPaginationBar options={options} actions={actions}/>
                </div>
            </div>
        );
    }
}

DrugCombinationCategoryList.propTypes = {
    actions: PropTypes.object.isRequired
};

DrugCombinationCategoryList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugCombinationCategoryList);