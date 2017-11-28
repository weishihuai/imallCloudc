import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext';

export function drugCombinationCategorySetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.DRUG_COMBINATION_CATEGORY_SEARCH_PARAMS,
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
            type: types.DRUG_COMBINATION_CATEGORY_ADD_MODULE,
            isShowAdd: params
        })
    }
}

/**
 * 新增
 * @param data
 * @param actions
 * @param params
 * @returns {Function}
 */
export function saveOrUpdate(data, actions, params){
    let isAdd;
    let url;
    if(data.id===undefined) {//新增
        isAdd = true;
        url = iportal + "/drugCombinationCategory/save.json";
    }else{//更新
        isAdd = false;
        url = iportal + "/drugCombinationCategory/update.json";
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
                dispatch({
                    type: types.DRUG_COMBINATION_CATEGORY_ADD_MODULE
                })
            }else{
                dispatch({
                    type: types.DRUG_COMBINATION_CATEGORY_EDIT_MODULE,
                    data: {}
                })
            }

            let params = {
                categoryNm:""
            };

            actions.drugCombinationCategoryList(params, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}


export function drugCombinationCategoryList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/drugCombinationCategory/query.json?categoryNm='+ params.categoryNm + '&page=' + page + '&size=' + size , {
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
                type: types.DRUG_COMBINATION_CATEGORY_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//*搜索组件回调固定方法*/
export function drugCombinationCategorySearch(params) {
    return function (dispatch) {
        dispatch(drugCombinationCategorySetSearchParams(params));
        dispatch(drugCombinationCategoryList(params, 0, params.size));
    }
}

/**
 * 编辑
 * @param isShowEdit
 * @param record
 * @returns {Function}
 */
export function showEditForm( isShowEdit,record={}){
    return function (dispatch) {
        dispatch({
            type: types.DRUG_COMBINATION_CATEGORY_EDIT_MODULE,
            isShowEdit: isShowEdit,
            data: record
        })
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
            type: types.DRUG_COMBINATION_CATEGORY_DEL_MODULE,
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
        return fetch(iportal + '/drugCombinationCategory/delete.json', {
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
            let params =  Object.assign({}, params, {
                categoryNm:""
            });
            dispatch(drugCombinationCategoryList(params, params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function checkByCategoryNm(values, dispatch, props, blurredField) {
    return new Promise((resolve, reject) => {
        props.asyncErrorValidMessageFunction("");
        if(values.categoryNm!==undefined && values.categoryNm!==null && $.trim(values.categoryNm)!==''){
            const id = values.id ? values.id : '';
            fetch(iportal + '/drugCombinationCategory/checkByCategoryNm.json?id=' + id + '&categoryNm=' + values.categoryNm, {
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
                if(json){
                    reject({categoryNm: "分类名称已经存在"});
                    props.asyncErrorValidMessageFunction("分类名称已经存在")
                }else{
                    resolve();
                    props.asyncErrorValidMessageFunction("");
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    })
}