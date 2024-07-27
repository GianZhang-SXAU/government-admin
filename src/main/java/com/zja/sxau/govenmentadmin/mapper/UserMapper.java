package com.zja.sxau.govenmentadmin.mapper;


import com.zja.sxau.govenmentadmin.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users")
    List<User> getAllUsers();

    @Select("SELECT * FROM users WHERE user_id = #{userId}")
    User getUserById(Integer userId);

    @Insert("INSERT INTO users(name, phone, idcard, password, location, city, district, province, work, profession) " +
            "VALUES(#{name}, #{phone}, #{idcard}, #{password}, #{location}, #{city}, #{district}, #{province}, #{work}, #{profession})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int insertUser(User user);

    @Update("UPDATE users SET name=#{name}, phone=#{phone}, idcard=#{idcard}, password=#{password}, location=#{location}, " +
            "city=#{city}, district=#{district}, province=#{province}, work=#{work}, profession=#{profession} WHERE user_id=#{userId}")
    int updateUser(User user);

    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    int deleteUser(Integer userId);
}
