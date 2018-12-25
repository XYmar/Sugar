package com.rengu.sugar.sugarreimburseservice.controller;

import com.rengu.sugar.sugarreimburseservice.entity.ReimburseFormEntity;
import com.rengu.sugar.sugarreimburseservice.service.ReimburseFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2018/12/21 11:18
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
    public ReimburseFormEntity saveReimburseForm(@RequestBody ReimburseFormEntity reimburseFormEntity) {
        return reimburseFormService.saveReimburseForm(reimburseFormEntity);
    }

    // 根据用户id查询所有报销单
    @GetMapping(value = "/by_user/{userId}")
    public List<ReimburseFormEntity> getReimburseFormsByUserId(@PathVariable(value = "userId") String userId) {
        return reimburseFormService.getReimburseFormByUserId(userId);
    }

    // 根据报销单id查询所有报销记录
    @GetMapping(value = "/byReimburseFormId/{reimburseFormId}")
    public ReimburseFormEntity getReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId) {
        return reimburseFormService.getReimburseFormById(reimburseFormId);
    }

    // 查询所有报销单
    @GetMapping
    public List<ReimburseFormEntity> getReimburseForms() {
        return reimburseFormService.getAll();
    }

    // 根据Id修改某条报销单信息
    @PutMapping(value = "/{reimburseFormId}")
    public ReimburseFormEntity updateReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId, @RequestBody ReimburseFormEntity reimburseFormEntity) {
        return reimburseFormService.updateReimburseFormById(reimburseFormId, reimburseFormEntity);
    }

    // 根据id删除报销单
    @DeleteMapping(value = "/{reimburseFormId}")
    public ReimburseFormEntity deleteReimburseFormById(@PathVariable(value = "reimburseFormId") String reimburseFormId) {
        return reimburseFormService.deleteReimburseFormById(reimburseFormId);
    }

}
