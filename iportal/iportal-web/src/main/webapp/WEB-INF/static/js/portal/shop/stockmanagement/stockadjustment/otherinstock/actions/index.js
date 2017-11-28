import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

/*其他入库列表*/
export function otherInStockList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/otherInStock/list.json?' + '&typeCode=' + (params.typeCode || "") + '&searchFields=' + (params.searchFields || "") + '&inStockCode=' + (params.inStockCode || "")
            + '&inStockTimeStartString=' + (params.inStockTimeStartString || "") + '&inStockTimeEndString=' + (params.inStockTimeEndString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.OTHER_IN_STOCK_LIST,
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
            type: types.OTHER_IN_STOCK_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function otherInStockAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.OTHER_IN_STOCK_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function otherInStockDetailModal(isDetailShow,inStockCode) {
    return function (dispatch) {
        dispatch({
            type: types.OTHER_IN_STOCK_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            inStockCode:inStockCode
        });
    };
}

/*查看详情*/
export function otherInStockDetailData(inStockCode) {
    return function (dispatch) {
        return fetch(iportal + '/otherInStock/findByInStockCode.json?inStockCode='+inStockCode, {
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
                    type: types.OTHER_IN_STOCK_DETAIL_DATA,
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
            type: types.OTHER_IN_STOCK_APPROVE_VALIDATE,
            approveData:approveData
        });
    }
}

//保存其他入库信息
export function saveOtherInStock(data,params) {
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/otherInStock/saveOtherInStock.json', {
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
            dispatch(otherInStockList(params,0,10));
            dispatch(otherInStockAddModel(false));
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
                    type: types.OTHER_IN_STOCK_FIND_CURRENT_LOGIN_MESSAGE,
                    loginUserMessage: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//动态获取货位信息
export function listAllStorageSpace() {
    return function (dispatch) {
        return fetch(iportal+'/storageSpace/listAllStorageSpace.json', {
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
                    type: types.OTHER_IN_STOCK_ALL_STORAGE_SPACE,
                    allStorageSpace: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function loadGoodsBatchList(goodsId, callback) {
    return function (dispatch) {
        return fetch(iportal + '/goodsBatch/findBatch.json?goodsId=' + goodsId, {
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
            callback(json, goodsId);
        }).catch(function (ex) {
            console.log('parsing failed', ex);
        });
    }
}