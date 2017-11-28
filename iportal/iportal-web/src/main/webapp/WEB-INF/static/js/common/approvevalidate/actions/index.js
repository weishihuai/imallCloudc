import * as types from '../constants/ActionTypes'

export function showValidateModel(approveType="") {
    return function (dispatch) {
        dispatch({type: types.APPROVE_VALIDATE_COMPONENT_MODAL, data:approveType});
    }
}

export function doValidate(userName, password,approveType,callbackFunc) {
    require("jquery-md5");
    return function (dispatch) {
    return fetch(iportal + "/sysUser/validate.json?userName=" + userName + "&password=" + $.md5(password), {
        mode: 'cors',
        credentials: 'include',
        method: 'POST',
        redirect: 'follow'
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
    }).then(function(json) {
         callbackFunc(Object.assign({},json,{
             approveType:approveType
         }));
        dispatch(showValidateModel());
    }).catch(function(ex) {
        dispatch(showErrorMsg(ex.response));
        console.log('parsing failed', ex)
    });
    }
}

export function showErrorMsg(response){
    return function (dispatch) {
        if(response.status!=200){
            const code = response.headers.get("code");
            switch (code){
                case 'COMMON_OBJECT_NO_FOUND':
                      $("#user-name-error").html("用户不存在").show();
                    break;
                case 'PASSWORD_ERROR':
                    $("#password-error").html("密码错误").show();
                    break;
                default:
                    response.text().then(function (text) {
                        var title = response.status + ' ' + response.statusText;
                        $.niftyNoty({
                            type: "danger",
                            container : 'floating',
                            html : '<h4 class="alert-title">错误消息</h4> <p class="alert-message">'+text+'</p>',
                            timer : 0,
                            floating:{position:'bottom-right',animationIn:'fadeIn'}
                        });
                    });
                    break;
            }
        }
    }
}