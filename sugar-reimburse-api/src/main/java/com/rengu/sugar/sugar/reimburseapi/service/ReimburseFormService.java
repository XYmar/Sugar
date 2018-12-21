package com.rengu.sugar.sugar.reimburseapi.service;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseFormEntity;
import com.rengu.sugar.sugar.reimburseapi.feignclient.ReimburseFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/21 11:26
 */
@Service
@Slf4j
public class ReimburseFormService {
    private final ReimburseFeignClient reimburseFeignClient;

    @Autowired
    public ReimburseFormService(ReimburseFeignClient reimburseFeignClient) {
        this.reimburseFeignClient = reimburseFeignClient;
    }

    public ReimburseFormEntity saveReimburseForm(ReimburseFormEntity reimburseFormEntity) {
        return reimburseFeignClient.saveReimburseForm(reimburseFormEntity);
    }

    public List<ReimburseFormEntity> getAll() {
        return reimburseFeignClient.getReimburseForms();
    }

    public ReimburseFormEntity updateReimburseFormById(String reimburseFormId, ReimburseFormEntity reimburseFormEntity) {
        return reimburseFeignClient.updateReimburseFormById(reimburseFormId, reimburseFormEntity);
    }

    public ReimburseFormEntity deleteReimburseFormById(String reimburseFormId) {
        return reimburseFeignClient.deleteReimburseFormById(reimburseFormId);
    }

    public List<ReimburseFormEntity> getReimburseFormByUserId(String userId) {
        return reimburseFeignClient.getReimburseFormsByUserId(userId);
    }
}
