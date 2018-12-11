package com.rengu.sugar.sugaruserapi.feignclient;

import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "sugar-user-service")
public interface UserFeignClient {
    // 添加用户
    @PostMapping(value = "/user")
    UserEntity saveUser(UserEntity userEntity);
}
