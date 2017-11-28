import * as types from "../constants/ActionTypes";

const initialState = {
    editGroup: false,//是否显示分组
    groupId: '', //分组ID
    groupData: {recommendList: []},//分组数据
    salesCategoryList: [],//销售分类
    page: {content: [], number: 0, totalElements: 0, size: 10},//商品数据
    goodsSearchParams: {salesCategoryId: '', keyword: ''},//商品搜索参数
    groupList: [],//分组列表
    showAdd: false,//显示新增分组
    goodsList: [], //商品列表
    shopData: [],//门店数据
    showConfirm: false,
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.DECORATION_RECOMMEND_EDIT_GROUP:
            return Object.assign({}, state, {editGroup: action.editGroup, groupId: action.groupId});
        case types.DECORATION_RECOMMEND_GROUP_DATA:
            return Object.assign({}, state, {groupData: action.data});
        case types.DECORATION_RECOMMEND_SALES_CATEGORY_DATA:
            return Object.assign({}, state, {salesCategoryList: action.data});
        case types.DECORATION_RECOMMEND_GOODS_DATA:
            return Object.assign({}, state, {page: action.data});
        case types.DECORATION_RECOMMEND_GOODS_SEARCH_PARAMS:
            return Object.assign({}, state, {goodsSearchParams: action.data});
        case types.DECORATION_RECOMMEND_GROUP_LIST:
            return Object.assign({}, state, {groupList: action.data});
        case types.DECORATION_RECOMMEND_GROUP_SHOW_ADD:
            return Object.assign({}, state, {showAdd: action.showAdd});
        case types.DECORATION_RECOMMEND_GOODS_LIST:
            return Object.assign({}, state, {goodsList: action.data});
        case types.DECORATION_RECOMMEND_SHOP_DATA:
            return Object.assign({}, state, {shopData: action.data});
        case types.DECORATION_RECOMMEND_SHOW_CONFIRM:
            return Object.assign({}, state, {showConfirm: action.showConfirm});
        default:
            return state
    }
}
