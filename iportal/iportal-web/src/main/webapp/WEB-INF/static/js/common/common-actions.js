/**
 * 获取当前登录用户信息
 * @param callback
 * @returns {Function}
 */
export function getLoginUserInfo(callback) {
    return function (dispatch) {
        return fetch(iportal + '/sysUser/getLoginUserInfo.json' , {
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
            callback(json);
        }).catch(function (ex) {
            console.log('parsing failed', ex);
        });
    }
}