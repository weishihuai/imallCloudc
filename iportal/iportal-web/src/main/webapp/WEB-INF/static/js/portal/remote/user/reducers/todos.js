import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    mobile:"",
    name: "",
    shopNm:"",
    state:"",
    startTimeString:"",
    endTimeString:"",
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,                                     //弹窗 新增 状态
    detailState: false,                                  //弹窗 详情 状态
    editState: false,                                    //弹窗 编辑 状态
    detailObject: {},                                    //弹窗 详情 与 编辑 对象
    allJobs:[],                                          //岗位 数据
    jobSelectList:[],                                    //岗位 选中 数据
    isUpdatePassWord: false,                             //弹窗 修改密码 状态
    selectId:"",                                         //勾选 禁用 启用
    selectIds:[],                                        //勾选id
    enableFormState:false,                                //用户状态窗
    isEnable:true,                                      //是否启用
    logParams:{
        page: 0,
        size: 10,
        tableKey:"com.imall.iportal.core.main.entity.SysUser",
        userId:null
    },
    logListState: false,
    logPage: {content: [], totalElements: 0, number: 0, size: 10},
    logDataListState: false,
    logDataId:null,
    logDataList:[],

};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SYS_USER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case types.SYS_USER_SET_IS_ENABLE:
            return Object.assign({}, state, {
                isEnable: action.isEnable
            });
        case types.SYS_USER_SET_SELECT_IDS:
            return Object.assign({}, state, {
                selectIds: action.selectIds,
            });
        case types.SYS_USER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SYS_USER_LIST_DETAIL_MODEL:
            return Object.assign({},state,{
                detailState:action.detailState,
            });
        case types.SYS_USER_LIST_UPDATE_MODEL:
            return Object.assign({},state,{
                editState:action.editState,
             });
        case types.SYS_USER_FIND_BUSINESS_CATEGORY:
            return Object.assign({}, state, {
                allJobs: action.allJobs
            });

        case types.SYS_USER_SET_ALL_JOBS:
            return Object.assign({}, state, {
                jobSelectList: action.jobSelectList
            });
        case types.SYS_USER_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.addState
            });
        case types.SYS_USER_SET_DETAIL_OBJECT:
            return Object.assign({},state,{
                detailObject:action.detailObject,
                jobSelectList:action.detailObject.jobSelectList,
            });
        case types.SYS_USER_SET_PASSWORD_STATE:
            return Object.assign({}, state, {
                isUpdatePassWord:action.isUpdatePassWord,
                selectId:action.selectId
            });
        case types.SYS_USER_SET_ENABLE_FORM_STATE:
            return Object.assign({}, state, {
                enableFormState:action.enableFormState,

            });
        case types.SYS_USER_LOG_LIST_MODEL_STAT_CHANGE:
            return Object.assign({},state,{
                logListState:action.data.isShow,
                logParams: Object.assign({}, state.logParams, {
                    userId: action.data.userId
                })
            });
        case types.SYS_USER_LOG_DATA_LIST_MODEL_STAT_CHANGE:
            return Object.assign({},state,{
                logDataListState:action.data.isShow,
                logDataId: action.data.logDataId
            });
        case types.SYS_USER_LOG_LIST:
            return Object.assign({}, state, {
                logPage: action.data,
                logParams: Object.assign({}, state.logParams, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case types.SYS_USER_LOG_DATA_LIST:
            return Object.assign({}, state, {
                logDataList: action.data
            });
        default:
            return state
    }
}
