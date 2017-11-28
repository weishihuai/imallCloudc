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

export function queryList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/sysJob/findPage.json' + paramUrl, {
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
                    type: types.SYS_JOB_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//显示 新增
export function showAddForm(addState = false) {
    return function (dispatch) {
        dispatch({
            type: types.SYS_JOB_LIST_ADD_MODEL,
            addState: addState,
        })
    }
}



//设置 多选
export function setSelectIds(selectIds) {
    return function(dispatch){
        return dispatch({
            type:types.SYS_JOB_SET_SELECT_IDS,
            selectIds:selectIds,

        });
    }
}

//获取  当前 已经 保存的 角色id
export function findSysRoleIds(jobId) {
    return function (dispatch) {
        if (jobId == undefined || jobId == null || isNaN(jobId)) {
            return;
        }
        return fetch(iportal + '/sysAuth/findRoleIdsByJobId.json?jobId=' + jobId, {
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
                    type: types.SYS_JOB_SET_SELECT_ROLE_IDS,
                    selectRoleIds: json,
                    selectId:jobId
                });
                dispatch(setRoleFormState(true));

            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//新增当前用户 角色
export function  saveJobRole(jobId,roleIds){
    return function (dispatch) {
        var url = iportal+"/sysAuth/saveJobRole.json?jobId="+jobId+"&roleIds="+roleIds;
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            dispatch(findSysRoleIds(jobId));
            dispatch(queryList("",0,10));
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}
//删除当前用户 角色
export function  deleteJobRole(jobId,roleIds,){
    return function (dispatch) {
        var url = iportal+"/sysAuth/deleteJobRole.json?jobId="+jobId+"&roleIds="+roleIds;
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            dispatch(findSysRoleIds(jobId));
            dispatch(queryList("",0,10));

        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}


//获取 所有 角色列表
export function findAllSysRoles() {
    return function (dispatch) {
        return fetch(iportal + '/sysRole/findByOrgId.json', {
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
                    type: types.SYS_JOB_SET_SYS_ROLES,
                    sysRoles: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//设置  删除 弹窗 状态
export function setDeleteFormState(deleteFormState) {
    return function(dispatch){
        return dispatch({
            type:types.SYS_JOB_SET_DELETE_FORM_STATE,
            deleteFormState:deleteFormState,
        });
    }
}


//设置 选择 角色 弹窗 状态
export function setRoleFormState(roleFormState,selectId) {
    return function(dispatch){
        if(selectId!=undefined&&!isNaN(selectId)){
            return dispatch(findSysRoleIds(selectId))
        }
        return dispatch({
            type:types.SYS_JOB_SET_ROLE_FORM_STATE,
            roleFormState:roleFormState,

        });
    }
}

//保存 资源
export function saveOrDeleteRolePermission(roleId,id,isChecked){
    return function (dispatch) {
        return fetch(iportal+'/sysRolePermission/saveOrDeleteRolePermission.json?roleId='+roleId+'&id=' + id+"&isChecked="+isChecked,{
            credentials: 'same-origin',
            method: 'GET'
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });
    }
}
//保存 菜单
export function saveOrDeleteRoleMenu(roleId,id,isChecked){
    return function (dispatch) {
        return fetch(iportal+'/sysRoleMenu/saveOrDeleteRoleMenu.json?roleId='+roleId+'&id=' + id+"&isChecked="+isChecked , {
            credentials: 'same-origin',
            method: 'GET'
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });
    }
}


//保存 批量删除 状态
export function deleteSelectIdsState(ids = {},listSearchParams) {

    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});

        if(ids==undefined||ids.length==0){
            return;
        }

        var url = iportal + "/sysJob/delete.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formatData(ids))
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
            dispatch(queryList(listSearchParams, 0, 10));
            dispatch(setSelectIds([]));
            dispatch(setDeleteFormState(false));

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
        return fetch(iportal + '/sysJob/findOne?id=' + id, {
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
                    type: types.SYS_JOB_SET_DETAIL_OBJECT,
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
//获取 角色列表
export function findAllRole(id,isDetail) {

    return function (dispatch) {
        if (id == undefined || id == null || isNaN(id)) {
            return;
        }
        return fetch(iportal + '/sysRole/findOne?id=' + id, {
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
                    type: types.SYS_JOB_SET_DETAIL_OBJECT,
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
            type:types.SYS_JOB_LIST_UPDATE_MODEL,
            editState:state
        });
    }
}
//保存 编辑 新增
export function saveAndUpdateData(data, idAdd = false,listSearchParams) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (idAdd) {
            var url = iportal + "/sysJob/save.json";

        } else {
            var url = iportal + "/sysJob/update.json";

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
            dispatch(queryList(listSearchParams, 0, 10));

        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex)
        });

    }
}


