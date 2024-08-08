package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Admin;
import com.zja.sxau.govenmentadmin.entity.User;
import com.zja.sxau.govenmentadmin.mapper.AuthMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthMapper authMapper;

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> payload) {
       String username = payload.get("username");
       String password = DigestUtils.md5Hex(payload.get("password"));

        User user = authMapper.findByUsernameAndPassword(username, password);
        if (user != null) {
            Map<String, Object> response = new HashMap<>();
            user.setPassword(null);  //为了安全起见，不返回密码
            response.put("type", "user");
            response.put("user", user);
            return response;
        }
        Admin admin = authMapper.findAdminByUsernameAndPassword(username, password);
        if (admin != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("type", "admin");
            response.put("admin", admin);
            return response;
        }

    return null;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");
        String username = payload.get("username");
        String password = DigestUtils.md5Hex(payload.get("password"));
        String email = payload.get("email");
        String phone = payload.get("phone");

        if ("admin".equals(type)) {
            Admin admin = new Admin();
            admin.setUsername(username);
            admin.setPassword(password);
            admin.setEmail(email);
            admin.setPhone(phone);

            int result = authMapper.insertAdmin(admin);
            if (result > 0) {
                return ResponseEntity.ok().body(Map.of("success", true, "message", "管理员注册成功!"));
            }
        } else if ("user".equals(type)) {
            User user = new User();
            user.setName(payload.get("name"));
            user.setPhone(phone);
            user.setIdCard(payload.get("idCard"));
            user.setPassword(password);
            user.setLocation(payload.get("location"));
            user.setCity(payload.get("city"));
            user.setDistrict(payload.get("district"));
            user.setProvince(payload.get("province"));
            user.setWork(payload.get("work"));
            user.setProfession(payload.get("profession"));

            int result = authMapper.insertUser(user);
            if (result > 0) {
                return ResponseEntity.ok().body(Map.of("success", true, "message", "用户注册成功!"));
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "message", "注册失败，请重试！"));
    }

}
