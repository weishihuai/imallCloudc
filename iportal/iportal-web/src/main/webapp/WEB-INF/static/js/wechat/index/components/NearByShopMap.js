import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {setNearByShopType} from "../actions";
import {INDEX_CUR_WE_SHOP_ID} from "../constants/ActionTypes";

class NearByShopMap extends Component{

    componentWillMount(){
        const _this = this;
        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://map.qq.com/api/js?v=2.exp&callback=init";
        document.body.appendChild(script);

        _this.idDrag = false;
        window.init = function () {
            let center;
            const {nearByShop} = _this.context.store.getState().indexTodos;
            if(nearByShop && nearByShop.length > 0){
                center = new qq.maps.LatLng(nearByShop[0].shopLat, nearByShop[0].shopLng);
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
            nearByShop.map(shop => {
                let position = new qq.maps.LatLng(shop.shopLat, shop.shopLng);
                markerArr.push(new qq.maps.Marker({icon, map, position}));
            });
        };
    }

    componentDidMount(){
        
    }

    deliveryAmountRender(shop){
        switch (shop.deliveryTypeCode){
            case 'NEVER_PAY':
                return '免费配送';
            case 'NEED_PAY':
                return '配送费:'+ shop.deliveryAmount +'元';
            case 'FULL_AMOUNT_NOT_PAY':
                return '满' + shop.deliveryMinOrderAmount + '元免配送费';
            default:
                return '';
        }
    }

    shopDivClick(weShopId){
        $.cookie(INDEX_CUR_WE_SHOP_ID, weShopId);
        window.location.hash = "#";
    }

    render(){
        const {store} = this.context;
        const {setNearByShopType} = this.props;
        const {nearByShop} = store.getState().indexTodos;
        return(
            <div>
                <div className="main-index" style={{top: "0"}}>

                    <div style={{marginBottom: "10rem"}} className="choice choice-no choice-no-map">
                        <div id="container" className="main-map">
                        </div>

                        <div className="ch-mc">
                            {
                                nearByShop.map((shop, index) => {
                                    return(
                                        <div onClick={() => shop.isNormalSales == 'N' ? {} : this.shopDivClick(shop.weShopId)} key={index} className="item">
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

                <div className="list-or-map list-or-map2"><a href="javascript:;" onClick={() => setNearByShopType('list')}>列表</a>&nbsp;&nbsp;|&nbsp;&nbsp;地图</div>
            </div>
        );
    }
}

NearByShopMap.propTypes = {
};

NearByShopMap.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setNearByShopType}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NearByShopMap);