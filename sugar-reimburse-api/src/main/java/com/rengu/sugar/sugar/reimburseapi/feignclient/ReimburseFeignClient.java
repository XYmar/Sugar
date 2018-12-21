package com.rengu.sugar.sugar.reimburseapi.feignclient;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
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

    // 根据Id修改某条报销单信息
    @PutMapping(value = "/reimburse_form/{reimburseFormId}")
    ReimburseFormEntity updateReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId, ReimburseFormEntity reimburseFormEntity);

    // 根据id删除报销单
    @DeleteMapping(value = "/reimburse_form/{reimburseFormId}")
    ReimburseFormEntity deleteReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId);
    
    /**
     * 报销相关
     */
// 保存一条报销记录
    @PostMapping(value = "/reimburse")
    ReimburseEntity saveReimburse(@RequestBody ReimburseEntity reimburseEntity);

    // 查询某用户的所有报销记录
    @GetMapping(value = "/reimburse")
    List<ReimburseEntity> getReimburseByUserId(String userId);

    // 根据Id修改某条报销记录
    @PutMapping(value = "/reimburse/{reimburseId}")
    ReimburseEntity updateReimburseById(@PathVariable(value = "reimburseId") String reimburseId, ReimburseEntity reimburseEntity);

    // 删除用户
    @DeleteMapping(value = "/reimburse/{reimburseId}")
    ReimburseEntity deleteUserById(@PathVariable(value = "reimburseId") String reimburseId);
}
