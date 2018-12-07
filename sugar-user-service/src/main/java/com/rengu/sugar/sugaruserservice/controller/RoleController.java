package com.rengu.sugar.sugaruserservice.controller;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public RoleEntity saveRole(RoleEntity roleEntity) {
        return roleService.saveRole(roleEntity);
    }

    // 查询所有角色
    @GetMapping
    public List<RoleEntity> getRoles() {
        return roleService.getAll();
    }
}
