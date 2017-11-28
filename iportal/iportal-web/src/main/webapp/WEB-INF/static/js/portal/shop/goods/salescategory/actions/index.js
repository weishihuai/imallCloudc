import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext';

export function salesCategoryListSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.SALES_CATEGORY_SEARCH_PARAMS,
            data: params
        })
    }
}

export function salesCategoryList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/salesCategory/query.json?categoryName='+ params.categoryName +'&page=' + page + '&size=' + size , {
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
                type: types.SALES_CATEGORY_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//*搜索组件回调固定方法*/
export function salesCategorySearch(params) {
    return function (dispatch) {
        dispatch(salesCategoryListSetSearchParams(params));
        dispatch(salesCategoryList(params, 0, params.size));
    }
}

/**
 * 新增窗口显示与隐藏
 * @param params
 * @returns {Function}
 */
export function showCategoryAddForm(params){
    return function (dispatch) {
        dispatch({
            type: types.SALES_CATEGORY_ADD_MODULE,
            isShowAdd: params
        })
    }
}

/**
 * 新增或更新销售分类
 * @param data
 * @returns {Function}
 */
export function salesCategorySaveUpdateData(data, actions, params){
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        let isAdd;
        let url;
        if(data.id!==undefined){//更新
            isAdd = false;
            url = iportal + "/salesCategory/update.json";
        }else{//新增
            isAdd = true;
            url = iportal + "/salesCategory/save.json";
        }

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
                    type: types.SALES_CATEGORY_ADD_MODULE
                })
            }else{
                dispatch({
                    type: types.SALES_CATEGORY_UPDATE_MODULE,
                    data: {}
                })
            }


            actions.salesCategoryList(params, params.page, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 编辑
 * @param isShowEdit
 * @param id
 * @returns {Function}
 */
export function showCategorySaveEditForm(isShowEdit,id){
    if(isShowEdit){
        return function(dispatch){
            return fetch(iportal + "/salesCategory/findById.json?id=" + id, {
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
                    type: types.SALES_CATEGORY_UPDATE_MODULE,
                    isShowEdit: isShowEdit,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function (dispatch) {
            dispatch({
                type: types.SALES_CATEGORY_UPDATE_MODULE,
                isShowEdit: isShowEdit,
                data: {}
            })
        }
    }
}

/**
 * 销售分类异步校验
 * @param values
 * @param props
 * @returns {Function}
 */
export function checkSalesCategory(values, dispatch, props, blurredField) {
    return new Promise((resolve, reject) => {
        props.asyncErrorValidMessageFunction("");
        if(values.categoryName!==undefined && values.categoryName!==null && $.trim(values.categoryName)!==''){
            const id = values.id ? values.id : '';
            fetch(iportal + '/salesCategory/checkSalesCategory.json?id=' + id + '&categoryName=' + values.categoryName, {
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
                    reject({categoryName: "分类名称已经存在"});
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