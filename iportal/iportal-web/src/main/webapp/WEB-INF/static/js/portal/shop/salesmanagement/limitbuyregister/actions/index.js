import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext';

export function limitBuyRegisterSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.LIMIT_BUY_REGISTER_SEARCH_PARAMS,
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
        dispatch({
            type: types.LIMIT_BUY_REGISTER_ADD_MODULE,
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
        let url = iportal + "/limitBuyRegister/findById.json?id="+id;
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
                    type: types.LIMIT_BUY_REGISTER_DETAIL_MODULE,
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
                type: types.LIMIT_BUY_REGISTER_DETAIL_MODULE,
                isShowDetail: isShowDetail
            });
        }
    }
}

/**
 * 编辑窗口显示与隐藏
 * @param id
 * @returns {Function}
 */
export function showEditForm(isShowEdit, id={}){
    if(isShowEdit==true){
        let url = iportal + "/limitBuyRegister/findById.json?id="+id;
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
                    type: types.LIMIT_BUY_REGISTER_EDIT_MODULE,
                    isShowEdit: isShowEdit,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function(dispatch){
            dispatch({
                type: types.LIMIT_BUY_REGISTER_EDIT_MODULE,
                isShowEdit: isShowEdit
            });
        }
    }
}

/**
 * 新增或更新
 * @param data
 * @param actions
 * @param params
 * @returns {Function}
 */
export function saveOrUpdate(data, actions, params){
    let url;
    let isAdd;
    if(data.id==undefined){//新增
        url = iportal + "/limitBuyRegister/save.json";
        isAdd = true;
    }else{//更新
        url = iportal + "/limitBuyRegister/update.json";
        isAdd = false;
    }

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
            if(isAdd){
                dispatch({
                    type: types.LIMIT_BUY_REGISTER_ADD_MODULE,
                    isShowAdd: false,
                    items: null
                });
            }else{
                dispatch({
                    type: types.LIMIT_BUY_REGISTER_EDIT_MODULE,
                    isShowEdit: false
                });
            }

            let data = {
                sellOrderCode:"",             //订单编号
                patientNm:"",                 //患者名称
                registerStartDateString:"",   //开始时间
                registerEndDateString:""      //结束时间
            };

            actions.limitBuyRegisterList(data, params.page, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 列表
 */
export function limitBuyRegisterList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/limitBuyRegister/query.json?sellOrderCode=' + params.sellOrderCode + '&patientNm=' + params.patientNm + '&registerStartDateString='
            + params.registerStartDateString + '&registerEndDateString=' + params.registerEndDateString + '&page=' + page + '&size=' + size , {
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
                type: types.LIMIT_BUY_REGISTER_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//*搜索组件回调固定方法*/
export function limitBuyRegisterSearch(params) {
    return function (dispatch) {
        dispatch(limitBuyRegisterSetSearchParams(params));
        dispatch(limitBuyRegisterList(params, 0, params.size));
    }
}

/**
 * 获取麻黄碱订单项
 * @param order
 * @returns {Function}
 */
export function queryOrderItem(order){
    return function (dispatch) {
        return fetch(iportal + '/limitBuyRegister/listItemByOrderId.json?orderId=' + order.id, {
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
                type: types.LIMIT_BUY_REGISTER_ADD_MODULE,
                isShowAdd: true,
                items: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}



