import {
    DRUG_COMBINATION_CATEGORY_ADD_MODULE,
    DRUG_COMBINATION_CATEGORY_EDIT_MODULE,
    DRUG_COMBINATION_CATEGORY_LIST,
    DRUG_COMBINATION_CATEGORY_SEARCH_PARAMS,
    DRUG_COMBINATION_CATEGORY_DEL_MODULE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    categoryNm:""  //货位
};

const initialState = {
    params: INIT_PARAMS,        //搜索参数
    isShowAdd:false,        //是否显示新增页面
    isShowEdit:false,       //是否显示编辑页面
    isShowConfirm:false,    //是否显示确认框
    id:null,
    page:{}  //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case DRUG_COMBINATION_CATEGORY_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
       case DRUG_COMBINATION_CATEGORY_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //新增
        case DRUG_COMBINATION_CATEGORY_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd
            });
        //编辑
        case DRUG_COMBINATION_CATEGORY_EDIT_MODULE:
            return Object.assign({}, state, {
                isShowEdit: action.isShowEdit,
                data: action.data
            });
        //删除
        case DRUG_COMBINATION_CATEGORY_DEL_MODULE:
            return Object.assign({}, state, {
                isShowConfirm: action.isShowConfirm,
                id: action.id
            });
        default:
            return state;
    }
}