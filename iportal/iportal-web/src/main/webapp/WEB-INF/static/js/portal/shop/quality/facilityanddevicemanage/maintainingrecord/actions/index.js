import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {showErrorMsg} from '../../../../../../common/common';

/**
 * 设置搜索参数
 * @param params
 * @returns {Function}
 */
export function maintainingRecordSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.MAINTAINING_RECORD_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 列表
 */
export function maintainingRecordList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/maintainingRecord/query.json?deviceTypeCode=' + params.deviceTypeCode + '&deviceNum=' + params.deviceNum + '&deviceNm='
            + params.deviceNm + '&maintainResponseMan=' + params.maintainResponseMan + '&page=' + page + '&size=' + size , {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
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
                type: types.MAINTAINING_RECORD_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 搜索
 * @param params
 * @returns {Function}
 */
export function maintainingRecordSearch(params) {
    return function (dispatch) {
        dispatch(maintainingRecordSetSearchParams(params));
        dispatch(maintainingRecordList(params, 0, params.size));
    }
}

/**
 * 详情
 * @param id
 * @returns {Function}
 */
export function showMaintainingRecordDetail(id) {
    return function (dispatch) {
        return fetch(iportal + '/maintainingRecord/findById.json?id=' + id, {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
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
                type: types.MAINTAINING_RECORD_DETAIL_MODULE,
                isShowDetail: true,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 隐藏详情窗口
 * @returns {Function}
 */
export function hiddenDetailWin(){
    return function (dispatch) {
        dispatch({
            type: types.MAINTAINING_RECORD_DETAIL_MODULE,
            isShowDetail: false,
            data: null
        });
    }
}




