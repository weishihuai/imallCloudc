import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common';
import {formatData} from '../../../../../../common/redux-form-ext';


export function temperatureMoistureMonitorRecordSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.TEMPERATURE_MOISTURE_MONITOR_RECORD_SEARCH_PARAMS,
            data: params
        })
    }
}

export function temperatureMoistureMonitorRecordList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/temperatureMoistureMonitorRecord/query.json?storageSpaceId='+ params.storageSpaceId + '&fromMonitorDateString='+ params.fromMonitorDateString  + '&toMonitorDateString='+ params.toMonitorDateString +'&page=' + page + '&size=' + size , {
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
                type: types.TEMPERATURE_MOISTURE_MONITOR_RECORD_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//*搜索组件回调固定方法*/
export function temperatureMoistureMonitorRecordSearch(params) {
    return function (dispatch) {
        dispatch(temperatureMoistureMonitorRecordSetSearchParams(params));
        dispatch(temperatureMoistureMonitorRecordList(params, 0, params.size));
    }
}

/**
 * 新增窗口显示与隐藏
 * @param params
 * @returns {Function}
 */
export function showAddForm(params){
    return function (dispatch) {
        dispatch({
            type: types.TEMPERATURE_MOISTURE_MONITOR_RECORD_ADD_MODULE,
            isShowAdd: params
        })
    }
}

/**
 * 新增
 * @param data
 * @param actions
 */
export function saveRecord(data, actions, params){
    let url = iportal + "/temperatureMoistureMonitorRecord/save.json";
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(data))
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(showAddForm(false));

            let params = {
                storageSpaceId:"",
                fromMonitorDateString:"",
                toMonitorDateString:""
            };

            actions.temperatureMoistureMonitorRecordList(params, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 加载所有的已启用的货位
 */
export function listAllEnableStorageSpace(callbackFun=()=>{}) {
    return function (dispatch) {
        return fetch(iportal + '/storageSpace/listAllStorageSpace.json', {
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
                type: types.STORAGE_SPACE,
                storageSpace: json
            });

            dispatch(callbackFun);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}