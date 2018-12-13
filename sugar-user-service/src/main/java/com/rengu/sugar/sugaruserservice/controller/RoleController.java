package com.rengu.sugar.sugaruserservice.controller;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public RoleEntity saveRole(@RequestBody RoleEntity roleEntity) {
        return roleService.saveRole(roleEntity);
    }

    // 查询所有角色
    @GetMapping
    public List<RoleEntity> getRoles() {
        return roleService.getAll();
    }

    // 根据id查询角色
    @GetMapping(value = "/{roleId}")
    public RoleEntity getRoleById(@PathVariable(value = "roleId") String id) {
        return roleService.getRoleById(id);
    }

    // 根据id修改角色
    @PatchMapping(value = "/{roleId}")
    public RoleEntity updateRoleById(@PathVariable(value = "roleId") String id, RoleEntity roleEntity) {
        return roleService.updateRoleById(id, roleEntity);
    }

    // 根据id删除角色
    @DeleteMapping(value = "/{roleId}")
    public RoleEntity deleteRoleById(@PathVariable(value = "roleId") String id) {
        return roleService.deleteRoleById(id);
    }
}
