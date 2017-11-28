import {
    WE_SHOP_FANS_LIST,
    WE_SHOP_FANS_SEARCH_PARAMS,
    WE_SHOP_FANS_UPDATE_MODEL,
    WE_SHOP_FANS_TOTAL_DATA
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    fansName: "",   //粉丝姓名
    mobile: "",  // 手机号码
    nickName: "",  // 微信称昵
    fansSourceCode: "",  // 粉丝来源代码
    buyTimes: "", // 购买次数
    isMember: "", // 粉丝身份
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
    id: "",
    updateState: false,   //更新备注窗体
    fansTotalData:{} , //粉丝总数、会员粉丝数、非会员粉丝数
 };

export default function todos(state = initialState, action) {
    switch (action.type) {
        case WE_SHOP_FANS_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case WE_SHOP_FANS_LIST:   //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case WE_SHOP_FANS_UPDATE_MODEL:  //显示 隐藏 更新 页面
            return Object.assign({}, state, {
                updateState: action.isUpdateModelShow,
                data: action.data || {},
                id:action.id
            });
        case WE_SHOP_FANS_TOTAL_DATA:
            return Object.assign({}, state, {
                fansTotalData: action.data,
            });
        default:
            return state
    }
}
