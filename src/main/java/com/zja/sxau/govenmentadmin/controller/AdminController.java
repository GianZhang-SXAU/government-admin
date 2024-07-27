package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Admin;
import com.zja.sxau.govenmentadmin.service.AdminService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @CrossOrigin(origins = "*")
    @PostMapping("/register")
    public String register(@RequestBody Admin admin) {
        adminService.register(admin);
        return "Register successful";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        Admin admin = adminService.login(username, password, response);
        if (admin != null) {
            return "Login successful";
        }
        return "Invalid username or password";
    }
}