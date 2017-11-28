import {
    MEMBER_LIST,
    MEMBER_LIST_ADD_MODEL,
    MEMBER_LIST_EDIT_MODEL,
    MEMBER_SEARCH_PARAMS,
    MEMBER_SHOP_DETAIL_DATA,
    MEMBER_SHOP_DETAIL_MODAL
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchFields: "",  //会员卡号/手机号码
    name: "", //会员姓名
    createDateBeginString: "", //创建时间(开始)
    createDateEndString: "" //创建时间(结束)
};

const INIT_DATA = {
    isGiveCard:'Y'
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    editState: false,   //编辑窗体
    addState: false,   //新增窗体
    data: {},
    id: null,
    memberDetailState: false,//详情窗口状态
    memberDetailData:{}  //详情数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case MEMBER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case MEMBER_LIST_EDIT_MODEL:
            return Object.assign({}, state, {
                editState: action.isShow,
                data: action.data || {},
            });
        case MEMBER_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.isAddShow,
                data: INIT_DATA
            });
        case MEMBER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case MEMBER_SHOP_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                memberDetailState: action.isDetailShow,
                id: action.id
            });
        case MEMBER_SHOP_DETAIL_DATA:
            return Object.assign({}, state, {
                memberDetailData: action.data
            });
        default:
            return state
    }
}
