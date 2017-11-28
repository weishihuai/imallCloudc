import * as types from "../constants/ActionTypes";
import {showErrorMsg} from "../../../../common/common";

export function setEditGroup(editGroup, groupId) {
    return function (dispatch) {
        dispatch({type: types.DECORATION_RECOMMEND_EDIT_GROUP, editGroup, groupId});
    }
}

export function setGoodsData(data) {
    return function (dispatch) {
        dispatch({type: types.DECORATION_RECOMMEND_GOODS_DATA, data});
    }
}

export function setShowAdd(showAdd) {
    return function (dispatch) {
        dispatch({type: types.DECORATION_RECOMMEND_GROUP_SHOW_ADD, showAdd});
    }
}

export function setGroupData(data) {
    return function (dispatch) {
        dispatch({type: types.DECORATION_RECOMMEND_GROUP_DATA, data});
    }
}

export function setGroupList(data) {
    return function (dispatch) {
        dispatch({type: types.DECORATION_RECOMMEND_GROUP_LIST, data});
    }
}

export function setShowConfirm(showConfirm) {
    return function (dispatch) {
        dispatch({type: types.DECORATION_RECOMMEND_SHOW_CONFIRM, showConfirm});
    }
}

export function getGroupList(callback) {
    return function (dispatch) {
        return fetch(iportal + '/decorationRecommendGroup/findByShopId.json', {
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
            if(json.length > 0){
                json[0].cur = true;
                dispatch(getGoodsList(json[0].id));
            }
            dispatch({type: types.DECORATION_RECOMMEND_GROUP_LIST, data: json});
            if(callback){
                callback();
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function getGroupData(id) {
    return function (dispatch) {
        return fetch(iportal + '/decorationRecommendGroup/findOne.json?id=' + id, {
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
            dispatch({type: types.DECORATION_RECOMMEND_GROUP_DATA, data: json});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function getSalesCategory(callback) {
    return function (dispatch) {
        return fetch(iportal + '/salesCategory/listForGoodsAdd', {
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
            dispatch({type: types.DECORATION_RECOMMEND_SALES_CATEGORY_DATA, data: json});
            if(callback){
                callback(json);
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function getGoodsData(goodsSearchParams, groupId, page, size) {
    return function (dispatch) {
        return fetch(iportal + '/goods/queryDecorationRecommendGoods.json?groupId=' + groupId + '&salesCategoryId=' + goodsSearchParams.salesCategoryId + "&keyword=" + goodsSearchParams.keyword + "&page=" + page + "&size=" + size, {
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
            dispatch({type: types.DECORATION_RECOMMEND_GOODS_DATA, data: json});
            dispatch({type: types.DECORATION_RECOMMEND_GOODS_SEARCH_PARAMS, data: goodsSearchParams});
            $(".table-box-r input[type='checkbox']").each(function () {
                $(this)[0].checked = false;
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function saveBatchRecommend(data, callback) {
    return function(dispatch){
        return fetch(iportal + "/decorationRecommend/saveBatchRecommend.json", {
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
            dispatch(setGroupData({recommendList: []}));
            dispatch(getGroupData(data[0].decorationGroupId));
            if(callback){
                callback("success");
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            if(callback){
                callback("fail");
            }
        });
    }
}

export function deleteBatchRecommend(groupId, ids, callback) {
    return function(dispatch){
        return fetch(iportal + "/decorationRecommend/deleteBatch.json", {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(ids)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch(setGroupData({recommendList: []}));
            dispatch(getGroupData(groupId));
            dispatch(getGoodsData({salesCategoryId: '', keyword: ''}, groupId, 0, 5));
            if(callback){
                callback("success");
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            if(callback){
                callback("fail");
            }
        });
    }
}

export function updateBatchRecommend(groupId, data, callback) {
    return function(dispatch){
        return fetch(iportal + "/decorationRecommend/update.json", {
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
            dispatch(setGroupData({recommendList: []}));
            dispatch(getGroupData(groupId));
            if(callback){
                callback("success");
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            if(callback){
                callback("fail");
            }
        });
    }
}

export function updateGroup(id, groupNm, callback) {
    return function(dispatch){
        return fetch(iportal + "/decorationRecommendGroup/update.json?id=" + id + "&groupNm=" + groupNm, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            if(callback){
                callback("success");
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            if(callback){
                callback("fail");
            }
        });
    }
}

export function saveGroup(groupNm, callback) {
    return function(dispatch){
        return fetch(iportal + "/decorationRecommendGroup/save.json?groupNm=" + groupNm, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch(getGroupList());
            if(callback){
                callback("success");
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            if(callback){
                callback("fail");
            }
        });
    }
}

export function deleteGroup(groupId) {
    return function(dispatch){
        return fetch(iportal + "/decorationRecommendGroup/delete.json?id=" + groupId, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function(json){
            dispatch(getGroupList());
            dispatch(setShowConfirm(false));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function getGoodsList(groupId) {
    return function (dispatch) {
        return fetch(iportal + '/decorationRecommend/listRecommendGoods.json?groupId=' + groupId, {
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
            dispatch({type: types.DECORATION_RECOMMEND_GOODS_LIST, data: json});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}

export function getShopData() {
    return function (dispatch) {
        return fetch(iportal + '/weshop/getDecorationShopMsg.json', {
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
            dispatch({type: types.DECORATION_RECOMMEND_SHOP_DATA, data: json});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
        });
    }
}
