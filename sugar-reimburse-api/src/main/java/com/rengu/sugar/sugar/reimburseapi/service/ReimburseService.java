package com.rengu.sugar.sugar.reimburseapi.service;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
import com.rengu.sugar.sugar.reimburseapi.feignclient.ReimburseFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/20 16:56
 */
@Service
@Slf4j
public class ReimburseService {
    private final ReimburseFeignClient reimburseFeignClient;

    @Autowired
    public ReimburseService(ReimburseFeignClient reimburseFeignClient) {
        this.reimburseFeignClient = reimburseFeignClient;
    }

    public ReimburseEntity saveReimburse(ReimburseEntity reimburseEntity) {
        return reimburseFeignClient.saveReimburse(reimburseEntity);
    }

    public List<ReimburseEntity> getReimburseByUserId(String userId) {
        return reimburseFeignClient.getReimburseByUserId(userId);
    }

    public ReimburseEntity updateReimburseById(String reimburseId, ReimburseEntity reimburseEntity) {
        return reimburseFeignClient.updateReimburseById(reimburseId, reimburseEntity);
    }


    public ReimburseEntity deleteReimburseById(String reimburseId) {
        return reimburseFeignClient.deleteUserById(reimburseId);
    }
}
