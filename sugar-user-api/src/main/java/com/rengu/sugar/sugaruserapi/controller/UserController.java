package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import com.rengu.sugar.sugaruserapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author: XYmar
 * Date: 2018/12/10 16:42
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 添加用户
    @PostMapping
    public UserEntity saveUser(UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

}
