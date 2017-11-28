import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {showErrorMsg} from '../../../../../../common/common';

/**
 * 设置搜索参数
 * @param params
 * @returns {Function}
 */
export function useRecordSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.USE_RECORD_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 列表
 */
export function useRecordList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/useRecord/query.json?deviceTypeCode=' + params.deviceTypeCode + '&deviceNum=' + params.deviceNum + '&deviceNm='
            + params.deviceNm + '&operationMan=' + params.operationMan + '&page=' + page + '&size=' + size , {
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
                type: types.USE_RECORD_LIST,
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
export function useRecordSearch(params) {
    return function (dispatch) {
        dispatch(useRecordSetSearchParams(params));
        dispatch(useRecordList(params, 0, params.size));
    }
}

/**
 * 详情
 * @param id
 * @returns {Function}
 */
export function showUseRecordDetail(id) {
    return function (dispatch) {
        return fetch(iportal + '/useRecord/findById.json?id=' + id, {
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
                type: types.USE_RECORD_DETAIL_MODULE,
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
            type: types.USE_RECORD_DETAIL_MODULE,
            isShowDetail: false,
            data: null
        });
    }
}




