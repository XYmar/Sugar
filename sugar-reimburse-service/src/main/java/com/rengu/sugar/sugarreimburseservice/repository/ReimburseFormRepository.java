package com.rengu.sugar.sugarreimburseservice.repository;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimburseFormRepository extends JpaRepository<ReimburseFormEntity, String> {
    List<ReimburseFormEntity> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
