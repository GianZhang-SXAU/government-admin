package com.zja.sxau.govenmentadmin.controller;

import com.github.bingoohuang.patchca.color.SingleColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.word.RandomWordFactory;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {

    @Autowired
    private StringRedisTemplate redisTemplate; // 用于操作Redis数据库的模板类

    private ConfigurableCaptchaService captchaService; // 验证码生成服务

    // 构造方法中初始化验证码生成服务
    public CaptchaController() {
        captchaService = new ConfigurableCaptchaService();

        // 设置验证码图片的颜色
        captchaService.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));

        // 设置验证码的字体和大小
        captchaService.setFontFactory(new RandomFontFactory(32, new String[]{"Verdana"}));

        // 设置验证码的字符生成规则
        RandomWordFactory wordFactory = new RandomWordFactory();
        wordFactory.setCharacters("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"); // 允许的字符
        wordFactory.setMinLength(4); // 验证码最小长度
        wordFactory.setMaxLength(4); // 验证码最大长度
        captchaService.setWordFactory(wordFactory);
    }

    /**
     * 获取验证码图片
     * @author: 张建安
     * 请求方式：GET
     * 接口路径：/captcha
     * @param: uuid: String, 用于标识特定用户的唯一标识符
     * @return: image/png
     * 用途：生成一个验证码图片，并将验证码存储到Redis中，关联到用户的UUID，验证码有效期为5分钟
     */
    @CrossOrigin(origins = "*") // 允许跨域访问
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        response.setContentType("image/png"); // 设置响应内容类型为PNG图片
        String captcha = EncoderHelper.getChallangeAndWriteImage(captchaService, "png", response.getOutputStream()); // 生成验证码并写入响应流
        redisTemplate.opsForValue().set("captcha:" + uuid, captcha, 5, TimeUnit.MINUTES); // 将验证码存储到Redis中，有效期5分钟
    }

    /**
     * 验证用户输入的验证码是否正确
     * @author: 张建安
     * 请求方式：GET
     * 接口路径：/verifyCaptcha
     * 参数：
     *  - uuid: String, 用户的唯一标识符，用于获取存储在Redis中的验证码
     *  - code: String, 用户输入的验证码
     * 返回值：
     *  - ResponseEntity<Boolean>: 如果验证码正确返回true，否则返回false
     * 用途：校验用户输入的验证码是否与Redis中存储的验证码匹配
     */
    @CrossOrigin(origins = "*") // 允许跨域访问
    @GetMapping("/verifyCaptcha")
    public ResponseEntity<Boolean> verifyCaptcha(@RequestParam String uuid, @RequestParam String code) {
        String captcha = redisTemplate.opsForValue().get("captcha:" + uuid); // 从Redis中获取验证码
        boolean isValid = captcha != null && captcha.equalsIgnoreCase(code); // 比较验证码是否正确
        return ResponseEntity.ok(isValid); // 返回验证结果
    }
}

