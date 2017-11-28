import * as types from "../constants/ActionTypes";
import {reset} from "redux-form";
import {showErrorMsg} from "../../../../common/common";

export function portalResourceDelete(ids,actions,parentId){
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
                    return fetch(iportal+"/sysResource/delete.json", {
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
                        dispatch({type: types.PORTAL_RESOURCE_TREE_REFRESH,isRefreshTree:true});
                        dispatch({type: types.PORTAL_RESOURCE_LIST_MULTI_ROW_SELECT,ids:[]});
                        actions.list("","resourceName","",parentId,0,10);
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

export function portalResourceSaveUpdateData(data,isAdd,actions){
    return function (dispatch) {
        var url = iportal+"/sysResource/save.json";
        if(data.id!=undefined){
            url = iportal+"/sysResource/update.json?id="+data.id;
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
                dispatch({type:types.PORTAL_RESOURCE_ADD_MODAL,apps:[]});
            }else{
                dispatch({type: types.PORTAL_RESOURCE_UPDATE_MODAL,data: {}});
            }
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            dispatch({type: types.PORTAL_RESOURCE_TREE_REFRESH,isRefreshTree:true});
            actions.list("","resourceName","",data.parentId,0,10);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function portalResourceAddModal(treePId){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_RESOURCE_ADD_MODAL,
            apps:[]
        };
        fetch(iportal+'/sysApp/findAbleApp.json', {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            })
            .then(function(json) {
                dispatch({
                    type: types.PORTAL_RESOURCE_ADD_MODAL,
                    data:{isAvailable:'Y',resourceType:'BTN',appId:1,parentId:treePId,routerKey:"#",routerTemplateJs:"#"},
                    apps: json
                });
            })
            .catch(function(ex) {
                console.log('parsing failed', ex)
            });
    };
}

export function portalFindAbleApp(){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_FIND_ABLE_APP,
            apps:[]
        };
        fetch(iportal+'/sysApp/findAbleApp.json', {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                 dispatch({
                    type: types.PORTAL_FIND_ABLE_APP,
                    apps: json
                });
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    };
}

export function portalResourceUpdateModal(id){
    return function (dispatch) {
        dispatch(portalFindAbleApp());
        const empty = {
            type: types.PORTAL_RESOURCE_UPDATE_MODAL,
            data: {}
        };
        if(id==undefined||id==null||isNaN(id)){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysResource/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_RESOURCE_UPDATE_MODAL,
                    data: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });

    }
}

export function list(isAvailable,searchName,searchValue,parentId,page,size){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_RESOURCE_LIST,
            data: {content:[],totalElements:0,number:1,size:10},
        };
        dispatch(empty);
        return fetch(iportal+'/sysResource/list.json?parentId='+parentId+'&isAvailable='+isAvailable+'&'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get' })
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_RESOURCE_LIST,
                    data: json,
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }

}

export function portalResourceUrlMgrModal(resourceId,url,isReload,page, sizePerPage){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_RESOURCE_URL_MGR_MODAL,
            resourceUrlMgrPage: {content: [], totalElements: 0, number: 1, size: 10},
            isReload:isReload,
            resourceUrlData:{resourceId:resourceId,url:""}
        };
        if(resourceId==undefined||resourceId==null){
            return dispatch(empty);
        }
        return fetch(iportal + '/sysResourceUrl/list.json?resourceId=' + resourceId+"&url="+url+"&page="+page+"&size="+sizePerPage, {
            credentials: 'same-origin',
            method: 'get'
        })
            .then(function(response) {
                dispatch(showErrorMsg(response));
                if(response.status!=200){
                    dispatch({
                        type: types.PORTAL_RESOURCE_URL_MGR_MODAL,
                        resourceUrlMgrPage: {content: [], totalElements: 0, number: 1, size: 10},
                        isReload:isReload,
                        resourceUrlData:{resourceId:resourceId,url:""}
                    })
                }
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_RESOURCE_URL_MGR_MODAL,
                    resourceUrlMgrPage: json,
                    isReload:isReload,
                    resourceUrlData:{resourceId:resourceId,url:""}
                });
            }).catch(function(ex) {
                dispatch({
                    type: types.PORTAL_RESOURCE_URL_MGR_MODAL,
                    resourceUrlMgrPage: {content: [], totalElements: 0, number: 1, size: 10},
                    isReload:isReload,
                    resourceUrlData:{resourceId:resourceId,url:""}
                });
                console.log('parsing failed', ex)
            });
    }
}

export function portalResourceUrlDelete(resourceId,resourceUrlIds,actions){
    if(resourceUrlIds.length<=0){
        $.niftyNoty({ type:'danger',container : '#resourceUrlMgrMsg', html : "系统消息：删除失败，请先选择要删除的资源URL", timer : 3000});
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
                    return fetch(iportal+"/sysResourceUrl/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(resourceUrlIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : '#resourceUrlMgrMsg', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_RESOURCE_URL_LIST_MULTI_ROW_SELECT,resourceUrlIds:[]});
                        actions.portalResourceUrlMgrModal(resourceId,"",true,0,10);
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

export function portalResourceUrlSaveUpdateData(resourceUrlData){
    return function (dispatch) {
        var url = iportal+"/sysResourceUrl/saveResourceUrl.json";
        if(resourceUrlData.id!=undefined){
            url = iportal+"/sysResourceUrl/updateResourceUrl.json?id="+resourceUrlData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(resourceUrlData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            $.niftyNoty({type: "success", container : '#resourceUrlModalMsg', html : "系统消息：操作成功", timer : 3000});
            dispatch(portalResourceUrlMgrModal(resourceUrlData.resourceId,"",true,0,10));
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}