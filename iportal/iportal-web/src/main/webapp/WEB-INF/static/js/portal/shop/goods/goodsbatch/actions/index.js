import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../common/common'
import {formatData} from '../../../../../common/redux-form-ext'

/*商品批号修改列表*/
export function goodsBatchList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/goodsBatch/list.json?' + '&batch=' + (params.batch || "") + '&searchFields=' + (params.searchFields || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.GOODS_BATCH_LIST,
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
            type: types.GOODS_BATCH_SEARCH_PARAMS,
            data: params
        })
    }
}

/*编辑窗体*/
export function goodsBatchEditModel(isShow, data) {
    return function (dispatch) {
        dispatch({
            type: types.GOODS_BATCH_UPDATE_MODEL,
            isShow: isShow,
            data: data
        });
    }
}

//更新商品批号
export  function goodsBatchUpdateData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if(data.id != undefined){
            var  url = iportal + "/goodsBatch/update.json?id=" + data.id;
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
            dispatch(goodsBatchEditModel(false));
            dispatch(goodsBatchList(params ,params.page ,params.size))
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
            type: types.GOODS_BATCH_LIST_APPROVE_VALIDATE,
            approveData:approveData
        });
    }
}