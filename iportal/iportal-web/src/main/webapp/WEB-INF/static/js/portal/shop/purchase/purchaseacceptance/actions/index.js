import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from "../../../../../common/common";

export function showReceiveDetail(receiveRecordId) {
    return function (dispatch) {
        if (receiveRecordId) {
            return fetch(iportal + '/purchaseReceiveRecord/detail.json?id=' + receiveRecordId, {
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
                dispatch({type: types.PURCHASE_RECEIVE_DETAIL, data: json});
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
            });
        } else {
            dispatch({type: types.PURCHASE_RECEIVE_DETAIL});
        }
    }
}

export function showPurchaseAcceptance(receiveRecordId) {
    return function (dispatch) {
        if (receiveRecordId) {
            return fetch(iportal + '/purchaseReceiveRecord/findEnableAcceptanceReceiveRecord.json?id=' + receiveRecordId, {
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
                dispatch({type: types.PURCHASE_ACCEPTANCE_INIT_DATA, data: json});
                dispatch({type: types.PURCHASE_ACCEPTANCE_SHOW_ACCEPTANCE});
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
            });
        } else {
            dispatch({type: types.PURCHASE_ACCEPTANCE_SHOW_ACCEPTANCE});
        }
    }
}

export function savePurchaseAcceptance(data) {
    return function(dispatch){
        return fetch(iportal + "/purchaseAcceptanceRecord/saveAcceptanceRecord.json", {
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
            dispatch(showPurchaseAcceptance());
            dispatch(setParamsAndLoad({receiveOrderNum: ''}));
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.PURCHASE_ACCEPTANCE_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function query(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/purchaseReceiveRecord/query.json?receiveOrderNum='+ params.receiveOrderNum +'&page=' + page + '&size=' + size , {
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
                type: types.PURCHASE_ACCEPTANCE_LIST,
                data: json
            });
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
