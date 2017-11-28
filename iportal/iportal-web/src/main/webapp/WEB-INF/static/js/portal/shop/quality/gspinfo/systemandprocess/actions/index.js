import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {showErrorMsg} from '../../../../../../common/common';

/**
 * 列表
 * @param params
 * @returns {Function}
 */
export function systemAndProcessList(params) {
    return function (dispatch) {
        return fetch(iportal + '/docInf/searchByDocType.json?docType=' + params.docType, {
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
                type: types.SYSTEM_AND_PROCESS_LIST,
                record: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}