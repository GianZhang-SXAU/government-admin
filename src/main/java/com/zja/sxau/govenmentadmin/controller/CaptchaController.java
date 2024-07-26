package com.zja.sxau.govenmentadmin.controller;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.font.RandomFontFactory;

import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*")
@Controller
public class CaptchaController {

//    @RestController
//    @RequestMapping("/img")
//    public class CheckCodeServlet {
//
//        @RequestMapping("/{params}")
//        public void getVerificationCode(HttpServletResponse response, HttpServletRequest request) {
//            try {
//                int width=200;
//                int height=69;
//                BufferedImage verifyImg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
//                //生成对应宽高的初始图片
//                String randomText = VerifyCode.drawRandomText(width,height,verifyImg);
//                //单独的一个类方法，出于代码复用考虑，进行了封装。
//                //功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符
//                request.getSession().setAttribute("verifyCode", randomText);
//                response.setContentType("image/png");//必须设置响应内容类型为图片，否则前台不识别
//                OutputStream os = response.getOutputStream(); //获取文件输出流
//                ImageIO.write(verifyImg,"png",os);//输出图片流
//                os.flush();
//                os.close();//关闭流
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    @Autowired
    private StringRedisTemplate redisTemplate;

    private ConfigurableCaptchaService captchaService;

    public CaptchaController() {
        captchaService = new ConfigurableCaptchaService();
        captchaService.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        captchaService.setFontFactory(new RandomFontFactory(32, new String[]{"Verdana"}));
    }

    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        response.setContentType("image/png");
        String captcha = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", response.getOutputStream());
        redisTemplate.opsForValue().set("captcha:" + uuid, captcha, 5, TimeUnit.MINUTES);
    }

    @GetMapping("/verifyCaptcha")
    public boolean verifyCaptcha(@RequestParam String uuid, @RequestParam String code) {
        String captcha = redisTemplate.opsForValue().get("captcha:" + uuid);
        return captcha != null && captcha.equalsIgnoreCase(code);
    }
    }
