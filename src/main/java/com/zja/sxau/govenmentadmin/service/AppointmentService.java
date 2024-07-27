package com.zja.sxau.govenmentadmin.service;

import com.zja.sxau.govenmentadmin.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    public List<Appointment> getAllAppointments();
    public Appointment getAppointmentById(int appointmentId);
    public void createAppointment(Appointment appointment);
    public void updateAppointment(Appointment appointment);
    public void deleteAppointment(int appointmentId);
}
