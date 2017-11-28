import React, { PropTypes, Component } from 'react'
import { Router, Route, DefaultRoute, IndexRoute, Link, hashHistory } from 'react-router'
import * as TodoActions from '../actions'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import ReceiveAddrAdd from "../../index/components/ReceiveAddrAdd";
import WeChatGoodsList from "../../goodslist/components/WeChatGoodsList";
import WeChatGoodsDetail from "../../goodslist/components/WeChatGoodsDetail";
import WeChatGoodsSearch from "../../goodslist/components/WeChatGoodsSearch";
import ReceiveAddrList from "../../index/components/ReceiveAddrList";
import WeChatUserInfo from "../../wechatuser/components/WeChatUserInfo";
import WeChatUserDetail from "../../wechatuser/components/WeChatUserDetail";
import WeChatUserNickNameUpdate from "../../wechatuser/components/WeChatUserNickNameUpdate";
import WeShopDetail from "../../weshop/components/WeShopDetail";
import WeShopLocation from "../../weshop/components/WeShopLocation";
import Index from "../../index/components/Index";
import NearByShopComponent from "../../index/components/NearByShopComponent";
import ShoppingCart from "../../shopping/components/ShoppingCart";
import ShoppingForm from "../../shopping/components/ShoppingForm";
import SwitchShopComponent from "../../index/components/SwitchShopComponent";
import WeChatUserBindMobile from "../../wechatuser/components/WeChatUserBindMobile";
import WeChatOrderList from "../../order/components/WeChatOrderList";
import WeChatOrderDetail from "../../order/components/WeChatOrderDetail";
import ShoppingAddressForm from "../../shopping/components/ShoppingAddressForm";
import WeChatUserReceiveAddrList from "../../receiveaddr/components/WeChatUserReceiveAddrList";
import WeChatUserReceiveAddrAdd from "../../receiveaddr/components/WeChatUserReceiveAddrAdd";
import WeChatUserReceiveAddrUpdate from "../../receiveaddr/components/WeChatUserReceiveAddrUpdate";
import ShoppingReceiveAddrAdd from "../../shopping/components/ShoppingReceiveAddrAdd";
import OrderSuccessComponent from "../../shopping/components/OrderSuccessComponent";

const showShareHash = ['#/', '#/goods-detail', '#/weshop-detail'];
const menuItems = ["menuItem:share:appMessage", "menuItem:share:timeline", "menuItem:share:qq", "menuItem:share:weiboApp", "menuItem:share:facebook", "menuItem:share:QZone"];
const alwaysHide = ["menuItem:openWithQQBrowser", "menuItem:openWithSafari"];

class MenuRouter extends Component{

    constructor(){
        super();
        this.hash = "";
    }

    componentDidUpdate(){
        let hash = window.location.hash;
        if(this.hash == hash){
            return;
        }
        this.hash = hash;
        let reg = /\/\d+/;
        hash = hash.replace(reg, "");
        wx.hideMenuItems({menuList: alwaysHide});
        if ($.inArray(hash, showShareHash) != -1){
            wx.showMenuItems({menuList: menuItems});
        }else {
            wx.hideMenuItems({menuList: menuItems});
        }
    }

    render(){
        return(
            <Router onUpdate={() => window.scrollTo(0, 0)} history={hashHistory}>
               <Route path="/" component={Index}>
                    <Route path="/index" component={Index}/>
                </Route>


                <Route path="/goods-list" component={WeChatGoodsList}/>
                <Route path="/goods-detail/:id" component={WeChatGoodsDetail}/>
                <Route path="/goods-search" component={WeChatGoodsSearch}/>
                <Route path="/index-receive-addr-list" component={ReceiveAddrList}/>
                <Route path="/index-receive-addr-add" component={ReceiveAddrAdd}/>
                <Route path="/near-by-shop" component={NearByShopComponent}/>
                <Route path="/switch-shop" component={SwitchShopComponent}/>
                <Route path="/wechat-user-info" component={WeChatUserInfo}/>
                <Route path="/wechat-user-info-detail/:id" component={WeChatUserDetail}/>
                <Route path="/wechat-user-nickname-update/:id" component={WeChatUserNickNameUpdate}/>
                <Route path="/weshop-detail/:id" component={WeShopDetail}/>
                {/*<Route path="/weshop-location" component={WeShopLocation}/>*/}
                <Route path="/shopping-list" component={ShoppingCart}/>
                <Route path="/shopping-form" component={ShoppingForm}/>
                <Route path="/shopping-address-form" component={ShoppingAddressForm}/>
                <Route path="/bind-mobile" component={WeChatUserBindMobile}/>
                <Route path="/order-list" component={WeChatOrderList}/>
                <Route path="/order-detail/:id" component={WeChatOrderDetail}/>
                <Route path="/wechat-user-receive-addr-list" component={WeChatUserReceiveAddrList}/>
                <Route path="/wechat-user-receive-addr-add" component={WeChatUserReceiveAddrAdd}/>
                <Route path="/wechat-user-receive-addr-update/:id" component={WeChatUserReceiveAddrUpdate}/>
                <Route path="/shopping-receive-addr-add" component={ShoppingReceiveAddrAdd}/>
                <Route path="/order-success/:id" component={OrderSuccessComponent}/>

                {/*这个一定要放在最后，表示前面路由无匹配的时候，跳转到首页*/}
                <Route path="*" component={Index}/>
            </Router>
        );
    }
}

MenuRouter.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(MenuRouter);