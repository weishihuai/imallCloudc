import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../common/validForm/constants/ActionTypes";
import {formatData} from "../../../../common/redux-form-ext";
import {showErrorMsg} from '../../../../common/common';
/**
 * 列表
 * 列表在更新,删除操作后,可直接更新页面,所以要为参数字段设置默认值
 * @param params
 * @param page
 * @param size
 * @returns {Function} 更新列表数
 */

export function shopList(params, page, size) {
    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('entNm', params.entNm || "")
            .set('isEnable', params.isEnable || "")
            .set('startTimeString', params.startTimeString || "")
            .set('endTimeString', params.endTimeString || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/shop/query.json' + paramUrl, {
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
                    type: types.SHOP_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}


//保存 状态 是否开启
export function updateShopEnable(data = {},params) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (data.id == undefined || data.id == null || isNaN(data.id)) {
            return;
        }
        var url = iportal + "/shop/updateShopState.json?id=" + data.id + "&state=" + data.state;
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
            dispatch(shopList(params, params.page, params.size));
            dispatch(showIsEnableState());
            dispatch(initValue());
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
//保存 修改密码
export function updatePassword(data = {}, params) {
    require("jquery-md5");
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        if (data.id == undefined || data.id == null || isNaN(data.id)) {
            return;
        }
        let passwordMd5 = $.md5(data.password);
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
            dispatch(shopList(params, params.page, params.size));
            dispatch(initValue());
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
            type: types.SHOP_SEARCH_PARAMS,
            data: params
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
            type: types.SHOP_LIST_IS_ENABLE,
            isEnableObject: isEnableObject,
            isEnable: isEnable
        });
    }
}
//显示 新增
export function showAddForm(addState = false) {
    return function (dispatch) {
        dispatch({
            type: types.SHOP_LIST_ADD_MODEL,
            addState: addState,
        })
    }
}
//初始 todos 数据
export function initValue() {
    return function (dispatch) {
        dispatch({
            type: types.SHOP_SET_INIT_VALUE,
        })
    }
}
//修改 资质文件
export function updateSHOPCERFILE(shopCertificatesFileVoList) {
    return function (dispatch) {
        dispatch({
            type: types.SHOP_SET_CERTIFICATES_FILE_DATA,
            shopCertificatesFileVoList: shopCertificatesFileVoList

        })
    }
}
//显示 详情
export function showDetail(detailState=false,id,orgId) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return  dispatch(findShopDetail(id,true,orgId));
        }
        return dispatch({
            type:types.SHOP_LIST_DETAIL_MODEL,
            detailState:detailState
        });
    }
}

//显示 修改密码
export function showUpdatePassword(isUpdatePassWord=false,id,orgId) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return  dispatch(findPasswordObject(id,orgId));
        }
        return dispatch({
            type:types.SHOP_SET_PASSWORD_STATE,
            isUpdatePassWord:isUpdatePassWord
        });
    }
}

//获取 详情 编辑 对象
export function findShopDetail(id,isDetail,orgId) {

    return function (dispatch) {
        if (id == undefined || id == null || isNaN(id)) {
            return;
        }
        return fetch(iportal + '/shop/findById.json?id=' + id+"&orgId="+orgId, {
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
                    type: types.SHOP_SET_DETAIL_OBJECT,
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
//获取 修改密码 编辑 对象
export function findPasswordObject(shopId,orgId) {

    return function (dispatch) {
        if (shopId == undefined || shopId == null || isNaN(shopId)) {
            return;
        }
        return fetch(iportal + '/sysUser/findByShopId.json?shopId=' + shopId + '&orgId='+orgId, {
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
                const isUpdatePassWordObject=Object.assign({},json,{
                    userId:json.id
                })
                 dispatch({
                    type: types.SHOP_SET_PASSWORD_OBJECT,
                     isUpdatePassWordObject: isUpdatePassWordObject
                });

                return dispatch(showUpdatePassword(true));

            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
//显示 编辑
export function showUpdate(editState=false,id,orgId) {
    return function(dispatch){
        if(id!=undefined&&!isNaN(id)){
            return  dispatch(findShopDetail(id,false,orgId));
        }
        return dispatch({
            type:types.SHOP_LIST_UPDATE_MODEL,
            editState:editState
        });
    }
}
//保存 编辑 新增
export function saveAndUpdateData(data, idAdd = false,params) {
    return function (dispatch) {
        if (idAdd) {
            var url = iportal + "/shop/save.json";

        } else {
            var url = iportal + "/shop/update.json";

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
            dispatch(initValue());
            dispatch(shopList(params, params.page, params.size));


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
                return dispatch({
                    type: types.SHOP_FIND_BUSINESS_RANGE,
                    businessRangeAllData: json
                });

            })
            .catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('查看详情失败', ex)
            });
    }
}
