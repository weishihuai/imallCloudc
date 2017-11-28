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
export function prescriptionSetSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.PRESCRIPTION_SEARCH_PARAMS,
            data: params
        })
    }
}

/**
 * 新增窗口显示与隐藏
 * @param isShowAdd
 * @returns {Function}
 */
export function showAddForm(isShowAdd){
    return function (dispatch) {
        dispatch({
            type: types.PRESCRIPTION_REGISTER_ADD_MODULE,
            isShowAdd: isShowAdd
        })
    }
}

/**
 * 文件回显
 * @param files
 * @param addFileList
 * @param delFileList
 * @returns {Function}
 */
export function showFiles(files, addFileList=[], delFileList=[]) {
    return function (dispatch) {
        dispatch({
            type: types.PRESCRIPTION_REGISTER_ADD_FILE_MODULE,
            files: files,
            addFileList: addFileList,
            delFileList: delFileList
        })
    }
}

/**
 * 详情
 * @param id 处方登记 ID
 * @param isShowDetail
 * @returns {Function}
 */
export function showDetail(isShowDetail, id={}){
    if(isShowDetail===true){
        let url = iportal + "/prescriptionRegister/findById.json?id="+id;
        return function(dispatch){
            return fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(function(response){
                if (response.status >= 200 && response.status < 300) {
                    return response
                }else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function(json){
                dispatch({
                    type: types.PRESCRIPTION_DETAIL_MODULE,
                    isShowDetail: isShowDetail,
                    record: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function(dispatch){
            dispatch({
                type: types.PRESCRIPTION_DETAIL_MODULE,
                isShowDetail: isShowDetail
            });
        }
    }
}

/**
 * 编辑窗口显示与隐藏
 * @param isShowEdit
 * @param id
 * @returns {Function}
 */
export function showEditForm(isShowEdit, id={}){
    if(isShowEdit===true){
        let url = iportal + "/prescriptionRegister/findById.json?id="+id;
        return function(dispatch){
            return fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(function(response){
                if (response.status >= 200 && response.status < 300) {
                    return response
                }else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function(json){
                dispatch({
                    type: types.PRESCRIPTION_REGISTER_EDIT_MODULE,
                    isShowEdit: isShowEdit,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function(dispatch){
            dispatch({
                type: types.PRESCRIPTION_REGISTER_EDIT_MODULE,
                isShowEdit: isShowEdit
            });
        }
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
        url = iportal + "/prescriptionRegister/save.json";
        isAdd = true;
    }else{//更新
        url = iportal + "/prescriptionRegister/update.json";
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
                    type: types.PRESCRIPTION_REGISTER_ADD_MODULE,
                    isShowAdd: false
                });
            }else{
                dispatch({
                    type: types.PRESCRIPTION_REGISTER_EDIT_MODULE,
                    isShowEdit: false
                });
            }

            let data = {
                prescriptionRegisterState: params.prescriptionRegisterState,            //处方登记状态
                prescriptionSellOrderCode: "",                                          //处方销售订单编码
                patientNm: "",                                                          //患者名称
                startPrescriptionDateString: "",                                        //开始时间
                endPrescriptionDateString: ""                                           //结束时间
            };

            actions.prescriptionList(data, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 列表
 * @param params
 * @param page
 * @param size
 * @returns {Function}
 */
export function prescriptionList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/prescriptionRegister/query.json?prescriptionRegisterState=' + params.prescriptionRegisterState + '&prescriptionSellOrderCode=' + params.prescriptionSellOrderCode + '&patientNm='
            + params.patientNm + '&startPrescriptionDateString=' + params.startPrescriptionDateString + '&endPrescriptionDateString=' + params.endPrescriptionDateString + '&page=' + page + '&size=' + size , {
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
                type: types.PRESCRIPTION_LIST,
                prescriptionRegisterState: params.prescriptionRegisterState,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 搜索组件回调
 * @param params
 * @returns {Function}
 */
export function prescriptionSearch(params) {
    return function (dispatch) {
        dispatch(prescriptionSetSearchParams(params));
        dispatch(prescriptionList(params, 0, params.size));
    }
}

/**
 * 获取处方药订单项
 * @param order
 * @returns {Function}
 */
export function queryOrderItem(order){
    return function (dispatch) {
        return fetch(iportal + '/prescriptionRegister/listItemByOrderId.json?orderId=' + order.id, {
            mode: 'cors',
            credentials: 'include',
            method: 'get'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            dispatch({
                type: types.PRESCRIPTION_REGISTER_ADD_ORDER_ITEM_MODULE,
                items: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/**
 * 显示或隐藏调剂窗口
 * @param isShowDispensing
 * @param id
 */
export function showDispensingForm(isShowDispensing, id={}) {
    if(isShowDispensing===true){
        let url = iportal + "/prescriptionRegister/findById.json?id="+id;
        return function(dispatch){
            return fetch(url, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET',
                redirect: 'follow',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(function(response){
                if (response.status >= 200 && response.status < 300) {
                    return response
                }else {
                    let error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).then(function (response) {
                return response.json();
            }).then(function(json){
                dispatch({
                    type: types.PRESCRIPTION_DISPENSING_MODULE,
                    isShowDispensing: isShowDispensing,
                    data: json
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
        }
    }else{
        return function(dispatch){
            dispatch({
                type: types.PRESCRIPTION_DISPENSING_MODULE,
                isShowDispensing: isShowDispensing
            });
        }
    }
}

/**
 * 处方调剂
 * @param data
 * @param actions
 * @param params
 */
export function dispensingPrescription(data, actions, params){
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + "/prescriptionRegister/dispensingPrescription.json", {
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
                type: types.PRESCRIPTION_DISPENSING_MODULE,
                isShowDispensing: false
            });

            let data = {
                prescriptionRegisterState: params.prescriptionRegisterState,            //处方登记状态
                prescriptionSellOrderCode: "",                                          //处方销售订单编码
                patientNm: "",                                                          //患者名称
                startPrescriptionDateString: "",                                        //开始时间
                endPrescriptionDateString: ""                                           //结束时间
            };

            actions.prescriptionList(data, 0, params.size);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
