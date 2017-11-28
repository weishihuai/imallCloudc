import * as types from "../constants/ActionTypes";

const initState = {
    isAutoLocated: true, //是否自动定位
    currLocation: {lat: null, lng: null, cityName: '', province: '', addr: ''},//当前位置
    shopData: {salesCategoryList: []},//门店数据
    noShopType: 'list',//无门店展示类型 list  map
    nearByShopType: 'list',//附近门店展示类型 list  map
    supportDeliverZone: [],//支持配送的区域
    cityShop: [],//城市区域下的门店
    nearByShop: [],//附近门店
    showMgrWeChat: false, //显示店长微信
    groupList: [],//分组列表
    goodsPage: {content: [], number: 0, totalElements: 0, size: 5},//推荐商品
    switchShop: [],//可切换的门店列表
    loginUserInfo: {},//登录用户信息
    showQRCode: true, //显示公众号二维码
    scrollTop: 0,     //滚动条滚动的高度
};

export default function indexTodos(state = initState, action) {
     switch (action.type){
         case types.INDEX_CURR_LOCATION:
             return Object.assign({}, state, {currLocation: action.currLocation, isAutoLocated: action.isAutoLocated});
         case types.INDEX_SHOP_DATA:
             return Object.assign({}, state, {shopData: action.data});
         case types.INDEX_NO_SHOP_TYPE:
             return Object.assign({}, state, {noShopType: action.noShopType});
         case types.INDEX_NEAR_BY_SHOP_TYPE:
             return Object.assign({}, state, {nearByShopType: action.nearByShopType});
         case types.INDEX_SUPPORT_DELIVERY_ZONE:
             return Object.assign({}, state, {supportDeliverZone: action.data});
         case types.INDEX_CITY_SHOP:
             return Object.assign({}, state, {cityShop: action.data});
         case types.INDEX_NEAR_BY_SHOP_DATA:
             return Object.assign({}, state, {nearByShop: action.data});
         case types.INDEX_SHOW_SHOW_MGR_WE_CHAT:
             return Object.assign({}, state, {showMgrWeChat: action.showMgrWeChat});
         case types.INDEX_GROUP_LIST:
             return Object.assign({}, state, {groupList: action.data});
         case types.INDEX_GOODS_PAGE:
             return Object.assign({}, state, {goodsPage: action.data});
         case types.INDEX_SWITCH_SHOP:
             return Object.assign({}, state, {switchShop: action.data});
         case types.INDEX_LOGIN_USER_INFO:
             return Object.assign({}, state, {loginUserInfo: action.data});
         case types.INDEX_SHOW_QR_CODE:
             return Object.assign({}, state, {showQRCode: action.showQRCode});
         case types.INDEX_SCROLL_TOP:
             return Object.assign({}, state, {scrollTop: action.scrollTop});
         default:
             return state;
     }
}