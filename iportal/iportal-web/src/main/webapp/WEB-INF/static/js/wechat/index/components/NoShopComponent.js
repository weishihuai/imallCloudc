import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import NoShopList from  "./NoShopList";
import NoShopMap from  "./NoShopMap";
import {findSupportDeliveryZone, findByCityName, setSupportDeliveryZone} from "../actions";

class NoShopComponent extends Component{

    componentWillMount(){
        let {supportDeliverZone} = this.context.store.getState().indexTodos;
        if(supportDeliverZone.length == 0){
            const _this = this;
            const {setSupportDeliveryZone, findSupportDeliveryZone, findByCityName} = this.props;
            findSupportDeliveryZone(data => {
                let {currLocation} = _this.context.store.getState().indexTodos;
                let findCity = currLocation.cityName;
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
    }

    componentDidMount(){
    }

    render(){
        const {store} = this.context;
        const {noShopType} = store.getState().indexTodos;
        return(
            <div>
                {noShopType == 'list' && <NoShopList/>}
                {noShopType == 'map' && <NoShopMap/>}
            </div>
        );
    }
}

NoShopComponent.propTypes = {
};

NoShopComponent.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({findSupportDeliveryZone, findByCityName, setSupportDeliveryZone}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NoShopComponent);