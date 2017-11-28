import * as types from '../constants/ActionTypes';

const INIT_PARAMS = {
    docType:"GSP_CHECK_TABLE"       //文档类型
};

const initialState = {
    params: INIT_PARAMS, //搜索参数
    record:[]  //数据
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        //设置列表信息
        case types.GSP_CHECK_LIST:
            return Object.assign({}, state, {
                record: action.record
            });
        default:
            return state;
    }
}