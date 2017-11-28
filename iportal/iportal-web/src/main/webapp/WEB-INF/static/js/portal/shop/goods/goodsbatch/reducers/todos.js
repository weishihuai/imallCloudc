import {
    GOODS_BATCH_UPDATE_MODEL,
    GOODS_BATCH_LIST,
    GOODS_BATCH_SEARCH_PARAMS,
    GOODS_BATCH_LIST_APPROVE_VALIDATE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    batch: "",  //批号
    searchFields: '' //商品名称、商品编码、拼音码
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    editState: false,   //编辑窗体
    data:{},   //批次信息
    approveData: null,  //审核人信息
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case GOODS_BATCH_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case GOODS_BATCH_UPDATE_MODEL:
            return Object.assign({}, state, {
                editState: action.isShow,
                data: action.data || {},
            });
        case GOODS_BATCH_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case GOODS_BATCH_LIST_APPROVE_VALIDATE:
            return Object.assign({}, state, {
                approveData: action.approveData
            });
        default:
            return state
    }
}
