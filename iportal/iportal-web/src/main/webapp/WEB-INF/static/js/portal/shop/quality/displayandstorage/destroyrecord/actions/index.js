import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common'

//药品销毁记录列表
export function destroyRecordList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/destroyRecord/query.json?' + '&keyword=' + (params.keyword || "") + '&batch=' + (params.batch || "")  + '&fromDestroyTimeString=' + (params.fromDestroyTimeString || "") + '&toDestroyTimeString=' + (params.toDestroyTimeString || "")  + "&page=" + page + "&size=" + size, {
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
                    type: types.DRUG_DESTROY_RECORD_LIST,
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
            type: types.DRUG_DESTROY_RECORD_SEARCH_PARAMS,
            data: params
        })
    }
}