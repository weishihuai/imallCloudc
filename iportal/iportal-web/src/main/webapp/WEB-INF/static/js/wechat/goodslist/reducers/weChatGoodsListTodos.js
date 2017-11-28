import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    keywords:"",              //名称/拼音/首拼
    orderBy:"",               //排序的字段 销量/零售价
    sortOrder:"",             //升序/降序
    categoryId: "",           //销售分类ID
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    nextPageLength:10,    //下一页的长度
    quickNavState:false,      //详情页面状态
    detailData:{retailPrice: 0, marketPrice: 0, normalSales: true},          //详情
    renderAgain:false,      //单纯的再次render一遍
    scrollTop: 0,           //滚动条滚动距离
    goodsCount: 0,         //购物车商品数量
    showTel: false,        //显示电话
    startY: 0,        //触摸屏幕时Y轴坐标
};

export default function weChatGoodsListTodos(state = initialState, action) {
    switch (action.type) {
        case types.WE_CHAT_GOODS_LIST_LIST:
            return Object.assign({},state,{
                nextPageLength:action.nextPageLength,
                page:action.data
            });
        case types.WE_CHAT_GOODS_LIST_SET_PARAMS:
            return Object.assign({},state,{
                params:action.data
            });
        case types.WE_CHAT_GOODS_DETAIL_QUICK_NAV_STATE:
            return Object.assign({},state,{
                quickNavState: action.show
            });
        case types.WE_CHAT_GOODS_DETAIL_DATA:
            return Object.assign({},state,{
                detailData:action.data
            });
        case types.WE_CHAT_GOODS_SEARCH_RENDER_AGAIN:
            return Object.assign({},state,{
                renderAgain:!state.renderAgain
            });
        case types.WE_CHAT_GOODS_LIST_SCROLL_TOP:
            return Object.assign({},state,{scrollTop: action.scrollTop});
        case types.WE_CHAT_GOODS_DETAIL_GOODS_COUNTS:
            return Object.assign({},state,{goodsCount: action.goodsCount});
        case types.WE_CHAT_GOODS_DETAIL_SHOW_TEL:
            return Object.assign({},state,{showTel: action.showTel});
        case types.WE_CHAT_GOODS_LIST_STARTY_CHANGE:
            return Object.assign({},state,{startY: action.startY});
        default:
            return state;
    }
}