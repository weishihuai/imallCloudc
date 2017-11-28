import * as types from "../constants/ActionTypes";
import {VALID_FORM_INIT} from "../../../common/validForm/constants/ActionTypes";
import {showErrorMsg, niftyNoty} from "../../../common/common";
import {formatData} from "../../../common/redux-form-ext";

/*新增会员窗体*/
export function memberAddModel(isMemberAddShow) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        dispatch({
            type: types.POS_CASHIER_MEMBER_ADD_MODEL,
            isMemberAddShow: isMemberAddShow
        });
    }
}

//新增会员
export function memberAddData(data) {
    return function (dispatch) {
        dispatch({type: VALID_FORM_INIT});
        var url = iportal + "/member/saveSalesPosMember.json";
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
            dispatch({type: types.POS_CASHIER_MEMBER_MODAL});
            dispatch(memberAddModel(false));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}

/*会员搜索*/
export function memberSearchData(searchText, uniqueKey) {
    return function (dispatch) {
        let url = iportal + "/member/findMemberAndConvertCart.json?searchText=" + searchText + "&uniqueKey=" + uniqueKey;
        return fetch(url, {
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
            if(json.cart) {
                dispatch(updateShoppingCartInfo(json.cart));
            }
            if(json.member) {
                dispatch({
                    type: types.POS_CASHIER_MEMBER_DATA,
                    data: json.member
                });
            } else {
                niftyNoty("查询会员不存在！");
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

export function resetMemberSearch(uniqueKey) {
    return function (dispatch) {
        let url = iportal + "/salesPosCart/convertWithoutMember.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                "Content-Type":"application/x-www-form-urlencoded"
            },
            body: "uniqueKey=" + uniqueKey
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
                type: types.POS_CASHIER_RESET_MEMBER,
                data: json
            });
            dispatch(updateShoppingCartInfo(json))
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

export function searchFieldsChange(value) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_SEARCH_FIELDS_CHANGE,
            searchFields: value
        })
    }
}

//选择商品窗口展示
export function goodsSearchData(isShow, searchFields) {

    if(!isShow) {
        return function (dispatch) {
            dispatch({
                type: types.POS_CASHIER_SEARCH_GOODS_MODAL,
                data: "",
                isShow: isShow,
                searchFields: ""
            })
        }
    }

    return function (dispatch) {
        if(searchFields == undefined || searchFields.trim() == "") {
            niftyNoty("请输入查询商品信息");
            return;
        }
        let url = iportal + "/goods/listPosGoods.json?searchFields=" + searchFields;
        return fetch(url, {
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
                type: types.POS_CASHIER_SEARCH_GOODS_MODAL,
                isShow: true,
                data: json,
                searchFields: searchFields
            });
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

//选择批次窗口展示
export function batchSearchData(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_SEARCH_BATCH_MODAL,
            isShow: isShow,
        })
    }
}

//获取购物车
export function getShoppingCart() {
    return function (dispatch) {
        let url = iportal + "/salesPosCart/getCart.json";
        return fetch(url, {mode: 'cors', credentials: 'include', method: 'get'})
            .then(function (response) {
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
                dispatch(updateShoppingCartInfo(json));
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            }
        );
    };
}

//获取购物车
export function createShiftRecord() {
    return function (dispatch) {
        let url = iportal + "/shiftRecord/saveRecord.json";
        return fetch(url, {mode: 'cors', credentials: 'include', method: 'get'})
            .then(function (response) {
                if (response.status >= 200 && response.status < 300) {
                    return response
                } else {
                    var error = new Error(response.statusText);
                    error.response = response;
                    throw error
                }
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            }
        );
    };
}

//更新购物车信息
export function updateShoppingCartInfo(json) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_SHOPPING_CART_DATA,
            data: json,
        });
    }
}

//获取当前营业信息
export function getLoginCashier() {
    return function (dispatch) {
        let url = iportal + "/sysUser/findByLoginCashier.json";
        return fetch(url, {mode: 'cors', credentials: 'include', method: 'get'})
            .then(function (response) {
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
                    type: types.POS_CASHIER_LOGIN_MAN_DATA,
                    data: json,
                });
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex);
            });
    };
}

//添加到购物车
export function addToCart(uniqueKey, skuId, batch, quantity,mobile) {
    return function (dispatch) {
        if(!uniqueKey || !skuId || !batch || !quantity) {
            return;
        }
        let url = iportal + "/salesPosCart/addItem.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                "Content-Type":"application/x-www-form-urlencoded"
            },
            body: "uniqueKey=" + uniqueKey + "&skuId=" + skuId + "&batch=" + batch + "&quantity=" + quantity
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
            if(mobile==null){
                dispatch(updateShoppingCartInfo(json));
            }else{
                dispatch(memberSearchData(mobile,uniqueKey));
            }
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

//修改购物车项弹窗展示
export function updateQuantityModal(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_UPDATE_QUANTITY_MODAL,
            isShow: isShow,
        })
    }
}

