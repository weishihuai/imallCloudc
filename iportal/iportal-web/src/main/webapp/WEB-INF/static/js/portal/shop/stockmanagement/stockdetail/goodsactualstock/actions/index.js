import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";

export function gooodsActualStockList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/goods/queryGoodsActualStock.json?page=' + page + '&size=' + size
            + (params.searchFields ? "&searchFields=" + params.searchFields : ""), {
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
                type: types.GOODS_ACTUAL_STOCK_LIST,
                data: json
            })
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}
