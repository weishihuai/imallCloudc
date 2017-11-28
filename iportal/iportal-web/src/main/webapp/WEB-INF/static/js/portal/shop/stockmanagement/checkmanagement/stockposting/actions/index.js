import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common'

/*盘点过账列表*/
export function stockPostingList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/stockCheck/queryStockPosting.json?' + 'checkOrderNum=' + (params.checkOrderNum || "") + '&searchFields=' + (params.searchFields || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.STOCK_POSTING_LIST,
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
            type: types.STOCK_POSTING_SEARCH_PARAMS,
            data: params
        })
    }
}

//盘点过账操作
export  function stockPosting(id,params){
    return function (dispatch) {
        var  url = iportal + "/stockCheck/updateStockCheckStateToPosting.json?id=" + id;
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
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
            dispatch(stockPostingList(params,params.page,params.size));
            dispatch({type : types.STOCK_POSTING_CONFIRM_POST_MODAL, isConfirmModelShow: false});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

