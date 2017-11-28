import * as types from "../constants/ActionTypes";

const initState = {
    showSelectZone: false,
    receiveAddrList: [],//地址列表
    receiveAddrDetail: {},//地址详细数据
};

export default function receiveAddrTodos(state = initState, action) {
     switch (action.type){
         case types.RECEIVE_ADDR_SELECT_ZONE:
             return Object.assign({}, state, {showSelectZone: action.showSelectZone});
         case types.RECEIVE_ADDR_LIST_DATA:
             return Object.assign({}, state, {receiveAddrList: action.data});
         case types.RECEIVE_ADDR_DETAIL_DATA:
             return Object.assign({}, state, {receiveAddrDetail: action.data});
         default:
             return state;
     }
}