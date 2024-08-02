package com.zja.sxau.govenmentadmin.entity.VO;

import com.zja.sxau.govenmentadmin.entity.Queue;
import lombok.Data;

@Data
public class QueueResponse {

    /**
     * @Author: 张建安
     * @CreateTime: 2024-08-02
     */
    private String message;
    private Queue queue;

    public QueueResponse(String message, Queue queue) {
        this.message = message;
        this.queue = queue;
    }

}
