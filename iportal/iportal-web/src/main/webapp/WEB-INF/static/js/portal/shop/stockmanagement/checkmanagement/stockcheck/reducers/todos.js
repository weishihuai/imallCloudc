import {
    STOCK_CHECK_LIST,
    STOCK_CHECK_LIST_ADD_MODEL,
    STOCK_CHECK_SEARCH_PARAMS,
    STOCK_CHECK_DETAIL_DATA,
    STOCK_CHECK_DETAIL_MODAL,
    STOCK_CHECK_UPDATE_REAL_QUANTITY_DATA,
    STOCK_CHECK_UPDATE_REAL_QUANTITY_MODAL,
    STOCK_CHECK_FIND_CURRENT_LOGIN_MESSAGE,
    STOCK_CHECK_CANCEL_CONFIRM_MODEL
    } from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    checkOrderNum: "",  //盘点单号
    checkedStateCode: "", //盘点状态
    createDateBeginString: "", //创建时间(开始)
    createDateEndString: "" //创建时间(结束)
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,   //新增窗体
    data: {},
    stockCheckDetailState: false,
    stockCheckDetailData:{},
    checkOrderNum: null,  //盘点单号
    stockCheckUpdateState: false,
    stockCheckUpdateRealQuantityData:{},
    stockCheckRealCheckQuantityUpdateVoList:[],  //盘点时表单数据
    selectGoodsBatchList:[],
    stockCheckSaveVoList:[],  //保存时表单数据
    loginUserMessage:{},  //当前登录用户的信息
    confirmModelState:false,
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case STOCK_CHECK_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case STOCK_CHECK_LIST_ADD_MODEL:
            return Object.assign({}, state, {
                addState: action.isAddShow,
                data: action.data || {}
            });
        case STOCK_CHECK_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case STOCK_CHECK_DETAIL_MODAL:  //显示 隐藏 详情 页面
            return Object.assign({}, state, {
                stockCheckDetailState: action.isDetailShow,
                checkOrderNum: action.checkOrderNum
            });
        case STOCK_CHECK_DETAIL_DATA:
            return Object.assign({}, state, {
                stockCheckDetailData: action.data
            });
        case STOCK_CHECK_UPDATE_REAL_QUANTITY_MODAL:
            return Object.assign({}, state, {
                stockCheckUpdateState: action.isUpdateShow,
                checkOrderNum: action.checkOrderNum
            });
        case STOCK_CHECK_UPDATE_REAL_QUANTITY_DATA:
            return Object.assign({}, state, {
                stockCheckUpdateRealQuantityData: action.data
            });
        case STOCK_CHECK_FIND_CURRENT_LOGIN_MESSAGE:
            return Object.assign({}, state, {
                loginUserMessage: action.loginUserMessage
            });
        case STOCK_CHECK_CANCEL_CONFIRM_MODEL:
            return Object.assign({}, state, {
                confirmModelState:action.isConfirmModelShow,
                checkOrderNum: action.checkOrderNum
            });
        default:
            return state
    }
}
