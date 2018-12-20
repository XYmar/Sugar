package com.rengu.sugar.sugaruserservice.controller;

import com.rengu.sugar.sugaruserservice.entity.DepartmentEntity;
import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.service.DepartmentService;
import com.rengu.sugar.sugaruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/19 15:09
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    private final UserService userService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, UserService userService) {
        this.departmentService = departmentService;
        this.userService = userService;
    }

    // 保存部门
    @PostMapping
    @PreAuthorize(value = "hasRole('admin')")
    public DepartmentEntity saveDepartment(@RequestBody DepartmentEntity departmentEntity) {
        return departmentService.saveDepartment(departmentEntity);
    }

    // 保存部门成员
    @PostMapping(value = "/{departmentId}/saveMember")
    @PreAuthorize(value = "hasRole('admin')")
    public DepartmentEntity saveMemberById(@PathVariable(value = "departmentId") String departmentId, @RequestParam(value = "ids") String[] ids) {
        List<UserEntity> userEntityList = new ArrayList<>();
        for (String id : ids) {
            userEntityList.add(userService.getUserById(id));
        }
        return departmentService.saveMemberById(departmentId, userEntityList);
    }

    // 查询所有用户
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public List<DepartmentEntity> getDepartments() {
        return departmentService.getAll();
    }

    // 根据Id修改部门
    @PutMapping(value = "/{departmentId}")
    @PreAuthorize(value = "hasRole('admin')")
    public DepartmentEntity updateDepartmentById(@PathVariable(value = "departmentId") String departmentId, @RequestBody DepartmentEntity departmentEntity) {
        return departmentService.updateDepartmentById(departmentId, departmentEntity);
    }

    // 删除部门
    @DeleteMapping(value = "/{departmentId}")
    @PreAuthorize(value = "hasRole('admin')")
    public DepartmentEntity deleteDepartmentById(@PathVariable(value = "departmentId") String departmentId) {
        return departmentService.deleteDepartmentById(departmentId);
    }
}
