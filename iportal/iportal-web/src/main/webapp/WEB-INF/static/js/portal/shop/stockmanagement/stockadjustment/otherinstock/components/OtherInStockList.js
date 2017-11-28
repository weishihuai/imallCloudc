import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import OtherInStockSearchForm from "./OtherInStockSearchForm";
import OtherInStockDetail from "./OtherInStockDetail";
import OtherInStockAddForm from "./OtherInStockAddForm";
import ImallA from '../../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';

/**
 * 其他入库列表
 */
class OtherInStockList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.otherInStockList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:adjust:otherInStock:add','stock:adjust:otherInStock:detail']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {params} = this.context.store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.otherInStockList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.otherInStockList(params, page - 1, sizePerPage);
    }

    typeCodeFormat(typeCode) {
        switch (typeCode) {
            case "RECEIVE":
                return "获赠";
            case "OVERFLOW":
                return "报溢";
            case "TAKE_BACK":
                return "领用退回";
            case "OTHER":
                return "其他";
        }
    }

    saveSubmitData(data) {
        const {loginUserMessage,params} = this.context.store.getState().todos;
        const otherInStockSaveSubmitData = {
            operationManId:loginUserMessage.id,
            inStockTimeString:data.inStockTimeString,
            typeCode:data.typeCode,
            remark:data.remark,
            supplierId:data.supplierId,
            approveManId: data.approveDataId,
            otherInStockGoodsSaveVoList:data.goodsList,
        };
        return this.props.actions.saveOtherInStock(otherInStockSaveSubmitData,params);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const otherInStockList = store.getState().todos.page.content || [];
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
                            <h5>其他入库</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存调整</a>
                            <span>></span>
                            <a href="javascript:void(0);">其他入库</a>
                            <OtherInStockSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:adjust:otherInStock:add" text="添加" className="added" onClick={()=>this.props.actions.otherInStockAddModel(true)}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"250px"}}>入库单号</th>
                                    <th className="procurement" style={{width:"175px"}}>入库人</th>
                                    <th className="time" style={{width:"200px"}}>入库日期</th>
                                    <th className="dosage" style={{width:"150px"}}>入库类型</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { otherInStockList.length <= 0 &&
                                <tr >
                                    <th colSpan="5" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {otherInStockList.map((otherInStock, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={otherInStock.inStockCode}>{otherInStock.inStockCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={otherInStock.operatorName}>{otherInStock.operatorName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={otherInStock.inStockTimeString}>{otherInStock.inStockTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={otherInStock.typeCode}>{this.typeCodeFormat(otherInStock.typeCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingRight": "20px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:adjust:otherInStock:detail" className="gray-btn" text="查看" onClick={()=>actions.otherInStockDetailModal(true,otherInStock.inStockCode)}>查看</ImallA>
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
                    {store.getState().todos.otherInStockDetailState && <OtherInStockDetail store={store} actions={actions}/>}
                    {store.getState().todos.addState && <OtherInStockAddForm store={store} actions={actions} onSubmit={(data)=>this.saveSubmitData(data)}/>}
                    <IMallPaginationBar actions={actions} options={options}/>
                </div>
            </div>
        )
    }
}

OtherInStockList.propTypes = {};

OtherInStockList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OtherInStockList);