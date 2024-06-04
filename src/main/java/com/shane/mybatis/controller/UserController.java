package com.shane.mybatis.controller;

import com.shane.mybatis.common.group.AddValidationGroup;
import com.shane.mybatis.common.group.EditValidationGroup;
import com.shane.mybatis.dto.ResponseResult;
import com.shane.mybatis.dto.UserParam;
import com.shane.mybatis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    public ResponseResult<Object> add(@Validated(AddValidationGroup.class) @RequestBody UserParam request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorList = new ArrayList<>();
            String fs = "Invalid Parameter : object - {%s}, field - {%s}, errorMessage - {%s}";
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                errorList.add(String.format(fs,fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));

                log.error("Invalid Parameter : object - {}, field - {}, errorMessage - {}",
                        fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseResult.fail(errorList, "invalid parameter");
        }

        return ResponseResult.success("OK");
    }

    @PutMapping("/")
    public ResponseResult<Object> edit(@Validated(EditValidationGroup.class) @RequestBody UserParam request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            List<String> errorList = new ArrayList<>();
            String fs = "Invalid Parameter : object - {%s}, field - {%s}, errorMessage - {%s}";
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                errorList.add(String.format(fs,fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()));

                log.error("Invalid Parameter : object - {}, field - {}, errorMessage - {}",
                        fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            return ResponseResult.fail(errorList, "invalid parameter");
        }

        return ResponseResult.success("OK");
    }
}
