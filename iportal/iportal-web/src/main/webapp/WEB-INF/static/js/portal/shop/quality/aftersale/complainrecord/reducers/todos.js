import * as types from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    searchFields: "",
    customerName: "",
    fromDateString: "",
    toDateString: ""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},   //详情
    addFormModal: false,       //添加窗口显示状态
    detailModal: false,       //详情窗口显示状态

};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //设置搜索参数
        case types.COMPLAIN_RECORD_SEARCH_PARAM_CHANGE:
            return Object.assign({},state,{
                params: action.data
            });
        //列表查找结果
        case types.COMPLAIN_RECORD_LIST:
            return Object.assign({},state,{
                page: action.data
            });
        //新增窗口展示
        case types.COMPLAIN_RECORD_ADD_FORM_MODAL:
            return Object.assign({},state,{
                addFormModal: action.isShow
            });
        //详情展示
        case types.COMPLAIN_RECORD_DETAIL_MODAL:
            return Object.assign({},state,{
                detailModal: action.isShow,
                data: action.data
            });
        default:
            return state
    }
}
