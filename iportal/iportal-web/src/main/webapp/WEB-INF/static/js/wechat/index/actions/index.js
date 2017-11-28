import * as types from "../constants/ActionTypes";
import {getShoppingCart} from "../../shopping/actions";

export function setCurrLocation(currLocation, isAutoLocated) {
    return function (dispatch) {
        dispatch({type: types.INDEX_CURR_LOCATION, currLocation, isAutoLocated});
        $.cookie(types.INDEX_CUR_LOCATION_COOKIE_KEY, JSON.stringify(currLocation));
    }
}

export function setNoShopType(noShopType) {
    return function (dispatch) {
        dispatch({type: types.INDEX_NO_SHOP_TYPE, noShopType});
    }
}

export function setShowMgrWeChat(showMgrWeChat) {
    return function (dispatch) {
        dispatch({type: types.INDEX_SHOW_SHOW_MGR_WE_CHAT, showMgrWeChat});
    }
}

export function setNearByShopType(nearByShopType) {
    return function (dispatch) {
        dispatch({type: types.INDEX_NEAR_BY_SHOP_TYPE, nearByShopType});
    }
}

export function setShopData(data) {
    return function (dispatch) {
        dispatch({type: types.INDEX_SHOP_DATA, data});
    }
}

export function setSupportDeliveryZone(data) {
    return function (dispatch) {
        dispatch({type: types.INDEX_SUPPORT_DELIVERY_ZONE, data});
    }
}

export function setGoodsPage(data) {
    return function (dispatch) {
        dispatch({type: types.INDEX_GOODS_PAGE, data});
    }
}

export function setGroupList(data) {
    return function (dispatch) {
        dispatch({type: types.INDEX_GROUP_LIST, data});
    }
}

export function setLoginUserInfo(data) {
    return function (dispatch) {
        dispatch({type: types.INDEX_LOGIN_USER_INFO, data});
    }
}

export function setShowQRCode(showQRCode) {
    return function (dispatch) {
        dispatch({type: types.INDEX_SHOW_QR_CODE, showQRCode});
    }
}

export function setScrollTop(scrollTop) {
    return function (dispatch) {
        dispatch({type: types.INDEX_SCROLL_TOP, scrollTop});
    }
}

export function findShop(lat, lng, callback) {
    return function (dispatch) {
        dispatch(setGroupList([]));
        return fetch(iportal + '/wechat/findShop.json?lat=' + lat + "&lng=" + lng, {
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
            if(callback){
                callback(json);
            }
            $.cookie(types.INDEX_CUR_WE_SHOP_ID, json.weShopId ? json.weShopId : '');
            dispatch({type: types.INDEX_SHOP_DATA, data: json});
            if(json.weShopId){
                dispatch(findRecommendGroup());
            }
            dispatch(getShoppingCart());
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findShopById(weShopId, callback) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/findShopById.json?weShopId=' + weShopId, {
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
            if(callback){
                callback();
            }
            dispatch({type: types.INDEX_SHOP_DATA, data: json});
            if(json.weShopId){
                dispatch(findRecommendGroup());
            }
            dispatch(getShoppingCart());
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findNearByShop(lat, lng) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/findNearByShop.json?lat=' + lat + "&lng=" + lng, {
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
            dispatch({type: types.INDEX_NEAR_BY_SHOP_DATA, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findSupportDeliveryZone(callback) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/findSupportDeliveryZone.json', {
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
            if(callback){
                callback(json);
            }
            dispatch({type: types.INDEX_SUPPORT_DELIVERY_ZONE, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findByCityName(cityName, callback) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/findByCityName.json?cityName=' + cityName, {
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
            dispatch({type: types.INDEX_CITY_SHOP, data: json});
            if(callback){
                callback(json);
            }
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function getReceiveAddrList() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/receiveAddr/list.json', {
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
            dispatch({type: types.INDEX_RECEIVE_ADDR_LIST_DATA, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function selectZone(showSelectZone) {
    return function (dispatch) {
        dispatch({type: types.INDEX_RECEIVE_ADDR_SELECT_ZONE, showSelectZone});
    }
}

export function saveReceiveAddr(data) {
    return function(dispatch){
        return fetch(iportal + "/wechat/receiveAddr/save.json", {
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
            window.location.hash = "#/index-receive-addr-list";
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findRecommendGroup() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/findRecommendGroup.json', {
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
                dispatch(queryRecommendGoods(json[0].id, 0, 5));
            }else{
                dispatch(setGoodsPage({content: [], number: 0, totalElements: 0, size: 5}));
            }
            dispatch({type: types.INDEX_GROUP_LIST, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function queryRecommendGoods(groupId, page, size, goodsList = []) {
    return function (dispatch) {
        dispatch(setScrollTop(0));
        return fetch(iportal + '/wechat/queryRecommendGoods.json?groupId=' + groupId + "&page=" + page + "&size=" + size, {
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
            dispatch({type: types.INDEX_GOODS_PAGE, data: Object.assign({}, json, {content: goodsList.concat(json.content)})});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findSwitchShop(lat, lng) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/findSwitchShop.json?lat=' + lat + "&lng=" + lng, {
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
            dispatch({type: types.INDEX_SWITCH_SHOP, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}