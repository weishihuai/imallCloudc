import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import IMallPaginationBar from "../../../../../../common/imallpagination/components/IMallPaginationBar";
import {loadDrugLockDealGoodsBatchList, closeDrugLockDealGoodsBatchSelectWinAndCallBack} from "../actions";
import * as types from "../constants/ActionTypes";

/*锁定商品列表*/
class DrugLockDealGoodsBatchList extends Component {

    componentWillMount() {
        const {store} = this.context;
        this.props.loadDrugLockDealGoodsBatchList(0, 10, store.getState().todos.params);
    }

    selectConfirm() {
        const {store} = this.context;
        const {selectedContents, callback} = store.getState().todos;
        this.props.closeDrugLockDealGoodsBatchSelectWinAndCallBack(selectedContents, callback);
    }

    /*查询*/
    search() {
        const {store} = this.context;
        let params = store.getState().todos.params;
        let [goodsNm,goodsCode,batch]=[$('#_goodsNm').val(), $('#_goodsCode').val(), $('#_batch').val()];
        params.goodsNm = goodsNm;
        params.goodsCode = goodsCode;
        params.goodsBatch = batch;

        store.dispatch({
            type: types.DRUG_LOCK_DEAL_SEARCH_PARAMS,
            params: params
        });
        this.props.loadDrugLockDealGoodsBatchList(0, 10, params);
    }

    reset() {
        const {store} = this.context;
        $('#_goodsNm').val('');
        $('#_goodsCode').val('');
        $('#_batch').val('');
        let params = store.getState().todos.params;
        params.goodsNm = "";
        params.goodsCode = "";
        params.goodsBatch = "";
        store.dispatch({
            type: types.DRUG_LOCK_DEAL_SEARCH_PARAMS,
            params: params
        });
    }

    onRowSelect(store, row, number) {
        let {selectedIds, isSingle,selectAllOrNot, selectedAllPages,isSelected,selectedContents} = store.getState().todos;
        let newSelectedIds = [];
        let newSelectedContents = [];
        let goodsBatchList = store.getState().todos.page.content||[];
        if (isSingle) {
            if(selectedIds.indexOf(row.id)>-1){
                isSelected = false;
            }else{
                isSelected = true;
            }
            if (isSelected) {
                newSelectedIds.push(row.id);
                newSelectedContents.push(row);
            }
        } else {
            if(selectedIds.indexOf(row.id)>-1){
                isSelected = false;
            }else{
                isSelected = true;
            }
            if (isSelected) {
                newSelectedIds = selectedIds.filter(id => id !== row.id).concat(row.id);
                newSelectedContents = selectedContents.filter(obj => obj.id !== row.id).concat(row);
            } else {
                newSelectedIds = selectedIds.filter(id => id !== row.id);
                newSelectedContents = selectedContents.filter(obj => obj.id !== row.id);

                let flag = true;
                goodsBatchList.map((goods,index)=>{//如果当前页每一项都不在选中页面里面的话,设置可以全选  并将当前页从全选的pages里面过滤掉
                    if(newSelectedIds.indexOf(goods.id)>-1){
                        flag = false;
                    }
                });
                if(flag){
                    selectAllOrNot = true;
                    selectedAllPages = selectedAllPages.filter(page=>page!==number);
                }
            }
        }

        store.dispatch({
            type: types.DRUG_LOCK_DEAL_CHANGE_SELECT_GOODS,
            selectedIds: newSelectedIds,
            selectedContents: newSelectedContents,
            isSelected:isSelected,
            selectAllOrNot:selectAllOrNot,
            selectedAllPages:selectedAllPages
        })
    }

    onSelectAll(store, rows,number) {
        let {selectedIds, isSingle,isSelected,selectAllOrNot, selectedContents} = store.getState().todos;
        let newSelectedIds = [];
        let newSelectedContents = [];
        let currentSelectedIds = [];
        let selectedAllPages = store.getState().todos.selectedAllPages||[];
        rows.map((row, index)=> {
            if (row) {
                currentSelectedIds.push(row.id);
            }
        });
        if (isSingle) {
            newSelectedIds = selectedIds;
            newSelectedContents = selectedContents;
        } else {
            if (selectAllOrNot) {
                newSelectedIds = selectedIds.filter(id => currentSelectedIds.indexOf(id) === -1).concat(currentSelectedIds);
                newSelectedContents = selectedContents.filter(obj => currentSelectedIds.indexOf(obj.id) === -1).concat(rows);
                selectedAllPages = selectedAllPages.filter(page=>page!==number).concat(number);
            } else {
                newSelectedIds = selectedIds.filter(id => currentSelectedIds.indexOf(id) === -1);
                newSelectedContents = selectedContents.filter(obj => currentSelectedIds.indexOf(obj.id) === -1);
                selectedAllPages = selectedAllPages.filter(page=>page!==number);
            }
            selectAllOrNot = !selectAllOrNot;
        }
        store.dispatch({
            type: types.DRUG_LOCK_DEAL_CHANGE_SELECT_GOODS,
            selectedIds: newSelectedIds,
            selectedContents: newSelectedContents,
            selectAllOrNot:selectAllOrNot,
            isSelected:isSelected,
            selectedAllPages:selectedAllPages
        })
    }

    onSizePerPageList(sizePerPage) {
        const {store} = this.context;
        let params = store.getState().todos.params;
        this.props.loadDrugLockDealGoodsBatchList(0, sizePerPage, params);
    }

