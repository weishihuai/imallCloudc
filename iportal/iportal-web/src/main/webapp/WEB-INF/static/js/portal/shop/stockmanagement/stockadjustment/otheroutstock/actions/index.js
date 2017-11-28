import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

/*其他出库列表*/
export function otherOutStockList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/otherOutStock/list.json?' + 'typeCode=' + (params.typeCode || "") + '&searchFields=' + (params.searchFields || "") + '&outStockCode=' + (params.outStockCode || "") + '&outStockTimeStartString=' + (params.outStockTimeStartString || "") + '&outStockTimeEndString=' + (params.outStockTimeEndString || "") + "&page=" + page + "&size=" + size, {
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
            }).then(function (json) {
                dispatch({
                    type: types.OTHER_OUT_STOCK_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*设置参数*/
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.OTHER_OUT_STOCK_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function otherOutStockAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.OTHER_OUT_STOCK_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function otherOutStockDetailModal(isDetailShow,outStockCode) {
    return function (dispatch) {
        dispatch({
            type: types.OTHER_OUT_STOCK_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            outStockCode:outStockCode
        });
    };
}

/*查看详情*/
export function otherOutStockDetailData(outStockCode) {
    return function (dispatch) {
        return fetch(iportal + '/otherOutStock/findByOutStockCode.json?outStockCode='+outStockCode, {
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
                    type: types.OTHER_OUT_STOCK_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//审核人验证
export function approveValidateData(approveData) {
    return function (dispatch) {
        dispatch({
            type: types.OTHER_OUT_STOCK_APPROVE_VALIDATE,
            approveData:approveData
        });
    }
}

//保存其他出库信息
export function saveOtherOutStock(data,params) {
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/otherOutStock/saveOtherOutStock.json', {
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
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(otherOutStockList(params,params.page,params.size));
            dispatch(otherOutStockAddModel(false));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//当前登录用户信息
export function findCurrentLoginUserMessage() {
    return function (dispatch) {
        return fetch(iportal + '/sysUser/findCurrentLoginUserMessage.json', {
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
                    type: types.OTHER_OUT_STOCK_FIND_CURRENT_LOGIN_MESSAGE,
                    loginUserMessage: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

