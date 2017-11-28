import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../common/common";
import {VALID_FORM_INIT} from "../../../../common/validForm/constants/ActionTypes";
import {formatData} from "../../../../common/redux-form-ext";

//粉丝列表
export function fansList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/fans/list.json?' + '&fansName=' + (params.fansName || "") + '&mobile=' + (params.mobile || "") + '&nickName=' + (params.nickName || "") + '&fansSourceCode=' + (params.fansSourceCode || "")  + '&buyTimes=' + (params.buyTimes || "") + '&isMember=' + (params.isMember || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.WE_SHOP_FANS_LIST,
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
            type: types.WE_SHOP_FANS_SEARCH_PARAMS,
            data: params
        })
    }
}

/*更新备注窗体*/
export function fansUpdateRemarkModel(isUpdateModelShow,data,id) {
    return function (dispatch) {
        dispatch({
            type: types.WE_SHOP_FANS_UPDATE_MODEL,
            isUpdateModelShow: isUpdateModelShow,
            data: data,
            id:id
        });
    }
}

//更新粉丝备注
export function updateFansRemark(data){
    return function (dispatch) {
        if(data.id != undefined){
            var  url = iportal + "/fans/updateRemark.json?id=" + data.id;
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
            dispatch({type: VALID_FORM_INIT});
            dispatch(fansUpdateRemarkModel(false));
            dispatch(fansList("",0,10));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/*统计粉丝总数*/
export function countFansTotal() {
    return function (dispatch) {
        return fetch(iportal + '/fans/countFansTotal.json', {
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
                    type: types.WE_SHOP_FANS_TOTAL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
