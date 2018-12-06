package com.rengu.sugar.sugaruserservice.controller;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
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
    public List<UserEntity> getUsers() {
        return userService.getAll();
    }

    // 根据Id查询用户
    @GetMapping(value = "/{userId}")
    public UserEntity getUserById(@PathVariable(value = "userId") String userId) {
        return userService.getUserById(userId);
    }

    // 根据Id修改用户
    @PatchMapping(value = "/{userId}")
    public UserEntity updateUserById(@PathVariable(value = "userId") String userId, UserEntity userEntity) {
        return userService.updateUserById(userId, userEntity);
    }

    // 根据Id修改密码
    @PatchMapping(value = "/{userId}/password")
    public UserEntity updatePasswordById(@PathVariable(value = "userId") String userId, @RequestParam(value = "password") String password) {
        return userService.updateUserPasswordById(userId, password);
    }

    // 删除用户
    @DeleteMapping(value = "/{userId}")
    public UserEntity deleteUserById(@PathVariable(value = "userId") String userId) {
        return userService.deleteUserById(userId);
    }
}
