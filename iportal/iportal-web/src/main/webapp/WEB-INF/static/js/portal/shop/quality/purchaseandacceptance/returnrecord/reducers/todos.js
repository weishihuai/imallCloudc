import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    returnedPurchaseOrderNum: "",    //退货单编号
    isPayed: "",    //是否已结款
    returnedPurchaseType: ""    //退货类型
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    type: "SUMMARY"//类型：汇总（SUMMARY） 明细（DETAIL）
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.RETURN_RECORD_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.RETURN_RECORD_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.RETURN_RECORD_TYPE:
            return Object.assign({}, state, {type: action.data});
        default:
            return state;
    }
}