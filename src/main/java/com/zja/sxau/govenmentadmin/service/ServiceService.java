package com.zja.sxau.govenmentadmin.service;



import com.zja.sxau.govenmentadmin.entity.Service;

import java.util.List;


public interface ServiceService{
    public List<com.zja.sxau.govenmentadmin.entity.Service> findAll();
    public Service findById(Integer serviceId);
    public void save(Service service);
    public void delete(Integer serviceId);
}