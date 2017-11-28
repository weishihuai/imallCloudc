import * as types from "../constants/ActionTypes";
import {niftyNoty} from "../../../../../common/common";

export function shiftRecordList(params, page, size) {
    return function (dispatch) {
        const empty = {
            type: types.SHIFT_RECORD_RECORD_LIST,
            data: {content: [], totalElements: 0, number: 1, size: 10}
        };
        dispatch(empty);

        return fetch(iportal + '/shiftRecord/findPage.json?' + 'posManName=' + (params.posManName || "") + '&formCreateDateString=' + (params.formCreateDateString || "") + '&toCreateDateString=' + (params.toCreateDateString || "") + "&page=" + page + "&size=" + size, {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
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
                    type: types.SHIFT_RECORD_RECORD_LIST,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}

export function orderListModel(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.SHIFT_RECORD_ORDER_LIST_SHOW,
            isShow: isShow,
            data:{posMan:0, succeedTimeString:"", shiftTimeString:""}
        });
    };
}

export function orderList(params, page, size) {
    return function (dispatch) {
        const empty = {
            type: types.SHIFT_RECORD_ORDER_LIST,
            data: {content: [], totalElements: 0, number: 1, size: 10}
        };
        dispatch(empty);

        return fetch(iportal + '/order/findShiftRangeOrder.json?' + 'openOrderManId=' + (params.posMan || "") + '&formCreateDateString=' + (params.formCreateDateString || "") + '&toCreateDateString=' + (params.toCreateDateString || "") + "&page=" + page + "&size=" + size, {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
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
                    type: types.SHIFT_RECORD_ORDER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}
