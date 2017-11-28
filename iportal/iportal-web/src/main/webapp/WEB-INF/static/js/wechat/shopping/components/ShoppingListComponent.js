import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {
    select,
    selectAll,
    unselectAll,
    add,
    subtract,
    changeQuantity,
    deleteSelected,
    getShoppingCart
} from "../actions/index";
import {
    WECHAT_SHOPPING_CART,
    WECHAT_SHOPPING_EDIT_STATE_CHANGE,
    WECHAT_SHOPPING_EDIT_SELECT_CHANGE,
    WECHAT_SHOPPING_EDIT_SELECTALL_CHANGE
} from "../constants/ActionTypes";

class ShoppingListComponent extends Component {

    changeQuantity(skuId, quantity){
        if(quantity && quantity > 0){
            this.props.changeQuantity(skuId, quantity);
        }else {
            const store = this.context.store;
            const shoppingCart = store.getState().shoppingTodos.shoppingCart;
            for (let i in shoppingCart.cartItems){
                let item = cartItems[i];
                if (item.skuId == skuId){
                    item.quantity = "";
                    break;
                }
            }
            store.dispatch({type: WECHAT_SHOPPING_CART, data: shoppingCart});
        }
    }

    add(skuId){
        this.props.add(skuId);
    }

    subtract(skuId){
        this.props.subtract(skuId);
    }

    select(skuId){
        const {store} = this.context;
        const isEdit = store.getState().shoppingTodos.isEdit;
        if(isEdit){
            store.dispatch({type: WECHAT_SHOPPING_EDIT_SELECT_CHANGE, skuId: skuId});
        }
        else {
            this.props.select(skuId);
        }
    }

    selectAll(e){
        const {store} = this.context;
        const isEdit = store.getState().shoppingTodos.isEdit;
        if(isEdit){
            store.dispatch({type: WECHAT_SHOPPING_EDIT_SELECTALL_CHANGE, selectAll: true});
        } else {
            if($(".sr-top a.select").hasClass("selected")){
                this.props.unselectAll();
            }else {
                this.props.selectAll();
            }
        }
    }

    unselectAll(e){
        const {store} = this.context;
        const isEdit = store.getState().shoppingTodos.isEdit;
        if(isEdit){
            store.dispatch({type: WECHAT_SHOPPING_EDIT_SELECTALL_CHANGE, selectAll: false});
        }
        else {
            this.props.unselectAll();
        }
    }

    deleteItems(e){
        const {store} = this.context;
        const selectedIds = store.getState().shoppingTodos.selectedIds;
        this.props.deleteSelected(selectedIds);
    }

    edit(e){
        const {store} = this.context;
        store.dispatch({type: WECHAT_SHOPPING_EDIT_STATE_CHANGE, isEdit: true});
    }

    finish(e){
        const {store} = this.context;
        store.dispatch({type: WECHAT_SHOPPING_EDIT_STATE_CHANGE, isEdit: false});
    }

    selectAllState(){
        return this.checkSelectAll() ? "select selected" : "select";
    }

    unselectAllState(){
        return this.checkSelectAll() ? "select" : "select selected";
    }

    checkSelectAll(){
        const {store} = this.context;
        const cartItems = store.getState().shoppingTodos.shoppingCart.cartItems;
        if (cartItems.length == 0){
            return false;
        }
        for (let i=0; i  <cartItems.length; i++) {
            const item = cartItems[i];
            if(!item.isItemSelected){
                return false;
            }
        }
        return true;
    }

    selectState(cartItem){
        const {store} = this.context;
        const isEdit = store.getState().shoppingTodos.isEdit;
        if(isEdit){
            const selectedIds = store.getState().shoppingTodos.selectedIds;
            return $.inArray(cartItem.skuId, selectedIds)> -1 ? "select selected" : "select";
        }
        else {
            return cartItem.isItemSelected ? "select selected" : "select";
        }
    }

