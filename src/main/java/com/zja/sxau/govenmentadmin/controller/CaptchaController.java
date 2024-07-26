package com.zja.sxau.govenmentadmin.controller;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@CrossOrigin(origins = "*")
@Controller
public class CaptchaController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ConfigurableCaptchaService captchaService;

    public CaptchaController() {
        captchaService = new ConfigurableCaptchaService();
        captchaService.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        captchaService.setFontFactory(new RandomFontFactory(32, new String[]{"Verdana"}));

        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
        wordFactory.setMinLength(4);
        wordFactory.setMaxLength(4);
        captchaService.setWordFactory(wordFactory);
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
