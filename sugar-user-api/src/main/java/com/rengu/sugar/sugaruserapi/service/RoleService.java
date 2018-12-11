package com.rengu.sugar.sugaruserapi.service;

import com.rengu.sugar.sugaruserapi.entity.RoleEntity;
import com.rengu.sugar.sugaruserapi.feignclient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/11 15:12
 */
@Slf4j
@Service
public class RoleService {
    private final UserFeignClient userFeignClient;

    @Autowired
    public RoleService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    // 保存角色角色
    public RoleEntity saveRole(RoleEntity roleEntity) {
        return userFeignClient.saveRole(roleEntity);
    }

    // 查询所有角色
    public List<RoleEntity> getAll() {
        return userFeignClient.getRoles();
    }

    // 根据Id查询角色信息
    public RoleEntity getRoleById(String roleId) {
        return userFeignClient.getRoleById(roleId);
    }

    // 根据Id修改角色信息
    public RoleEntity updateRoleById(String roleId, RoleEntity roleEntityArgs) {
        return userFeignClient.updateRoleById(roleId, roleEntityArgs);
    }

    // 根据id删除角色信息
    public RoleEntity deleteRoleById(String roleId) {
        return userFeignClient.deleteRoleById(roleId);
    }

}
