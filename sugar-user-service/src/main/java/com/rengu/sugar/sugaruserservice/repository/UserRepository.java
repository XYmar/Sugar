package com.rengu.sugar.sugaruserservice.repository;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findAllByUsername(String username);
}
