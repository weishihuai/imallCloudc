import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    purchaseOrderType: "",  //采购订单类型
    purchaseOrderState: "", //采购订单状态
    purchaseOrderNum: ""    //采购订单编号
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    type: "SUMMARY"//类型：汇总（SUMMARY） 明细（DETAIL）
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.PURCHASE_RECORD_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.PURCHASE_RECORD_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.PURCHASE_RECORD_TYPE:
            return Object.assign({}, state, {type: action.data});
        default:
            return state;
    }
}