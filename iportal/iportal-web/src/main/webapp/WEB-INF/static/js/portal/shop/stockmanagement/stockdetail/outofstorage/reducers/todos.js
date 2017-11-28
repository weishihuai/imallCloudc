import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchFields: "",
    batch: "",
    fromDateString: "",
    logSourceTypeCode: "",
    toDateString: ""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //设置搜索参数
        case types.OUT_OF_STOCK_SEARCH_PARAM_CHANGE:
            return Object.assign({},state,{
                params: action.data
            });
        //列表查找结果
        case types.OUT_OF_STOCK_LIST:
            return Object.assign({},state,{
                page: action.data
            });
        default:
            return state
    }
}
