package com.zja.sxau.govenmentadmin.entity;

import lombok.Data;
import java.sql.Timestamp;

/**
 *@Author: 张建安
 *@CreateTime: 2024-08-08
 */

@Data
public class Commit {

    private Integer commentId;
    private Integer userId;
    private Integer serviceId;
    private String content;
    private Timestamp createdAt;

}
