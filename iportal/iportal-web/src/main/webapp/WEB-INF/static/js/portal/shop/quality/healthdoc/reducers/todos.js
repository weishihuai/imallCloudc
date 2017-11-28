import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    userName:"",
    state:""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,                                     //弹窗 新增 状态
    detailState: false,                                  //弹窗 详情 状态
    editState: false,                                    //弹窗 编辑 状态
    detailObject: {staffHealthDocInfDetailVoList:[]},                                  //弹窗 详情 与 编辑 对象
    index:"",                                            //下标
};
export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.HEALTH_DOC_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data.page==undefined || initialState.params
            });
        case types.HEALTH_DOC_SET_INDEX:
            return Object.assign({}, state, {
                index: action.index
            });
        case types.HEALTH_DOC_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.HEALTH_DOC_LIST_DETAIL_MODEL:
            return Object.assign({},state,{
                detailState:action.detailState,
            });
        case types.HEALTH_DOC_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.addState
            });
        case types.HEALTH_DOC_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
            });



        default:
            return state
    }
}
