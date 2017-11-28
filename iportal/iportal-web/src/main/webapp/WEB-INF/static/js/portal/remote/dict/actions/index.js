import * as types from "../constants/ActionTypes";

export function portalDictDelete(dictIds,actions){
    if(dictIds.length<=0){
        $.niftyNoty({type: "danger", container : 'page', html : "系统消息：删除失败，请先选择要删除的字典", timer : 3000});
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
                    return fetch(iportal+"/sysDict/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(dictIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_DICT_LIST_MULTI_ROW_SELECT,dictIds:[]});
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

export function dictSaveUpdateData(dictData,isAdd,actions){

    return function (dispatch) {
        var url = iportal+"/sysDict/save.json";
        if(dictData.id!=undefined){
            url = iportal+"/sysDict/update.json?id="+dictData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dictData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            if(isAdd){
                dispatch({type:types.PORTAL_DICT_ADD_MODAL});
            }else{
                dispatch({type: types.PORTAL_DICT_UPDATE_MODAL,dictData: {}});
            }
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            actions.list("","","",0,20);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function dictItemSaveUpdateData(dictItemData,dictId,isAdd,actions){

    return function (dispatch) {
        var url = iportal+"/sysDictItem/save.json";
        if(dictItemData.id!=undefined){
            url = iportal+"/sysDictItem/update.json?id="+dictItemData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(dictItemData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            $.niftyNoty({type: "success", container : '#dictItemModalMsg', html : "系统消息：操作成功", timer : 3000});
            if(isAdd){
                actions.portalDictItemAddModal();
            }else{
                actions.portalDictItemUpdateModal(null);
            }
            actions.dictItemMgrModal(dictId,"","",true);
            console.log('parsed json', json)
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });

    }
}

export function portalDictAddModal(){
    const empty = {
        type: types.PORTAL_DICT_ADD_MODAL,
        dictData:{isAvailable:'Y'}
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function portalDictUpdateModal(id){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_DICT_UPDATE_MODAL,
            dictData: {}
        };
        if(id==undefined||id==null){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysDict/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_DICT_UPDATE_MODAL,
                    dictData: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });

    }
}

export function portalDictItemDelete(dictId,dictItemIds,actions){
    if(dictItemIds.length<=0){
        $.niftyNoty({ type:'danger',container : '#dictItemMgrMsg', html : "系统消息：删除失败，请先选择要删除的字典项", timer : 3000});
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
                    return fetch(iportal+"/sysDictItem/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(dictItemIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : '#dictItemMgrMsg', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_DICT_ITEM_LIST_MULTI_ROW_SELECT,dictItemIds:[]});
                        actions.dictItemMgrModal(dictId,"","",true);
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

export function portalDictItemAddModal(dataDictId){
    const empty = {
        type: types.PORTAL_DICT_ITEM_ADD_MODAL,
        dictItemData:{isAvailable:'Y',isDefault:'Y',dataDictId:dataDictId}
    };
    return function (dispatch) {
        dispatch(empty);
    };
}

export function portalDictItemUpdateModal(id){

    return function (dispatch) {
        const empty = {
            type: types.PORTAL_DICT_ITEM_UPDATE_MODAL,
            dictItemData: {}
        };
        if(id==undefined||id==null){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysDictItem/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_DICT_ITEM_UPDATE_MODAL,
                    dictItemData: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }
}

export function dictItemMgrModal(dataDictId,dictItemNm,dictItemCode,isReload){
    return function (dispatch) {
        const empty = {
            type: types.DICT_ITEM_MGR_MODAL,
            dictItemMgrPage: {content: [], totalElements: 0, number: 1, size: 10},
            isReload:isReload
        };
        if(dataDictId==undefined||dataDictId==null){
            return dispatch(empty);
        }
        return fetch(iportal + '/sysDictItem/list.json?dataDictId=' + dataDictId+"&dictItemNm="+dictItemNm+"&dictItemCode="+dictItemCode+"&page="+0+"&size="+10, {
            credentials: 'same-origin',
            method: 'get'
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            dispatch({
                type: types.DICT_ITEM_MGR_MODAL,
                dictItemMgrPage: json,
                isReload:isReload
            })
        }).catch(function(ex) {
            console.log('parsing failed', ex)
        });
    }
}

export function list(isAvailable,searchName,searchValue,page,size){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_DICT_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/sysDict/list.json?isAvailable='+isAvailable+'&'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get' })
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch({
                    type: types.PORTAL_DICT_LIST,
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




