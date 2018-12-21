package com.rengu.sugar.sugar.reimburseapi.feignclient;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "sugar-reimburse-service")
public interface ReimburseFeignClient {
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
    ReimburseEntity updateReimburseById(@PathVariable(value = "reimburseId") String reimburseId, @RequestBody ReimburseEntity reimburseEntity);

    // 删除用户
    @DeleteMapping(value = "/reimburse/{reimburseId}")
    ReimburseEntity deleteUserById(@PathVariable(value = "reimburseId") String reimburseId);
}
