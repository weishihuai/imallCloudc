import {
    STORAGE_SPACE_LIST,
    STORAGE_SPACE_UPDATE_MODEL,
    STORAGE_SPACE_SEARCH_PARAMS,
    STORAGE_SPACE_ADD_MODEL
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    storageSpaceNm: "",  //货位名称
    storageSpaceType: "", //货位类型
    enableStateCode: "" //是否启用状态
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    editState: false,   //编辑窗体
    addState: false,   //新增窗体
    data:{}   //货位信息
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case STORAGE_SPACE_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case STORAGE_SPACE_UPDATE_MODEL:
            return Object.assign({}, state, {
                editState: action.isShow,
                data: action.data || {},
            });
        case STORAGE_SPACE_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.isAddShow,
                data: action.data || {},
            });
        case STORAGE_SPACE_LIST:
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
