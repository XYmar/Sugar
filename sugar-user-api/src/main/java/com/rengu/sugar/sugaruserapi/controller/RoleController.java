package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.Utils.ResultUtils;
import com.rengu.sugar.sugaruserapi.entity.ResultEntity;
import com.rengu.sugar.sugaruserapi.entity.RoleEntity;
import com.rengu.sugar.sugaruserapi.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/11 15:21
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // 添加角色信息
    @PostMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity saveRole(RoleEntity roleEntity) {
        return ResultUtils.build(roleService.saveRole(roleEntity));
    }

    // 查询所有角色
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public List<RoleEntity> getRoles() {
        return roleService.getAll();
    }

    // 根据id查询角色
    @GetMapping(value = "/{roleId}")
    @PreAuthorize(value = "hasRole('admin')")
    public RoleEntity getRoleById(@PathVariable(value = "roleId") String id) {
        return roleService.getRoleById(id);
    }

    // 根据id修改角色
    @PutMapping(value = "/{roleId}")
    @PreAuthorize(value = "hasRole('admin')")
    public RoleEntity updateRoleById(@PathVariable(value = "roleId") String id, RoleEntity roleEntity) {
        return roleService.updateRoleById(id, roleEntity);
    }

    // 根据id删除角色
    @DeleteMapping(value = "/{roleId}")
    @PreAuthorize(value = "hasRole('admin')")
    public RoleEntity deleteRoleById(@PathVariable(value = "roleId") String id) {
        return roleService.deleteRoleById(id);
    }
}
