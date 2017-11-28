import * as types from "../constants/ActionTypes";

var indexInterval = undefined;

export function indexManageList( page, size){
    return function (dispatch) {
        const empty = {
            type: types.INDEX_MANAGE_LIST,
            data: {content:[],totalElements:0,number:1,size:10}
        };
        dispatch(empty);
        return fetch(iportal+'/indexManage/list.json?page='+page+'&size='+size, {credentials: 'same-origin', method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {

                var hasProcessing = false;
                json.content.map(function(result){
                    if(result.state=="PROCESSING"){
                        hasProcessing = true;
                    }
                });

                if(hasProcessing){
                    if(indexInterval == undefined){
                        indexInterval = setInterval(function(){
                            dispatch(indexManageList(0,20));
                        },5000);
                    }
                }else{
                    if(indexInterval!=undefined){
                        window.clearInterval(indexInterval);
                        indexInterval = undefined;
                    }
                }

                dispatch({
                    type: types.INDEX_MANAGE_LIST,
                    data: json
                })
            }).catch(function(ex) {
                console.log('parsing failed', ex)
            });
    }
}


export function rebuildIndex(id){
    return function (dispatch) {
        return fetch(iportal+'/indexManage/rebuildIndex.json?id='+id,{
            credentials: 'same-origin',
            method: 'get'})
            .then(function(response) {
                dispatch(showErrorMsg(response));
                return response.json();
            }).then(function(json) {
                dispatch(indexManageList(0,20));
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