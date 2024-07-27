package com.zja.sxau.govenmentadmin.controller;



import com.zja.sxau.govenmentadmin.entity.Service;
import com.zja.sxau.govenmentadmin.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<Service> findAll() {
        System.out.println(serviceService.findAll());
        return serviceService.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Service findById(@PathVariable Integer id) {
        return serviceService.findById(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping
    public void save(@RequestBody Service service) {
        serviceService.save(service);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        serviceService.delete(id);
    }
}

