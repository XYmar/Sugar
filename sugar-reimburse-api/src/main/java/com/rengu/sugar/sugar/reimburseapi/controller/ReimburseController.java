package com.rengu.sugar.sugar.reimburseapi.controller;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
import com.rengu.sugar.sugar.reimburseapi.service.ReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/20 16:59
 */
@Controller
@RequestMapping(value = "/reimburse")
public class ReimburseController {
    private final ReimburseService reimburseService;

    @Autowired
    public ReimburseController(ReimburseService reimburseService) {
        this.reimburseService = reimburseService;
    }

    // 保存一条报销记录
    @PostMapping
    public ReimburseEntity saveReimburse(ReimburseEntity reimburseEntity) {
        return reimburseService.saveReimburse(reimburseEntity);
    }

    // 查询某用户的所有报销记录
    @GetMapping
    public List<ReimburseEntity> getReimburseByUserId(String userId) {
        return reimburseService.getReimburseByUserId(userId);
    }

    // 根据Id修改某条报销记录
    @PutMapping(value = "/{reimburseId}")
    public ReimburseEntity updateReimburseById(@PathVariable(value = "reimburseId") String reimburseId, ReimburseEntity reimburseEntity) {
        return reimburseService.updateReimburseById(reimburseId, reimburseEntity);
    }

    // 删除用户
    @DeleteMapping(value = "/{reimburseId}")
    public ReimburseEntity deleteUserById(@PathVariable(value = "reimburseId") String reimburseId) {
        return reimburseService.deleteReimburseById(reimburseId);
    }
}
