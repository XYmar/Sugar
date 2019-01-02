package com.rengu.sugar.sugarreimburseservice.repository;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseEntity;
import com.rengu.sugar.sugarreimburseservice.entity.ReimburseFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimburseRepository extends JpaRepository<ReimburseEntity, String> {
    List<ReimburseEntity> findByReimburseFormEntity(ReimburseFormEntity reimburseFormEntity);

    void deleteAllByReimburseFormEntity(ReimburseFormEntity reimburseFormEntity);
}
