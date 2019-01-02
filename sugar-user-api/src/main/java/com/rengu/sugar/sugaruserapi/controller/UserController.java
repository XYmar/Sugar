package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.Utils.ResultUtils;
import com.rengu.sugar.sugaruserapi.entity.ResultEntity;
import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import com.rengu.sugar.sugaruserapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    // 判断邮箱是否已被注册
    @GetMapping(value = "/has-email")
    public boolean hasUserByEmail(@RequestParam(value = "email") String email) {
        return userService.hasUserByEmail(email);
    }

    // 添加用户
    @PostMapping
    public void saveUser(UserEntity userEntity) {
        userService.saveUser(userEntity);
    }

    // 查询所有用户
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity getUsers() {
        return ResultUtils.build(userService.getUsers());
    }

    // 根据用户名查询用户
    @GetMapping(value = "/by-username")
    public UserEntity getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.getUserByUsername(username);
    }

    // 根据id查询用户
    @GetMapping(value = "/{userId}")
    public ResultEntity getUserById(@PathVariable(value = "userId") String userId) {
        return ResultUtils.build(userService.getUserById(userId));
    }

    // 根据角色id及验证码查询用户
    @GetMapping(value = "/activeCode/{activeCode}")
    public boolean getUserByEmailAndCode(@RequestParam(value = "email") String email, @PathVariable(value = "activeCode") String activeCode) {
        return userService.getUserByEmailAndCode(email, activeCode);
    }

    // 根据id修改用户
    @PutMapping(value = "/{userId}")
    public ResultEntity updateUserById(@PathVariable(value = "userId") String userId, UserEntity userEntity) {
        return ResultUtils.build(userService.updateUserById(userId, userEntity));
    }

    // 删除用户
    @DeleteMapping(value = "/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity deleteUserById(@PathVariable(value = "userId") String userId) {
        return ResultUtils.build(userService.deleteUserById(userId));
    }

    // 根据Id修改密码
    @PutMapping(value = "/{userId}/password")
    public ResultEntity updatePasswordById(@PathVariable(value = "userId") String userId, @RequestParam(value = "password") String password) {
        return ResultUtils.build(userService.updatePasswordById(userId, password));
    }

    // 根据Id修改角色
    @PutMapping(value = "/{userId}/role")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity updateRoleById(@PathVariable(value = "userId") String userId, @RequestParam(value = "roleId") String roleId) {
        return ResultUtils.build(userService.updateUserRoleById(userId, roleId));
    }

    // 根据Id激活用户
    @PutMapping(value = "/{userId}/active")
    public void updateEmailStateById(@PathVariable(value = "userId") String userId) {
        userService.updateEmailStateById(userId);
    }

}
