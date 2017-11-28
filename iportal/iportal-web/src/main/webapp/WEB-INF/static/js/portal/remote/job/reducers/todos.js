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
    selectId:"",                                         //List 选择 操作()
    selectIds:[],                                        //List 批量 操作
    selectRoleIds:[],                                    //角色 选中 列表
    sysRoles:[],                                         //角色 列表
    deleteFormState:false,                               //弹窗 删除 状态
    roleFormState:false                                  //弹窗 角色 状态
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SYS_JOB_SET_SELECT_IDS:
            return Object.assign({}, state, {
                selectIds: action.selectIds
            });
        case types.SYS_JOB_SET_ROLE_FORM_STATE:
            return Object.assign({}, state, {
                roleFormState: action.roleFormState,

            });
        case types.SYS_JOB_SET_SYS_ROLES:
            return Object.assign({}, state, {
                sysRoles: action.sysRoles,

            });
        case types.SYS_JOB_SET_SELECT_ROLE_IDS:
            return Object.assign({}, state, {
                selectRoleIds: action.selectRoleIds,
                selectId:action.selectId
            });
        case types.SYS_JOB_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SYS_JOB_LIST_UPDATE_MODEL:
            return Object.assign({},state,{
                editState:action.editState,
             });
        case types.SYS_JOB_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.addState
            });
        case types.SYS_JOB_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
            });

        case types.SYS_JOB_SET_DELETE_FORM_STATE:
            return Object.assign({}, state, {
                deleteFormState:action.deleteFormState,
            });
        default:
            return state
    }
}
