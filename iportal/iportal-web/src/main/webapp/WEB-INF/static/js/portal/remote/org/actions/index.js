import * as types from "../constants/ActionTypes";

export function portalOrgDelete(ids,actions,parentId){
    if(ids.length<=0){
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
                    return fetch(iportal+"/sysOrg/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(ids)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_ORG_TREE_REFRESH,isRefreshTree:true});
                        dispatch({type: types.PORTAL_ORG_LIST_MULTI_ROW_SELECT,ids:[]});
                        actions.list("","","",parentId,0,20);
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

export function portalOrgSaveUpdateData(data,isAdd,actions){

    return function (dispatch) {
        var url = iportal+"/sysOrg/save.json";
        if(data.id!=undefined){
            url = iportal+"/sysOrg/update.json?id="+data.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            if(isAdd){
                dispatch({type:types.PORTAL_ORG_ADD_MODAL});
            }else{
                dispatch({type: types.PORTAL_ORG_UPDATE_MODAL,data: {}});
            }
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            dispatch({type: types.PORTAL_ORG_TREE_REFRESH,isRefreshTree:true});
            actions.list("","","",data.parentId,0,20);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function portalOrgAddModal(){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_ORG_ADD_MODAL,
            data:{}
        };
        dispatch(empty);
    };
}

export function portalOrgUpdateModal(id){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_ORG_UPDATE_MODAL,
            data: {}
        };
        if(id==undefined||id==null||isNaN(id)){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysOrg/getOrg.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                console.log('org data', json)
                dispatch({
                    type: types.PORTAL_ORG_UPDATE_MODAL,
                    data: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }
}

export function list(isAvailable,searchName,searchValue,parentId,page,size){
    return function (dispatch) {
        dispatch(portalLoadZone());
        const empty = {
            type: types.PORTAL_ORG_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/sysOrg/list.json?parentId='+parentId+'&isAvailable='+isAvailable+'&'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_ORG_LIST,
                    data: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }

}

export function portalLoadZone(){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_LOAD_ZONE,
            zones:[]
        };
        fetch(iportal+'/sysZone/findSysZoneTree.json', {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_LOAD_ZONE,
                    zones: json
                })
            }).catch(function(ex) {
                 console.log('parsing failed', ex)
            });
    };
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

