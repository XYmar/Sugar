package com.rengu.sugar.sugaruserservice.service;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import com.rengu.sugar.sugaruserservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity saveUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
