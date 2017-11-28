import * as types from "../constants/ActionTypes";

/**
 *  更新缓存记录
 * @param cacheData
 * @param actions
 * @returns {Function}
 */
export function cacheSaveUpdateData(cacheData,actions){

    return function (dispatch) {

        return fetch(iportal+"/sysCacheTool/update.json" , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cacheData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            dispatch({type: types.PORTAL_CACHE_UPDATE_MODAL,cacheData: {}});
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            actions.list("",0,15);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}


/**
 * 显示编辑窗体
 * @param entity
 * @returns {Function}
 */
export function portalCacheUpdateModal(entity){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_CACHE_UPDATE_MODAL,
            cacheData: entity
        };
        return dispatch(empty);
    }
}


/**
 *  加载缓存列表
 * @param searchValue
 * @param page
 * @param size
 * @returns {Function}
 */
export function list(searchValue,page,size){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_CACHE_LIST,
            data: {content:[],totalElements:0,number:1,size:15}
        };
        dispatch(empty);
        return fetch(iportal+'/sysCacheTool/list.json?cacheEntityName='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_CACHE_LIST,
                    data: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }

}

/**
 *  显示错误
 * @param response
 * @returns {Function}
 */
export function showErrorMsg(response){
    return function (dispatch) {
        if(response.status!=200){
            response.text().then(function (text) {
                var title = response.status + ' ' + response.statusText;
                $.niftyNoty({
                    type: "danger",
                    container : 'floating',
                    html : '<h4 class="alert-title">错误消息</h4> <p class="alert-message">'+text+'</p>',
                    timer : 0,
                    floating:{position:'bottom-right',animationIn:'fadeIn'}
                });
            });
        }
    }
}

/**
 * 根据key清除缓存
 * @param cacheEntityName
 * @param pattern
 * @returns {Function}
 */
export  function  portalCacheClear(cacheEntityName,pattern){
    return function (dispatch) {
        var bootbox = require("bootbox");
        bootbox.confirm({
            locale:'zh_CN',
            message : "<h4 class='text-thin'>系统提示</h4><p>您确定清除"+cacheEntityName+"的缓存？</p>",
            buttons: {
                confirm: {
                    label: "确定"
                },
                cancel: {
                    label: "取消"
                }
            },
            callback : function(result) {
                if(result){
                    return fetch(iportal+"/sysCacheTool/clear.json", {
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded"
                        },
                        credentials: 'same-origin',
                        method: 'post',
                        body: "pattern=" + pattern
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：清除成功", timer : 3000});
                        actions.list("",0,15);
                    }).catch(function(ex) {
                        console.log('parsing failed', ex)
                    });
                }
            },
            animateIn: 'bounceIn',
            animateOut : 'bounceOut'
        });
    }
}





