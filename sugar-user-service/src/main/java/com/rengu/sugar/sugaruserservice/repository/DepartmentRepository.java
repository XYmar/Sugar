package com.rengu.sugar.sugaruserservice.repository;

import com.rengu.sugar.sugaruserservice.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {
    boolean existsByName(String name);
}
