import React, {Component, PropTypes} from "react";
import {connect} from "react-redux";
import {bindActionCreators} from "redux";
import {query, findDetailAndOpen, setScrollTop, initGoodsPage, setGoodsCount} from "../actions";
import AutoLoadMore from "../../../common/component/AutoLoadMore";
import {WEB_NAME} from "../../../common/common-constant";
import {hashHistory} from "react-router";
import {add as addCart} from "../../shopping/actions";
import {getGoodsCount} from "../../../common/common-frontend-actions";
import {WE_CHAT_GOODS_LIST_STARTY_CHANGE} from "../constants/ActionTypes";

class WeChatGoodsList extends Component {

    constructor(props){
        super(props);
        this.backFunc = this.backFunc.bind(this);
        this.touchmoveHandle = this.touchmoveHandle.bind(this);
        this.touchstartHandle = this.touchstartHandle.bind(this);
        this.isBack = true;
    }

    backFunc(){
        if(this.isBack){
            this.props.initGoodsPage();
        }
    }

    touchmoveHandle(event) {
        const {startY} = this.context.store.getState().weChatGoodsListTodos;
        var touch = event.touches[0];
        if (touch.pageY - startY > 0) {
            $("#search").css("top", "0");
        } else {
            $("#search").css("top", "-3.1rem");
        }
    }

    touchstartHandle(event) {
        var touch = event.touches[0];
        this.context.store.dispatch({type: WE_CHAT_GOODS_LIST_STARTY_CHANGE, startY: touch.pageY});
    }

    componentWillMount() {
        const {store} = this.context;
        const {page, scrollTop} = store.getState().weChatGoodsListTodos;
        const query = this.props.location.state || {};
        const {setScrollTop, setGoodsCount, getGoodsCount} = this.props;
        if(page.content.length == 0){
            this.props.query({ keywords: query.keywords || '', orderBy:"", sortOrder:"", categoryId: query.categoryId || ''}, 0, page.size);
        }else {
            setScrollTop(scrollTop);
        }
        if (query.categoryNm){
            document.title = WEB_NAME + query.categoryNm;
        }else {
            document.title = WEB_NAME + "商品列表";
        }
        window.addEventListener("popstate", this.backFunc, false);

        getGoodsCount(count => {
            setGoodsCount(count);
        });

        document.addEventListener("touchmove", this.touchmoveHandle, false);
        document.addEventListener("touchstart", this.touchstartHandle, false);
    }

    componentDidMount(){

    }

    componentWillUnmount(){
        window.removeEventListener("popstate", this.backFunc, false);
        document.removeEventListener("touchmove", this.touchmoveHandle, false);
        document.removeEventListener("touchstart", this.touchstartHandle, false);
    }

    componentDidUpdate(){
        const {scrollTop} = this.context.store.getState().weChatGoodsListTodos;
        const {setScrollTop} = this.props;
        if(scrollTop > 100 && window.location.hash.indexOf("goods-list") != -1){
            setTimeout(function () {
                document.body.scrollTop = scrollTop;
                setScrollTop(0);
            }, 50);
        }
    }

    loadMoreData() {
        const {store} = this.context;
        let {params, page} = store.getState().weChatGoodsListTodos;
        this.props.query(params, page.number + 1, page.size, page.content);
    }

    changeSortOrder(orderBy) {
        const {store} = this.context;
        let {page, params} = store.getState().weChatGoodsListTodos;
        params.keywords = $('#keywords').val().trim();

        if (!orderBy) {
            params.orderBy = "";
            params.sortOrder = "";
        }
        if (orderBy && orderBy == params.orderBy) {//第二次点击同样类型的排序 就改变排序顺序
            if (params.sortOrder == "up") {
                params.sortOrder = "down";
            }else{
                params.sortOrder = "up";
                params.orderBy = orderBy;
            }
        }
        if (orderBy && orderBy != params.orderBy) {//点击不同类型的排序 销量默认降序  价格默认升序
            if (orderBy == "salesVolume") {
                params.sortOrder = "down";
            } else {
                params.sortOrder = "up";
            }
            params.orderBy = orderBy;
        }
        this.props.query(params, 0, page.size);
    }

    toGoodsDetail(id){
        this.isBack = false;
        this.props.setScrollTop(document.body.scrollTop);
        window.location.hash = "#/goods-detail/" + id;
    }

