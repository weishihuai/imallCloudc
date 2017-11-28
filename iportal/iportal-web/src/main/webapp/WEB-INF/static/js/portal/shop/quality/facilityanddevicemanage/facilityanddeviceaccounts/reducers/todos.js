import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    deviceTypeCode:"",  //设备类型代码
    deviceNum:"",       //设备编号
    deviceNm:"",        //设备名称
    responseMan:""      //负责人
};

const initialState = {
    params: INIT_PARAMS,         //搜索参数
    isShowAdd:false,            //是否显示新增页面
    isShowDetail:false,         //是否显示详情页面
    isShowEdit:false,           //是否显示编辑页面
    isShowUse:false,            //是否显示使用页面
    isShowMaintaining:false,    //是否显示维护页面
    record:null,                //详情
    page:{}                     //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case types.FACILITY_AND_DEVICE_ACCOUNTS_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
       case types.FACILITY_AND_DEVICE_ACCOUNTS_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //新增
        case types.FACILITY_AND_DEVICE_ACCOUNTS_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd,
                items: action.items
            });
        //详情
        case types.FACILITY_AND_DEVICE_ACCOUNTS_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                data: action.data
            });
        //编辑
        case types.FACILITY_AND_DEVICE_ACCOUNTS_EDIT_MODULE:
            return Object.assign({}, state, {
                isShowEdit: action.isShowEdit,
                data: action.data
            });
        //使用
        case types.FACILITY_AND_DEVICE_ACCOUNTS_USE_MODULE:
            return Object.assign({}, state, {
                isShowUse: action.isShowUse,
                data: action.data
            });
        //维护
        case types.FACILITY_AND_DEVICE_ACCOUNTS_MAINTAINING_MODULE:
            return Object.assign({}, state, {
                isShowMaintaining: action.isShowMaintaining,
                data: action.data
            });
        default:
            return state;
    }
}