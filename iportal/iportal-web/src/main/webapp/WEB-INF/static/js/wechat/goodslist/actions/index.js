import * as types from "../constants/ActionTypes";

export function setParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.WE_CHAT_GOODS_LIST_SET_PARAMS,
            data: params
        });
    }
}

export function changeQuickNav(show) {
    return function (dispatch) {
        dispatch({
            type:types.WE_CHAT_GOODS_DETAIL_QUICK_NAV_STATE,
            show
        })
    }
}

export function initGoodsPage() {
    return function (dispatch) {
        dispatch({
            type: types.WE_CHAT_GOODS_LIST_LIST,
            nextPageLength: 10,
            data: {content: [], number: 0, totalElements: 0, size: 10}
        });
    }
}

export function setScrollTop(scrollTop) {
    return function (dispatch) {
        dispatch({type:types.WE_CHAT_GOODS_LIST_SCROLL_TOP, scrollTop});
    }
}

export function setShowTel(showTel) {
    return function (dispatch) {
        dispatch({type:types.WE_CHAT_GOODS_DETAIL_SHOW_TEL, showTel});
    }
}

export function setGoodsCount(goodsCount) {
    return function (dispatch) {
        dispatch({type:types.WE_CHAT_GOODS_DETAIL_GOODS_COUNTS, goodsCount});
    }
}

export function query(params, page, size,goodsList=[]) {
    const url ="/wechat/goods/list.json";
    return function (dispatch) {
        dispatch(setScrollTop(0));
        return fetch(iportal+ url
            +'?keywords=' + params.keywords
            +'&orderBy=' + params.orderBy
            +'&sortOrder=' + params.sortOrder
            +'&categoryId=' + params.categoryId
            +'&page=' + page + '&size=' + size , {
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
            dispatch(setParams(params));
            dispatch({
                type: types.WE_CHAT_GOODS_LIST_LIST,
                nextPageLength:json.content.length,
                data: Object.assign({},json,{
                    content:goodsList.concat(json.content)
                })
            });
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}

export function findDetailAndOpen(id, callback) {
    const url ="/wechat/goods/detail.json";
    return function (dispatch) {
        return fetch(iportal+ url
            +'?id=' + id , {
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
                type: types.WE_CHAT_GOODS_DETAIL_DATA,
                data:json
            });
            if (callback){
                callback(json);
            }
        }).catch(function (ex) {
            showError(decodeURI(ex.response.headers.get("error")));
        });
    }
}




