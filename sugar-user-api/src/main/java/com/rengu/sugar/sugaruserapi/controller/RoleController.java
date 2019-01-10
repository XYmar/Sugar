package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.Utils.ResultUtils;
import com.rengu.sugar.sugaruserapi.entity.ResultEntity;
import com.rengu.sugar.sugaruserapi.entity.RoleEntity;
import com.rengu.sugar.sugaruserapi.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "添加角色信息", notes = "添加角色信息")
    @PostMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity saveRole(RoleEntity roleEntity) {
        return ResultUtils.build(roleService.saveRole(roleEntity));
    }

    // 查询所有角色
    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity getRoles() {
        return ResultUtils.build(roleService.getAll());
    }

    // 根据id查询角色
    @ApiOperation(value = "根据id查询角色", notes = "根据id查询角色")
    @GetMapping(value = "/{roleId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity getRoleById(@PathVariable(value = "roleId") String id) {
        return ResultUtils.build(roleService.getRoleById(id));
    }

    // 根据id修改角色
    @ApiOperation(value = "根据id修改角色", notes = "根据id修改角色")
    @PutMapping(value = "/{roleId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity updateRoleById(@PathVariable(value = "roleId") String id, RoleEntity roleEntity) {
        return ResultUtils.build(roleService.updateRoleById(id, roleEntity));
    }

    // 根据id删除角色
    @ApiOperation(value = "根据id删除角色", notes = "根据id删除角色")
    @DeleteMapping(value = "/{roleId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity deleteRoleById(@PathVariable(value = "roleId") String id) {
        return ResultUtils.build(roleService.deleteRoleById(id));
    }
}
