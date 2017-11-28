import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../common/common';
import {formatData} from '../../../../../common/redux-form-ext'

export function changeAddGoodFormState(isOpen) {
    return function (dispatch) {
        if(!isOpen){
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type:types.GOODS_ADD_FORM_STATE,
            isOpen:isOpen
        });
    }
}

export function changeEnableFormState(isOpen) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_LIST_ENABLE_FORM_STATE,
            data:isOpen
        })
    }
}

export function setEnableFormInitData(data) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_LIST_ENABLE_FORM_INIT_DATA,
            data:data
        });
        dispatch(changeEnableFormState(true))
    }
}

export function changeGoodsRecordListState(isOpen,id) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_RECORD_LIST_STATE,
            isOpen:isOpen,
            id:id
        });
    }
}

export function queryRecordList(page, size, id) {
    return function (dispatch) {
        return fetch(iportal + '/goodsEnableRecord/query.json?id=' + id +"&page="+ + page + '&size=' + size, {
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
                    type: types.GOODS_RECORD_LIST_DATA,
                    data: json
                });
                dispatch(changeGoodsRecordListState(true,id))
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function changeUpdateGoodFormState(isOpen) {
    return function (dispatch) {
        if(!isOpen){
            dispatch({type: VALID_FORM_INIT});
        }
        dispatch({
            type:types.GOODS_UPDATE_FORM_STATE,
            isOpen:isOpen
        });
    }
}

export function changeGoodsDocSelectListState(isOpen) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_DOC_SELECT_LIST_STATE,
            isOpen:isOpen
        });
    }
}

export function changeGoodsDetailViewState(isOpen) {
    return function (dispatch) {
        dispatch({
            type:types.GOODS_DETAIL_VIEW_STATE,
            isOpen:isOpen
        });
    }
}


export function getGoodsDocDetail(id,callback = ()=> {}) {
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
                    type: types.GOODS_DOC_SELECT_DETAIL,
                    data: json
                });
                dispatch(callback);
                dispatch(changeGoodsDocSelectListState(false));
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
export function queryGoodsDocList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/goodsDoc/list.json?docGoodsNm=' + params.docGoodsNm + '&docApproveNumber=' + params.docApproveNumber + '&docProduceManufacturer=' + params.docProduceManufacturer + '&approveStateCode=PASS_APPROVE' +'&page=' + page + '&size=' + size, {
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
                    type: types.GOODS_DOC_SELECT_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function getGoodsDetail(id,isUpdate) {
    return function (dispatch) {
        return fetch(iportal + '/goods/findOne.json?id='+id, {
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
                    type: types.GOODS_DETAIL_DATA,
                    data: json
                });
                if(isUpdate){
                    dispatch(changeUpdateGoodFormState(true));
                }else{
                    dispatch(changeGoodsDetailViewState(true));
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}



export function queryGoodsList(page, size, params) {
    return function (dispatch) {
        return fetch(iportal + '/goods/list.json?storageSpaceId=' + params.storageSpaceId + '&isEnable=' + params.isEnable+ '&approveStateCode=' + params.approveStateCode + '&keyWords=' + params.keyWords + '&fromDateString=' + params.fromDateString + '&toDateString=' + params.toDateString+ '&page=' + page + '&size=' + size, {
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

export function getStorageSpaceIdList(callback = ()=> {}) {
    return function (dispatch) {
        return fetch(iportal + '/storageSpace/listForGoodsList.json', {
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
                    type: types.GOODS_LIST_STORAGE_SPACE_LIST,
                    data: json
                });
                dispatch(callback);
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function saveOrUpdateGoods(data, params,id){
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        let url =iportal + "/goods/save.json";
        let isAdd = true;
        if(id){
            url = iportal + "/goods/update.json";
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
                dispatch(changeAddGoodFormState(false))
                dispatch(queryGoodsList(params.page,params.size,params))
            }else{
                dispatch(changeUpdateGoodFormState(false))
            }

            dispatch(queryGoodsList(params.page,params.size,params))
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}


export function getSalesCategoryList() {
    return function (dispatch) {
        return fetch(iportal + '/salesCategory/listForGoodsAdd.json', {
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
                    type: types.GOODS_ADD_FORM_SALES_CATEGORY_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function updateIsEnable(data,params) {
    return function(dispatch){
    dispatch({type: VALID_FORM_INIT});
        let url = "";
        if(data.operationState=="N"){
            url = iportal + '/goods/updateStartUsing.json';
        }else if(data.operationState=="Y"){
            url = iportal + '/goods/updateBlockUp.json';
        }else {
            return ;
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
            body: JSON.stringify(data)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){

            dispatch(changeEnableFormState(false));
            dispatch(queryGoodsList(params.page,params.size,params))
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
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
                    type: types.GOODS_ADD_FORM_DOSAGE_FORM_LIST,
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
export function setDocSearchParam(params) {
    return function (dispatch) {
        dispatch({
            type: types.GOODS_DOC_SELECT_SET_SEARCH_PARAM,
            data: params
        })
    }
}


export function exportExcel(params) {
    return function (dispatch) {
        require('queryObject');
        const paramUrl = $.query
            .set('keyWords', params.keyWords || "")
            .set('isEnable', params.isEnable || "")
            .set('fromDateString', params.fromDateString || "")
            .set('toDateString', params.toDateString || "")
            .set('storageSpaceId', params.storageSpaceId || "")
            .set("rd", Math.random())
            .toString();
        return fetch(iportal + '/goods/exportExcel.json' + paramUrl, {
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
        return fetch(iportal + '/goods/importExcel.json' + paramUrl, {
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
                    dispatch(queryGoodsList(params.page,params.size,params))
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}




