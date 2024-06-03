package com.shane.mybatis.controller;

import com.shane.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public boolean updateUser(@PathVariable Integer id,
                             @RequestParam("time") Integer time) {
        return userService.updateUser(id, time);
    }
}
