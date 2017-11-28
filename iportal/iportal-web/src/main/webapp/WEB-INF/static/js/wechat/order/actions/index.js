import * as types from "../constants/ActionTypes";
import "whatwg-fetch";

/**
 * 列表
 * @param page
 * @param size
 * @param historyData 历史数据
 * @returns {Function}
 */
export function orderList(page, size, historyData=[]) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/order/query.json?page=' + page + '&size=' + size, {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.WE_CHAT_ORDER_LIST,
                pageLength: json.content.length,
                data: Object.assign({},json,{
                    content:historyData.concat(json.content)
                })
            });
        }).catch(function (ex) {
            console.log('parsing failed', ex);
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

/**
 * 获取订单详情
 * @param id        订单 ID
 * @returns {Function}
 */
export function findById(id) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/order/findById.json?id=' + id, {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.WE_CHAT_ORDER_DETAIL,
                orderDetail: json
            });
        }).catch(function (ex) {
            console.log('parsing failed', ex);
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

/**
 * 更新订单状态
 * @param data
 * @returns {Function}
 */
export function updateOrderState(data) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/order/updateOrderState.json' , {
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
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch(showConfirm(false));
            dispatch(findById(data.id));
        }).catch(function (ex) {
            console.log('parsing failed', ex);
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

/**
 * 再次购买
 * @param id
 * @returns {Function}
 */
export function buyAgain(id) {
    return function(dispatch){
        return fetch(iportal + '/wechat/order/buyAgain.json?id=' + id , {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            location.href = '#/shopping-list';
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

/**
 * 订单取消窗口显示与隐藏
 * @param isShowCancel
 * @returns {Function}
 */
export function showOrderCancelView(isShowCancel) {
    return function (dispatch) {
        dispatch({
            type: types.WE_CHAT_ORDER_CANCEL,
            isShowCancel: isShowCancel
        });
    }
}

/**
 * 确认窗口显示与隐藏
 * @param isShowConfirm
 * @returns {Function}
 */
export function showConfirm(isShowConfirm) {
    return function (dispatch) {
        dispatch({
            type: types.WE_CHAT_CONFIRM,
            isShowConfirm: isShowConfirm
        });
    }
}