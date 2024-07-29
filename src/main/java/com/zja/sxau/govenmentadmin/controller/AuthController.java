package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Admin;
import com.zja.sxau.govenmentadmin.entity.User;
import com.zja.sxau.govenmentadmin.mapper.AuthMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


}
