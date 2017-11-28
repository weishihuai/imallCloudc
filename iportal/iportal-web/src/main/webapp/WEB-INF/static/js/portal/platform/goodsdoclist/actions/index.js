import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../common/common';
import {formatData} from '../../../../common/redux-form-ext'
export function changeGoodsDocAddFormState(isOpen) {
    return function (dispatch) {
        if(!isOpen){
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type:types.GOODS_DOC_ADD_FORM_STATE,
            isOpen:isOpen
        });
    }
}

export function changeGoodsDocUpdateFormState(isOpen) {
    return function (dispatch) {
        if(!isOpen){
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type:types.GOODS_DOC_UPDATE_FORM_STATE,
            isOpen:isOpen
        });
    }
}

export function changeConfirmState(isOpen,goodsId) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_DOC_CONFIRM_DELETE_VIEW_STATE,
            data:isOpen,
            goodsId:goodsId
        })
    }
}

export function changeGoodsDocApproveAndDetailState(isOpen) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_DOC_APPROVE_OR_DETAIL_STATE,
            isOpen:isOpen
        });
    }
}

export function getGoodsDocDetail(id,isUpdate) {
    return function (dispatch) {
        return fetch(iportal + '/goodsDoc/findOne.json?id='+id, {
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
                    type: types.GOODS_DOC_DETAIL_DATA,
                    data: json
                });
                if(isUpdate){
                    dispatch(changeGoodsDocUpdateFormState(true));
                }else{
                    dispatch(changeGoodsDocApproveAndDetailState(true));
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function getGoodsCategoryList(callback = ()=> {}) {
    return function (dispatch) {
        return fetch(iportal + '/goodsCategory/findAllCategories.json' , {
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
                    type: types.GOODS_CATEGORY_LIST,
                    data: json
                })
                dispatch(callback);
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}


export function queryGoodsDocList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/goodsDoc/list.json?approveStateCode=' + params.approveStateCode + '&keyWords=' + params.keyWords + '&fromDateString=' + params.fromDateString + '&toDateString=' + params.toDateString+ '&goodsCategoryId=' + params.goodsCategoryId+ '&page=' + page + '&size=' + size, {
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
                    type: types.GOODS_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}


export function setSearchParam(params) {
    return function (dispatch) {
        dispatch({
            type: types.GOODS_LIST_SET_PARAMS,
            params: params
        })
    }
}

export function getDosageFormList() {
    return function (dispatch) {
        return fetch(iportal + '/sysDictItem/findByAvaiAndDictType.json?dictType=DOSAGE_FORM', {
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
                    type: types.GOODS_DOC_GET_DOSAGE_FORM_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}



export function saveOrUpdateGoods(data, params,id){
    return function(dispatch){
        let url =iportal + "/goodsDoc/save.json";
        let isAdd = true;
        if(id){
            url = iportal + "/goodsDoc/update.json";
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
            if(isAdd){
                dispatch(changeGoodsDocAddFormState(false))
            }else{
                dispatch(changeGoodsDocUpdateFormState(false))
            }
            dispatch({type: VALID_FORM_INIT});
            dispatch(queryGoodsDocList(params.page,params.size,params))
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function updateDelete(id,params){
    return function(dispatch){
        const url =iportal + "/goodsDoc/updateDelete.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
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
            dispatch(queryGoodsDocList(params.page,params.size,params));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}


export function updateDenyApprove(id,params){
    return function(dispatch){
        const url =iportal + "/goodsDoc/updateDenyApprove.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(changeGoodsDocApproveAndDetailState(false));
            dispatch(queryGoodsDocList(params.page,params.size,params));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function updatePassApprove(id,params){
    return function(dispatch){
        const url =iportal + "/goodsDoc/updatePassApprove.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch(changeGoodsDocApproveAndDetailState(false));
            dispatch(queryGoodsDocList(params.page,params.size,params));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

export function exportExcel(params) {
    return function (dispatch) {
        require('queryObject');
        const paramUrl = $.query
            .set('keyWords', params.keyWords || "")
            .set('approveStateCode', params.approveStateCode || "")
            .set('fromDateString', params.fromDateString || "")
            .set('toDateString', params.toDateString || "")
            .set('goodsCategoryId', params.goodsCategoryId || "")
            .set("rd", Math.random())
            .toString();
        return fetch(iportal + '/goodsDoc/exportExcel.json' + paramUrl, {
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
                if(json.success){ //导出成功，打开或下载Excel
                    const url = json.msg;
                    console.log(url);
                    const win = window.open(url, '_blank');
                    win.focus();
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function importExcel(fileId, params) {
    return function (dispatch) {
        require('queryObject');
        const paramUrl = $.query
            .set('fileId', fileId || "")
            .set("rd", Math.random())
            .toString();
        return fetch(iportal + '/goodsDoc/importExcel.json' + paramUrl, {
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
                if(!json.success){ //导入失败，打开或下载日志 txt
                    const url = json.msg;
                    console.log(url);
                    const win = window.open(url, '_blank');
                    win.focus();
                }
                else {
                    dispatch(queryGoodsDocList(params.page,params.size,params));
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
