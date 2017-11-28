import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    orderSourceCode:"",         //订单 来源 代码
    orderNum:"",                //订单 编号
    isEphedrineOrder:"",        //是否 麻黄碱 订单
    isPrescriptionOrder:"",     //是否 处方药 订单
    formCreateDateString:"",    //销售开始时间
    toCreateDateString:""       //销售结束时间
};

const initialState = {
    params: INIT_PARAMS,                 //搜索参数
    page:{},                             //列表数据
    isShowOrderWin:false,               //是否显示订单选择窗口
    selectedOrderMap:new Map(),         //选择的订单
    selectedPageNumMap:new Map()       //全选的分页页码
};

export default function orderTodos(state = initialState, action) {
    switch (action.type) {
        //初始化参数
        case types.COMMON_ORDER_SEARCH_PARAMS_INIT:
            return Object.assign({}, state, {
                params: {
                    page: 0,
                    size: 10,
                    orderSourceCode:"",                                 //订单 来源 代码
                    orderNum:"",                                        //订单 编号
                    isEphedrineOrder:action.isEphedrineOrder,           //是否 麻黄碱 订单
                    isPrescriptionOrder:action.isPrescriptionOrder,     //是否 处方药 订单
                    formCreateDateString:"",                            //销售开始时间
                    toCreateDateString:""                               //销售结束时间
                },
                page:{},                            //列表数据
                isShowOrderWin:false,              //是否显示订单选择窗口
                selectedOrderMap:new Map(),        //选择的订单
                selectedPageNumMap:new Map()       //全选的分页页码
            });
        //初始化搜索框的参数
        case types.COMMON_ORDER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //列表
        case types.COMMON_ORDER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //弹窗状态
        case types.COMMON_ORDER_LIST_STATU:
            return Object.assign({}, state, {
                isShowOrderWin: action.isShowOrderWin
            });
        //更新已选择的订单和全选的订单页码
        case types.COMMON_ORDER_UPDATE_SELECTED_ORDER:
            return Object.assign({}, state, {
                selectedOrderMap: action.selectedOrderMap,
                selectedPageNumMap: action.selectedPageNumMap
            });
        default:
            return state;
    }
}

