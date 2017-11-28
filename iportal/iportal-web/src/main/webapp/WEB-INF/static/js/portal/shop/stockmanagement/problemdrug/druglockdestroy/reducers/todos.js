import {DRUG_DESTROY_LIST,
    DRUG_DESTROY_SEARCH_PARAMS,
    DRUG_DESTROY_APPROVE_VALIDATE,
    DRUG_DESTROY_MODEL,
RUG_DESTROY_FIND_CURRENT_LOGIN_MESSAGE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    batch: "",  //批号
    keyword: "" //商品编码、拼音码、名称
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
    id: "",
    approveUserNames:{}, //获取审核对象名字
    destroyState:false,  //销毁窗体状态
    loginUserMessage:{realName:""}, //当前登录的用户的信息
 };

export default function todos(state = initialState, action) {
    switch (action.type) {
        case DRUG_DESTROY_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case DRUG_DESTROY_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case DRUG_DESTROY_APPROVE_VALIDATE:
            return Object.assign({},state,{
                approveUserNames:action.approveUserNames,
            });
        case DRUG_DESTROY_MODEL:
            return Object.assign({}, state, {
                destroyState: action.isDestroyShow,
                id: action.id || "",
                data: action.data || {},
            });
        case RUG_DESTROY_FIND_CURRENT_LOGIN_MESSAGE:
            return Object.assign({},state,{
                loginUserMessage:action.loginUserMessage
            });
        default:
            return state
    }
}
