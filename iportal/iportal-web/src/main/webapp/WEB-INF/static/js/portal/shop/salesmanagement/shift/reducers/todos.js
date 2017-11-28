import * as types from "../constants/ActionTypes";


const RECORD_PARAMS = {
    page: 0,
    size: 10,
    posManName: "",
    formCreateDateString: "",
    toCreateDateString: ""
};

const ORDER_PARAMS = {
    page: 0,
    size: 10,
    posMan: 0,
    formCreateDateString: "",
    toCreateDateString: ""
};

const initialState = {
    recordParams: RECORD_PARAMS, //搜索参数
    recordPage: {content: [], totalElements: 0, number: 0, size: 10},
    orderParams:ORDER_PARAMS,
    orderPage: {content: [], totalElements: 0, number: 0, size: 10},
    orderListModelStat: false,
    shiftRecord: {}
};

export default function todos(state = initialState, action) {
    switch (action.type) {
        case types.SHIFT_RECORD_RECORD_LIST:{
            return Object.assign({}, state, {
                recordPage: action.data,
                recordParams: Object.assign({}, state.recordParams, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        }
        case types.SHIFT_RECORD_ORDER_LIST:{
            return Object.assign({}, state, {
                orderPage: action.data,
                orderParams: Object.assign({}, state.orderParams, {
                    page: action.data.number,
                    size: action.data.size
                })
            });
        }
        //重置
        case types.SHIFT_RECORD_RECORD_SEARCH_RESET:
            return Object.assign({}, state, {
                recordParams: Object.assign({}, state.recordParams, {
                    page: 0,
                    size: 10,
                    posManName: "",
                    formCreateDateString: "",
                    toCreateDateString: ""
                })
            });
        case types.SHIFT_RECORD_SALES_TIME_CHANGE:
            return Object.assign({}, state, {
                recordParams: Object.assign({}, state.recordParams, {
                    formCreateDateString: action.data.formCreateDateString,
                    toCreateDateString: action.data.toCreateDateString,
                })
            });
        case types.SHIFT_RECORD_RECORD_SEARCH_SUBMIT:
            return Object.assign({}, state, {
                recordParams: Object.assign({}, state.recordParams, {
                    posManName: action.data
                })
            });
        case types.SHIFT_RECORD_ORDER_LIST_SHOW:
            return Object.assign({}, state, {
                orderListModelStat: action.isShow,
                orderParams: Object.assign({}, state.orderParams, {
                    posMan: action.data.posMan,
                    formCreateDateString: action.data.succeedTimeString,
                    toCreateDateString: action.data.shiftTimeString
                }),
                shiftRecord:action.data
            });
        default:
            return state
    }
}
