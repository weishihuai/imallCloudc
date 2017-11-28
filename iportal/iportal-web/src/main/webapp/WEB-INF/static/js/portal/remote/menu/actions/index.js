import * as types from "../constants/ActionTypes";

export function portalMenuDelete(menuIds,actions,parentId){
    if(menuIds.length<=0){
        $.niftyNoty({type: "danger", container : 'page', html : "系统消息：删除失败，请先选择要删除的菜单", timer : 3000});
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
                    return fetch(iportal+"/sysMenu/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(menuIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_MENU_TREE_REFRESH,isRefreshTree:true});
                        dispatch({type: types.PORTAL_MENU_LIST_MULTI_ROW_SELECT,menuIds:[]});
                        actions.list("",parentId,0,10);
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

export function bindFormValue(value,field){
    const empty = {
        type: types.BIND_FORM_VLAUE,
        inputValue:value,
        field:field
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function menuSaveUpdateData(menuData,isAdd){

    return function (dispatch) {
        var url = iportal+"/sysMenu/save.json";
        if(menuData.id!=undefined){
            url = iportal+"/sysMenu/update.json?id="+menuData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(menuData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            if(isAdd){
                dispatch(portalMenuAddModal());
            }else{
                dispatch(portalMenuUpdateModal());
            }
            dispatch({type: types.PORTAL_MENU_TREE_REFRESH,isRefreshTree:true});
            dispatch(list("",menuData.parentId,0,10));
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function reloadMenuAddUpdateForm(isSelectResource,resourceId,resourceName,icon){
    const empty = {
        type: types.RELOAD_MENU_ADD_FORM,
        menuData:{resourceId:resourceId,resourceName:resourceName,icon:icon}
    };
    return function (dispatch) {
        if(isSelectResource){
            dispatch(resourceSelectModal()); 
        }else{
            dispatch(iconSelectModal());
        }
        dispatch(empty);
    };
}

export function iconSelectModal(){
    const empty = {
        type: types.ICON_SELECT_MODAL
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function resourceFormChangeToState(formData){
    return function (dispatch) {
        dispatch({
            type: types.RESOURCE_FORM_CHANGE_TO_STATE,
            formData: formData
        })
    }
}

export function resourceSelectModal(){
    const empty = {
        type: types.RESOURCE_SELECT_MODAL
    };
    return function (dispatch) {
        dispatch(empty);
    };
}


export function portalMenuAddModal(parentId){
    const empty = {
        type: types.PORTAL_MENU_ADD_MODAL,
        menuData:{isAvailable:'Y',menuType:'APP',parentId:parentId}
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function portalMenuUpdateModal(id){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_MENU_UPDATE_MODAL,
            menuData: {}
        };
        if(id==undefined||id==null){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysMenu/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_MENU_UPDATE_MODAL,
                    menuData: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }
}

export function list(menuName,parentId,page,size){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_MENU_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/sysMenu/list.json?parentId='+parentId+'&menuName='+menuName+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get' })
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_MENU_LIST,
                    data: json,
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




