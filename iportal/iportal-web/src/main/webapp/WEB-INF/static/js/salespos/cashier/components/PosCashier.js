import React, {Component} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import PosMemberAddForm from "./PosMemberAddForm";
import PosGoodsSelectModal from "./PosGoodsSelectModal";
import DeleteConfirmView from "./DeleteConfirmView";
import PosCartItmQuantityUpdateForm from "./PosCartItmQuantityUpdateForm";
import PosSettlementModal from "./PosSettlementModal";
import PosCashierShiftForm from "./PosCashierShiftForm";
import {
    POS_CASHIER_SELECT_BATCH_QUANTITY_CHANGE,
    POS_CASHIER_CART_ITEM_INDEX_UP,
    POS_CASHIER_CART_ITEM_INDEX_DOWN,
    POS_CASHIER_SETTLEMENT_MODAL,
    POS_CASHIER_CART_ITEM_INDEX_RESET,
    POS_CASHIER_SELECTED_GOODS_BATCH_STOCK
} from "../constants/ActionTypes";
import {niftyNoty} from "../../../common/common";
import {REGEXP_SIGNLESS_INT} from "../../../common/common-constant";

/**
 * 销售pos - 收银页面
 */

class PosCashier extends Component {

    constructor(props) {
        super(props);
        this.cashierOp = this.cashierOp.bind(this);
    }

    componentWillMount() {
        this.props.actions.createShiftRecord();//创建交班记录
        this.props.actions.getShoppingCart();
        this.props.actions.getLoginCashier();
    }

    componentDidMount() {
        window.addEventListener('keydown', this.cashierOp);

    }

    componentWillUnmount() {
        window.removeEventListener('keydown', this.cashierOp);
    }

    cashierOp(event) {
        const {store} = this.context;
        const {searchGoodsState, searchBatchState, selectCartItemIndex, cartItems, settlementStat, shiftRecordStat,confirmModelState,isDeleteAll,updateItemQuantityStat} = store.getState().todos;
        const { updateQuantityModal,changeConfirmState} = this.props.actions;

        if(searchGoodsState || searchBatchState || settlementStat || shiftRecordStat||updateItemQuantityStat) {
            return;
        }
        const oldjs = document.getElementById("notyLayer");

        switch(event.keyCode){
            //esc
            case 27:
                event.preventDefault();
                if(oldjs) {
                    oldjs.parentNode.removeChild(oldjs);
                }
                break;
            //up
            case 38:
                event.preventDefault();
                this.refs.searchFields.blur();
                store.dispatch({type: POS_CASHIER_CART_ITEM_INDEX_UP});
                break;
            //down
            case 40:
                event.preventDefault();
                this.refs.searchFields.blur();
                store.dispatch({type: POS_CASHIER_CART_ITEM_INDEX_DOWN});
                break;
            //F1
            case 112:
                event.preventDefault();
                this.refs.searchFields.select();
                break;
            //F2
            case 113:
                event.preventDefault();
                if((confirmModelState&&isDeleteAll=="Y")||updateItemQuantityStat||settlementStat||oldjs){
                    return;
                }
                if(cartItems.length == 0){//购物车还没有商品直接返回
                    return ;
                }
                if(selectCartItemIndex < 0) {//购物车有商品 但是没有选中 提示操作
                    niftyNoty("请用↑↓箭头选中要删除的单品");
                    return;
                }
                this.refs.searchFields.blur();
                changeConfirmState(true,"N");
                break;
            //F3
            case 114:
                event.preventDefault();
                if((confirmModelState&&isDeleteAll=="N")||updateItemQuantityStat||settlementStat||oldjs){
                    return;
                }
                if(cartItems.length == 0){
                    return ;
                }
                this.refs.searchFields.blur();
                changeConfirmState(true,"Y");
                break;
            //F4
            case 115:
                event.preventDefault();
                if(confirmModelState||settlementStat||oldjs){
                    return;
                }
                if(this.checkIsSelected()){
                    updateQuantityModal(true);
                }
                break;
            //F10
            case 121:
                event.preventDefault();
                if(confirmModelState||updateItemQuantityStat||oldjs){
                    return;
                }
                if(cartItems && cartItems.length > 0) {
                    store.dispatch({type: POS_CASHIER_SETTLEMENT_MODAL, isShow: true});
                }
                break;
            //F12
            case 123:
                event.preventDefault();
                this.shift();
                break;
        }

        //ALT+R
        if(event.keyCode == 82 && event.altKey){
            event.preventDefault();
            window.open(iportal + "/returned.html");
        }
    }

