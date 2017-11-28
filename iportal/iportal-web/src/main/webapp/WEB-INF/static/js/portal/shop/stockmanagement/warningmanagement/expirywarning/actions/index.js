import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common';

/*近效期催销列表*/
export function expiryWarningList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal+'/goodsBatch/queryNearExpireDatePage.json?'+'&searchFields='+(params.searchFields || "")+'&batch='+(params.batch || "")+"&page=" + page + "&size=" + size, {
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
                    type: types.EXPIRY_WARNING_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*设置参数*/
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.EXPIRY_WARNING_SEARCH_PARAMS,
            data: params
        })
    }
}
