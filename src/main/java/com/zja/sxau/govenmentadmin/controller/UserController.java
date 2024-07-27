package com.zja.sxau.govenmentadmin.controller;

import com.zja.sxau.govenmentadmin.entity.User;
import com.zja.sxau.govenmentadmin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Integer userId) {
        return userMapper.getUserById(userId);
    }

    @PostMapping
    public int insertUser(@RequestBody User user) {
        return userMapper.insertUser(user);
    }

    @PutMapping("/{userId}")
    public int updateUser(@PathVariable Integer userId, @RequestBody User user) {
        user.setUserId(userId);
        return userMapper.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public int deleteUser(@PathVariable Integer userId) {
        return userMapper.deleteUser(userId);
    }
}