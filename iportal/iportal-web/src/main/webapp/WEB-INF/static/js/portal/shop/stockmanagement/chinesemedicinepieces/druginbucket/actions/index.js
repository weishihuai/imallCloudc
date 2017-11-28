import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

/*药品装斗列表*/
export function drugInBucketList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugInBucket/list.json?' + '&searchFields=' + (params.searchFields || "") + '&batch=' + (params.batch || "") + '&inBucketManName=' + (params.inBucketManName || "")
            + '&inBucketStartTimeString=' + (params.inBucketStartTimeString || "") + '&inBucketEndTimeString=' + (params.inBucketEndTimeString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.DRUG_IN_BUCKET_LIST,
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
            type: types.DRUG_IN_BUCKET_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function drugInBucketAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_IN_BUCKET_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function drugInBucketDetailModal(isDetailShow,id) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_IN_BUCKET_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            id:id
        });
    };
}

/*查看详情*/
export function drugInBucketDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/drugInBucket/findById.json?id='+id, {
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
                    type: types.DRUG_IN_BUCKET_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//获取审核对象
export function findApproveValidateData(approveData) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_IN_ADD_APPROVE,
            approveData:approveData
        });
    }
}

//获取货位
export function findAllStorageSpace() {
    return function (dispatch) {
        return fetch(iportal + '/storageSpace/listAllStorageSpaceIsNotVirtualWarehouse.json?isVirtualWarehouse=N&storageSpaceType=CHINESE_HERBAL_MEDICINE&isEnable=Y', {
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
                    type: types.DRUG_IN_FIND_ALL_STORAGE_SPACE,
                    allStorageSpace: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
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
                    type: types.DRUG_IN_FIND_CURRENT_LOGIN_MESSAGE,
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
            return fetch(iportal + '/drugInBucket/saveDrugInBucket.json', {
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
                dispatch(drugInBucketList(params,0,10));
                dispatch(drugInBucketAddModel(false));
                dispatch(findApproveValidateData({}));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

