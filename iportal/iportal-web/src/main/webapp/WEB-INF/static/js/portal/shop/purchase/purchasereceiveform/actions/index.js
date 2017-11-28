import * as types from '../constants/ActionTypes';
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from "../../../../../common/common";

export function showPurchaseReceive(purchaseOrderId) {
    return function (dispatch) {
        if (purchaseOrderId) {
            return fetch(iportal + '/purchaseOrder/findPurchaseReceiveInfo.json?id=' + purchaseOrderId, {
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
                dispatch({type: types.PURCHASE_RECEIVE_DETAIL_DATA, data: json});
                dispatch({type: types.PURCHASE_RECEIVE_DETAIL, purchaseOrderId});
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
            });
        } else {
            dispatch({type: types.PURCHASE_RECEIVE_DETAIL, purchaseOrderId});
        }
    }
}

export function savePurchaseReceive(data, callback = () => {}) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + "/purchaseReceiveRecord/saveReceiveRecord.json", {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (json) {
            dispatch(showPurchaseReceive(null));
            callback();
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}