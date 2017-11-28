document.ready = function (callback) {
    ///兼容FF,Google
    if (document.addEventListener) {
        document.addEventListener('DOMContentLoaded', function () {
            document.removeEventListener('DOMContentLoaded', arguments.callee, false);
            callback();
        }, false)
    }
    //兼容IE
    else if (document.attachEvent) {
        document.attachEvent('onreadytstatechange', function () {
            if (document.readyState == "complete") {
                document.detachEvent("onreadystatechange", arguments.callee);
                callback();
            }
        })
    }
    else if (document.lastChild == document.body) {
        callback();
    }
}
function resizeFontSize(){
    var clientWidth = document.body.clientWidth;
    var x = 0;
    /*if(clientWidth >= 1024){
        x = '32px';
    }else */if(clientWidth <= 320){
        x = '10px';
    }else{
        x = clientWidth * 0.03125 + 'px';
    }
    document.querySelector('html').setAttribute('style','font-size: '+ x +';');
}
document.ready(resizeFontSize);
window.onresize =  function(){
    setTimeout(function(){
        resizeFontSize();
    }, 400);
};