    addCart(e, skuId, imgUrl, target){
        e.stopPropagation();
        const {addCart, setGoodsCount} = this.props;
        let total = 0;
        addCart(skuId, data => {
            data.cartItems.map(item => {
                total += parseInt(item.quantity);
            });
            setGoodsCount(total);
            let src = getSpecImg(imgUrl, '100X100');
            goodsListToCart(target, src);
        });
    }

    search(e) {
        // if(e.keyCode==13){
        const {store} = this.context;
        const {params} = store.getState().weChatGoodsListTodos;
        hashHistory.push({pathname: 'goods-search', state: {categoryId: params.categoryId || '', keyword: e.target.value}});
            // this.props.query(temp, 0, page.size);
            // document.activeElement.blur();//搜索后关闭键盘
        // }
    }

    render() {
        const {store} = this.context;
        const contents = store.getState().weChatGoodsListTodos.page.content || [];
        const {params, page, goodsCount} = store.getState().weChatGoodsListTodos;
        const query = this.props.location.state || {};
        const lastPage = page.lastPage == undefined ? true : page.lastPage;
        return (
            <div style={{marginBottom: "-11rem"}} className="main-goods-list">
                <div id="search"  data-position="fixed" data-role="header" role="banner" style={{position: "fixed", backgroundColor:"#fff", top: 0, left: 0, rigth:0, transition: "top 250ms", width:"100%" ,zIndex: 10}}>
                    <div className="list-search">
                        <input defaultValue={query.keywords} onClick={e => this.search(e)} readOnly="readOnly" type="search" id="keywords" placeholder="搜索药品名称"/>
                    </div>
                    <ul className="mt">
                        <li className={params.sortOrder ? "" : "active"}>
                            <a onClick={()=>this.changeSortOrder("")}>综合</a>
                        </li>
                        <li className={params.sortOrder && params.orderBy == "salesVolume" ? "active" : ""}>
                            <a onClick={()=>this.changeSortOrder("salesVolume")}>销量</a>
                        </li>
                        <li className={params.sortOrder && params.orderBy == "retailPrice" ? "sort-price" : ""}>
                            <a onClick={()=>this.changeSortOrder("retailPrice")}>价格</a>
                        </li>
                    </ul>
                </div>
                {
                    contents.length == 0 && <div className="no-data" >暂无数据</div>
                }
                <div className="mc" id="goodsList">
                    {
                        contents.map((goods, index)=> {
                            return (
                                <div style={contents.length == index + 1 ? {borderBottom: "none"} : {}} key={index} className="item"  onClick={() => this.toGoodsDetail(goods.id)}>
                                    <div className="pic">
                                        <a><img src={getSpecImg(goods.imgUrl, '220X220')}/></a>
                                    </div>
                                    <a className="title elli">{goods.goodsNm}</a>
                                    <p>{"规格:" + goods.spec}</p>
                                    <div className="rx-box">
                                        {goods.isPrescriptionDrugs == "Y" &&
                                        <span className="rx">处方药</span>}
                                    </div>
                                    <div className="price">{goods.isMember=="Y"?"¥" + goods.memberPrice.toFixed(2):"¥" + goods.retailPrice.toFixed(2)}</div>
                                    <div className={"dw-btn" + " l-add-"+ index}>
                                        <a onClick={(e) => this.addCart(e, goods.skuId, goods.imgUrl, '.l-add-' + index)}>
                                            <img style={{width: "1.375rem", height: "1.375rem"}} src="/iportal/static/img/wechat/pic66x66.png"/>
                                        </a>
                                    </div>
                                </div>
                            )
                        })
                    }
                </div>

                <div onClick={() => window.location.hash = '#/shopping-list'} className="fl-car"><span>{goodsCount}</span></div>

                {/*加载更多*/}
                <AutoLoadMore container={'goodsList'} loadMore={this.loadMoreData.bind(this)} lastPage={lastPage}/>

            </div>
        )
    }
}

WeChatGoodsList.propTypes = {};

WeChatGoodsList.contextTypes = {
    store: React.PropTypes.object
};

function mapStateToProps(state) {
    return state;
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({
        query, findDetailAndOpen, setScrollTop, initGoodsPage, addCart, setGoodsCount, getGoodsCount
    }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(WeChatGoodsList);