package com.zja.sxau.govenmentadmin.utils;

import java.security.MessageDigest;

public class MD5Util {
    /**
     *@Author: 张建安
     *@CreateTime: 2024-8-2
     *@Discription: MD5密码加密转发工具类
     */
    public static String md5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}