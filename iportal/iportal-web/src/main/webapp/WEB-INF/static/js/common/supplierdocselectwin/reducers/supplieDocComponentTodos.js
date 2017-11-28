/**
 * Created by lzx on 2017/4/27.
 */
import * as types from "../constants/ActionTypes";
const INITPARAM = {
    supplierNm: "",
    certificatesNum: "",
    page: 0,
    size: 10
}
const initialState = {
    params: INITPARAM,                                                                //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 100},        //商品列表
    commonAddSupplierListState: false,                                     //添加商品列表页面状态
    selectedId: "",                                                    //已选ids
    selectedContent: {},                                              //已选的objs
};

export default function supplieDocComponentTodos(state = initialState, action) {
    switch (action.type) {
        case types.COMMON_ADD_SUPPLIER_LIST_STATE:                 //添加供应商状态
            return Object.assign({}, state, {
                commonAddSupplierListState: action.isOpen,
            });
        case types.COMMON_SUPPLIER_LIST:                           //列表数据
            return Object.assign({}, state, {
                page: action.data
            });

        case types.COMMON_SUPPLIER_LIST_SET_PARAMS:                //设置查询参数
            return Object.assign({}, state, {
                params: action.params
            });

        case types.COMMON_SUPPLIER_SELECT:                      //选择或者取消
            return Object.assign({}, state, {
                selectedId: action.selectedId,
                selectedContent: action.selectedContent
            });
        case types.COMMON_SUPPLIER_WIN_CLOSE:
            return Object.assign({}, state, {
                selectedId: [],
                selectedContent: [],
                page: {content: [], totalElements: 0, number: 0, size: 100},
                commonAddSupplierListState: false,
            });
        default:
            return state;
    }
}