import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext';

export function drugCombinationSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_COMBINATION_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 新增窗口显示与隐藏
 * @param params
 * @returns {Function}
 */
export function showAddForm(params){
    return function (dispatch) {
        dispatch({
            type: types.DRUG_COMBINATION_ADD_MODULE,
            isShowAdd: params
        })
    }
}

/**
 * 编辑窗口显示与隐藏
 * @param isShowEdit
 * @param id
 * @returns {Function}
 */
export function showEditForm(isShowEdit, id={}) {
    return function(dispatch){
        if(isShowEdit) {
            let url = iportal + "/platform/drugCombination/findById.json?id="+id;
            return fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(function(response){
                if (response.status >= 200 && response.status < 300) {
                    return response
                }else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function(json){
                dispatch({
                    type: types.DRUG_COMBINATION_EDIT_MODULE,
                    isShowEdit: isShowEdit,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }else{
            dispatch({
                type: types.DRUG_COMBINATION_EDIT_MODULE,
                isShowEdit: isShowEdit
            });
        }
    }
}

/**
 * 详情
 * @param id
 * @returns {Function}
 */
export function showDetail(isShowDetail, id={}){
    if(isShowDetail==true){
        let url = iportal + "/platform/drugCombination/findById.json?id="+id;
        return function(dispatch){
            return fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(function(response){
                if (response.status >= 200 && response.status < 300) {
                    return response
                }else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function(json){
                dispatch({
                    type: types.DRUG_COMBINATION_DETAIL_MODULE,
                    isShowDetail: isShowDetail,
                    record: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function(dispatch){
            dispatch({
                type: types.DRUG_COMBINATION_DETAIL_MODULE,
                isShowDetail: isShowDetail
            });
        }
    }
}

/**
 * 新增或更新
 * @param data
 * @param actions
 * @param params
 * @returns {Function}
 */
export function saveOrUpdate(data, actions, params){
    let url;
    let isAdd = false;
    if(data.id){
        url = iportal + "/platform/drugCombination/update.json";
    }else{
        isAdd = true;
        url = iportal + "/platform/drugCombination/save.json";
    }

    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
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
            if(isAdd){
                dispatch(showAddForm(false));
            }else{
                dispatch(showEditForm(false));
            }

            let data = {
                drugCombinationCategoryId:"",  //联合用药分类 ID
                disease:"",  //病症
                symptom:"",  //症状
                commonSense:""  //常识判断
            };

            actions.drugCombinationList(data, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}


export function drugCombinationList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/platform/drugCombination/query.json?drugCombinationCategoryId='
            + params.drugCombinationCategoryId + '&disease=' + params.disease + '&symptom='
            + params.symptom + '&commonSense=' + params.commonSense + '&page=' + page + '&size=' + size , {
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
                type: types.DRUG_COMBINATION_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//*搜索组件回调固定方法*/
export function drugCombinationSearch(params) {
    return function (dispatch) {
        dispatch(drugCombinationSetSearchParams(params));
        dispatch(drugCombinationList(params, 0, params.size));
    }
}

/**
 * 显示删除确认框
 * @param isShowConfirm
 * @param id
 * @returns {Function}
 */
export function showDelConfirm(isShowConfirm, id=null) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_COMBINATION_DELETE_MODULE,
            isShowConfirm: isShowConfirm,
            id: id
        });
    }
}

/**
 * 删除
 * @param id
 */
export function del(id, params){
    return function (dispatch) {
        dispatch(showDelConfirm(false));
        return fetch(iportal + '/platform/drugCombination/delete.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(id))
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
            let data = {
                drugCombinationCategoryId:"",  //联合用药分类 ID
                disease:"",  //病症
                symptom:"",  //症状
                commonSense:""  //常识判断
            };
            dispatch(drugCombinationList(data, params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function initCategories(callbackFun = ()=>{}){
    return function (dispatch) {
        return fetch(iportal + '/platform/drugCombination/listAllCategory.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'GET',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
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
                type: types.DRUG_COMBINATION_CATEGORY_MODULE,
                categories: json
            });

            dispatch(callbackFun);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}