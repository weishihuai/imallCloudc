import React, { PropTypes, Component } from 'react'
import {bindActionCreators} from 'redux';
import {connect} from 'react-redux';
import {setNearByShopType} from "../actions";
import {INDEX_CUR_WE_SHOP_ID} from "../constants/ActionTypes";

class NearByShopList extends Component{

    componentWillMount(){

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
                <div className="main-index nearby-stores-main">
                    <div className="choice choice-no-list">
                        <div className="ch-mc">
                            <div className="mc-box clearfix">
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
                    <a href="javascript:;" className="back-top"></a>
                </div>

                <div className="list-or-map">列表&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javascript:;" onClick={() => setNearByShopType('map')} >地图</a></div>
            </div>
        );
    }
}

NearByShopList.propTypes = {
};

NearByShopList.contextTypes = {
  store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({setNearByShopType}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(NearByShopList);