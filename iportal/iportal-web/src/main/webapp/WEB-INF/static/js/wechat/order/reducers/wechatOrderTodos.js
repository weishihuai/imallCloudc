import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    page: 0,
    size: 10
};

const initialState = {
    params: INIT_PARAMS,        //搜索参数
    pageLength: 0,              //下一页数据量
    orderDetail: null,          //详情
    isShowCancel: false,        //是否显示取消窗口
    isShowConfirm: false,       //是否显示确认窗口
    page:{}                     //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type){
        //订单列表
        case types.WE_CHAT_ORDER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                pageLength: action.pageLength,
                params: Object.assign({}, state.params, {
                    page: action.data.page,
                    size: action.data.size
                })
            });
        //订单详情
        case types.WE_CHAT_ORDER_DETAIL:
            return Object.assign({}, state, {
                orderDetail: action.orderDetail
            });
        //订单取消
        case types.WE_CHAT_ORDER_CANCEL:
            return Object.assign({}, state, {
                isShowCancel: action.isShowCancel
            });
        //确认
        case types.WE_CHAT_CONFIRM:
            return Object.assign({}, state, {
                isShowConfirm: action.isShowConfirm
            });
        default:
            return state;
    }
}