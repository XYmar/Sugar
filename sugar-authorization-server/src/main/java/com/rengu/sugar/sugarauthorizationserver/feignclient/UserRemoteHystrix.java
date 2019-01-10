package com.rengu.sugar.sugarauthorizationserver.feignclient;

import com.rengu.sugar.sugarauthorizationserver.entity.UserEntity;
import org.springframework.stereotype.Component;

/**
 * Author: XYmar
 * Date: 2019/1/10 16:48
 */
@Component
public class UserRemoteHystrix implements UserFeignClient{
    @Override
    public UserEntity getUserByUsername(String username) {
        return null;
    }

    @Override
    public boolean hasUserByUsername(String username) {
        return false;
    }
}
