package com.rengu.sugar.sugaruserservice.repository;

import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByTelephoneNum(String telephoneNum);

    Optional<UserEntity> findByEmail(String email);
}
