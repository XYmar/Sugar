package com.rengu.sugar.sugarreimburseservice.service;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseFormEntity;
import com.rengu.sugar.sugarreimburseservice.repository.ReimburseFormRepository;
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
 * Date: 2018/12/21 10:55
 */
@Service
@Slf4j
public class ReimburseFormService {
    private final ReimburseFormRepository reimburseFormRepository;

    @Autowired
    public ReimburseFormService(ReimburseFormRepository reimburseFormRepository) {
        this.reimburseFormRepository = reimburseFormRepository;
    }

    // 新建一个报销单
    @CachePut(value = "ReimburseForm_Cache", key = "#reimburseFormEntity.getId()")
    public ReimburseFormEntity saveReimburseForm(ReimburseFormEntity reimburseFormEntity) {
        if (reimburseFormEntity == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseFormEntity.getUsername())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_USERNAME_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseFormEntity.getUserId())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_USERID_ARGS_NOT_FOUND);
        }

        if (StringUtils.isEmpty(reimburseFormEntity.getDescription())) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_DESCRIPTION_ARGS_NOT_FOUND);
        }

        reimburseFormEntity.setTotalPay(0);
        return reimburseFormRepository.save(reimburseFormEntity);
    }

    // 修改一条报销记录
    @CachePut(value = "ReimburseForm_Cache", key = "#reimburseFormId")
    public ReimburseFormEntity updateReimburseFormById(String reimburseFormId, ReimburseFormEntity reimburseFormEntityArgs) {
        if (StringUtils.isEmpty(reimburseFormId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ID_ARGS_NOT_FOUND);
        }

        ReimburseFormEntity reimburseFormEntity = getReimburseFormById(reimburseFormId);

        if (reimburseFormEntityArgs == null) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ARGS_NOT_FOUND);
        }

        if (!StringUtils.isEmpty(reimburseFormEntityArgs.getUsername()) && !reimburseFormEntityArgs.getUsername().equals(reimburseFormEntityArgs.getUsername())) {
            reimburseFormEntity.setUsername(reimburseFormEntityArgs.getUsername());
        }

        if (!StringUtils.isEmpty(reimburseFormEntityArgs.getUserId()) && !reimburseFormEntityArgs.getUserId().equals(reimburseFormEntityArgs.getUserId())) {
            reimburseFormEntity.setUserId(reimburseFormEntityArgs.getUserId());
        }

        if (!StringUtils.isEmpty(reimburseFormEntityArgs.getDescription()) && !reimburseFormEntityArgs.getDescription().equals(reimburseFormEntityArgs.getDescription())) {
            reimburseFormEntity.setDescription(reimburseFormEntityArgs.getDescription());
        }

        return reimburseFormRepository.save(reimburseFormEntity);
    }

    // 查询所有报销单信息
    public List<ReimburseFormEntity> getAll() {
        return reimburseFormRepository.findAll();
    }

    // 根据Id查询报销单信息
    @Cacheable(value = "ReimburseForm_Cache", key = "#reimburseFormId")
    public ReimburseFormEntity getReimburseFormById(String reimburseFormId) {
        if (!hasReimburseFormById(reimburseFormId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_ID_ARGS_NOT_FOUND + reimburseFormId);
        }
        return reimburseFormRepository.findById(reimburseFormId).get();
    }

    // 根据用户Id查询报销单信息
    public List<ReimburseFormEntity> getReimburseFormByUserId(String userId) {
        if (!hasReimburseFormByUserId(userId)) {
            throw new RuntimeException(ReimburseMessage.REIMBURSEFORM_USERID_ARGS_NOT_FOUND + userId);
        }
        return reimburseFormRepository.findByUserId(userId);
    }

    // 根据Id删除一条报销记录
    @CacheEvict(value = "ReimburseForm_Cache", key = "#reimburseFormId")
    public ReimburseFormEntity deleteReimburseFormById(String reimburseFormId) {
        ReimburseFormEntity reimburseFormEntity = getReimburseFormById(reimburseFormId);
        reimburseFormRepository.delete(reimburseFormEntity);
        return reimburseFormEntity;
    }

    // 根据id查询报销是否存在
    public boolean hasReimburseFormById(String reimburseFormId) {
        if (StringUtils.isEmpty(reimburseFormId)) {
            return false;
        }
        return reimburseFormRepository.existsById(reimburseFormId);
    }

    // 根据userId查询报销是否存在
    public boolean hasReimburseFormByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return false;
        }
        return reimburseFormRepository.existsByUserId(userId);
    }


}
