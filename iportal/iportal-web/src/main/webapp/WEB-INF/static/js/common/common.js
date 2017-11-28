/**
 * Created by Donie on 2015/9/23.
 */
export const validate = values => {
    const errors = {}
    for(var i in values){
        var obj = $("#"+i);
        if($(obj).hasClass("required")){
            if (!values[i]) {
                errors[i] = "此项必填";
                return errors;
            }
        }
        const minlength =  $(obj).attr("data-min-length");
        const maxlength =  $(obj).attr("data-max-length");
        if (values[i]!=undefined&&minlength!=undefined&&values[i].length  < minlength) {
            errors[i] = "此项不能少于" + minlength + "个字符";
            return errors;
        }
        if (values[i]!=undefined&&maxlength!=undefined&&values[i].length > maxlength) {
            errors[i] = "此项不能大于" + maxlength + "个字符";
            return errors;
        }
    }
    return errors
};

export function showErrorMsg(response){
    return function (dispatch) {
        if(response.status!=200){
            console.log("showErrorMsg");
            response.text().then(function (text) {
                niftyNoty(text);
            });
        }
    }
}


export function niftyNoty(errorMsg, isError){
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

