import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    approveStateCode: "",           //审核状态代码
    keyWords:"",                    //拼音/商品名称/通用名称/商品编码
    fromDateString:"",              //审核时间
    toDateString:"",
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], number: 0, totalElements: 0, size: 10},
    approveFormState:false,         //审核页面状态
    approveInf:{},                  //审核的信息
    detailData:{},                  //详细
    detailViewState:false,          //详细页面状态
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_LIST:
            return Object.assign({},state,{
                page:action.data
            });
        case types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_SET_PARAMS:
            return Object.assign({},state,{
                params:action.data
            });
        case types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_FORM_STATE:
            return Object.assign({},state,{
                approveFormState:action.data
            });
        case types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_INF:
            return Object.assign({},state,{
                approveInf:action.data
            });
        case types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_DETAIL:
            return Object.assign({},state,{
                detailViewState:action.data
            });
        case types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_DETAIL_DATA:
            return Object.assign({},state,{
                detailData:action.data
            });
        default:
            return state;
    }
}