import * as types from "../constants/ActionTypes";


/**
 *
 * @param isOpen  打开商品弹窗 true
 * @param isSingle 是否单选 单选true 多选false
 * @param prescriptionDrugsTypeCode 处方药类型代码:有则填  没有则传 ""
 * @param callback 回调方法
 * @returns {Function}
 */
export function changeCommonSupplierListState(isOpen, callback = ()=> {}) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_ADD_SUPPLIER_LIST_STATE,
            isOpen: isOpen,
            callback: callback
        })
    }
}

export function showSupplierComponent(isOpen, supplierNm, callback = ()=> {}) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_ADD_SUPPLIER_LIST_STATE,
            isOpen: isOpen,
            params: {supplierNm: supplierNm || ''},
            callback: callback
        })
    }
}

export function closeSupplierSelectWinAndCallBack(selectedContents, callback) {
    return function (dispatch) {
        callback(selectedContents);
        dispatch({
            type: types.COMMON_SUPPLIER_WIN_CLOSE
        })
    }

}

export function loadCommonSupplierList( params,page, size) {

    return function (dispatch) {
        require('queryObject');
        var paramUrl = $.query
            .set('supplierNm', params.supplierNm || "")
            .set('certificatesNum', params.certificatesNum || "")
            .set('page', page)
            .set('size', size)
            .set("rd", Math.random())
            .toString();
        //输出 :  "?isAvailable=Y&page=0&size=10"
        return fetch(iportal + '/supplier/queryPage.json' + paramUrl, {
            mode: 'cors', credentials: 'include', method: 'get'
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
                    type: types.COMMON_SUPPLIER_LIST,
                    data: json
                })
            }).catch(function (ex) {
                dispatch(showErrorMsg(ex.response));
                console.log('parsing failed', ex)
            });
    }
}



export function showErrorMsg(response) {
    return function (dispatch) {
        if (response.status != 200) {
            response.text().then(function (text) {
                var title = response.status + ' ' + response.statusText;
                $.niftyNoty({
                    type: "danger",
                    container: 'floating',
                    html: '<h4 class="alert-title">错误消息</h4> <p class="alert-message">' + text + '</p>',
                    timer: 0,
                    floating: {position: 'bottom-right', animationIn: 'fadeIn'}
                });
            });
        }
    }
}

export function selectDate(selectedId,selectedContent) {
    return function (dispatch) {
        dispatch({
            type:types.COMMON_SUPPLIER_SELECT,
            selectedId:selectedId,
            selectedContent:selectedContent
        })
    }
}

//设置搜索参数
export function setSearchParams(params) {
    return function (dispatch) {
        dispatch({
            type: types.COMMON_SUPPLIER_LIST_SET_PARAMS,
            params: params
        })
    }
}