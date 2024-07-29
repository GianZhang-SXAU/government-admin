package com.zja.sxau.govenmentadmin.mapper;


import com.zja.sxau.govenmentadmin.entity.Admin;
import com.zja.sxau.govenmentadmin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AuthMapper {
    @Select("SELECT * FROM users WHERE name = #{username} AND password = #{password}")
    User findByUsernameAndPassword( String username, String password);

    @Select("SELECT * FROM admin WHERE username = #{username} AND password = #{password}")
    Admin findAdminByUsernameAndPassword(String username, String password);
}
