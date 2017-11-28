import {PURCHASE_RECEIVE_LIST, PURCHASE_RECEIVE_LIST_SET_PARAMS, PURCHASE_RECEIVE_SHOW_CONFIRM} from '../constants/ActionTypes';

const INIT_PARAMS = {
    purchaseOrderType: "",  //采购订单类型
    purchaseOrderNum: ""    //采购订单编号
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    showConfirm: false,
    selectId: null,
    selectPurchaseOrderNum:""
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case PURCHASE_RECEIVE_LIST:
            return Object.assign({}, state, {page: action.data});
        case PURCHASE_RECEIVE_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case PURCHASE_RECEIVE_SHOW_CONFIRM:
            return Object.assign({}, state, {showConfirm: action.showConfirm, selectId: action.selectId, selectPurchaseOrderNum:  action.selectPurchaseOrderNum});
        default:
            return state;
    }
}