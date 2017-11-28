import * as types from '../constants/ActionTypes';

const initialState = {
    purchaseOrderId: null,
    showPurchaseReceive: false,
   data: {orderItemVoList: []}
};

export default function purchaseReceiveTodos(state = initialState, action) {
    switch (action.type) {
        case types.PURCHASE_RECEIVE_DETAIL:
            return Object.assign({}, state, {purchaseOrderId: action.purchaseOrderId, showPurchaseReceive: !state.showPurchaseReceive});
        case types.PURCHASE_RECEIVE_DETAIL_DATA:
            return Object.assign({}, state, {data: action.data});
        default:
            return state;
    }
}