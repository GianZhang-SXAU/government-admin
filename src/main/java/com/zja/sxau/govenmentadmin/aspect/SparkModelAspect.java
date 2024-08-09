package com.zja.sxau.govenmentadmin.aspect;

import com.zja.sxau.govenmentadmin.annotation.UseSparkModel;
import com.zja.sxau.govenmentadmin.service.SparkModelService;
import com.zja.sxau.govenmentadmin.utils.data.AIModelData;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
@Component
public class SparkModelAspect {
//定义一个切面做拦截器，若添加@useSparkMode注解，则拦截请求，调用方法，向星火大模型发送请求
    @Autowired
    private SparkModelService sparkModelService;

    /*
    *
    * 将使用注解useSparkModel注解的设置为切点
    * */

    @Around("@annotation(UseSparkModel)")
    public Object around(ProceedingJoinPoint joinPoint, UseSparkModel UseSparkModel) throws Throwable {
        // 获取方法参数
        Object[] args = joinPoint.getArgs();

        // 假设消息文本是第一个参数，且为String类型
        if (args.length > 0 && args[0] instanceof String) {
            String message = (String) args[0];

            // 调用星火大模型
            String response = sparkModelService.callSparkModel(message);

            // 可以在这里处理返回值，也可以直接返回调用结果
            System.out.println("星火模型响应: " + response);
        }

        // 继续执行被注解的方法
        return joinPoint.proceed();
    }
}
