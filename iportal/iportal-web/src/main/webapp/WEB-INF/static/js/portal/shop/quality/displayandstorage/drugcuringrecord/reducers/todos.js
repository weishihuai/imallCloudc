import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    page: 0,
    size: 10,
    keyword:"",
    curingPlanNum:"",          //养护计划单号
    startCuringDateString:"",  //养护开始日期
    endCuringDateString:""     //养护结束日期
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page:{}  //列表数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //初始化搜索框的参数
        case types.DRUG_CURING_RECORD_SEARCH_PARAMS:
            return Object.assign({}, state, {
                params: action.data || INIT_PARAMS
            });
        //设置列表信息
        case types.DRUG_CURING_RECORD_LIST:
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        default:
            return state;
    }
}