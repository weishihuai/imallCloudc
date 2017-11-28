import * as types from "../constants/ActionTypes";

const initState = {
    showSelectZone: false,
    receiveAddrList: [],//地址列表
};

export default function receiveAddrTodos(state = initState, action) {
     switch (action.type){
         case types.INDEX_RECEIVE_ADDR_SELECT_ZONE:
             return Object.assign({}, state, {showSelectZone: action.showSelectZone});
         case types.INDEX_RECEIVE_ADDR_LIST_DATA:
             return Object.assign({}, state, {receiveAddrList: action.data});
         default:
             return state;
     }
}