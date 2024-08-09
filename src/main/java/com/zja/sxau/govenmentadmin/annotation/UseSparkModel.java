package com.zja.sxau.govenmentadmin.annotation;

import com.zja.sxau.govenmentadmin.utils.data.AIModelData;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 运行时保留
@Target(ElementType.METHOD) // 用于方法
public @interface UseSparkModel {
    AIModelData value(); // 使用枚举类型来表示调用方式
}
