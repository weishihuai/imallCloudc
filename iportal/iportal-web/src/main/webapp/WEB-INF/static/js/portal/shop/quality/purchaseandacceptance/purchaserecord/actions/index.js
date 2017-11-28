import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";

export function changeType(type, params) {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_RECORD_TYPE, data: type});
        dispatch(query(params, 0, 10, type));
    }
}

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.PURCHASE_RECORD_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function query(params, page, size, type) {
    let url = "SUMMARY" == type ? "/purchaseOrder/query.json" : "/purchaseOrderItem/query.json";
    return function (dispatch) {
        return fetch(iportal + url + '?purchaseOrderType='+ params.purchaseOrderType + '&purchaseOrderState='+ params.purchaseOrderState  + '&purchaseOrderNum='+ params.purchaseOrderNum +'&page=' + page + '&size=' + size , {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.PURCHASE_RECORD_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setParamsAndLoad(params, type) {
    return function (dispatch) {
        dispatch(setParams(params));
        dispatch(query(params, 0, 10, type));
    }
}
