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

    // 添加用户
    @PostMapping
    public ResultEntity saveUser(UserEntity userEntity) {
        return ResultUtils.build(userService.saveUser(userEntity));
    }

    // 查询所有用户
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity getUsers() {
        return ResultUtils.build(userService.getUsers());
    }

    // 根据id查询用户
    @GetMapping(value = "/{userId}")
    public ResultEntity getUserById(@PathVariable(value = "userId") String userId) {
        return ResultUtils.build(userService.getUserById(userId));
    }

    // 根据id修改用户
    @PatchMapping(value = "/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity updateUserById(@PathVariable(value = "userId") String userId, UserEntity userEntity) {
        return ResultUtils.build(userService.updateUserById(userId, userEntity));
    }

    // 删除用户
    @DeleteMapping(value = "/user/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity deleteUserById(@PathVariable(value = "userId") String userId) {
        return ResultUtils.build(userService.deleteUserById(userId));
    }

    // 根据Id修改密码
    @PatchMapping(value = "/{userId}/password")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity updatePasswordById(@PathVariable(value = "userId") String userId, @RequestParam(value = "password") String password) {
        return ResultUtils.build(userService.updatePasswordById(userId, password));
    }

    // 根据Id修改角色
    @PatchMapping(value = "/user/{userId}/role")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity updateRoleById(@PathVariable(value = "userId") String userId, @RequestParam(value = "roleId") String roleId) {
        return ResultUtils.build(userService.updateUserRoleById(userId, roleId));
    }

}
