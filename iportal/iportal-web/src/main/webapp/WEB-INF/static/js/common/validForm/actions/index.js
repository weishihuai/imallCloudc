/**
 * Created by ou on 2017/7/13.
 */
import * as types from '../constants/ActionTypes'


/**
 * 校验窗口状态
 * @param state
 * @returns {Function}
 */
export function checkValidForm(state) {
    return function (dispatch) {
        dispatch({
                type: types.VALID_FORM_STATE,
                validFormState: state
            }
        )
    }
}
/**
 * 校验窗口信息
 * @param errorValidMessage
 * @returns {Function}
 */
export function errorValidMessageFunction(errorValidMessage) {
    return function (dispatch) {
        dispatch({
            type: types.VALID_ERROR_MESSAGE,
            errorValidMessage:errorValidMessage

        });
    }
}
/**
 * 异步校验窗口信息
 * @param errorValidMessage
 * @returns {Function}
 */
export function asyncErrorValidMessageFunction(asyncValidMessage) {
    return function (dispatch) {
        dispatch({
            type: types.VALID_SET_SYNC_MESSAGE,
            asyncValidMessage:asyncValidMessage

        });
    }
}
/**
 * 初始化校驗
 * @returns {Function}
 */
export function initValidForm () {
    return function (dispatch) {
        dispatch({
            type:types.VALID_FORM_INIT
        });
    }
}