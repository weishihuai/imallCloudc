import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../common/validForm/constants/ActionTypes'
import {formatData} from '../../../../common/redux-form-ext'
import {showErrorMsg} from '../../../../common/common';

/**
 * 列表
 * 列表在更新,删除操作后,可直接更新页面,所以要为参数字段设置默认值
 * @param params
 * @param page
 * @param size
 * @returns {Function} 更新列表数
 */

export function supplierList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('pinyin', params.pinyin || "")
            .set('state', params.state || "")
            .set('approveStateCode', params.approveStateCode || "")
            .set('startTimeString', params.startTimeString || "")
            .set('endTimeString', params.endTimeString || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/supplier/query.json' + paramUrl, {
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


export function exportExcel(params) {
    return function (dispatch) {
        require('queryObject');
        const paramUrl = $.query
            .set('pinyin', params.pinyin || "")
            .set('supplierNm', params.supplierNm || "")
            .set('state', params.state || "")
            .set('startTimeString', params.startTimeString || "")
            .set('endTimeString', params.endTimeString || "")
            .set("rd", Math.random())
            .toString();
        return fetch(iportal + '/supplier/exportExcel.json' + paramUrl, {
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

export function importExcel(params) {
    return function (dispatch) {
        require('queryObject');
        const paramUrl = $.query
            .set('fileId', params.fileId || "")
            .set("rd", Math.random())
            .toString();
        return fetch(iportal + '/supplier/importExcel.json' + paramUrl, {
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
                    dispatch(supplierList("", 0, 10));
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}


//保存 状态 是否开启
export function updateSupplierEnable(data = {},params) {
    return function (dispatch) {
    dispatch({type: VALID_FORM_INIT});
        if (data.id == undefined || data.id == null || isNaN(data.id)) {
            return;
        }
        var url = iportal + "/supplier/updateSupplierState.json?id=" + data.id + "&state=" + data.state;
        return fetch(url, {
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
            dispatch(showIsEnableState())
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
//设置文件
export function setFieldObjs(fileMngs) {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_SET_UPDATE_FIELDS,
            fileMngs: fileMngs
        })
    }
}
//显示 启用窗口
export function showIsEnableState(isEnableObject = {}) {
    return function (dispatch) {
        if (isEnableObject.id == undefined || isEnableObject.id == "" || isNaN(isEnableObject.id)) {
            var isEnable = false;
        } else {
            var isEnable = true;
        }
        dispatch({
            type: types.SUPPLIER_LIST_IS_ENABLE,
            isEnableObject: isEnableObject,
            isEnable: isEnable
        });
    }
}
//显示 新增
export function showAddForm(addState = false) {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_LIST_ADD_MODEL,
            addState: addState,
        })
    }
}
//修改 资质文件
export function updateSUPCERFILE(supplierCertificatesFileList) {
    return function (dispatch) {
        dispatch({
            type: types.SUPPLIER_SET_CERTIFICATES_FILE_DATA,
            supplierCertificatesFileList: supplierCertificatesFileList

        })
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
        return fetch(iportal + '/supplier/findById.json?id=' + id, {
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
//显示 编辑
export function showUpdate(state=false,id) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return dispatch(findSupplierDetail(id,false));
        }
        return dispatch({
            type:types.SUPPLIER_LIST_UPDATE_MODEL,
            editState:state
        });
    }
}
//保存 编辑 新增
export function saveAndUpdateData(data, idAdd = false,params) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (idAdd) {
            var url = iportal + "/supplier/save.json";

        } else {
            var url = iportal + "/supplier/update.json";

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
            dispatch({type: VALID_FORM_INIT});
            if (idAdd) {
                dispatch(showAddForm())
            } else {
                dispatch(showUpdate());
            }
            dispatch(findBySupplierComponent({}));
            dispatch(supplierList(params, params.page, params.size));

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

//设置渲染资质文件页面状态 并且 获取组件对象
export function setDetailObjectAndSetCertificateIsShow(detailObject,state=true) {
    return function (dispatch) {
       if(detailObject!=undefined){
           dispatch({
               type: types.SUPPLIER_SET_DETAIL_OBJECT,
               detailObject: Object.assign({},detailObject,{supplierDocId:detailObject.id})
           });
       }
       return dispatch({
            type: types.SUPPLIER_SET_CERTIFICATES_IS_SHOW,
            certificateIsShow: state
        })

    }
}
export function findBySupplierComponent(detailObject) {
    return function (dispatch) {
         dispatch({
            type: types.SUPPLIER_SET_DETAIL_OBJECT,
            detailObject: detailObject
        });
    }
}