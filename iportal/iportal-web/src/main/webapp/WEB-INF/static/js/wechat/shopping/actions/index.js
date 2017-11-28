import * as types from "../constants/ActionTypes";

export function selectZone(showSelectZone) {
    return function (dispatch) {
        dispatch({type: types.WECHAT_SHOPPING_SHOW_SELECT_ZONE, showSelectZone});
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
            window.location.hash = "#/shopping-address-form";
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function select(skuId) {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/select.json?skuId=" + skuId, {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function selectAll() {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/selectAll.json", {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function unselectAll() {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/unselectAll.json", {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function add(skuId, callback) {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/add.json?skuId=" + skuId, {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
            if (callback){
                callback(json);
            }
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function subtract(skuId) {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/subtract.json?skuId=" + skuId, {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function changeQuantity(skuId, quantity) {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/changeQuantity.json?skuId=" + skuId + "&quantity=" + quantity, {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function saveShopping(data) {
    return function(dispatch){
        return fetch(iportal + "/wechat/normalShopping/saveOrder.json", {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            window.location.hash = "#/order-success/" + json.msg;
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}


export function deleteSelected(ids, callback) {
    return function(dispatch){
        if(ids==null || ids.length <= 0){
            showError("请选择要删除的项");
            return false;
        }
        return fetch(iportal + "/wechat/normalShopping/deleteSelected.json", {
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
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
            dispatch(deleteConfirmModel(false));
            if(callback){
                callback();
            }
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function getShoppingCart() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/normalShopping/getCart.json', {
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
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function setDefaultReceiverAddr() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/normalShopping/saveDefaultReceiverAddress.json', {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
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
            dispatch({type: types.WECHAT_SHOPPING_CART, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function getDeliveryTime() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/normalShopping/getDeliveryTime.json', {
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
            dispatch({type: types.WECHAT_SHOPPING_FORM_DELIVERY_DATE, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function getReceiverAddrList() {
    return function (dispatch) {
        return fetch(iportal + '/wechat/normalShopping/cartListAddr.json', {
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
            dispatch({type: types.WECHAT_SHOPPING_ADDRESS_LIST, data: json});
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function saveAddress(id) {
    return function(dispatch){
        if(id==null){
            showError("请选择配送地址");
            return false;
        }
        return fetch(iportal + "/wechat/normalShopping/saveReceiverAddress.json", {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
        }).then(function(response){
            if (response.status >= 200 && response.status < 300) {
                return response
            }else {
                var error = new Error(response.statusText);
                error.response = response;
                throw error;
            }
        }).then(function (response) {
            return response.json();
        }).then(function(json){
            window.location.hash = "#/shopping-form";
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function deleteConfirmModel(isDeleteModel,len) {
    return function (dispatch) {
        if (len > 0) {
            dispatch({
                type: types.WE_CHAT_SHOPPING_CART_DELETE_CONFIRM,
                isDeleteModel: isDeleteModel
            });
        }else {
            dispatch({
                type: types.WE_CHAT_SHOPPING_CART_DELETE_CONFIRM,
                isDeleteModel: false
            });
        }
    }
}

