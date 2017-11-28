import {
    LIMIT_BUY_REGISTER_ADD_MODULE,
    LIMIT_BUY_REGISTER_DETAIL_MODULE,
    LIMIT_BUY_REGISTER_EDIT_MODULE,
    LIMIT_BUY_REGISTER_LIST,
    LIMIT_BUY_REGISTER_SEARCH_PARAMS
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    sellOrderCode:"",             //订单编号
    patientNm:"",                 //患者名称
    registerStartDateString:"",   //开始时间
    registerEndDateString:""      //结束时间
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    isShowAdd:false,    //是否显示新增页面
    isShowDetail:false, //是否显示详情页面
    isShowEdit:false,   //是否显示编辑页面
    id:null,
    record:null,        //详情
    items:null,         //订单项
    page:{}             //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case LIMIT_BUY_REGISTER_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
       case LIMIT_BUY_REGISTER_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //新增
        case LIMIT_BUY_REGISTER_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd,
                items: action.items
            });
        //详情
        case LIMIT_BUY_REGISTER_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                record: action.record
            });
        //编辑
        case LIMIT_BUY_REGISTER_EDIT_MODULE:
            return Object.assign({}, state, {
                isShowEdit: action.isShowEdit,
                data: action.data
            });
        default:
            return state;
    }
}