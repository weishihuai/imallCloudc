import {
    STOCK_OUT_WARNING_SEARCH_PARAMS,
    STOCK_OUT_WARNING_LIST
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchFields: "",  //商品名称、编码、拼音码
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case STOCK_OUT_WARNING_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case STOCK_OUT_WARNING_LIST:
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
