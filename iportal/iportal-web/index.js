$(function() {
    $("#id").click(function () {
       $(".aaa").addClass("on");
       $(".bbb").removeClass("off");
       $(".ccc").removeClass("off");
    });


    $("a[class=ddd]").click(function () {
        // $(this).val();
        let sexCode = $(this).attr("rel");
        $("#id").val(sexCode);
    });

});

function save() {
    let username = $("#username").val();
    let password = $("#password").val();

    if (username === null || username === "") {
        return false;
    }

    if (password === null || password === "") {
        return false;
    }
    //......
}

$(function() {
    $(".layer2 .del").click(function () {
        $(this).prev().val("");   //前一个同胞 this.prev();
    });
});


$(document).ready(function () {
    $(".layer-bg .close-btn").click(function () {
       $("#id").hide();  //隐藏
    });

    //提交
    $(".md .submit").click(function(){
        let mobile = $.trim($("#id").val());   //去空格处理

        let strP = /^1\d{10}$/;    /*1开头 11位  正则表达式*/
        if (!strP.test(mobile)) {
            //......
        }
    })
});

$(function () {

   $("#id").click(function () {
      if($("#id").hasClass("on")) {  //判断是否包含某个class
          $("#id").removeClass("on");
      }else {
          $("#id").addClass("on");
      }
   });

});