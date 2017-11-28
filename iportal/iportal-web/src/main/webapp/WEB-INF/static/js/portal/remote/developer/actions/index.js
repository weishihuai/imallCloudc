import * as types from "../constants/ActionTypes";

export function list(isAvailable,searchName,searchValue,page,size){
    return function (dispatch) {
        const empty = {
            type: types.PORTAL_DEVELOPER_AUTH_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/sysDeveloperAuth/list.json?isAvailable='+isAvailable+'&'+searchName+'='+searchValue+"&page="+page+"&size="+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            })
            .then(function(json) {
                dispatch({
                    type: types.PORTAL_DEVELOPER_AUTH_LIST,
                    data: json
                });
            })
            .catch(function(ex) {
                dispatch(empty);
                console.log('parsing failed', ex)
            });
    }

}

export function portalDeveloperAuthAddModal(){
    return function (dispatch) {
        dispatch(portalFindAbleApp());
        const empty = {
            type: types.PORTAL_DEVELOPER_AUTH_ADD_MODAL,
            developerData:{isAvailable:'Y'},
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
                    type: types.PORTAL_DEVELOPER_AUTH_ADD_MODAL,
                    data:{isAvailable:'Y'},
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

export function portalDeveloperAuthUpdateModal(id){
    return function (dispatch) {
        dispatch(portalFindAbleApp());
        const empty = {
            type: types.PORTAL_DEVELOPER_AUTH_UPDATE_MODAL,
            developerData: {}
        };
        if(id==undefined||id==null||isNaN(id)){
            return dispatch(empty);
        }
        return fetch(iportal+'/sysDeveloperAuth/findOne.json?id='+id, {
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            })
            .then(function(json) {
                dispatch({
                    type: types.PORTAL_DEVELOPER_AUTH_UPDATE_MODAL,
                    developerData: json
                })
            })
            .catch(function(ex) {
                dispatch(empty);
                console.log('parsing failed', ex)
            });
    }
}


export function portalDeveloperAuthDelete(developerIds,actions){
    if(developerIds.length<=0){
        $.niftyNoty({type: "danger", container : 'page', html : "系统消息：删除失败，请先选择要删除的信息", timer : 3000});
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
                    return fetch(iportal+"/sysDeveloperAuth/delete.json", {
                        credentials: 'same-origin',
                        method: 'POST',
                        headers: {
                            'Accept': 'application/json',
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(developerIds)
                    }).then(function(response) {
                        dispatch(showErrorMsg(response));
                        return response.json()
                    }).then(function(json) {
                        $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
                        dispatch({type: types.PORTAL_DEVELOPER_AUTH_LIST_MULTI_ROW_SELECT,developerIds:[]});
                        actions.list("","","",0,10);
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

export function developerAuthSaveUpdateData(developerData,isAdd,actions){
    return function (dispatch) {
        var url = iportal+"/sysDeveloperAuth/save.json";
        if(developerData.id!=undefined){
            url = iportal+"/sysDeveloperAuth/update.json?id="+developerData.id;
        }
        return fetch(url , {
            credentials: 'same-origin',
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(developerData)
        }).then(function(response) {
            dispatch(showErrorMsg(response));
            return response.json();
        }).then(function(json) {
            if(isAdd){
                dispatch({type:types.PORTAL_DEVELOPER_AUTH_ADD_MODAL});
            }else{
                dispatch({type: types.PORTAL_DEVELOPER_AUTH_UPDATE_MODAL,developerData: {}});
            }
            $.niftyNoty({type: "success", container : 'page', html : "系统消息：操作成功", timer : 3000});
            actions.list("","","",0,20);
            console.log('parsed json', json)
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
