package com.zja.sxau.govenmentadmin.service.impl;

import com.zja.sxau.govenmentadmin.entity.Admin;
import com.zja.sxau.govenmentadmin.mapper.AdminMapper;
import com.zja.sxau.govenmentadmin.service.AdminService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void register(Admin admin) {
        admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
        adminMapper.insert(admin);
    }

    @Override
    public Admin login(String username, String password, HttpServletResponse response) {
        Admin admin = adminMapper.findByUsername(username);
        if (admin != null && admin.getPassword().equals(DigestUtils.md5Hex(password))) {
            String token = generateToken();
            redisTemplate.opsForValue().set(token, admin, 1, TimeUnit.HOURS);

            Cookie cookie = new Cookie("token", token);
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            return admin;
        }
        return null;
    }

    @Override
    public void updateProfile(Admin updatedAdmin) {
         adminMapper.updateProfile(updatedAdmin);
    }

    private String generateToken() {
        return DigestUtils.md5Hex(System.currentTimeMillis() + Math.random() + "");
    }
}
