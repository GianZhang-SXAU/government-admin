package com.zja.sxau.govenmentadmin.entity;

import lombok.Data;

import java.util.Date;


@Data
public class Queue {
    private int queueId;
    private int appointmentId;
    private int queueNumber;
    private String queueStatus;
    private Date calledTime;
    private int windowId;
}
