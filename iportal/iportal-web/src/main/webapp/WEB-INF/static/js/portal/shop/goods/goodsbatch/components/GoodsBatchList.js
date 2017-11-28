import React, {Component} from "react";
import GoodsBatchUpdateForm from "./GoodsBatchUpdateForm";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import GoodsBatchSearchForm from "./GoodsBatchSearchForm";
import IMallPaginationBar from "../../../../../common/imallpagination/components/IMallPaginationBar";
import ImallA from '../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../common/imallbutton/actions';

/**
 * 商品批号修改 列表
 */
class GoodsBatchList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.goodsBatchList(params, params.page, params.size);
        this.props.portalOperationalAuth(['goods:goodsBatch:update']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.goodsBatchList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.goodsBatchList(params, page - 1, sizePerPage);
    }

    render() {
        const {store} = this.context;
        const page = store.getState().todos.page;
        const number = page.number + 1;
        const goodsBatchList = store.getState().todos.page.content || [];
        const options = {
            sizePerPage: page.size > 0 ? page.size : 10,
            sizePerPageList: page.totalElements > 0 ? [10, 20, 40] : [],
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
            hideSizePerPage: page.totalElements <= page.size
        };

        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>批号修改</h5>
                            <a href="javascript:void(0);">商品管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">批号修改</a>
                            <GoodsBatchSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt"/>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-number">序号</th>
                                    <th className="th-coding"  style={{width:"120px"}}>商品编码</th>
                                    <th className="th-title" style={{width:"200px"}}>商品名称</th>
                                    <th className="common-name">通用名称</th>
                                    <th className="standard">规格</th>
                                    <th className="dosage">剂型</th>
                                    <th className="units">单位</th>
                                    <th className="manufacturer" style={{width:"200px"}}>生产厂商</th>
                                    <th className="approval" style={{width:"150px"}}>批准文号</th>
                                    <th className="origin" style={{width:"150px"}}>产地</th>
                                    <th className="batch-number">批号</th>
                                    <th className="time">生产日期</th>
                                    <th className="time">有效期</th>
                                    <th className="cargo-location" style={{width:"120px"}}>货位</th>
                                    <th className="inventory" style={{width:"100px"}}>库存数量</th>
                                    <th className="operating" style={{minWidth:"150px"}}>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                {goodsBatchList.length <= 0 &&
                                <tr >
                                    <th colSpan="16" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {goodsBatchList.map((goodsBatch, index) => {
                                        return (<tr key={index}>
                                                <td><div className="td-cont td-number">{index + 1}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.goodsCode}>{goodsBatch.goodsCode}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.goodsNm}>{goodsBatch.goodsNm}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.goodsCommonNm}>{goodsBatch.goodsCommonNm}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.spec}>{goodsBatch.spec}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.dosageForm}>{goodsBatch.dosageForm || "暂无"}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.unit}>{goodsBatch.unit}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.produceManufacturer}>{goodsBatch.produceManufacturer}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.approvalNumber}>{goodsBatch.approvalNumber || "暂无"}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.productionPlace}>{goodsBatch.productionPlace || "暂无"}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.batch}>{goodsBatch.batch}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.produceDateString}>{goodsBatch.produceDateString}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.validDateString}>{goodsBatch.validDateString}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.storageSpaceNm}>{goodsBatch.storageSpaceNm}</div></td>
                                                <td><div className="td-cont" title={goodsBatch.currentStock}>{goodsBatch.currentStock}</div></td>
                                                <td>
                                                    <div className="td-cont" style={{paddingRight:"10px"}}>
                                                        <ImallA href="javascript:void(0);" permissionCode="goods:goodsBatch:update" className="gray-btn" text="修改" onClick={()=> this.props.actions.goodsBatchEditModel(true,goodsBatch)}>修改</ImallA>
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
                    {store.getState().todos.editState && <GoodsBatchUpdateForm store={store} actions={this.props.actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

GoodsBatchList.propTypes = {};

GoodsBatchList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(GoodsBatchList);