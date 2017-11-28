import {
    TEMPERATURE_MOISTURE_MONITOR_RECORD_LIST,
    TEMPERATURE_MOISTURE_MONITOR_RECORD_SEARCH_PARAMS,
    TEMPERATURE_MOISTURE_MONITOR_RECORD_ADD_MODULE,
    STORAGE_SPACE
} from '../constants/ActionTypes';

const INIT_PARAMS = {
    page: 0,
    size: 10,
    storageSpaceId:"",        //货位 ID
    fromMonitorDateString:"", //监控开始日期
    toMonitorDateString:""    //监控结束日期
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    isShowAdd:false,  //是否显示新增页面
    id:null,
    storageSpace: [],   //已启用货位
    page:{}  //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case TEMPERATURE_MOISTURE_MONITOR_RECORD_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
        case TEMPERATURE_MOISTURE_MONITOR_RECORD_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //新增
        case TEMPERATURE_MOISTURE_MONITOR_RECORD_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd
            });
        //货位
        case STORAGE_SPACE:
            return Object.assign({}, state, {
                storageSpace: action.storageSpace
            });
        default:
            return state;
    }
}