import * as types from "../constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common'

//计量器具 检测记录 列表
export function measureRecordList(params, page, size) {
    return function (dispatch) {
        const empty = {
            type: types.MEASURE_RECORD_LIST,
            data: {content: [], totalElements: 0, number: 1, size: 10}
        };
        dispatch(empty);
        return fetch(iportal + '/measureRecord/list.json?' + '&measuringDeviceNum=' + (params.measuringDeviceNum || "") + '&manufacturingNum=' + (params.manufacturingNum || "") + '&measureDateStartString=' + (params.measureDateStartString || "") + '&measureDateEndString=' + (params.measureDateEndString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.MEASURE_RECORD_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//设置参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.MEASURE_RECORD_SEARCH_PARAMS,
            data: params
        })
    }
}

//显示 隐藏 详情页 页面
export function measureRecordDetailModal(isDetailShow,id) {
    return function (dispatch) {
        dispatch({
            type: types.MEASURE_RECORD_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            id:id
        });
    };
}

//查看详情
export function measureRecordDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/measureRecord/findById.json?id='+id, {
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
        })
            .then(function (response) {
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.MEASURE_RECORD_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
