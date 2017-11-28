/**
 * Created by ou on 2017/7/13.
 */

import * as types from '../constants/ActionTypes'
const initialState = {
    errorValidMessage: "",   //校验出错信息
    asyncValidMessage: "",    //异步校验信息
    validFormState:false,    //校验弹出窗开启状态
};

export default function validTodos(state=initialState,action) {
    switch(action.type){
        case types.VALID_ERROR_MESSAGE:
            return Object.assign({}, state, {
                errorValidMessage: action.errorValidMessage
            });
        case types.VALID_SET_SYNC_MESSAGE:
            return Object.assign({}, state, {
                asyncValidMessage: action.asyncValidMessage
            });
        case types.VALID_FORM_STATE:
            return Object.assign({}, state, {
                validFormState: action.validFormState
            });
        case types.VALID_FORM_INIT:
            return Object.assign({}, state, initialState);
        default:
                return state;
    }
}
