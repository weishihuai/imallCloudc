import {OTHER_OUT_STOCK_LIST,
    OTHER_OUT_STOCK_ADD_MODEL,
    OTHER_OUT_STOCK_SEARCH_PARAMS,
    OTHER_OUT_STOCK_DETAIL_MODAL,
    OTHER_OUT_STOCK_DETAIL_DATA,
    OTHER_OUT_STOCK_APPROVE_VALIDATE,
    OTHER_OUT_STOCK_FIND_CURRENT_LOGIN_MESSAGE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    typeCode: "",  //出库类型代码
    searchFields: "",  //出库人
    outStockCode: "",  //出库人
    outStockTimeStartString: "",  //出库时间（开始）
    outStockTimeEndString: ""  //出库时间（结束）
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,   //新增窗体
    data: {},
    id: null,
    outStockCode: null,  //出库单号
    otherOutStockDetailState: false,
    otherOutStockDetailData:{},
    approveData: null,  //审核人信息
    loginUserMessage:{},
    goodsContent:{
        content: [],
        totalElements: 0,
        number: 0,
        size: 1000
    },   //勾选对象
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case OTHER_OUT_STOCK_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case OTHER_OUT_STOCK_ADD_MODEL: //显示 隐藏 添加 页面
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case OTHER_OUT_STOCK_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case OTHER_OUT_STOCK_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                otherOutStockDetailState: action.isDetailShow,
                outStockCode: action.outStockCode
            });
        case OTHER_OUT_STOCK_DETAIL_DATA:  //详情信息
            return Object.assign({}, state, {
                otherOutStockDetailData: action.data
            });
        case OTHER_OUT_STOCK_APPROVE_VALIDATE: //审核人验证
            return Object.assign({}, state, {
                approveData: action.approveData
            });
        case OTHER_OUT_STOCK_FIND_CURRENT_LOGIN_MESSAGE:
            return Object.assign({},state,{
                loginUserMessage:action.loginUserMessage
            });
        default:
            return state
    }
}
