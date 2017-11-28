import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

//药品销毁列表
export function drugDestroyList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugLock/queryDrugDestroyPage.json?' + '&keyword=' + (params.keyword || "") + '&batch=' + (params.batch || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.DRUG_DESTROY_LIST,
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
            type: types.DRUG_DESTROY_SEARCH_PARAMS,
            data: params
        })
    }
}

//销毁窗体
export function drugLockDestroyModel(isDestroyShow,id,data) {
    return function (dispatch) {
        dispatch({
            type:types.DRUG_DESTROY_MODEL,
            isDestroyShow:isDestroyShow,
            id: id,
            data:data
        });
    }
}

//药品销毁
export  function drugLockDestroyData(data,id,params){
    return function (dispatch) {
        var  url = iportal + "/drugLock/drugLockDestroy.json?id=" + id;
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            }
            else if (response.status == 302) {
            }
            else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({type: VALID_FORM_INIT});
            dispatch(drugLockDestroyModel(false));
            dispatch(drugDestroyList(params,params.page,params.size));
            dispatch(setApproveNames({}));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//获取当前登录用户信息
export function findCurrentLoginUserMessage() {
    return function (dispatch) {
        return fetch(iportal + '/sysUser/findCurrentLoginUserMessage.json', {
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
        })
            .then(function (response) {
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.RUG_DESTROY_FIND_CURRENT_LOGIN_MESSAGE,
                    loginUserMessage: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//设置审核对象
export function setApproveNames(approveUserNames) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_DESTROY_APPROVE_VALIDATE,
            approveUserNames: approveUserNames
        })
    }
}