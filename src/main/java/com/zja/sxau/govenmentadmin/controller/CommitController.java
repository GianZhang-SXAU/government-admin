package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Commit;
import com.zja.sxau.govenmentadmin.fliter.SensitiveWordFilter;
import com.zja.sxau.govenmentadmin.service.CommitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *@Author: 张建安
 *@CreateTime: 2024-08-08
 */

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/commit")
public class CommitController {

    @Autowired
    private CommitService commitService;
    @Autowired
    private SensitiveWordFilter sensitiveWordFilter;

    @GetMapping("/comments")
    public List<Commit> getAllComments() {
        return commitService.getAllComments();
    }

    @PostMapping("/comment")
    public void createComment(@RequestBody Commit comment) {
        // 对评论内容进行敏感词脱敏
        comment.setContent(sensitiveWordFilter.sanitizeContent(comment.getContent()));
        System.out.println(comment.getContent());
        commitService.createComment(comment);
    }

    @DeleteMapping("/comment/{id}")
    public void deleteComment(@PathVariable("id") int commentId) {
        commitService.deleteComment(commentId);
    }

    @GetMapping("/comment/user")
    public List<Commit> getCommentsByIdCard(@RequestParam("idCard") String idCard) {
        return commitService.getCommentsByIdCard(idCard);
    }



}
