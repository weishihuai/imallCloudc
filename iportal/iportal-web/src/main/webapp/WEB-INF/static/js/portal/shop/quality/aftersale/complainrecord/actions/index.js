import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";
import {formatData} from "../../../../../../common/redux-form-ext";
import {VALID_FORM_INIT} from "../../../../../../common/validForm/constants/ActionTypes";

export function complainRecordList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/complainRecord/query.json?page=' + page + '&size=' + size
            + (params.searchFields ? "&searchFields=" + params.searchFields : "") + (params.customerName ? "&customerName=" + params.customerName : "")
            + (params.fromDateString ? "&fromDateString=" + params.fromDateString : "")
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
                type: types.COMPLAIN_RECORD_LIST,
                data: json
            })
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function complainAddFormModal(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.COMPLAIN_RECORD_ADD_FORM_MODAL,
            isShow: isShow
        })
    }
}

export function complainDetailModal(data, isShow) {
    return function (dispatch) {
        dispatch({
            type: types.COMPLAIN_RECORD_DETAIL_MODAL,
            data: data,
            isShow: isShow
        })
    }
}

export function saveComplainRecord(data){
    return function(dispatch){
        let url = iportal + "/complainRecord/save.json";
        return fetch(url, {
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
            dispatch(complainRecordList(0, 10, ""));
            dispatch({type:types.COMPLAIN_RECORD_ADD_FORM_MODAL, isShow:false});
            dispatch({type: VALID_FORM_INIT});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            dispatch({type: VALID_FORM_INIT});
        });
    }
}


