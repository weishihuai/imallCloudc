import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    purchaseOrderType: "",  //采购订单类型
    purchaseOrderState: "", //采购订单状态
    purchaseOrderNum: ""    //采购订单编号
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    showAdd: false,
    showConfirm: false,
    selectId: null,
    selectPurchaseOrderNum:""
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.PURCHASE_ORDER_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.PURCHASE_ORDER_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.PURCHASE_ORDER_SHOW_ADD:
            return Object.assign({}, state, {showAdd: !state.showAdd});
        case types.PURCHASE_ORDER_ADD_FORM_INIT:
            return Object.assign({}, state, {goodsData: []});
        case types.PURCHASE_ORDER_SHOW_CONFIRM:
            return Object.assign({}, state, {
                showConfirm: action.showConfirm,
                selectId: action.selectId,
                selectPurchaseOrderNum:  action.selectPurchaseOrderNum});
        default:
            return state;
    }
}