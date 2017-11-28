/**
 * Created by ygw on 2016/6/13.
 */
/**
 *
 * @param type 文件类型 'css' or 'js'
 * @param file 文件路径 '/static/lib/js/nifty.js'
 * @param time 时间 3000
 * @param async 是否异步 false or true
 */
function lazyloadWithWait(id, type, file, time, async){

    //window.onload = function () {

        setTimeout(function () {
            lazyload(id, type, file, async)
        }, time);
   // };
}

function lazyload(id, type, file, async){

    // reference to <head>
    var head = document.getElementsByTagName('head')[0];

    if(type=='css'){
        // a new CSS
        var css = document.createElement('link');
        css.type = "text/css";
        css.rel = "stylesheet";
        css.href = file;
        css.async = async;
        head.appendChild(css);
    }
    else {
        // a new JS
        var oldjs = document.getElementById(id);
        if(oldjs) oldjs.parentNode.removeChild(oldjs);
        var scriptObj = document.createElement("script");
        scriptObj.type = "text/javascript";
        scriptObj.src = file;
        scriptObj.async = async;
        scriptObj.id  = id;
        scriptObj.onload = function(){
            console.log("加载" + file);
        };
        head.appendChild(scriptObj);
    }
}