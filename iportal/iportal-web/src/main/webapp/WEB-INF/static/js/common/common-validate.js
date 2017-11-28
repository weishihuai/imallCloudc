/**
 * Created by Administrator on 2016/7/8.
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
            if (values[i]=="请选择") {
                errors[i] = "此项必选";
                return errors;
            }
        }
        const minlength =  $(obj).attr("data-min-length");
        const maxlength =  $(obj).attr("data-max-length");
        const numberInput = $(obj).attr("data-number-input");
        if (values[i]!=undefined&&numberInput!=undefined&&!(new RegExp("^(0|[1-9]\\d*)$").test(values[i]))) {
            errors[i] = "请填写一个数字";
            return errors;
        }
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
