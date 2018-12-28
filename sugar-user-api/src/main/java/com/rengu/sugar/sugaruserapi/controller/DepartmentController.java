package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.Utils.ResultUtils;
import com.rengu.sugar.sugaruserapi.entity.DepartmentEntity;
import com.rengu.sugar.sugaruserapi.entity.ResultEntity;
import com.rengu.sugar.sugaruserapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Author: XYmar
 * Date: 2018/12/19 16:04
 */
@RestController
@RequestMapping(value = "/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    // 保存部门
    @PostMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity saveDepartment(DepartmentEntity departmentEntity) {
        return ResultUtils.build(departmentService.saveDepartment(departmentEntity));
    }

    // 查询所有用户
    @GetMapping
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity getDepartments() {
        return ResultUtils.build(departmentService.getAll());
    }

    // 根据Id修改部门
    @PutMapping(value = "/{departmentId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity updateDepartmentById(@PathVariable(value = "departmentId") String departmentId, DepartmentEntity departmentEntity) {
        return ResultUtils.build(departmentService.updateDepartmentById(departmentId, departmentEntity));
    }

    // 删除部门
    @DeleteMapping(value = "/{departmentId}")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity deleteDepartmentById(@PathVariable(value = "departmentId") String departmentId) {
        return ResultUtils.build(departmentService.deleteDepartmentById(departmentId));
    }

    // 保存部门成员
    @PostMapping(value = "/{departmentId}/saveMember")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity saveMemberById(@PathVariable(value = "departmentId") String departmentId, String[] ids) {
        return ResultUtils.build(departmentService.saveMemberById(departmentId, ids));
    }

    // 删除部门成员
    @PutMapping(value = "/{departmentId}/deleteMember")
    @PreAuthorize(value = "hasRole('admin')")
    public ResultEntity deleteDepartmentMemberById(@PathVariable(value = "departmentId") String departmentId, String userId) {
        return ResultUtils.build(departmentService.deleteDepartmentMemberById(departmentId, userId));
    }
}
