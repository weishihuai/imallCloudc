import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";

/*门店位置*/
class WeShopLocation extends Component{

    componentWillMount(){
        const _this = this;
        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://map.qq.com/api/js?v=2.exp&callback=init";
        document.body.appendChild(script);

        _this.idDrag = false;
        window.init = function () {
            let center;
            const {data} = _this.context.store.getState().weChatGoodsListTodos.detailData;
            if(data.shopLat !== null && data.shopLat !== undefined && data.shopLng !== null && data.shopLng !== undefined){
                center = new qq.maps.LatLng(data.shopLat, data.shopLng);
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
            let position = new qq.maps.LatLng(data.shopLat, data.shopLng);
            markerArr.push(new qq.maps.Marker({icon, map, position}));
            _this.markerArr = markerArr;
        };
    }

    componentDidMount(){

    }

    render(){
        return(
            <div className="map">
                <div className="main-index" style={{top: "0"}}>
                    <div className="choice choice-no choice-no-map">
                        <div id="container" className="main-map" style={{height:"13.8125rem"}}>

                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

WeShopLocation.propTypes = {
};

WeShopLocation.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopLocation);