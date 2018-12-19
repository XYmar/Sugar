package com.rengu.sugar.sugaruserservice.service;

import com.rengu.sugar.sugaruserservice.entity.DepartmentEntity;
import com.rengu.sugar.sugaruserservice.repository.DepartmentRepository;
import com.rengu.sugar.sugaruserservice.utils.ApplicationMessage;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/19 14:06
 */
@Service
@Slf4j
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // 添加部门
    @CachePut(value = "Department_Cache", key = "#departmentEntity.getId()")
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {

        if (departmentEntity == null) {
            throw new RuntimeException(ApplicationMessage.DEPARTMENT_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(departmentEntity.getName())) {
            throw new RuntimeException(ApplicationMessage.DEPARTMENT_NAME_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(departmentEntity.getLeaderName())) {
            throw new RuntimeException(ApplicationMessage.DEPARTMENT_LEADERNAME_ARGS_NOT_FOUND);
        }

        // departmentEntity.setUserEntities(departmentEntity.getUserEntities());

        //去重
        if (hasDepartmentByName(departmentEntity.getName())) {
            throw new RuntimeException(ApplicationMessage.DEPARTMENT_NAME_EXISTED + departmentEntity.getName());
        }

        // 保存部门信息
        return departmentRepository.save(departmentEntity);
    }

    // 查询所有部门
    public List<DepartmentEntity> getAll() {
        return departmentRepository.findAll();
    }

    // 根据Id查询用户信息
    @Cacheable(value = "Department_Cache", key = "#departmentId")
    public DepartmentEntity getDepartmentById(String departmentId) {
        if (!hasDepartmentById(departmentId)) {
            throw new RuntimeException(ApplicationMessage.USER_ID_NOT_FOUND + departmentId);
        }
        return departmentRepository.findById(departmentId).get();
    }

    // 根据Id修改用户
    @CachePut(value = "Department_Cache", key = "#departmentId")
    public DepartmentEntity updateDepartmentById(String departmentId, DepartmentEntity departmentEntityArgs) {
        if (StringUtils.isEmpty(departmentId)) {
            throw new RuntimeException(ApplicationMessage.DEPARTMENT_ID_NOT_FOUND);
        }
        DepartmentEntity departmentEntity = getDepartmentById(departmentId);
        if (departmentEntityArgs == null) {
            throw new RuntimeException(ApplicationMessage.USER_ARGS_NOT_FOUND);
        }

        if (!StringUtils.isEmpty(departmentEntityArgs.getName()) && !departmentEntity.getName().equals(departmentEntityArgs.getName())) {
            if (hasDepartmentByName(departmentEntityArgs.getName())) {
                throw new RuntimeException(ApplicationMessage.DEPARTMENT_NAME_EXISTED + departmentEntityArgs.getName());
            }
            departmentEntity.setName(departmentEntityArgs.getName());
        }

        if (!StringUtils.isEmpty(departmentEntityArgs.getLeaderName()) && !departmentEntity.getLeaderName().equals(departmentEntityArgs.getLeaderName())) {
            departmentEntity.setLeaderName(departmentEntityArgs.getLeaderName());
        }

        if (!StringUtils.isEmpty(departmentEntityArgs.getDescription()) && (departmentEntity.getDescription() == null || !departmentEntity.getDescription().equals(departmentEntityArgs.getDescription()))) {
            departmentEntity.setDescription(departmentEntityArgs.getDescription());
        }

        departmentEntity.setUserEntities(departmentEntityArgs.getUserEntities());
        return departmentRepository.save(departmentEntity);
    }

    // 根据Id删除部门
    @CacheEvict(value = "Department_Cache", key = "#departmentId")
    public DepartmentEntity deleteDepartmentById(String departmentId) {
        DepartmentEntity departmentEntity = getDepartmentById(departmentId);
        departmentRepository.delete(departmentEntity);
        return departmentEntity;
    }

    // 根据名称查询部门是否存在
    public boolean hasDepartmentByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return departmentRepository.existsByName(name);
    }

    // 根据Id判断用户是否存在
    public boolean hasDepartmentById(String departmentId) {
        if (StringUtils.isEmpty(departmentId)) {
            return false;
        }
        return departmentRepository.existsById(departmentId);
    }
}
