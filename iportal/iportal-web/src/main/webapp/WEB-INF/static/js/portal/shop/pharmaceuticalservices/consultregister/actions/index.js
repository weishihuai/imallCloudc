import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {formatData} from "../../../../../common/redux-form-ext";

export function setSearchParam(params) {
    return function (dispatch) {
        dispatch({
            type: types.CONSULT_LIST_SET_PARAMS,
            params: params
        })
    }
}

export function queryList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/consultService/query.json?searchFields=' + (params.searchText || "") + '&fromDateString=' + (params.fromDateString || "") + '&toDateString=' + (params.toDateString || "") + '&page=' + page + '&size=' + size, {
            mode: 'cors', credentials: 'include', method: 'get'
        })
            .then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    return response
                } else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            })
            .then(function (response) {
                return response.json();
            }).then(function (json) {
                dispatch({
                    type: types.CONSULT_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
            });
    }
}

export function consultAddForm(isShow) {
    return function (dispatch) {
        dispatch({
            type:types.CONSULT_ADD_FORM_MODAL,
            isShow:isShow
        });
    }
}

export function consultDetail(data, isShow) {
    if(!isShow || !data){
        return function (dispatch) {
            dispatch({
                type: types.CONSULT_DETAIL_MODAL,
                isShow: isShow
            });
        }
    }
    return function(dispatch){
        let url = iportal + "/consultService/findById.json?id=" + data;
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'get',
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({
                type: types.CONSULT_DETAIL_MODAL,
                data: json,
                isShow: isShow
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    };
}

export function goodsDetail(isShow, title, data) {
    return function (dispatch) {
        dispatch({
            type:types.CONSULT_GOODS_DETAIL_MODAL,
            isShow:isShow,
            title: title,
            data:data
        });
    }
}

export  function saveConsultService(data){
    return function(dispatch){
        let url = iportal + "/consultService/save.json";
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
            dispatch(queryList(0, 10, ""));
            dispatch({type:types.CONSULT_ADD_FORM_MODAL, isShow:false});
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch({type: VALID_FORM_INIT});
        });
    }
}

export function detail(id) {
    return function (dispatch) {

        return fetch(iportal + '/consultService/findById.json?id=' + id, {
            mode: 'cors', credentials: 'include', method: 'get'
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
                return dispatch({
                    type: types.CONSULT_DETAIL_MODAL,
                    isShow: true,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

