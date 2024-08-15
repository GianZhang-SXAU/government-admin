//package com.zja.sxau.govenmentadmin.config;
//
//import com.zja.sxau.govenmentadmin.utils.ServiceWebSocketHandler;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//
///**
// *@Author: 张建安
// *@CreateTime: 2024-08-13
// */
//
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new ServiceWebSocketHandler(), "/ws/services")
//                .setAllowedOrigins("*");
//    }
//
//
//
//}
