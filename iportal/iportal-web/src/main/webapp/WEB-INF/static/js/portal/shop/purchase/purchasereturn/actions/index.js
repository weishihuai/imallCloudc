import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from "../../../../../common/common";

export function showAdd() {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_RETURN_SHOW_ADD});
    }
}

export function showGoodsSelect(supplierId) {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_RETURN_SHOW_GOODS_SELECT, supplierId});
    }
}

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.PURCHASE_RETURN_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function queryReturnableItem(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/purchaseAcceptanceRecordItem/queryReturnableItem.json?supplierId='+ params.supplierId + '&produceManufacturer='+ params.produceManufacturer  + '&goodsCode='+ params.goodsCode + "&goodsNm="+ params.goodsNm +'&page=' + page + '&size=' + size , {
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
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.PURCHASE_RETURN_GOODS_DATA,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function showDetail(id) {
    return function (dispatch) {
        if(id){
            return fetch(iportal + '/returnedPurchaseOrder/findOne.json?id=' + id , {
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
            }).then(function (response) {
                return response.json();
            }).then(function (json) {
                dispatch({
                    type: types.PURCHASE_RETURN_SHOW_DETAIL,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
            });
        }else {
            dispatch({type: types.PURCHASE_RETURN_SHOW_DETAIL});
        }

    }
}

export function query(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/returnedPurchaseOrder/query.json?returnedPurchaseType='+ params.returnedPurchaseType + '&isPayed='+ params.isPayed  + '&returnedPurchaseOrderNum='+ params.returnedPurchaseOrderNum +'&page=' + page + '&size=' + size , {
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
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.PURCHASE_RETURN_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function savePurchaseReturn(data) {
    return function(dispatch){
        return fetch(iportal + "/returnedPurchaseOrder/save.json", {
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
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch(showAdd());
            dispatch(setParamsAndLoad({returnedPurchaseType: '', isPayed: '', returnedPurchaseOrderNum: ''}));
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setParamsAndLoad(params) {
    return function (dispatch) {
        dispatch(setParams(params));
        dispatch(query(params, 0, 10));
    }
}

export function setGoodsSelectParamsAndLoad(params) {
    return function (dispatch) {
        dispatch({type: types.PURCHASE_RETURN_GOODS_SELECT_SET_PARAMS, data: params});
        dispatch(queryReturnableItem(params, 0, 10));
    }
}
