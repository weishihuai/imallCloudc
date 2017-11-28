import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {setNoShopType, setSupportDeliveryZone, findByCityName} from "../actions";

class NoShopMap extends Component{

    componentWillMount(){
        const _this = this;
        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://map.qq.com/api/js?v=2.exp&callback=init";
        document.body.appendChild(script);

        _this.idDrag = false;
        window.init = function () {
            let center;
            const {cityShop} = _this.context.store.getState().indexTodos;
            if(cityShop && cityShop.length > 0){
                center = new qq.maps.LatLng(cityShop[0].shopLat, cityShop[0].shopLng);
            }else {
                center = new qq.maps.LatLng(39.916527,116.397128);
            }
            let map = new qq.maps.Map(document.getElementById('container'),{
                center: center,
                zoom: 13
            });
            _this.map = map;
            let icon = new qq.maps.MarkerImage(iportal + '/static/img/wechat/map-icon.png');
            _this.icon = icon;
            let markerArr = [];
            cityShop.map(shop => {
                let position = new qq.maps.LatLng(shop.shopLat, shop.shopLng);
                markerArr.push(new qq.maps.Marker({icon, map, position}));
            });
            _this.markerArr = markerArr;
        };
    }

    componentDidMount(){
        new Swiper('.tab-tit',{freeMode : true, slidesPerView : 'auto'});
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
        const _this = this;
        findByCityName(zoneName, data => {
            _this.markerArr.map(m => {
                m.setMap(null);
            });
            let markerArr = [];
            data.map(shop => {
                let position = new qq.maps.LatLng(shop.shopLat, shop.shopLng);
                markerArr.push(new qq.maps.Marker({icon: _this.icon, map: _this.map, position}));
            });
            if(data.length > 0){
                let center = new qq.maps.LatLng(data[0].shopLat, data[0].shopLng);
                _this.map.panTo(center);
            }
            _this.markerArr = markerArr;
        });
        setSupportDeliveryZone(temp);
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

    shopDivClick(shop){
        this.map.panTo(new qq.maps.LatLng(shop.shopLat, shop.shopLng));
    }

    render(){
        const {store} = this.context;
        const {currLocation, supportDeliverZone, cityShop} = store.getState().indexTodos;
        return(
            <div className="map">
                <div className="shop-header shop-header-no-map">
                    <div style={{zIndex: "100"}} className="address"><a href="#/index-receive-addr-list" className="elli">送至：{currLocation.addr}</a></div>
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
                <div className="main-index" style={{top: "0"}}>

                    <div style={{marginBottom: "10rem"}} className="choice choice-no choice-no-map">
                        <div id="container" className="main-map">

                        </div>
                        <div className="ch-mc">
                            {
                                cityShop.map((shop, index) => {
                                    return(
                                        <div onClick={() => this.shopDivClick(shop)} key={index} className="item">
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

                <div className="list-or-map list-or-map2"><a onClick={() => this.props.setNoShopType('list')} href="javascript:;">列表</a>&nbsp;&nbsp;|&nbsp;&nbsp;地图</div>
            </div>
        );
    }
}

NoShopMap.propTypes = {
};

NoShopMap.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setNoShopType, setSupportDeliveryZone, findByCityName}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NoShopMap);