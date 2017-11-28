import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {WEB_NAME} from "../../../common/common-constant";

/*门店位置*/
class WeShopLocation extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "门店位置";
        let script = document.createElement("script");
        script.type = "text/javascript";
        script.src = "http://map.qq.com/api/js?v=2.exp&callback=init";
        document.body.appendChild(script);
        const query = this.props.location.state || {};
        window.init = function () {
            let center = new qq.maps.LatLng(query.lat, query.lng);
            let map = new qq.maps.Map(document.getElementById('container'),{
                center: center,
                zoom: 13
            });
            let icon = new qq.maps.MarkerImage(iportal + '/static/img/wechat/map-icon.png');
            new qq.maps.Marker({icon, map, position : center});
        };
    }

    componentDidMount(){
        $("#container").bind("DOMNodeInserted", function (e) {
            let t = e;
        });
    }

    render(){
        return(
            <div style={{marginBottom: "-11rem"}} className="map">
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