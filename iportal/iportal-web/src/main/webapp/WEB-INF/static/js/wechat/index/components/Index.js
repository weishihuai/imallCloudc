import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {QQ_MAP_KEY, QQ_MAP_PROJECT_NM} from "../../../common/common-constant";
import {setCurrLocation, findByCityName, setSupportDeliveryZone, findShop, findShopById, findSupportDeliveryZone, setLoginUserInfo, setShowQRCode, setScrollTop} from "../actions/index";
import HasShopComponent from "./HasShopComponent";
import NoShopComponent from "./NoShopComponent";
import {INDEX_CUR_LOCATION_COOKIE_KEY, INDEX_CUR_WE_SHOP_ID} from "../constants/ActionTypes";
import {getLoginUserInfo} from "../../../common/common-frontend-actions";
import {WEB_NAME} from "../../../common/common-constant";

class Index extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "首页";
        const {setCurrLocation, findShop, findShopById, setSupportDeliveryZone, findByCityName, findSupportDeliveryZone, getLoginUserInfo, setLoginUserInfo, setScrollTop} = this.props;
        getLoginUserInfo(data => {
            setLoginUserInfo(data);
        });
        const {isAutoLocated, scrollTop} = this.context.store.getState().indexTodos;
        if(scrollTop <= 0){
            setScrollTop(0);
            const cookieCurLocation = $.cookie(INDEX_CUR_LOCATION_COOKIE_KEY);
            const weShopId = $.cookie(INDEX_CUR_WE_SHOP_ID);
            if(cookieCurLocation){
                const jsonObj = JSON.parse(cookieCurLocation);
                setCurrLocation(jsonObj);
                if(weShopId){
                    findShopById(weShopId);
                }else {
                    findShop(jsonObj.lat, jsonObj.lng);
                }
            } else {
                if (isAutoLocated){
                    new qq.maps.Geolocation(QQ_MAP_KEY, QQ_MAP_PROJECT_NM).getLocation(position => {
                        let city = position.city;
                        setCurrLocation({lat: position.lat, lng: position.lng, addr: position.addr, cityName: city}, true);
                        findShop(position.lat, position.lng, data => {
                            if(!data.weShopId){
                                findSupportDeliveryZone(data => {
                                    let findCity = city;
                                    let temp = [];
                                    let flag = false;
                                    data.map(zone => {
                                        zone.cur = zone.zoneName == findCity;
                                        if(!flag){
                                            flag = zone.cur;
                                        }
                                        temp.push(zone);
                                    });
                                    if(!flag && temp.length > 0){
                                        temp[0].cur = true;
                                        findCity = temp[0].zoneName;
                                    }
                                    setSupportDeliveryZone(temp);
                                    findByCityName(findCity);
                                });
                            }
                        });
                    }, null, {timeout: 8000});
                }
            }
        }
    }

    componentDidMount(){

    }

    render(){
        const {store} = this.context;
        const {shopData, loginUserInfo, showQRCode} = store.getState().indexTodos;
        return(
           <div>
               {shopData.shopId && <HasShopComponent />}
               {!shopData.shopId && <NoShopComponent/>}
               {loginUserInfo.isSubscribe == 'N' && showQRCode && <div className="layer check-wechat-box">
                   <div className="check-wechat">
                       <img src={'https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=' + loginUserInfo.qrCodeTicket}/>
                       <a onClick={() => this.props.setShowQRCode(false)} href="javascript:;" className="icon-close"></a>
                   </div>
               </div>}
           </div>
        );
    }
}

Index.propTypes = {
};

Index.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        setCurrLocation, findByCityName,
        setSupportDeliveryZone, findShop,
        findShopById, findSupportDeliveryZone,
        getLoginUserInfo, setLoginUserInfo,
        setShowQRCode, setScrollTop}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(Index);