    calcSelected(){
        const {store} = this.context;
        const isEdit = store.getState().shoppingTodos.isEdit;
        if(isEdit){
            const selectedIds = store.getState().shoppingTodos.selectedIds;
            return selectedIds.length;
        }
        else {
            const cartItems = store.getState().shoppingTodos.shoppingCart.cartItems;
            var sum = 0;
            for (var i=0;i<cartItems.length;i++) {
                const item = cartItems[i];
                if(item.isItemSelected){
                    sum = sum + 1;
                }
            }
            return sum;
        }
    }

    toSetCenter(){
        const cartItems = this.context.store.getState().shoppingTodos.shoppingCart.cartItems;
        if(cartItems.length == 0){
            showError("您的需求单没有商品");
            return false;
        }
        let selected = false;
        for(let i = 0; i< cartItems.length; i++){
            if (cartItems[i].isItemSelected){
                selected = true;
                break;
            }
        }
        if(!selected){
            showError("请选择下单项");
            return false;
        }
        window.location.hash = "#/shopping-form";
    }

    render() {
        const {store} = this.context;
        const shoppingCart = store.getState().shoppingTodos.shoppingCart;

        return (
            <div>
                <div className="demand-order-main">

                    <div style={{paddingTop: "0"}} className="mc">
                        <div className="store-box">
                            <div className="sr-top">
                                <a href="javascript:void(0);" className={this.selectAllState()} onClick={(e)=>this.selectAll(e)}/>
                                <div className="addr"><a href={"#/weshop-detail/" + shoppingCart.weShopId} className="elli">{shoppingCart.weShopName}</a></div>
                                <a href="javascript:void(0);" className="compile" onClick={(e)=>this.edit(e)}>编辑</a>
                            </div>
                            <div className="sr-bot">
                                {shoppingCart.cartItems.map((cartItem, index) => {
                                    return (
                                        <div className="sr-item" key={index}>
                                            <a href="javascript:void(0);" style={{
                                                height: "100%",
                                                backgroundSize: "0.625rem 0.625rem",
                                                top: "0",
                                                marginTop: "0",
                                                backgroundPosition: "0 1.84rem"
                                            }} className={this.selectState(cartItem)} onClick={(e)=>this.select(cartItem.skuId)}/>
                                            <div className="pic">
                                                <a href={"#/goods-detail/" + cartItem.goodsId}><img src={getSpecImg(cartItem.goodsImgUrl, '220X220')}/></a>
                                            </div>
                                            <div className="sr-rt">
                                                <a href={"#/goods-detail/" + cartItem.goodsId}>
                                                    <p className="title elli">{cartItem.goodsNm}</p>
                                                    <p className="elli">规格: {cartItem.spec}</p>
                                                    <div className="rt-box">
                                                        <i>¥{cartItem.unitPrice}</i>
                                                        {cartItem.isRx ? <span className="rx">处方药</span> : <span/>}
                                                    </div>
                                                </a>
                                                <div className="quantity-wrapper" style={{marginTop:"5px"}}>
                                                    <a href="javascript:void(0);" className={"quantity-decrease " + (cartItem.quantity <= 1 ? "limited" : "")} onClick={(e)=> cartItem.quantity == 1 ? {} : this.subtract(cartItem.skuId)}/>
                                                    <input type="tel" value={cartItem.quantity} onChange={(e)=>{e.target.value = e.target.value.replace(/\D/g, '');this.changeQuantity(cartItem.skuId, e.target.value)}}/>
                                                    <a href="javascript:void(0);" className="quantity-increase" onClick={(e)=>this.add(cartItem.skuId)}/>
                                                </div>
                                            </div>
                                        </div>
                                    );
                                })}
                            </div>
                        </div>
                    </div>
                </div>
               <div className="demand-bar">
                    <p>合计<span>
                        ￥{shoppingCart.orderTotalAmount - shoppingCart.freightAmount}
                        <i>(不含运费)</i>
                    </span></p>
                    <a onClick={() => this.toSetCenter()} className="sub-btn">提交需求单({this.calcSelected()})</a>
                </div>
            </div>
        );
    }
}

ShoppingListComponent.propTypes = {};

ShoppingListComponent.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({select, selectAll, unselectAll, add, subtract, changeQuantity, deleteSelected, getShoppingCart}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingListComponent);