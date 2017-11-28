import {DRUG_LOCK_LIST,
    DRUG_LOCK_ADD_MODEL,
    DRUG_LOCK_SEARCH_PARAMS
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
    addState: false,   //新增窗体
    data: {},
    id: null,
 };

export default function todos(state = initialState, action) {
    switch (action.type) {
        case DRUG_LOCK_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case DRUG_LOCK_ADD_MODEL: //显示 隐藏 添加 页面
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case DRUG_LOCK_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        default:
            return state
    }
}
