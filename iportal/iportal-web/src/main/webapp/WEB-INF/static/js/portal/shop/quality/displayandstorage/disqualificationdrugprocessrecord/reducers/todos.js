import {DISQUALIFICATION_DRUG_PROCESSING_RECORD_LIST,
    DISQUALIFICATION_DRUG_PROCESSING_SEARCH_PARAMS,
} from "../constants/ActionTypes";

const INIT_PARAMS = {
    page: 0,
    size: 10,
    keyword: "",  //商品编码、拼音码、名称
    recordDateStartTimeString:"", //销毁时间 (开始)
    recordDateEndTimeString:""  //销毁时间 (结束)
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    page: {content: [], totalElements: 0, number: 0, size: 10},
    data: {},
 };

export default function todos(state = initialState, action) {
    switch (action.type) {
        case DISQUALIFICATION_DRUG_PROCESSING_SEARCH_PARAMS: //设置参数
            return Object.assign({}, state, {
                params: action.data || initialState.params
            });
        case DISQUALIFICATION_DRUG_PROCESSING_RECORD_LIST:  //列表
            return Object.assign({}, state, {
                page: action.data,
                params: Object.assign({}, state.params, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        default:
            return state
    }
}
