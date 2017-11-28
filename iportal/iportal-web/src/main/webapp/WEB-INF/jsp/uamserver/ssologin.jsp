<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-COMPATIBLE" content="IE=edge,chrome=1"/>
    <title>登录并授权</title>
    <link rel="stylesheet" href="${iportal}/static/css/base.css" />
    <link rel="stylesheet" href="${iportal}/static/css/log-in.css" />

    <style>.error{color:red;}</style>
    <script src="${iportal}/static/lib/js/jquery-2.1.1.min.js"></script>
    <script src="${iportal}/static/lib/js/jquery.md5.js"></script>
    <script>
        function validation(){
            var username = $("#username").val();
            var passwordInput = $("#passwordInput").val();
            var validateCode = $("#validateCode").val();

            if(username == "" || username == null){
                $(".error").text("帐号必填！");
                return false;
            }

            if(passwordInput == "" || passwordInput == null){
                $(".error").text("密码必填！");
                return false;
            }

            if (validateCode == "" || validateCode == null) {
                $(".error").text("请输入验证码！");
                return false;
            }

            $("#password").val($.md5(passwordInput));
            return true;
        }

        $(document).ready(function(){
            //为看不清绑定监听事件
            $("#invisibility").click(function(){
                $("#validateCodeImg").attr("src", "<%=request.getContextPath()%>/ValidateCode?t=" + Math.random());
            });
            //为图片点击绑定监听事件
            $("#validateCodeImg").click(function(){
                $("#validateCodeImg").attr("src", "<%=request.getContextPath()%>/ValidateCode?t=" + Math.random());
            });
        })
    </script>

    <%--<script type="text/javascript">
        <c:forEach var="appContext" items="${sysAppContexts}">
        var ${appContext.webContext}="${appContext.hostname}/${appContext.webContext}";
        </c:forEach>
    </script>--%>

    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="mt">
        <h5>乐商电子商务管理系统</h5>
        <span>iMall ecommerce information management system</span>
    </div>
    <div class="mc">
        <form id="loginForm" action="" method="post" onsubmit="return validation()">
            <div class="cont">
                <div class="item">
                    <div class="pic"><img src="${iportal}/static/img/user30x30.png" alt=""></div>
                    <input type="text" class="form-control" id="username" name="username" value="<shiro:principal/>">
                </div>
                <div class="item">
                    <div class="pic"><img src="${iportal}/static/img/pass30x30.png" alt=""></div>
                    <input type="password" class="form-control" id="passwordInput" >
                    <input type="hidden" id="password" name="password">
                </div>
                <div class="item">
                    <div class="pic"><img src="${iportal}/static/img/code30x30.png" alt=""></div>
                    <input id="validateCode" name="validateCode" maxlength="4" type="text" class="input_bg" style="width:155px;" tabindex="4">
                    <div class="code-pic"><img id="validateCodeImg" alt="" src='<%=request.getContextPath()%>/ValidateCode?t=<%=Math.random()%>' style="cursor: pointer;" /></div>
                    <a id="invisibility" href="javascript:void(0)" class="change">看不清，换一张</a>
                </div>
                <div class="item">
                    <div class="error" style="height: 50px;">${error}</div>
                </div>
            </div>
        </form>
        <a href="javascript:;" class="login" onclick="$('#loginForm').submit();">登录</a>
    </div>
</div>
<canvas id="Mycanvas" style="background-color: #f7fafc; width: 100%;"></canvas>
<script>
    //定义画布宽高和生成点的个数
    var WIDTH = window.innerWidth, HEIGHT = window.innerHeight, POINT = 35;

    var canvas = document.getElementById('Mycanvas');
    canvas.width = WIDTH,
        canvas.height = HEIGHT;
    var context = canvas.getContext('2d');
    context.strokeStyle = 'rgba(0,0,0,0.2)',
        context.strokeWidth = 1,
        context.fillStyle = 'rgba(0,0,0,0.05)';
    var circleArr = [];

    //线条：开始xy坐标，结束xy坐标，线条透明度
    function Line (x, y, _x, _y, o) {
        this.beginX = x,
            this.beginY = y,
            this.closeX = _x,
            this.closeY = _y,
            this.o = o;
    }
    //点：圆心xy坐标，半径，每帧移动xy的距离
    function Circle (x, y, r, moveX, moveY) {
        this.x = x,
            this.y = y,
            this.r = r,
            this.moveX = moveX,
            this.moveY = moveY;
    }
    //生成max和min之间的随机数
    function num (max, _min) {
        var min = arguments[1] || 0;
        return Math.floor(Math.random()*(max-min+1)+min);
    }
    // 绘制原点
    function drawCricle (cxt, x, y, r, moveX, moveY) {
        var circle = new Circle(x, y, r, moveX, moveY)
        cxt.beginPath()
        cxt.arc(circle.x, circle.y, circle.r, 0, 2*Math.PI)
        cxt.closePath()
        cxt.fill();
        return circle;
    }
    //绘制线条
    function drawLine (cxt, x, y, _x, _y, o) {
        var line = new Line(x, y, _x, _y, o)
        cxt.beginPath()
        cxt.strokeStyle = 'rgba(0,0,0,'+ o +')'
        cxt.moveTo(line.beginX, line.beginY)
        cxt.lineTo(line.closeX, line.closeY)
        cxt.closePath()
        cxt.stroke();

    }
    //每帧绘制
    function draw () {
        context.clearRect(0,0,canvas.width, canvas.height);
        for (var i = 0; i < POINT; i++) {
            drawCricle(context, circleArr[i].x, circleArr[i].y, circleArr[i].r);
        }
        for (var i = 0; i < POINT; i++) {
            for (var j = 0; j < POINT; j++) {
                if (i + j < POINT) {
                    var A = Math.abs(circleArr[i+j].x - circleArr[i].x),
                        B = Math.abs(circleArr[i+j].y - circleArr[i].y);
                    var lineLength = Math.sqrt(A*A + B*B);
                    var C = 1/lineLength*7-0.009;
                    var lineOpacity = C > 0.03 ? 0.03 : C;
                    if (lineOpacity > 0) {
                        drawLine(context, circleArr[i].x, circleArr[i].y, circleArr[i+j].x, circleArr[i+j].y, lineOpacity);
                    }
                }
            }
        }
    }
    //初始化生成原点
    function init () {
        circleArr = [];
        for (var i = 0; i < POINT; i++) {
            circleArr.push(drawCricle(context, num(WIDTH), num(HEIGHT), num(15, 2), num(10, -10)/18, num(10, -10)/18));
        }
        draw();
    }
    //调用执行
    window.onload = function () {
        init();
        setInterval(function () {
            for (var i = 0; i < POINT; i++) {
                var cir = circleArr[i];
                cir.x += cir.moveX;
                cir.y += cir.moveY;
                if (cir.x > WIDTH) cir.x = 0;
                else if (cir.x < 0) cir.x = WIDTH;
                if (cir.y > HEIGHT) cir.y = 0;
                else if (cir.y < 0) cir.y = HEIGHT;
            }
            draw();
        }, 10);
    }
</script>
</body>

</html>
