import * as types from "../constants/ActionTypes";

const initialState = {
    params: {
        goodsNm: "",
        goodsCode: "",
        produceManufacturer: "",
        isEphedrine:"",
        page: 0,
        size: 10
    },                                                                  //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},         //商品列表
    commonAddGoodsListState: false,                                     //添加商品列表页面状态
    isSingle: false,                                                    //是否单选
    selectedIds: [],                                                    //已选ids
    selectedContents: [],                                               //已选的objs
    callback:()=>{},                                                    //回调方法
    selectAllOrNot:true,                                                //全选或者全不选
    isSelected:true,                                                    //是否是选中状态
    selectedAllPages:[]                                                 //所有已经全选的页面
};

export default function goodsTodos(state = initialState, action) {
    switch (action.type) {
        case types.COMMON_ADD_GOODS_LIST_STATE:                 //添加商品页面显示状态
            return Object.assign({}, state, {
                commonAddGoodsListState: true,
                isSingle: action.isSingle,
                params:Object.assign({},state.params,{
                    isEphedrine:action.isEphedrine
                }),
                callback:action.callback
            });
        case types.COMMON_GOODS_LIST:                           //列表状态
            return Object.assign({}, state, {
                page: action.data
            });

        case types.COMMON_GOODS_LIST_SET_PARAMS:                //设置查询参数
            return Object.assign({},state,{
                params:action.params
            });

        case types.COMMON_GOODS_LIST_CHANGE_SELECT_GOODS:       //选择或者取消
            return Object.assign({}, state, {
                selectedIds : action.selectedIds,
                selectedContents : action.selectedContents,
                isSelected:action.isSelected,
                selectAllOrNot:action.selectAllOrNot,
                selectedAllPages:action.selectedAllPages
            });
        case types.COMMON_GOODS_WIN_CLOSE:
            return Object.assign({},state,{
                params: {
                    goodsNm: "",
                    goodsCode: "",
                    produceManufacturer: "",
                    isEphedrine:"",
                    page: 0,
                    size: 10
                },
                page: {content: [], totalElements: 0, number: 0, size: 10},
                commonAddGoodsListState: false,
                isSingle: false,
                selectedIds: [],
                selectedContents: [],
                selectAllOrNot:true,
                isSelected:true,
                selectedAllPages:[]
            });
        case types.COMMON_GOODS_LIST_CHANGE_SELECT_ALL_OR_NOT_STATE:
            return Object.assign({},state,{
                selectAllOrNot:action.data
            });
        default:
            return state;
    }
}