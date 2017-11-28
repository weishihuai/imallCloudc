import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from '../../../../../../common/validForm/constants/ActionTypes'
import {showErrorMsg} from '../../../../../../common/common'

/*货位移动列表*/
export function storageSpaceMoveList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/storageSpaceMove/list.json?' + 'moveManName=' + (params.moveManName || "") + '&moveOrderNum=' + (params.moveOrderNum || "") + '&moveStartTimeString=' + (params.moveStartTimeString || "") + '&moveEndTimeString=' + (params.moveEndTimeString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.STORAGE_SPACE_MOVE_LIST,
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
            type: types.STORAGE_SPACE_MOVE_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function storageSpaceMoveAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.STORAGE_SPACE_MOVE_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function storageSpaceMoveDetailModal(isDetailShow,moveOrderNum) {
    return function (dispatch) {
        dispatch({
            type: types.STORAGE_SPACE_MOVE_DETAIL_MODAL,
            isDetailShow: isDetailShow,
            moveOrderNum:moveOrderNum
        });
    };
}

/*查看详情*/
export function storageSpaceMoveDetailData(moveOrderNum) {
    return function (dispatch) {
        return fetch(iportal + '/storageSpaceMove/findByMoveOrderNum.json?moveOrderNum='+moveOrderNum, {
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
                    type: types.STORAGE_SPACE_MOVE_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//审核人验证
export function approveValidateData(approveData) {
    return function (dispatch) {
        dispatch({
            type: types.STORAGE_SPACE_MOVE_APPROVE_VALIDATE,
            approveData:approveData
        });
    }
}

//动态获取货位信息
export function listAllStorageSpace() {
    return function (dispatch) {
        return fetch(iportal+'/storageSpace/listAllStorageSpace.json', {
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
                    type: types.STORAGE_SPACE_MOVE_ALL_STORAGE_SPACE,
                    allStorageSpace: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//保存货位移动信息
export function saveStorageSpaceMove(data,params) {
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + '/storageSpaceMove/saveStorageSpaceMove.json', {
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
                let error = new Error(response.statusText);
                error.response = response;
                throw error
            }
        }).then(function(json){

            dispatch(storageSpaceMoveList(params,params.page,params.size));
            dispatch(storageSpaceMoveAddModel(false));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//生成移动单号
export function generateStorageSpaceMoveNum() {
    return function (dispatch) {
        return fetch(iportal + '/storageSpaceMove/generateMoveOrderNum.json', {
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
                    type: types.STORAGE_SPACE_MOVE_GENERATE_NUM,
                    moveNum: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}


