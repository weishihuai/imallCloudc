import * as types from "../constants/ActionTypes";
import {niftyNoty} from '../../common';
import {showErrorMsg} from '../../../common/common';

/**
 * 设置搜索参数
 * @param params    搜索参数
 * @returns {Function}
 */
export function commonOrdeSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_ORDER_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 搜索
 * @param params    搜索参数
 * @returns {Function}
 */
export function commonOrderSearch(params) {
    return function (dispatch) {
        dispatch(commonOrdeSetSearchParams(params));
        dispatch(commonOrderList(params, 0, params.size));
    }
}

/**
 * 列表
 * @param params    搜索参数
 * @param page      页码
 * @param size      分页大小
 */
export function commonOrderList(params, page, size){
    return function (dispatch) {
        return fetch(iportal + '/order/commonOrderQuery.json?orderSourceCode=' + params.orderSourceCode + '&orderNum=' + params.orderNum + '&isEphedrineOrder='
            + params.isEphedrineOrder + '&isPrescriptionOrder=' + params.isPrescriptionOrder + '&formCreateDateString=' + params.formCreateDateString
            + '&toCreateDateString=' + params.toCreateDateString + '&page=' + page + '&size=' + size , {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
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
                type: types.COMMON_ORDER_LIST,
                data: json
            });

            dispatch({
                type: types.COMMON_ORDER_LIST_STATU,
                isShowOrderWin: true
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 订单选择组件调用入口方法
 * @param isEphedrineOrder      是否麻黄碱   是：'Y'；否：'N'；不需要区分：''
 * @param isPrescriptionOrder   是否处方药   是：'Y'；否：'N'；不需要区分：''
 */
export function showOrderSelectWin(isEphedrineOrder, isPrescriptionOrder){
    return function (dispatch) {
        //初始化参数
        dispatch({
            type: types.COMMON_ORDER_SEARCH_PARAMS_INIT,
            isEphedrineOrder: isEphedrineOrder,                 //是否麻黄碱
            isPrescriptionOrder: isPrescriptionOrder,           //是否处方药
        });

        //加载第一页数据
        let params = {
            orderSourceCode: '',
            orderNum: '',
            isEphedrineOrder: isEphedrineOrder,
            isPrescriptionOrder: isPrescriptionOrder,
            formCreateDateString: '',
            toCreateDateString: ''
        };
        dispatch(commonOrderList(params, 0, 10));
    }
}

/**
 * 关闭窗口
 * @returns {Function}
 */
export function closeOrderSelectWin(){
    return function (dispatch) {
        dispatch({
            type: types.COMMON_ORDER_LIST_STATU,
            isShowOrderWin: false
        });
    }
}

/**
 * 更新已被选中的订单和被全选的订单页码
 * @param selectedOrderMap  被选中的订单
 * @param selectedPageNum   被全选的订单页码
 */
export function updateSelectedOrder(selectedOrderMap, selectedPageNumMap) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_ORDER_UPDATE_SELECTED_ORDER,
            selectedOrderMap: selectedOrderMap,
            selectedPageNumMap: selectedPageNumMap
        });
    }
}

/**
 * 确定选中
 * @param isSingle              是否单选     单选：true；多选：false
 * @param selectedOrderMap      被选中的订单
 */
export function commonOrderSelect(isSingle, selectedOrderMap, event, callback) {
    return function (dispatch) {
        let rows = [];
        let index = 0;
        for (var [key, value] of selectedOrderMap) {
            rows[index] = value;
            index = index + 1;
        }

        if(rows.length==0){
            niftyNoty('至少要选一条记录', false);
            event.preventDefault();
        }else{
            dispatch({
                type: types.COMMON_ORDER_LIST_STATU,
                isShowOrderWin: false
            });

            if (isSingle) {
                callback(rows[0]);
            } else {
                callback(rows);
            }
        }
    }
}

