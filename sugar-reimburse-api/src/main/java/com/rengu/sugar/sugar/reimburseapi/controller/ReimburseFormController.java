package com.rengu.sugar.sugar.reimburseapi.controller;

import com.rengu.sugar.sugar.reimburseapi.Utils.ResultUtils;
import com.rengu.sugar.sugar.reimburseapi.entity.ReimburseFormEntity;
import com.rengu.sugar.sugar.reimburseapi.entity.ResultEntity;
import com.rengu.sugar.sugar.reimburseapi.service.ReimburseFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author: XYmar
 * Date: 2018/12/21 11:27
 */
@RestController
@RequestMapping(value = "/reimburse_form")
public class ReimburseFormController {
    private final ReimburseFormService reimburseFormService;

    @Autowired
    public ReimburseFormController(ReimburseFormService reimburseFormService) {
        this.reimburseFormService = reimburseFormService;
    }

    // 新建一个报销单
    @PostMapping
    public ResultEntity saveReimburseForm(ReimburseFormEntity reimburseFormEntity) {
        return ResultUtils.build(reimburseFormService.saveReimburseForm(reimburseFormEntity));
    }

    // 查询所有报销单
    @GetMapping
    public ResultEntity getReimburseForms() {
        return ResultUtils.build(reimburseFormService.getAll());
    }

    // 查询所有报销单
    @GetMapping(value = "/by_user/{userId}")
    public ResultEntity getReimburseFormsByUserId(@PathVariable(value = "userId") String userId) {
        return ResultUtils.build(reimburseFormService.getReimburseFormByUserId(userId));
    }

    // 根据报销单id查询所有报销记录
    @GetMapping(value = "/byReimburseFormId/{reimburseFormId}")
    public ResultEntity getReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId) {
        return ResultUtils.build(reimburseFormService.getReimburseFormById(reimburseFormId));
    }

    // 根据Id修改某条报销单信息
    @PutMapping(value = "/{reimburseFormId}")
    public ResultEntity updateReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId, ReimburseFormEntity reimburseFormEntity) {
        return ResultUtils.build(reimburseFormService.updateReimburseFormById(reimburseFormId, reimburseFormEntity));
    }

    // 根据id删除报销单
    @DeleteMapping(value = "/{reimburseFormId}")
    public ResultEntity deleteReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId) {
        return ResultUtils.build(reimburseFormService.deleteReimburseFormById(reimburseFormId));
    }
}
