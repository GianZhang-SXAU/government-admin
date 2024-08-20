package com.zja.sxau.govenmentadmin.service;


import com.zja.sxau.govenmentadmin.entity.Commit;

import java.util.List;

/**
 *@Author: 张建安
 *@CreateTime: 2024-08-08
 */
public interface CommitService {
    public List<Commit> getAllComments();
    public void createComment(Commit comment);
    public void deleteComment(int commentId);

    public List<Commit> getCommentsByIdCard(String idCard);
    public String sanitizeContent(String content);
}
