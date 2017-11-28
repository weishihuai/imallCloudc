import {DRUG_LOCK_DEAL_LIST,
    DRUG_LOCK_DEAL_ADD_MODEL,
    DRUG_LOCK_DEAL_SEARCH_PARAMS,
    DRUG_LOCK_DEAL_APPROVE_VALIDATE,
    DRUG_LOCK_DEAL_GOODS_LIST_STATE,
    DRUG_LOCK_DEAL_GOODS_WIN_CLOSE,
    DRUG_LOCK_DEAL_GOODS_LIST,
    DRUG_LOCK_DEAL_CHANGE_SELECT_GOODS,
    DRUG_LOCK_DEAL_CHANGE_SELECT_ALL_OR_NOT_STATE
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    goodsNm: "",
    goodsCode: "",
    page: 0,
    size: 10,
    batch: "",  //批号
    isEphedrine:"",
    goodsBatch: "",  //批号
    keyword: "" //商品编码、拼音码、名称
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    goodsLockPage: {content: [], totalElements: 0, number: 0, size: 10},
    addState: false,   //新增窗体
    data: {},
    id: null,
    approveData: null,  //审核人信息
    commonAddGoodsBatchListState: false,                                //添加商品列表页面状态
    isSingle: false,                                                    //是否单选
    selectedIds: [],                                                    //已选ids
    selectedContents: [],                                               //已选的objs
    callback:()=>{},                                                    //回调方法
    selectAllOrNot:true,                                                //全选或者全不选
    isSelected:true,                                                    //是否是选中状态
    selectedAllPages:[]                                                 //所有已经全选的页面
 };

export default function todos(state = initialState, action) {
    switch (action.type) {
        case DRUG_LOCK_DEAL_APPROVE_VALIDATE: //审核人验证
            return Object.assign({}, state, {
                approveData: action.approveData
            });
        case DRUG_LOCK_DEAL_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case DRUG_LOCK_DEAL_ADD_MODEL: //显示 隐藏 添加 页面
            return Object.assign({}, state, {
                addState: action.isAddShow,
            });
        case DRUG_LOCK_DEAL_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        case DRUG_LOCK_DEAL_GOODS_LIST_STATE:                 //添加商品页面显示状态
            return Object.assign({}, state, {
                commonAddGoodsBatchListState: true,
                isSingle: action.isSingle,
                params:Object.assign({},state.params,{
                }),
                callback:action.callback
            });
        case DRUG_LOCK_DEAL_GOODS_LIST:        //列表状态
            return Object.assign({}, state, {
                goodsLockPage: action.data
            });
        case DRUG_LOCK_DEAL_CHANGE_SELECT_GOODS:       //选择或者取消
            return Object.assign({}, state, {
                selectedIds : action.selectedIds,
                selectedContents : action.selectedContents,
                isSelected:action.isSelected,
                selectAllOrNot:action.selectAllOrNot,
                selectedAllPages:action.selectedAllPages
            });
        case DRUG_LOCK_DEAL_GOODS_WIN_CLOSE:
            return Object.assign({},state,{
                params: {
                    goodsNm: "",
                    goodsCode: "",
                    batch: "",
                    page: 0,
                    size: 10
                },
                goodsLockPage: {content: [], totalElements: 0, number: 0, size: 10},
                commonAddGoodsBatchListState: false,
                isSingle: false,
                selectedIds: [],
                selectedContents: [],
                selectAllOrNot:true,
                isSelected:true,
                selectedAllPages:[]
            });
        case DRUG_LOCK_DEAL_CHANGE_SELECT_ALL_OR_NOT_STATE:
            return Object.assign({},state,{
                selectAllOrNot:action.data
            });
        default:
            return state
    }
}
