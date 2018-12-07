package com.rengu.sugar.sugaruserservice.service;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.repository.RoleRepository;
import com.rengu.sugar.sugaruserservice.utils.ApplicationMessage;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    // 保存角色角色
    @CachePut(value = "Role_Cache", key = "#roleEntity.getName()")
    public RoleEntity saveRole(RoleEntity roleEntity) {
        if (StringUtils.isEmpty(roleEntity.getName())) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_ARGS_NOT_FOUND);
        }
        if (hasRoleByName(roleEntity.getName())) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_EXISTED + roleEntity.getName());
        }
        return roleRepository.save(roleEntity);
    }

    // 查询所有角色
    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    // 检查角色名称是否已存在
    public boolean hasRoleByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return roleRepository.existsByName(name);
    }

    // 根据角色名称查询角色
    @Cacheable(value = "Role_Cache", key = "#name")
    public RoleEntity findRoleByName(String name) {
        if (!hasRoleByName(name)) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_NOT_FOUND + name);
        }
        return roleRepository.findByName(name);
    }

    // 根据Id查询角色信息
    @Cacheable(value = "Role_Cache", key = "#roleId")
    public RoleEntity getRoleById(String roleId) {
        if (!hasRoleById(roleId)) {
            throw new RuntimeException(ApplicationMessage.ROLE_ID_NOT_FOUND + roleId);
        }
        return roleRepository.findById(roleId).get();
    }

    // 根据Id修改角色信息
    @CachePut(value = "Role_Cache", key = "#roleId")
    public RoleEntity updateRoleById(String roleId, RoleEntity roleEntityArgs) {
        if (StringUtils.isEmpty(roleId)) {
            throw new RuntimeException(ApplicationMessage.USER_ID_NOT_FOUND);
        }
        RoleEntity roleEntity = getRoleById(roleId);
        if (roleEntityArgs == null) {
            throw new RuntimeException(ApplicationMessage.USER_ARGS_NOT_FOUND);
        }

        if (!StringUtils.isEmpty(roleEntityArgs.getName()) && !roleEntity.getName().equals(roleEntityArgs.getName())) {
            if (hasRoleByName(roleEntityArgs.getName())) {
                throw new RuntimeException(ApplicationMessage.ROLE_NAME_EXISTED + roleEntityArgs.getName());
            }
            roleEntity.setName(roleEntityArgs.getName());
        }

        if (!StringUtils.isEmpty(roleEntityArgs.getDescription())) {
            roleEntity.setDescription(roleEntityArgs.getDescription());
        }

        return roleRepository.save(roleEntity);
    }

    // 根据id删除角色信息
    @CacheEvict(value = "Role_Cache", key = "#roleId")
    public RoleEntity deleteRoleById(String roleId) {
        RoleEntity RoleEntity = getRoleById(roleId);
        roleRepository.delete(RoleEntity);
        return RoleEntity;
    }

    // 根据Id判断角色是否存在
    public boolean hasRoleById(String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            return false;
        }
        return roleRepository.existsById(roleId);
    }
}
