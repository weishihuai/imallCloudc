import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";

export function purchaseSaleStockList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/outInStockLog/queryOutInStockStatistics.json?page=' + page + '&size=' + size
            + (params.searchFields ? "&searchFields=" + params.searchFields : "") + (params.fromDateString ? "&fromDateString=" + params.fromDateString : "")
            + (params.toDateString ? "&toDateString=" + params.toDateString : ""), {
            mode: 'cors', credentials: 'include', method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.PURCHASE_SALE_STOCK_LIST,
                data: json
            })
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}
