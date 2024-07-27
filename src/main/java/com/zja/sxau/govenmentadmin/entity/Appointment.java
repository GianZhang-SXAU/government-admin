package com.zja.sxau.govenmentadmin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private Integer appointmentId;
    private Integer serviceId;
    private String phoneNumber;
    private String documentType;
    private String documentNumber;
    private Date appointmentDate;
    private Date appointmentTime;
    private String status;
}
