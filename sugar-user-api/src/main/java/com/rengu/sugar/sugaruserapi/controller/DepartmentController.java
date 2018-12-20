package com.rengu.sugar.sugaruserapi.controller;

import com.rengu.sugar.sugaruserapi.entity.DepartmentEntity;
import com.rengu.sugar.sugaruserapi.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
        return departmentService.saveDepartment(departmentEntity);
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
    public DepartmentEntity updateDepartmentById(@PathVariable(value = "departmentId") String departmentId, DepartmentEntity departmentEntity) {
        return departmentService.updateDepartmentById(departmentId, departmentEntity);
    }

    // 删除部门
    @DeleteMapping(value = "/{departmentId}")
    @PreAuthorize(value = "hasRole('admin')")
    public DepartmentEntity deleteDepartmentById(@PathVariable(value = "departmentId") String departmentId) {
        return departmentService.deleteDepartmentById(departmentId);
    }

    // 保存部门成员
    @PostMapping(value = "/{departmentId}/saveMember")
    @PreAuthorize(value = "hasRole('admin')")
    public DepartmentEntity saveMemberById(@PathVariable(value = "departmentId") String departmentId, String[] ids) {
        return departmentService.saveMemberById(departmentId, ids);
    }
}
