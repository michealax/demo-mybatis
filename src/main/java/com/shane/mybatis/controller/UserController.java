package com.shane.mybatis.controller;

import com.shane.mybatis.common.group.EditValidationGroup;
import com.shane.mybatis.common.version.ApiVersion;
import com.shane.mybatis.dto.ResponseResult;
import com.shane.mybatis.dto.UserParam;
import com.shane.mybatis.entity.User;
import com.shane.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/{v}/user")
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

    @RequestMapping("get")
    public ResponseResult<User> getUser() {
        User user = User.builder()
                .name("shane [default]")
                .age(0)
                .build();
        return ResponseResult.success(user);
    }

    @RequestMapping("get")
    @ApiVersion("1")
    public ResponseResult<User> getUserV1() {
        User user = User.builder()
                .name("shane [v1]")
                .age(1)
                .build();
        return ResponseResult.success(user);
    }

    @RequestMapping("get")
    @ApiVersion("2")
    public ResponseResult<User> getUserV2() {
        User user = User.builder()
                .name("shane [v2]")
                .age(2)
                .build();
        return ResponseResult.success(user);
    }

    @RequestMapping("get")
    @ApiVersion("3")
    public ResponseResult<User> getUserV3() {
        User user = User.builder()
                .name("shane [v3]")
                .age(3)
                .build();
        return ResponseResult.success(user);
    }
}
