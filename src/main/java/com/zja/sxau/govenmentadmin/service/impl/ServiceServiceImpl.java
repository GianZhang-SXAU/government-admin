package com.zja.sxau.govenmentadmin.service.impl;

import com.zja.sxau.govenmentadmin.entity.Service;
import com.zja.sxau.govenmentadmin.mapper.ServiceMapper;
import com.zja.sxau.govenmentadmin.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 服务管理
 * @author 张建安
 * */
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceMapper serviceMapper;

    public List<Service> findAll() {
        return serviceMapper.findAll();
    }

    public Service findById(Integer serviceId) {
        return serviceMapper.findById(serviceId);
    }

    public void save(Service service) {
        if (service.getServiceId() == null) {
            serviceMapper.insert(service);
        } else {
            serviceMapper.update(service);
        }
    }

    public void delete(Integer serviceId) {
        serviceMapper.delete(serviceId);
    }
}
