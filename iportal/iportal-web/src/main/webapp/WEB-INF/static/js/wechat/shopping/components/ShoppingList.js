import React, {PropTypes, Component} from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {select, selectAll, unselectAll, add, subtract, changeQuantity, deleteSelected, getShoppingCart} from "../actions/index";
import {findDetailAndOpen} from "../../goodslist/actions";

import {
    WECHAT_SHOPPING_EDIT_STATE_CHANGE,
    WECHAT_SHOPPING_EDIT_SELECT_CHANGE,
    WECHAT_SHOPPING_EDIT_SELECTALL_CHANGE,
    WECHAT_SHOPPING_MESSAGE
} from "../constants/ActionTypes";

class ShoppingList extends Component {

    componentWillMount() {
        this.props.getShoppingCart();
    }

    changeQuantity(skuId, quantity){
        this.props.changeQuantity(skuId, quantity);
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
        }
        else {
            this.props.selectAll();
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

    openGoodsDetailView(id) {
        this.props.findDetailAndOpen(id);
    }

    selectAllState(){
        return this.checkSelectAll() ? "select selected" : "select";
    }

    unselectAllState(){
        return this.checkSelectAll() ? "select" : "select selected";
    }

    checkSelectAll(){
        const {store} = this.context;
        const isEdit = store.getState().shoppingTodos.isEdit;
        var selectAll=true ;
        if(isEdit){
            const selectedIds = store.getState().shoppingTodos.selectedIds;
            const cartItems = store.getState().shoppingTodos.shoppingCart.cartItems;
            for (var i=0;i<cartItems.length;i++) {
                const item = cartItems[i];
                if($.inArray(item.skuId, selectedIds)<= -1){
                    selectAll=false ;
                }
            }
        }
        else {
            const cartItems = store.getState().shoppingTodos.shoppingCart.cartItems;
            for (var i=0;i<cartItems.length;i++) {
                const item = cartItems[i];
                if(!item.isItemSelected){
                    selectAll=false ;
                }
            }
        }
        return selectAll;
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

    render() {
        const {store} = this.context;
        const shoppingCart = store.getState().shoppingTodos.shoppingCart;
        const isEdit = store.getState().shoppingTodos.isEdit;
        const messageIsShow = store.getState().shoppingTodos.messageIsShow;
        const message = store.getState().shoppingTodos.message;

        if(messageIsShow){
            setTimeout(function(){
                store.dispatch({type: WECHAT_SHOPPING_MESSAGE, data: ""});
            },3000)
        }

        return (
            <div>
                <div className="demand-order-main">

                    { messageIsShow ? <div className="layer-warning">{message}</div> : <div></div>}

                    <div className="mt">
                        <a href="javascript:void(0);" className={this.unselectAllState()} onClick={(e)=>this.unselectAll(e)}>全不选</a>
                        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;需求单</span>
                        { isEdit ? <a href="javascript:void(0);" className="compile" onClick={(e)=>this.finish(e)}>完成</a> : <a href="javascript:void(0);" className="compile" onClick={(e)=>this.edit(e)}>编辑</a>}
                    </div>
                    <div className="mc">
                        <div className="store-box">
                            <div className="sr-top">
                                <a href="javascript:void(0);" className={this.selectAllState()} onClick={(e)=>this.selectAll(e)}></a>
                                <div className="addr"><a href={"#/weshop-detail/" + shoppingCart.weShopId} className="elli">{shoppingCart.weShopName}</a></div>
                            </div>
                            <div className="sr-bot">
                                {shoppingCart.cartItems.map((cartItem, index) => {
                                    return (
                                        <div className="sr-item" key={index}>
                                            <a href="javascript:void(0);" className={this.selectState(cartItem)} onClick={(e)=>this.select(cartItem.skuId)}></a>
                                            <div className="pic"><a href="javascript:void(0);" onClick={()=>this.openGoodsDetailView(cartItem.goodsId)}><img src={cartItem.goodsImgUrl} alt="" /></a></div>
                                            <div className="sr-rt">
                                                <a href="javascript:void(0);" className="title elli" onClick={()=>this.openGoodsDetailView(cartItem.goodsId)}>{cartItem.goodsNm}</a>
                                                <p className="elli">规格: {cartItem.spec}</p>
                                                <div className="rt-box">
                                                    <i>¥{cartItem.unitPrice}</i>
                                                    {cartItem.isRx ? <span className="rx">处方药</span> : <span></span>}
                                                </div>
                                                <div className="quantity-wrapper">
                                                    <a href="javascript:void(0);" className={"quantity-decrease " + (cartItem.quantity <= 1 ? "limited" : "")} onClick={(e)=>this.subtract(cartItem.skuId)}></a>
                                                    <input type="tel" value={cartItem.quantity} onChange={(e)=>this.changeQuantity(cartItem.skuId, e.target.value)}/>
                                                    <a href="javascript:void(0);" className="quantity-increase" onClick={(e)=>this.add(cartItem.skuId)}></a>
                                                </div>
                                            </div>
                                        </div>
                                    );
                                })}
                            </div>
                        </div>
                    </div>
                </div>
                { isEdit ? (<div className="demand-bar">
                    <a href="javascript:void(0);" className="del-btn" onClick={(e)=>this.deleteItems(e)}>删除({this.calcSelected()})</a>
                </div>) : (<div className="demand-bar">
                    <p>总计<span>￥{shoppingCart.orderTotalAmount}</span></p>
                    <a href="#/shopping-form" className="sub-btn">提交需求单({this.calcSelected()})</a>
                </div>)}
            </div>
        );
    }
}

ShoppingList.propTypes = {};

ShoppingList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({select, selectAll, unselectAll, add, subtract, changeQuantity, deleteSelected, getShoppingCart, findDetailAndOpen}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingList);