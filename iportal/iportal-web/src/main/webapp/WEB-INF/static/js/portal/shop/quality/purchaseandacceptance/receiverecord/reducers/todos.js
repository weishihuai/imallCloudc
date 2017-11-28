import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    receiveOrderNum: ""    //收货单编号
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    type: "SUMMARY"//类型：汇总（SUMMARY） 明细（DETAIL）
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.RECEIVE_RECORD_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.RECEIVE_RECORD_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.RECEIVE_RECORD_TYPE:
            return Object.assign({}, state, {type: action.data});
        default:
            return state;
    }
}