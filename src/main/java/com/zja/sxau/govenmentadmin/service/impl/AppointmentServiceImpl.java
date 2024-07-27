package com.zja.sxau.govenmentadmin.service.impl;

import com.zja.sxau.govenmentadmin.entity.Appointment;
import com.zja.sxau.govenmentadmin.mapper.AppointmentMapper;
import com.zja.sxau.govenmentadmin.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentMapper.getAllAppointments();
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentMapper.getAppointmentById(appointmentId);
    }

    @Override
    public void createAppointment(Appointment appointment) {
        appointmentMapper.insertAppointment(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        appointmentMapper.updateAppointment(appointment);
    }

    @Override
    public void deleteAppointment(int appointmentId) {
        appointmentMapper.deleteAppointment(appointmentId);
    }
}