    removeCartItem(){
        const {store} = this.context;
        const {cartItems,selectCartItemIndex} = store.getState().todos;
        const {changeConfirmState} = this.props.actions;
        if(cartItems.length == 0){//购物车还没有商品直接返回
            return ;
        }
        if(selectCartItemIndex < 0) {//购物车有商品 但是没有选中 提示操作
            niftyNoty("请用↑↓箭头选中要删除的单品");
            return;
        }
        this.refs.searchFields.blur();
        changeConfirmState(true,"N");
    }
    removeCart(){
        const {store} = this.context;
        const {cartItems} = store.getState().todos;
        const {changeConfirmState} = this.props.actions;
        if(cartItems.length == 0){
            return ;
        }
        this.refs.searchFields.blur();
        changeConfirmState(true,"Y");
    }


    checkIsSelected(){
        const {selectCartItem} = this.context.store.getState().todos;
        if(selectCartItem.batch!=""){
            return true;
        }
        niftyNoty("请用↑↓箭头选择要修改的商品批次!");
        return false;
    }

    settlement() {

        const {store} = this.context;
        const {cartItems} = store.getState().todos;

        if(cartItems && cartItems.length > 0) {
            store.dispatch({type: POS_CASHIER_SETTLEMENT_MODAL, isShow: true});
        }
    }

    memberAdd(row) {
        this.props.actions.memberAddModel(true,row);
    }

    shift() {
        this.props.actions.shiftModel(true);
    }

    selectBatchQuantityFocus(){
        return ()=>{
            this.refs.selectBatchQuantity.select();
        }
    }

    selectBatchDrugFocus(){
        return ()=>{
            this.refs.searchFields.select();
        }
    }

    resetCartItemIndex(){
        this.context.store.dispatch({
            type: POS_CASHIER_CART_ITEM_INDEX_RESET
        })
    }
    memberSearch() {
        if(!$.trim(this.refs.memberSearch.value)){
            return ;
        }
        this.props.actions.memberSearchData($.trim(this.refs.memberSearch.value), this.context.store.getState().todos.uniqueKey);
        this.refs.memberSearch.value = "";
    };

    onReset() {
        const {memberData} = this.context.store.getState().todos;
        if(!$.isEmptyObject(memberData)){
            this.props.actions.resetMemberSearch(this.context.store.getState().todos.uniqueKey);
        }
        this.refs.memberSearch.value = "";
    }

    toGoodsSearchData(event) {

        const {store} = this.context;
        const {searchGoodsState, searchBatchState, settlementStat, shiftRecordStat} = store.getState().todos;

        if(searchGoodsState || searchBatchState || settlementStat || shiftRecordStat) {
            return;
        }

        if(event.keyCode == 13) {
            this.props.actions.goodsSearchData(true, event.target.value.trim());
        }

    }

    checkAndAddToCart(e,uniqueKey, selectSkuId, selectBatch){
        if(e.keyCode==13 && e.target.value <=0){
            niftyNoty("请输入一个正整数!");
            return;
        }
        if(e.keyCode==13 && e.target.value>0){
            if(!REGEXP_SIGNLESS_INT.test(e.target.value)){
                niftyNoty("请输入一个正整数!");
                return;
            }
            const stock = this.context.store.getState().todos.selectGoodsStock;
            if(stock != null && e.target.value > stock){
                niftyNoty("数量不能大于库存!");
                return;
            }
            //商品批次库存复位为null
            this.context.store.dispatch({
                type:POS_CASHIER_SELECTED_GOODS_BATCH_STOCK,
            });
            //添加到购物车
            const {memberData} = this.context.store.getState().todos;
            this.props.actions.addToCart(uniqueKey, selectSkuId, selectBatch, e.target.value,memberData.mobile);
            //焦点回到药品输入框
            this.refs.searchFields.select();
            //清空数量的值
            this.refs.selectBatchQuantity.value ='';
        }
    }

