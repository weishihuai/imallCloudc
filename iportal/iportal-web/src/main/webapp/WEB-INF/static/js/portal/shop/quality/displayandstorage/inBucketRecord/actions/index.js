import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common';

/**
 * 列表
 * 列表在更新,删除操作后,可直接更新页面,所以要为参数字段设置默认值
 * @param params
 * @param page
 * @param size
 * @returns {Function} 更新列表数
 */

export function supplierList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('searchValue', params.searchValue || "")
            .set('batch', params.batch || "")
            .set('state', params.state || "")
            .set('loadingBucketManNm', params.loadingBucketManNm || "")
            .set('startTimeString', params.startTimeString || "")
            .set('endTimeString', params.endTimeString || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/chineseMedicinePiecesLoadingBucketRecord/queryList.json' + paramUrl, {
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
                    type: types.SUPPLIER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//设置 搜索参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_SEARCH_PARAMS,
            data: params
        })
    }
}
