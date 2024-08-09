package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.annotation.UseSparkModel;
import com.zja.sxau.govenmentadmin.entity.DTO.MessageRequestDTO;
import com.zja.sxau.govenmentadmin.service.SparkModelService;
import com.zja.sxau.govenmentadmin.utils.data.AIModelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class SparkModelController {

    @Autowired
    private SparkModelService sparkModelService;

    @UseSparkModel(AIModelData.HTTP)
    @PostMapping("/callSparkModel")
    public String callSparkModel(@RequestBody MessageRequestDTO request) {

     System.out.println(request.getMessage());
     System.out.println(sparkModelService.callSparkModel(request.getMessage()));

        // 这个方法的实现将被切面拦截，直接返回星火大模型的响应
//        return request.getMessage();
        return sparkModelService.callSparkModel(request.getMessage());
    }
}
