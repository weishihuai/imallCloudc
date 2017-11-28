import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    stopSaleNum:"",         //停售单号
    docMakerNm:"",          //制单人名称
    approveManName:"",      //复核人名称
    fromMakeTimeStr:"",     //制单时间
    toMakeTimeStr:"",
    toStopSaleDateStr:"",   //停售时间
    fromStopSaleDateStr:""
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    detailData:{},      //详情
    detailViewState:false,  //详情页面状态
    addFormState:false,     //添加页面状态
    selectedPage:{content: [], number: 0, totalElements: 0, size: 1000},//已选的商品批次
    isAllSelected:false,    //是否被全选的状态
    selectedIds:[],         //已经选中的ids
    selectedContents:[],    //已选中的objs
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.STOP_SALE_NOTICE_LIST:
            return Object.assign({},state,{
                page:action.data
            });
        case types.STOP_SALE_NOTICE_LIST_SET_PARAMS:
            return Object.assign({},state,{
                params:action.data
            });
        case types.STOP_SALE_NOTICE_DETAIL_DATA:
            return Object.assign({},state,{
                detailData:action.data
            });
        case types.STOP_SALE_NOTICE_DETAIL_VIEW_STATE:
            return Object.assign({},state,{
                detailViewState:action.data
            });
        case types.STOP_SALE_NOTICE_ADD_FORM_STATE:
            return Object.assign({},state,{
                addFormState:action.data,
                isAllSelected:false,
                selectedIds:[],
                selectedContents:[],
                selectedPage:{content: [], number: 0, totalElements: 0, size: 1000}
            });
        case types.STOP_SALE_NOTICE_ADD_GOODS_BATCH_PAGE:
            return Object.assign({},state,{
                selectedPage:action.data,
                isAllSelected:action.isAllSelected
            });
        case types.STOP_SALE_NOTICE_ADD_CHANGE_SELECTED_PAGE:
            return Object.assign({},state,{
                selectedContents:action.newSelectedContents,
                selectedIds:action.newSelectedIds,
                isAllSelected:action.isAllSelected
            });
        case types.STOP_SALE_NOTICE_DELETE_GOODS_BATCH_CONTENTS:
            return Object.assign({},state,{
                selectedPage:action.data,
                isAllSelected:false,
                selectedIds:[],
                selectedContents:[]
            });
        default:
            return state;
    }
}