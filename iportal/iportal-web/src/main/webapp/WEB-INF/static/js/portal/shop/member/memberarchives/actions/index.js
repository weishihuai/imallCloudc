import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../common/common'
import {formatData} from '../../../../../common/redux-form-ext'

/*会员档案列表*/
export function memberList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/member/list.json?' + '&searchFields=' + (params.searchFields || "") + '&name=' + (params.name || "") + '&createDateBeginString=' + (params.createDateBeginString || "") + '&createDateEndString=' + (params.createDateEndString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.MEMBER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*设置参数*/
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.MEMBER_SEARCH_PARAMS,
            data: params
        })
    }
}

/*编辑窗体*/
export function memberEditModel(isShow, data) {
    return function (dispatch) {
        if (!isShow) {
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type: types.MEMBER_LIST_EDIT_MODEL,
            isShow: isShow,
            data: data
        });
    }
}

/*新增窗体*/
export function memberAddModel(isAddShow) {
    return function (dispatch) {
        if (!isAddShow) {
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type: types.MEMBER_LIST_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

//更新会员档案
export  function memberUpdateData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if(data.id != undefined){
            var  url = iportal + "/member/update.json?id=" + data.id;
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
            dispatch(memberEditModel(false));
            dispatch(memberList(params, params.page, params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//新增会员档案
export  function memberAddData(data,params){
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        var  url = iportal + "/member/save.json";
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
            dispatch(memberAddModel(false));
            dispatch(memberList(params, params. page,params.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function memberDetailModal(isDetailShow,id) {
    return function (dispatch) {
        dispatch({
            type: types.MEMBER_SHOP_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            id:id
        });
    };
}

/*查看详情*/
export function memberDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/member/findById.json?id='+id, {
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
                    type: types.MEMBER_SHOP_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