//修改购物车项数量
export function updateQuantity(uniqueKey, skuId, batch, quantity) {
    return function (dispatch) {
        let url = iportal + "/salesPosCart/updateQuantity.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                "Content-Type":"application/x-www-form-urlencoded"
            },
            body: "uniqueKey=" + uniqueKey + "&skuId=" + skuId + "&batch=" + batch + "&quantity=" + quantity
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
                type: types.POS_CASHIER_UPDATE_QUANTITY_MODAL,
                isShow: false,
            });
            dispatch(updateShoppingCartInfo(json));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

//删除购物车项
export function removeCartItem(uniqueKey, skuId, batch) {
    return function (dispatch) {
        let url = iportal + "/salesPosCart/removeCartItem.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                "Content-Type":"application/x-www-form-urlencoded"
            },
            body: "uniqueKey=" + uniqueKey + "&skuId=" + skuId + "&batch=" + batch
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
            dispatch(updateShoppingCartInfo(json));
            dispatch({type: types.POS_CASHIER_CART_ITEM_INDEX_UP});
            dispatch(changeConfirmState(false,""));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

export function changeConfirmState(isOpen,isDeleteAll) {
    return function (dispatch) {
        dispatch({
            type:types.POS_CASHIER_CART_DELETE_VIEW_STATE,
            data:isOpen,
            isDeleteAll:isDeleteAll
        })
    }
}

//清空购物车
export function clearCart(uniqueKey) {
    return function (dispatch) {
        let url = iportal + "/salesPosCart/clearCart.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                "Content-Type":"application/x-www-form-urlencoded"
            },
            body: "uniqueKey=" + uniqueKey
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
            dispatch(updateShoppingCartInfo(json));
            dispatch({type: types.POS_CASHIER_CART_ITEM_INDEX_UP});
            dispatch(changeConfirmState(false,""));
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

//结算弹窗展示
export function settlementModal(isShow) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_SETTLEMENT_MODAL,
            isShow: isShow,
        })
    }
}

//修改购物车项数量修改
export function updateNewQuantityChange(quantity) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_UPDATE_NEW_QUANTITY_CHANGE,
            data: quantity,
        })
    }
}

//结算
export function saveSalesPosOrder(uniqueKey, healthInsurancePayAmount, cashPayAmount, paymentWayCode) {

    if(healthInsurancePayAmount == "") {
        healthInsurancePayAmount = 0;
    }

    return function (dispatch) {
        let url = iportal + "/salesPosCart/saveSalesPosOrder.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                "Content-Type":"application/x-www-form-urlencoded"
            },
            body: "uniqueKey=" + uniqueKey + "&healthInsurancePayAmount=" + healthInsurancePayAmount + "&cashPayAmount=" + cashPayAmount + "&paymentWayCode=" + paymentWayCode
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
            dispatch({type: types.POS_CASHIER_LOGIN_MAN_DATA_CHANGE});
            dispatch(updateShoppingCartInfo(json));
            dispatch({type: types.POS_CASHIER_CART_ITEM_INDEX_UP});
            dispatch({type: types.POS_CASHIER_RESET_MEMBER});
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    };
}

//收银交班弹窗
export function shiftModel(isShiftShow) {
    return function (dispatch) {
        dispatch({
            type: types.POS_CASHIER_SHIFT_MODEL,
            isShiftShow: isShiftShow
        });
    }
}

//收银交班记录获取
export function getShiftRecord() {
    return function (dispatch) {
        let url = iportal + "/shiftRecord/findByShopIdAndPosManAndNotShift.json";
        return fetch(url, {mode: 'cors', credentials: 'include', method: 'get'})
            .then(function (response) {
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
                    type: types.POS_CASHIER_SHIFT_DATA_INIT,
                    data: json,
                });
            }).catch(function (ex) {
                    dispatch(showErrorMsg(ex.response));
                    console.log('parsing failed', ex);
                }
            );
    };
}

//收银交班
export function cashierShift(data) {
    return function (dispatch) {
        var url = iportal + "/shiftRecord/updateRecord.json";
        return fetch(url, {
            mode: 'cors',
            credentials: 'include',
            method: 'POST',
            redirect: 'follow',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
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
            dispatch(shiftModel(false));
            window.location.href = iportal + "/sso/poslogout?succeedWhoId=" + json.msg;
        }).catch(function (ex) {
            dispatch(showErrorMsg(ex.response));
            console.log('parsing failed', ex);
        });
    }
}
