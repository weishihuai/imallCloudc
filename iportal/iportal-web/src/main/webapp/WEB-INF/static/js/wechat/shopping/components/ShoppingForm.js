import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {setDefaultReceiverAddr, getDeliveryTime, saveShopping} from "../actions/index";
import {WEB_NAME} from "../../../common/common-constant";
import {
    WECHAT_SHOPPING_FORM_DELIVERY_DATE_LAYER_STAT_CHANGE,
    WECHAT_SHOPPING_FORM_DELIVERY_DATE_CHANGE,
    WECHAT_SHOPPING_FORM_DELIVERY_TIME_CHANGE
} from "../constants/ActionTypes";

class ShoppingForm extends Component {

    componentWillMount() {
        document.title = WEB_NAME + "结算中心";
        this.props.setDefaultReceiverAddr();
        this.props.getDeliveryTime();
    }

    summit(e){
        const {store} = this.context;
        const {isToday, deliveryTime, shoppingCart} = store.getState().shoppingTodos;
        if(!shoppingCart.receiverAddrId){
            showError("请选择配送地址");
            return false;
        }
        const remark = this.refs.remark.value;
        if(remark.length > 255){
            showError("附加要求字符长度过长");
            return false;
        }
        this.props.saveShopping({isToday:isToday, deliveryTime:deliveryTime, remark:remark});
    }

    componentDidMount(){
        const _this = this;
        $("#root").click(function (e) {
            if($(".set-center-layer").is(":visible") && !$(e.target).is(".set-center-layer *")){
                _this.deliveryDateLayerStat(false);
            }
        });
    }

    checkHasRx(){
        const {store} = this.context;
        const cartItems = store.getState().shoppingTodos.shoppingCart.cartItems;
        var hasRx = false;
        for (var i=0;i<cartItems.length;i++) {
            const item = cartItems[i];
            if(item.isRx && item.isItemSelected){
                hasRx = true;
            }
        }
        return hasRx;
    }

    checkReceiverAddr(receiverAddr){
        if(receiverAddr == null){
            return {receiverName:"", contactTel:""};
        }
        return receiverAddr
    }

    deliveryDateLayerStat(state){
        const {store} = this.context;
        store.dispatch({type: WECHAT_SHOPPING_FORM_DELIVERY_DATE_LAYER_STAT_CHANGE, data: state});
    }

    deliveryDateChange(isToday, deliveryDate){
        const {store} = this.context;
        store.dispatch({type: WECHAT_SHOPPING_FORM_DELIVERY_DATE_CHANGE, data: {isToday:isToday, deliveryDate:deliveryDate}});
    }

    deliveryTimeChange(deliveryTime){
        const {store} = this.context;
        store.dispatch({type: WECHAT_SHOPPING_FORM_DELIVERY_TIME_CHANGE, data: deliveryTime});
        store.dispatch({type: WECHAT_SHOPPING_FORM_DELIVERY_DATE_LAYER_STAT_CHANGE, data: false});
    }

