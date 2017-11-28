import {
    GOODS_SPLIT_ZERO_ADD_MODULE,
    GOODS_SPLIT_ZERO_DETAIL_MODULE,
    GOODS_SPLIT_ZERO_LIST,
    GOODS_SPLIT_ZERO_SEARCH_PARAMS,
    GOODS_SPLIT_ZERO_GOODS_SELECT
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    keyword:"",           //搜索名称
    startTimeString:"",   //开始时间
    endTimeString:""      //结束时间
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    isShowAdd:false,    //是否显示新增页面
    isShowDetail:false, //是否显示详情页面
    id:null,
    record:null,        //详情
    goods: null,        //商品信息
    page:{}             //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case GOODS_SPLIT_ZERO_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
       case GOODS_SPLIT_ZERO_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //新增
        case GOODS_SPLIT_ZERO_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd,
                goods: action.goods
            });
        //商品选择
        case GOODS_SPLIT_ZERO_GOODS_SELECT:
            return Object.assign({}, state, {
                goods: action.goods
            });
        //详情
        case GOODS_SPLIT_ZERO_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                record: action.record
            });
        default:
            return state;
    }
}