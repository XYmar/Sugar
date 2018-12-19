package com.rengu.sugar.sugaruserapi.service;

import com.rengu.sugar.sugaruserapi.entity.DepartmentEntity;
import com.rengu.sugar.sugaruserapi.feignclient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/19 16:05
 */
@Slf4j
@Service
public class DepartmentService {
    private final UserFeignClient userFeignClient;

    @Autowired
    public DepartmentService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }


    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
        return userFeignClient.saveDepartment(departmentEntity);
    }

    public List<DepartmentEntity> getAll() {
        return userFeignClient.getDepartments();
    }

    public DepartmentEntity updateDepartmentById(String departmentId, DepartmentEntity departmentEntity) {
        return userFeignClient.updateDepartmentById(departmentId, departmentEntity);
    }

    public DepartmentEntity deleteDepartmentById(String departmentId) {
        return userFeignClient.deleteDepartmentById(departmentId);
    }
}