    render() {
        const {store} = this.context;
        const {shoppingCart, deliveryDate, deliveryTime, deliveryDateLayerStat, deliveryTimeVo, isToday} = store.getState().shoppingTodos;
        return (
            <div>
                <div className="set-center-main">
                    <div className="address">
                            {
                                !shoppingCart.receiverAddr ? (<a href="#/shopping-address-form">请选择配送地址</a>) :
                                (<a href="#/shopping-address-form">
                                    <span>{shoppingCart.receiverAddr.receiverName}</span>
                                    <span>{shoppingCart.receiverAddr.contactTel}</span>
                                    <p>{shoppingCart.receiverAddr.deliveryAddr + shoppingCart.receiverAddr.detailAddr}</p>
                                </a>)
                            }
                    </div>
                    <div className="time">
                        <a href="javascript:void(0);" onClick={(e) => this.deliveryDateLayerStat(true)}>
                            <span>送达时间</span>
                            <div className="tm-box">
                                <div className="tm-cont">
                                    <i>{deliveryDate}</i>
                                    <i>{deliveryTime}</i>
                                </div>
                            </div>
                        </a>
                    </div>
                    <div className="shop-box">
                        <div className="mt">
                            <a href={"#/weshop-detail/" + shoppingCart.weShopId} className="name">{shoppingCart.weShopName}</a>
                            <span>{shoppingCart.shopPromiseSendTime}小时内送达</span>
                        </div>
                        <div className="mc">
                            {shoppingCart.cartItems.map((cartItem, index) => {
                                return (
                                cartItem.isItemSelected  ? <div className="item" key={index}>
                                    <div className="pic"><a href={"#/goods-detail/" + cartItem.goodsId}><img src={getSpecImg(cartItem.goodsImgUrl, '160X160')}/></a></div>
                                    <div className="md">
                                        <a href="javascript:void(0);" className="title">{cartItem.goodsNm}</a>
                                        <p>规格: {cartItem.spec}</p>
                                        <p>x{cartItem.quantity}</p>
                                    </div>
                                    <div className="mr">
                                        <div className="price">¥{cartItem.unitPrice}</div>
                                        {cartItem.isRx ? <span className="rx">处方药</span> : <span></span>}
                                    </div>
                                </div> : ''
                                );
                            })}
                        </div>
                    </div>
                    <div className="delivery">
                        <span>配送费</span>
                        <i>￥{shoppingCart.freightAmount}</i>
                    </div>
                    <div className="ask">
                        <input maxLength="255" type="text" placeholder="可填写附加要求，我们会尽力安排。" id="remark" name="remark" ref="remark"/>
                    </div>
                    {this.checkHasRx() ? <div className="tips">
                        温馨提示：<br />
                        您的需求清单内含有处方药，请准备好处方笺，药师送货上门后收取。
                    </div> : <div></div>}
                </div>
                <div style={{bottom: "0"}} className="demand-bar">
                    <p>总计<span>￥{shoppingCart.orderTotalAmount}</span></p>
                    <a href="javascript:void(0);" className="sub-btn" onClick={(e)=>this.summit(e)}>确认提交</a>
                </div>
                {deliveryDateLayerStat && <div className="set-center-layer">
                    <div className="set-center-layer-box">
                        <div className="mt"><span>送达时间</span><a href="javascript:void(0);" className="close" onClick={(e) => this.deliveryDateLayerStat(false)}/></div>
                        <div className="ml">
                            {deliveryTimeVo.todayHours.length > 0 && <a href="javascript:void(0);" className={isToday ? "cur" : ""} onClick={(e)=>this.deliveryDateChange(true, deliveryTimeVo.today)}>{deliveryTimeVo.today}</a>}
                            <a href="javascript:void(0);" className={isToday ? "" : "cur"} onClick={(e)=>this.deliveryDateChange(false, deliveryTimeVo.tomorrow)}>{deliveryTimeVo.tomorrow}</a>
                        </div>
                        <div className="mr">
                            {isToday && deliveryTimeVo.inBusinessTime && <a href="javascript:void(0);" className={deliveryTime==='尽快送达' ? "cur" : ""} onClick={(e)=>this.deliveryTimeChange('尽快送达')}>尽快送达</a>}
                            {isToday ? deliveryTimeVo.todayHours.map((hour, index) => {
                                return (
                                <a href="javascript:void(0);" className={deliveryTime===hour ? "cur" : ""} key={index} onClick={(e)=>this.deliveryTimeChange(hour)}>{hour}</a>
                                );
                            }) : deliveryTimeVo.tomorrowHours.map((hour, index) => {
                                return (
                                <a href="javascript:void(0);" className={deliveryTime===hour ? "cur" : ""} key={index} onClick={(e)=>this.deliveryTimeChange(hour)}>{hour}</a>
                                );
                            })}
                        </div>
                    </div>
                </div>}

            </div>
        );
    }
}

ShoppingForm.propTypes = {};

ShoppingForm.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setDefaultReceiverAddr, getDeliveryTime, saveShopping}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ShoppingForm);