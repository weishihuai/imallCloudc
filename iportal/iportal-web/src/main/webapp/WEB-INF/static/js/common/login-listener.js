/**
 * Created by ygw on 2017/8/17.
 */

$(document).ready(function(){
    function niftyNoty(errorMsg, isError, callbackFunc){
        // reference to <head>
        var id = "notyLayer";
        var body = document.getElementsByTagName('body')[0];
        var oldjs = document.getElementById(id);
        if(oldjs) oldjs.parentNode.removeChild(oldjs);

        var layerDiv = document.createElement("div");
        layerDiv.id  = id;
        layerDiv.className = "layer";
        layerDiv.style = "z-index:1000";

        var layerBoxDiv = document.createElement("div");
        layerBoxDiv.className = isError ? "layer-box layer-text w860" : "layer-box layer-error w430";

        var layerHeaderDiv = document.createElement("div");
        layerHeaderDiv.className = "layer-header";

        var layerBodyDiv = document.createElement("div");
        layerBodyDiv.className = "layer-body";

        var layerTitleSpan = document.createElement("span");
        layerTitleSpan.innerHTML = isError ?  "错误" : "提示";

        var layerCloseA = document.createElement("a");
        layerCloseA.className = "close";
        layerCloseA.href = "javascript:void(0)";
        layerCloseA.onclick = function (e) {
            var oldjs = document.getElementById(id);
            if(oldjs) oldjs.parentNode.removeChild(oldjs);
            callbackFunc();
        };

        var layerMsgSpan = document.createElement("span");
        layerMsgSpan.innerHTML = errorMsg;

        layerHeaderDiv.appendChild(layerTitleSpan);
        layerHeaderDiv.appendChild(layerCloseA);
        layerBodyDiv.appendChild(layerMsgSpan);
        layerBoxDiv.appendChild(layerHeaderDiv);
        layerBoxDiv.appendChild(layerBodyDiv);
        layerDiv.appendChild(layerBoxDiv);
        body.appendChild(layerDiv);
    }

    var path = "http://localhost:8080/iportal".replace("http://", "ws://") + "/websocket/login";
    var ws = new WebSocket(path);
    ws.onopen = () => {
        // 打开一个连接
        //ws.send('something'); // 发送一个消息
    };

    ws.onmessage = (e) => {
        // 接收到了一个消息
        console.log(e.data);
        if(e.data==='cmd:expiration'){
            ws.send('cmd:close');
            niftyNoty("您2小时没操作，登录已失效...", false, function () {
                location.reload();
            });
        }
    };

    ws.onerror = (e) => {
        // 发生了一个错误
        console.log(e.message);
    };

    ws.onclose = (e) => {
        // 连接被关闭了
        console.log(e.code, e.reason);
    };
});