    selectBatchQuantityChange(value) {
        this.context.store.dispatch({
            type: POS_CASHIER_SELECT_BATCH_QUANTITY_CHANGE,
            data: value
        })
    }



    render() {
        const {store} = this.context;
        const {actions} = this.props;
        const {memberData, addMemberState, searchGoodsState,selectBatchQuantity, goodsSearchFields, selectSkuId, cartItems, shoppingCart, loginCashierData,
            selectGoodsUnitPrice, selectBatch, selectGoodsNm, uniqueKey, updateItemQuantityStat, selectCartItemIndex, settlementStat, shiftRecordStat,confirmModelState,isDeleteAll} = store.getState().todos;
        const {memberAddData, searchFieldsChange, cashierShift} = this.props.actions;
        const selectCartItem = store.getState().todos.selectCartItem||{batch:""};
        const batch = selectCartItem.batch;
        return (
            <div className="pos-box">
                <div className="pos-lt">
                    <div className="lt-top">

                        <div className="item">
                            <span>药&nbsp;品</span>
                            <input id="goodsName" type="text" ref="searchFields" value={goodsSearchFields} placeholder="编码/条形码/名称"
                                   onKeyDown={(e) => this.toGoodsSearchData(e)}
                                   onChange={(e) => searchFieldsChange(e.target.value.trim())}
                                   onFocus={() => this.resetCartItemIndex()}/>
                        </div>
                        <div className="item">
                            <span>名&nbsp;称</span>
                            <input type="text" value={selectGoodsNm} readOnly="readOnly" disabled="disabled"/>
                        </div>
                        <div className="item">
                            <span>批&nbsp;号</span>
                            <input id="batch" type="text" value={selectBatch} readOnly="readOnly" disabled="disabled"/>
                        </div>
                        <div className="item">
                            <span>数&nbsp;量</span>
                            <input id="quantity" type="text" ref="selectBatchQuantity" value={selectBatchQuantity}
                                   onKeyDown={(e) => this.checkAndAddToCart(e,uniqueKey, selectSkuId, selectBatch)}
                                   onChange={(e) => this.selectBatchQuantityChange(e.target.value)}
                                   onFocus={() => this.resetCartItemIndex()}/>
                        </div>
                        <div className="item">
                            <span>单&nbsp;价</span>
                            <input id="unitPrice" type="text" value={selectGoodsUnitPrice} readOnly="readOnly" disabled="disabled"/>
                        </div>
                        <div className="item">
                            <a href="javascript:void(0);" onClick={()=>{this.removeCartItem()}} className="m-btn gray-btn">删除单品[F2]</a>
                            <a href="javascript:void(0);" onClick={()=>{this.removeCart()}} className="m-btn gray-btn">删除整单[F3]</a>
                        </div>
                        <a href="javascript:void(0);" className="account" onClick={()=> this.settlement()}>结算[F10]</a>
                        <div className="item">
                            <a href={iportal + "/returned.html"} className="m-btn gray-btn" target="_blank">退货[Alt+R]</a>
                            <a href="javascript:void(0);" className="m-btn gray-btn" onClick={()=> this.memberAdd()}>新增会员</a>
                        </div>
                        <div className="item">
                            <a href="javascript:void(0);" className="m-btn gray-btn" onClick={()=> this.shift()}>交班[F12]</a>
                            <a href={iportal + "/report.html"} className="m-btn gray-btn" target="_blank">销售报表</a>
                        </div>
                    </div>
                    <div className="lt-bot" style={{position:"relative"}}>
                        <p><span>登录用户：</span>{loginCashierData.userName}</p>
                        <p><span>退货金额：</span>{loginCashierData.returnedPurchaseAmount}</p>
                        <p><span>总&nbsp;金&nbsp;额：</span>{loginCashierData.addUpAmount}</p>
                        <p><span>现&nbsp;&nbsp;&nbsp;金：</span>{loginCashierData.cashAmount}</p>
                        <p><span>上次交班：</span>{loginCashierData.succeedTimeString}</p>
                    </div>
                </div>
                <div className="main">
                    <div className="mt">
                        <div className="search">
                            <span>会员：</span>
                            <input type="text" placeholder="会员卡号/手机号" id="searchText" ref="memberSearch"
                                   onFocus={() => this.resetCartItemIndex()}
                                   onKeyDown={(e) => e.keyCode == 13 && this.memberSearch()}/>
                            <a href="javascript:void(0);" className="green-btn" onClick={this.memberSearch.bind(this)}>搜索</a>
                            <a href="javascript:void(0);" className="gray-btn" onClick={() => this.onReset()}>重置</a>
                        </div>
                        <div className="result">
                            <span>姓名：</span>
                            <i>{memberData ? memberData.name : ""}</i>
                            <span>卡号：</span>
                            <i>{memberData ? memberData.memberCardNum : ""}</i>
                        </div>
                        <div className="amount">
                            <span>修改数量[F4]</span>
                            <em>合计：</em>
                            <i>{shoppingCart ? shoppingCart.orderTotalAmount : 0}元</i>
                        </div>
                    </div>
                    <div className="mc">
                        <div className="pos-table-box">
                            <table>
                                <thead>
                                    <tr>
                                        <th className="th-number">序号</th>
                                        <th className="th-coding">商品编码</th>
                                        <th className="common-name">通用名称</th>
                                        <th className="th-title">商品名称</th>
                                        <th className="manufacturer">生产厂商</th>
                                        <th className="standard">规格</th>
                                        <th className="units">单位</th>
                                        <th className="batch-number">批号</th>
                                        <th className="inventory">数量</th>
                                        <th className="retail-price">单价(元)</th>
                                        <th className="statutory">小计</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {cartItems.map((item, index) =>{
                                        return (
                                            <tr key={index} className={index == selectCartItemIndex ? "active" : ""}>
                                                <td>
                                                    <div className="td-cont td-number">{index + 1}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.goodsCode}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.commonNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.goodsNm}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.produceManufacturer}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.spec}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.unit}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.batch}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.quantity}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.unitPrice}</div>
                                                </td>
                                                <td>
                                                    <div className="td-cont">{item.totalAmount}</div>
                                                </td>
                                            </tr>
                                        )
                                    })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {confirmModelState && isDeleteAll=="Y"&&<DeleteConfirmView store={store} actions={actions} zIndex="999" title="删除整单" text={"是否删除整单?"} confirmBtn="确定" callback={(result) => actions.clearCart(uniqueKey)} close={() => actions.changeConfirmState(false,"")}/>}
                    {confirmModelState && isDeleteAll=="N"&&<DeleteConfirmView store={store} actions={actions} zIndex="999" title="删除单品" text={"是否该单品-"+batch+"?"} confirmBtn="确定" callback={(result) => actions.removeCartItem(uniqueKey, selectCartItem.objectId)} close={() => actions.changeConfirmState(false,"")}/>}
                    {addMemberState && <PosMemberAddForm store={store} actions={actions} onSubmit={(data) => memberAddData(data)}/>}
                    {searchGoodsState && <PosGoodsSelectModal store={store} actions={actions} selectBatchQuantityFocus={this.selectBatchQuantityFocus().bind(this)} selectBatchDrugFocus={this.selectBatchDrugFocus().bind(this)}/>}
                    {updateItemQuantityStat && <PosCartItmQuantityUpdateForm store={store} actions={actions} />}
                    {settlementStat && <PosSettlementModal store={store} actions={actions} />}
                    {shiftRecordStat && <PosCashierShiftForm store={store} actions={actions} onSubmit={(data) => cashierShift(data)}/>}
                </div>
            </div>
        )
    }
}

PosCashier.propTypes = {};

PosCashier.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(PosCashier);