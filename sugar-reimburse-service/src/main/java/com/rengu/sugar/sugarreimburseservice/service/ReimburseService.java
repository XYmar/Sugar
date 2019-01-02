package com.rengu.sugar.sugarreimburseservice.service;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseEntity;
import com.rengu.sugar.sugarreimburseservice.entity.ReimburseFormEntity;
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
 * Date: 2018/12/25 9:39
 */
@Service
@Slf4j
public class ReimburseService {
    private final ReimburseRepository reimburseRepository;
    private final ReimburseFormService reimburseFormService;

    @Autowired
    public ReimburseService(ReimburseRepository reimburseRepository, ReimburseFormService reimburseFormService) {
        this.reimburseRepository = reimburseRepository;
        this.reimburseFormService = reimburseFormService;
    }

    // 根据报销单id，保存一条报销记录
    @CachePut(value = "Reimburse_Cache", key = "#reimburseEntity.getId()")
    public ReimburseEntity saveReimburse(String reimburseFormId, ReimburseEntity reimburseEntity) {
        if (StringUtils.isEmpty(reimburseFormId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ID_ARGS_NOT_FOUND);
        }

        ReimburseFormEntity reimburseFormEntity = reimburseFormService.getReimburseFormById(reimburseFormId);

        if (reimburseEntity == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getDepartmentName())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_DEPARTMENTNAME_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getDescription())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_DESCRIPTION_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getForUsage())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USAGE_ARGS_NOT_FOUND);
        }

        if (reimburseEntity.getMoney() == 0) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_MONEY_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getType())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_TYPE_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseEntity.getUseTime())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_USETIME_ARGS_NOT_FOUND);
        }

        reimburseEntity.setReimburseFormEntity(reimburseFormEntity);

        return reimburseRepository.save(reimburseEntity);
    }

    // 根据报销单id，查询报销记录
    @Cacheable(value = "Reimburse_Cache", key = "#reimburseId")
    public List<ReimburseEntity> getReimbursesByReimburseFormId(String reimburseFormId) {
        if (!reimburseFormService.hasReimburseFormById(reimburseFormId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ID_ARGS_NOT_FOUND + reimburseFormId);
        }
        ReimburseFormEntity reimburseFormEntity = reimburseFormService.getReimburseFormById(reimburseFormId);
        return reimburseRepository.findByReimburseFormEntity(reimburseFormEntity);
    }

    // 修改一条报销记录
    @CachePut(value = "Reimburse_Cache", key = "#reimburseId")
    public ReimburseEntity updateReimburseById(String reimburseId, ReimburseEntity reimburseEntityArgs) {
        if (StringUtils.isEmpty(reimburseId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ID_ARGS_NOT_FOUND);
        }

        ReimburseEntity reimburseEntity = getReimburseById(reimburseId);

        if (reimburseEntityArgs == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ARGS_NOT_FOUND);
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getDepartmentName()) && !reimburseEntity.getDepartmentName().equals(reimburseEntityArgs.getDepartmentName())) {
            reimburseEntity.setDepartmentName(reimburseEntityArgs.getDepartmentName());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getDescription()) && !reimburseEntity.getDescription().equals(reimburseEntityArgs.getDescription())) {
            reimburseEntity.setDescription(reimburseEntityArgs.getDescription());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getForUsage()) && !reimburseEntity.getForUsage().equals(reimburseEntityArgs.getForUsage())) {
            reimburseEntity.setForUsage(reimburseEntityArgs.getForUsage());
        }

        if (!(reimburseEntityArgs.getMoney() == 0) && !(reimburseEntity.getMoney() == reimburseEntityArgs.getMoney())) {
            reimburseEntity.setMoney(reimburseEntityArgs.getMoney());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getType()) && !reimburseEntity.getType().equals(reimburseEntityArgs.getType())) {
            reimburseEntity.setType(reimburseEntityArgs.getType());
        }

        if (!StringUtils.isEmpty(reimburseEntityArgs.getUseTime()) && !reimburseEntity.getUseTime().equals(reimburseEntityArgs.getUseTime())) {
            reimburseEntity.setUseTime(reimburseEntityArgs.getUseTime());
        }

        return reimburseRepository.save(reimburseEntity);
    }

    // 根据Id删除一条报销记录
    @CacheEvict(value = "Reimburse_Cache", key = "#reimburseId")
    public ReimburseEntity deleteReimburseById(String reimburseId) {
        ReimburseEntity reimburseEntity = getReimburseById(reimburseId);
        reimburseRepository.delete(reimburseEntity);
        return reimburseEntity;
    }

    // 根据Id查询报销单信息
    @Cacheable(value = "Reimburse_Cache", key = "#reimburseId")
    public ReimburseEntity getReimburseById(String reimburseId) {
        if (!hasReimburseById(reimburseId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSE_ID_ARGS_NOT_FOUND + reimburseId);
        }
        return reimburseRepository.findById(reimburseId).get();
    }

    // 根据id查询报销是否存在
    public boolean hasReimburseById(String reimburseId) {
        if (StringUtils.isEmpty(reimburseId)) {
            return false;
        }
        return reimburseRepository.existsById(reimburseId);
    }

}
