package com.zja.sxau.govenmentadmin.entity;

import lombok.Data;

@Data
public class Admin {
    private Integer adminId;
    private String username;
    private String password;
    private String email;
    private String phone;
}
