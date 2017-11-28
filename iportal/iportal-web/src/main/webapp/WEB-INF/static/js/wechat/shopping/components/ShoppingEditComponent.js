import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {changeQuantity, deleteSelected, getShoppingCart,deleteConfirmModel} from "../actions/index";
import {WECHAT_SHOPPING_EDIT_STATE_CHANGE} from "../constants/ActionTypes";
import WeChatCommonConfirmComponent from "../../../common/component/WeChatCommonConfirmComponent";

class ShoppingEditComponent extends Component {
    
    deleteItems(e){
        if($(".demand-bar a.disabled").length != 0){
            return false;
        }
        let skuIds = [];
        $(".sr-item a.selected").each(function(){
            skuIds.push($(this).attr("data-skuId"));
        });
        if(skuIds.length == 0){
            return false;
        }
        let isEmpty = skuIds.length == $(".sr-item a.select").length;
        const _this = this;
        this.props.deleteSelected(skuIds, () => {
            $(".demand-bar a.del-btn").addClass("disabled");
            $("#count").html("0");
            $(".cart-edit .sr-top a.select").removeClass("selected");
            $(".sr-item a.select").removeClass("selected");
            if(isEmpty){
                _this.finish();
            }
        });
    }

    finish(){
        const {store} = this.context;
        store.dispatch({type: WECHAT_SHOPPING_EDIT_STATE_CHANGE, isEdit: false});
    }

    componentDidMount(){
        $(".cart-edit .quantity-decrease").click(function () {
            let inObj = $(this).siblings("input");
            let val = inObj.val();
            if(val == 1){
                $(this).addClass("limited");
                return false;
            }else {
                inObj.val(val - 1);
            }
        });

        $(".cart-edit .quantity-increase").click(function () {
            let inObj = $(this).siblings("input");
            $(this).siblings(".quantity-decrease").removeClass("limited");
            inObj.val(parseInt(inObj.val()) + 1);
        });

        $(".cart-edit .sr-item a.select").click(function () {
            $(this).toggleClass("selected");
            let selectedLen = $(".cart-edit .sr-item a.selected").length;
            if(selectedLen == 0){
                $(".demand-bar .del-btn").addClass("disabled");
            }else {
                $(".demand-bar .del-btn").removeClass("disabled");
            }
            $("#count").html(selectedLen);
            if(selectedLen == $(".cart-edit .sr-item a.select").length){
                $(".sr-top a.select").addClass("selected");
            }else {
                $(".sr-top a.select").removeClass("selected");
            }
        });

        $(".cart-edit .sr-top a.select").click(function () {
            $(this).toggleClass("selected");
            if($(this).hasClass("selected")){
                $(".cart-edit .sr-item a.select").addClass("selected");
                $(".demand-bar .del-btn").removeClass("disabled");
            }else {
                $(".cart-edit .sr-item a.select").removeClass("selected");
                $(".demand-bar .del-btn").addClass("disabled");
            }
            $("#count").html($(".cart-edit .sr-item a.selected").length);
        });
    }

    componentWillUnmount(){
        $(".cart-edit").unbind();
    }

    render() {
        const {store} = this.context;
        const shoppingCart = store.getState().shoppingTodos.shoppingCart;
        const {isDeleteModel} = store.getState().shoppingTodos;
        return (
            <div>
                {isDeleteModel &&
                <WeChatCommonConfirmComponent title="确认删除？" text="" callback={(e)=>this.deleteItems(e)} close={()=>this.props.deleteConfirmModel(false)} />
                }
                <div className="demand-order-main cart-edit">
                    <div style={{paddingTop: "0"}} className="mc">
                        <div className="store-box">
                            <div className="sr-top">
                                <a href="javascript:void(0);" className="select"/>
                                <div className="addr"><a href="javascript:;" className="elli">{shoppingCart.weShopName}</a></div>
                                <a href="javascript:void(0);" className="compile" onClick={(e)=>this.finish(e)}>完成</a>
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
                                            }} data-skuId={cartItem.skuId} className="select"/>
                                            <div className="pic"><a href="javascript:void(0);"><img src={getSpecImg(cartItem.goodsImgUrl, '220X220')}/></a></div>
                                            <div className="sr-rt">
                                                <a href="javascript:void(0);" className="title elli">{cartItem.goodsNm}</a>
                                                <p className="elli">规格: {cartItem.spec}</p>
                                                <div className="rt-box">
                                                    <i>¥{cartItem.unitPrice}</i>
                                                    {cartItem.isRx ? <span className="rx">处方药</span> : <span></span>}
                                                </div>
                                                <div className="quantity-wrapper" style={{marginTop:"5px"}}>
                                                    <a href="javascript:void(0);" className={"quantity-decrease " + (cartItem.quantity <= 1 ? "limited" : "")}/>
                                                    <input onKeyUp={e => {e.target.value = e.target.value.replace(/\D/g, '')}} type="tel" defaultValue={cartItem.quantity}/>
                                                    <a href="javascript:void(0);" className="quantity-increase"/>
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
                    <a href="javascript:void(0);" className="del-btn disabled" onClick={()=>this.props.deleteConfirmModel(true,$(".cart-edit .sr-item a.selected").length)}>删除(<span id="count">0</span>)</a>
                </div>
            </div>
        );
    }
}

ShoppingEditComponent.propTypes = {};

ShoppingEditComponent.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({changeQuantity, deleteSelected, getShoppingCart,deleteConfirmModel}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingEditComponent);