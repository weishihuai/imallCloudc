import * as types from "../constants/ActionTypes";

export function selectZone(showSelectZone) {
    return function (dispatch) {
        dispatch({type: types.RECEIVE_ADDR_SELECT_ZONE, showSelectZone});
    }
}

export function saveReceiveAddr(data) {
    return function(dispatch){
        return fetch(iportal + "/wechat/receiveAddr/save.json", {
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
            window.location.hash = "#/wechat-user-receive-addr-list";
        }).catch(function (ex) {

        });
    }
}

export function updateReceiveAddr(data) {
    return function(dispatch){
        return fetch(iportal + "/wechat/receiveAddr/update.json", {
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
            window.location.hash = "#/wechat-user-receive-addr-list";
        }).catch(function (ex) {

        });
    }
}

export function getReceiveAddrList() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/receiveAddr/list.json', {
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
            dispatch({type: types.RECEIVE_ADDR_LIST_DATA, data: json});
        }).catch(function (ex) {

        });
    }
}

export function getReceiveAddr(id) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/receiveAddr/findOne.json?id=' + id, {
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
            dispatch({type: types.RECEIVE_ADDR_DETAIL_DATA, data: json});
        }).catch(function (ex) {

        });
    }
}
