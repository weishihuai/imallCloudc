import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../common/common';

//获取 详情 编辑 对象
export function findShopDetail() {

    return function (dispatch) {
        return fetch(iportal + '/shop/findByCurrentShop.json', {
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
                 dispatch({
                    type: types.SHOP_SET_DETAIL_OBJECT,
                    detailObject: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//获取 品种
export function findByAvailableAndDictType(data) {

    return function (dispatch) {
        return fetch(iportal + '/sysDictItem/findByAvaiAndDictType.json?dictType=' + data, {
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
            })
            .then(function (json) {
                    return dispatch({
                        type: types.SHOP_FIND_BUSINESS_RANGE,
                        businessRangeAllData: json
                    });


            })
            .catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('查看详情失败', ex)
            });
    }
}