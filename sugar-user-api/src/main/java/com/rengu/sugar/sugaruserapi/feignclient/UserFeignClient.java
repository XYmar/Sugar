package com.rengu.sugar.sugaruserapi.feignclient;

import com.rengu.sugar.sugaruserapi.entity.RoleEntity;
import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "sugar-user-service")
public interface UserFeignClient {
    /**
     * 用户相关
     */
    // 添加用户
    @PostMapping(value = "/user")
    UserEntity saveUser(UserEntity userEntity);

    // 查询所有用户
    @GetMapping(value = "/user")
    List<UserEntity> getUsers();

    // 根据Id查询用户
    @GetMapping(value = "/user/{userId}")
    UserEntity getUserById(@PathVariable(value = "userId") String userId);

    // 根据id修改用户
    @PatchMapping(value = "/user/{userId}")
    UserEntity updateUserById(@PathVariable(value = "userId") String userId, UserEntity userEntity);

    // 根据Id修改密码
    @PatchMapping(value = "/user/{userId}/password")
    UserEntity updatePasswordById(@PathVariable(value = "userId") String userId, @RequestParam(value = "password") String password);

    // 根据Id修改角色
    @PatchMapping(value = "/user/{userId}/role")
    UserEntity updateRoleById(@PathVariable(value = "userId") String userId, @RequestParam(value = "roleId") String roleId);

    // 删除用户
    @DeleteMapping(value = "/user/{userId}")
    UserEntity deleteUserById(@PathVariable(value = "userId") String userId);


    /**
     * 角色相关
     */

    // 添加角色信息
    @PostMapping(value = "/role")
    RoleEntity saveRole(RoleEntity roleEntity);

    // 查询所有角色
    @GetMapping(value = "/role")
    List<RoleEntity> getRoles();

    // 根据id查询角色
    @GetMapping(value = "/role/{roleId}")
    RoleEntity getRoleById(@PathVariable(value = "roleId") String id);

    // 根据id修改角色
    @PatchMapping(value = "/role/{roleId}")
    RoleEntity updateRoleById(@PathVariable(value = "roleId") String id, RoleEntity roleEntity);

    // 根据id删除角色
    @DeleteMapping(value = "/role/{roleId}")
    RoleEntity deleteRoleById(@PathVariable(value = "roleId") String id);
}
