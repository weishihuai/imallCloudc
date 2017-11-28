import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes';
import {showErrorMsg} from '../../../../../../common/common';

/*库存盘点列表*/
export function stockCheckList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/stockCheck/list.json?' + '&checkOrderNum=' + (params.checkOrderNum || "") + '&checkedStateCode=' + (params.checkedStateCode || "")
            + '&createDateBeginString=' + (params.createDateBeginString || "") + '&createDateEndString=' + (params.createDateEndString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.STOCK_CHECK_LIST,
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
            type: types.STOCK_CHECK_SEARCH_PARAMS,
            data: params
        })
    }
}

/*取消库存盘点*/
export function stockCheckCancel(checkOrderNum,params) {
    return function (dispatch) {
        return fetch(iportal + '/stockCheck/updateCheckedStateByCheckOrderNum.json?checkOrderNum='+checkOrderNum, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body:JSON.stringify(checkOrderNum)
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
        }).then(function (json) {
            dispatch({type : types.STOCK_CHECK_CANCEL_CONFIRM_MODEL, isConfirmModelShow: false});
            dispatch(stockCheckList(params,params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex)
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function stockCheckDetailModal(isDetailShow,checkOrderNum) {
    return function (dispatch) {
        dispatch({
            type: types.STOCK_CHECK_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            checkOrderNum:checkOrderNum
        });
    };
}

/*查看盘点详情*/
export function stockCheckDetailData(checkOrderNum) {
    return function (dispatch) {
        return fetch(iportal + '/stockCheck/findByCheckOrderNum.json?checkOrderNum='+checkOrderNum, {
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
                    type: types.STOCK_CHECK_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*库存盘点窗体*/
export function stockCheckUpdateRealQuantityModal(isUpdateShow,checkOrderNum) {
    return function (dispatch) {
        dispatch({
            type: types.STOCK_CHECK_UPDATE_REAL_QUANTITY_MODAL,
            isUpdateShow: isUpdateShow,
            checkOrderNum:checkOrderNum
        });
    };
}

/*库存盘点时的盘点详情*/
export function stockCheckUpdateDetailData(checkOrderNum) {
    return function (dispatch) {
        return fetch(iportal + '/stockCheck/findByCheckOrderNum.json?checkOrderNum='+checkOrderNum, {
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
                    type: types.STOCK_CHECK_UPDATE_REAL_QUANTITY_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*库存盘点*/
export function stockCheckUpdateData(data,params) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + "/stockCheck/updateStockCheckRealCheckQuantity.json", {
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
        }).then(function (json) {
            dispatch(stockCheckUpdateRealQuantityModal(false));
            dispatch(stockCheckList(params,params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });

    }
}

/*新增库存盘点*/
export  function stockCheckAddData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        var  url = iportal + "/stockCheck/saveStockCheck.json";
        return fetch(url, {
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
        }).then(function (json) {
            dispatch(stockCheckAddModel(false));
            dispatch(stockCheckList(params,params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/*新增窗体*/
export function stockCheckAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.STOCK_CHECK_LIST_ADD_MODEL,
            isAddShow: isAddShow
        });
    }
}

//获取当前登录用户的信息
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
                    type: types.STOCK_CHECK_FIND_CURRENT_LOGIN_MESSAGE,
                    loginUserMessage: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

