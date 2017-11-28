import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from "../../../../../common/common";

export function showAdd() {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_ORDER_SHOW_ADD});
    }
}

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.PURCHASE_ORDER_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function query(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/purchaseOrder/query.json?purchaseOrderType='+ params.purchaseOrderType + '&purchaseOrderState='+ params.purchaseOrderState  + '&purchaseOrderNum='+ params.purchaseOrderNum +'&page=' + page + '&size=' + size , {
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
                type: types.PURCHASE_ORDER_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function cancelOrder(id, todos, callback) {
    return function(dispatch){
        return fetch(iportal + "/purchaseOrder/cancel.json?id=" + id, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch(query(todos.params, todos.page.number, todos.page.size));
            callback();
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}

export function savePurchaseOrder(data) {
    return function(dispatch){
        return fetch(iportal + "/purchaseOrder/save.json", {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch(showAdd());
            dispatch(setParamsAndLoad({purchaseOrderType: '', purchaseOrderState: '', purchaseOrderNum: ''}));
            dispatch({type: VALID_FORM_INIT});
            dispatch({type: types.PURCHASE_ORDER_ADD_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setParamsAndLoad(params) {
    return function (dispatch) {
        dispatch(setParams(params));
        dispatch(query(params, 0, 10));
    }
}

export function setShowConfirm(showConfirm, selectId,selectPurchaseOrderNum) {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_ORDER_SHOW_CONFIRM, showConfirm, selectId,selectPurchaseOrderNum});
    }
}
