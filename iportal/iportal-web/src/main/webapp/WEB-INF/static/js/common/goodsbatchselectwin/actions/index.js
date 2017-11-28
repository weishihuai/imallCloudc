import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../common/common';

/**
 *
 * @param isSingle 是否单选 单选true 多选false
 * @param isEphedrine 是否麻黄碱 有则填  没有则不传
 * @param prescriptionDrugsTypeCode 处方药类型代码:有则填  没有则不传
 * @param gtInStockDays 入库时间大于 N 天，没有则不传
 * @param isChineseMedicinePieces  Y Or N是否中药饮片 没有则不传
 * @param isBuiltIn  Y Or N 货位 是否 内置 没有则不传
 * @param isVirtualWarehouse  Y Or N 货位 是否 虚拟 没有则不传
 * @param isBiggerThanCurrentStock  Y Or N是否大于库存 没有则不传
 * @param isSplitZero  Y Or N 是否拆零 没有则不传
 * @param isInEffective  Y Or N 药品是否有效 没有则不传
 * @param isAllowOutStock  Y Or N 是否允许出库 没有则不传
 * @param callback 回调方法
 * @returns {Function}
 */
export function changeCommonGoodsBatchListState(params, callback = ()=> {}) {
    return function (dispatch,) {
        dispatch({
            type: types.COMMON_ADD_GOODS_BATCH_LIST_STATE,
            isSingle: params.isSingle,
            isEphedrine: params.isEphedrine||"",
            prescriptionDrugsTypeCode: params.prescriptionDrugsTypeCode||"",
            gtInStockDays: params.gtInStockDays||0,
            isChineseMedicinePieces: params.isChineseMedicinePieces||"",
            isBuiltIn: params.isBuiltIn||"",
            isVirtualWarehouse: params.isVirtualWarehouse||"",
            isBiggerThanCurrentStock: params.isBiggerThanCurrentStock||"",
            isSplitZero: params.isSplitZero||"",
            batchState:params.batchState||"",
            isInEffective:params.isInEffective||"",
            isAllowOutStock:params.isAllowOutStock||"",
            callback: callback
        })
    }
}

export function closeGoodsBatchSelectWinAndCallBack(selectedContents, callback) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_GOODS_BATCH_WIN_CLOSE
        })
        callback(selectedContents);
    }

}

export function loadCommonGoodsBatchList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/goodsBatch/commonGoodsBatchList.json?keyWords=' + params.goodsNm
            + '&goodsCode=' + params.goodsCode
            + '&batch=' + params.batch
            + "&gtInStockDays=" + params.gtInStockDays
            + "&prescriptionDrugsTypeCode=" + params.prescriptionDrugsTypeCode
            + "&isEphedrine=" + params.isEphedrine
            + "&isChineseMedicinePieces=" + params.isChineseMedicinePieces
            + "&isBuiltIn=" + params.isBuiltIn
            + "&isVirtualWarehouse=" + params.isVirtualWarehouse
            + "&isBiggerThanCurrentStock=" + params.isBiggerThanCurrentStock
            + "&isSplitZero=" + params.isSplitZero
            + "&batchState=" + params.batchState
            + "&isInEffective=" + params.isInEffective
            + "&isAllowOutStock=" + params.isAllowOutStock
            + '&page=' + page
            + '&size=' + size, {
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
                    type: types.COMMON_GOODS_BATCH_LIST,
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
            type: types.COMMON_GOODS_BATCH_LIST_SET_PARAMS,
            params: params
        })
    }
}
