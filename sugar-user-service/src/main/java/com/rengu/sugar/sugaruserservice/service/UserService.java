package com.rengu.sugar.sugaruserservice.service;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.repository.UserRepository;
import com.rengu.sugar.sugaruserservice.utils.ApplicationMessage;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    @Value("${config.defaultUserRoleName}")
    private String defaultUserRoleName;
    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    // 保存管理员用户
    public UserEntity saveUser(UserEntity userEntity, RoleEntity... roleEntities) {
        Set<RoleEntity> roleEntitySet = userEntity.getRoleEntities();
        if (roleEntitySet != null) {
            roleEntitySet.addAll(Arrays.asList(roleEntities));
        } else {
            roleEntitySet = new HashSet<>(Arrays.asList(roleEntities));
        }
        userEntity.setRoleEntities(roleEntitySet);
        return userRepository.save(userEntity);
    }

    // 保存用户
    @CachePut(value = "User_Cache", key = "#userEntity.getId()")
    public UserEntity saveUser(UserEntity userEntity) {
        if (userEntity == null) {
            throw new RuntimeException(ApplicationMessage.USER_ARGS_NOT_FOUND);
        }
        if (StringUtils.isEmpty(userEntity.getUsername())) {
            throw new RuntimeException(ApplicationMessage.USER_USERNAME_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(userEntity.getPassword())) {
            throw new RuntimeException(ApplicationMessage.USER_PASSWORD_ARGS_NOT_FOUND);
        }
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntity.getPassword()));

        if (StringUtils.isEmpty(userEntity.getTelephoneNum())) {
            throw new RuntimeException(ApplicationMessage.USER_TELEPHONENUM_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(userEntity.getEmail())) {
            throw new RuntimeException(ApplicationMessage.USER_EMAIL_ARGS_NOT_FOUND);
        }

        RoleEntity role = roleService.findRoleByName(defaultUserRoleName);
        HashSet<RoleEntity> set = new HashSet<>();
        set.add(role);
        userEntity.setRoleEntities(set);

        //去重
        if (hasUserByUsername(userEntity.getUsername())) {
            throw new RuntimeException(ApplicationMessage.USER_USERNAME_EXISTED + userEntity.getUsername());
        }

        if (hasUserByTelephoneNum(userEntity.getTelephoneNum())) {
            throw new RuntimeException(ApplicationMessage.USER_TELEPHONENUM_EXISTED);
        }

        if (hasUserByEmail(userEntity.getEmail())) {
            throw new RuntimeException(ApplicationMessage.USER_EMAIL_EXISTED);
        }

        return userRepository.save(userEntity);
    }

    // 查询所有用户
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    // 根据Id查询用户信息
    @Cacheable(value = "User_Cache", key = "#userId")
    public UserEntity getUserById(String userId) {
        if (!hasUserById(userId)) {
            throw new RuntimeException(ApplicationMessage.USER_ID_NOT_FOUND + userId);
        }
        return userRepository.findById(userId).get();
    }

    // 根据Id修改用户
    @CachePut(value = "User_Cache", key = "#userId")
    public UserEntity updateUserById(String userId, UserEntity userEntityArgs) {
        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException(ApplicationMessage.USER_ID_NOT_FOUND);
        }
        UserEntity userEntity = getUserById(userId);
        if (userEntityArgs == null) {
            throw new RuntimeException(ApplicationMessage.USER_ARGS_NOT_FOUND);
        }

        if (!StringUtils.isEmpty(userEntityArgs.getUsername()) && !userEntity.getUsername().equals(userEntityArgs.getUsername())) {
            if (hasUserByUsername(userEntityArgs.getUsername())) {
                throw new RuntimeException(ApplicationMessage.USER_USERNAME_EXISTED + userEntityArgs.getUsername());
            }
            userEntity.setUsername(userEntityArgs.getUsername());
        }
        if (!StringUtils.isEmpty(userEntityArgs.getTelephoneNum()) && !userEntity.getTelephoneNum().equals(userEntityArgs.getTelephoneNum())) {
            if (hasUserByTelephoneNum(userEntityArgs.getTelephoneNum())) {
                throw new RuntimeException(ApplicationMessage.USER_TELEPHONENUM_EXISTED);
            }
            userEntity.setTelephoneNum(userEntityArgs.getTelephoneNum());
        }
        if (!StringUtils.isEmpty(userEntityArgs.getEmail()) && !userEntity.getEmail().equals(userEntityArgs.getEmail())) {
            if (hasUserByEmail(userEntityArgs.getEmail())) {
                throw new RuntimeException(ApplicationMessage.USER_EMAIL_EXISTED);
            }
            userEntity.setEmail(userEntityArgs.getEmail());
        }

        if (!StringUtils.isEmpty(userEntityArgs.getPassword())) {
            userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntityArgs.getPassword()));
        }
        return userRepository.save(userEntity);
    }

    // 根据id修改密码
    @CachePut(value = "User_Cache", key = "#userId")
    public UserEntity updateUserPasswordById(String userId, String password) {
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException(ApplicationMessage.USER_PASSWORD_ARGS_NOT_FOUND);
        }
        UserEntity userEntity = getUserById(userId);
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));
        return userRepository.save(userEntity);
    }

    // 根据Id删除用户
    @CacheEvict(value = "User_Cache", key = "#userId")
    public UserEntity deleteUserById(String userId) {
        UserEntity userEntity = getUserById(userId);
        userRepository.delete(userEntity);
        return userEntity;
    }

    // 根据Id判断用户是否存在
    public boolean hasUserById(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return false;
        }
        return userRepository.existsById(userId);
    }

    // 根据用户名查询用户是否存在
    public boolean hasUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return false;
        }
        return userRepository.existsByUsername(username);
    }

    // 根据联系方式查询用户是否存在
    public boolean hasUserByTelephoneNum(String telephoneNumber) {
        if (StringUtils.isEmpty(telephoneNumber)) {
            return false;
        }
        return userRepository.findByTelephoneNum(telephoneNumber).isPresent();
    }

    // 根据邮箱查询用户是否存在
    public boolean hasUserByEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        return userRepository.findByEmail(email).isPresent();
    }
}
