package com.zja.sxau.govenmentadmin.mapper;


import com.zja.sxau.govenmentadmin.entity.Appointment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface AppointmentMapper {

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(int appointmentId);


    void insertAppointment(Appointment appointment);

    void updateAppointment(Appointment appointment);

    void deleteAppointment(int appointmentId);

}
