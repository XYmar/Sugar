package com.rengu.sugar.sugaruserapi.feignclient;

import com.rengu.sugar.sugaruserapi.entity.RoleEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "sugar-user-service")
public interface RoleFeignClient {
    // 添加角色信息
    @PostMapping
    RoleEntity saveRole(RoleEntity roleEntity);

    // 查询所有角色
    @GetMapping
    List<RoleEntity> getRoles();

    // 根据id查询角色
    @GetMapping(value = "/{roleId}")
    RoleEntity getRoleById(@PathVariable(value = "roleId") String id);

    // 根据id修改角色
    @PatchMapping(value = "/{roleId}")
    RoleEntity updateRoleById(@PathVariable(value = "roleId") String id, RoleEntity roleEntity);

    // 根据id删除角色
    @DeleteMapping(value = "/{roleId}")
    RoleEntity deleteRoleById(@PathVariable(value = "roleId") String id);
}