    onPageChange(page, sizePerPage) {
        const {store} = this.context;
        const selectedAllPages = store.getState().todos.selectedAllPages;
        store.dispatch({
            type:types.DRUG_LOCK_DEAL_CHANGE_SELECT_ALL_OR_NOT_STATE,
            data:!(selectedAllPages.indexOf(page)>-1)
        });
        let params = store.getState().todos.params;
        this.props.loadDrugLockDealGoodsBatchList(page - 1, sizePerPage, params);
    }

    closeDrugLockDealGoodsBatchSelectWin(store){
        store.dispatch({
            type:types.DRUG_LOCK_DEAL_GOODS_WIN_CLOSE
        })
    }

    render() {
        const {store} = this.context;
        const page = store.getState().todos.page;
        const drugLockDealGoodsBatchList = store.getState().todos.goodsLockPage.content || [];
        const selectedIds = store.getState().todos.selectedIds || [];
        const selectedAllPages = store.getState().todos.selectedAllPages;
        const number = page.number + 1;
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
            hideSizePerPage: page.totalElements <= page.size,
            isWindow:true
        };
        return (
            <div className="layer" style={{zIndex:"300"}}>
                <div className="layer-box layer-info layer-addsp w1075">
                    <div className="layer-header">
                        <span>添加商品</span>
                        <a href="javascript:void(0)" className="close" onClick={()=>this.closeDrugLockDealGoodsBatchSelectWin(store)}/>
                    </div>
                    <div className="layer-body">
                        <div className="md-box">
                            <div className="box-mc clearfix">
                                <div className="item">
                                    <label>商品编码<input id="_goodsCode" type="text"/></label>
                                </div>
                                <div className="item">
                                    <label>名称<input id="_goodsNm" type="text"/></label>
                                </div>
                                <div className="item">
                                    <label>批号<input id="_batch" type="text"/></label>
                                </div>
                                <a href="javascript:void(0)" className="green-btn" onClick={()=>this.search()}>查询</a>
                                <a href="javascript:void(0)" className="gray-btn" onClick={()=>this.reset()}>重置</a>
                            </div>
                        </div>
                        <div className="md-box">
                            <div className="box-mc receiving-box clearfix">
                                <div className="item">
                                    <button className="item-determine black-btn" style={{border:"none"}} href="javascript:void(0)" onClick={()=>this.selectConfirm()} disabled={selectedIds.length==0}>确定选中</button>
                                </div>
                            </div>
                            <div className="box-mc">
                                <div className="table-box">
                                    <table style={{width:"1020px"}}>
                                        <thead>
                                        <tr>
                                            <th className="th-checkbox" onClick={()=>this.onSelectAll(store,drugLockDealGoodsBatchList,number)}><input type="checkbox" checked={selectedAllPages.indexOf(number) >-1} /></th>
                                            <th className="serial-number">序号</th>
                                            <th className="commodity-code">商品编码</th>
                                            <th className="commodity-name">商品名称</th>
                                            <th className="general-name">通用名称</th>
                                            <th className="specifications">规格</th>
                                            <th className="dosage-form">剂型</th>
                                            <th className="unit">单位</th>
                                            <th className="manufacturers" style={{width:"85px"}}>生产厂商</th>
                                            <th className="unit">库存</th>
                                            <th className="general-name">货位</th>
                                            <th className="general-name">批号</th>
                                            <th className="time" style={{width:"90px",paddingRight:"3px"}}>生产日期</th>
                                            <th className="time" style={{width:"90px",paddingRight:"3px"}}>有效期至</th>
                                            <th className="general-name">锁定数量</th>
                                            <th className="general-name">锁定原因</th>
                                        </tr>
                                        </thead>
                                        { drugLockDealGoodsBatchList.length <= 0 &&
                                        <tbody><tr ><th colSpan="100" style={{textAlign:"center"}}>暂无数据</th></tr></tbody>
                                        }
                                        <tbody>
                                        {
                                            drugLockDealGoodsBatchList.map((goods,index)=>{
                                                return (
                                                    <tr key={index}>
                                                        <td onClick={()=>this.onRowSelect(store, goods,number)}><input type="checkbox"  checked={selectedIds.indexOf(goods.id)>-1} /></td>
                                                        <td><div className="td-cont" >{index+1}</div></td>
                                                        <td><div className="td-cont">{goods.goodsCode}</div></td>
                                                        <td><div className="td-cont">{goods.goodsNm}</div></td>
                                                        <td><div className="td-cont">{goods.commonNm}</div></td>
                                                        <td><div className="td-cont">{goods.spec}</div></td>
                                                        <td><div className="td-cont">{goods.dosageForm}</div></td>
                                                        <td><div className="td-cont">{goods.unit}</div></td>
                                                        <td><div className="td-cont">{goods.produceManufacturer}</div></td>
                                                        <td><div className="td-cont">{goods.currentStock}</div></td>
                                                        <td><div className="td-cont">{goods.storageSpaceNm}</div></td>
                                                        <td><div className="td-cont">{goods.batch}</div></td>
                                                        <td><div className="td-cont">{goods.produceDateString}</div></td>
                                                        <td><div className="td-cont">{goods.validDateString}</div></td>
                                                        <td><div className="td-cont">{goods.lockQuantity}</div></td>
                                                        <td><div className="td-cont">{goods.lockReason}</div></td>
                                                    </tr>
                                                );
                                            })
                                        }
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <IMallPaginationBar options={options} actions={this.props.actions}/> </div>
                </div>
            </div>
        );
    }
}

DrugLockDealGoodsBatchList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        loadDrugLockDealGoodsBatchList,
        closeDrugLockDealGoodsBatchSelectWinAndCallBack
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(DrugLockDealGoodsBatchList);