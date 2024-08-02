package com.zja.sxau.govenmentadmin.entity;

import lombok.Data;

import java.util.Date;


@Data
public class Queue {
    private Integer queueId;
    private Integer appointmentId;
    private Integer windowId;
    private Integer queueNumber;
    private String queueStatus;
    private Date calledTime;
    private String orderNumber;
}
