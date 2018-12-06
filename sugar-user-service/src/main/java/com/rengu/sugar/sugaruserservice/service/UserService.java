package com.rengu.sugar.sugaruserservice.service;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.repository.UserRepository;
import com.rengu.sugar.sugaruserservice.utils.ApplicationMessage;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // 保存用户
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
            throw new RuntimeException(ApplicationMessage.USER_TelephoneNum_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(userEntity.getEmail())) {
            throw new RuntimeException(ApplicationMessage.USER_Email_ARGS_NOT_FOUND);
        }

        //去重
        if (hasUserByUsername(userEntity.getUsername())) {
            throw new RuntimeException(ApplicationMessage.USER_USERNAME_EXISTED + userEntity.getUsername());
        }

        if (hasUserByTelephoneNum(userEntity.getTelephoneNum())) {
            throw new RuntimeException(ApplicationMessage.USER_TelephoneNum_EXISTED);
        }

        if (hasUserByEmail(userEntity.getEmail())) {
            throw new RuntimeException(ApplicationMessage.USER_Email_EXISTED);
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
    @CacheEvict(value = "User_Cache", allEntries = true)
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
                throw new RuntimeException(ApplicationMessage.USER_TelephoneNum_EXISTED);
            }
            userEntity.setTelephoneNum(userEntityArgs.getTelephoneNum());
        }
        if (!StringUtils.isEmpty(userEntityArgs.getEmail()) && !userEntity.getEmail().equals(userEntityArgs.getEmail())) {
            if (hasUserByEmail(userEntityArgs.getEmail())) {
                throw new RuntimeException(ApplicationMessage.USER_Email_EXISTED);
            }
            userEntity.setEmail(userEntityArgs.getEmail());
        }

        if (!StringUtils.isEmpty(userEntityArgs.getPassword())) {
            userEntity.setPassword(new BCryptPasswordEncoder().encode(userEntityArgs.getPassword()));
        }
        return userRepository.save(userEntity);
    }

    // 根据Id删除用户
    @CacheEvict(value = "User_Cache", allEntries = true)
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
