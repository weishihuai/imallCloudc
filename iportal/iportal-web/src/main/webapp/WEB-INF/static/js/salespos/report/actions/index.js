import * as types from "../constants/ActionTypes";
import {niftyNoty} from "../../../common/common";
import {formatData} from "../../../common/redux-form-ext";

export function orderList(params, page, size) {
    params = formatData(params);
    return function (dispatch) {
        let url  = iportal + '/order/commonOrderQuery.json?'+'orderNum=' + (params.orderNum || "") + '&goodsSearchFields=' + (params.goodsSearchFields || "") + '&memberSearchFields=' + (params.memberSearchFields || "")
        + '&formCreateDateString=' + (params.formCreateDateString || "") + '&toCreateDateString=' + (params.toCreateDateString || "") + "&page=" + page + "&size=" + size;

        if(params.orderType && params.orderType==="RETURN_ORDER") {
            url = iportal + '/sellReturnedPurchaseOrder/query.json?'+'orderNum=' + (params.orderNum || "") + '&goodsSearchFields=' + (params.goodsSearchFields || "") + '&memberSearchFields=' + (params.memberSearchFields || "")
                + '&fromDateString=' + (params.formCreateDateString || "") + '&toDateString=' + (params.toCreateDateString || "") + "&page=" + page + "&size=" + size;
        }
        dispatch(sumOrderTotalAmount(params));

        return fetch(url
        , {
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
                dispatch(getOrderItemList(orderId,params.orderType));
                dispatch({
                    type: types.SALES_REPORT_ORDER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}

export function sumOrderTotalAmount(params) {
    return function (dispatch) {
        const empty = {
            type: types.SALES_REPORT_SUM_ORDER_TOTAL_AMOUNT,
            data: 0
        };
        dispatch(empty);
        let isHasReturnedPurchase = "";
        if(params.orderType && params.orderType==="SALES_ORDER"){
            isHasReturnedPurchase = 'N';
        }
        if(params.orderType && params.orderType==="RETURN_ORDER"){
            isHasReturnedPurchase = 'Y';
        }
        return fetch(iportal + '/order/sumOrderTotalAmount.json?' + 'orderNum=' + (params.orderNum || "") + '&goodsSearchFields=' + (params.goodsSearchFields || "") + '&memberSearchFields=' + (params.memberSearchFields || "")
            + '&isHasReturnedPurchase='+ isHasReturnedPurchase  + '&formCreateDateString=' + (params.formCreateDateString || "") + '&toCreateDateString=' + (params.toCreateDateString || ""), {
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
                    type: types.SALES_REPORT_SUM_ORDER_TOTAL_AMOUNT,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}

export function getOrderItemList(orderId,orderType) {
    return function (dispatch) {
        const empty = {
            type: types.SALES_REPORT_ORDER_ITEM_LIST,
            data: []
        };
        dispatch(empty);
        if(orderId==0){
            return;
        }
        let url = iportal + '/orderItem/findByOrderId.json?';
        if(orderType == "RETURN_ORDER"){
            url = iportal + '/sellReturnedPurchaseOrderItem/findBySellReturnedPurchaseOrderId.json?';
        }
        return fetch(url + '&orderId=' + orderId, {
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
                    type: types.SALES_REPORT_ORDER_ITEM_LIST,
                    data: json
                })
            }).catch(function (ex) {
                niftyNoty(ex.response.statusText);
                console.log('parsing failed', ex)
            });
    }
}