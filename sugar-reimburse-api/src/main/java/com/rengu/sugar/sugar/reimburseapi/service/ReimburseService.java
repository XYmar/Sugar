package com.rengu.sugar.sugar.reimburseapi.service;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
import com.rengu.sugar.sugar.reimburseapi.feignclient.ReimburseFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/25 9:54
 */
@Service
@Slf4j
public class ReimburseService {
    private final ReimburseFeignClient reimburseFeignClient;

    @Autowired
    public ReimburseService(ReimburseFeignClient reimburseFeignClient) {
        this.reimburseFeignClient = reimburseFeignClient;
    }

    public ReimburseEntity saveReimburse(String reimburseFormId, ReimburseEntity reimburseEntity) {
        return reimburseFeignClient.saveReimburse(reimburseFormId, reimburseEntity);
    }

    public List<ReimburseEntity> getReimbursesByReimburseFormId(String reimburseFormId) {
        return reimburseFeignClient.getReimbursesByReimburseFormId(reimburseFormId);
    }

    public ReimburseEntity updateReimburseById(String reimburseId, ReimburseEntity reimburseEntity) {
        return reimburseFeignClient.updateReimburseById(reimburseId, reimburseEntity);
    }

    public ReimburseEntity deleteReimburseById(String reimburseId) {
        return reimburseFeignClient.deleteReimburseById(reimburseId);
    }
}
