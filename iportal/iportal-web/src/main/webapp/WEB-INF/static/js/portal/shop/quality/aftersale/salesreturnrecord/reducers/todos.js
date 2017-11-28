import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    orderNum: "",       //退货订单编号
    cashierId:"",        //收银员ID
    fromDateString: "",
    toDateString: "",
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},   //详情
    detailModal: false,       //详情窗口显示状态
    sysUserList: [],        //员工列表
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //设置搜索参数
        case types.SALES_RETURN_RECORD_SEARCH_PARAM_CHANGE:
            return Object.assign({},state,{
                params: action.data
            });
        //列表查找结果
        case types.SALES_RETURN_RECORD_LIST:
            return Object.assign({},state,{
                page: action.data
            });
        //详情展示
        case types.SALES_RETURN_RECORD_DETAIL_MODAL:
            return Object.assign({},state,{
                detailModal: action.isShow,
                data: action.data
            });
        //详情展示
        case types.SALES_RETURN_RECORD_LIST_USER_LIST:
            return Object.assign({},state,{
                sysUserList: action.data
            });
        default:
            return state
    }
}
