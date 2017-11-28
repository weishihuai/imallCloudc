import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {QQ_MAP_KEY, QQ_MAP_PROJECT_NM, WEB_NAME} from "../../../common/common-constant";
import {getReceiveAddrList, deleteReceiveAddr, findShop} from "../actions/index";
import {setCurrLocation} from "../actions";
import {INDEX_CUR_LOCATION_COOKIE_KEY} from "../constants/ActionTypes";

class ReceiveAddrList extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "收货地址列表";
        this.props.getReceiveAddrList();
    }

    located(){
        const {setCurrLocation, findShop} = this.props;
        if(!this.geolocation){
            this.geolocation = new qq.maps.Geolocation(QQ_MAP_KEY, QQ_MAP_PROJECT_NM);
        }
        this.geolocation.getLocation(position => {
            findShop(position.lat, position.lng, data => {
                setCurrLocation({lat: position.lat, lng: position.lng, addr: position.addr, cityName: position.city}, false);
                window.location.hash = "#";
            });
        }, null, {timeout: 8000})
    }

    selectAddr(addr){
        const {setCurrLocation, findShop} = this.props;
        findShop(addr.addrLat, addr.addrLng, data => {
            setCurrLocation({lat: addr.addrLat, lng: addr.addrLng, addr: addr.deliveryAddr, cityName: addr.cityName}, false);
            window.location.hash = "#";
        });
    }

    render(){
        const {store} = this.context;
        const receiveAddrList = store.getState().receiveAddrTodos.receiveAddrList;
        const cookieCurLocation = $.cookie(INDEX_CUR_LOCATION_COOKIE_KEY);
        let addr = "";
        if (cookieCurLocation){
            addr = JSON.parse(cookieCurLocation).addr;
        }
        return(
            <div className="main-index select-address">
                <div className="dt">
                    <div onClick={() => this.located()} className="current-position">点击定位当前位置</div>
                </div>
                <ul className="dd">
                    <li>
                        <div style={{color: "#238EE5"}} className="address-text">送至：{addr}</div>
                    </li>
                    {receiveAddrList.map((addr, index) => {
                        return(
                            <li onClick={() => this.selectAddr(addr)} key={index}>
                                <div className="address-text">{addr.deliveryAddr + addr.detailAddr}</div>
                                <div className="contact-number"><p>{addr.receiverName}</p><p>{addr.contactTel}</p></div>
                            </li>
                        );
                    })}
                </ul>
                <div className="check-btn-total">
                    <a href="#/index-receive-addr-add" className="add-btn">新增收货地址</a>
                </div>
            </div>
        );
    }
}

ReceiveAddrList.propTypes = {
};

ReceiveAddrList.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({getReceiveAddrList, deleteReceiveAddr, setCurrLocation, findShop}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(ReceiveAddrList);