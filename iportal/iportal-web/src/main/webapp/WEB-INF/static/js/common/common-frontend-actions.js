/**
 * 获取当前登录用户信息
 * @param callback
 * @returns {Function}
 */
export function getLoginUserInfo(callback) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/common/getLoginUserInfo.json' , {
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
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function sendNormalValidateCode(mobile, callback) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/common/sendNormalValidateCode.json?mobile=' + mobile , {
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
            callback(true);
        }).catch(function (ex) {
            callback(false);
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function getGoodsCount(callback) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/normalShopping/count.json', {
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
            callback(json.msg);
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function share(title, link, imgUrl, desc) {
    return function (dispatch) {
        wx.onMenuShareTimeline({title, link, imgUrl,
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareAppMessage({title, desc, link, imgUrl, type: '', dataUrl: '',
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareQQ({
            title, desc, link, imgUrl,
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareWeibo({title, desc, link, imgUrl,
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });

        wx.onMenuShareQZone({title, desc, link, imgUrl,
            success: function () {
                // 用户确认分享后执行的回调函数
            },
            cancel: function () {
                // 用户取消分享后执行的回调函数
            }
        });
    }
}

export function openLocation(lat, lng, name, address) {
    return function (dispatch) {
        wx.openLocation({
            latitude: lat, // 纬度，浮点数，范围为90 ~ -90
            longitude: lng, // 经度，浮点数，范围为180 ~ -180。
            name, // 位置名
            address, // 地址详情说明
            scale: 16, // 地图缩放级别,整形值,范围从1~28。默认为最大
            infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
        });
    }
}