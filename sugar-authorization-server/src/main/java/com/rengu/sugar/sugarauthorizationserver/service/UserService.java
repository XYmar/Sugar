package com.rengu.sugar.sugarauthorizationserver.service;

import com.rengu.sugar.sugarauthorizationserver.entity.UserEntity;
import com.rengu.sugar.sugarauthorizationserver.feignclient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        if (!userFeignClient.hasUserByUsername(s)) {
            throw new UsernameNotFoundException(s + "该用户名不存在");
        }
        return getUserByUsername(s);
    }

    // 调用用户服务，根据用户名查询用户
    public UserEntity getUserByUsername(String username) {
        return userFeignClient.getUserByUsername(username);
    }
}
