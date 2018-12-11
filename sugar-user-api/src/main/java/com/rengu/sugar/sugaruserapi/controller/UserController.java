package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import com.rengu.sugar.sugaruserapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 查询所有用户
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public List<UserEntity> getUsers() {
        return userService.getUsers();
    }

    // 根据id查询用户
    @GetMapping(value = "/{userId}")
    public UserEntity getUserById(@PathVariable(value = "userId") String userId) {
        return userService.getUserById(userId);
    }

    // 根据id修改用户
    @PatchMapping(value = "/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public UserEntity updateUserById(@PathVariable(value = "userId") String userId, UserEntity userEntity) {
        return userService.updateUserById(userId, userEntity);
    }

    // 删除用户
    @DeleteMapping(value = "/user/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public UserEntity deleteUserById(@PathVariable(value = "userId") String userId) {
        return userService.deleteUserById(userId);
    }

}
