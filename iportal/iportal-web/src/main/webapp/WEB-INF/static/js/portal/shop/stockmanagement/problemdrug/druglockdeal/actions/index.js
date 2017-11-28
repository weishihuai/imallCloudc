import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

export function drugLockDealList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugLock/queryDrugLockDeal.json?' + '&keyword=' + (params.keyword || "") + '&batch=' + (params.batch || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.DRUG_LOCK_DEAL_LIST,
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
            type: types.DRUG_LOCK_DEAL_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function drugLockDealAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_LOCK_DEAL_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

/*处理锁定药品*/
export function updateDrugLockDeal(data,params) {
    return function(dispatch){
        return fetch(iportal + '/drugLock/updateDrugLockDeal.json', {
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
            dispatch(drugLockDealList(params,0,10));
            dispatch(drugLockDealAddModel(false));
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//审核人验证
export function approveValidateData(approveData) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_LOCK_DEAL_APPROVE_VALIDATE,
            approveData:approveData
        });
    }
}

/**
 *
 * @param isSingle 是否单选 单选true 多选false
 * @param callback 回调方法
 * @returns {Function}
 */
export function changeDrugLockDealGoodsBatchListState(params,callback = ()=> {}) {
    return function (dispatch,) {
        dispatch({
            type: types.DRUG_LOCK_DEAL_GOODS_LIST_STATE,
            isSingle: params.isSingle,
            callback: callback
        })
    }
}

export function closeDrugLockDealGoodsBatchSelectWinAndCallBack(selectedContents, callback) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_LOCK_DEAL_GOODS_WIN_CLOSE
        });
        callback(selectedContents);
    }

}

export function loadDrugLockDealGoodsBatchList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/drugLock/drugLockDealGoodsBatchList.json?goodsNm=' + (params.goodsNm || "") + '&goodsCode=' + (params.goodsCode || "") + '&batch=' + (params.goodsBatch || "") + '&page=' + page + '&size=' + size, {
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
                    type: types.DRUG_LOCK_DEAL_GOODS_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
