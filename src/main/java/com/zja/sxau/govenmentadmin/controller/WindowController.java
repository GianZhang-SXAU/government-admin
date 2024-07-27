package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.Window;
import com.zja.sxau.govenmentadmin.mapper.WindowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/windows")
public class WindowController {

    @Autowired
    private WindowMapper windowMapper;

    @GetMapping
    public List<Window> getAllWindows() {
        return windowMapper.getAllWindows();
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public Window getWindowById(@PathVariable int id) {
        return windowMapper.getWindowById(id);
    }

    @PostMapping
    public void addWindow(@RequestBody Window window) {
        windowMapper.insertWindow(window);
    }
    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public void updateWindow(@PathVariable int id, @RequestBody Window window) {
        window.setWindowId(id);
        windowMapper.updateWindow(window);
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public void deleteWindow(@PathVariable int id) {
        windowMapper.deleteWindow(id);
    }
}
