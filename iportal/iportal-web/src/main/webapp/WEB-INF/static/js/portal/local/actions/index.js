import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../common/common.js";
import {VALID_FORM_INIT} from "../../../common/validForm/constants/ActionTypes";

function receivePosts(type, json) {
    return {
        type: type,
        data: json
    }
}

export function getLoginUser(){

    return function (dispatch) {

        dispatch(receivePosts(types.REQ_LOGIN_USER, {org: '', userName:'', realName:'', employeeCode: ''}));

        return fetch(iportal+'/sso/getLoginUser.json', {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch(receivePosts(types.REQ_LOGIN_USER, json))
            }).catch(function(ex) {
                console.log('parsing failed', ex);
                dispatch(receivePosts(types.REQ_LOGIN_USER, {org: '', userName:'', realName:'', employeeCode: ''}))
            });
    }

}

export function findAllJobs(){

    return function (dispatch) {

        dispatch(receivePosts(types.REQ_LOGIN_JOBS, {currJob:{jobName:''}, jobs: []}));

        return fetch(iportal+'/runas/findAllJobs.json', {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json()
            })
            .then(function(json) {
                dispatch(receivePosts(types.REQ_LOGIN_JOBS, json))
            }).catch(function(ex) {
                console.log('parsing failed', ex);
                dispatch(receivePosts(types.REQ_LOGIN_JOBS, {currJob:{jobName:''}, jobs: []}))
            });
    }

}



export function reqRouterConfigs(){

    return function (dispatch) {

        dispatch(receivePosts(types.REQ_ROUTER_CONFIGS, []));

        return fetch(iportal+'/routerConfig.json', {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json()
            })
            .then(function(json) {
                dispatch(receivePosts(types.REQ_ROUTER_CONFIGS, json))
            }).catch(function(ex) {
                console.log('parsing failed', ex);
                dispatch(receivePosts(types.REQ_ROUTER_CONFIGS, []))
            });
    }

}

export function reqPortalMenus(callbackFunc){

    return function (dispatch) {

        dispatch(receivePosts(types.REQ_PORTAL_MENUS, []));

        return fetch(iportal+'/getPortalMenus.json', {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json()
            })
            .then(function(json) {
                dispatch(receivePosts(types.REQ_PORTAL_MENUS, json));
                callbackFunc();
            }).catch(function(ex) {
                console.log('parsing failed', ex);
                dispatch(receivePosts(types.REQ_PORTAL_MENUS, []))
            });
    }

}

export function selectNextMenuItem(nextMenuItem){

    return function (dispatch) {
        return dispatch(receivePosts(types.SELECT_NEXT_MENU_ITEM, nextMenuItem));
    }
}

export function selectLastMenuItem(lastMenuItem){

    return function (dispatch) {
        $("#root").removeAttr("style");
        document.body.removeAttribute("style");
        document.body.removeAttribute("class");
        return dispatch(receivePosts(types.SELECT_LAST_MENU_ITEM, lastMenuItem));
    }
}

export function selectActiveMenuItem(activeMenuItem){

    return function (dispatch) {
        return dispatch(receivePosts(types.SELECT_ACTIVE_MENU_ITEM, activeMenuItem));
    }
}

export function optModifyPasswordModal(){
    return  {
        type: types.OPT_MODIFY_PASSWORD
    }
}


export function savePasswordFunc(data){

    require("jquery-md5");
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        let passwordMd5 = $.md5(data.password.trim());
        var url = iportal + "/sysUser/modifyPasswordByCurrUser.json?password="+passwordMd5;
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
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
            dispatch(optModifyPasswordModal());
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function switchTo(id){

    return function (dispatch) {
        return fetch(iportal+'/runas/switchTo.json?id=' + id, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json()
            })
            .then(function(json) {
                clearUserAuthCache(dispatch);
            }).catch(function(ex) {
                console.log('parsing failed', ex);
            });
    }

}

function clearUserAuthCache(dispatch){
    return fetch(iportal+'/clearUserAuthCache.json', {credentials: 'same-origin', method: 'get'})
        .then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json()
        })
        .then(function(json) {
            $.niftyNoty({type: "success", container : '#msgDiv', html : "系统消息：操作成功", timer : 3000});
            location.reload();
        }).catch(function(ex) {
            console.log('parsing failed', ex);
        });
}


