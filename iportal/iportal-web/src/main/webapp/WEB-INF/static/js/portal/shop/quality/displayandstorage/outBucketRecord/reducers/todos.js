import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchValue:"",
    batch: "",
    startTimeString:"",
    endTimeString:"",
    cleaningBucketManNm:""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},

};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SUPPLIER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data==""? initialState.params:action.data
            });
        case types.SUPPLIER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        default:
            return state
    }
}
