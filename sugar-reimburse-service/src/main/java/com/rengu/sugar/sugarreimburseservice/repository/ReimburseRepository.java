package com.rengu.sugar.sugarreimburseservice.repository;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimburseRepository extends JpaRepository<ReimburseEntity, String> {
    boolean existsByUserId(String userId);

    List<ReimburseEntity> findByUserId(String userId);
}
