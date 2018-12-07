package com.rengu.sugar.sugaruserservice.utils;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.service.RoleService;
import com.rengu.sugar.sugaruserservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = -1)
public class ApplicationInit implements ApplicationRunner {

    private final RoleService roleService;
    private final UserService userService;
    @Value("${config.defaultAdminRoleName}")
    private String defaultAdminRoleName;
    @Value("${config.defaultUserRoleName}")
    private String defaultUserRoleName;
    @Value("${config.defaultAdminUsername}")
    private String defaultAdminUsername;
    @Value("${config.defaultAdminPassword}")
    private String defaultAdminPassword;

    @Autowired
    public ApplicationInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        // 初始化系统角色
        if (!roleService.hasRoleByName(defaultAdminRoleName)) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(defaultAdminRoleName);
            roleEntity.setDescription("默认的管理员角色");
            roleService.saveRole(roleEntity);
        }
        if (!roleService.hasRoleByName(defaultUserRoleName)) {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(defaultUserRoleName);
            roleEntity.setDescription("默认的用户角色");
            roleService.saveRole(roleEntity);
        }
        // 初始化角色
        if (!userService.hasUserByUsername(defaultAdminUsername)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername(defaultAdminUsername);
            userEntity.setPassword(defaultAdminPassword);
            userService.saveUser(userEntity, roleService.findRoleByName(defaultAdminRoleName), roleService.findRoleByName(defaultUserRoleName));
        }
    }
}
