package com.zja.sxau.govenmentadmin.service;

import com.zja.sxau.govenmentadmin.entity.DTO.LoginDTO;
import com.zja.sxau.govenmentadmin.entity.User;

public interface UserService {
    User login(String phone, String idCard, String password);
}
