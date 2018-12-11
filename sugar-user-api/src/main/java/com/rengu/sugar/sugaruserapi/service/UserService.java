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

    // 添加用户
    public UserEntity saveUser(UserEntity userEntity) {
        return userFeignClient.saveUser(userEntity);
    }

    // 查询所有用户
    public List<UserEntity> getUsers() {
        return userFeignClient.getUsers();
    }

    // 根据id查询用户
    public UserEntity getUserById(String userId) {
        return userFeignClient.getUserById(userId);
    }
}
