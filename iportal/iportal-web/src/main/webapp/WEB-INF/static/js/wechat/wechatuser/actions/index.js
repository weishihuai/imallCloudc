import * as types from "../constants/ActionTypes";

/*微信用户 修改昵称*/
export function updateWeChatUserNickName(data) {
    return function(dispatch){
        return fetch(iportal + "/wechat/weChatUser/updateNickName.json", {
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
            dispatch(updateNickNameModel(false));
            dispatch(weChatUserInfoDetailData(data.id));
            window.location.hash = "#/wechat-user-info-detail/" + data.id;
        }).catch(function (ex) {

        });
    }
}

export function setWeChatUserInfoDetailData(data) {
    return function (dispatch) {
        dispatch({type: types.WE_CHAT_USER_INFO_DETAIL_DATA, data});
    }
}

/*查看详情*/
export function weChatUserInfoDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/weChatUser/findById.json?id='+id, {
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
                    type: types.WE_CHAT_USER_INFO_DETAIL_DATA,
                    data: json
                });
                window.location.hash = "#/wechat-user-info-detail/" + id;
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*获取微信用户的相关信息*/
export function findWeChatUserInfo() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/weChatUser/findWeChatUserInfo.json', {
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
            dispatch({
                type: types.WE_CHAT_USER_INFO_DATA,
                data: json
            });
        }).catch(function (ex) {

        });
    }
}

export function updateNickNameModel(isUpdateModelShow, data) {
    return function (dispatch) {
        dispatch({
            type: types.WE_CHAT_USER_UPDATE_NICKNAME_MODEL,
            isUpdateModelShow: isUpdateModelShow,
            data: data
        });
    }
}

export function bindMobile(id, mobile, validateCode) {
    return function(dispatch){
        return fetch(iportal + "/wechat/weChatUser/bindMobile.json?mobile=" + mobile + "&validateCode=" + validateCode, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            window.location.hash = "#/wechat-user-info-detail/" + id;
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function getWeiXinJsConfig(callback) {
    let signUrl = location.href.split('#')[0];
    signUrl = encodeURIComponent(signUrl);
    return function(dispatch){
        return fetch(iportal + "/wechat/common/getWeiXinJsConfig.json?signUrl=" + signUrl, {
            mode: 'cors',
            credentials: 'include',
            method: 'GET',
            redirect: 'follow'
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            callback(json);
        }).catch(function (ex) {
            showError("初始化失败，请稍后再试。");
        });
    }
}

export function uploadUserIcoFromWeiXin(mediaId, callback) {
    return function(dispatch){
        return fetch(iportal + "/wechat/weChatUser/uploadUserIcoFromWeiXin.json?mediaId=" + mediaId, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            callback(json);
        }).catch(function (ex) {
            showError("上传图片失败，请稍后再试。");
        });
    }
}

