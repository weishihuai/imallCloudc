package com.imall.iportal.core.main.web.servlet;

import com.imall.commons.base.global.Global;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * User: lxd
 * Date: 10-12-23
 * Time: 上午11:23
 * 生成验证码
 */
public class ValidateCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 4454212588015635042L;
    private static final String FONT = "Times New Roman";

//去除验证码混淆的字母和数字，若有需要，重新替换。
//    private static final String[] codes = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",  "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static final String[] codes = { "2","3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",  "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 创建图象
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font(FONT, Font.PLAIN, 18));

        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        StringBuffer sRandBuff = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(codes.length);
            String rand = String.valueOf(codes[index]);
            sRandBuff.append(rand);
            //String rand = String.valueOf(RAND_STRING.charAt(random.nextInt(10 + 26 + 26)));
            //将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }

        // 将认证码存入SESSION
        request.getSession().setAttribute(Global.VALIDATE_CODE, sRandBuff.toString());

        // 图象生效
        g.dispose();

        // 输出图象到页面
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    //给定范围获得随机颜色
    Color getRandColor(int fc, int bc) {
        Random random = new Random();
        int tempFc = fc;
        int tempBc = bc;
        if (fc > 255) {
            tempFc = 255;
        }
        if (bc > 255) {
            tempBc = 255;
        }
        int r = tempFc + random.nextInt(tempBc - tempFc);
        int g = tempFc + random.nextInt(tempBc - tempFc);
        int b = tempFc + random.nextInt(tempBc - tempFc);
        return new Color(r, g, b);
    }
}