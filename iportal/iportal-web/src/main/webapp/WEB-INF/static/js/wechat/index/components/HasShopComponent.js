import React, {PropTypes, Component} from "react";
import {bindActionCreators} from "redux";
import {connect} from "react-redux";
import {setShowMgrWeChat, findRecommendGroup, queryRecommendGoods, setGroupList, setScrollTop} from "../actions";
import {hashHistory} from "react-router";
import BottomComponent from "./BottomComponent";
import {add as addCart} from "../../shopping/actions";
import AutoLoadMore from "../../../common/component/AutoLoadMore";

class HasShopComponent extends Component{

    loadRecommendGoods(){
        const {goodsPage, groupList} = this.context.store.getState().indexTodos;
        let groupId;
        groupList.map(group => {
            if (group.cur){
                groupId = group.id;
            }
        });
        if (groupId){
            this.props.queryRecommendGoods(groupId, goodsPage.number + 1, goodsPage.size, goodsPage.content);
        }
    }

    queryRecommendGoods(index){
        const {groupList} = this.context.store.getState().indexTodos;
        const {queryRecommendGoods, setGroupList} = this.props;
        groupList.map(group => {
            group.cur = false;
        });
        groupList[index].cur = true;
        setGroupList(groupList);
        queryRecommendGoods(groupList[index].id, 0, 5);
    }

    toGoodsList(categoryId, categoryNm){
        hashHistory.push({pathname: 'goods-list', state: {categoryId, categoryNm}});
    }

    toGoodsDetail(id){
        this.props.setScrollTop(document.body.scrollTop);
        window.location.hash = "#/goods-detail/" + id;
    }

    componentDidUpdate(){
        const {scrollTop} = this.context.store.getState().indexTodos;
        const {setScrollTop} = this.props;
        if(scrollTop > 100 && window.location.hash == "#/"){
            setTimeout(function () {
                document.body.scrollTop = scrollTop;
                setScrollTop(0);
            }, 50);
        }
        if(this.swiper){
            this.swiper.destroy();
        }
        this.swiper = new Swiper('.tab-tit',{freeMode : true, slidesPerView : 'auto'});

        if(this.categorySwiper){
            this.categorySwiper.destroy();
        }
        this.categorySwiper = new Swiper('.entry-nav', {
            pagination: '.swiper-pagination',/*分页器*/
            slidesPerView: 5,
            slidesPerGroup: 5,
            paginationClickable: true,/*点击那几个小点*/
        });
    }

    addCart(e, skuId, imgUrl, target){
        e.stopPropagation();
        this.props.addCart(skuId, () => {
            let src = getSpecImg(imgUrl, '100X100');
            indexToCart(target, src);
        });
    }

