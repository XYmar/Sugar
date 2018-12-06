package com.rengu.sugar.sugaruserservice.controller;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public UserEntity saveUser(UserEntity userEntity) {
        return userService.saveUser(userEntity);
    }

    @GetMapping
    public List<UserEntity> getUsers() {
        return userService.getAll();
    }

    // 根据Id查询用户
    @GetMapping(value = "/{userId}")
    public UserEntity getUserById(@PathVariable(value = "userId") String userId) {
        return userService.getUserById(userId);
    }

    // 根据Id修改设备
    @PatchMapping(value = "/{userId}")
    public UserEntity updateUserById(@PathVariable(value = "userId") String deviceId, UserEntity userEntity) {
        return userService.updateUserById(deviceId, userEntity);
    }
}
