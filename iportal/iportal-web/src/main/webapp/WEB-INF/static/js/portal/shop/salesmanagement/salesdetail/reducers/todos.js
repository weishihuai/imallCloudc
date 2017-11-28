import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    orderNum: "",    //订单编号
    orderSourceCode: "",    //订单来源
    orderStateCode: "",    //订单状态
    keyword: ""//关键字
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    type: "SUMMARY",//类型：汇总（SUMMARY） 明细（DETAIL）
    statOrder: {amountReceivable: 0, amountReceived: 0, profit: 0, goodsTotalNum: 0},//订单统计
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SALES_DETAIL_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.SALES_DETAIL_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.SALES_DETAIL_TYPE:
            return Object.assign({}, state, {type: action.data});
        case types.SALES_DETAIL_STAT_ORDER:
            return Object.assign({}, state, {statOrder: action.data});
        default:
            return state;
    }
}