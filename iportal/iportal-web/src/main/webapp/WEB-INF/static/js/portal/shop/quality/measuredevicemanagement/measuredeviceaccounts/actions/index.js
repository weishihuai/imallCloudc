import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'
import {formatData} from '../../../../../../common/redux-form-ext'

/*计量器具列表*/
export function measuringDeviceAccountsList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/measuringDeviceAccounts/list.json?' + '&measuringDeviceNum=' + (params.measuringDeviceNum || "") + '&manufacturingNum=' + (params.manufacturingNum||"") + '&useStateCode=' + (params.useStateCode||"") + "&page=" + page + "&size=" + size, {
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
                    type: types.MEASURE_DEVICE_ACCOUNTS_LIST,
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
            type: types.MEASURE_DEVICE_ACCOUNTS_SEARCH_PARAMS,
            data: params
        })
    }
}

/*编辑窗体*/
export function measuringDeviceAccountsUpdateModel(isUpdateShow, data) {
    return function (dispatch) {
        dispatch({
            type: types.MEASURE_DEVICE_ACCOUNTS_UPDATE_MODEL,
            isUpdateShow: isUpdateShow,
            data: data
        });
    }
}

/*新增窗体*/
export function measuringDeviceAccountsAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.MEASURE_DEVICE_ACCOUNTS_ADD_MODAL,
            isAddShow: isAddShow,
        });
    }
}

//检测窗体
export function measuringDeviceAccountsCheckModel(isCheckShow,id) {
    return function (dispatch) {
        dispatch({
           type:types.MEASURE_DEVICE_ACCOUNTS_CHECK_MODAL,
            isCheckShow:isCheckShow,
            id: id,
        });
    }
}

//修改计量器具信息
export  function measuringDeviceAccountsUpdateData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if(data.id !== undefined){
            var  url = iportal + "/measuringDeviceAccounts/update.json?id=" + data.id;
        }
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
            dispatch(measuringDeviceAccountsUpdateModel(false));
            dispatch(measuringDeviceAccountsList(params,params.page,params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//添加计量器具
export  function measuringDeviceAccountsAddData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        var  url = iportal + "/measuringDeviceAccounts/save.json";
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
            dispatch(measuringDeviceAccountsAddModel(false));
            dispatch(measuringDeviceAccountsList(params,params.page,params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//检测计量器具
export  function measuringDeviceAccountsCheckData(data,id,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        var  url = iportal + "/measuringDeviceAccounts/measuringDeviceCheck.json?id=" + id;
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
            dispatch(measuringDeviceAccountsCheckModel(false));
            dispatch(measuringDeviceAccountsList(params,params.page,params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
