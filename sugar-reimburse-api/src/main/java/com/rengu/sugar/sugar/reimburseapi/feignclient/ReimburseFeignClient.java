package com.rengu.sugar.sugar.reimburseapi.feignclient;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseFormEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Component
@FeignClient(name = "sugar-reimburse-service")
public interface ReimburseFeignClient {
    /**
     * 报销单相关
     */
    // 新建一个报销单
    @PostMapping(value = "/reimburse_form")
    ReimburseFormEntity saveReimburseForm(ReimburseFormEntity reimburseFormEntity);

    // 查询所有报销单
    @GetMapping(value = "/reimburse_form")
    List<ReimburseFormEntity> getReimburseForms();

    // 查询所有报销单
    @GetMapping(value = "/reimburse_form/by_user/{userId}")
    List<ReimburseFormEntity> getReimburseFormsByUserId(@PathVariable(value = "userId") String userId);

    // 根据报销单id查询报销单
    @GetMapping(value = "/reimburse_form/byReimburseFormId/{reimburseFormId}")
    ReimburseFormEntity getReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId);

    // 根据Id修改某条报销单信息
    @PutMapping(value = "/reimburse_form/{reimburseFormId}")
    ReimburseFormEntity updateReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId, ReimburseFormEntity reimburseFormEntity);

    // 根据id删除报销单
    @DeleteMapping(value = "/reimburse_form/{reimburseFormId}")
    ReimburseFormEntity deleteReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId);

}
