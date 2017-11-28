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

export function sysUserList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('shopNm', params.shopNm || "")
            .set('name', params.name || "")
            .set('mobile', params.mobile || "")
            .set('isEnable', params.state || "")
            .set('startTimeString', params.startTimeString || "")
            .set('endTimeString', params.endTimeString || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/sysUser/list.json' + paramUrl, {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
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
                    type: types.SYS_USER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}


//设置搜索参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.SYS_USER_SEARCH_PARAMS,
            data: params
        })
    }
}


//显示 新增
export function showAddForm(addState = false) {
    return function (dispatch) {
        dispatch({
            type: types.SYS_USER_LIST_ADD_MODEL,
            addState: addState,
        })
    }
}

//显示 详情
export function showDetail(state=false,id) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return  dispatch(findDetail(id,true));
        }
        return dispatch({
            type:types.SYS_USER_LIST_DETAIL_MODEL,
            detailState:state
        });
    }
}

//显示 修改密码
export function showUpdatePassword(isUpdatePassWord=false,id="") {
    return function(dispatch){
        return dispatch({
            type:types.SYS_USER_SET_PASSWORD_STATE,
            isUpdatePassWord:isUpdatePassWord,
            selectId:id
        });
    }
}
//设置 多选
export function setSelectIds(selectIds) {
    return function(dispatch){
        return dispatch({
            type:types.SYS_USER_SET_SELECT_IDS,
            selectIds:selectIds,

        });
    }
}
//设置 enbanle弹窗状态
export function setEnableFormState(enableFormState) {
    return function(dispatch){
        return dispatch({
            type:types.SYS_USER_SET_ENABLE_FORM_STATE,
            enableFormState:enableFormState,

        });
    }
}
//设置  是否禁用按钮
export function setIsEnable(isEnable) {
    return function(dispatch){
        return dispatch({
            type:types.SYS_USER_SET_IS_ENABLE,
            isEnable:isEnable,

        });
    }
}
//保存 修改密码
export function updatePassword(data = {},listSearchParams) {
    require("jquery-md5");
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        let passwordMd5 = $.md5(data.password);
        if (data.id == undefined || data.id == null || isNaN(data.id)) {
            return;
        }
        let newData={
            ids:[data.id],
            password: passwordMd5
        }
        var url = iportal + "/sysUser/modifyPasswordOne.json?id="+data.id+"&password="+passwordMd5;
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(newData))
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
            dispatch(showUpdatePassword());
            dispatch(sysUserList(listSearchParams,listSearchParams.page, listSearchParams.size));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
//保存 批量修改状态
export function updateSelectIdsState(data = {},listSearchParams) {

    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});

        if(data.ids==undefined||data.ids.length==0){
            return;
        }

        var url = iportal + "/sysUser/updateIsEnable.json";
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
            dispatch(sysUserList(listSearchParams, listSearchParams.page, listSearchParams.size));
            dispatch(setSelectIds([]));
            dispatch(setEnableFormState(false));

        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
//获取 详情 编辑 对象
export function findDetail(id,isDetail) {

    return function (dispatch) {
        if (id == undefined || id == null || isNaN(id)) {
            return;
        }
        return fetch(iportal + '/sysUser/findById.json?id=' + id, {
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
                    type: types.SYS_USER_SET_DETAIL_OBJECT,
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
            type:types.SYS_USER_LIST_UPDATE_MODEL,
            editState:state
        });
    }
}
//保存 编辑 新增
export function saveAndUpdateData(data, idAdd = false,listSearchParams) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (idAdd) {
            var url = iportal + "/sysUser/save.json";

        } else {
            var url = iportal + "/sysUser/update.json";

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

            if (idAdd) {
                dispatch(showAddForm())
            } else {
                dispatch(showUpdate());

            }
            dispatch(sysUserList(listSearchParams, listSearchParams.page, listSearchParams.size));

        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex)
        });

    }
}
//获取 岗位
export function findByAvailableAndDictType() {

    return function (dispatch) {
        return fetch(iportal + '/sysJob/findByOrgId.json', {
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

                    return dispatch({
                        type: types.SYS_USER_FIND_BUSINESS_CATEGORY,
                        allJobs: json
                    });
            })
            .catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('查看详情失败', ex)
            });
    }
}

export function sysLogList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('tableKey', params.tableKey || "")
            .set('objectId', params.userId || "")
            .set('searchText', params.searchText || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/sysLogData/findPageByTableKey.json' + paramUrl, {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
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
                    type: types.SYS_USER_LOG_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function sysLogDataList(logDataId) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('id', logDataId)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/sysLogData/findOne.json' + paramUrl, {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
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
                    type: types.SYS_USER_LOG_DATA_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

export function logListModel(isShow, userId) {
    return function (dispatch) {
        return dispatch({
            type: types.SYS_USER_LOG_LIST_MODEL_STAT_CHANGE,
            data:{
                isShow: isShow,
                userId:userId
            }
        });
    };
}

export function logDataListModel(isShow, logDataId) {
    return function (dispatch) {
        return dispatch({
            type: types.SYS_USER_LOG_DATA_LIST_MODEL_STAT_CHANGE,
            data:{
                isShow: isShow,
                logDataId: logDataId
            }
        });
    };
}

