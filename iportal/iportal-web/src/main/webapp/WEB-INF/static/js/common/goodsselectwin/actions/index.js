import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../common/common";


/**
 *
 * @param isSingle 是否单选 单选true 多选false
 * @param isEphedrine 是否麻黄碱  没有则不传
 * @param prescriptionDrugsTypeCode  没有则不传
 * @param callback 回调方法
 * @returns {Function}
 */
export function changeCommonGoodsListState(params, callback = ()=> {
}) {
    return function (dispatch,) {
        dispatch({
            type: types.COMMON_ADD_GOODS_LIST_STATE,
            isSingle: params.isSingle ,
            isEphedrine: params.isEphedrine || "",
            prescriptionDrugsTypeCode: params.prescriptionDrugsTypeCode || "",
            callback: callback
        })
    }
}

export function closeGoodsSelectWinAndCallBack(selectedContents, callback) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_GOODS_WIN_CLOSE
        });
        callback(selectedContents);
    }

}

export function loadCommonGoodsList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/goods/commonGoodsList.json?keyWords=' + params.goodsNm + '&goodsCode=' + params.goodsCode + '&produceManufacturer=' + params.produceManufacturer + '&page=' + page + '&size=' + size, {
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
                    type: types.COMMON_GOODS_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function setSearchParam(params) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_GOODS_LIST_SET_PARAMS,
            params: params
        })
    }
}

