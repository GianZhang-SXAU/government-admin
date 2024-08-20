package com.zja.sxau.govenmentadmin.service.impl;


import com.zja.sxau.govenmentadmin.entity.Commit;
import com.zja.sxau.govenmentadmin.mapper.CommitMapper;
import com.zja.sxau.govenmentadmin.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *@Author: 张建安
 *@CreateTime: 2024-08-08
 */
@Service
public class CommitServiceImpl implements CommitService {


    @Autowired
    private CommitMapper commitMapper;

    @Override
    public List<Commit> getAllComments() {
        return commitMapper.findAll();
    }

    @Override
    public void createComment(Commit comment) {
        commitMapper.insert(comment);
    }

    @Override
    public void deleteComment(int commentId) {
        commitMapper.deleteById(commentId);
    }

    @Override
    public List<Commit> getCommentsByIdCard(String idCard) {
        return commitMapper.findByUserIdCard(idCard);
    }

    @Override
    public String sanitizeContent(String content) {
        // 简单的敏感词脱敏处理
        String[] sensitiveWords = {"badword1", "badword2"};
        for (String word : sensitiveWords) {
            content = content.replaceAll(word, "***");
        }
        return content;
    }

}
