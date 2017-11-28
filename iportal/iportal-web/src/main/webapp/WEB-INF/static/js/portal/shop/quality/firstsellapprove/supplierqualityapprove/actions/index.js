import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {formatData} from '../../../../../../common/redux-form-ext'
import {showErrorMsg, niftyNoty} from "../../../../../../common/common";
//列表
export function supplierList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('searchValue', params.searchValue || "")
            .set('state', params.state || "")
            .set('startTimeString', params.startTimeString || "")
            .set('endTimeString', params.endTimeString || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/firstManageSupplierQualityApprove/query.json' + paramUrl, {
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
                    type: types.SUPPLIER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//保存 审核
export function updateApproveFormState(approveUserNames,data = {},params) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (approveUserNames.entApproveMan == undefined && approveUserNames.qualityApproveMan == undefined && approveUserNames.purchaseApproveMan == undefined) {
            niftyNoty(("请审核"));
            return;
        }
        var url = iportal + "/firstManageSupplierQualityApproveInf/save.json";
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
            dispatch(supplierList(params, params.page, params.size));
            dispatch(showApproveFormState({}));
            dispatch(initValue())
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
//设置搜索参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_SEARCH_PARAMS,
            data: params
        })
    }
}
//设置设置审核对象
export function setApproNames(approveUserNames) {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_SET_APPRO_NAMES,
            approveUserNames: approveUserNames
        })
    }
}
//设置init数据
export function initValue() {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_SET_INIT_DATA,
        })
    }
}
//获取 审核对象 对象
export function findFirstManageSupplierQualityApproveInf(id) {
    return function (dispatch) {
        if (id == undefined || id == null || isNaN(id)) {
            return;
        }
        return fetch(iportal + '/firstManageSupplierQualityApproveInf/findOne.json?id=' + id, {
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
                return dispatch(showApproveFormState(json));
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//显示 启用窗口
export function showApproveFormState(approveFormStateObject = {}) {
    return function (dispatch) {
        if (approveFormStateObject.id == undefined || approveFormStateObject.id == "" || isNaN(approveFormStateObject.id)) {
            var approveFormState = false;
        } else {
            var approveFormState = true;
        }
        dispatch({
            type: types.SUPPLIER_LIST_IS_ENABLE,
            approveFormStateObject: approveFormStateObject,
            approveFormState: approveFormState
        });
    }
}
//显示 详情
export function showDetail(state=false,id) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return  dispatch(findSupplierDetail(id,true));
        }
        return dispatch({
            type:types.SUPPLIER_LIST_DETAIL_MODEL,
            detailState:state
        });
    }
}
//获取 详情 编辑 对象
export function findSupplierDetail(id,isDetail) {

    return function (dispatch) {
        if (id == undefined || id == null || isNaN(id)) {
            return;
        }
        return fetch(iportal + '/firstManageSupplierQualityApprove/findById.json?id=' + id, {
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
                    type: types.SUPPLIER_SET_DETAIL_OBJECT,
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
//获取 品种
export function findByAvailableAndDictType(data) {

    return function (dispatch) {
        return fetch(iportal + '/sysDictItem/findByAvaiAndDictType.json?dictType=' + data, {
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
            })
            .then(function (json) {
                if (data == "BUSINESS_CATEGORY") {
                    return dispatch({
                        type: types.SUPPLIER_FIND_BUSINESS_CATEGORY,
                        businessCategoryAllData: json
                    });
                } else {
                    return dispatch({
                        type: types.SUPPLIER_FIND_BUSINESS_RANGE,
                        businessRangeAllData: json
                    });
                }

            })
            .catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('查看详情失败', ex)
            });
    }
}
//设置详情对象
export function setDetail(detailObject) {
    return function (dispatch) {
         dispatch({
            type: types.SUPPLIER_SET_DETAIL_OBJECT,
            detailObject: detailObject
        });
    }
}
