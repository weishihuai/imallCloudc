import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../common/common';

/**
 *
 * @param isOpen  打开商品弹窗 true
 * @param isSingle 是否单选 单选true 多选false
 * @param prescriptionDrugsTypeCode 处方药类型代码:有则填  没有则传 ""
 * @returns {Function}
 */
export function changeCommonSupplierDocListState(isOpen) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_ADD_SUPPLIER_LIST_STATE,
            isOpen: isOpen,
        })
    }
}
export function closeSupplierSelectWin() {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_SUPPLIER_WIN_CLOSE
        })
    }

}

export function loadCommonSupplierList(page, size, params) {

    return function (dispatch) {
        return fetch(iportal + '/supplierDoc/queryPage.json?supplierNm=' + params.supplierNm + '&certificatesNum=' + params.certificatesNum||" " +'&page=' + page + '&size=' + size, {
            mode: 'cors', credentials: 'include', method: 'get'
        })
            .then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    return response
                } else {
                    var error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            })
            .then(function (response) {
                return response.json();
            }).then(function (json) {
                dispatch({
                    type: types.COMMON_SUPPLIER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}





export function selectDate(selectedId,selectedContent) {
    return function (dispatch) {
        dispatch({
            type:types.COMMON_SUPPLIER_SELECT,
            selectedId:selectedId,
            selectedContent:selectedContent
        })
    }
}