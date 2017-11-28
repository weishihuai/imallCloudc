import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {setShopData, setNearByShopType} from "../actions";
import NearByShopList from "./NearByShopList";
import NearByShopMap from "./NearByShopMap";
import {findNearByShop} from "../actions"
import {WEB_NAME} from "../../../common/common-constant";

class NearByShopComponent extends Component{

    componentWillMount(){
        document.title = WEB_NAME + "附近门店";
        const {currLocation} = this.context.store.getState().indexTodos;
        this.props.setNearByShopType("list");
        if(currLocation.lat && currLocation.lng){
            this.props.findNearByShop(currLocation.lat, currLocation.lng);
        }else {
            window.location.hash = "#";
        }
    }

    componentDidMount(){
        new Swiper('.tab-tit',{freeMode : true, slidesPerView : 'auto'});
    }

    render(){
        const {store} = this.context;
        const {nearByShopType} = store.getState().indexTodos;
        return(
            <div style={{marginBottom: "-11rem"}}>
                {nearByShopType == 'list' && <NearByShopList/>}
                {nearByShopType == 'map' && <NearByShopMap/>}
            </div>
        );
    }
}

NearByShopComponent.propTypes = {
};

NearByShopComponent.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setShopData, findNearByShop, setNearByShopType}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NearByShopComponent);