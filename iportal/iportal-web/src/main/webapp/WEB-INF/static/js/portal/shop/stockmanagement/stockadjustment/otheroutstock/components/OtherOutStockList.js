import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import OtherOutStockSearchForm from "./OtherOutStockSearchForm";
import OtherOutStockDetail from "./OtherOutStockDetail";
import ImallA from '../../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';
import OtherOutStockAddForm from "./OtherOutStockAddForm";

/**
 * 其他出库列表
 */
class OtherOutStockList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.otherOutStockList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:adjust:otherOutStock:add','stock:adjust:otherOutStock:detail']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.otherOutStockList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.otherOutStockList(params, page - 1, sizePerPage);
    }

    typeCodeFormat(typeCode) {
        switch (typeCode) {
            case "REPORTED_LOSS":
                return "报损";
            case "INTERNAL_USE":
                return "内部领用";
            case "CHECK_OUT":
                return "抽检出库";
            case "OTHER":
                return "其他";
        }
    }

    saveSubmit(data) {
        const {loginUserMessage,params} = this.context.store.getState().todos;
        const otherOutStockSaveData = {
            approveManId: data.approveDataId,
            outStockTimeString:data.outStockTimeString,
            otherOutStockGoodsSaveVoList:data.goodsList,
            remark:data.remark,
            typeCode:data.typeCode,
            operationManId:loginUserMessage.id,
        };
        return this.props.actions.saveOtherOutStock(otherOutStockSaveData,params);
    }

    render() {
        const {store} = this.context;
        const {page} = store.getState().todos;
        const {actions} = this.props;
        const number = page.number + 1;
        const otherOutStockList = store.getState().todos.page.content || [];
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
                            <h5>其他出库</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存调整</a>
                            <span>></span>
                            <a href="javascript:void(0);">其他出库</a>
                            <OtherOutStockSearchForm store={store} actions={actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:adjust:otherOutStock:add" text="添加" className="added" onClick={()=> this.props.actions.otherOutStockAddModel(true)}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"250px"}}>出库单号</th>
                                    <th className="procurement" style={{width:"175px"}}>出库人</th>
                                    <th className="time" style={{width:"200px"}}>出库日期</th>
                                    <th className="dosage" style={{width:"150px"}}>出库类型</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                { otherOutStockList.length <= 0 &&
                                <tr >
                                    <th colSpan="5" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                <tbody>
                                {otherOutStockList.map((otherOutStock, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={otherOutStock.outStockCode}>{otherOutStock.outStockCode}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={otherOutStock.operatorName}>{otherOutStock.operatorName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={otherOutStock.outStockTimeString}>{otherOutStock.outStockTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={otherOutStock.typeCode}>{this.typeCodeFormat(otherOutStock.typeCode)}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" style={{"paddingRight": "20px"}}>
                                            <ImallA href="javascript:void(0);" permissionCode="stock:adjust:otherOutStock:detail" className="gray-btn" text="查看" onClick={()=>actions.otherOutStockDetailModal(true,otherOutStock.outStockCode)}>查看</ImallA>
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
                    {store.getState().todos.addState && <OtherOutStockAddForm store={store} actions={actions} onSubmit={(data)=>{this.saveSubmit(data)}}/>}
                    {store.getState().todos.otherOutStockDetailState && <OtherOutStockDetail store={store} actions={actions}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

OtherOutStockList.propTypes = {};

OtherOutStockList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(OtherOutStockList);