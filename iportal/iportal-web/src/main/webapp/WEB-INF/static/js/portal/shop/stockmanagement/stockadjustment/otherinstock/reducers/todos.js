import {OTHER_IN_STOCK_LIST,
    OTHER_IN_STOCK_ADD_MODEL,
    OTHER_IN_STOCK_SEARCH_PARAMS,
    OTHER_IN_STOCK_DETAIL_MODAL,
    OTHER_IN_STOCK_DETAIL_DATA,
    OTHER_IN_STOCK_APPROVE_VALIDATE,
    OTHER_IN_STOCK_FIND_CURRENT_LOGIN_MESSAGE,
    OTHER_IN_STOCK_ALL_STORAGE_SPACE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    typeCode: "",  //入库类型代码
    searchFields: "",  //入库人
    inStockCode: "",  //入库人
    inStockTimeStartString: "",  //入库时间（开始）
    inStockTimeEndString: ""  //入库时间（结束）
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,   //新增窗体
    data: {},
    id: null,
    inStockCode: null,  //入库单号
    otherInStockDetailState: false,  //详情窗体
    otherInStockDetailData:{},  //入库详情数据
    approveData: null,  //审核人信息
    loginUserMessage:{}, //当前登录的用户的信息
    allStorageSpace: [], //货位信息
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case OTHER_IN_STOCK_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case OTHER_IN_STOCK_ADD_MODEL: //显示 隐藏 添加 页面
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case OTHER_IN_STOCK_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case OTHER_IN_STOCK_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                otherInStockDetailState: action.isDetailShow,
                inStockCode: action.inStockCode
            });
        case OTHER_IN_STOCK_DETAIL_DATA:  //详情信息
            return Object.assign({}, state, {
                otherInStockDetailData: action.data
            });
        case OTHER_IN_STOCK_APPROVE_VALIDATE: //审核人验证
            return Object.assign({}, state, {
                approveData: action.approveData
            });
        case OTHER_IN_STOCK_FIND_CURRENT_LOGIN_MESSAGE:
            return Object.assign({},state,{
                loginUserMessage:action.loginUserMessage
            });
        case OTHER_IN_STOCK_ALL_STORAGE_SPACE:
            return Object.assign({},state,{
                allStorageSpace:action.allStorageSpace
            });
        default:
            return state
    }
}
