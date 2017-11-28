import * as types from '../constants/ActionTypes';
import {showErrorMsg} from "../../../../../common/common";

export function showDetail(id) {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_ORDER_DETAIL, id});
    }
}

export function purchaseOrderDetail(id) {
    return function (dispatch) {
        return fetch(iportal + '/purchaseOrder/detail.json?id=' + id, {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({type: types.PURCHASE_ORDER_DETAIL_DATA, data: json});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}