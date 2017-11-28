import {
    DRUG_CHECK_ADD_MODULE,
    DRUG_CHECK_EDIT_MODULE,
    DRUG_CHECK_DETAIL_MODULE,
    DRUG_CHECK_LIST,
    DRUG_CHECK_SEARCH_PARAMS,
    DRUG_CHECK_GOODS_SELECT
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    checkDocumentNum:"",            //检查单号
    checkTypeCode:"",               //检查类型代码
    fromPlanCheckTimeString:"",     //开始时间
    toPlanCheckTimeString:""        //结束时间
};

const initialState = {
    params: INIT_PARAMS,        //搜索参数
    isShowAdd:false,            //是否显示新增页面
    isShowEdit:false,           //是否显示编辑页面
    isShowDetail:false,         //是否显示详情页面
    record:null,                //详情
    goods: [],                  //需要检查的商品
    goodsMap: new Map(),        //需要检查药品 Map
    checkGoodsIdMap: new Map(), //已经被选中的商品 Map
    isCheckAll: false,          //是否全部选中
    page:{}                     //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case DRUG_CHECK_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
       case DRUG_CHECK_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        //新增
        case DRUG_CHECK_ADD_MODULE:
            return Object.assign({}, state, {
                isShowAdd: action.isShowAdd,
                goods: action.goods,
                goodsMap: action.goodsMap
            });
        //编辑
        case DRUG_CHECK_EDIT_MODULE:
            return Object.assign({}, state, {
                isShowEdit: action.isShowEdit,
                data: action.data
            });
        //详情
        case DRUG_CHECK_DETAIL_MODULE:
            return Object.assign({}, state, {
                isShowDetail: action.isShowDetail,
                record: action.record
            });
        //商品选择
        case DRUG_CHECK_GOODS_SELECT:
            return Object.assign({}, state, {
                checkGoodsIdMap: action.checkGoodsIdMap,
                isCheckAll: action.isCheckAll
            });
        default:
            return state;
    }
}