import * as types from "../constants/ActionTypes";
import "whatwg-fetch";
import {VALID_FORM_INIT} from "../../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from '../../../../../../common/common';
import {formatData} from '../../../../../../common/redux-form-ext';

/**
 * 设置搜索参数
 * @param params
 * @returns {Function}
 */
export function facilityAndDeviceAccountsSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.FACILITY_AND_DEVICE_ACCOUNTS_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 弹窗显示与隐藏
 * @param isShow
 * @param formType
 * @param record
 */
export function showOrHidden(isShow, formType, record={}) {
    return function (dispatch) {
        dispatch({
            type: formType,
            [switchIsShow(formType)]: isShow,
            data: resetRecord(formType, record)
        })
    }
}

function resetRecord(formType, record) {
    switch (formType){
        case types.FACILITY_AND_DEVICE_ACCOUNTS_USE_MODULE:
            return Object.assign({}, record, {
                remark: '',
                facilityAndDeviceAccountsId: record.id
            });
        case types.FACILITY_AND_DEVICE_ACCOUNTS_MAINTAINING_MODULE:
            return Object.assign({}, record, {
                remark: '',
                facilityAndDeviceAccountsId: record.id
            });
    }

    return record;
}

function switchIsShow(formType){
    switch (formType){
        case types.FACILITY_AND_DEVICE_ACCOUNTS_ADD_MODULE:
            return "isShowAdd";
        case types.FACILITY_AND_DEVICE_ACCOUNTS_DETAIL_MODULE:
            return "isShowDetail";
        case types.FACILITY_AND_DEVICE_ACCOUNTS_EDIT_MODULE:
            return "isShowEdit";
        case types.FACILITY_AND_DEVICE_ACCOUNTS_USE_MODULE:
            return "isShowUse";
        case types.FACILITY_AND_DEVICE_ACCOUNTS_MAINTAINING_MODULE:
            return "isShowMaintaining";
    }
}

/**
 * 新增或更新
 * @param data
 * @param actions
 * @param params
 * @returns {Function}
 */
export function saveOrUpdate(data, actions, params){
    let url;
    let isAdd;
    if(data.id===undefined){//新增
        url = iportal + "/facilityAndDeviceAccounts/save.json";
        isAdd = true;
    }else{//更新
        url = iportal + "/facilityAndDeviceAccounts/update.json";
        isAdd = false;
    }

    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
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
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            if(isAdd){
                dispatch({
                    type: types.FACILITY_AND_DEVICE_ACCOUNTS_ADD_MODULE,
                    isShowAdd: false
                });
            }else{
                dispatch({
                    type: types.FACILITY_AND_DEVICE_ACCOUNTS_EDIT_MODULE,
                    isShowEdit: false
                });
            }

            let data = {
                deviceTypeCode:"",  //设备类型代码
                deviceNum:"",       //设备编号
                deviceNm:"",        //设备名称
                responseMan:""      //负责人
            };

            actions.facilityAndDeviceAccountsList(data, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 使用
 * @param data
 * @param actions
 * @param params
 */
export function saveUseRecord(data, actions, params) {
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + "/useRecord/save.json", {
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
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch({
                type: types.FACILITY_AND_DEVICE_ACCOUNTS_USE_MODULE,
                isShowUse: false
            });

            let data = {
                deviceTypeCode:"",  //设备类型代码
                deviceNum:"",       //设备编号
                deviceNm:"",        //设备名称
                responseMan:""      //负责人
            };

            actions.facilityAndDeviceAccountsList(data, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 维护
 * @param data
 * @param actions
 * @param params
 */
export function saveMaintainingRecord(data, actions, params) {
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + "/maintainingRecord/save.json", {
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
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){
            dispatch({
                type: types.FACILITY_AND_DEVICE_ACCOUNTS_MAINTAINING_MODULE,
                isShowMaintaining: false
            });

            let data = {
                deviceTypeCode:"",  //设备类型代码
                deviceNum:"",       //设备编号
                deviceNm:"",        //设备名称
                responseMan:""      //负责人
            };

            actions.facilityAndDeviceAccountsList(data, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 列表
 */
export function facilityAndDeviceAccountsList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/facilityAndDeviceAccounts/query.json?deviceTypeCode=' + params.deviceTypeCode + '&deviceNum=' + params.deviceNum + '&deviceNm='
            + params.deviceNm + '&responseMan=' + params.responseMan + '&page=' + page + '&size=' + size , {
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
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.FACILITY_AND_DEVICE_ACCOUNTS_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 搜索
 * @param params
 * @returns {Function}
 */
export function facilityAndDeviceAccountsSearch(params) {
    return function (dispatch) {
        dispatch(facilityAndDeviceAccountsSetSearchParams(params));
        dispatch(facilityAndDeviceAccountsList(params, 0, params.size));
    }
}

/**
 * 设备号异步校验
 * @param values
 * @param props
 * @returns {Function}
 */
export function checkDeviceNum(values, dispatch, props, blurredField) {
    return new Promise((resolve, reject) => {
        if(values.deviceNum===undefined || values.deviceNum===null || $.trim(values.deviceNum)===''){
            reject({deviceNum: "请输入设备编号"});
            props.asyncErrorValidMessageFunction("请输入设备编号")
        }else{
            const id = values.id ? values.id : '';
            fetch(iportal + '/facilityAndDeviceAccounts/checkDeviceNum.json?id=' + id + '&deviceNum=' + values.deviceNum, {
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
            }).then(function (response) {
                return response.json();
            }).then(function (json) {
                if(json){
                    reject({deviceNum: "设备编号已经存在"});
                    props.asyncErrorValidMessageFunction("设备编号已经存在")
                }else{
                    resolve();
                    props.asyncErrorValidMessageFunction("");
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    })
}




