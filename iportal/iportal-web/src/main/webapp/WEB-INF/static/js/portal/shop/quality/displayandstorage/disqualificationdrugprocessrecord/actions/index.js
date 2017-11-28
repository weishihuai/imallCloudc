import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common'

//不合格药品 处理记录列表
export function disqualificationDrugProcessRecordList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/disqualificationDrugProcessRecord/list.json?' + '&keyword=' + (params.keyword || "") + '&recordDateStartTimeString=' + (params.recordDateStartTimeString || "") + '&recordDateEndTimeString=' + (params.recordDateEndTimeString || "")  + "&page=" + page + "&size=" + size, {
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
                    type: types.DISQUALIFICATION_DRUG_PROCESSING_RECORD_LIST,
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
            type: types.DISQUALIFICATION_DRUG_PROCESSING_SEARCH_PARAMS,
            data: params
        })
    }
}