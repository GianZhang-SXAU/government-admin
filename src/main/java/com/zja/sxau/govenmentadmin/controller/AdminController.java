package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Admin;
import com.zja.sxau.govenmentadmin.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
        Admin admin = adminService.login(username, password, response);
        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            System.out.println("Login successful" + session);
            return "Login successful";
        }
        return "Invalid username or password";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/profile")
    public Admin getProfile(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new RuntimeException("Session expired or not logged in");
        }
        Admin admin = (Admin) session.getAttribute("user");
        if (admin == null) {
            throw new RuntimeException("Not logged in");
        }
        return admin;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/profile")
    public String updateProfile(@RequestBody Admin updatedAdmin, HttpServletRequest request) {
      /*
      * 8.25：张建安 不知道我当时脑子抽了还是咋了，为啥用了Session，我前几天咋想的
      * */
//        HttpSession session = request.getSession(false);
//        if (session == null) {
//            throw new RuntimeException("Session expired or not logged in");
//        }
//        Admin currentAdmin = (Admin) session.getAttribute("user");
//        if (currentAdmin == null) {
//            throw new RuntimeException("Not logged in");
//        }
//        updatedAdmin.setAdminId(currentAdmin.getAdminId());
        adminService.updateProfile(updatedAdmin);

        return "Profile updated";
    }
}
