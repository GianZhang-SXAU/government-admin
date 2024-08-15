//package com.zja.sxau.govenmentadmin.utils;
//
//
//import com.zja.sxau.govenmentadmin.entity.Service;
//import com.zja.sxau.govenmentadmin.mapper.ServiceMapper;
//import com.zja.sxau.govenmentadmin.service.ServiceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.socket.TextMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//
//
//public class ServiceWebSocketHandler extends TextWebSocketHandler {
//
//    /**
//     * @Author: 张建安
//     * @CreateTime: 2024-08-14
//     */
//
//    @Override
//    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        // 解析客户端消息
//        String serviceName = message.getPayload();
//
//        // 从 Neo4j 获取服务信息
//        Service service = serviceService.getServiceByName(serviceName);
//
//        // 将服务信息转换为 JSON 并发送回客户端
//        String response = convertServiceToJson(service);
//        session.sendMessage(new TextMessage(response));
//    }
//
//    private String convertServiceToJson(Service service) {
//        // 将 Service 对象转换为 JSON 字符串
//        // 这里可以使用 Jackson 或其他 JSON 序列化工具
//        return "";
//    }
//
//}
