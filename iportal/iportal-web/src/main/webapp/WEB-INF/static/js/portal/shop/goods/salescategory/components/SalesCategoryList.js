import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import Imalla from "../../../../../common/imallbutton/components/ImallA";
import SalesCategorySearchForm from "../components/SalesCategorySearchForm";
import SalesCategoryAddForm from "../components/SalesCategoryAddForm";
import SalesCategoryEditForm from "./SalesCategoryUpdateForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import {portalOperationalAuth} from "../../../../../../js/common/imallbutton/actions";

class SalesCategoryList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.portalOperationalAuth(["goods:salesCategory:add", "goods:salesCategory:update"]);
        this.props.actions.salesCategoryList(params, params.page, params.size);
    }

    showSalesCategoryAddForm(){
        this.props.actions.showCategoryAddForm(true);
    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.salesCategoryListSetSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.salesCategoryList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.salesCategoryListSetSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.salesCategoryList(params, page - 1, sizePerPage);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {page, params} = store.getState().todos;
        const number=page.number+1;
        const salesCategory = store.getState().todos.page.content || [];
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
        <div>
            {store.getState().todos.isShowAdd&&<SalesCategoryAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.salesCategorySaveUpdateData(data, actions, params)} asyncErrorValidMessageFunction={this.props.asyncErrorValidMessageFunction} />}
            {store.getState().todos.isShowEdit&&<SalesCategoryEditForm store={store} actions={this.props.actions} onSubmit={(data) => this.props.actions.salesCategorySaveUpdateData(data, actions, params)} asyncErrorValidMessageFunction={this.props.asyncErrorValidMessageFunction} />}
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>销售分类管理</h5>
                            <a href="javascript:void(0);">商品管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">销售分类管理</a>
                            <SalesCategorySearchForm actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <Imalla href="javascript:void(0);" permissionCode="goods:salesCategory:add" className="added" onClick={this.showSalesCategoryAddForm.bind(this)} text="添加" />
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="types">排序</th>
                                    <th className="name">分类名称</th>
                                    <th className="status">是否前台展示</th>
                                    <th className="status">启用状态</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {salesCategory.length<=0 &&
                                    <tr >
                                        <td colSpan="5" style={{textAlign:"center"}}>
                                            <div className="empty-box">
                                                <span>暂无数据</span>
                                            </div>
                                        </td>
                                    </tr>
                                }
                                {
                                    salesCategory.map((salesCategory, index) => {
                                        return (
                                            <tr key={salesCategory.id}>
                                                <td>
                                                    <div className="td-cont" title={salesCategory.dispalyPosition}>{salesCategory.dispalyPosition}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={salesCategory.categoryName}>{salesCategory.categoryName}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={salesCategory.isFrontendShowString}>{salesCategory.isFrontendShowString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" title={salesCategory.isEnableString}>{salesCategory.isEnableString}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont" style={{'paddingLeft': 0, 'paddingRight': 25 + 'px', 'textAlign': 'right'}}>
                                                        <Imalla href="javascript:void(0);" permissionCode="goods:salesCategory:update" className="gray-btn" onClick={()=>this.props.actions.showCategorySaveEditForm(true, salesCategory.id)} text="修改" />
                                                    </div>
                                                </td>
                                            </tr>
                                        )
                                    })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <IMallPaginationBar options={options} actions={actions}/>
            </div>
        </div>
        )
    }
}

SalesCategoryList.propTypes = {
    actions: PropTypes.object.isRequired
};

SalesCategoryList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(SalesCategoryList);