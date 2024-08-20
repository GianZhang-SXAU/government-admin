package com.zja.sxau.govenmentadmin.mapper;


import com.zja.sxau.govenmentadmin.entity.Commit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommitMapper {

    @Select("SELECT * FROM comment")
    List<Commit> findAll();

    @Insert("INSERT INTO comment(user_id, service_id, content) VALUES(#{userId}, #{serviceId}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "commentId")
    void insert(Commit comment);

    @Delete("DELETE FROM comment WHERE comment_id = #{commentId}")
    void deleteById(int commentId);

    @Select("SELECT * FROM comment WHERE user_id = (SELECT user_id FROM users WHERE idcard = #{idCard})")
    List<Commit> findByUserIdCard(String idCard);

}
