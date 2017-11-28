import {
    STOCK_POSTING_LIST,
    STOCK_POSTING_SEARCH_PARAMS,
    STOCK_POSTING_CONFIRM_POST_MODAL
    } from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    checkOrderNum: "",  //盘点单号
    searchFields: ""  //商品名称、编码、拼音码
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
    id: null,
    confirmModelState:false,
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case STOCK_POSTING_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case STOCK_POSTING_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case STOCK_POSTING_CONFIRM_POST_MODAL:
            return Object.assign({}, state, {
                confirmModelState:action.isConfirmModelShow,
                id: action.id
            });
        default:
            return state
    }
}
