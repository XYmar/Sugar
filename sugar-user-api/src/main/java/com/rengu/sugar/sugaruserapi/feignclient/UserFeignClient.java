package com.rengu.sugar.sugaruserapi.feignclient;

import com.rengu.sugar.sugaruserapi.entity.DepartmentEntity;
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
    @GetMapping(value = "/user/has-email")
    boolean hasUserByEmail(@RequestParam(value = "email") String email);

    // 添加用户
    @PostMapping(value = "/user")
    void saveUser(UserEntity userEntity);

    // 查询所有用户
    @GetMapping(value = "/user")
    List<UserEntity> getUsers();

    // 根据用户名查询用户
    @GetMapping(value = "/user/by-username")
    UserEntity getUserByUsername(@RequestParam(value = "username") String username);

    // 根据Id查询用户
    @GetMapping(value = "/user/{userId}")
    UserEntity getUserById(@PathVariable(value = "userId") String userId);

    // 根据id修改用户
    @PutMapping(value = "/user/{userId}")
    UserEntity updateUserById(@PathVariable(value = "userId") String userId, UserEntity userEntity);

    // 根据Id修改密码
    @PutMapping(value = "/user/{userId}/password")
    UserEntity updatePasswordById(@PathVariable(value = "userId") String userId, @RequestParam(value = "password") String password);

    // 根据Id修改角色
    @PutMapping(value = "/user/{userId}/role")
    UserEntity updateRoleById(@PathVariable(value = "userId") String userId, @RequestParam(value = "roleId") String roleId);

    // 删除用户
    @DeleteMapping(value = "/user/{userId}")
    UserEntity deleteUserById(@PathVariable(value = "userId") String userId);

    // 根据Id激活用户
    @PutMapping(value = "/user/{userId}/active")
    void updateEmailStateById(@PathVariable(value = "userId") String userId);

    // 根据邮箱及验证码查询用户
    @GetMapping(value = "/user/activeCode/{activeCode}")
    boolean getUserByEmailAndCode(@RequestParam(value = "email") String email, @PathVariable(value = "activeCode") String activeCode);


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
    @PutMapping(value = "/role/{roleId}")
    RoleEntity updateRoleById(@PathVariable(value = "roleId") String id, RoleEntity roleEntity);

    // 根据id删除角色
    @DeleteMapping(value = "/role/{roleId}")
    RoleEntity deleteRoleById(@PathVariable(value = "roleId") String id);


    /**
     * 部门相关
     */
    // 保存部门
    @PostMapping(value = "/department")
    DepartmentEntity saveDepartment(@RequestBody DepartmentEntity departmentEntity);

    // 查询所有用户
    @GetMapping(value = "/department")
    List<DepartmentEntity> getDepartments();

    // 根据Id修改部门
    @PutMapping(value = "/department/{departmentId}")
    DepartmentEntity updateDepartmentById(@PathVariable(value = "departmentId") String departmentId, @RequestBody DepartmentEntity departmentEntity);

    // 删除部门
    @DeleteMapping(value = "/department/{departmentId}")
    DepartmentEntity deleteDepartmentById(@PathVariable(value = "departmentId") String departmentId);

    // 保存部门成员
    @PostMapping(value = "/department/{departmentId}/saveMember")
    DepartmentEntity saveMemberById(@PathVariable(value = "departmentId") String departmentId, @RequestParam(value = "ids") String[] ids);

    // 删除部门成员
    @PutMapping(value = "/department/{departmentId}/deleteMember")
    DepartmentEntity deleteDepartmentMemberById(@PathVariable(value = "departmentId") String departmentId, @RequestBody String userId);

}
