import * as types from "../constants/ActionTypes";

export function portalExceptionDelete(exceptionIds,actions){
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
                    return fetch(iportal+"/sysExceptionCode/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(exceptionIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_EXCEPTION_LIST_MULTI_ROW_SELECT,exceptionIds:[]});
                        actions.list("","","",0,20);
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

export function exceptionSaveUpdateData(exceptionData,isAdd,actions){

    return function (dispatch) {
        var url = iportal+"/sysExceptionCode/save.json";
        if(exceptionData.id!=undefined){
            url = iportal+"/sysExceptionCode/update.json?id="+exceptionData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(exceptionData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            if(isAdd){
                dispatch({type:types.PORTAL_EXCEPTION_ADD_MODAL});
            }else{
                dispatch({type: types.PORTAL_EXCEPTION_UPDATE_MODAL,exceptionData: {}});
            }
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            actions.list("","","",0,20);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function portalExceptionAddModal(){
    const empty = {
        type: types.PORTAL_EXCEPTION_ADD_MODAL,
        exceptionData:{isAvailable:'Y'}
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function portalExceptionUpdateModal(id){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_EXCEPTION_UPDATE_MODAL,
            exceptionData: {}
        };
        if(id==undefined||id==null||isNaN(id)){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysExceptionCode/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_EXCEPTION_UPDATE_MODAL,
                    exceptionData: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }
}


export function list(searchName,searchValue,page,size){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_EXCEPTION_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/sysExceptionCode/list.json?'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_EXCEPTION_LIST,
                    data: json
                })
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





