package com.rengu.sugar.sugarreimburseservice.service;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseEntity;
import com.rengu.sugar.sugarreimburseservice.repository.ReimburseRepository;
import com.rengu.sugar.sugarreimburseservice.utils.ReimburseMessage;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Author: XYmar
 * Date: 2018/12/20 15:29
 */
@Service
@Slf4j
public class ReimburseService {
    private final ReimburseRepository reimburseRepository;

    @Autowired
    public ReimburseService(ReimburseRepository reimburseRepository) {
        this.reimburseRepository = reimburseRepository;
    }

    // 保存一条报销记录
    @CachePut(value = "Reimburse_Cache", key = "#reimburseEntity.getId()")
    public ReimburseEntity saveReimburse(ReimburseEntity reimburseEntity) {
        if (reimburseEntity == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getDepartmentName())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_DEPARTMENTNAME_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getUsage())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USAGE_ARGS_NOT_FOUND);
        }

        if (reimburseEntity.getMoney() == 0) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_MONEY_ARGS_NOT_FOUND);
        }

        if (reimburseEntity.getUseTime() == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USETIME_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getUserId())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USERID_ARGS_NOT_FOUND);
        }

        return reimburseRepository.save(reimburseEntity);
    }

    // 修改一条报销记录
    @CachePut(value = "Reimburse_Cache", key = "#reimburseId")
    public ReimburseEntity updateReimburseById(String reimburseId, ReimburseEntity reimburseEntityArgs) {
        if (StringUtils.isEmpty(reimburseId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_ID_ARGS_NOT_FOUND);
        }

        ReimburseEntity reimburseEntity = getReimburseById(reimburseId);

        if (reimburseEntityArgs == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_ARGS_NOT_FOUND);
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getDepartmentName()) && !reimburseEntityArgs.getDepartmentName().equals(reimburseEntityArgs.getDepartmentName())) {
            reimburseEntity.setDepartmentName(reimburseEntityArgs.getDepartmentName());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getType()) && !reimburseEntityArgs.getType().equals(reimburseEntityArgs.getType())) {
            reimburseEntity.setType(reimburseEntityArgs.getType());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getUsage()) && !reimburseEntityArgs.getUsage().equals(reimburseEntityArgs.getUsage())) {
            reimburseEntity.setUsage(reimburseEntityArgs.getUsage());
        }

        if (!(reimburseEntityArgs.getUseTime() == null) && !reimburseEntityArgs.getUseTime().equals(reimburseEntityArgs.getUseTime())) {
            reimburseEntity.setUseTime(reimburseEntityArgs.getUseTime());
        }

        if (!(reimburseEntityArgs.getDays() == reimburseEntityArgs.getDays())) {
            reimburseEntity.setDays(reimburseEntityArgs.getDays());
        }

        if (!(reimburseEntityArgs.getMoney() == 0) && !(reimburseEntityArgs.getMoney() == (reimburseEntityArgs.getMoney()))) {
            reimburseEntity.setMoney(reimburseEntityArgs.getMoney());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getDescription()) && !reimburseEntityArgs.getDescription().equals(reimburseEntityArgs.getDescription())) {
            reimburseEntity.setDescription(reimburseEntityArgs.getDescription());
        }

        return reimburseRepository.save(reimburseEntity);
    }

    // 根据userId查询改用户的所有报销信息
    @Cacheable(value = "Reimburse_Cache", key = "#userId")
    public List<ReimburseEntity> getReimburseByUserId(String userId) {
        if (!hasReimburseByUserId(userId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USERID_ARGS_NOT_FOUND + userId);
        }
        return reimburseRepository.findByUserId(userId);
    }

    // 根据Id删除一条报销记录
    @CacheEvict(value = "Reimburse_Cache", key = "#reimburseId")
    public ReimburseEntity deleteReimburseById(String reimburseId) {
        ReimburseEntity reimburseEntity = getReimburseById(reimburseId);
        reimburseRepository.delete(reimburseEntity);
        return reimburseEntity;
    }

    // 根据userId查询报销是否存在
    public boolean hasReimburseByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return false;
        }
        return reimburseRepository.existsByUserId(userId);
    }

    // 根据id查询报销是否存在
    public boolean hasReimburseById(String reimburseId) {
        if (StringUtils.isEmpty(reimburseId)) {
            return false;
        }
        return reimburseRepository.existsById(reimburseId);
    }

    // 根据Id查询用户信息
    @Cacheable(value = "Reimburse_Cache", key = "#reimburseId")
    public ReimburseEntity getReimburseById(String reimburseId) {
        if (!hasReimburseById(reimburseId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USERID_ARGS_NOT_FOUND + reimburseId);
        }
        return reimburseRepository.findById(reimburseId).get();
    }
}
