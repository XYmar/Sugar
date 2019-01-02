package com.rengu.sugar.sugaruserservice.repository;

import com.rengu.sugar.sugaruserservice.entity.RoleEntity;
import com.rengu.sugar.sugaruserservice.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    boolean existsByUsername(String username);

    Optional<UserEntity> findByTelephoneNum(String telephoneNum);

    boolean existsByEmail(String email);

    boolean existsByActiveCode(String activeCode);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByRoleEntities(RoleEntity roleEntity);

    UserEntity findByEmail(String email);

    UserEntity findByActiveCode(String activeCode);
}
