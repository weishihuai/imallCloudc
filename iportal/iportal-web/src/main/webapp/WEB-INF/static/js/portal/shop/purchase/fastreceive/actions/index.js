import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from "../../../../../common/common";

export function showAdd() {
    return function (dispatch) {
        dispatch({type: types.FAST_RECEIVE_ADD});
    }
}

export function loadGoodsBatchList(goodsId, callback) {
    return function (dispatch) {
        return fetch(iportal + '/goodsBatch/findBatch.json?goodsId=' + goodsId, {
            mode: 'cors',
            credentials: 'include',
            method: 'GET'
        }).then(function (response) {
            if (response.status >= 200 && response.status < 300) {
                return response
            } else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function (json) {
            callback(json, goodsId);
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function loadStorageSpace() {
    return function (dispatch) {
          return fetch(iportal + '/storageSpace/listForGoodsList.json', {
              mode: 'cors',
              credentials: 'include',
              method: 'GET'
          }).then(function (response) {
              if (response.status >= 200 && response.status < 300) {
                  return response
              } else {
                  var error = new Error(response.statusText);
                  error.response = response;
                  throw error;
              }
          }).then(function (response) {
              return response.json();
          }).then(function (json) {
              dispatch({type: types.FAST_RECEIVE_STORAGE_SPACE_DATA, data: json});
          }).catch(function (ex) {
              dispatch(showErrorMsg(ex.response));
          });
    }
}

export function showDetail(id) {
    return function (dispatch) {
        if (id) {
            return fetch(iportal + '/purchaseAcceptanceRecord/detail.json?id=' + id, {
                mode: 'cors',
                credentials: 'include',
                method: 'GET'
            }).then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    return response
                } else {
                    var error = new Error(response.statusText);
                    error.response = response;
                    throw error;
                }
            }).then(function (response) {
                return response.json();
            }).then(function (json) {
                dispatch({type: types.FAST_RECEIVE_DETAIL, data: json});
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
            });
        } else {
            dispatch({type: types.FAST_RECEIVE_DETAIL});
        }
    }
}

export function saveFastReceive(data) {
    return function(dispatch){
        dispatch({type: VALID_FORM_INIT});
        return fetch(iportal + "/purchaseOrder/saveFastReceive.json", {
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
                throw error;
            }
        }).then(function(json){
            dispatch(showAdd());
            dispatch(setParamsAndLoad({acceptanceOrderNum: ''}));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.FAST_RECEIVE_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function query(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/purchaseAcceptanceRecord/queryFastReceive.json?acceptanceOrderNum='+ params.acceptanceOrderNum +'&page=' + page + '&size=' + size , {
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
                type: types.FAST_RECEIVE_LIST,
                data: json
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function setParamsAndLoad(params) {
    return function (dispatch) {
        dispatch(setParams(params));
        dispatch(query(params, 0, 10));
    }
}
