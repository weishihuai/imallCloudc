import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {changeQuickNav, findDetailAndOpen, setGoodsCount, setShowTel} from "../actions";
import {WEB_NAME} from "../../../common/common-constant";
import {add as addCart} from "../../shopping/actions";
import {getGoodsCount, share, openLocation} from "../../../common/common-frontend-actions";
import {hashHistory} from "react-router";
import WeShopTel from "./WeShopTel";

class WeChatGoodsDetail extends Component {

    componentWillMount(){
        this.props.changeQuickNav(false);
        document.title = WEB_NAME + "商品详情";
        const {params, findDetailAndOpen, getGoodsCount, setGoodsCount, share} = this.props;
        findDetailAndOpen(params.id, data => {
            new Swiper('.scroll-imgs', {
                pagination: '.swiper-pagination',/*分页器*/
                slidesPerView: 1,
                paginationClickable: true,/*点击那几个小点*/
                autoplay : 3000,
                loop : true
            });
            let hash = window.location.hash;
            let link = iportal + '/wechat/index.html?router=' + hash.replace("#/", "") + hash;
            let imgUrl = data.imgUrlList && data.imgUrlList.length > 0 ? data.imgUrlList[0] : iportal +"/static/img/wechat/pic66x66.png";
            let desc = "通用名称：" + data.commonNm + "生产厂商：" + data.produceManufacturer +"，规格：" + data.spec;
            // let desc = "通用名称：" + data.commonNm + "生产厂商：生产厂商，规格：" + data.spec;
            share(data.commonNm, link, imgUrl, desc);
        });
        getGoodsCount(count => {
            setGoodsCount(count);
        });
    }

    componentDidMount() {
        const _this = this;
       $(".main-goods-detail-body").click(function (e) {
            if($(e.target).is("#nav")){
                const {quickNavState} = _this.context.store.getState().weChatGoodsListTodos;
                _this.props.changeQuickNav(!quickNavState);
            }else {
                _this.props.changeQuickNav(false);
            }
       });
    }

    addCart(skuId){
        const {addCart, setGoodsCount} = this.props;
        let total = 0;
        addCart(skuId, data => {
             data.cartItems.map(item => {
                total += parseInt(item.quantity);
             });
            setGoodsCount(total);
        });
    }

    openLocation(detailData){
        this.props.openLocation(detailData.shopLat, detailData.shopLng, detailData.shopNm, detailData.detailLocation);
    }

    render() {
        const {store} = this.context;
        const {detailData,quickNavState, goodsCount, showTel} = store.getState().weChatGoodsListTodos;
        const imgUrlList = store.getState().weChatGoodsListTodos.detailData.imgUrlList||[];
        return (
            <div className="main-goods-detail-body">
                {showTel && <WeShopTel tel={detailData.contactTel}/>}
                <div className="main-goods-detail">
                    <div className="swiper-container scroll-imgs">
                        <div className="swiper-wrapper" style={{height: "auto"}}>
                            {
                                imgUrlList.map(imgUrl=>{
                                    return (
                                        <div className="swiper-slide"><a><img src={imgUrl} alt=""/></a></div>
                                    )
                                })
                            }
                        </div>
                        <div className="swiper-pagination"></div>
                    </div>
                    <div className="mc" style={{paddingBottom: "1.5625rem"}}>
                        <div className="mc-box">
                            <div className="name">{detailData.commonNm}</div>
                            <div className="spec">规格: {detailData.spec}</div>
                            <div className="price">
                                <i>¥{detailData.retailPrice.toFixed(2)}</i>
                                <del>¥{detailData.marketPrice.toFixed(2)}</del>
                                {detailData.isPrescriptionDrugs=="Y"&&<span className="rx">处方药</span>}
                            </div>
                        </div>
                        <div className="shop">
                            <span>{detailData.shopNm}</span>
                            <a href={"#/weshop-detail/" + detailData.weShopId}>门店详情</a>
                        </div>
                        <div className="phone"><a onClick={() => this.props.setShowTel(true)} className="elli">{detailData.contactTel}</a></div>
                        <div className="address"><a onClick={() => this.openLocation(detailData)}>{detailData.detailLocation}</a></div>
                        <div className="specification">
                            <div className="sp-mt">药品说明书</div>
                            <div className="sp-mc">
                                <p>{detailData.instructions}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div className="quick-nav">
                    {quickNavState&&<div className="nav-box">
                        <a href="#" className="nb-home">首页</a>
                        <a href="#/goods-search" className="nb-src">搜索</a>
                        <a href="#/wechat-user-info" className="nb-my">我的</a>
                    </div>}
                    <span id="nav">快速<br/>导航</span>
                </div>
                {detailData.showAddCart &&
                <div className="dt-bar">
                    <div onClick={() => window.location.hash = '#/shopping-list'} className="demand">需求单<span>{goodsCount}</span></div>
                    <a onClick={() => this.addCart(detailData.skuId)} className="add">加入需求单</a>
                </div>}
                {!detailData.normalSales && <div className="suspend" style={{bottom: "1.875rem"}}>本店暂停营业</div>}
            </div>
        )
    }
}

WeChatGoodsDetail.propTypes = {};

WeChatGoodsDetail.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        changeQuickNav, findDetailAndOpen, addCart, getGoodsCount, setGoodsCount, setShowTel, share, openLocation
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatGoodsDetail);