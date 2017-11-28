import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    acceptanceOrderNum: ""    //验收单编号
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    showDetail: false,
    showAdd: false,
    detailData: {itemList: []},
    storageSpaceList: [],//货位列表
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.FAST_RECEIVE_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.FAST_RECEIVE_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.FAST_RECEIVE_DETAIL:
            return Object.assign({}, state, {showDetail: !state.showDetail, detailData: action.data});
        case types.FAST_RECEIVE_STORAGE_SPACE_DATA:
            return Object.assign({}, state, {storageSpaceList: action.data});
        case types.FAST_RECEIVE_ADD:
            return Object.assign({}, state, {showAdd: !state.showAdd});
        default:
            return state;
    }
}