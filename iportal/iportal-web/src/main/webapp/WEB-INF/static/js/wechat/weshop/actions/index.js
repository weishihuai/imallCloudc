import * as types from "../constants/ActionTypes";

/*微门店详情*/
export function weShopDetailData(id) {
    return function (dispatch) {
        return fetch(iportal + '/wechat/weShop/findById.json?id=' + id, {
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
                    type: types.WE_SHOP_DETAIL_DATA,
                    data: json
                });
            }).catch(function(ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}

/*门店公告 弹窗*/
export function placardInfModel(isModelShow, data) {
    return function (dispatch) {
        dispatch({
            type: types.WE_SHOP_PLACARD_INF_MODEL,
            isModelShow: isModelShow,
            data: data
        });
    }
}

/*店长微信 弹窗*/
export function shopMgrWeiXinModel(isShow, shopMgrWeiXinUrl) {
    return function (dispatch) {
        dispatch({
            type: types.WE_SHOP_MGR_WEI_XIN_MODEL,
            isShow: isShow,
            shopMgrWeiXinUrl: shopMgrWeiXinUrl
        });
    }
}

/*门店电话 弹窗*/
export function weShopTelModel(isTelModelShow, weShopContactTel) {
    return function (dispatch) {
        dispatch({
            type: types.WE_SHOP_TEL_MODEL,
            isTelModelShow: isTelModelShow,
            weShopContactTel: weShopContactTel
        });
    }
}