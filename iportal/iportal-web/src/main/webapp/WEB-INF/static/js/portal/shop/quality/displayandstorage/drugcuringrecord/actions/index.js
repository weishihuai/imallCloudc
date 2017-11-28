import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {showErrorMsg} from '../../../../../../common/common';

/**
 * 设置搜索参数
 * @param params
 * @returns {Function}
 */
export function drugCuringRecordSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_CURING_RECORD_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 列表
 * @param params
 * @param page
 * @param size
 * @returns {Function}
 */
export function drugCuringRecordList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugCuringRecord/query.json?keyword=' + params.keyword + '&curingPlanNum=' + params.curingPlanNum + '&startCuringDateString='
            + params.startCuringDateString + '&endCuringDateString=' + params.endCuringDateString + '&page=' + page + '&size=' + size , {
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
                type: types.DRUG_CURING_RECORD_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 搜索组件回调
 * @param params
 * @returns {Function}
 */
export function drugCuringRecordSearch(params) {
    return function (dispatch) {
        dispatch(drugCuringRecordSetSearchParams(params));
        dispatch(drugCuringRecordList(params, 0, params.size));
    }
}