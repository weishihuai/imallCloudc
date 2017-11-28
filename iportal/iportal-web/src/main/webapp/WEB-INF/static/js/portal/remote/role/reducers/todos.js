import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,                                     //弹窗 新增 状态
    editState: false,                                    //弹窗 编辑 状态
    detailObject: {},                                    //弹窗 详情 与 编辑 对象
    selectId:"",                                         //选择 操作
    selectIds:[],                                        //批量 勾选 删除
    deleteFormState:false,                               //弹窗 删除 状态
    menuFormState:false,                                 //弹窗 菜单 状态
    resourceFormState:false                              //弹窗 资源 状态
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SYS_ROLE_SET_SELECT_IDS:
            return Object.assign({}, state, {
                selectIds: action.selectIds
            });
        case types.SYS_ROLE_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SYS_ROLE_LIST_UPDATE_MODEL:
            return Object.assign({},state,{
                editState:action.editState,
             });
        case types.SYS_ROLE_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.addState
            });
        case types.SYS_ROLE_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
            });

        case types.SYS_ROLE_SET_DELETE_FORM_STATE:
            return Object.assign({}, state, {
                deleteFormState:action.deleteFormState,
            });
        case types.SYS_ROLE_SET_MENU_FORM_STATE:
            return Object.assign({}, state, {
                menuFormState:action.menuFormState,
                selectId:action.selectId||""
            });
        case types.SYS_ROLE_SET_RESOURCE_FORM_STATE:
            return Object.assign({}, state, {
                resourceFormState:action.resourceFormState,
                selectId:action.selectId||""
            });
        default:
            return state
    }
}
