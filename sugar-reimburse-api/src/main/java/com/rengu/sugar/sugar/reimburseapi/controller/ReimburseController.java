package com.rengu.sugar.sugar.reimburseapi.controller;

import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseEntity;
import com.rengu.sugar.sugar.reimburseapi.service.ReimburseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/25 9:56
 */
@RestController
@RequestMapping(value = "/reimburse")
public class ReimburseController {
    private final ReimburseService reimburseService;

    @Autowired
    public ReimburseController(ReimburseService reimburseService) {
        this.reimburseService = reimburseService;
    }

    // 保存一条报销记录
    @PostMapping(value = "byReimburseFormId/{reimburseFormId}")
    public ReimburseEntity saveReimburse(@PathVariable(value = "reimburseFormId") String reimburseFormId, ReimburseEntity reimburseEntity) {
        return reimburseService.saveReimburse(reimburseFormId, reimburseEntity);
    }

    // 根据报销单id查询所有报销记录
    @GetMapping(value = "/byReimburseFormId/{reimburseFormId}")
    public List<ReimburseEntity> getReimbursesByReimburseFormId(@PathVariable(value = "reimburseFormId") String reimburseFormId) {
        return reimburseService.getReimbursesByReimburseFormId(reimburseFormId);
    }

    // 根据Id修改某条报销记录
    @PutMapping(value = "/{reimburseId}")
    public ReimburseEntity updateReimburseById(@PathVariable(value = "reimburseId") String reimburseId, ReimburseEntity reimburseEntity) {
        return reimburseService.updateReimburseById(reimburseId, reimburseEntity);
    }

    // 根据id删除报销记录
    @DeleteMapping(value = "/{reimburseId}")
    public ReimburseEntity deleteReimburseById(@PathVariable(value = "reimburseId") String reimburseId) {
        return reimburseService.deleteReimburseById(reimburseId);
    }
}
