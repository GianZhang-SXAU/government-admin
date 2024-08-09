package com.zja.sxau.govenmentadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SparkConfig {

    /**
     * @Author: 张建安
     * @CreateTime: 2024-08-09
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
