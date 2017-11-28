import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../../../../common/validForm/constants/ActionTypes";
import {showErrorMsg} from "../../../../../../common/common";
import {formatData} from '../../../../../../common/redux-form-ext'

//不良反应报告列表
export function badReactionRepList(params, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/badReactionRep/list.json?' + '&repType=' + (params.repType || "") + '&patientName=' + (params.patientName || "") + '&repStartDateString=' + (params.repStartDateString || "") + '&repEndDateString=' + (params.repEndDateString || "") + "&page=" + page + "&size=" + size, {
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
                    type: types.BAD_REACTION_REP_LIST,
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
            type: types.BAD_REACTION_REP_SEARCH_PARAMS,
            data: params
        })
    }
}

/*新增窗体*/
export function badReactionRepAddModel(isAddShow) {
    return function (dispatch) {
        dispatch({
            type: types.BAD_REACTION_REP_ADD_MODEL,
            isAddShow: isAddShow,
        });
    }
}

/*编辑窗体*/
export function badReactionRepUpdateModel(isUpdateModelShow, data) {
    return function (dispatch) {
        dispatch({
            type: types.BAD_REACTION_REP_UPDATE_MODEL,
            isUpdateModelShow: isUpdateModelShow,
            data: data
        });
    }
}

/*显示 隐藏 详情页 页面*/
export function badReactionRepDetailModal(isDetailShow,id) {
    return function (dispatch) {
        dispatch({
            type: types.BAD_REACTION_REP_DETAIL_MODEL,
            isDetailShow: isDetailShow,
            id:id
        });
    };
}

/*查看详情*/
export function badReactionRepDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/badReactionRep/findById.json?id='+id, {
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
                    type: types.BAD_REACTION_REP_DETAIL_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

//新增不良反应报告
export function badReactionRepSaveData(data){
    return function (dispatch) {
        var  url = iportal + "/badReactionRep/save.json";
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
            dispatch(badReactionRepAddModel(false));
            dispatch(badReactionRepList("",0,10));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

//更新不良反应报告
export function badReactionRepUpdateData(data){
    return function (dispatch) {
        if(data.id != undefined){
            var  url = iportal + "/badReactionRep/update.json?id=" + data.id;
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
            dispatch(badReactionRepUpdateModel(false));
            dispatch(badReactionRepList("",0,10));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/*查询药品信息*/
export function badReactionRepDrugInfData(id) {
    return function (dispatch) {
        return fetch(iportal + '/badReactionDrugInf/findByBadReactionRepId.json?id='+id, {
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
                    type: types.BAD_REACTION_REP_DRUG_INF_DATA,
                    data: json
                })
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}
