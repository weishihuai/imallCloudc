import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

//药品清斗列表
export function drugClearBucketList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugClearBucket/list.json?' + '&searchFields=' + (params.searchFields || "") + '&batch=' + (params.batch || "") + '&clearBucketManName=' + (params.clearBucketManName || "")
            + '&clearBucketStartTimeString=' + (params.clearBucketStartTimeString || "") + '&clearBucketEndTimeString=' + (params.clearBucketEndTimeString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.DRUG_CLEAR_BUCKET_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//设置参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_CLEAR_BUCKET_SEARCH_PARAMS,
            data: params
        })
    }
}

//新增窗体
export function drugClearBucketAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_CLEAR_BUCKET_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

//显示 隐藏 详情页 页面
export function drugClearBucketDetailModal(isDetailShow,id) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_CLEAR_BUCKET_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            id:id
        });
    };
}

//查看详情
export function drugClearBucketDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/drugClearBucket/findById.json?id='+id, {
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
                    type: types.DRUG_CLEAR_BUCKET_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//获取审核对象
export function fingApproveValidateData(approveData) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_CLEAR_ADD_APPROVE,
            approveData:approveData
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
                    type: types.DRUG_CLEAR_FIND_CURRENT_LOGIN_MESSAGE,
                    loginUserMessage: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//保存
export function saveData(data,params) {
        return function(dispatch){
            dispatch({type: VALID_FORM_INIT});
            return fetch(iportal + '/drugClearBucket/saveDrugClearBucket.json', {
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
                dispatch(drugClearBucketList(params,0,10));
                dispatch(drugClearBucketAddModel(false));
                dispatch(findApproveValidateData({}));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

