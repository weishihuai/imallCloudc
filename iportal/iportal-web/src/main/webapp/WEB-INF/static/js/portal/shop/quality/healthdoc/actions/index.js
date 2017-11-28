import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../common/validForm/constants/ActionTypes'
import {formatData} from '../../../../../common/redux-form-ext'
import {showErrorMsg} from '../../../../../common/common';

/**
 * 获取 列表
 */

export function queryList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('userName', params.userName || "")
            .set('state', params.state || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/staffHealthDoc/queryStaffHealthDoc.json' + paramUrl, {
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
                    type: types.HEALTH_DOC_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//显示 详情
export function showDetail(state=false,id) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return  dispatch(findDetail(id,true));
        }
        return dispatch({
            type:types.HEALTH_DOC_LIST_DETAIL_MODEL,
            detailState:state
        });
    }
}
//设置搜索参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.HEALTH_DOC_SEARCH_PARAMS,
            data: params
        })
    }
}
//设置选中下标
export function setIndex(index) {
    return function (dispatch) {
        dispatch({
            type: types.HEALTH_DOC_SET_INDEX,
            index: index
        })
    }
}
//显示 新增
export function showAddForm(addState = false) {
    return function (dispatch) {
        dispatch({
            type: types.HEALTH_DOC_LIST_ADD_MODEL,
            addState: addState,
        })
    }
}
//获取 详情 编辑 对象
export function findDetail(id,isDetail) {

    return function (dispatch) {
        if (id == undefined || id == null || isNaN(id)) {
            return;
        }
        return fetch(iportal + '/staffHealthDoc/findById.json?id=' + id, {
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
                    type: types.HEALTH_DOC_SET_DETAIL_OBJECT,
                    detailObject: json
                });

                if(isDetail){
                    return dispatch(showDetail(true));
                }else {
                    return dispatch(showUpdate(true));
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//显示 编辑
export function showUpdate(state=false,id) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return dispatch(findDetail(id,false));
        }
        return dispatch({
            type:types.HEALTH_DOC_LIST_UPDATE_MODEL,
            editState:state
        });
    }
}
//保存 编辑 新增
export function saveAndUpdateData(data, idAdd = false,param) {

    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (idAdd) {
            var url = iportal + "/staffHealthDocInf/save.json";

        } else {
            var url = iportal + "/staffHealthDocInf/save.json";

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
            body: JSON.stringify(formatData(data.healthDocList))
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            }
            else if (response.status == 302) {

            }
            else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {

            if (idAdd) {
                dispatch(showAddForm())
            } else {
                dispatch(showUpdate());
            }
            dispatch(queryList(param, param.page, param.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex)
        });

    }
}

export function setDetail(detailObject) {
    return function (dispatch) {
         dispatch({
            type: types.HEALTH_DOC_SET_DETAIL_OBJECT,
            detailObject: detailObject
        });
    }
}