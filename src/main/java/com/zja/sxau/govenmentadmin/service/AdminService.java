package com.zja.sxau.govenmentadmin.service;

import com.zja.sxau.govenmentadmin.entity.Admin;
import jakarta.servlet.http.HttpServletResponse;

public interface AdminService {
    public void register(Admin admin);
    public Admin login(String username, String password, HttpServletResponse response);
}
