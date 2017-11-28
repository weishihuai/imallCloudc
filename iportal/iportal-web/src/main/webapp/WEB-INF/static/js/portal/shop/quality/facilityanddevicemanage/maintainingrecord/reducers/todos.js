import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    deviceTypeCode:"",      //设备类型代码
    deviceNum:"",           //设备编号
    deviceNm:"",            //设备名称
    maintainResponseMan:""  //检修负责人
};

const initialState = {
    params: INIT_PARAMS,         //搜索参数
    isShowDetail:false,         //是否显示详情页面
    page:{}                     //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case types.MAINTAINING_RECORD_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
       case types.MAINTAINING_RECORD_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //详情
        case types.MAINTAINING_RECORD_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                data: action.data
            });
        default:
            return state;
    }
}