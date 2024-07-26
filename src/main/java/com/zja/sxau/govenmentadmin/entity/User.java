package com.zja.sxau.govenmentadmin.entity;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String name;
    private String phone;
    private String idCard;
    private String password;
    private String location;
    private String city;
    private String district;
    private String province;
    private String work;
    private String profession;
}
