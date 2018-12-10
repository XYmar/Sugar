package com.rengu.sugar.sugarauthorizationserver.feignclient;

import com.rengu.sugar.sugarauthorizationserver.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "sugar-user-service")
public interface UserFeignClient {

    @GetMapping(value = "/user/by-username")
    UserEntity getUserByUsername(@RequestParam(value = "username") String username);

    @GetMapping(value = "/user/has-username")
    boolean hasUserByUsername(@RequestParam(value = "username") String username);
}
