import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../common/common";

export function changeType(type, params) {
    return function (dispatch) {
        dispatch({type: types.SALES_DETAIL_TYPE, data: type});
        dispatch(query(params, 0, 10, type));
    }
}

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.SALES_DETAIL_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function statOrder(params) {
    return function (dispatch) {
        return fetch(iportal + '/order/statOrder.json?orderNum='+ params.orderNum +"&orderSourceCode=" + params.orderSourceCode + "&orderStateCode=" + params.orderStateCode +  '&keyword=' + params.keyword , {
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
                type: types.SALES_DETAIL_STAT_ORDER,
                data: json
            });
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}

export function query(params, page, size, type) {
    let url = "SUMMARY" == type ? "/order/querySellSummary.json" : "/orderItem/querySellDetailPageVo.json";
    return function (dispatch) {
        dispatch(statOrder(params));
        return fetch(iportal + url + '?orderNum='+ params.orderNum +"&orderSourceCode=" + params.orderSourceCode + "&orderStateCode=" + params.orderStateCode +  '&keyword=' + params.keyword + '&page=' + page + '&size=' + size , {
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
                type: types.SALES_DETAIL_LIST,
                data: json
            });
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}

export function setParamsAndLoad(params, type) {
    return function (dispatch) {
        dispatch(setParams(params));
        dispatch(query(params, 0, 10, type));
    }
}
