import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    returnedPurchaseOrderNum: "",  //购进退出单号
    isPayed: "", //是否结款
    returnedPurchaseType: "",//退货类型
};

const GOODS_SELECT_PARAMS = {
    supplierId: null,//供应商ID
    produceManufacturer:"",//生产厂商
    goodsCode: "",//商品编码
    goodsNm: ""//商品名称
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    showAdd: false,
    showDetail: false,
    detailData: {itemList: []},
    returnGoodsData: {content: [], number: 0, totalElements: 0, size: 10},//退货商品列表
    showGoodsSelect: false,
    goodsSelectParams: GOODS_SELECT_PARAMS,
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.PURCHASE_RETURN_LIST:
            return Object.assign({}, state, {page: action.data});
        case types.PURCHASE_RETURN_LIST_SET_PARAMS:
           return Object.assign({}, state, {params: action.data});
        case types.PURCHASE_RETURN_SHOW_ADD:
            return Object.assign({}, state, {showAdd: !state.showAdd});
        case types.PURCHASE_RETURN_SHOW_DETAIL:
            return Object.assign({}, state, {showDetail: !state.showDetail, detailData: action.data});
        case types.PURCHASE_RETURN_SHOW_GOODS_SELECT:
            return Object.assign({}, state, {showGoodsSelect: !state.showGoodsSelect, goodsSelectParams: Object.assign({}, state.goodsSelectParams, {supplierId: action.supplierId})});
        case types.PURCHASE_RETURN_GOODS_DATA:
            return Object.assign({}, state, {returnGoodsData: action.data});
        case types.PURCHASE_RETURN_GOODS_SELECT_SET_PARAMS:
            return Object.assign({}, state, {goodsSelectParams: Object.assign({}, state.goodsSelectParams, action.data)});
        default:
            return state;
    }
}