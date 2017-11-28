import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext';

export function goodsSplitZeroSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.GOODS_SPLIT_ZERO_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 新增窗口显示与隐藏
 * @param isShowAdd
 * @returns {Function}
 */
export function showAddForm(isShowAdd){
    return function (dispatch) {
        dispatch(refreshGoodsInfo(null));
        dispatch({
            type: types.GOODS_SPLIT_ZERO_ADD_MODULE,
            isShowAdd: isShowAdd
        })
    }
}

/**
 * 详情
 * @param id
 * @returns {Function}
 */
export function showDetail(isShowDetail, id={}){
    if(isShowDetail==true){
        let url = iportal + "/goodsSplitZero/findById.json?id="+id;
        return function(dispatch){
            return fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
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
                    type: types.GOODS_SPLIT_ZERO_DETAIL_MODULE,
                    isShowDetail: isShowDetail,
                    record: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function(dispatch){
            dispatch({
                type: types.GOODS_SPLIT_ZERO_DETAIL_MODULE,
                isShowDetail: isShowDetail
            });
        }
    }
}

/**
 * 新增
 * @param data
 * @param actions
 * @param params
 * @returns {Function}
 */
export function save(data, actions, params){
    let url = iportal + "/goodsSplitZero/saveGoodsSplitZero.json";
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
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
            dispatch(showAddForm(false));
            actions.goodsSplitZeroList(params, params.page, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function goodsSplitZeroList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/goodsSplitZero/query.json?keyword=' + params.keyword + '&startTimeString=' + params.startTimeString + '&endTimeString='
            + params.endTimeString + '&page=' + page + '&size=' + size , {
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
            dispatch({
                type: types.GOODS_SPLIT_ZERO_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//*搜索组件回调固定方法*/
export function goodsSplitZeroSearch(params) {
    return function (dispatch) {
        dispatch(goodsSplitZeroSetSearchParams(params));
        dispatch(goodsSplitZeroList(params, 0, params.size));
    }
}

/**
 * 刷拆零商品信息
 * @param goods
 * @returns {Function}
 */
export function refreshGoodsInfo(goods){
    return function(dispatch){
        dispatch({
            type: types.GOODS_SPLIT_ZERO_GOODS_SELECT,
            goods: goods
        });
    }
}

