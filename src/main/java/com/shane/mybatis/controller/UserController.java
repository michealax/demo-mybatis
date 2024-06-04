package com.shane.mybatis.controller;

import com.shane.mybatis.common.group.EditValidationGroup;
import com.shane.mybatis.dto.ResponseResult;
import com.shane.mybatis.dto.UserParam;
import com.shane.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public boolean updateUser(@PathVariable Integer id,
                              @RequestParam("time") Integer time) {
        return userService.updateUser(id, time);
    }

    @PostMapping("/")
    public ResponseResult<Object> add(@Valid @RequestBody UserParam request) {
        return ResponseResult.success("OK");
    }

    @PutMapping("/")
    public ResponseResult<Object> edit(@Validated({Default.class, EditValidationGroup.class}) @RequestBody UserParam request) {
        return ResponseResult.success("OK");
    }
}
