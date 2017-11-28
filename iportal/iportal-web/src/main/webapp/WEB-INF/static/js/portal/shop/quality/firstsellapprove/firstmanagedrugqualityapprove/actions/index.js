import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";
import {formatData} from '../../../../../../common/redux-form-ext'
import {VALID_FORM_INIT} from "../../../../../../common/validForm/constants/ActionTypes";
export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_SET_PARAMS,
            data: params
        });
    }
}

export function changeFirstManageDrugQualityApproveFormState(isOpen) {
    return function (dispatch) {
        dispatch({
            type: types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_FORM_STATE,
            data: isOpen
        });
    }
}

export function changeFirstManageDrugQualityApproveDetail(isOpen) {
    return function (dispatch) {
        dispatch({
            type: types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_DETAIL,
            data: isOpen
        });
    }
}

export function getApproveInfAndOpenApproveForm(id){
    const url ="/firstManageDrugQualityApproveInf/findByFirstManageDrugQualityApproveId.json";
    return function (dispatch) {
        return fetch(iportal + url + '?id='+id , {
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
                type: types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_INF,
                data: Object.assign({},json,{
                    firstManageDrugQualityApproveId:id
                })
            });
            dispatch(changeFirstManageDrugQualityApproveFormState(true))
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}


export function getFirstManageDrugQualityApproveDetail(id){
    const url ="/firstManageDrugQualityApprove/findOne.json";
    return function (dispatch) {
        return fetch(iportal + url + '?id='+id , {
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
                type: types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_DETAIL_DATA,
                data: json
            });
            dispatch(changeFirstManageDrugQualityApproveDetail(true))
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}


export function approveFirstManageDrugQualityInf(data,params,page){
    return function(dispatch){
        const url = "/firstManageDrugQualityApproveInf/approveFirstManageDrugQualityInf.json";
        return fetch(iportal+url, {
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
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch({type: VALID_FORM_INIT});
            dispatch(changeFirstManageDrugQualityApproveFormState(false));
            dispatch(query(params,params.page,page.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function query(params, page, size) {
    const url ="/firstManageDrugQualityApprove/query.json";
    return function (dispatch) {
        return fetch(iportal + url + '?approveStateCode='+ params.approveStateCode +'&keyWords=' + params.keyWords+'&fromDateString=' + params.fromDateString+'&toDateString=' + params.toDateString+'&page=' + page + '&size=' + size , {
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
                type: types.FIRST_MANAGE_DRUG_QUALITY_APPROVE_LIST,
                data: json
            });
        }).catch(function (ex) {
            showErrorMsg(ex.response);
        });
    }
}

