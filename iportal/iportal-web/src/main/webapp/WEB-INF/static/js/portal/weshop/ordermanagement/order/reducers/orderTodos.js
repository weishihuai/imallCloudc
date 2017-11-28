import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    page: 0,
    size: 10,
    keyword:"",                     //商品编码/通用名称/通用名称首字母/商品名称
    receiverName:"",                //收货人姓名
    contactTel:"",                  //联系电话
    orderNum:"" ,                   //订单编号
    orderStateCode:"",              //订单状态代码
    startCreateDateString:"",       //下单开始时间
    endCreateDateString:"",         //下单结束时间
    bookDeliveryTimeStartString:"", //预约送达时间开始
    bookDeliveryTimeEndString:"" ,  //预约送达时间结束
    paymentWayCode:""               //支付 方式 代码
};

const initialState = {
    params: INIT_PARAMS,         //搜索参数
    orderStatistics: null,      //订单统计数据
    isShowDetail: false,        //是否显示详情
    isShowClose: false,         //是否关闭订单窗口
    isShowConfirm: false,       //是否显示提示框
    isShowSend: false,          //是否显示发货窗口
    confirmType: null,
    isShowRemark: false,        //是否显示备注窗口
    data: null,                 //详情
    sendData: null,             //发货信息
    order: null,                //订单
    id: null,                   //订单 ID
    page:{}                     //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case types.ORDER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
        case types.ORDER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size,
                    orderStateCode: action.orderStateCode
                })
            });
        //订单统计
        case types.ORDER_STATISTICS_MODULE:
            return Object.assign({}, state, {
                orderStatistics: action.orderStatistics
            });
        //订单详情
        case types.ORDER_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                data: action.data
            });
        //订单关闭
        case types.ORDER_CLOSE_MODULE:
            return Object.assign({}, state, {
                isShowClose: action.isShowClose,
                data: action.data
            });
        //确认框
        case types.ORDER_CONFIRM_MODULE:
            return Object.assign({}, state, {
                isShowConfirm: action.isShowConfirm,
                confirmType: action.confirmType,
                id: action.id
            });
        //备注
        case types.ORDER_REMARK_MODULE:
            return Object.assign({}, state, {
                isShowRemark: action.isShowRemark,
                order: action.order
            });
        //订单发货
        case types.ORDER_SEND_MODULE:
            return Object.assign({}, state, {
                isShowSend: action.isShowSend,
                sendData: action.sendData
            });
        default:
            return state;
    }
}