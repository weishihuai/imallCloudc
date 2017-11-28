import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {setNoShopType, setSupportDeliveryZone, findByCityName} from "../actions";
import BottomComponent from "./BottomComponent";

class NoShopList extends Component{

    componentWillMount(){

    }

    componentDidMount(){
        new Swiper('.tab-tit',{freeMode : true, slidesPerView : 'auto'});
    }

    deliveryAmountRender(shop){
        switch (shop.deliveryTypeCode){
            case 'NEVER_PAY':
                return '免费配送';
            case 'NEED_PAY':
                return '配送费:'+ shop.deliveryAmount +'元';
            case 'FULL_AMOUNT_NOT_PAY':
                return '满减配送费';
            default:
                return '';
        }
    }

    zoneNameClick(e, zone){
        if($(e.target).hasClass("cur") || $(e.target).parent().hasClass("cur")){
            return false;
        }
        const {setSupportDeliveryZone, findByCityName} = this.props;
        const {supportDeliverZone} = this.context.store.getState().indexTodos;
        let zoneName = zone.zoneName;
        let temp = [];
        supportDeliverZone.map(dz => {
            dz.cur = dz.zoneName == zoneName;
            temp.push(dz);
        });
        findByCityName(zoneName);
        setSupportDeliveryZone(temp);
    }

    getCityName(){
        let cityName = "";
        const {supportDeliverZone} = this.context.store.getState().indexTodos;
        supportDeliverZone.map(zone => {
            if (zone.cur){
                cityName = zone.zoneName;
                return false;
            }
            }
        );
        return cityName;
    }

    render(){
        const {store} = this.context;
        const {currLocation, supportDeliverZone, cityShop} = store.getState().indexTodos;
        return(
            <div className="list">
                <div className="shop-header shop-header-no-list">
                    <div style={{zIndex: "100"}} className="address"><a href="#/index-receive-addr-list" className="elli">送至：{currLocation.addr}</a></div>
                </div>
                <div className="main-index">
                    <div className="choice choice-no-list">
                        <div className="ch-mt">
                            <div className="mt-top">支持配送城市<div className="mt-top-city">{this.getCityName()}<span>/{cityShop.length}</span></div></div>
                            <div className="mt-bot">
                                <div className="tab-tit swiper-container">
                                    <ul className="tab-nav swiper-wrapper clearfix">
                                        {supportDeliverZone.map((zone, index) => {
                                            return(
                                                <li onClick={e => this.zoneNameClick(e, zone)} key={index} className={"swiper-slide" + (zone.cur ? ' cur' : '')}>
                                                    <a href="javascript:;">{zone.zoneName}</a>
                                                </li>
                                            );
                                        })}
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div className="ch-mc">
                            <div className="mc-box clearfix">
                                {
                                    cityShop.map((shop, index) =>{
                                        return(
                                            <div key={index} className="item">
                                                {shop.isNormalSales == 'N' && <span className="stop-busine-t">暂停营业</span>}
                                                <div className="pic"><a href="javascript:;"><img src={shop.logoUrl} /></a></div>
                                                <a href="javascript:;" className="title elli">{shop.shopNm}</a>
                                                <p>配送范围:{shop.deliveryRange/1000}km | {this.deliveryAmountRender(shop)}</p>
                                                <p>地址: {shop.detailLocation}</p>
                                            </div>
                                        );
                                    })
                                }
                            </div>
                        </div>
                    </div>

                    <a href="##" className="back-top"></a>
                </div>

                <div className="list-or-map">列表&nbsp;&nbsp;|&nbsp;&nbsp;<a onClick={() => this.props.setNoShopType('map')} href="javascript:;">地图</a></div>

                <BottomComponent/>
            </div>
        );
    }
}

NoShopList.propTypes = {
};

NoShopList.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setNoShopType, setSupportDeliveryZone, findByCityName}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NoShopList);