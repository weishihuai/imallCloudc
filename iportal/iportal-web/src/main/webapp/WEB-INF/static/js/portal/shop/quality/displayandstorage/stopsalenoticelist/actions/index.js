import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {formatData} from '../../../../../../common/redux-form-ext'

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.STOP_SALE_NOTICE_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function changeStopSaleNoticeDetailViewState(isOpen) {
    return function (dispatch) {
        dispatch({
            type: types.STOP_SALE_NOTICE_DETAIL_VIEW_STATE,
            data: isOpen
        })
    }
}

export function changeAddFormState(isOpen) {
    return function (dispatch) {
        dispatch({
            type: types.STOP_SALE_NOTICE_ADD_FORM_STATE,
            data: isOpen
        })
    }
}

export function query(params, page, size) {
    let url ="/drugStopSaleNotice/query.json";
    return function (dispatch) {
        return fetch(iportal + url
            +'?stopSaleNum='+ params.stopSaleNum
            +'&docMakerNm=' + params.docMakerNm
            +'&approveManName=' + params.approveManName
            +'&fromMakeTimeStr=' + params.fromMakeTimeStr
            +'&toMakeTimeStr=' + params.toMakeTimeStr
            +'&toStopSaleDateStr=' + params.toStopSaleDateStr
            +'&fromStopSaleDateStr=' + params.fromStopSaleDateStr
            +'&page=' + page
            + '&size=' + size , {
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
                type: types.STOP_SALE_NOTICE_LIST,
                data: json
            });
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}

export function findDetailAndOpenDetailView(id) {
    let url ="/drugStopSaleNotice/findOne.json";
    return function (dispatch) {
        return fetch(iportal + url +'?id='+ id, {
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
                type: types.STOP_SALE_NOTICE_DETAIL_DATA,
                data: json
            });
            dispatch(changeStopSaleNoticeDetailViewState(true))
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}


export function saveStopSalseNotice(data,params,page) {
    return function(dispatch){
        return fetch(iportal + '/drugStopSaleNotice/save.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(data))
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(changeAddFormState(false));
            dispatch({type: VALID_FORM_INIT});
            dispatch(query(params,page.number,page.size))
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}


