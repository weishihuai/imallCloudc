import * as types from "../constants/ActionTypes";
import {formatData} from "../../../../common/redux-form-ext";
import {showErrorMsg} from "../../../../common/common";
import {VALID_FORM_INIT} from "../../../../common/validForm/constants/ActionTypes";


export function changeAddFormState(isOpen) {
    return function (dispatch) {
        if(!isOpen){
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type:types.GOODS_CATEGORY_ADD_FORM_STATE,
            data:isOpen
        })
    }
}

export function changeUpdateFormState(isOpen) {
    return function (dispatch) {
        if(!isOpen){
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type:types.GOODS_CATEGORY_UPDATE_FORM_STATE,
            data:isOpen
        })
    }
}

export function saveCurrentId(id) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_CATEGORY_CURRENT_ID,
            data:id
        })
    }
}

export function saveIdAndOpenAddForm(id) {
    return function (dispatch) {
        dispatch(saveCurrentId(id))
        dispatch(changeAddFormState(true))
    }
}

export function changeConfirmState(isOpen, id) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_CATEGORY_DELETE_VIEW_STATE,
            data:isOpen,
            id:id
        })
    }
}

export function categoryList(){
    return function (dispatch) {
        const empty = {
            type: types.PRODUCT_SALES_CATEGORY_LIST,
            data: []
        };
        dispatch(empty);
        return fetch(iportal+'/goodsCategory/listRoots.json', {mode: 'cors', credentials: 'include', method: 'get'})
            .then(function(response){
                if (response.status >= 200 && response.status < 300) {
                    return response
                }
                else if(response.status==302){

                }
                else {
                    var error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function(response) {
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PRODUCT_SALES_CATEGORY_LIST,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function saveOrUpdate(data,isAdd){
    return function(dispatch){
        let url =iportal + "/goodsCategory/save.json";
        if(!isAdd){
            url = iportal + "/goodsCategory/update.json";
            isAdd = false;
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
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch({type: VALID_FORM_INIT});
            if(isAdd){
                dispatch(changeAddFormState(false))
            }else {
                dispatch(changeUpdateFormState(false))
            }
            dispatch(categoryList());
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}



export function findGoodsCategoryDetailAndUpdateForm(id) {
    return function (dispatch) {
        return fetch(iportal + '/goodsCategory/findOne.json?id='+id, {
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
                    type: types.GOODS_CATEGORY_DETAIL_DATA,
                    data: json
                });
                dispatch(changeUpdateFormState(true));
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function updateDelete(id){
    return function(dispatch){
        const url =iportal + "/goodsCategory/updateDelete.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(id))
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(changeConfirmState(false,""));
            dispatch(categoryList());
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}


