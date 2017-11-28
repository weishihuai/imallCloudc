import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

/*药品锁定列表*/
export function drugLockList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugLock/list.json?' + '&keyword=' + (params.keyword || "") + '&batch=' + (params.batch || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.DRUG_LOCK_LIST,
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
            type: types.DRUG_LOCK_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function drugLockAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_LOCK_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

//保存药品锁定
export function saveDrugLock(data,params) {
    return function(dispatch){
        return fetch(iportal + '/drugLock/saveDrugLock.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(drugLockList(params,0,10));
            dispatch(drugLockAddModel(false));
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
