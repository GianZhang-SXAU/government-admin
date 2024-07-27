package com.zja.sxau.govenmentadmin.mapper;

import com.zja.sxau.govenmentadmin.entity.Admin;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM admin WHERE username = #{username}")
    Admin findByUsername(String username);

    @Insert("INSERT INTO admin(username, password, email, phone) VALUES(#{username}, #{password}, #{email}, #{phone})")
    @Options(useGeneratedKeys = true, keyProperty = "adminId")
    void insert(Admin admin);

    @Update("UPDATE admin SET email = #{email} , phone = #{phone} WHERE admin_id = #{adminId}")
    void updateProfile(Admin updatedAdmin);
}
