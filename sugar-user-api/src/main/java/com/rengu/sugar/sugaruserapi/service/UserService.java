package com.rengu.sugar.sugaruserapi.service;

import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import com.rengu.sugar.sugaruserapi.feignclient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserFeignClient userFeignClient;

    @Autowired
    public UserService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    // 判断邮箱是否已被注册
    public boolean hasUserByEmail(String email) {
        return userFeignClient.hasUserByEmail(email);
    }

    // 添加用户
    public void saveUser(UserEntity userEntity) {
        userFeignClient.saveUser(userEntity);
    }

    // 查询所有用户
    public List<UserEntity> getUsers() {
        return userFeignClient.getUsers();
    }

    // 根据id查询用户
    public UserEntity getUserById(String userId) {
        return userFeignClient.getUserById(userId);
    }

    // 根据id修改用户
    public UserEntity updateUserById(String userId, UserEntity userEntity) {
        return userFeignClient.updateUserById(userId, userEntity);
    }

    // 根据id删除用户
    public UserEntity deleteUserById(String userId) {
        return userFeignClient.deleteUserById(userId);
    }

    // 根据Id修改密码
    public UserEntity updatePasswordById(String userId, String password) {
        return userFeignClient.updatePasswordById(userId, password);
    }

    // 根据id修改角色
    public UserEntity updateUserRoleById(String userId, String roleId) {
        return userFeignClient.updateRoleById(userId, roleId);
    }
}
