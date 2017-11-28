import React, {Component, PropTypes} from "react";
import {Button, Modal} from "react-bootstrap";
import {BootstrapTable, TableHeaderColumn} from "react-bootstrap-table";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import IMallPaginationBar from "../../imallpagination/components/IMallPaginationBar";
import CommonOrderSearchForm from "./CommonOrderSearchForm";
import {
    closeOrderSelectWin,
    commonOrderList,
    commonOrderSearch,
    commonOrderSelect,
    updateSelectedOrder
} from "../actions";

class CommonOrderList extends Component{
    constructor(props) {
        super(props)
    }

    componentWillMount() {

    }

    componentDidUpdate() {

    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().orderTodos;
        this.props.commonOrderList(params, params.page, sizePerPage);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const {params} = store.getState().orderTodos;
        this.props.commonOrderList(params, page - 1, sizePerPage);
    }


    renderShowsTotal(start, to, total) {
        return <span>共&nbsp;{total}&nbsp;条，每页显示数量</span>
    }

    /**
     * 确定选中
     */
    selectConfirm(event){
        const {store} = this.context;
        let{selectedOrderMap} = store.getState().orderTodos;
        this.props.commonOrderSelect(this.props.isSingle, selectedOrderMap, event, this.props.callback);
    }

    /**
     * 关闭窗口
     */
    closeOrderSelectWin(){
        this.props.closeOrderSelectWin();
    }

    /**
     * 全选
     * @param rows      当前分页数据
     * @param number    当前页码
     */
    onSelectAll(rows, number){
        const {store} = this.context;
        let{selectedOrderMap, selectedPageNumMap} = store.getState().orderTodos;
        if(!this.props.isSingle) {//多选
            if(selectedPageNumMap.has(number)){//取消全选
                rows.forEach(function(row){
                    selectedOrderMap.delete(row.id, row);
                });

                selectedPageNumMap.delete(number);
            }else{//全选
                rows.forEach(function(row){
                    selectedOrderMap.set(row.id, row);
                });

                selectedPageNumMap.set(number);
            }
        }

        this.props.updateSelectedOrder(selectedOrderMap, selectedPageNumMap);
    }

    /**
     * 单选
     * @param row       被选中的记录
     * @param rows      当前分页数据
     * @param number    当前页码
     */
    onRowSelect(row, rows, number){
        const {store} = this.context;
        let{selectedOrderMap, selectedPageNumMap} = store.getState().orderTodos;

        if(this.props.isSingle){//单选
            selectedOrderMap.clear();
            selectedOrderMap.set(row.id, row);
        }else{//多选
            if(selectedOrderMap.has(row.id)){//取消选中
                selectedOrderMap.delete(row.id);
                selectedPageNumMap.delete(number);
            }else{//选中
                selectedOrderMap.set(row.id, row);
                let allSelected = true;
                rows.forEach(function(item){
                    if(!selectedOrderMap.has(item.id)){
                        allSelected = false;
                    }
                });

                if(allSelected){//当前页所有记录都被选中
                    selectedPageNumMap.set(number);
                }
            }
        }

        this.props.updateSelectedOrder(selectedOrderMap, selectedPageNumMap);
    }

    render() {
        const {actions}=this.props;
        const {store} = this.context;
        const {page} = store.getState().orderTodos;
        const number = page.number + 1;
        const orderList = store.getState().orderTodos.page.content || [];
        const selectedOrderMap = store.getState().orderTodos.selectedOrderMap;
        const selectedPageNumMap = store.getState().orderTodos.selectedPageNumMap;
        const isSingle = this.props.isSingle;
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
            paginationShowsTotal: page.totalElements > page.size?this.renderShowsTotal:null,
            hideSizePerPage:page.totalElements <= page.size,
            isWindow:true
        };

        return (
            <div className="layer" style={{zIndex:"150"}}>
                <div className="layer-box layer-info layer-addsp w1210">
                    <div className="layer-header">
                        <span>添加订单</span>
                        <a href="javascript:void(0)" className="close" onClick={()=>this.closeOrderSelectWin()}/>
                    </div>
                    <div className="layer-body">
                        <CommonOrderSearchForm actions={actions} commonOrderSearch={this.props.commonOrderSearch}/>
                        <div className="md-box">
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <button className="item-determine black-btn" type="button" style={{border:"none"}} href="javascript:void(0)" onClick={(event)=>this.selectConfirm(event)}>确定选中</button>
                                </div>
                            </div>
                            <div className="box-mc">
                                <div className="table-box">
                                    <table className="w1075">
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox">
                                                {!isSingle && <input type="checkbox" checked={selectedPageNumMap.has(number)} onClick={()=>this.onSelectAll(orderList, number)}/>}
                                            </th>
                                            <th className="serial-number">序号</th>
                                            <th className="commodity-code">销售订单编号</th>
                                            <th className="commodity-name">销售时间</th>
                                            <th className="general-name">订单类型</th>
                                            <th className="specifications">订单金额</th>
                                            <th className="dosage-form">营业员</th>
                                            <th className="manufacturers">是否处方药订单</th>
                                            <th className="manufacturers">是否麻黄碱订单</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        { orderList.length <= 0 &&
                                            <tr >
                                                <th colSpan="9" style={{textAlign:"center"}}>暂无数据</th>
                                            </tr>
                                        }
                                        {
                                            orderList.map((order,index)=>{
                                                return (
                                                    <tr key={index}>
                                                        <td onClick={()=>this.onRowSelect(order, orderList, number)}><input type="checkbox"  checked={selectedOrderMap.has(order.id)} /></td>
                                                        <td><div className="td-cont" >{index+1}</div></td>
                                                        <td><div className="td-cont">{order.orderNum}</div></td>
                                                        <td><div className="td-cont">{order.createDateString}</div></td>
                                                        <td><div className="td-cont">{order.orderSourceName}</div></td>
                                                        <td><div className="td-cont">{order.orderTotalAmount}</div></td>
                                                        <td><div className="td-cont">{order.openOrderMan}</div></td>
                                                        <td><div className="td-cont">{order.isPrescriptionOrder}</div></td>
                                                        <td><div className="td-cont">{order.isEphedrineOrder}</div></td>
                                                    </tr>
                                                );
                                            })
                                        }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <IMallPaginationBar options={options} actions={actions}/> </div>
                </div>
            </div>
        );
    }
}

CommonOrderList.contextTypes = {
    store: React.PropTypes.object
};

CommonOrderList.propsType = {
    callback: PropTypes.func.isRequired
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        commonOrderList,
        commonOrderSelect,
        closeOrderSelectWin,
        updateSelectedOrder,
        commonOrderSearch
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(CommonOrderList);