    render(){
        const {store} = this.context;
        const {currLocation, shopData, showMgrWeChat, groupList, goodsPage} = store.getState().indexTodos;
        const lastPage = goodsPage.lastPage == undefined ? true : goodsPage.lastPage;
        return(
            <div>
                <div className="shop-header">
                    <div style={{zIndex: "100"}} className="address"><a href="#/index-receive-addr-list" className="elli">送至：{currLocation.addr}</a></div>
                    {shopData.enableSwitchShop && <a href="#/near-by-shop" className="nearby">附近门店</a>}
                    {!shopData.enableSwitchShop && <a href="#/switch-shop" className="nearby">切换门店</a>}
                    <div className="search"><a href="#/goods-search">搜索药品</a></div>
                </div>
                <div className="main-index">
                    <div className="mt">
                        <div onClick={() => window.location.hash = "#/weshop-detail/" + shopData.weShopId} className="mt-pic"><img src={shopData.logoUrl} /></div>
                        <div className="name"><a href={"#/weshop-detail/" + shopData.weShopId}>{shopData.shopNm}</a></div>
                        <div className="contact">
                            <a onClick={() => this.props.setShowMgrWeChat(true)} href="javascript:;" className="wechat">微信</a>
                            <a href={"tel:" + shopData.contactTel} className="phone">电话</a>
                        </div>
                    </div>
                    <div className="swiper-container entry-nav">
                        <div className="swiper-wrapper" style={{height: "auto"}}>
                            {
                                shopData.salesCategoryList.map((c, i) => {
                                    return(
                                        i%2 == 0 ? <div className="swiper-slide">
                                            <div className="item"><a onClick={() => this.toGoodsList(c.id, c.categoryName)}>
                                                <span className="item-img"><img src={c.logoUrl ? c.logoUrl : iportal + "/static/img/wechat/pic70x70.jpg"} /></span>
                                                <span className="item-tit">{c.categoryName}</span>
                                            </a></div>
                                            {i < shopData.salesCategoryList.length - 1 &&
                                            <div className="item">
                                                <a onClick={() => this.toGoodsList(shopData.salesCategoryList[i + 1].id, shopData.salesCategoryList[i + 1].categoryName)}>
                                                    <span className="item-img"><img src={shopData.salesCategoryList[i + 1].logoUrl ? shopData.salesCategoryList[i + 1].logoUrl : iportal + "/static/img/wechat/pic70x70.jpg"} /></span>
                                                    <span className="item-tit">{shopData.salesCategoryList[i + 1].categoryName}</span>
                                                </a>
                                            </div>
                                            }
                                        </div> : ''
                                    );
                                })
                            }
                        </div>

                        <div className="swiper-pagination"></div>
                    </div>
                    <div className="choice">
                        <div className="ch-mt">
                            <div className="mt-top">店长推荐</div>
                            <div className="mt-bot">
                                <div className="tab-tit swiper-container">
                                    <ul className="tab-nav swiper-wrapper clearfix">
                                        {
                                            groupList.map((group, index) => {
                                                return(
                                                    <li onClick={() => this.queryRecommendGoods(index)} key={index} className={"swiper-slide" + (group.cur ? ' cur' : '')}>
                                                        <a href="javascript:;">{group.groupNm}</a>
                                                    </li>
                                                );
                                            })
                                        }
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div className="ch-mc">
                            <div id="goodsList" className="mc-box clearfix">
                                {
                                    goodsPage.content.map((goods, index) => {
                                        return(
                                            <div key={index} className="item" onClick={() => this.toGoodsDetail(goods.id)}>
                                                <div className="pic"><a><img src={getSpecImg(goods.imgUrl, '220X220')} /></a></div>
                                                <a className="title elli">{goods.goodsNm}</a>
                                                <p>规格: {goods.spec}</p>
                                                <div className="rx-box">
                                                    {goods.isPrescriptionDrugs == 'Y' && <span className="rx">处方药</span>}
                                                </div>
                                                <div className="price">¥{goods.memberPrice}</div>
                                                <div className={"dw-btn" + ' i-add-' + index}><a onClick={(e) => this.addCart(e, goods.skuId, goods.imgUrl, '.i-add-' + index)} href="javascript:;"><img src={iportal + "/static/img/wechat/pic66x66.png"}/></a></div>
                                            </div>
                                        );
                                    })
                                }
                                <AutoLoadMore container={'goodsList'} loadMore={this.loadRecommendGoods.bind(this)} lastPage={lastPage}/>
                            </div>
                           </div>
                    </div>

                    <a href="##" className="back-top"></a>
                </div>
                <BottomComponent/>
                {showMgrWeChat && <div className="layer check-wechat-box">
                    <div className="check-wechat">
                        <img src={shopData.shopMgrWeChatUrl}/>
                        <a onClick={() => this.props.setShowMgrWeChat(false)} href="javascript:;" className="icon-close"></a>
                    </div>
                </div>}
            </div>
        );
    }
}

HasShopComponent.propTypes = {
};

HasShopComponent.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        setShowMgrWeChat, findRecommendGroup,
        queryRecommendGoods, setGroupList,
        setScrollTop, addCart}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(HasShopComponent);