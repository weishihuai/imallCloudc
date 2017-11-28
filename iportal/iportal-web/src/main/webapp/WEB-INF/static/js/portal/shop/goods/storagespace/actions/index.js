import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../common/common'
import {formatData} from '../../../../../common/redux-form-ext'

/*商品货位列表*/
export function storageSpaceList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/storageSpace/list.json?' + '&storageSpaceNm=' + (params.storageSpaceNm || "") + '&storageSpaceType=' + (params.storageSpaceType||"") + '&enableStateCode=' + (params.enableStateCode||"") + "&page=" + page + "&size=" + size, {
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
                    type: types.STORAGE_SPACE_LIST,
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
            type: types.STORAGE_SPACE_SEARCH_PARAMS,
            data: params
        })
    }
}

/*编辑窗体*/
export function storageSpaceEditModel(isShow, data) {
    return function (dispatch) {
        if (!isShow) {
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type: types.STORAGE_SPACE_UPDATE_MODEL,
            isShow: isShow,
            data: data
        });
    }
}

/*新增窗体*/
export function storageSpaceAddModel(isAddShow) {
    return function (dispatch) {
        if (!isAddShow) {
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type: types.STORAGE_SPACE_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

//更新商品货位
export  function storageSpaceUpdateData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if(data.id != undefined){
            var  url = iportal + "/storageSpace/update.json?id=" + data.id;
        }
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
            dispatch(storageSpaceEditModel(false));
            dispatch(storageSpaceList(params, params.page, params.size))
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//新增商品货位
export  function storageSpaceAddData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        var  url = iportal + "/storageSpace/save.json";
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
            dispatch(storageSpaceAddModel(false));
            dispatch(storageSpaceList(params, params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
