import * as types from "../constants/ActionTypes";

export function portalAppDelete(appIds,actions){
    if(appIds.length<=0){
        $.niftyNoty({type: "danger", container : 'page', html : "系统消息：删除失败，请先选择要删除的应用", timer : 3000});
        return;
    }
    return function (dispatch) {
        var bootbox = require("bootbox");
        bootbox.confirm({
            locale:'zh_CN',
            message : "<h4 class='text-thin'>系统提示</h4><p>您确定要删除吗？</p>",
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
                    return fetch(iportal+"/sysApp/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(appIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_APP_LIST_MULTI_ROW_SELECT,appIds:[]});
                        actions.applist("","","",0,20);
                        console.log('parsed json', json)
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

export function appSaveUpdateData(appData,isAdd,actions){

    return function (dispatch) {
        var url = iportal+"/sysApp/save.json";
        if(appData.id!=undefined){
            url = iportal+"/sysApp/update.json?id="+appData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(appData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            if(isAdd){
                dispatch({type:types.PORTAL_APP_ADD_MODAL});
            }else{
                dispatch({type: types.PORTAL_APP_UPDATE_MODAL,appData: {}});
            }
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            actions.applist("","","",0,20);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function portalAppAddModal(){
    const empty = {
        type: types.PORTAL_APP_ADD_MODAL,
        appData:{isAvailable:'Y'}
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function portalAppUpdateModal(id){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_APP_UPDATE_MODAL,
            appData: {}
        };
        if(id==undefined||id==null||isNaN(id)){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysApp/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_APP_UPDATE_MODAL,
                    appData: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }
}

export function applist(isAvailable,searchName,searchValue,page,size){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_APP_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/sysApp/list.json?isAvailable='+isAvailable+'&'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_APP_LIST,
                    data: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }

}

export function exportSearch(isAvailable,searchName,searchValue,page,size, callback){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_APP_UPDATE_EXPORT_ROWS,
            exportRows: []
        };
        dispatch(empty);
        return fetch(iportal+'/sysApp/list.json?isAvailable='+isAvailable+'&'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_APP_UPDATE_EXPORT_ROWS,
                    exportRows: json.content
                });
                callback();
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }

}

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



