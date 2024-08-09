package com.zja.sxau.govenmentadmin;

import cn.dustlight.captcha.annotations.EnableCaptcha;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaptcha // 启用 CAPTCHA
@MapperScan("com.zja.sxau.govenmentadmin.mapper")
public class GovenmentAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovenmentAdminApplication.class, args);
    }

}
