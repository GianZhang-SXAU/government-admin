package com.zja.sxau.govenmentadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Service
public class SparkModelService {
    /**
     * @Author: 张建安
     * @CreateTime: 2024-08-09
     */
    @Autowired
    private RestTemplate restTemplate;
    private final String apiKey = "df3243ca6455dbd4ca36f1eac6c11480";
    private final String apiSecret = "NzhmZWRmMzBiZDU5MDkzNWU0OTU3MjU5";
    private final String apiUrl = "https://spark-api-open.xf-yun.com/v1/chat/completions"; // 星火大模型的API地址
    private final String apiPassword = "bDAtSHjtKimPiFkzgzBO:iWzeTPzhvNxECGmeecpu"; // 替换为实际的API密码

    public String callSparkModel(String message) {
        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey + ":" + apiSecret);
        headers.set("Content-Type", "application/json");

        // 设置请求体
        String requestBody = String.format(
                "{ \"model\": \"general\", \"messages\": [{ \"role\": \"user\", \"content\": \"%s\" }] }", message
        );

        // 创建HttpEntity对象
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // 发起HTTP请求
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                String.class
        );

        // 返回响应体
        return response.getBody();
    }
}

