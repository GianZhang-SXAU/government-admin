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


    List<Commit> findByUserIdCard(String idcard);

    @Update("UPDATE comment SET user_id = #{userId}, service_id = #{serviceId}, content = #{content},created_at = #{createdAt} WHERE comment_id = #{commentId}")
    void updateCommentById(Commit comment);

}
