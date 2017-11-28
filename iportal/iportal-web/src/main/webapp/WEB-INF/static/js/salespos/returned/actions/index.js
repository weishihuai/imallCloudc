import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../common/validForm/constants/ActionTypes";
import {niftyNoty} from "../../../common/common";
import {showErrorMsg} from '../../../common/common'

export function orderList(params, page, size) {
    return function (dispatch) {
        const empty = {
            type: types.RETURNED_PURCHASE_ORDER_LIST,
            data: {content: [], totalElements: 0, number: 1, size: 10}
        };
        dispatch(empty);

        return fetch(iportal + '/sellReturnedPurchaseOrder/commonOrderQuery.json?' + 'orderNum=' + (params.orderNum || "") + '&goodsSearchFields=' + (params.goodsSearchFields || "")  + '&formCreateDateString=' + (params.formCreateDateString || "") + '&toCreateDateString=' + (params.toCreateDateString || "") + "&isHasReturnedPurchase=N" + "&page=" + page + "&size=" + size, {
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
                const orderId = json.content.length > 0 ? json.content[0].id : 0;
                dispatch(getOrderItemList(orderId));
                dispatch({
                    type: types.RETURNED_PURCHASE_ORDER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}

export function getOrderItemList(orderId) {
    return function (dispatch) {
        const empty = {
            type: types.RETURNED_PURCHASE_ORDER_ITEM_LIST,
            data: []
        };
        dispatch(empty);
        if(orderId===0){
            return;
        }
        return fetch(iportal + '/sellReturnedPurchaseOrder/findByOrderId.json?' + '&orderId=' + orderId, {
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
                    type: types.RETURNED_PURCHASE_ORDER_ITEM_LIST,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}

export function orderSelectModel(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.RETURNED_PURCHASE_ORDER_SELECT_MODEL_STAT_CHANGE,
            isShow: isShow
        });
    };
}

export function returnedCalcForm(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.RETURNED_PURCHASE_RETURNED_CALC_FORM_STAT_CHANGE,
            isShow: isShow
        });
    };
}

export function calcSubmit(data) {
    if(parseFloat(data.realReturnCashAmount)<parseFloat(data.refundTotalAmount)){

        niftyNoty("实退金额不能小于应退金额");
        return function (dispatch) {
            dispatch({type: VALID_FORM_INIT});
        };
    }
    return function (dispatch) {
        var url = iportal + "/sellReturnedPurchaseOrder/posReturnedSave.json";
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
            dispatch({type: VALID_FORM_INIT});
            dispatch(returnedCalcForm(false));
            window.location.href = iportal + "/returned.html";
        }).catch(function (ex) {
            niftyNoty(ex.response.statusText);
            console.log('parsing failed', ex);
        });
    }
}