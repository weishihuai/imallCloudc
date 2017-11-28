import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";

export function userList(callback = ()=> {}) {
    return function (dispatch) {
        return fetch(iportal + '/sysUser/findByLoginShop.json', {
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
                    type: types.SALES_RETURN_RECORD_LIST_USER_LIST,
                    data: json
                });
                dispatch(callback);
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function salesReturnRecordList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/sellReturnedPurchaseOrder/query.json?page=' + page + '&size=' + size
            + (params.orderNum ? "&orderNum=" + params.orderNum : "") + (params.cashierId ? "&cashierId=" + params.cashierId : "")
            + (params.fromDateString ? "&fromDateString=" + params.fromDateString : "")
            + (params.toDateString ? "&toDateString=" + params.toDateString : ""), {
            mode: 'cors', credentials: 'include', method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.SALES_RETURN_RECORD_LIST,
                data: json
            })
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function salesReturnRecordDetailModal(id, isShow) {
    return function (dispatch) {
        if(!isShow) {
            dispatch({
                type: types.SALES_RETURN_RECORD_DETAIL_MODAL,
                data: "",
                isShow: isShow
            });
            return;
        }
        return fetch(iportal + '/sellReturnedPurchaseOrder/findOne.json?id=' + id, {
            mode: 'cors', credentials: 'include', method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.SALES_RETURN_RECORD_DETAIL_MODAL,
                data: json,
                isShow: true
            })
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    };
}
