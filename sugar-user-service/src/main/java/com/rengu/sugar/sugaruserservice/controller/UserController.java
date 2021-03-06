package com.rengu.sugar.sugaruserservice.controller;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void saveUser(@RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
    }

    // 查询所有用户
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public List<UserEntity> getUsers() {
        return userService.getAll();
    }

    // 根据Id查询用户
    @GetMapping(value = "/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public UserEntity getUserById(@PathVariable(value = "userId") String userId) {
        return userService.getUserById(userId);
    }

    // 根据用户名查询用户
    @GetMapping(value = "/by-username")
    public UserEntity getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.getUserByUsername(username);
    }

    // 根据角色id查询用户
    @GetMapping(value = "/role/{roleId}")
    public List<UserEntity> getUserByRoleId(@PathVariable(value = "roleId") String roleId) {
        return userService.getUserByRoleId(roleId);
    }

    // 根据角色id及验证码查询用户
    @GetMapping(value = "/activeCode/{activeCode}")
    public boolean getUserByIdAndCode(@RequestParam(value = "email") String email, @PathVariable(value = "activeCode") String activeCode) {
        return userService.getUserByEmailAndCode(email, activeCode);
    }

    // 根据Id修改用户
    @PutMapping(value = "/{userId}")
    public UserEntity updateUserById(@PathVariable(value = "userId") String userId, @RequestBody UserEntity userEntity) {
        return userService.updateUserById(userId, userEntity);
    }

    // 根据Id修改密码
    @PutMapping(value = "/{userId}/password")
    public UserEntity updatePasswordById(@PathVariable(value = "userId") String userId, @RequestParam(value = "password") String password) {
        return userService.updateUserPasswordById(userId, password);
    }

    // 根据Id修改角色
    @PutMapping(value = "/{userId}/role")
    @PreAuthorize(value = "hasRole('admin')")
    public UserEntity updateRoleById(@PathVariable(value = "userId") String userId, @RequestParam(value = "roleId") String roleId) {
        return userService.updateUserRoleById(userId, roleId);
    }

    // 根据Id激活用户
    @PutMapping(value = "/{userId}/active")
    public void updateEmailStateById(@PathVariable(value = "userId") String userId) {
        userService.updateEmailStateById(userId);
    }


    // 删除用户
    @DeleteMapping(value = "/{userId}")
    @PreAuthorize(value = "hasRole('admin')")
    public UserEntity deleteUserById(@PathVariable(value = "userId") String userId) {
        return userService.deleteUserById(userId);
    }

    // 根据用户名查看用户是否存在
    @GetMapping(value = "/has-username")
    public boolean hasUserByUsername(@RequestParam(value = "username") String username) {
        return userService.hasUserByUsername(username);
    }

    // 查看邮箱是否已被注册；根据邮箱查看用户是否存在
    @GetMapping(value = "/has-email")
    public boolean hasUserByEmail(@RequestParam(value = "email") String email) {
        return userService.hasUserByEmail(email);
    }
}
