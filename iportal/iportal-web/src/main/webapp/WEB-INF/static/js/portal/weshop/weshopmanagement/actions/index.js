import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../common/common";
import {VALID_FORM_INIT} from "../../../../common/validForm/constants/ActionTypes";

export function loadZone(parentId, type) {
    return function (dispatch) {
        return fetch(iportal + '/sysZone/findByParentId.json?subMaxLayer=0&id=' +  parentId, {
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
            dispatch(setZoneData(type, json));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function saveOrUpdate(data) {
    let url = data.id ? "/weshop/update.json" : "/weshop/save.json";
    return function(dispatch){
        return fetch(iportal + url, {
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
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch({type: VALID_FORM_INIT});
            showSuccess("保存成功");
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function getDetail(callback) {
    return function (dispatch) {
        return fetch(iportal + '/weshop/findOne.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            if(json.id){
                json.shopPromiseSendTime = json.shopPromiseSendTime ? json.shopPromiseSendTime : '';
                dispatch({type: types.WE_SHOP_DETAIL_DATA, data: json});
                callback(json);
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setZoneData(type, data) {
    return function (dispatch) {
        dispatch({type, data});
    }
}