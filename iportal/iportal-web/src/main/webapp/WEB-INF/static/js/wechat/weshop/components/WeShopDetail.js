import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {weShopDetailData, placardInfModel, shopMgrWeiXinModel, weShopTelModel} from "../actions/index";
import WeShopPlacardInf from "./WeShopPlacardInf";
import WeShopMgrWeiXin from "./WeShopMgrWeiXin";
import WeShopTel from "./WeShopTel";
import {WEB_NAME} from "../../../common/common-constant";
import {hashHistory} from "react-router";
import {openLocation} from "../../../common/common-frontend-actions";

/*详情页面*/
class WeShopDetail extends Component {

    constructor(props) {
        super(props);
    }

    componentWillMount() {
        document.title = WEB_NAME + "门店详情";
        this.props.weShopDetailData(this.props.params.id);
    }

    componentDidUpdate() {

    }

    componentDidMount() {
        new Swiper('.tab-tit',{freeMode : true, slidesPerView : 'auto'});
    }

    openLocation(detailData){
        this.props.openLocation(detailData.shopLat, detailData.shopLng, detailData.shopNm, detailData.detailLocation);
    }

    render() {
        const {store} = this.context;
        let {weShopDetailData} = store.getState().weShopTodos || {};
        const imgUrlList = store.getState().weShopTodos.weShopDetailData.imgUrlList || [];
        let deliveryRange = parseFloat(weShopDetailData.deliveryRange / 1000).toFixed(2) || "";
        const {placardInfModelShowState,shopMgrWeiXinModelShowState,telModelShowState} = store.getState().weShopTodos;

        return (
            <div className="main-index stores-details-main">
                <div className="shop-header shop-header-sd">
                    {weShopDetailData.isNormalSales === "N" &&  <div className="stop-business-box">
                        <div className="stop-business">本店暂停营业</div>
                    </div>}
                </div>
                <div className="mt">
                    <div className="mt-pic"><img src={weShopDetailData.logoUrl} alt=""/></div>
                    <div className="name"><a href="javascript:void(0);">{weShopDetailData.shopNm}</a></div>
                    <div className="distribution">配送费&nbsp;{weShopDetailData.deliveryAmount}元&nbsp;&nbsp;|&nbsp;&nbsp;配送范围 {deliveryRange}km</div>
                    <div style={{height: "auto"}} className="sd-info">
                        <p>{weShopDetailData.shopBrief}</p>
                    </div>
                </div>
                <div className="choice">
                    <div className="ch-mt">
                        <div className="mt-bot">
                            <div className="tab-tit swiper-container">
                                <ul className="tab-nav swiper-wrapper clearfix">
                                    {
                                        imgUrlList.map((imgUrl,index)=> {
                                            return (
                                                <li className="swiper-slide" key={index}>
                                                    <a href="javascript:void(0);"><img src={imgUrl} alt=""/></a>
                                                </li>
                                            )
                                        })
                                    }
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div className="ch-mc">
                        <div className="mc-box clearfix">
                            <div className="item"><a href="javascript:void(0);" onClick={() => this.props.placardInfModel(true,weShopDetailData)}>门店公告</a></div>
                            <div className="item"><a href="javascript:void(0);" onClick={() => this.openLocation(weShopDetailData)} >门店位置</a></div>
                            <div className="item"><p className="dt">经营时间</p><p className="dd">{weShopDetailData.sellStartTime}-{weShopDetailData.sellEndTime}</p></div>
                        </div>
                    </div>
                </div>
                <a href="javascript:void(0);" className="back-top"/>
                <div className="bottom-bar bar-wechat-phone">
                    <div className="bar-wechat"><a href="javascript:void(0);" onClick={() => this.props.shopMgrWeiXinModel(true,weShopDetailData.shopMgrWeiXinUrl)}>店长微信</a></div>
                    <div className="bar-phone"><a href="javascript:void(0);" onClick={() => this.props.weShopTelModel(true,weShopDetailData.contactTel)}>门店电话</a></div>
                </div>
                { placardInfModelShowState && <WeShopPlacardInf/> }
                { shopMgrWeiXinModelShowState && <WeShopMgrWeiXin/> }
                { telModelShowState && <WeShopTel/> }
            </div>
        )
    }
}

WeShopDetail.propTypes = {};

WeShopDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        weShopDetailData,
        placardInfModel,
        shopMgrWeiXinModel,
        weShopTelModel,
        openLocation
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeShopDetail);