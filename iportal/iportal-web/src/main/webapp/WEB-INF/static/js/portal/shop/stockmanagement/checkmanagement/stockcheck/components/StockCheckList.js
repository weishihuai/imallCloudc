import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import StockCheckSearchForm from "./StockCheckSearchForm";
import StockCheckDetail from "./StockCheckDetail";
import StockCheckCheckedForm from "./StockCheckCheckedForm";
import StockCheckAddForm from "./StockCheckAddForm";
import ImallA from '../../../../../../common/imallbutton/components/ImallA'
import {portalOperationalAuth} from '../../../../../../common/imallbutton/actions';
import {STOCK_CHECK_CANCEL_CONFIRM_MODEL} from "../constants/ActionTypes";
import CommonConfirmComponent from "../../../../../../common/component/CommonConfirmComponent";

/**
 * 库存盘点列表*/

class StockCheckList extends Component {

    constructor(props) {
        super(props)
    }

    componentWillMount() {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.stockCheckList(params, params.page, params.size);
        this.props.portalOperationalAuth(['stock:check:check:add','stock:check:check:check','stock:check:check:detail','stock:check:check:cancel']);
    }

    componentDidMount() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:0,size:sizePerPage}));
        this.props.actions.stockCheckList(params, 0, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().todos;
        this.props.actions.setSearchParams(Object.assign({}, params, {page:page-1,size:sizePerPage}));
        this.props.actions.stockCheckList(params, page - 1, sizePerPage);
    }

    stockCheckAdd() {
        this.props.actions.stockCheckAddModel(true);
    }

    checkedStateCodeFormat(checkedStateCode) {
        switch (checkedStateCode) {
            case 'CHECKED':
                return '已盘点';
            case 'UNCHECKED':
                return '未盘点';
            case 'CANCEL':
                return '已取消';
            case "UN_POST":
                return '未过账';
        }
    }

    /*提交数据*/
    submitData(data){
        const params = this.context.store.getState().todos.params;
        const stockCheckSaveVoList = {
            stockCheckGoodsSaveVoList: data.goodsList,
            operationTimeString:data.operationTimeString,
        };
       return this.props.actions.stockCheckAddData(stockCheckSaveVoList,params);
    }

    /*盘点提交数据*/
    checkSubmitData(data){
        const params = this.context.store.getState().todos.params;
        const stockCheckVoList = {
            stockCheckRealCheckQuantityUpdateVoList: data.stockCheckRealCheckQuantityUpdateVoList,
            operationTimeString:data.operationTimeString,
        };
       return this.props.actions.stockCheckUpdateData(stockCheckVoList,params);
    }

    /*取消确认操作*/
    stockCheckCancelConfirmModel(isConfirmModelShow,checkOrderNum){
        this.context.store.dispatch({
            type : STOCK_CHECK_CANCEL_CONFIRM_MODEL,
            isConfirmModelShow: isConfirmModelShow,
            checkOrderNum:checkOrderNum
        });
    }

    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const page = store.getState().todos.page;
        const params = store.getState().todos.params;
        const number = page.number + 1;
        const stockCheckList = store.getState().todos.page.content || [];
        let checkOrderNum = this.context.store.getState().todos.checkOrderNum;
        const _this = this;

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

        function buttonFormatter(checkedStateCode,checkOrderNum,_this) {
            if(checkedStateCode === 'CHECKED') {
                return (
                    <div className="td-cont" style={{paddingLeft:"0px",paddingRight:"25px",textAlign:"right"}}>
                        <ImallA href="javascript:void(0);" permissionCode="stock:check:check:detail" className="gray-btn" text="查看" onClick={()=>actions.stockCheckDetailModal(true,checkOrderNum)}>查看</ImallA>
                    </div>
                );
            } else if(checkedStateCode === 'CANCEL') {
                return (
                    <div className="td-cont" style={{paddingLeft:"0px",paddingRight:"25px",textAlign:"right"}}>
                        <ImallA href="javascript:void(0);" permissionCode="stock:check:check:detail" className="gray-btn" text="查看" onClick={()=>actions.stockCheckDetailModal(true,checkOrderNum)}>查看</ImallA>
                    </div>
                );
            } else {
                return (
                    <div className="td-cont" style={{paddingLeft:"0px",paddingRight:"25px",textAlign:"right"}}>
                        <ImallA href="javascript:void(0);" permissionCode="stock:check:check:cancel" className="gray-btn" text="取消" onClick={()=> _this.stockCheckCancelConfirmModel(true,checkOrderNum)}>取消</ImallA>
                        <ImallA href="javascript:void(0);" permissionCode="stock:check:check:detail" className="gray-btn" text="查看" onClick={()=>actions.stockCheckDetailModal(true,checkOrderNum)}>查看</ImallA>
                        <ImallA href="javascript:void(0);" permissionCode="stock:check:check:check" className="gray-btn" text="盘点" onClick={()=>actions.stockCheckUpdateRealQuantityModal(true,checkOrderNum)}>盘点</ImallA>
                    </div>
                );
            }
        }

        return (
            <div className="main-box">
                <div className="main">
                    <div className="mt">
                        <div className="mt-lt">
                            <h5>库存盘点</h5>
                            <a href="javascript:void(0);">库存管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">盘点管理</a>
                            <span>></span>
                            <a href="javascript:void(0);">库存盘点</a>
                            <StockCheckSearchForm store={store} actions={this.props.actions}/>
                        </div>
                        <div className="mt-rt">
                            <ImallA href="javascript:void(0);" permissionCode="stock:check:check:add" text="添加" className="added" onClick={()=> this.stockCheckAdd()}>添加</ImallA>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="table-box">
                            <table>
                                <thead>
                                <tr>
                                    <th className="th-coding" style={{width:"250px"}}>盘点单号</th>
                                    <th className="procurement" style={{width:"200px"}}>操作人名称</th>
                                    <th className="time" style={{width:"200px"}}>操作时间</th>
                                    <th className="state" style={{width:"200px"}}>状态</th>
                                    <th className="operating">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                { stockCheckList.length <= 0 &&
                                <tr >
                                    <th colSpan="5" style={{textAlign: "center"}}>
                                        <div className="empty-box">
                                            <span>暂无数据</span>
                                        </div>
                                    </th>
                                </tr>
                                }
                                {stockCheckList.map((stockCheck, index) => {
                                    return (<tr key={index}>
                                    <td>
                                        <div className="td-cont" title={stockCheck.checkOrderNum}>{stockCheck.checkOrderNum}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockCheck.operatorName}>{stockCheck.operatorName}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockCheck.operationTimeString}>{stockCheck.operationTimeString}</div>
                                    </td>
                                    <td>
                                        <div className="td-cont" title={stockCheck.checkedStateCode}>{this.checkedStateCodeFormat(stockCheck.checkedStateCode)}</div>
                                    </td>
                                    <td>
                                        {buttonFormatter(stockCheck.checkedStateCode,stockCheck.checkOrderNum,_this)}
                                    </td>
                                </tr>
                                    )
                                })
                                }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {store.getState().todos.confirmModelState && <CommonConfirmComponent store={store} actions={actions} zIndex="999" title="取消盘点" text={"是否取消?"} confirmBtn="确定" callback={(result) => actions.stockCheckCancel(checkOrderNum,params)} close={() => this.stockCheckCancelConfirmModel(false,null)}/>}
                    {store.getState().todos.stockCheckDetailState && <StockCheckDetail store={store} actions={this.props.actions}/>}
                    {store.getState().todos.stockCheckUpdateState && <StockCheckCheckedForm store={store} actions={this.props.actions} onSubmit={(data) => this.checkSubmitData(data)}/>}
                    {store.getState().todos.addState && <StockCheckAddForm store={store} actions={this.props.actions} onSubmit={(data) => this.submitData(data)}/>}
                    <IMallPaginationBar options={options} actions={this.props.actions}/>
                </div>
            </div>
        )
    }
}

StockCheckList.propTypes = {};

StockCheckList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({portalOperationalAuth}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(StockCheckList);