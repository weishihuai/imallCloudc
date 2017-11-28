import {PURCHASE_ORDER_DETAIL, PURCHASE_ORDER_DETAIL_DATA} from '../constants/ActionTypes';

const initialState = {
   id: null,
   showDetail: false,
   data: {orderItemVoList: []}
};

export default function purchaseOrderDetailTodos(state = initialState, action) {
    switch (action.type) {
        case PURCHASE_ORDER_DETAIL:
            return Object.assign({}, state, {id: action.id, showDetail: !state.showDetail});
        case PURCHASE_ORDER_DETAIL_DATA:
            return Object.assign({}, state, {data: action.data});
        default:
            return state;
    }
}