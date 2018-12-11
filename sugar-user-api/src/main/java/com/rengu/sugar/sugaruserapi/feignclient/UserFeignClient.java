package com.rengu.sugar.sugaruserapi.feignclient;

import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Component
@FeignClient(name = "sugar-user-service")
public interface UserFeignClient {
    // 添加用户
    @PostMapping(value = "/user")
    UserEntity saveUser(UserEntity userEntity);

    // 查询所有用户
    @GetMapping(value = "/user")
    List<UserEntity> getUsers();

    // 根据Id查询用户
   /* @GetMapping(value = "/user/{userId}")
    UserEntity getUserById(@PathVariable(value = "userId") String userId);*/
}
