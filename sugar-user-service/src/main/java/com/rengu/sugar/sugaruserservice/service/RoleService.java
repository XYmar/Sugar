package com.rengu.sugar.sugaruserservice.service;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.repository.RoleRepository;
import com.rengu.sugar.sugaruserservice.utils.ApplicationMessage;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 保存用户角色
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

    // 查询所有用户
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
    public RoleEntity getRoleByName(String name) {
        if (!hasRoleByName(name)) {
            throw new RuntimeException(ApplicationMessage.ROLE_NAME_NOT_FOUND + name);
        }
        return roleRepository.findByName(name);
    }
}
